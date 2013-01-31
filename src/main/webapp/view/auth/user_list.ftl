<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Easyshopping</title>
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${base}/js/jquery.js"></script>
</head>

<body>
<form action="user!view" method="post" >
<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>用户管理</li>
		</span>

</div>
<div class="content_c">
  <div class="content_c_div">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="10%">用户名称</td>
        <td width="25%"><input  class="box_170" type="text" name="user.userName"></td>
        <td width="65%">
          <input  type="submit" class="select_button" value="查 询" >
          <input type="reset" class="select_button" value="重 置"></td>
      </tr>
    </table>
   
  </div>
</div>

<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>查询结果</li>
		</span>

</div>

<div class="content_c">
  <div class="content_c_div2">
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1">
                        <tr>
                          <td width="5%" align="center" class="td1">选择<input type="checkbox" name="selectAll" id="selectAll"/></td>
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 用户名称</td>
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 用户组</td>
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 电话</td>
                          <td width="30%" align="center" class="td1"><img src="../images/normal_seq.gif"> 移动电话</td>
                          <td width="25%" align="center" class="td1"><img src="../images/normal_seq.gif"> 地址</td>
                          <td colspan="15" align="center" class="td1">操作</td>
                        </tr>
                       		<#list users as user>
							<TR bgcolor="#FFFFFF">
								<TD height="15" align="center">
									<input type="checkbox" name="ids" value="${user.id}"/>
								</TD>
								<TD height="15" align="center">
									${user.username}
								</TD>
								<TD height="15" align="center">
									<#list user.roles as urole>
										${urole.roleName}
									</#list>
								</TD>
								
								<TD height="15" align="center">
									${user.userAccount.phone}
								</TD>
								
								<TD height="25" align="center">
									${user.userAccount.mobile}
								</TD>
								<TD height="15" align="center">
									${user.userAccount.address}
								</TD>
								
								<TD height="15" align="center">
									<a href="user!viewModify?id=${user.id}"><img src="../images/icon_edit.gif" border="0" title="修改"></a>
								</TD>       
								
								<TD height="15" align="center">
									<a href="user!delete?id=${user.id}"> <img src="../images/icon_del.gif" border="0" title="删除"></a>
								</TD>

							</TR>
					  </#list>	
    </table>
					  <!--fy start-->
					 <table width="99%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td align="left">
                          <input type="button" value="创 建" class="select_button" onClick="location.href='user!register'">
                      	  <input type="button" id="asignRoleBtn" value="分配角色" class="select_button" >
                          </td>
                          <td align="left">
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
		$('#asignRoleBtn').click(function(){
			var $selects = $('input[name=ids]:checked');
			if($selects.length < 1){
				alert("请选择用户");
				return false;
			}else{
				var $form = $('form');
				$form.attr('action',"user!asignRole");
//				$form[0].submit;
				$('input:submit',$form).trigger("click");
				return false;
			}
		});
	});
</script>
</body>
</html>
