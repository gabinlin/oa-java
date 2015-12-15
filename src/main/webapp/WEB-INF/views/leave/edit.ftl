<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/resource.ftl"/>
</head>
<body>
<div class="demo-content">
    <div class="row">
        <div class="span24">
            <form id="saveForm" class="form-horizontal" method="post" action="/leave/save">
                <input type="hidden" name="id" value="${(entity.id)!}"/>
                <div class="control-group">
                    <label class="control-label"><s>*</s>姓名：</label>
                    <div class="controls">
                        <#if (entity.employee.id)??>
                            <input type="text" readonly value="${(entity.employee.name)!}"/>
                            <input type="hidden" name="employeeId" value="${(entity.employee.id)!}"/>
                        <#else>
                            <div id="s1" data-rules="{required:true}">

                            </div>
                        </#if>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"><s>*</s>类型：</label>
                    <div class="controls">
                        <select name="type">
                            <option value="1">事假</option>
                            <option value="2">病假</option>
                            <option value="3">调休</option>
                            <option value="4">外出</option>
                            <option value="5">丧假</option>
                            <option value="6">年假</option>
                            <option value="7">婚假</option>
                            <option value="8">产假</option>
                        </select>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"><s>*</s>开始时间：</label>
                    <div class="controls">
                        <#if (entity.beginDate)??>
                            <input type="text" data-rules="{required:true}" class="calendar calendar-time" name="beginDate" value="${(entity.beginDate)?string("Y-M-d H:m:ss")}" />
                        <#else>
                            <input type="text" data-rules="{required:true}" class="calendar calendar-time" name="beginDate"  />
                        </#if>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label"><s>*</s>结束时间：</label>
                    <div class="controls">
                    <#if (entity.endDate)??>
                        <input type="text" data-rules="{required:true}" class="calendar calendar-time" name="endDate" value="${(entity.endDate)?string("Y-M-d H:m:ss")}" />
                    <#else>
                        <input type="text" data-rules="{required:true}" class="calendar calendar-time" name="endDate" />
                    </#if>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">备注：</label>
                    <div class="controls control-row-auto">
                        <textarea name="remark" class="control-row4 input-large">${(entity.remark)!}</textarea>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    BUI.use('bui/form',function  (Form) {
        new Form.Form({
            srcNode : '#saveForm',
            submitType : 'ajax',
            callback : function(data){
                if (edy.ajaxHelp.handleAjax((data))) {
                    edy.alert(data.message || "操作成功");
                    top.reload();
                }
            }
        }).render();
        var Select = BUI.Select;
        var suggest = new Select.Suggest({
            render:'#s1',
            name:'name',
            url:'/employee/suggest/name'
        });
        suggest.render();
        $("[name='name']").val("${(entity.employee.name)!}");
        $("#type").val("${(entity.type)!}");
    });
</script>
<!-- script end -->
</body>
</html>