<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/23
  Time: 14:18
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
            <li class="layui-this" lay-id="info">我的资料</li>
            <li lay-id="avatar">头像</li>
            <li lay-id="pass">密码</li>
        </ul>
        <div class="layui-tab-content" style="padding: 20px 0;">
            <div class="layui-form layui-form-pane layui-tab-item layui-show">
                <form action="/home/user/profileEdit" method="post">
                    <input type="hidden" name="userId" value="${user.userId}">
                    <div class="layui-form-item">
                        <label class="layui-form-label">用户名 </label>
                        <div class="layui-input-inline">
                            <input type="text" value="${user.userName}"  id="userName" required
                                   autocomplete="off" class="layui-input" disabled>
                        </div>
                        <div class="layui-form-mid layui-word-aux" id="userNameTips"></div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">昵称</label>
                        <div class="layui-input-inline">
                            <input type="text" id="userNickname" name="userNickname" required lay-verify="required" autocomplete="off" value="${user.userNickname}" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">个人简介</label>
                        <div class="layui-input-block">
                            <textarea placeholder="随便写点什么吧" id="userProfile" name="userProfile" autocomplete="off" class="layui-textarea" style="height: 80px;">${user.userProfile}</textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn" key="set-mine" lay-filter="*" lay-submit>确认修改</button>
                    </div>
                </form>
            </div>


                <div class="layui-form layui-form-pane layui-tab-item">
                    <form class="layui-form" action="/back/user/editSubmit" id="userForm" method="post">
                        <input type="hidden" name="userId" value="${user.userId}">
                        <div class="layui-form-item">
                            <label class="layui-form-label">头像</label>
                            <div class="layui-input-inline">
                                <div class="layui-upload">
                                    <div class="layui-upload-list" style="">
                                        <img class="layui-upload-img" src="${user.userAvatar}" id="demo1" width="100"
                                             height="100">
                                        <p id="demoText"></p>
                                    </div>
                                    <button type="button" class="layui-btn" id="test1">上传图片</button>
                                    <input type="hidden" name="userAvatar" id="userAvatar" value="${user.userAvatar}">
                                </div>
                            </div>
                        </div>
                    <div class="layui-form-item">
                        <button class="layui-btn" key="set-mine" lay-filter="*" lay-submit>确认修改</button>
                    </div>
                    </form>
                </div>


            <div class="layui-form layui-form-pane layui-tab-item">
                <form id="passForm" action="/home/user/passEdit" method="post">
                    <input type="hidden" name="userId" value="${user.userId}">
                    <div class="layui-form-item">
                        <label for="oldPass" class="layui-form-label">当前密码</label>
                        <div class="layui-input-inline">
                            <input type="password" id="oldPass" name="oldPass" required lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label for="newPass" class="layui-form-label">新密码</label>
                        <div class="layui-input-inline">
                            <input type="password" id="newPass" name="newPass" required lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-form-mid layui-word-aux">6到16个字符</div>
                    </div>
                    <div class="layui-form-item">
                        <label for="rePass" class="layui-form-label">确认密码</label>
                        <div class="layui-input-inline">
                            <input type="password" id="rePass" name="rePass" required lay-verify="required" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
<%--                        TODO button和input的区别--%>
<%--                        <button id="submit-btn" class="layui-btn" key="set-mine" lay-filter="*" lay-submit>确认修改</button>--%>
                        <input type="button" id="submit-btn" class="layui-btn" lay-filter="*" lay-submit value="确认修改">
                    </div>
                </form>
            </div>

        </div>

    </div>
</div>
</rapid:override>

<rapid:override name="footer-script">

    <script>
        //上传图片
        layui.use('upload', function () {
            var $ = layui.jquery,
                upload = layui.upload;
            var uploadInst = upload.render({
                elem: '#test1',
                url: '/back/upload/img',
                before: function (obj) {
                    obj.preview(function (index, file, result) {
                        $('#demo1').attr('src', result);
                    });
                },
                done: function (res) {
                    $("#userAvatar").attr("value", res.data.src);
                    if (res.code > 0) {
                        return layer.msg('上传失败');
                    }
                },
                error: function () {
                    var demoText = $('#demoText');
                    demoText.html('' +
                        '<span style="color: #FF5722;">上传失败</span>' +
                        ' <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });

        });

        $(document).ready(function () {
            $("li#setting").addClass(" layui-this")
        })
    </script>

    <script src="../../../statics/js/jquery-3.4.1.js"></script>
    <%--<script src="/js/jquery-3.4.1.js"></script>--%>

    <script type="text/javascript">

        $("#submit-btn").click(function () {
            var oldPass = $("#oldPass").val();
            var newPass = $("#newPass").val();
            var rePass = $("#rePass").val();
            if (oldPass == "") {
                alert("当前密码不可为空!");
            } else if (newPass == "" || rePass == "") {
                alert("新密码不可为空!");
            } else {
                $.ajax({
                    async: false,//同步，待请求完毕后再执行后面的代码
                    type: "POST",
                    url: '/home/user/passEdit',
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    data: $("#passForm").serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.status == "error") {
                            alert(data.msg);
                            top.location.href = "/home/user/index";
                        } else {
                            alert(data.msg);
                            top.location.href = "/home/user/index";
                        }
                    },
                    error: function () {
                        alert("数据获取失败")
                    }
                })
            }
        })

    </script>
</rapid:override>

<jsp:include page="../Public/user_framework.jsp"/>