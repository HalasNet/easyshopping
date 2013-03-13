<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags"%>
<%@  taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>EasyShopping</title>
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript">
function resetQuery()
{
   location.href='authority';
}

function del(id)
{
	if (confirm('确定要删除吗?'))
	{
		location.href='authority!del?id=' + id;
	}
}

</script>
</head>

<body>
<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>权限管理</li>
		</span>

</div>
<div class="content_c">
  <div class="content_c_div">
  <form action="authority!view" method="post" >
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="10%">权限名称</td>
        <td width="25%"><input  class="box_170" type="text" name="authority.name"></td>
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
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 权限编码</td>
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 权限名称</td>
                          <td width="30%" align="center" class="td1"><img src="../images/normal_seq.gif"> 访问路径</td>
                          <td width="25%" align="center" class="td1"><img src="../images/normal_seq.gif"> 权限说明</td>
                          <td colspan="15" align="center" class="td1">操作</td>
                        </tr>
                        <s:iterator value="list" >
                        	<s:if test="parent == null">
							<TR bgcolor="#FFFFFF">
								<TD height="15" align="center">
									<s:property value="authcode" />
								</TD>
								
								<TD height="15" align="center">
									<s:property value="name" />
								</TD>
								
								<TD height="25" align="center">
									<s:property value="requestURI" />
								</TD>
								<TD height="15" align="center">
									<s:property value="comment" />
								</TD>
								
								<TD height="15" align="center">
									<a href="authority!viewModify?id=<s:property value="id"/>"><img src="../images/icon_edit.gif" border="0" title="修改"></a>
								</TD>       
								
								<TD height="15" align="center">
									<a href="javascript:del('<s:property value="id"/>')"> <img src="../images/icon_del.gif" border="0" title="删除"></a>
								</TD>

							</TR>
                        	</s:if>
					  </s:iterator>	
    </table>
					  <!--fy start-->
					 <table width="99%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td align="left"><input type="button" value="创 建" class="select_button" onClick="location.href='authority!viewCreate'">
                          </td>
                          
                         
											
                        </tr>
    </table>
					  <!--fy end-->
	
  </div>
</div>
</body>
</html>
