<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/25
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>


<rapid:override name="content">


    <div class="layui-container">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8">
                <div class="fly-panel" style="margin-bottom: 0;">
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
                        <legend>文章分类面板</legend>
                    </fieldset>

                    <div class="layui-collapse" lay-accordion="">
                        <ul>

                            <c:forEach items="${treeList}" var="tree">
                                <div class="layui-colla-item">
                                    <h2 class="layui-colla-title">
                                        <a href="/home/category/${tree.parentId}">${tree.parentName}</a>
                                        <span><i class="layui-icon">&#xe705;</i> ${tree.allCount}</span>
                                    </h2>
                                    <c:forEach items="${tree.childList}" var="child">
                                        <div class="layui-colla-content layui-show">
                                            <div class="layui-collapse" lay-accordion="">
                                                <div class="layui-colla-item">
                                                    <h2 class="layui-colla-title">
                                                        <a href="/home/category/${child.childId}">${child.childName}</a>
                                                        <span><i class="layui-icon">&#xe705;</i> ${child.articleCount}</span>
                                                    </h2>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:forEach>

                        </ul>
                    </div>
            </div>
        </div>
    </div>


</rapid:override>



<script src="../../../statics/js/jquery-3.4.1.js"></script>

<rapid:override name="footer-script">
<%--    <script>--%>
<%--        var treeList;--%>
<%--        $(document).ready(function () {--%>
<%--            $.ajax({--%>
<%--                url: "/home/category/get",--%>
<%--                dataType: "json",--%>
<%--                method: "post",--%>
<%--                contentType: "application/x-www-form-urlencoded; charset=utf-8",--%>
<%--                async: false,--%>
<%--                success:function (data) {--%>
<%--                    console.log(data)--%>
<%--                    treeList = data;--%>
<%--                },--%>
<%--                error:function () {--%>
<%--                    alert("数据获取失败！");--%>
<%--                }--%>
<%--            })--%>
<%--        })--%>

<%--        layui.use(['tree', 'util'], function() {--%>
<%--            var tree = layui.tree--%>
<%--                , layer = layui.layer--%>
<%--                , util = layui.util--%>
<%--                , data = treeList--%>
<%--            //无连接线风格--%>
<%--            tree.render({--%>
<%--                elem: 'tree_list'--%>
<%--                , data: data--%>
<%--                , showLine: false  //是否开启连接线--%>
<%--            });--%>
<%--        })--%>
<%--    </script>--%>
</rapid:override>


<jsp:include page="../Public/index_framework.jsp"/>
