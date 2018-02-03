<%-- 
    Document   : fantasy_content
    Created on : May 23, 2017, 2:55:02 PM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test = "${(user_login.tip.tipNaziv == 'Registrovani korisnik') and (fn:length(user_login.fantasyLigaList) lt 3)}">
    <form method="POST" action="./fantasy" onsubmit="return validacijaUnosLige()">
        <h4>Napravite svoju ligu</h4>
        <table>
            <tr>
                <td>Naziv lige:</td>
                <td><input type="text" name="nazivLige" value="" onkeyup="proveriNazivLige(this.value)"></td>
                <td><p id="validacija-nazivLige" class="validacija">${validacija_poruka}</p></td>
            </tr>
        </table>
        <input type="hidden" name="action" value="novaLiga">
        <input type="submit" class="ui-button ui-widget ui-corner-all" value="Kreiraj ligu" style="margin-top: 10px;">
    </form>
</c:if>
<div id="utakmice">
    <h4>Postojeće lige:</h4>
    <table>
        <thead>
        <th>Naziv lige</th>
        <th>Administrator</th>
        <th>Broj igrača</th>
        <th>Status</th>
        </thead>
        <tbody>
            <c:forEach var="current_league" items="${all_leagues}">
                <tr>
                    <td><a href="./fantasy?action=fantasyLiga&liga=${current_league.ligaId}">${current_league.ligaNaziv}</a></td>
                    <td class="centriraj">${current_league.administrator.korisnickoIme}</td>
                    <td class="centriraj">${current_league.brojTimova}</td>
                    <td class="centriraj">${current_league.status}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>