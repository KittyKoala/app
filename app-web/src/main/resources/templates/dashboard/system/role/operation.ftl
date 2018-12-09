<@ca.link_group>
    <@ca.link name="编辑" href="${baseUrl}/{{row.roleId}}/edit" class="btn btn-xs btn-inverse" modal="myModal"/>
    <@ca.drop_group>
        <@ca.link name="设置菜单" drop=true href="${baseUrl}/{{row.roleId}}/menus" modal="myModal"/>
        <@ca.link name="查看用户" drop=true href="${baseUrl}/{{row.roleId}}/users" modal="myModal"/>
        {{if row.isDeleted==1}}
            <@ca.link name="物理删除" drop=true href="${baseUrl}/{{row.roleId}}/remove" type="confirm" title="物理删除角色"/>
        {{else}}
        <@ca.link name="物理删除" drop=true class="disabled"/>
        {{/if}}
    </@ca.drop_group>
</@ca.link_group>