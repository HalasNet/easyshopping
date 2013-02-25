<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>EasyShopping</title>
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">
<link href="../styles/dtree.css" rel="stylesheet" type="text/css">
<script  type="text/javascript" src="../js/dtree.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>

</head>
<body>

<OBJECT id=dlgHelper CLASSID="clsid:3050f819-98b5-11cf-bb82-00aa00bdce0b" width="0" height="0" ></OBJECT>
<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>创建角色信息</li>
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
   <form id="roleForm" action="role!create" method="post" onsubmit="return checkSave();">
      <table width="420" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="30%" align="right">角色名称：</td>
	        <td width="70%"><input  class="box_170" type="text" name="model.roleName" id="roleName" maxlength="20">
	        <font color="red">*</font></td>
	      </tr>
	      <tr>
	        <td align="right">权限选择：</td>
	        <td width="70%">
				<table  width="170" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				    <td width="200">
				      <script type="text/javascript">
				        d = new dTree('d');
						d.add(0,-1,'全部权限：');
						<s:iterator value="authorityList">
							<s:if test='%{parent==null }'>
								d.add(<s:property value="id" />,0,'authority','<s:property value="id" />','<s:property value="name" />');
							</s:if>
							<s:else>
								d.add(<s:property value="id" />,<s:property value="parent.id" />,'authority','<s:property value="id" />','<s:property value="name" />');
							</s:else>
						</s:iterator>
						document.write(d);
						d.openAll();
					  </script>
					  <input type="hidden" name="authorityStr" id="authorityStr">
				      </td>
				  </tr>
			</table>
        </td>
      </tr>
      <tr>
        <td align="right">角色描述：</td>
        <td width="70%"><textarea name="model.remark" id="remark" rows="6" class="box_h"></textarea></td>
      </tr>
      <tr>
        <td height="30" colspan="2" align="center">
         <input name="submit" type="submit" class="select_button" value="确 定">
         <input name="button3" type="button" class="select_button"  onclick="history.go(-1)" value="返 回"></td>
      </tr>
    </table>
    </form>
  </div>
</div>
</body>
<script>
function checkSave()
{
   var roleName=$("#roleName");
   if(roleName.val()=="")
   {
      alert('角色名称不能为空');
      roleName.focus();
      return false;
   }
	var obj = document.all.authority;	
	var strArray = "";
	for(i=0;i<obj.length;i++){
		if(obj[i].checked){
			strArray += obj[i].value +":";
		}
	}
	$("#authorityStr").val(strArray);	
   return true;
}
</script>
</html>
