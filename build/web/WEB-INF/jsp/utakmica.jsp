<%-- 
    Document   : utakmica
    Created on : Jun 28, 2017, 12:40:03 AM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/template/template.jsp">
    <jsp:param name="title" value="${nba_utakmica.domacin.skraceniNaziv} ${nba_utakmica.poeniDomacin}:${nba_utakmica.poeniGost} ${nba_utakmica.gost.skraceniNaziv}"></jsp:param>
    <jsp:param name="content" value="utakmica_content"></jsp:param>
    <jsp:param name="script" value="utakmica_script"></jsp:param>
</jsp:include>