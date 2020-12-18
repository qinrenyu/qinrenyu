<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
					<ul class="nav default-sidenav">
						<!-- 	<li>
								<a href="admin/user-list"> <i class="fa fa-list-ul"></i> 会员管理 </a>
							</li>
							<li class="active">
								<a> <i class="fa fa-list-ul"></i> 添加会员 </a>
							</li> -->
	<c:forEach items="${leftMenuList }" var="leftMenuListItem">
		<c:if test="${leftMenuListItem.visiable eq '1'}">
			<li <c:if test="${leftMenuListItem.menuId eq leftMenuId}">class="active"</c:if>>
				<a href="${leftMenuListItem.menuPath}?leftMenuId=${leftMenuListItem.menuId}&topMenuId=${topMenuId}" title="${leftMenuListItem.menuName }"><span class="left-menu-item-name"> ${leftMenuListItem.menuName }</span></a>
			</li>
		</c:if>
	</c:forEach>
</ul>