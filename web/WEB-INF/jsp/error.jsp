<%-- 
    Document   : error
    Created on : May 23, 2017, 2:37:38 PM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/template/template.jsp">
    <jsp:param name="title" value="Nedozvoljen pristup!"></jsp:param>
    <jsp:param name="content" value="error_content"></jsp:param>
</jsp:include>