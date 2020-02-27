<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/23
  Time: 12:09
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
                <li id="release" data-type="mine-jie" lay-id="index" class="layui-this">我发的帖（<span>${user.userArticleCount}</span>）</li>
                <li id="favorite" data-type="collection">我收藏的帖（<span>${user.userFavoriteCount}</span>）</li>
            </ul>
            <div class="layui-tab-content" style="padding: 20px 0;">
                <div class="layui-form layui-form-pane layui-tab-item layui-show">
                    <ul id="myRelease" class="mine-view jie-row">

                        <c:if test="${empty articleList}">
                            <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有任何文章</span></div>
                        </c:if>
                        <c:forEach items="${articleList}" var="article">
                            <li>
                                <a class="jie-title" href="/home/article/${article.articleId}" target="_blank">${article.articleTitle}</a>
                                <i><fmt:formatDate value="${article.articleCreateTime}" pattern="YYYY-MM-DD"/></i>
                                <a class="layui-bg-orange mine-edit" href="/home/article/edit/${article.articleId}" >
                                    编辑
                                </a>
                                <a class="layui-bg-red mine-edit" href="/home/article/delete/${article.articleId}" onclick="javascript:return del()">
                                    删除
                                </a>
                                <em>${article.articleCommentCount} 评论</em>
                            </li>
                        </c:forEach>

                    </ul>
                </div>
                <div class="layui-form layui-form-pane layui-tab-item">
                <ul id="myFavorite" class="mine-view jie-row">

                        <c:if test="${empty favoriteList}">
                            <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有任何文章</span></div>
                        </c:if>
                        <c:forEach items="${favoriteList}" var="article">
                            <li>
                                <a class="jie-title" href="/home/article/${article.articleId}" target="_blank">${article.articleTitle}</a>
                                <i><fmt:formatDate value="${article.articleCreateTime}" pattern="YYYY-MM-DD"/></i>
                                <a class="layui-bg-red mine-edit" href="/home/user/deleteFav/${article.articleId}" onclick="javascript:return del()">
                                    取消收藏
                                </a>
                                <em>${article.articleCommentCount} 评论</em>
                            </li>
                        </c:forEach>

                    </ul>

                    <div id="LAY_page"></div>
                </div>
            </div>
        </div>
    </div>
</rapid:override>


<script src="../../../statics/js/jquery-3.4.1.js"></script>
<rapid:override name="footer-script">
    <script type="text/javascript">
        function del(){
            var msg=confirm("确定要删除吗？");
            if(msg==true)
            {
                return true;
            }
            else if(msg==false)
            {
                return false;
            }
        }
    </script>

    <script>
        $(document).ready(function () {
            $("li#index").addClass(" layui-this")
        })
    </script>
</rapid:override>

<jsp:include page="../Public/user_framework.jsp"/>