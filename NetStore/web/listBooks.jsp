<%@ page contentType="text/html;charset=UTF-8"
         language="java" %>
<%@ include file="header.jsp"%>
<a href="${pageContext.request.contextPath}">所有分类：</a>
<c:forEach items="${cs}" var="c">
    <a
            href="${pageContext.request.contextPath}/client/ClientServlet?op=showCategoryBooks&categoryId=${c.id}">${c.name}</a>
</c:forEach>
<table border="0" width="438" align="center">
    <tr>
        <c:forEach items="${page.records}" var="b">
            <td>
                <img src="${pageContext.request.contextPath}/images/${b.path}/${b.filename}"
                         alt="${b.filename}" width="200" height="200">
                书名：${b.name}<br/>
                作者：${b.author}<br/>
                单价：${b.price}<br/>
                <a href="">去看看</a>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <td align="center" colspan="3">
            <%@include file="/common/page.jsp" %>
        </td>
    </tr>
</table>

</body>
</html>
