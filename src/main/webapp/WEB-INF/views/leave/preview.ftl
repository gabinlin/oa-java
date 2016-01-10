<!DOCTYPE html>
<html lang="en">
<head>
<#include "../include/resource.ftl"/>
    <style>
        .bui-simple-list.bui-select-list ul{
            max-height: 500px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
<div class="row">

</div>
<div id="grid"></div>
<script>
    (function($) {
        BUI.use(['bui/grid'], function (Grid) {
            var editing = new Grid.Plugins.CellEditing({
                triggerSelected : false //触发编辑的时候不选中行
            });
            var enumName = {
                <#if allNameMapId??>
                    <#list allNameMapId?keys as name>
                        <#if name_index == 0>
                            "${allNameMapId[name]!}": "${name!}"
                        <#else>
                            ,"${allNameMapId[name]!}": "${name!}"
                        </#if>
                    </#list>
                </#if>
            };
            var enumObj = {1: "事假",2: "病假",3 : "调休", 4: "外出", 5: "丧假", 6: "年假", 7: "产假", 8: "婚假"},
//            var enumObj = {"事假": "事假","病假": "病假","调休" : "调休", "外出": "外出", "丧假": "丧假", "年假": "年假", "产假": "产假", "婚假": "婚假"},
                    columns = [
                {title: '员工', editor : {xtype : 'select', rules: {require:true},items : enumName}, dataIndex: 'id', width: 80, renderer : Grid.Format.multipleItemsRenderer(enumName)},
                {title: '核对员工', dataIndex: 'name', width: 80},
                {title: '假别', editor : {xtype :'select', rules: {require:true},select:{multipleSelect : false},items : enumObj},renderer : Grid.Format.multipleItemsRenderer(enumObj), dataIndex: 'leaveType', width: 80},
                {title: '开始时间', editor : {xtype : 'date', validator: function(value,obj) {
                    if (+value > +obj["endDate"]) {
                        return "开始时间不能晚于结束时间";
                    }
                }, datePicker : {showTime : true}}, rules: {require:true},dataIndex: 'beginDate', width: 200, renderer: BUI.Grid.Format.datetimeRenderer},
                {title: '结束时间', editor : {xtype : 'date', validator: function(value,obj) {
                    if (+value < +obj["beginDate"]) {
                        return "结束时间不能早于开始时间";
                    }
                }, datePicker : {showTime : true}, rules: {require:true}}, dataIndex: 'endDate', width: 200, renderer: BUI.Grid.Format.datetimeRenderer},
                {title: '备注', editor : {xtype : 'text', rules: {require:true}}, dataIndex: 'remark', width: 250},
                {title: '异常', editor : {xtype : 'text'}, dataIndex: 'exception', width: 250}
            ];
            var store = new BUI.Data.Store({
                    url : '/leave/previewCheck',
                    autoLoad:true, //自动加载数据
                    pageSize:1000	// 配置分页数目
                }),
                grid = new Grid.Grid({
                    height: edy.getSuggestGridHeight(),
                    render:'#grid',
                    columns : columns,
                    loadMask: true, //加载数据时显示屏蔽层
                    store: store,
                    // 底部工具栏
                    bbar:{
                        pagingBar:true
                    },
                    tbar:{
                        items : [{
                            btnCls : 'button button-small',
                            text : '<i class="icon-plus"></i>确认提交',
                            listeners : {
                                'click' : function() {
                                    var data = store.getResult();
                                    console.log();
                                    $.post("/leave/check", {jsonData: JSON.stringify({data: data})}, function(data) {
                                        if (edy.ajaxHelp.handleAjax(data)) {
                                            window.close();
                                        }
                                    });
                                }
                            }
                        }]
                    },
                    plugins : [editing,Grid.Plugins.CheckSelection,Grid.Plugins.ColumnResize]
                });
            grid.render();

        });
    } (jQuery));
</script>
</body>
</html>