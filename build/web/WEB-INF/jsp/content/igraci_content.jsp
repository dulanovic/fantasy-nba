<%-- 
    Document   : igraci_content
    Created on : Jun 26, 2017, 6:48:03 PM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="pretragaIgraci">
    <select id="izborPozicija">
        <option selected disabled>Izaberite poziciju</option>
        <option value="svi">Svi</option>
        <option value="PG">Plejmejker</option>
        <option value="SG">Bek</option>
        <option value="SF">Krilo</option>
        <option value="PF">Krilni centar</option>
        <option value="C">Centar</option>
    </select>
    <select id="izborTim">
        <option selected disabled>Izaberite tim</option>
        <option value="svi">Svi</option>
        <c:forEach var="current_team" items="${all_teams}">
            <option value="${current_team.timId}">${current_team.timNaziv}</option>
        </c:forEach>
    </select>
    <input type="text" id="igracPretraga" value="" size="20" onkeyup="vratiIgracePretraga('prikaz')">
    <a href="./fantasy?action=igraciTabela" class="ui-button ui-widget ui-corner-all" style="margin-left: 20px;">Tabelarni prikaz</a>
</div>
<div id="tabelaIzbor"></div>