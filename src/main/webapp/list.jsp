<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="common" uri="http://ibm.com/common/"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//CN">
<html>
<head>
<title>easyshopping</title>
</head>

<body>

	<table border="1">
		<tr>
			<th>编号</th>
			<th>用户名</th>
			<th>密码</th>
			<th>生日</th>
			<th>操作</th>
		</tr>
		<s:iterator value="#items" var="user" status="status">
			<tr>
				<td>${ t.id }</td>
				<td>${ t.userName }</td>
				<td>${ t.password }</td>
				<td>${ t.birthday }</td>
				<td><a href="<c:url value='/user/${t.id}/delete'/>">删除</a>|<a
					href="<c:url value='/user/${t.id}/update'/>">修改</a></td>
			</tr>
		</s:iterator>
		
	</table>
	<common:pageV2 url="/user" optimize="true" />
</body>
</html>
