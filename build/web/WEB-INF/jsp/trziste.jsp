<%-- 
    Document   : trziste
    Created on : Jun 21, 2017, 1:50:44 PM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/template/template.jsp">
    <jsp:param name="title" value="Tržište igrača - ${fantasy_tim.liga.ligaNaziv}"></jsp:param>
    <jsp:param name="content" value="trziste_content"></jsp:param>
    <jsp:param name="script" value="trziste_script"></jsp:param>
</jsp:include>