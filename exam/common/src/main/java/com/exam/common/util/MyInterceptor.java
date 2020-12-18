package com.exam.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.springframework.util.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Intercepts({
	@Signature(method = "prepare",type = StatementHandler.class,args = {
		Connection.class,Integer.class
	})
})

public class MyInterceptor implements Interceptor {
	protected static final String PAGE = "page";
	private Page<?> page;
	private static Log log = LogFactory.getLog(MyInterceptor.class);
	@Override
	public synchronized Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		try{
			RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
			StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
			BoundSql boundSql = delegate.getBoundSql();
			Object parameterObject = boundSql.getParameterObject();
			page = getPageParamsForParamObj(parameterObject);
			if(page==null){
				Object result = invocation.proceed();
				return result;
			}
			log.info("拦截sql=" + boundSql.getSql());
			//			//获取sql对应的参数

			


			if(!page.isGetAllRecord()){
					MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
					Connection connection = (Connection) invocation.getArgs()[0];
					String strSql = boundSql.getSql();
					
					this.setTotalRecord(boundSql, mappedStatement, connection);
					StringBuffer sqlBuffer = new StringBuffer(strSql);
					String pageSql = this.getMySqlPageSql(page, sqlBuffer);
					ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
					log.info("修改后的sql=" + pageSql);
				}
				

		}catch(Exception e){
			if(!e.getMessage().equals("page为空，拦截器不处理数据"))
				e.printStackTrace();
		}
		
		Object result = invocation.proceed();
	    return result;
	}



	private Page getPageParamsForParamObj(Object parameterObject) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
		Page pageParams = null;
		if (parameterObject == null) {
			return null;
		}
		//处理map参数，多个匿名参数和@Param注解参数，都是map
		if (parameterObject instanceof Map) {
			Map<String, Object> paramMap = (Map<String, Object>) parameterObject;
			Set<String> keySet = paramMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = paramMap.get(key);
				if (value instanceof Page) {
					return (Page) value;
				}
			}
		} else if (parameterObject instanceof Page) {// 参数是或者继承PageParams
			return (Page) parameterObject;
		} else{ // 从POJO属性尝试读取分页参数
			Field[] fields = parameterObject.getClass().getDeclaredFields();
			// 尝试从POJO中获得类型为PageParams的属性
			for(Field field :fields){
				if(field.getType() == Page.class){
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), parameterObject.getClass());
					Method method = pd.getReadMethod();
					return (Page) method.invoke(parameterObject);
				}
			}
		}
		return pageParams;
	}
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		/*String prop1 = properties.getProperty("prop1");
	    String prop2 = properties.getProperty("prop2");
	    System.out.println(prop1 + "------" + prop2);*/
	}
	
	/**
	 * 更换数据库版本可以通过该方法来处理，这里只处理mysql，该方法暂时不用
	 * @return
	 */
	public String getPageSql(){
		return null;
	}
	
	public synchronized String getMySqlPageSql(Page<?> page, StringBuffer sqlBuffer) {
		int offset = (page.getPageNo() - 1) * page.getPageSize();
		if (!page.isGetAllRecord())
			sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());
		return sqlBuffer.toString();
	}
	
	public synchronized String getCountSql(String sql){
		/*
		 * int index = sql.toLowerCase().indexOf("from"); String countSql =
		 * "select count(1) " + sql.substring(index); index =
		 * countSql.toLowerCase().indexOf("order by"); if(index != -1) countSql
		 * = countSql.substring(0, index - 1); index =
		 * countSql.toLowerCase().indexOf("group by"); if(index != -1) countSql
		 * = countSql.substring(0, index - 1);
		 */
		String countSql = "select count(1) from (" + sql + ") sb";
		return countSql;
	}


	public synchronized void setTotalRecord(BoundSql boundSql, MappedStatement mappedStatement, Connection connection){
		
		String sql = boundSql.getSql();
		String countSql = this.getCountSql(sql);
		System.out.println(countSql);
		BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		ReflectUtil.setFieldValue(countBoundSql, "sql", sql);
		ReflectUtil.setFieldValue(countBoundSql, "parameterMappings", boundSql.getParameterMappings());
		ReflectUtil.setFieldValue(countBoundSql, "parameterObject", boundSql.getParameterObject());
		ReflectUtil.setFieldValue(countBoundSql, "additionalParameters", ReflectUtil.getFieldValue(boundSql, "additionalParameters"));
		ReflectUtil.setFieldValue(countBoundSql, "metaParameters", ReflectUtil.getFieldValue(boundSql, "metaParameters"));
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,  boundSql.getParameterObject(), countBoundSql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pstmt =  connection.prepareStatement(countSql);
			parameterHandler.setParameters(pstmt);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				int totalRecord = rs.getInt(1);
				page.setTotalRecord(totalRecord);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
