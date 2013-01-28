<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%> 
<%@ taglib prefix="common" uri="http://ibm/commons" %>   

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//CN">
<html>
<head>
<title>easyshopping product search</title>
</head>

<body>
<h3><a href="./search!crud">生成索引</a></h3>
<form action="./search!search" method="post">
<input name="kw" id="kw" class="s_ipt" value="${ kw }" maxlength="100" autocomplete="off">
<input type="submit" id="su" value="搜一下">
</form>
<br /><br />
	<table border="1">
		<tr>
			<th>编号</th>
			<th>产品名称</th>
			<th>产品LOGO</th>
			<th>价格</th>
			<th>类别</th>
			<th>发布时间</th>
			<th>高亮显示</th>
		</tr>
		<s:iterator value="productIndexs" var="productIndex" status="status">
			<tr>
				<td>${ productIndex.id }</td>
				<td>${ productIndex.productName }</td>
				<td>${ productIndex.productLog }</td>
				<td>${ productIndex.productPrice }</td>
				<td>${ productIndex.categoryName }</td>		
				<td>${ productIndex.productPublishDate }</td>				
				<td>${ productIndex.findResult }</td>
			</tr>
		</s:iterator>
		
	</table>
	<common:page url="/search" optimize="true"/>
	<common:pageV2 url="/search" optimize="true"/>
</body>
</html>
