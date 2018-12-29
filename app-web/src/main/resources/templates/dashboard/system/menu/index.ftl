<@override name="style">
<link rel="stylesheet" href="${ctx}/libs/ztree/css/zTreeStyle.css"/>
</@override>

<@override name="main">
<div class="space-10"></div>
<div class="col-sm-8 col-sm-offset-2 col-xs-12">
    <div class="widget-box widget-color-green2">
        <div class="widget-header">
            <h4 class="widget-title lighter smaller">全部菜单</h4>
        </div>

        <div class="widget-body">
            <div class="widget-main padding-8">
                <div id="menu-tree" class="ztree"></div>
            </div>
        </div>
    </div>
</div>

<form id="form" action="${_baseUrl}/delete">
    <input type="hidden" name="menuId" id="menuId"/>
<form>
</@override>

<@override name="script">
    <script src="${ctx}/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
    <script src="${ctx}/libs/ztree/js/jquery.ztree.exedit-3.5.min.js"></script>
    <script>
        $(function () {
            var zNodes = [
                {id: 0, pId: -1, name: "根节点", open: true},
                <#list allMenus as menu>
                    {
                        id:${menu.menuId},
                        pId:${menu.pid},
                        code: "${menu.menuCode}",
                        name: "${menu.menuName}",
                        open: true
                    },
                </#list>];

            var $form = $('#form');

            var beforeEditName = function () {
                return false;
            };

            var beforeRemove = function (treeId, treeNode) {
                if (treeNode.id <= 0) {
                    return false;
                }
                $.messager.confirm("提示", "确定删除" + treeNode.name + "吗?", function () {
                    $("#menuId").val(treeNode.id);
                    formSubmit($form, null, function () {
                        var treeObj = $.fn.zTree.getZTreeObj("menu-tree");
                        treeObj.removeNode(treeNode);
                    })

                });

                return false;
            };

            var addHoverDom = function (treeId, treeNode) {
                var sObj = $("#" + treeNode.tId + "_span");
                if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) {
                    return;
                }
                var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                        + "' title='add'></span>";
                sObj.after(addStr);

                var addBtn = $("#addBtn_" + treeNode.tId);
                if (addBtn) {
                    addBtn.bind("click", function () {
                        $("#myModal").modal({
                            backdrop: 'static',
                            remote: '${_baseUrl}/create?parentCode=' + treeNode.code
                        });
                    });
                }

                if (treeNode.id > 0) {
                    var editBtn = $("#" + treeNode.tId + "_edit");
                    if (editBtn) {
                        editBtn.bind("click", function () {
                            $("#myModal").modal({
                                remote: '${_baseUrl}/' + treeNode.id + '/edit'
                            });
                        });
                    }
                }
            };

            var removeHoverDom = function (treeId, treeNode) {
                $("#addBtn_" + treeNode.tId).unbind().remove();
            };

            var setting = {
                view: {
                    addHoverDom: addHoverDom,
                    removeHoverDom: removeHoverDom
                },
                edit: {
                    enable: true
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    beforeEditName: beforeEditName,
                    beforeRemove: beforeRemove
                }
            };

            $.fn.zTree.init($("#menu-tree"), setting, zNodes);
        })
    </script>
</@override>

<@extends name="../../layout.ftl"/>