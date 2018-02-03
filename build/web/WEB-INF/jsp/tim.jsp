<%-- 
    Document   : tim
    Created on : Jun 27, 2017, 3:41:34 AM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/template/template.jsp">
    <jsp:param name="title" value="Tim - ${nba_tim.timNaziv}"></jsp:param>
    <jsp:param name="content" value="tim_content"></jsp:param>
    <jsp:param name="script" value="tim_script"></jsp:param>
</jsp:include>