<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//CN">
<html>
<head>
<title>easyshopping</title>
</head>

<body>
<s:debug></s:debug>
	<table border="1">
		<tr>
			<th>编号</th>
			<th>用户名</th>
			<th>密码</th>
			<th>生日</th>
			<th>操作</th>
		</tr>
		<s:iterator value="users" var="user" status="status">
			<tr>
				<td>${ user.id }</td>
				<td>${ user.userName }</td>
				<td>${ user.password }</td>
				<td>${ user.birthday }</td>
				<td><a href="<c:url value='/user/${user.id}/delete'/>">删除</a>|<a
					href="<c:url value='/user/${user.id}/update'/>">修改</a></td>
			</tr>
		</s:iterator>
		
	</table>
</body>
</html>
