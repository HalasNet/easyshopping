<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%> 
<%@ taglib prefix="common" uri="http://ibm/commons" %>   

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//CN">
<html>
<head>
<title>easyshopping search test</title>
<script type="text/javascript" src="../js/autocomplete/lib/jquery.js"></script>
<script type='text/javascript' src='../js/autocomplete/lib/jquery.bgiframe.min.js'></script>
<script type='text/javascript' src='../js/autocomplete/lib/jquery.ajaxQueue.js'></script>
<script type='text/javascript' src='../js/autocomplete/lib/thickbox-compressed.js'></script>
<script type='text/javascript' src='../js/autocomplete/jquery.autocomplete.js'></script>
<script type='text/javascript' src='localdata.js'></script>
<link rel="stylesheet" type="text/css" href="main.css" />
<link rel="stylesheet" type="text/css" href="../js/autocomplete/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="../js/autocomplete/lib/thickbox.css" />
</head>
<script type="text/javascript">
$().ready(function() {

	function findValueCallback(event, data, formatted) {
		$("<li>").html( !data ? "No match!" : "Selected: " + formatted).appendTo("#result");
	}
	
	function formatItem(row) {
		return row[0] + " (<strong>id: " + row[1] + "</strong>)";
	}
	function formatResult(row) {
		return row[0].replace(/(<.+?>)/gi, '');
	}
	
	$("#kw").autocomplete(cities);
	$("#suggest13").autocomplete(emails, {
		minChars: 0,
		width: 310,
		matchContains: true,
		autoFill: false,
		formatItem: function(row, i, max) {
			return row.pro_title ;
		},
		formatResult: function(row) {
			return row.pro_title;
		}
	});
});

</script>
<body>
<form action="./test!search" method="post">
<input name="kw" id="kw" class="s_ipt" value="${ kw }" maxlength="100" >
<input type="submit" id="su" value="搜一下">
</form>
<br /><br />
	<table border="1">
		<tr>
			<th>编号</th>
			<th>标题</th>
			<th>说明</th>
			<th>价格</th>
			<th>时间</th>
			<th>高亮显示</th>
		</tr>
		<s:iterator value="searchTests" var="searchTest" status="status">
			<tr>
				<td>${ searchTest.id }</td>
				<td>${ searchTest.proTitle }</td>
				<td>${ searchTest.proDescn }</td>
				<td>${ searchTest.proPrice }</td>
				<td>${ searchTest.proTime }</td>				
				<td>${ searchTest.findResult }</td>
			</tr>
		</s:iterator>
		
	</table>
	<common:page url="/search/test" optimize="true"/>
	<common:pageV2 url="/search/test" optimize="true"/>
</body>
</html>
