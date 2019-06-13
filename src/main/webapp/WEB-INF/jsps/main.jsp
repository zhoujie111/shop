<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>和卓生物-在线后台管理系统</title>
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/bootstrap.css" />
    <link rel="stylesheet"  href="<%=request.getContextPath()%>/css/index.css" />
    <script src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/userSetting.js"></script>
    <script type="text/javascript">

        /*
         * 页面中动态显示当前时间 js中Date内置对象的使用
         */
        function showTime(){
            var nowtime=new Date();
            var year=nowtime.getFullYear();
            var month=nowtime.getMonth()+1;
            var date=nowtime.getDate();
            document.getElementById("time").innerText=year+"年"+month+"月"+date+"日"+" "+nowtime.toLocaleTimeString();
        }
        //每1秒钟显示当前时间
        setInterval("showTime()",1000);

        $(function(){
            // 点击切换页面
            $("#product-type-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/backend/productType/findAll");
            });
            $("#product-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/backend/product/findAll");
            });
            $("#user-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/backend/customer/findAll");
            });
            $("#manager-set").click(function() {
                $("#frame-id").attr("src", "<%=request.getContextPath()%>/backend/sysuser/findAll");
            });
            $("#mybatis").click(function () {
                $("#frame-id").attr("src","<%=request.getContextPath()%>/backend/student/findAll");
            });
            $("#user-order").click(function () {
                $("#frame-id").attr("src","<%=request.getContextPath()%>/backend/order/findAll");
            });
        });
    </script>
</head>

<body>
<div class="wrapper-cc clearfix">
    <div class="content-cc">
        <!-- header start -->
        <!--第一部分：系统头部-->
        <div class="clear-bottom head">
            <div class="container head-cc">
                <p>和卓生物<span>后台管理系统</span></p>
                <div class="welcome">
                    <div class="left">欢迎您：</div>
                    <div class="right">${sysuser.name}</div>
                    <div class="exit"><a style="text-decoration: none; color: white" href="<%=request.getContextPath()%>/backend/sysuser/logout">退出</a></div>
                </div>
            </div>
        </div>
        <!-- header end -->
        <!-- content start -->
        <!--第二部分 左侧功能菜单区域-->
        <div class="container-flude flude-cc" id="a">
            <div class="row user-setting">
                <div class="col-xs-2 user-wrap">
                    <ul class="list-group">
                        <li class="list-group-item active" name="userSet" id="product-type-set">
                            <i class="glyphicon glyphicon-lock"></i> &nbsp;商品类型管理
                        </li>
                        <li class="list-group-item" name="userPic" id="product-set">
                            <i class="glyphicon glyphicon-facetime-video"></i> &nbsp;商品管理
                        </li>
                        <li class="list-group-item" name="userInfo" id="user-set">
                            <i class="glyphicon glyphicon-user"></i> &nbsp;客户管理
                        </li>
                        <li class="list-group-item" name="userInfo" id="user-order">
                            <i class="glyphicon glyphicon-console"></i> &nbsp;订单管理
                        </li>
                        <li class="list-group-item" name="adminSet" id="manager-set">
                            <i class="glyphicon glyphicon-globe"></i> &nbsp;系统用户管理
                        </li>
                        <!--todo 价格体系-->
                        <li class="list-group-item" name="userInfo" id="user-set1">
                            <i class="glyphicon glyphicon-apple"></i> &nbsp;价格体系管理
                        </li>

                        <!--todo 销售订单管理-->
                        <li class="list-group-item" name="userInfo" id="user-set2">
                            <i class="glyphicon glyphicon-book"></i> &nbsp;销售订单管理
                        </li>

                        <!--todo 物料管理-->
                        <li class="list-group-item" name="userInfo" id="user-set3">
                            <i class="glyphicon glyphicon-barcode"></i> &nbsp;物料管理
                        </li>

                        <!--todo 仓储管理-->
                        <li class="list-group-item" name="userInfo" id="user-set4">
                            <i class="glyphicon glyphicon-bitcoin"></i> &nbsp;仓储管理
                        </li>

                        <!--todo 产品线管理-->
                        <li class="list-group-item" name="userInfo" id="user-set5">
                            <i class="glyphicon glyphicon-check"></i> &nbsp;产品线管理
                        </li>

                        <!--todo 基础数据管理-->
                        <li class="list-group-item" name="userInfo" id="user-set6">
                            <i class="glyphicon glyphicon-cog"></i> &nbsp;基础数据管理
                        </li>

                        <!--todo 一对多关联-->
                        <li class="list-group-item" name="userInfo" id="mybatis">
                            <i class="glyphicon glyphicon-euro"></i> &nbsp;mybatis管理
                        </li>

                        <!--todo 权限管理-->
                        <li class="list-group-item" name="userInfo" id="user-set8">
                            <i class="glyphicon glyphicon-briefcase"></i> &nbsp;权限管理
                        </li>
                    </ul>
                </div>
                <!--第三部分：右侧 具体的单个页面区域-->
                <div class="col-xs-10" id="userPanel">
                    <iframe id="frame-id" src="<%=request.getContextPath()%>/backend/productType/findAll" width="100%" height="100%" frameborder="0" scrolling="no">
                    </iframe>
                </div>

            </div>
        </div>
        <!-- content end-->
    </div>
</div>
<!-- footers start -->
<!--第四部分：最下方 页脚区域-->
<div class="footer">
    <span>版权所有：和卓生物 周杰</span>
    <button style="margin-right: 0px;" class="btn btn-danger btn-xs">当前服务器时间：<span id="time"></span></button>
</div>
<!-- footers end -->
</body>

</html>
