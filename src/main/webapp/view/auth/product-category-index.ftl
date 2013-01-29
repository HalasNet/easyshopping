<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>CMS</title>
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">

</head>

<body>
<s:debug/>
<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>类别管理</li>
		</span>

</div>
<div class="content_c">
  <div class="content_c_div">
  <form action="product_category!queryCategoryByName" method="post" >
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="10%">类别名称</td>
        <td width="25%"><input  class="box_170" type="text" name="productCategory.categoryName" value="${categoryName}"></input> </td>
        <td width="65%">
          <input name="submit" type="submit" class="select_button" value="查 询" >
          <!--input name="reset" type="button" class="select_button" value="重 置" onclick="resetQuery();"--></td>
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
                          <td align="right" class="td1"></td>
                          <td width="65%" align="center" class="td1"><img src="../images/normal_seq.gif"> 类别名称</td>
                          <td colspan="15" align="center" class="td1">操作</td>
                        </tr>
                        <#if listCategory?size == 0>
                        	
                        	<TR bgcolor="#FFFFFF">
									<TD height="15" align="center" colspan="3">
										对不起，没有查询出相关的产品类别
									</TD>
							</TR>
                        <#else>
                        	<#list listCategory as category>
								<TR bgcolor="#FFFFFF">
									<TD height="15" align="center">
										<input type="checkbox" name="Ncheckbox" id="${category.id}" value="${category.id}"></input>
									</TD>
									<TD height="15" align="center">
										${category.categoryName}
									</TD>
									
									<TD height="15" align="center">
										<a href='product_category!modifyProductCategoryView?categoryId=${category.id}'><img src="../images/icon_edit.gif" border="0" title="修改"></a>
									</TD>       
									
									<TD height="15" align="center">
										<a href='javascript:del("${category.id}")'> <img src="../images/icon_del.gif" border="0" title="删除"></a>
									</TD>
	
								</TR>
					 		</#list>
                        </#if>
    </table>
					  <!--fy start-->
					 <table width="99%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td align="left">
                          <input type="button" value="创 建" class="select_button" onClick="location.href='product_category!addProductCategoryView'">
                          <input type="button" value="删除" class="select_button" onClick="return deleteSelect()">
                          </td>                         
											
                        </tr>
    </table>
					  <!--fy end-->
	
  </div>
</div>
</body>

<script language="JavaScript" type="text/javascript">
function del(id)
{
	if (confirm('确定要删除吗?'))
	{
		location.href='product_category!deleteProductCategory?categoryId=' + id;
	}
}


function deleteSelect()
{
	//String checkboxes[] = request.getParameterValues("checkbox");
	var vCheckbox = document.getElementsByName("Ncheckbox");
	var checkboxL = vCheckbox.length;
	var ids;
   	var j = 0;
    for(var i=0;i<checkboxL;i++)
    {   
	    if(vCheckbox[i].checked)   
	    {
	        if(ids==null)
	        {
	        	ids = vCheckbox[i].value+",";
	        }
	        else
	        {
	        	ids = ids + vCheckbox[i].value+",";
	        }
	    	j++;
   		 }     
    }
 	if (j==0)
 	{
 		alert("没有选中产品类别,请选择!");
 		return false;
 	}
 	else
 	{
 		var categoryIDs=ids.substr(0,ids.length-1);
 		alert(categoryIDs);
 		location.href='product_category!deleteBatchProductCategory?categoryId=' + categoryIDs;
 		return false;
 	}
}
</script>
</html>
