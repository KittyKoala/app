<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="设置权限" />
<link rel="stylesheet" href="${ctx}/libs/ztree/css/zTreeStyle.css"/>

<@override name="modal-body">
    <@form action="${ctx}/dashboard/system/role/${roleId}/menus" table_id="table" token=true beforeSubmit="beforeSubmit">
    <input type="hidden" name="menuIds" id="menuIds"/>
    <div class="control-group">
        <div>
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
    </@form>
</@override>

<@override name="modal-footer">
    <@submit/>
<script src="${ctx}/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/libs/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script>
    function beforeSubmit() {
        var zTree = $.fn.zTree.getZTreeObj("tree");
        var nodes = zTree.getCheckedNodes(true);
        var arr = [];
        for (var i = 0; i < nodes.length; i++) {
            arr.push(nodes[i].id);
        }
        $("#menuIds").val(arr);
    }

    $(function () {
        var zNodes = [
            <#list allMenus as menu>
                {
                    id:${menu.menuId},
                    pId:${menu.pid},
                    name: "${menu.menuName}",
                    open: true
                ${(roleMenus?? && roleMenus?seq_contains(menu.menuId))?string(", checked:true", "")}
                },
            </#list>];

        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        $.fn.zTree.init($("#tree"), setting, zNodes);
    });
</script>
</@override>

<@extends name="../../modal-layout.ftl"/>