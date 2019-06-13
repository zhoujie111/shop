<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
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

    <script type="text/javascript">
        $(function(){
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion:3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                pageUrl:function(type,page, current){
                    return '<%=request.getContextPath()%>/backend/customer/findAll?pageNum='+page;
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

        //导出excel
        function exportCustomer() {
            location.href="<%=request.getContextPath()%>/backend/customer/exportXls";
        }

        //显示确认删除的提示
        function showDeleteModal(id){
            $('#deleteCustomerId').val(id);
            $('#deleteCustomerModal').modal('show');
        }

        //删除商品类型
        function deleteCustomer(){
            $.get(
                '<%=request.getContextPath()%>/backend/customer/removeById',
                {'id':$('#deleteCustomerId').val()},
                function(result){
                    if(result.status==1){
                        layer.msg('删除成功',{
                            time:1000,
                            skin:'successMsg'
                        },function(){
                            //重新加载数据
                            location.href='<%=request.getContextPath()%>/backend/customer/findAll?pageNum='+${pageInfo.pageNum};
                        })
                    }else{
                        layer.msg('删除失败',{
                            time:1000,
                            skin:'errorsMsg'
                        })
                    }
                }
            );
        }
    </script>
</head>

<body>
<div class="panel panel-default" id="userInfo" id="homeSet">
    <div class="panel-heading">
        <h3 class="panel-title">客户管理</h3>
    </div>
    <div class="panel-body">
        <div class="showusersearch">
            <form class="form-inline" method="post" action="<%=request.getContextPath()%>/backend/customer/findByParams">
                <div class="form-group">
                    <label for="customer_name">姓名:</label>
                    <input type="text" class="form-control"id="customer_name" name="name" placeholder="请输入姓名" size="15px">
                </div>
                <div class="form-group">
                    <label for="customer_loginName">帐号:</label>
                    <input type="text" class="form-control" id="customer_loginName" name="loginName" placeholder="请输入帐号" size="15px">
                </div>
                <div class="form-group">
                    <label for="customer_phone">电话:</label>
                    <input type="text" class="form-control" id="customer_phone" name="phone" placeholder="请输入电话" size="15px">
                </div>
                <div class="form-group">
                    <label for="customer_address">地址:</label>
                    <input type="text" class="form-control" id="customer_address" name="address" placeholder="请输入地址">
                </div>
                <div class="form-group">
                    <label for="customer_isValid">状态:</label>
                    <select class="form-control" id="customer_isValid" name="isValid">
                        <option value="-1">全部</option>
                        <option value="1">---有效---</option>
                        <option value="0">---无效---</option>
                    </select>
                </div>
                <!--多条件查询-->
                <input type="submit" value="查询" class="btn btn-primary" id="doSearch">
            </form>
        </div>
        <br>
        <!--新增添加客户按钮-->
        <input type="button" value="添加客户信息" class="btn btn-primary" id="doAddManger">
        <!--todo 导出数据-->
        <input type="button" value="导出客户数据" class="btn btn-danger" onclick="exportCustomer()">
        <div class="show-list text-center" style="position: relative;top: 30px;">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">序号</th>
                    <th class="text-center">姓名</th>
                    <th class="text-center">帐号</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">地址</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="customer">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.loginName}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.address}</td>
                    <td>${customer.isValid}</td>
                    <td class="text-center">
                        <input type="button" class="btn btn-danger btn-sm" value="删除" onclick="showDeleteModal(${customer.id})">
                        <input type="button" class="btn btn-warning btn-sm doModify" value="修改">
                        <input type="button" class="btn btn-success btn-sm doDisable" value="禁用">
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

<!-- 添加客户信息 start todo 周杰-->
<div class="modal fade" tabindex="-1" id="myMangerUser">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <form id="frmAddSysuser">
            <div class="modal-content">
                <!-- 头部、主体、脚注 -->
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">添加客户信息</h4>
                </div>
                <div class="modal-body text-center">
                    <div class="row text-right">
                        <label for="marger-username" class="col-sm-4 control-label">姓名：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="marger-username" name="name">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="marger-loginName" class="col-sm-4 control-label">帐号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="marger-loginName" name="loginName">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="marger-phone" class="col-sm-4 control-label">电话：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="marger-phone" name="phone">
                        </div>
                    </div>
                    <br>
                    <div class="row text-right">
                        <label for="marger-address" class="col-sm-4 control-label">地址：</label>
                        <div class="col-sm-4">
                            <input type="email" class="form-control" id="marger-address" name="address">
                        </div>
                    </div>
                    <br>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary add-Manger" onclick="addSysuser()" type="button">添加</button>
                    <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- 修改客户信息 start -->
<div class="modal fade" tabindex="-1" id="myModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改客户</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="id" class="col-sm-4 control-label">编号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="id">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="name" class="col-sm-4 control-label">姓名：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="name">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="loginName" class="col-sm-4 control-label">帐号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="loginName">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="phone" class="col-sm-4 control-label">电话：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="phone">
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="adrees" class="col-sm-4 control-label">地址：</label>
                    <div class="col-sm-4">
                        <input type="email" class="form-control" id="adrees">
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary updateOne">修改</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改客户信息 end -->
<!--删除客户信息 start-->
<div class="modal fade" tabindex="-1" id="deleteCustomerModal">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">提示消息</h4>
            </div>
            <div class="modal-body text-center">
                <h4>确认要删除该客户信息吗？</h4>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="deleteCustomerId">
                <button class="btn btn-primary updateProType" onclick="deleteCustomer()" data-dismiss="modal">删除</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</body>

</html>