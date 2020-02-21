<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/21
  Time: 15:47
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
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box" style="margin-bottom: 0;">
                <h1>${article.articleTitle}</h1>
                <div class="fly-detail-info">
                    <c:forEach items="${article.categoryList}" var="cate">
                        <span class="layui-badge layui-bg-blue">${cate.categoryName}</span>
                    </c:forEach>

                    <span class="fly-list-nums">
                        <a href="#comment"><i class="iconfont"
                                              title="回答">&#xe60c;</i> ${article.articleCommentCount}</a>
<%--                        TODO 文章浏览量（人气）--%>
<%--                        <i class="iconfont" title="人气">&#xe60b;</i> ${article.articleViewCount}--%>
                    </span>
                </div>

                <div class="detail-about">
                    <a class="fly-avatar" href="/home/user/${article.articleUserId}">
                        <img src="${article.user.userAvatar}" alt="${article.user.userName}">
                    </a>
                    <div class="fly-detail-user">
                        <a href="/home/user/${article.articleUserId}" class="fly-link">
                            <cite>${article.user.userName}</cite>
                        </a>
                        <span>
                            文章发布时间：<fmt:formatDate value="${article.articleCreateTime}" pattern="yyyy-MM-dd hh:mm"/>
                        </span>
                    </div>
                    <div class="detail-hits">
                        <span>      </span>
                        <span>
                            最后更新时间：<fmt:formatDate value="${article.articleUpdateTime}" pattern="yyyy-MM-dd hh:mm"/>
                        </span>
                    </div>
                </div>

                <div class="detail-body photos">
                        ${article.articleContent}
                </div>

                <div class="fly-panel detail-box" id="flyReply">
                    <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                        <legend>回帖</legend>
                    </fieldset>

                    <ul class="jieda" id="jieda">

                        <c:if test="${commentList != null}">
                            <!-- 无数据时 -->
                            <li class="fly-none">消灭零回复</li>
                        </c:if>

                        <c:forEach items="${commentList}" var="comment">
                            <li>
                                <div class="detail-about detail-about-reply">
                                    <a class="fly-avatar" href="/home/user/${comment.commentAuthorId}">
                                        <img src="${comment.commentAuthorAvatar}" alt="${comment.commentAuthorName}">
                                    </a>
                                    <div class="fly-detail-user">
                                        <a href="/home/user/${comment.commentAuthorId}" class="fly-link">
                                            <cite>${comment.commentAuthorName}</cite>
                                        </a>
                                    </div>
                                    <div class="detail-hits">
                                        <span>
                                            <fmt:formatDate value="${article.articleCreateTime}" pattern="yyyy-MM-dd hh:mm"/>
                                        </span>
                                    </div>
                                </div>
                                <div class="detail-body jieda-body photos">
                                    <p>${comment.commentContent}</p>
                                </div>
                                <div class="jieda-reply">
<%--                                  <span class="jieda-zan" type="zan">--%>
<%--                                    <i class="iconfont icon-zan"></i>--%>
<%--                                    <em><!-- TODO 评论点赞 --></em>--%>
<%--                                  </span>--%>
                                  <span type="reply">
                                    <i class="iconfont icon-svgmoban53"></i>
                                    回复
                                  </span>
                                </div>
                            </li>
                        </c:forEach>

                    </ul>

                    <div class="layui-form layui-form-pane">
                        <form action="/home/comment/reply" method="post">
                            <div class="layui-form-item layui-form-text">
                                <div class="layui-input-block">
                                    <textarea id="L_content" name="commentContent" required lay-verify="required" placeholder="请输入内容"
                                              class="layui-textarea fly-editor" style="height: 150px;">
                                    </textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <input type="hidden" name="userId" value="${sessionScope.user.userId}">
                                <input type="hidden" name="articleId" value="${article.articleId}">
                                <button class="layui-btn" lay-filter="*" lay-submit>提交回复</button>
                            </div>
                        </form>
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
    </div>
</rapid:override>


<script src="../../../../statics/js/jquery-3.4.1.js"></script>


<%@ include file="../Public/index_framework.jsp" %>