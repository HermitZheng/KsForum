<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/23
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="content">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title" id="LAY_mine">
                <li id="comment" data-type="mine-jie" lay-id="index" class="layui-this">我的回复（<span>${user.userCommentCount}</span>）</li>
            </ul>
            <div class="layui-tab-content" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <ul id="myComment" class="mine-view jie-row">

                        <c:if test="${empty commentList}">
                            <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有任何评论</span></div>
                        </c:if>
                        <c:forEach items="${commentList}" var="comment">
                            <li>
                                <p>
                                <span><fmt:formatDate value="${comment.commentCreateTime}"
                                                      pattern="yyyy-MM-dd"/></span>
                                    在<a href="/home/article/${comment.articleId}" target="_blank">${comment.articleTitle}</a>中回答：${comment.commentContent}
                                </p>
                            </li>
                        </c:forEach>

                    </ul>

                    <div id="LAY_page"></div>
                </div>
            </div>
        </div>
    </div>
</rapid:override>

<rapid:override name="footer-script">
    <script>
        $(document).ready(function () {
            $("li#comment").addClass(" layui-this")
        })
    </script>
</rapid:override>


<%@ include file="../Public/user_framework.jsp" %>