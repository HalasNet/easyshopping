<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#import "/view/common.ftl" as pagination>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>商品管理</title>
<link href="../styles/gb2312.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dojo/dojo.xd.js">
</script>
<script language="JavaScript" type="text/javascript">

dojo.ready(function() {
	dojo.connect(dojo.byId("delete"),"click",function(e) {
		if (confirm('确定要删除吗?')){
			dojo.attr(dojo.byId('productForm'),"action","product/"+dojo.attr(dojo.byId("delete"),"value")+"?_method=DELETE");
			dojo.xhrPost({ form: "productForm" ,load: handleResponse});
		}
	});
	
	dojo.connect(dojo.byId("reset"),"click",function(e) {
		location.href='product';
	});
	
	dojo.connect(dojo.byId("submit"),"click",function(e) {
		dojo.attr(dojo.byId("curPage"),"value",0);
        dojo.xhrPost({ form:dojo.attr(dojo.byId("pageFormId"),"value")});
	});

});

function handleResponse(responseText){
    // responseText为后天返回的数据
    alert("处理成功！");
    window.location.reload();
}
</script>
</head>

<body>
<div class="content_title">
		<span>
		<li><img src="../images/icon_r2_c2.gif" ></li>
		<li>商品管理</li>
		</span>

</div>
<div class="content_c">
  <div class="content_c_div">
  <form action="product!search" method="post" id="productForm">
    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="10%">商品名称</td>
        <td width="25%"><input  class="box_170" type="text" name="model.productName"></td>
        <td width="10%">商品类别</td>
        <td width="25%">
        	<select name="categoryId">
        		<option value="0">全部</option>
        		<#list categoryList as category>
        			<option  value='${category.id}' <#if (category.id == categoryId)> selected</#if>>
        				${category.categoryName}
        			</option>
        		</#list>
        	</select>
        </td>
        <td width="30%">
          <input name="submit" type="button" class="select_button" value="查 询" id="submit">
          <input name="reset" type="button" class="select_button" value="重 置" id="reset"></td>
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
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 商品名称</td>
                          <td width="15%" align="center" class="td1"><img src="../images/normal_seq.gif"> 商品主图</td>
                          <td width="30%" align="center" class="td1"><img src="../images/normal_seq.gif"> 商品价格</td>
                          <td width="25%" align="center" class="td1"><img src="../images/normal_seq.gif"> 商品类别</td>
                          <td colspan="15" align="center" class="td1">操作</td>
                        </tr>
                        <#list list as product>
							<TR bgcolor="#FFFFFF">
								<TD height="15" align="center">
									${product.productName}
								</TD>
								
								<TD height="15" align="center">
									${product.productLog}
								</TD>
								
								<TD height="25" align="center">
									${product.productPrice}
								</TD>
								<TD height="15" align="center">
									${product.category.categoryName}
								</TD>
								
								<TD height="15" align="center">
									<a href="product!addProductView?product.id=${product.id}"><img src="../images/icon_edit.gif" border="0" title="修改"></a>
								</TD>       
								
								<TD height="15" align="center">
									<a href="#" id="delete" value="${product.id}"> <img src="../images/icon_del.gif" border="0" title="删除"></a>
								</TD>

							</TR>
					  </#list>	
    </table>
					  <!--fy start-->
					 <table width="99%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                          <td align="left"><input type="button" value="创 建" class="select_button" onClick="location.href='product!addProductView'">
                          </td>
                          <td align="fight">
                          <@pagination.pagingToolbar formId="productForm" />
                          </td>			
                        </tr>
    </table>
					  <!--fy end                           -->
</form>
  </div>
</div>
</body>
</html>
