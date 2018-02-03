<%-- 
    Document   : trziste_content
    Created on : Jun 21, 2017, 1:51:04 PM
    Author     : Korisnik
--%>

<%@page import="domen.FantasyTim"%>
<%@page import="util.Util"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="fantasy_team" value="${fantasy_tim}" />
<h3 style="text-align: center;">Tržište igrača</h3>
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
    <input type="text" id="igracPretraga" value="" size="20" onkeyup="vratiIgracePretraga('trziste')">
    <input type="hidden" id="liga_id" value="${fantasy_tim.liga.ligaId}">
</div>
<div id="tabelaIzbor" style="margin-top: 30px;"></div>