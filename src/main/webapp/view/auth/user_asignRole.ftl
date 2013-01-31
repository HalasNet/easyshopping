<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Easyshopping</title>
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/js/jquery.js"></script>
</head>

<body>
<s:debug/>
<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>用户管理 | 分配角色</li>
		</span>

</div>
  <form action="user!saveRole" method="post" >
<div class="content_c">
  <div class="content_c_div">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><#list users as user>${user.username}&nbsp;&nbsp;<input type="hidden" name="users[${user_index}].id" value="${user.id}"/></#list></td>
      </tr>
    </table>
 
  </div>
</div>

<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>请选择角色进行分配</li>
		</span>

</div>

<div class="content_c">
  <div class="content_c_div2">
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1">
                        <tr>
                          <td width="5%" align="center" class="td1">选择角色<input type="checkbox" name="selectAll" id="selectAll"/></td>
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 角色名称</td>
                        </tr>
                      <#list roles as role>
							<TR bgcolor="#FFFFFF">
								<TD height="15" align="center">
									<input type="checkbox" name="roles[${role_index}].id" value="${role.id}"/>
								</TD>
								<TD height="15" align="center">
									${role.roleName}
								</TD>
	

							</TR>
					  </#list>	
    </table>
					  <!--fy start-->
					 <table width="99%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td align="left">
                          <input type="submit" value="确 定" class="select_button">
                          </td>
											
                        </tr>
    </table>
					  <!--fy end-->
					  <!-- test git push not all -->
	
  </div>
</div>
   </form>
<script language="JavaScript" type="text/javascript">
	$(document).ready(function(){
		$('#selectAll').click(function(){
			var val = this.checked;
			$('input[name=ids]').each(function(){
				this.checked = val;
			});
		});
	});
</script>
</body>
</html>
