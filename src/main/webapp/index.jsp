<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//CN">
<html>
<head>
<title>easyshopping</title>
</head>

<body>
	<s:form name="login" action="login" method="post">
		<s:textfield name="userName" label="帐号"></s:textfield>
		<s:password name="password" label="密码"></s:password>
		<s:submit></s:submit>
	</s:form>
</body>
</html>