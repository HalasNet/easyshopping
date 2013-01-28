<#macro pagingToolbar action>
    <#assign ptotal = total />
    <#assign pcurPage = curPage />
    <#assign pprePage = prePage />
    <#assign pnextPage = nextPage />
    <#assign ptotalPage = totalPage />
    <#assign pstartNum = startNum />
    <#assign pendNum = endNum />

    <div class="pagingToolbar">
                       共${ptotalPage}页
        <a target="_self" href="${action + '&curPage=' + 1}" >首页</a>
        <a target="_self"  href="${action + '&curPage=' + pprePage}">上一页</a>
        <#list pstartNum..pendNum as i>
            <#if i==pcurPage>
                <span>${pcurPage}</span>
            <#else>
                <a target="_self" href="${action + '&curPage=' + i}">${i}</a>
            </#if>
        </#list>
        <a target="_self"  href="${action + '&curPage=' + pnextPage}">下一页</a>
        <a target="_self"  href="${action + '&curPage=' + ptotalPage}">末页</a>
    </div>
</#macro>