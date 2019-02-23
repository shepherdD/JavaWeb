<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
</head>
<body>
    <br/>
    <br/>
    <h1>后台管理</h1>
    <br/>
    <br/>
    <a href="${pageContext.request.contextPath}/manage/addCategory.jsp">添加分类</a>
    <a href="${pageContext.request.contextPath}/manage/ManageServlet?op=showAllCategorys">查询分类</a>
    <a href="${pageContext.request.contextPath}/manage/ManageServlet?op=addBookUI">添加图书</a>
    <a href="${pageContext.request.contextPath}/manage/ManageServlet?op=showPageBooks">查询图书</a>
    <a href="">待处理订单</a>
    <a href="">已处理订单</a>
    <br/>
