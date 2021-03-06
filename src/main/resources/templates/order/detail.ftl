<html>

<#--<head>-->
    <#--<meta charset="utf-8">-->
    <#--<title>卖家订单详情</title>-->
    <#--<link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">-->
<#--</head>-->
<#include "../common/header.ftl" >
<body>
<div id="wrapper" class="toggled">
<#--边栏-->
<#include "../common/nav.ftl">
<#--主要内容-->
    <div id="page-content-wrapper" style="float: none">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>订单总金额</th>

                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            <#--商品详情-->
                <table>
                    <div class="col-md-4 column"><br>
                        <table class="table">
                            <thead>
                            <tr>
                                <th>商品ID</th>
                                <th>商品名称</th>
                                <th>价格</th>
                                <th>数量</th>
                                <th>总额</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTO.orderDetailList as detailList>
                            <tr>
                                <td>
                                ${detailList.productId}
                                </td>
                                <td>
                                ${detailList.productName}
                                </td>
                                <td>
                                ${detailList.productPrice}
                                </td>
                                <td>
                                ${detailList.productQuantity}
                                </td>
                                <td>
                                ${detailList.productQuantity * detailList.productPrice}
                                </td>

                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                <#--操作-->
                    <div class="col-md-12 column">
                    <#if   orderDTO.getOrderStatusEnum ().message =="新订单">
                        <a href="/dianCan/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                        <a href="/dianCan/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                    </#if>
                    </div>
                </table>
            </div>
        </div>
    </div>
</div>



</body>
