<#assign isEdit=userDto.userId??>
<#assign modal_title="${isEdit?string('编辑用户', '添加新用户')}" />

<@override name="modal-body">
    <@ca.form id="modal-form" action="${ctx}/dashboard/system/user/${isEdit?string('update', 'save')}" token=true>
    <input type="hidden" id="old-mobileNo" value="${userDto.mobileNo!''}"/>
    <input type="hidden" id="old-email" value="${userDto.email!''}"/>

    <#if isEdit>
    <input type="hidden" name="userId" value="${userDto.userId}"/>
    </#if>
        <@ca.select id="areaCode" name="areaCode" label="区号" readonly=isEdit required=true>
            <#assign list=dict('list', 'AREA_CODE')/>
            <#if list?? && list?size gt 0>
                <#list list as dict>
                <option value="${dict.value}" <#if userDto.areaCode?? && dict.value==userDto.areaCode>selected</#if>>
                ${dict.dictCode}[${dict.value}]
                </option>
                </#list>
            </#if>
        </@ca.select>
        <@ca.input id="mobileNo" name="mobileNo" readonly=isEdit value="${userDto.mobileNo!''}" label="手机号" required=true/>

        <#if !isEdit>
            <@ca.input id="password" name="password" type="password" label="密码" required=true/>
            <@ca.input name="rePassword" type="password" label="确认密码" required=true/>
        </#if>

        <@ca.input name="name" value="${userDto.name!''}" label="姓名"/>
        <@ca.select name="idType" label="证件类型" dict_type="ID_TYPE" value="${userDto.idType!''}"/>
        <@ca.select name="rcvrEmail" label="是否接收邮件" enum_key="YesNo" value="${userDto.rcvrEmail!''}"/>
        <@ca.input id="idNo" name="idNo" value="${userDto.idNo!''}" label="证件号码"/>
        <@ca.input id="email" name="email" value="${userDto.email!''}" label="电子邮箱"/>
    </@ca.form>
</@override>

<@override name="modal-footer">
    <@ca.button name="取消" icon="fa-times" dismiss=true/>
    <@ca.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/app/js/dashboard/system/user/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>