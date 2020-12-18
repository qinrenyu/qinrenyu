<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta name="decorator" content="default"/>
</head>
<body>
<ul class="nav nav-tabs">
</ul>
<br/>
<form id="inputForm"  action="/sys/user/save" method="post" class="form-horizontal">
    <input type="hidden" name="userId" value="${user.userId}">
    <div class="control-group">
        <label class="control-label">登录姓名:</label>
        <div class="controls">
            <input type="text" name="userName" value="${user.userName}">
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">姓名:</label>
        <div class="controls">
            <input type="text" name="trueName" value="${user.trueName}">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">密码:</label>
        <div class="controls">
            <input type="text" name="password" value="${user.password}">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">邮箱:</label>
        <div class="controls">
            <input type="text" name="email" value="${user.email}">
        </div>
    </div>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit"
                                                         value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form>
</body>
</html>