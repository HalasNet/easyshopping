<#macro pagingToolbar formId>
<script language="JavaScript" type="text/javascript">
 function pageinationView(pageNum) {  
            dojo.attr(dojo.byId("curPage"),"value",pageNum);
            dojo.xhrPost({ form:dojo.attr(dojo.byId("pageFormId"),"value")});
 }  
</script>
    <#assign total = pager.total />
    <#assign curPage = pager.curPage />
    <#assign prePage = pager.prePage />
    <#assign nextPage = pager.nextPage />
    <#assign totalPage = pager.totalPage />
    <#assign startNum = pager.startNum />
    <#assign endNum = pager.endNum />

    <div class="pagingToolbar">
                       共${totalPage}页   ${total}条记录
                       当前 ${curPage}页
        <a target="_self" class="page" href="javascript:pageinationView(1)">首页</a>
        <a target="_self" class="page" href="javascript:pageinationView(${prePage})">上一页</a>
        <#list startNum..endNum as i>
            <#if i==curPage>
                <span>${curPage}</span>
            <#else>
                <a target="_self" class="page" href="javascript:pageinationView(${i})">${i}</a>
            </#if>
        </#list>
        <a target="_self" class="page" href="javascript:pageinationView(${nextPage})">下一页</a>
        <a target="_self" class="page" href="javascript:pageinationView(${totalPage})">末页</a>
        <input type="hidden" name="curPage" value=1 id="curPage"/>
        <input type="hidden" id="pageFormId" value="${formId}"/>
    </div>
</#macro>