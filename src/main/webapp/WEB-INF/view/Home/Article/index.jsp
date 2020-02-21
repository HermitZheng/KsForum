<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/21
  Time: 14:46
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

            <div class="fly-panel" style="margin-bottom: 0;">

                <div class="fly-panel-title fly-filter">
                    <a>文章列表</a>
<%--                    <span class="fly-filter-right layui-hide-xs">--%>
<%--            <a id="byUpdate" href="javascript:void(0);" class="layui-this">按最新</a>--%>
<%--            <span class="fly-mid"></span>--%>
<%--            <a id="byComment" href="javascript:void(0);">按热议</a>--%>
                    </span>
                </div>

                <ul id="update" class="fly-list">
                    <c:forEach items="${articleByUpdate}" var="article">
                        <li>
                            <a href="/home/user/${article.articleUserId}" class="fly-avatar">
                                <img src="${article.user.userAvatar}"${article.user.userName}>
                            </a>
                            <h2>
                                <a href="/home/article/${article.articleId}">${article.articleTitle}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="/home/user/${article.articleUserId}" link>
                                    <cite>${article.user.userName}</cite>
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

                <div style="border-top: 1px dotted #e2e2e2; text-align: center;">
                    <div class="laypage-main">
                        <a href="/home/article/page/${pageIndex-1}" class="laypage-prev">上一页</a>
                        <a href="/home/article/page/1/">1</a>
                        <span class="laypage-curr">2</span>
                        <a href="/home/article/page/3/">3</a>
                        <a href="/home/article/page/4/">4</a>
                        <a href="/home/article/page/5/">5</a>
                        <span>…</span>
                        <a href="/home/article/page/${lastPage}" class="laypage-last" title="尾页">尾页</a>
                        <a href="/home/article/page/${pageIndex+1}" class="laypage-next">下一页</a>
                    </div>
                </div>

            </div>
        </div>


        <div class="layui-col-md4">
            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">公告</dt>
                <c:forEach items="${noticeList}" var="notice">
                    <dd>
                        <a href="/home/notice/${notice.articleUserId}}">${notice.articleTitle}</a>
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
</rapid:override>




<script src="../../../../statics/js/jquery-3.4.1.js"></script>


<%@ include file="../Public/index_framework.jsp" %>