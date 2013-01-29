<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">
<title>operate category result</title>
</head>
<body>
<div class="content_c_div">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#d1d1d1">
            <tr bgcolor="#FFFFFF">
              <td bgcolor="#ebebea" align="center">
               	<s:if test='errorMsg == "" || errorMsg ==null'>
					操作产品类别成功
				</s:if>
				<s:else>
					<s:property value="errorMsg"/>
				</s:else>
              </td>
            </tr>	
    </table>
</div>
</body>
</html>