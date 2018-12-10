{{if value==1}}
<@confirm name="<span class='label label-danger arrowed-in'>已删除</span>"
href="${_baseUrl}/{{row.userId}}/delete/0" title="恢复用户"/>
{{else}}
<@confirm name="<span class='label label-success arrowed-in'>未删除</span>"
href="${_baseUrl}/{{row.userId}}/delete/1" title="删除用户"/>
{{/if}}