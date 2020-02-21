<%--
  Created by IntelliJ IDEA.
  User: zhuqiu
  Date: 2020/2/10
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="rapid" uri="http://www.rapid-framework.org.cn/rapid" %>

<rapid:override name="title">
    - 分类列表
</rapid:override>
<rapid:override name="header-style">
    <style>
        .layui-input-block {
            margin:0px 10px;
        }
    </style>
</rapid:override>

<rapid:override name="content">


    <blockquote class="layui-elem-quote">
        <span class="layui-breadcrumb" lay-separator="/">
              <a href="/back/index">首页</a>
              <a href="/back/category">分类列表</a>
              <a><cite>编辑分类</cite></a>
        </span>
    </blockquote>
    <div class="layui-row">
        <div class="layui-col-md4" >
            <form class="layui-form"  method="post" id="myForm" action="/back/category/editSubmit">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <strong>修改分类</strong>
                    </div>
                    <input type="hidden" name="categoryId" value="${category.categoryId}">
                    <div class="layui-input-block">
                        名称 <span style="color: #FF5722; ">*</span>
                        <input type="text" name="categoryName" value="${category.categoryName}" placeholder="" autocomplete="off" class="layui-input" required>
                    </div>
                    <br>
                    <div class="layui-input-block">
                        父节点 <span style="color: #FF5722; ">*</span>
                        <select name="superCategoryId" class="layui-input" required>
                            <c:forEach items="${categoryList}" var="c">
                                <c:choose>
                                    <c:when test="${c.categoryId==category.superCategoryId}">
                                        <option value="${c.categoryId}" selected>${c.categoryName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${c.superCategoryId==0}">
                                            <option value="${c.categoryId}">${c.categoryName}</option>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <option value="0" <c:if test="${category.superCategoryId==0}">selected</c:if> >无</option>
                        </select>
                    </div>
                    <br>
                    <br>
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formDemo" type="submit">保存</button>
                    </div>
                </div>
            </form>
            <br><br>
        </div>
        <div class="layui-col-md8" >
            <table class="layui-table" >
                <colgroup>
                    <col width="300">
                    <col width="100">
                    <col width="120">
                    <col width="100">
                    <col width="50">
                </colgroup>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>文章数</th>
                    <th>操作</th>
                    <th>ID</th>
                    <th>superID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categoryList}" var="c">
                    <c:if test="${c.superCategoryId==0}">
                        <tr>
                            <td>
                                <a href="/category/${c.categoryId}" target="_blank">${c.categoryName}</a>
                            </td>
                            <td>
                                <a href="/category/${c.categoryId}" target="_blank">${c.articleCount}</a>
                            </td>
                            <td>
                                <a href="/back/category/edit/${c.categoryId}" class="layui-btn layui-btn-mini">编辑</a>
                                <c:if test="${c.articleCount==0}">
                                    <a href="/back/category/delete/${c.categoryId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                                </c:if>
                            </td>
                            <td>${c.categoryId}</td>
                            <td>${c.superCategoryId}</td>
                        </tr>
                        <c:forEach items="${categoryList}" var="c2">
                            <c:if test="${c2.superCategoryId==c.categoryId}">
                                <tr>
                                    <td>
                                        <a href="/category/${c2.categoryId}" target="_blank">——${c2.categoryName}</a>
                                    </td>
                                    <td>
                                        <a href="/category/${c2.categoryId}" target="_blank">${c2.articleCount}</a>
                                    </td>
                                    <td>
                                        <a href="/back/category/edit/${c2.categoryId}" class="layui-btn layui-btn-mini">编辑</a>
                                        <c:if test="${c2.articleCount==0}">
                                            <a href="/back/category/delete/${c2.categoryId}" class="layui-btn layui-btn-danger layui-btn-mini" onclick="return confirmDelete()">删除</a>
                                        </c:if>
                                    </td>
                                    <td class="cate-parent">${c2.categoryId}</td>
                                    <td>${c2.superCategoryId}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>


                </c:forEach>
                </tbody>
            </table>
            <blockquote class="layui-elem-quote layui-quote-nm">
                温馨提示：
                <ul>
                    <li>分类最多只有两级，一级分类pid=0，二级分类pid=其父节点id</li>
                    <li>如果该分类包含文章，将不可删除</li>
                </ul>
            </blockquote>
        </div>

    </div>






</rapid:override>
<rapid:override name="footer-script">

</rapid:override>

<%@ include file="../Public/framework.jsp"%>
