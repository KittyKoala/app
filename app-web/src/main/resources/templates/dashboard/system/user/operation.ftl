<@ca.link_group>
    <@ca.link name="编辑" href="${baseUrl}/{{row.userId}}/edit" class="btn btn-xs btn-inverse" modal="myModal"/>
    <@ca.drop_group>
        <@ca.link name="设置角色" drop=true href="${baseUrl}/{{row.userId}}/roles" modal="myModal"/>
        <@ca.link name="设置版主" drop=true href="${baseUrl}/{{row.userId}}/forum" modal="myModal"/>
        <@ca.link name="修改密码" drop=true href="${baseUrl}/{{row.userId}}/password" modal="myModal"/>
        <@ca.link name="同步ID" drop=true href="${baseUrl}/{{row.userId}}/sync" type="confirm" title="同步用户的游戏ID"/>
        {{if row.isDeleted==1}}
            <@ca.link name="物理删除" drop=true href="${baseUrl}/{{row.userId}}/remove" type="confirm" title="物理删除用户"/>
        {{else}}
        <@ca.link name="物理删除" drop=true class="disabled"/>
        {{/if}}
    </@ca.drop_group>
</@ca.link_group>