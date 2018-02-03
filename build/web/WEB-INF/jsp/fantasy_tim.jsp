<%-- 
    Document   : fantasy_tim
    Created on : Jun 14, 2017, 7:26:16 PM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/template/template.jsp">
    <jsp:param name="title" value="Tim - ${fantasy_tim.timNaziv}"></jsp:param>
    <jsp:param name="content" value="fantasy_tim_content"></jsp:param>
    <jsp:param name="script" value="fantasy_tim_script"></jsp:param>
</jsp:include>