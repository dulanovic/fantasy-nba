<%-- 
    Document   : igrac
    Created on : Jun 27, 2017, 1:01:22 AM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/template/template.jsp">
    <jsp:param name="title" value="Igrač - ${nba_igrac}"></jsp:param>
    <jsp:param name="content" value="igrac_content"></jsp:param>
    <jsp:param name="script" value="igrac_script"></jsp:param>
</jsp:include>