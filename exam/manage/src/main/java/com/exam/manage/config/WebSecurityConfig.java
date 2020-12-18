package com.exam.manage.config;

import com.exam.common.util.StandardPasswordEncoderForSha1;
import com.exam.manage.security.filter.AuthenticationFilter;
import com.exam.manage.security.handler.CustomAccessDeniedHandler;
import com.exam.manage.security.handler.ExtrAuthenticationSuccessHandler;
import com.exam.manage.security.handler.MyAuthenticationEntryPoint;
import com.exam.manage.security.interceptor.MyAccessDecisionManager;
import com.exam.manage.security.interceptor.MyFilterSecurityInterceptor;
import com.exam.manage.security.interceptor.MyInvocationSecurityMetadataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyInvocationSecurityMetadataSourceService myFilterInvocationSecurityMetadataSource;


    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.antMatcher("/**").authorizeRequests();
        //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
        http .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/login","/login.html","/login-error").permitAll()
                .anyRequest()
                .authenticated()
                .and().headers().frameOptions().disable()
                .and()
                .formLogin()
                //指定登录页的路径
                .loginPage("/login-view")
                .and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login-view")
                .permitAll();
        // url权限认证处理
        registry.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                o.setAccessDecisionManager(myAccessDecisionManager);

                return o;
            }
        });
        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling()
                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/resources/**");
    }

    @Bean
     public AuthenticationFilter myAuthenticationFilter() throws Exception {
        AuthenticationFilter filter = new AuthenticationFilter();
        AuthenticationManager authenticationManager=authenticationManager();
        filter.setAuthenticationManager(authenticationManager);
        filter.setFilterProcessesUrl("/j_spring_security_check");
        ExtrAuthenticationSuccessHandler successHandler=new  ExtrAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/home");
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login-error"));
        return filter;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new StandardPasswordEncoderForSha1();
    }
}
