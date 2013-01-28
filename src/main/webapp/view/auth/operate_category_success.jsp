<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>operate category result</title>
</head>
<body>

<div>
<!-- <s:set id="error" value="errorMsg"></s:set> -->
<s:if test='errorMsg == "" || errorMsg ==null'>
	operate category success
</s:if>
<s:else>
	<s:property value="errorMsg"/>
</s:else>
</div>
</body>
</html>