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
		<li>修改权限信息</li>
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
   <form id="authForm" action="authority!modify" method="post" onsubmit="return checkSave();">
   		<input type="hidden" name="authority.id" value="${id }">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#d1d1d1">
            <tr bgcolor="#FFFFFF">
              <td bgcolor="#ebebea" align="right"><font color="red">*</font>权限编码： </td>
              <td><input  class="box_170" type="text" name="authority.authcode" id="authcode" maxlength="20" value="${authority.authcode}"></td>
              <td bgcolor="#ebebea" align="right"><font color="red">*</font>权限名称： </td>
              <td><input  class="box_170" type="text" name="authority.name" id="name"  maxlength="20" value="${authority.name}"></td>
            </tr>	
            <tr bgcolor="#FFFFFF">
              <td bgcolor="#ebebea" align="right">权限路径： </td>
              <td><input  class="box_170" type="text" name="authority.requestURI" id="requestURI"  maxlength="50" value="${authority.requestURI}"></td>
              <td bgcolor="#ebebea" align="right">权限说明： </td>
              <td><input  class="box_170" type="text" name="authority.comment" id="comment"  maxlength="20" value="${authority.comment}"></td>
            </tr>
            <tr bgcolor="#FFFFFF">
              <td bgcolor="#ebebea" align="right">具体操作： </td>
              <td>
              	增加<input type="checkbox" name="crud" value="_add" id="crud_add">	
              	删除<input type="checkbox" name="crud" value="_del" id="crud_del">
              	修改<input type="checkbox" name="crud" value="_update" id="crud_update">
              	查询<input type="checkbox" name="crud" value="_query" id="crud_query">
              </td>
              <td bgcolor="#ebebea" align="right">权限说明： </td>
              <td>&nbsp;</td>
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
	<s:iterator value="authority.crud" var="ac">
		<input type="hidden" class="crudValue" value="${ac.name }">
	</s:iterator>
<script>
function checkSave()
{
   var authcode=$("#authcode");
   if(authcode.val()=="")
   {
      alert('权限编码不能为空');
      authcode.focus();
      return false;
   }
   var name=$("#name");
   if(name.val()=="")
   {
      alert('权限名称不能为空');
      name.focus();
      return false;
   }

   return true;
}
$(document).ready(function(){
	$(".crudValue").each(function(i,e){
		var c = $(e).val();
		$("#crud"+c).attr('checked',"checked");
	});
});
</script>
</body>
</html>
