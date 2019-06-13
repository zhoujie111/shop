<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>和卓生物检测产品线</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/iconfont/iconfont.css">
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/js/zshop.js"></script>
</head>

<body>
<div id="wrapper">
    <!--共用页面头部-->
    <!-- navbar start -->
    <jsp:include page="top.jsp"/>
    <!-- navbar end -->

    <!-- content start -->
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="page-header" style="margin-bottom: 0px;">
                    <h3>商品列表</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-inline hot-search" action="<%=request.getContextPath()%>/front/product/search">
                    <div class="form-group">
                        <label class="control-label">商品：</label>
                        <input type="text" class="form-control" placeholder="商品名称">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label class="control-label">价格：</label>
                        <input type="text" class="form-control" placeholder="最低价格"> --
                        <input type="text" class="form-control" placeholder="最高价格">
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <label class="control-label">种类：</label>
                        <select class="form-control input-sm">
                            <option value="-1" selected="selected">查询全部</option>
                            <c:forEach items="${productTypes}" var="productType">
                                <option value="${productType.id}">${productType.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    &nbsp;
                    <div class="form-group">
                        <button type="button" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="content-back">
        <div class="container" id="a">
            <div class="row">
                <c:forEach items="${pageInfo.list}" var="product">
                    <div class="col-xs-3  hot-item">
                        <div class="panel clear-panel">
                            <div class="panel-body">
                                <div class="art-back clear-back">
                                    <div class="add-padding-bottom">
                                        <img src="${product.image}" class="shopImg">
                                    </div>
                                    <h4><a href="">${product.name}</a></h4>
                                    <div class="user clearfix pull-right">
                                        ￥${product.price}
                                    </div>
                                    <div class="desc">平时穿也不会显得夸张，很大方洋气 我已经自留了，还和小姐妹准备人手一件圣诞节穿着出去玩！ 经典的圆领，大气休闲，长袖设计，休闲舒适 宽松的版型，怕冷可以里面穿保暖衣）
                                    </div>
                                    <div class="attention pull-right">
                                        加入购物车 <i class="icon iconfont icon-gouwuche"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- content end-->
    <!-- footers start -->
    <div class="footers">
        版权所有：和卓生物
    </div>
    <!-- footers end -->
</div>

</body>

</html>