<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/22
  Time: 13:37
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
        Ks社区——用户主页
    </title>
    <link rel="stylesheet" href="../../../../statics/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="../../../../statics/css/back.css">
    <link rel="stylesheet" href="../../../../statics/css/global.css">
</head>
<body>

<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="/home">
            <img src="../../statics/img/logo.png" alt="layui">
        </a>
        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item">
                <a href="/home/article/page/1"><i class="layui-icon">&#xe705;</i>文章</a>
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
                            ${sessionScope.user.userNickname}
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="/home/user/${sessionScope.user.userId}"></i>我的主页</a></dd>
                        <dd><a href="/home/user/index"></i>用户中心</a></dd>
                        <dd><a href="/home/user/setting">基本设置</a></dd>
                            <%--                            <hr style="margin: 5px 0;">--%>
                            <%--                            <dd><a href="/home/logout" style="text-align: center;">退出</a></dd>--%>
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

    <div class="fly-home fly-panel" style="background-image: url();">
        <img src="${user.userAvatar}" class="layui-nav-img">
        <h1>
            ${user.userNickname}
        </h1>

        <p class="fly-home-info">
            <i class="iconfont icon-shijian"></i><span><fmt:formatDate value="${user.userRegisterTime}"
                                                                       pattern="yyyy-MM-dd"/> 加入</span>
    <%--        <i class="iconfont icon-chengshi"></i><span>来自杭州</span>--%>
        </p>

        <p class="fly-home-sign">${user.userProfile}</p>
    </div>

    <div class="layui-container">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md6 fly-home-jie">
                <div class="fly-panel">
                    <h3 class="fly-panel-title">${user.userNickname} 最近的文章</h3>
                    <ul class="jie-row">

                        <c:if test="${articleList == null}">
                            <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有任何文章</span></div>
                        </c:if>
                        <c:forEach items="${articleList}" var="article">
                            <li>
                                <a href="/home/article/${article.articleId}" class="jie-title"> ${article.articleTitle}</a>
                                <i><fmt:formatDate value="${article.articleCreateTime}"
                                                   pattern="yyyy-MM-dd"/></i>
                                <em class="layui-hide-xs">${article.articleCommentCount} 评论</em>
                            </li>
                        </c:forEach>

                    </ul>
                </div>
            </div>

            <div class="layui-col-md6 fly-home-da">
                <div class="fly-panel">
                    <h3 class="fly-panel-title">${user.userNickname} 最近的评论</h3>
                    <ul class="home-jieda">

                        <c:if test="${commentList == null}">
                            <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有任何评论</span></div>
                        </c:if>
                        <c:forEach items="${commentList}" var="comment">
                            <li>
                                <p>
                                <span><fmt:formatDate value="${comment.commentCreateTime}"
                                                      pattern="yyyy-MM-dd"/></span>
                                    在<a href="/home/article/${comment.articleId}" target="_blank">${comment.articleTitle}</a>中回答：
                                </p>
                                ${comment.commentContent}
                            </li>
                        </c:forEach>

                    </ul>
                </div>
            </div>

        </div>
    </div>

    <div class="fly-footer">
        <p><a href="/home" target="_blank">Ks社区</a> 2020 &copy;</p>
    </div>

    <script src="../../../../statics/js/jquery-3.4.1.js"></script>
    <script src="../../../../statics/plugin/layui/layui.all.js"></script>
    <script src="../../../../statics/js/back.js"></script>

</body>
</html>
