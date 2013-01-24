<html> 
 <body>
 <table> 
    <tr> 
        <th>角色ID</th> 
        <th>角色名称</th> 
        <th>角色备注 </th> 
        <th> 操作 </th> 
    </tr> 
    <#list model as role>  
    <tr> 
        <td>${role.id}</td> 
        <td>${role.roleName}</td> 
        <td>${role.remark}</td> 
        <td><a href="role/${role.id}"> 查看 </a> | 
        <a href="role/${role.id}/edit"> 编辑 </a> | 
        <a href="role/${role.id}/deleteConfirm"> 删除 </a></td> 
    </tr> 
     </#list>
 </table> 
 <a href="<%=request.getContextPath() %>/role/new"> 创建新角色 </a> 
 </body> 
 </html> 