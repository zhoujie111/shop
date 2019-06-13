<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>backend</title>
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/bootstrap.css" />
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/index.css" />
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/userSetting.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap-paginator.js"></script>
    <script src="<%=request.getContextPath()%>/layer/layer.js"></script>
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/zshop.css" />
    <script>
        $(function(){
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                pageUrl:function(type,page, current){
                    return '<%=request.getContextPath()%>/backend/order/findAll?pageNum='+page;
                },
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }
                }
            });
        });

        //显示商品类型
        function showOrder(id){
            $.post(
                '<%=request.getContextPath()%>/backend/order/findById',
                {'id':id},
                function(result){
                    if(result.status==1){
                        $('#proTypeNum').val(result.data.id);
                        $('#proTypeName').val(result.data.name);
                    }
                }
            );
        }

        //修改商品类型的名称
        function modifyName(){
            $.ajax({
                type:'post',
                url:'<%=request.getContextPath()%>/backend/productType/modifyName',
                data:{'id': $('#proTypeNum').val(),'name':$('#proTypeName').val()},
                dataType:'json',
                success:function(result){
                    if(result.status==1){
                        layer.msg(result.message,{
                            time:1000,
                            skin:'successMsg'
                        },function(){
                            //重新加载数据
                            location.href='<%=request.getContextPath()%>/backend/productType/findAll?pageNum='+${pageInfo.pageNum};
                        })
                    }else{
                        layer.msg(result.message,{
                            time:1000,
                            skin:'errorsMsg'
                        })
                    }
                }
            });
        }

        //显示确认删除的提示
        function showDeleteModal(id){
            $('#deleteProductTypeId').val(id);
            $('#deleteProductTypeModal').modal('show');
        }

        // 添加商品类型
        function addProductType(){
            $.post(
                '<%=request.getContextPath()%>/backend/productType/add',
                {'name':$('#productTypeName').val()},
                function(result){
                    if(result.status==1){
                        layer.msg(result.message,{
                            time:1000,
                            skin:'successMsg'
                        },function(){
                            location.href='<%=request.getContextPath()%>/backend/productType/findAll?pageNum='+${pageInfo.pageNum};
                        });
                    }else{
                        layer.msg(result.message,{
                            time:1000,
                            skin:'errorMsg'
                        });
                    }
                }
            );

        }
    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">订单管理</h3>
    </div>
    <div class="panel-body">
<%--        <input type="button" value="添加订单" class="btn btn-primary" id="doAddProTpye">--%>
        <br>
        <br>
        <div class="show-list text-center">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <!--字段的个数受排版的影响 todo-->
                <tr class="text-danger">
                    <th class="text-center">订单编号</th>
                    <th class="text-center">订单价格</th>
                    <th class="text-center">下单人</th>
<%--                    <th class="text-center">电话<th>--%>
                    <th class="text-center">订单类型</th>
                    <th class="text-center">创建时间</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="order">
                    <tr>
                        <td>${order.no}</td>
                        <td>${order.price}</td>
                        <td>${order.customer.name}</td>
<%--                        <td>${order.customer.phone}</td>--%>
                        <td>${order.orderType}</td>
                        <td>
                            <fmt:formatDate value="${order.createDate}"/>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doProTypeModify" value="修改" onclick="showOrder(${order.id})">
                            <input type="button" class="btn btn-warning btn-sm doProTypeDelete" onclick="showDeleteModal(${order.id})" value="删除">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 使用bootstrap-paginator实现分页 -->
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

</body>

</html>