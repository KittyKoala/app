<@override name="main">
    <@form action="${ctx}/dashboard/content/sql" success="success">
        <@textarea name="sql" label="SQL脚本" required=true/>

    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button class="btn btn-success" data-type="submit" data-loading-text="正在执行...">
                <i class="ace-icon fa fa-bolt"></i>
                执行
            </button>
        </div>
    </div>
    </@form>
</@override>

<@override name="script">
<script>
    /**
     * 执行完毕的回调
     *
     * @param response
     */
    function success(response) {
        Message.success("执行成功，响应行数：" + response.rows);
    }
</script>
</@override>

<@extends name="../../layout.ftl"/>