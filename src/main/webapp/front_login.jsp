<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户登录</title>
<link rel="stylesheet" href="styles/newlogin.css" type="text/css" />
</head>
<body style="background-color:#ffffff;">
<div class="main">
 
  <div class="big">
    <div class="left">
      <div class="tbg" style="margin-top:5px;">
                    <img src="images/login_07.gif" width="371" height="7" alt="" /></div>
      <div class="mbg">
        <div class="login">
          <div class="top"> <span class="left"> <img src="images/login_15.gif" width="152"
                                    height="22" alt="用户登录" /></span> </div>
          <div class="form">
            <div class="hiddendiv">
              <div style="display: none;" id="errortips"> 请输入您的用户名</div>
            </div>
            <form id="loginForm" method="post" action="#">
              <div class="user"> <span class="text">用户名：</span>
                <input name="str_username" type="text" id="str_username" value="" class="input" tabindex="1" />
                <script type="text/javascript">document.getElementById('str_username').focus();</script>
              </div>
              <div class="user"> <span class="text">密 码：</span>
                <input name="str_userpwd" type="password" id="str_userpwd" class="input"
                                        tabindex="1" />
              </div>
             
              <div class="user1">
                <input type="checkbox" name="rememberme" checked="checked" id="rememberme" class="check" />
                <span class="font12">记住我的用户名</span> </div>
              <div class="user2">
                <input id="btnSubmit" tabindex="1" type="button" name="Submit1" value="登  陆" class=btn1_mouseout onclick="return loginSubmit()" />
                <span class="fonth"><a href="#">忘记密码？</a></span> </div>
            </form>
          </div>
        </div>
        
        <div class="bg2">
          <div class="login-w"> 您还没有注册？</div>
          <div class="floatl "> <a href="#" > <img src="images/login_08.gif" alt="注册" width="105" height="30" align="absmiddle" /></a></div>
        </div>
      </div>
      <div class="top"> <span class="left"> <img src="images/login_46.gif"  /></span>
    </div>
    
  
   </div>
   </div>
</body>
</html>
