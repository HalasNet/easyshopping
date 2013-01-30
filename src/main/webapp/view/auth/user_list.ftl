<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Easyshopping</title>
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript">
function resetQuery()
{
   location.href='product_category';
}

function del(id)
{
	if (confirm('确定要删除吗?'))
	{
		location.href='product_category!del?id=' + id;
	}
}

</script>
</head>

<body>
<s:debug/>
<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>用户管理</li>
		</span>

</div>
<div class="content_c">
  <div class="content_c_div">
  <form action="user!view" method="post" >
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="10%">用户名称</td>
        <td width="25%"><input  class="box_170" type="text" name="user.userName"></td>
        <td width="65%">
          <input name="submit" type="submit" class="select_button" value="查 询" >
          <input name="reset" type="button" class="select_button" value="重 置" onclick="resetQuery();"></td>
      </tr>
    </table>
    </form>
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
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 用户名称</td>
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 电话</td>
                          <td width="30%" align="center" class="td1"><img src="../images/normal_seq.gif"> 移动电话</td>
                          <td width="25%" align="center" class="td1"><img src="../images/normal_seq.gif"> 地址</td>
                          <td colspan="15" align="center" class="td1">操作</td>
                        </tr>
                       		<#list users as user>
							<TR bgcolor="#FFFFFF">
								<TD height="15" align="center">
									${user.username}
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
									<a href="javascript:del('${user.id}')"> <img src="../images/icon_del.gif" border="0" title="删除"></a>
								</TD>

							</TR>
					  </#list>	
    </table>
					  <!--fy start-->
					 <table width="99%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td align="left"><input type="button" value="创 建" class="select_button" onClick="location.href='user!register'">
                          </td>
                          
                         
											
                        </tr>
    </table>
					  <!--fy end-->
					  <!-- test git push not all -->
	
  </div>
</div>
</body>
</html>
