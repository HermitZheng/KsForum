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
                    <input type="hidden" id="articleId" value="${articleId}">
                    <input type="hidden" id="user" value="${sessionScope.user}">
                    <div class="fly-detail-info">
                        <c:forEach items="${article.categoryList}" var="cate">
                            <span class="layui-badge layui-bg-blue">${cate.categoryName}</span>
                        </c:forEach>

                        <span class="fly-list-nums">
                            <c:if test="${isFav}">
                                <a class="" href="/home/user/deleteFav/${article.articleId}" onclick="javascript:return delFav()">
                                    <button type="button" class="layui-btn layui-btn-cyan">取消收藏</button>
                                </a>
                            </c:if>
                            <c:if test="${!isFav}">
                                <a>
                                    <button id="add_fav" type="button" class="layui-btn layui-btn-cyan">收藏</button>
                                </a>
                            </c:if>
                            <c:if test="${article.articleUserId == sessionScope.user.userId}">
                                <a href="/home/article/edit/${article.articleId}">
                                    <button type="button" class="layui-btn layui-btn-warm">编辑</button>
                                </a>
                                <a href="/home/article/delete/${article.articleId}" onclick="javascript:return delArt()">
                                    <button type="button" class="layui-btn layui-btn-danger">删除</button>
                                </a>
                            </c:if>
                            <a href="#comment"><i class="iconfont"
                                                  title="回答">&#xe60c;</i> ${article.articleCommentCount}</a>
    <%--                        TODO 文章浏览量（人气）--%>
    <%--                        <i class="iconfont" title="人气">&#xe60b;</i> ${article.articleViewCount}--%>
                        </span>
                    </div>

                    <div class="detail-about">
                        <a class="fly-avatar" href="/home/user/${article.articleUserId}">
                            <img src="${article.user.userAvatar}" alt="${article.user.userNickname}">
                        </a>
                        <div class="fly-detail-user">
                            <a href="/home/user/${article.articleUserId}" class="fly-link">
                                <cite>${article.user.userNickname}</cite>
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
                                            <img src="${comment.commentAuthorAvatar}"
                                                 alt="${comment.commentAuthorName}">
                                        </a>
                                        <div class="fly-detail-user">
                                            <a href="/home/user/${comment.commentAuthorId}" class="fly-link">
                                                <cite>${comment.commentAuthorName}</cite>
                                            </a>
                                        </div>
                                        <div class="detail-hits">
                                        <span>
                                            <fmt:formatDate value="${article.articleCreateTime}"
                                                            pattern="yyyy-MM-dd hh:mm"/>
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
                                        <span id="reply" type="reply">
                                            <i class="iconfont icon-svgmoban53"></i>
                                            回复
                                        </span>
                                        <c:if test="${comment.commentAuthorId.equals(sessionScope.user.userId)}">
                                            <span>
                                                <a href="/home/comment/delete/${comment.commentId}" onclick="javascript:return delCom()">
                                                    <button type="button" class="layui-btn layui-btn-danger">删除</button>
                                                </a>
                                            </span>
                                        </c:if>
                                    </div>
                                </li>
                            </c:forEach>

                        </ul>

                        <div class="layui-form layui-form-pane">
                            <form id="reply_form" action="/home/comment/reply" method="post">
                                <div class="layui-form-item layui-form-text">
                                    <div class="layui-input-block">
                                    <textarea id="L_content" name="commentContent" required lay-verify="required"
                                              placeholder="请输入内容"
                                              class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <input type="hidden" name="commentAuthorId" value="${sessionScope.user.userId}">
                                    <input type="hidden" name="commentAuthorName"
                                           value="${sessionScope.user.userNickname}">
                                    <input type="hidden" name="commentAuthorAvatar"
                                           value="${sessionScope.user.userAvatar}">
                                    <input type="hidden" name="articleId" value="${article.articleId}">
                                    <input type="hidden" name="articleTitle" value="${article.articleTitle}">
                                    <input type="button" id="reply-btn" class="layui-btn" lay-filter="*" lay-submit value="提交回复">
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

<rapid:override name="footer-script">

    <%--    TODO @回复功能--%>
    <script>
        function appendName() {
            var ele = document.getElementById("L_content");
            ele.value = ele.value;
        }
    </script>

    <script type="text/javascript">
        $("#reply-btn").click(function () {
            var content = $("#L_content").val();
            if (content == "") {
                alert("回复内容不能为空!");
            } else {
                $.ajax({
                    async: false,//同步，待请求完毕后再执行后面的代码
                    type: "POST",
                    url: '/home/comment/reply',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    data: $("#reply_form").serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.status == "error") {
                            alert(data.msg);
                            window.location.reload();
                        } else if (data.status == "login") {
                            alert(data.msg);
                            window.location.href = "/home/login";
                        } else {
                            alert(data.msg);
                            window.location.reload();
                        }
                    },
                    error: function () {
                        alert("数据获取失败")
                    }
                })
            }
        })
    </script>

    <script type="text/javascript">
        $("#add_fav").click(function () {
            var user = document.getElementById("user").value;
            console.log(user);
            var articleId = Number(document.getElementById("articleId").value);
            if (user != null){
                $.ajax({
                    async: false,//同步，待请求完毕后再执行后面的代码
                    type: "POST",
                    url: '/home/user/addFav/' + articleId,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    dataType: "json",
                    success: function (data) {
                        if (data.status == "error") {
                            alert(data.msg);
                            window.location.href="/home/login";
                        } else if (data.status == "login") {
                            alert(data.msg);
                            window.location.href="/home/login";
                        }
                    },
                    error: function () {
                        alert("收藏失败！");
                        window.location.reload();
                    }
                })
            }
        })
    </script>

    <script type="text/javascript">
        function delFav(){
            var msg=confirm("确定要取消收藏吗？");
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

    <script type="text/javascript">
        function delArt() {
            var msg = confirm("确定要删除文章吗？");
            if (msg == true) {
                return true;
            } else if (msg == false) {
                return false;
            }
        }
    </script>

    <script type="text/javascript">
        function delCom() {
            var msg = confirm("确定要删除评论吗？");
            if (msg == true) {
                return true;
            } else if (msg == false) {
                return false;
            }
        }
    </script>

    <script>
        layui.use(['form', 'layedit', 'laydate'], function () {
            var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;

            //上传图片,必须放在 创建一个编辑器前面
            layedit.set({
                uploadImage: {
                    url: '/back/upload/img' //接口url
                    , type: 'post' //默认post
                }
            });

            //创建一个编辑器
            var editIndex = layedit.build('content', {
                    height: 350,
                }
            );
            layui.code({
                elem: 'pre', //默认值为.layui-code
            });

            layedit.build('content', {
                tool: [
                    'strong' //加粗

                    , '|' //分割线

                    , 'link' //超链接
                    , 'unlink' //清除链接
                    , 'face' //表情
                    , 'image' //插入图片
                    , 'code'
                    , 'preview' //预览
                ]
            });

        });

    </script>

</rapid:override>

<%@ include file="../Public/index_framework.jsp" %>