<%-- 
    Document   : fantasy_liga
    Created on : Jun 4, 2017, 12:52:36 AM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/template/template.jsp">
    <jsp:param name="title" value="Liga - ${fantasy_liga.ligaNaziv}"></jsp:param>
    <jsp:param name="content" value="fantasy_liga_content"></jsp:param>    
    <jsp:param name="script" value="fantasy_liga_script"></jsp:param>
</jsp:include>