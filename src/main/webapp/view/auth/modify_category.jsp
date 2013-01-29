<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>PFMS</title>
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">
<%@  taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
</script>
</head>
<body>

<OBJECT id=dlgHelper CLASSID="clsid:3050f819-98b5-11cf-bb82-00aa00bdce0b" width="0" height="0" ></OBJECT>
<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>修改产品类别</li>
		</span>
</div>
<div class="content_c">
  <div class="content_c_div">

   <form id="authForm" action="product_category!modifyProductCategory" method="post" onsubmit="return checkSave();">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#d1d1d1">
            <tr bgcolor="#FFFFFF">
              <td bgcolor="#ebebea" align="right"><font color="red">*</font>产品类别名称： </td>
              <td><input type='hidden' id='categoryId' name='categoryId' value='<s:property value="productCategory.id"/>'/><input  class="box_170" type="text" name="productCategory.categoryName" id="categoryName" maxlength="20" value='<s:property value="productCategory.categoryName"/>'>
              <!--  input type='hidden' id='authorityId' name='authorityId' /-->
              </td>
              <td bgcolor="#ebebea" align="right">
			   <input  type="submit" class="select_button"  value="保 存">
               <input  type="button" class="select_button"  onclick="history.go(-1)" value="返 回"></td>
            </tr>	
    </table>
    </form>
  </div>
</div>
</body>
<script>
function checkSave()
{
	var categoryName = document.getElementById("categoryName").value;
	//alert(categoryName);
   
   if(categoryName=="" || categoryName ==" " || categoryName == null)
   {
      alert('产品类别名称不能为空');
      authcode.focus();
      return false;
   }
   return true;
}
</script>
</html>