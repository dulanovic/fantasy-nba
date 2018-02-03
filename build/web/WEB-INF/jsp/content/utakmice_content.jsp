<%-- 
    Document   : utakmice_content
    Created on : Jun 27, 2017, 6:26:24 PM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="utakmiceFilter">
    <select id="izborTim">
        <option selected disabled>Izaberite tim</option>
        <option value="svi">Svi</option>
        <c:forEach var="current_team" items="${all_teams}">
            <option value="${current_team.timId}">${current_team.timNaziv}</option>
        </c:forEach>
    </select>
    <input type="text" value="${trenutni_datum}" id="datepicker" onchange="vratiUtakmiceParametar('datum', this.value)">
</div>
<div id="utakmice">

</div>