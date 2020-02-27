<%--
  Created by IntelliJ IDEA.
  User: zhuqiu
  Date: 2020/2/8
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="KKSK,KSForum社区论坛">
    <link rel="stylesheet" href="../../../statics/plugin/layui/css/layui.css">
    <link rel="stylesheet" href="/layui/css/layui.css" type='text/css' media='all'>
    <link rel="stylesheet" href="../../../statics/css/global.css">
    <link rel="stylesheet" href="/css/global.css" type='text/css' media='all'>
    <title>KS论坛——后台登录</title>
</head>
<body>

<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item layui-this">
                <p><i class="iconfont"></i>后台管理系统登录页面</p>
            </li>
        </ul>

        <ul class="layui-nav fly-nav-user">
            <!-- 未登入的状态 -->
            <li class="layui-nav-item">
                <a class="iconfont icon-touxiang layui-hide-xs" href="user/login.html"></a>
            </li>
            <li class="layui-nav-item">
                <a href="/login">登入</a>
            </li>
            <!--            <li class="layui-nav-item">-->
            <!--                <a href="/register">注册</a>-->
            <!--            </li>-->
        </ul>
    </div>
</div>

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li class="layui-this">登入</li>
                <!--                <li><a href="reg.html">注册</a></li>-->
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">

                        <form id="admin_form" method="post">
                            <div class="layui-form-item">
                                <label for="admin_name" class="layui-form-label">账号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="admin_name" name="username" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="admin_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="admin_pass" name="password" required
                                           lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <input type="button" id="submit-btn" class="layui-btn" lay-filter="*" lay-submit value="立即登录">
                                <!--                                <span style="padding-left:20px;">-->
                                <!--                                  <a href="forget.html">忘记密码？</a>-->
                                <!--                                </span>-->
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="fly-footer">
    <p><a href="" target="_blank">Ks社区</a> 2020 </p>
</div>



<script src="../../../statics/js/jquery-3.4.1.js"></script>
<%--<script src="/js/jquery-3.4.1.js"></script>--%>

<script type="text/javascript">

    $("#submit-btn").click(function () {
        var user = $("#admin_name").val();
        var password = $("#admin_pass").val();
        if (user == "") {
            alert("用户名不可为空!");
        } else if (password == "") {
            alert("密码不可为空!");
        } else {
            $.ajax({
                async: false,//同步，待请求完毕后再执行后面的代码
                type: "POST",
                url: '/back/loginVerify',
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                data: $("#admin_form").serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.status == "error") {
                        alert(data.msg);
                    } else {
                        alert(data.msg);
                        window.location.href = "/back/index";
                    }
                },
                error: function () {
                    alert("数据获取失败")
                }
            })
        }
    })

</script>


</body>
</html>