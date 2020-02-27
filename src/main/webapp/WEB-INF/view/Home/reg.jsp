<%--
  Created by IntelliJ IDEA.
  User: Hanabi
  Date: 2020/2/23
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
        Ks社区
    </title>
    <link rel="stylesheet" href="../../../../statics/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="../../../../statics/css/back.css">
    <link rel="stylesheet" href="../../../../statics/css/global.css">
</head>

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

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li><a href="/home/login">登入</a></li>
                <li class="layui-this">注册</li>
            </ul>

            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">

                        <form id="reg_form" method="post">
                            <div class="layui-form-item">
                                <label for="L_name" class="layui-form-label">账号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_name" name="userName" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_nickname" class="layui-form-label">昵称</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="L_nickname" name="userNickname" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_pass" name="userPass" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_repass" class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="L_repass" name="rePass" required lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <input type="button" id="submit-btn" class="layui-btn" lay-filter="*" lay-submit value="立即注册">
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="fly-footer">
    <!-- 底部固定区域 -->
    <p>
        © <a href="/home">Ks社区</a> 2020
    </p>
</div>

<script src="../../../../statics/js/jquery-3.4.1.js"></script>
<script src="../../../../statics/plugin/layui/layui.all.js"></script>
<script src="../../../../statics/js/back.js"></script>
<%--<script src="/js/jquery-3.4.1.js"></script>--%>

<script type="text/javascript">

    $("#submit-btn").click(function () {
        var username = $('#L_name').val();
        var nickname = $("#L_nickname").val();
        var password = $("#L_pass").val();
        var repass = $('#L_repass').val();
        if (username == "") {
            alert("用户名不可为空!");
        } else if (password == "" || repass == "") {
            alert("密码不可为空!");
        } else if (nickname == "") {
            alert("昵称不可为空!");
        } else {
            $.ajax({
                async: false,//同步，待请求完毕后再执行后面的代码
                type: "POST",
                url: '/home/regVerify',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: $("#reg_form").serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.status == "error") {
                        alert(data.msg);
                    } else {
                        alert(data.msg);
                        self.location=document.referrer;
                    }
                },
                error: function () {
                    alert("数据获取失败")
                }
            })
        }
    })

</script>
