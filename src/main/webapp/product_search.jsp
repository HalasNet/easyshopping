<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%> 
<%@ taglib prefix="common" uri="http://ibm/commons" %>   

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//CN">
<html>
<head>
<title>easyshopping product search</title>
<script type="text/javascript" src="js/autocomplete/lib/jquery.js"></script>
<script type='text/javascript' src='js/autocomplete/lib/jquery.bgiframe.min.js'></script>
<script type='text/javascript' src='js/autocomplete/lib/jquery.ajaxQueue.js'></script>
<script type='text/javascript' src='js/autocomplete/lib/thickbox-compressed.js'></script>
<script type='text/javascript' src='js/autocomplete/jquery.autocomplete.js'></script>
<script type='text/javascript' src='js/autocomplete/localdata.js'></script>
<link rel="stylesheet" type="text/css" href="js/autocomplete/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="js/autocomplete/lib/thickbox.css" />
</head>
<script type="text/javascript">
$().ready(function() {
	
	$("#kw").autocomplete('./search!autoComplete', {
		minChars: 0,
		width: 155,
		matchContains: true,
		autoFill: false,
		formatItem: function(data, i, total) {
			return data[0];
		}, 
		formatResult: function(row) { 
			return row; 
		} 
	}).result(function(event, item) { 
		$("#signupform").submit();
	}); 
});

</script>
<body>
<h3><a href="./search!crud">生成索引</a></h3>
<form  id="signupform" action="./search!search" method="post" >
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
