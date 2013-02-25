<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>EasyShopping</title>
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">
<%@  taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
</script>
</head>
<body>

<OBJECT id=dlgHelper CLASSID="clsid:3050f819-98b5-11cf-bb82-00aa00bdce0b" width="0" height="0" ></OBJECT>
<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>创建商品信息</li>
		</span>
</div>
<div class="content_c">
  <div class="content_c_div">
		  <table width="95%" height="23" border="0" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
            <tr>
              <td bgcolor="#DBE0E6"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="25" align="center"><img src="../images/icon_r6_c4.gif"></td>
                    <td class="td1">基本信息</td>
                  </tr>
              </table></td>
            </tr>
          </table>
   <form id="authForm" action="product!addProduct" method="post" onsubmit="return checkSave();">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#d1d1d1">
            <tr bgcolor="#FFFFFF">
              <td bgcolor="#ebebea" align="right"><font color="red">*</font>商品名称： </td>
              <td><input  class="box_170" type="text" name="product.productName" id="productName" maxlength="20" value="${product.productName}" ><input type='hidden' id='productId' name='product.id' value="${product.id}"/></td>
              <td bgcolor="#ebebea" align="right">商品主图： </td>
              <td><input  class="box_170" type="text" name="product.productLog" id="productLog"  maxlength="20" value="${product.productLog}"></td>
            </tr>	
            <tr bgcolor="#FFFFFF">
              <td bgcolor="#ebebea" align="right">商品价格：</td>
              <td><input  class="box_170" type="text" name="product.productPrice" id="productPrice"  maxlength="50" value="${product.productPrice}"></td>
              <td bgcolor="#ebebea" align="right">商品类别： </td>
              <td><s:select list="categoryList"  listValue="categoryName " listKey="id"  name="product.category.id" id="categoryId" theme="simple" value="product.category.id"></s:select></td>
            </tr>
          
    </table>
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
           <tr>
             <td height="26" align="center" bgcolor="#DBE0E6">
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
   var productName=$("#productName");
   if(productName.val()=="")
   {
      alert('商品名称不能为空');
      productName.focus();
      return false;
   }

   return true;
}
</script>
</html>
