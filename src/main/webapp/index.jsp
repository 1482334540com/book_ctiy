<%--jsp标准代码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:forward page="/bookServlet?action=paging"></jsp:forward>


<%
    String basePath=request.getScheme()
            +"://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/"
            ;
    request.getSession().setAttribute("path",basePath);
%>