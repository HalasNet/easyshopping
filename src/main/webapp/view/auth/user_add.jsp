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
		<li>注册新用户</li>
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
   <form id="authForm" action="user!save" method="post" onsubmit="return checkSave();">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#d1d1d1">
            <tr bgcolor="#FFFFFF">
              <td bgcolor="#ebebea" align="right"><font color="red">*</font>用户名： </td>
              <td><input  class="box_170" type="text" name="user.userName" id="username" maxlength="20" ><input type='hidden' id='authorityId' name='authorityId' /></td>
              <td bgcolor="#ebebea" align="right"><font color="red">*</font>电话： </td>
              <td><input  class="box_170" type="text" name="user.userAccount.phone" id="phone"  maxlength="20"></td>
            </tr>	
            <tr bgcolor="#FFFFFF">
              <td bgcolor="#ebebea" align="right">手机号码</td>
              <td><input  class="box_170" type="text" name="user.userAccount.mobile" id="mobile"  maxlength="20"></td>
              <td bgcolor="#ebebea" align="right">地址： </td>
              <td><input  class="box_170" type="text" name="user.userAccount.address" id="address"  maxlength="20"></td>
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


   return true;
}
</script>
</html>
