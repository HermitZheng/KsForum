<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/19
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
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
    <link rel="stylesheet" href="../../../../statics/css/global.css">
    <rapid:block name="header-style"></rapid:block>
    <rapid:block name="header-script"></rapid:block>
</head>

<body>

    <div class="fly-header layui-bg-black">
        <div class="layui-container">
            <a class="fly-logo" href="/">
                <img src="../../statics/img/logo.png" alt="layui">
            </a>
            <ul class="layui-nav fly-nav layui-hide-xs">
                <li class="layui-nav-item">
                    <a href="/home/article"><i class="layui-icon">&#xe705;</i>文章</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/home/category"><i class="layui-icon">&#xe62a;</i>分类</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/home/article/write"><i class="layui-icon">&#xe642;</i>发帖</a>
                </li>
            </ul>
            <c:if test="${sessionScope.user != null}">
                <ul class="layui-nav layui-layout-right">
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <img src="${sessionScope.user.userAvatar}" class="layui-nav-img">
                                ${sessionScope.user.userName}
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="/home/user/profile">个人中心</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="/home/logout">退出</a>
                    </li>
                </ul>
            </c:if>
            <c:if test="${sessionScope.user == null}">
                <ul class="layui-nav fly-nav-user">
                    <!-- 未登入的状态 -->
                    <li class="layui-nav-item">
                        <a class="iconfont icon-touxiang layui-hide-xs" href="user/login.html"></a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="/home/login">登入</a>
                    </li>
                    <li class="layui-nav-item">
                        <a href="/home/register">注册</a>
                    </li>
                </ul>
            </c:if>
        </div>
    </div>

    <div class="fly-panel fly-column">
        <div class="layui-container">
            <ul class="layui-clear">
                <li class="layui-hide-xs layui-this"><a href="/home">首页</a></li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>
                <!-- 用户登入后显示 -->
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="/home/user/myArticle">我发表的贴</a></li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="/home/user/myComment">我评论的贴</a></li>
                <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="/home/user/myFavorite">我收藏的贴</a></li>
            </ul>

            <div class="fly-column-right layui-hide-xs">
                <form id="search" action="/home/article/search">
                    <div class="layui-inline">
                        <input type="text" name="articleTitle" placeholder="请输入标题" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn">
                            <i class="layui-icon"></i>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>


        <!-- 内容主体区域 -->
    <rapid:block name="content">

    </rapid:block>


    <div class="fly-footer">
        <!-- 底部固定区域 -->
        <p>
            © <a href="/home">Ks社区</a> 2020
        </p>
    </div>

    <script src="../../../../statics/js/jquery-3.4.1.js"></script>
    <script src="../../../../statics/plugin/layui/layui.all.js"></script>
    <script src="../../../../statics/js/back.js"></script>
    <rapid:block name="footer-script">
    </rapid:block>

</body>
</html>
