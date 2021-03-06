<li <#if category.parentCode==''>data-pathname="${category.categoryCode}"</#if>>
<#assign hasChildren=category.leaf?size gt 0/>
<#assign href=ctx + category.url/>
<#if category.url?starts_with('/')>
    <#assign href=ctx + category.url/>
<#else>
    <#assign href=category.url/>
</#if>
    <#if href==''>
        <#assign href="javascript:"/>
    </#if>

    <a href="${href}" <#if category.isBlank==1>target="_blank"</#if>>
    ${category.categoryName}<#if hasChildren && category.parentCode!=''><i class="fa fa-small fa-right fa-caret-right"></i><#elseif hasChildren && category.parentCode==''><i class="fa fa-small fa-chevron-down"></i></#if>
    </a>
<#if hasChildren>
    <ul class="${(category.parentCode!='')?string('sub-menus', 'sec-menus')} hidden">
        <#list category.leaf as category>
            <#include "categories.ftl"/>
        </#list>
    </ul>
</#if>
</li>
