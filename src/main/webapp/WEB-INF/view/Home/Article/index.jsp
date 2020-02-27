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

    <input type="hidden" id="page" value="">
    <input type="hidden" id="count" value="">
    <input type="hidden" id="articleList" value="">

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
                        <%--                    </span>--%>
                </div>

                <c:if test="${empty articleList.list}">
                    <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有任何文章</span></div>
                </c:if>

                <ul id="update" class="fly-list">
                    <c:forEach items="${articleList.list}" var="article">
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

<%--                <div style="border-top: 1px dotted #e2e2e2; text-align: center;">--%>
<%--                    <div class="laypage-main">--%>
<%--                        <a href="/home/article/page/${pageIndex-1}" class="laypage-prev">上一页</a>--%>
<%--                        <a href="/home/article/page/1/">1</a>--%>
<%--                        <span class="laypage-curr">2</span>--%>
<%--                        <a href="/home/article/page/3/">3</a>--%>
<%--                        <a href="/home/article/page/4/">4</a>--%>
<%--                        <a href="/home/article/page/5/">5</a>--%>
<%--                        <span>…</span>--%>
<%--                        <a href="/home/article/page/${lastPage}" class="laypage-last" title="尾页">尾页</a>--%>
<%--                        <a href="/home/article/page/${pageIndex+1}" class="laypage-next">下一页</a>--%>
<%--                    </div>--%>
<%--                </div>--%>

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
</rapid:override>

<rapid:override name="footer-script">
<%--    <script>--%>
<%--        layui.use(['laypage', 'layer'], function(){--%>
<%--            var laypage = layui.laypage--%>
<%--                ,layer = layui.layer;--%>

<%--        var articleList;--%>
<%--        $(document).ready(function () {--%>
<%--            $.ajax({--%>
<%--                url: "/home/article/get",--%>
<%--                dataType: "json",--%>
<%--                type:"post",--%>
<%--                async: false,--%>
<%--                success:function (data) {--%>
<%--                    articleList = data;--%>
<%--                }--%>
<%--        })--%>
<%--        })--%>

<%--        for (var i=0; i<articleList.length; i++){--%>
<%--            var item = articleList[i];--%>
<%--            var html = "<li>\n" +--%>
<%--                "                            <a href=\"/home/user/" + item.articleUserId +"\" class=\"fly-avatar\">\n" +--%>
<%--                "                                <img src=\""+ item['user'][0].userAvatar +"\""+ item['user'][0].userNickname +">\n" +--%>
<%--                "                            </a>\n" +--%>
<%--                "                            <h2>\n" +--%>
<%--                "                                <a href=\"/home/article/"+ item.articleId +"\">"+ item.articleTitle +"</a>\n" +--%>
<%--                "                            </h2>\n" +--%>
<%--                "                            <div class=\"fly-list-info\">\n" +--%>
<%--                "                                <a href=\"/home/user/"+ item.articleUserId +"\" link>\n" +--%>
<%--                "                                    <cite>"+ item['user'][0].userNickname +"</cite>\n" +--%>
<%--                "                                </a>\n" +--%>
<%--                "                                <span><fmt:formatDate value=\"" + item.articleUpdateTime +"\"\n" +--%>
<%--                "                                                      pattern=\"yyyy-MM-dd hh:mm\"/></span>\n" +--%>
<%--                "                                <span class=\"layui-badge layui-bg-blue\">"+ item['categoryList'][0].categoryName +"</span>\n" +--%>
<%--                "                                <span class=\"fly-list-nums\">\n" +--%>
<%--                "                            <i class=\"iconfont icon-pinglun1\" title=\"评论\"></i> "+ item.articleCommentCount +"\n" +--%>
<%--                "                            </span>\n" +--%>
<%--                "                            </div>\n" +--%>
<%--                "                        </li>"--%>
<%--        }--%>

<%--        laypage.render({--%>
<%--            elem: 'article'--%>
<%--            , count: articleList.length--%>
<%--            , limit: 15--%>
<%--            ,jump: function(obj){--%>
<%--                //模拟渲染--%>
<%--                document.getElementById('page_list').innerHTML = function(){--%>
<%--                    var arr = []--%>
<%--                        ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);--%>
<%--                    layui.each(thisData, function(index, item){--%>
<%--                        arr.push('<li>'+ item +'</li>');--%>
<%--                    });--%>
<%--                    return arr.join('');--%>
<%--                }();--%>
<%--            }--%>
<%--        })--%>
<%--        });--%>
<%--     </script>--%>

    <script>
    </script>
</rapid:override>


<script src="../../../../statics/js/jquery-3.4.1.js"></script>


<%@ include file="../Public/index_framework.jsp" %>