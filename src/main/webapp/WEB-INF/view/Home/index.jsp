<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/19
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>


<rapid:override name="content">
    <div class="layui-container">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8">
                <div class="fly-panel">
                    <div class="fly-panel-title fly-filter">
                        <a>置顶</a>
                    </div>

                    <ul class="fly-list">
                        <c:forEach items="${topList}" var="top">
                            <li>
                                <a href="/home/user/${top.articleUserId}" class="fly-avatar">
                                    <img src="${top.user.userAvatar}" alt="${top.user.userNickname}">
                                </a>
                                <h2>
                                    <a class="layui-badge">置顶</a>
                                    <a href="/home/article/${top.articleId}">${top.articleTitle}</a>
                                </h2>
                                <div class="fly-list-info">
                                    <a href="/home/user/${top.articleUserId}" link>
                                        <cite>${top.user.userNickname}</cite>
                                    </a>
                                    <span><fmt:formatDate value="${top.articleUpdateTime}"
                                                          pattern="yyyy-MM-dd hh:mm"/></span>

                                    <span class="fly-list-nums">
                            <i class="iconfont icon-pinglun1" title="评论"></i> ${top.articleCommentCount}
                            </span>
                                </div>
                                <div class="fly-list-badge">
                                    <!--
                                    <span class="layui-badge layui-bg-black">置顶</span>
                                    <span class="layui-badge layui-bg-red">精帖</span>
                                    -->
                                </div>
                            </li>
                        </c:forEach>
                    </ul>

                </div>

                <div class="fly-panel">
                    <div class="layui-tab layui-tab-brief">

<%--                        <div class="fly-panel-title fly-filter layui-tab-title">--%>
<%--                            <a>文章列表</a>--%>
<%--                            <span class="fly-filter-right layui-tab-title" align="right">--%>
<%--                                <ul class="layui-tab-title layui-inline">--%>
<%--                                    <li class="layui-this">按最新</li>--%>
<%--                                    <li>按热议</li>--%>
<%--                                </ul>--%>
<%--                            </span>--%>
<%--                        </div>--%>

                        <div class="fly-panel-title fly-filter">
                            <a>文章列表</a>
                            <span class="fly-filter-right layui-hide-xs">
                                <a id="byUpdate" href="javascript:void(0);" class="layui-this">按最新</a>
                                <span class="fly-mid"></span>
                                <a id="byComment" href="javascript:void(0);">按热议</a>
                            </span>
                        </div>

                        <div class="layui-form layui-form-pane layui-tab-item layui-show">
                            <ul id="update" class="fly-list">
                                <c:forEach items="${articleByUpdate}" var="article">
                                    <li>
                                        <a href="/home/user/${article.articleUserId}" class="fly-avatar">
                                            <img src="${article.user.userAvatar}"${article.user.userNickname}>
                                        </a>
                                        <h2>
                                            <a href="/home/article/${article.articleId}">${article.articleTitle}</a>
                                        </h2>
                                        <div class="fly-list-info">
                                            <a href="/home/user/${article.articleUserId}" link>
                                                <cite>${article.user.userNickname}</cite>
                                            </a>
                                            <span><fmt:formatDate value="${article.articleUpdateTime}"
                                                                  pattern="yyyy-MM-dd hh:mm"/></span>
                                            <span class="layui-badge layui-bg-blue">${article.categoryList[0].categoryName}</span>
                                            <c:if test="${article.categoryList[1] != null}">
                                                <span class="layui-badge layui-bg-blue">${article.categoryList[1].categoryName}</span>
                                            </c:if>
                                            <span class="fly-list-nums">
                                                <i class="iconfont icon-pinglun1" title="评论"></i> ${article.articleCommentCount}
                                            </span>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                        <div class="layui-form layui-form-pane layui-tab-item layui-show">
                            <ul id="comment" class="fly-list">
                                <c:forEach items="${articleByComment}" var="article">
                                    <li>
                                        <a href="/home/user/${article.articleUserId}" class="fly-avatar">
                                            <img src="${article.user.userAvatar}"${article.user.userNickname}>
                                        </a>
                                        <h2>
                                            <a href="/home/article/${article.articleId}">${article.articleTitle}</a>
                                        </h2>
                                        <div class="fly-list-info">
                                            <a href="/home/user/${article.articleUserId}" link>
                                                <cite>${article.user.userNickname}</cite>
                                            </a>
                                            <span><fmt:formatDate value="${article.articleUpdateTime}"
                                                                  pattern="yyyy-MM-dd hh:mm"/></span>
                                            <span class="layui-badge layui-bg-blue">${article.categoryList[0].categoryName}</span>
                                            <span class="fly-list-nums">
                                                <i class="iconfont icon-pinglun1" title="评论"></i> ${article.articleCommentCount}
                                            </span>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                        <div style="text-align: center">
                            <div class="laypage-main">
                                <a href="/home/article/page/1" class="laypage-next">更多文章</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="layui-col-md4">
                <dl class="fly-panel fly-list-one">
                    <dt class="fly-panel-title">公告</dt>
                    <c:forEach items="${noticeList}" var="notice">
                        <dd>
                            <a href="/home/article/${notice.articleId}">${notice.articleTitle}</a>
                            <span><i class="iconfont icon-pinglun1"></i> ${notice.articleCommentCount}</span>
                        </dd>
                    </c:forEach>
                    <!-- 无数据时 -->
                    <!--
                    <div class="fly-none">没有相关数据</div>
                    -->
                </dl>
            </div>

            <div class="layui-col-md4">
                <dl class="fly-panel fly-list-one">
                    <dt class="fly-panel-title">文章分类</dt>
                    <c:forEach items="${allCategory}" var="cate">
                        <dd>
                            <a href="/home/category/${cate.categoryId}">${cate.categoryName}</a>
                            <span><i class="layui-icon">&#xe705;</i> ${cate.articleCount}</span>
                        </dd>
                    </c:forEach>
                    <!-- 无数据时 -->
                    <!--
                    <div class="fly-none">没有相关数据</div>
                    -->
                </dl>
            </div>
        </div>
    </div>
</rapid:override>

<script src="../../../statics/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("ul#comment").hide();
    });

    $(document).ready(function(){
        $("#byUpdate").click(function(){
            $("#byComment").removeClass()
            $("#byUpdate").addClass("layui-this")
            $("ul#comment").hide();
            $("ul#update").show();
        });

        $("#byComment").click(function(){
            $("#byComment").addClass("layui-this")
            $("#byUpdate").removeClass()
            $("ul#update").hide();
            $("ul#comment").show();
        });
    });
</script>

<%@ include file="Public/index_framework.jsp" %>