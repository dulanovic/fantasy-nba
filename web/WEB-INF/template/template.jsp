<%-- 
    Document   : template
    Created on : May 22, 2017, 4:30:37 AM
    Author     : Korisnik
--%>

<%@page import="domen.FantasyTim"%>
<%@page import="constants.WebConstants"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${param.title}</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css" />

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.css">
        <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui/external/jquery/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.js"></script>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/DataTables-1.10.15/media/css/jquery.dataTables.min.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/DataTables-1.10.15/media/js/jquery.dataTables.min.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/ajax.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/validacija.js"></script>

    </head>
    <body>
        <div id="main">

            <%@include file="header.jsp" %>

            <div id="site_content">

                <jsp:include page="/WEB-INF/jsp/content/${param.content}.jsp"></jsp:include>

                </div>

            <%@include file="footer.jsp"%>

        </div>
    </body>
    <c:if test="${param.script != null}">
        <jsp:include page="/WEB-INF/jsp/script/${param.script}.jsp"></jsp:include>
    </c:if>
</html>
