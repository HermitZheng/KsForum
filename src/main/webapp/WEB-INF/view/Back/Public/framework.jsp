<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="shortcut icon" href="/img/logo.png">
    <title>
        Ks社区后台
        <rapid:block name="title"></rapid:block>
    </title>
    <link rel="stylesheet" href="../../../../statics/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="../../../../statics/css/back.css">
    <link rel="stylesheet" href="../../../../statics/plugin/font-awesome/css/font-awesome.min.css">
    <rapid:block name="header-style"></rapid:block>
    <rapid:block name="header-script"></rapid:block>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"><a href="/back/index" style="color:#009688;">
            KsForum后台
        </a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/home" target="_blank">前台</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">新建</a>
                <dl class="layui-nav-child">
                    <dd><a href="/back/article/insert">文章</a></dd>
<%--                    <dd><a href="/admin/page/insert">页面</a></dd>--%>
                    <dd><a href="/back/category">分类</a></dd>
<%--                    <dd><a href="/admin/notice/insert">公告</a></dd>--%>
                    <dd><a href="/back/user/insert">用户</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${sessionScope.user.userAvatar}" class="layui-nav-img">
                    ${sessionScope.user.userName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/back/user/profile">基本资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="/back/logout">退出</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">文章</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/back/article">全部文章</a></dd>
                        <dd><a href="/back/article/insert">写文章</a></dd>
                        <dd><a href="/back/category">全部分类</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">页面</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/page">全部页面</a></dd>
                        <dd><a href="/admin/page/insert">添加页面</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">公告</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/notice">全部公告</a></dd>
                        <dd><a href="/admin/notice/insert">添加公告</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="/back/comment">
                        评论
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">
                        用户
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="/back/user">全部用户</a></dd>
                        <dd><a href="/back/user/insert">添加用户</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">设置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/menu">菜单</a></dd>
                        <dd><a href="/admin/options">主要选项</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <rapid:block name="content">

            </rapid:block>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © <a href="http://blog.liuyanzhao.com">Ks社区</a> 2020
    </div>
</div>

<script src="../../../../statics/js/jquery-3.4.1.js"></script>
<script src="../../../../statics/plugin/layui/layui.all.js"></script>
<script src="../../../../statics/js/back.js"></script>
<rapid:block name="footer-script">

</rapid:block>
<script>
    //给文本编辑器的iframe引入代码高亮的css
    // $("iframe").contents().find("head").append("<link rel=\"stylesheet\" href=\"../../../../statics/css/highlight.css\">\n");
</script>

</body>
</html>
