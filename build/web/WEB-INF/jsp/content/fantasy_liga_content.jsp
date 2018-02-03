<%-- 
    Document   : fantasy_liga_content
    Created on : Jun 4, 2017, 12:52:55 AM
    Author     : Korisnik
--%>

<%@page import="domen.FantasyLiga"%>
<%@page import="domen.Korisnik"%>
<%@page import="java.util.List"%>
<%@page import="domen.FantasyTim"%>
<%@page import="constants.WebConstants"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div>
    <h4>Osnovni podaci:</h4>
    <table>
        <tr><td class="pitanje">Naziv lige:</td><td><a href="./fantasy?action=fantasyLiga&liga=${fantasy_liga.ligaId}">${fantasy_liga.ligaNaziv}</a></td></tr>
        <tr><td class="pitanje">Administrator:</td><td>${fantasy_liga.administrator}</td></tr>
        <tr><td class="pitanje">Broj slobodnih mesta:</td><td>${4 - fn:length(fantasy_liga.fantasyTimList)}</td></tr>
    </table>
</div>
<c:choose>
    <c:when test = "${empty fantasy_liga.fantasyTimList}">
        <h4 style="margin-top: 20px;">Liga trenutno nema nijedan tim, priključite se!</h4>
    </c:when>
    <c:when test="${fn:length(fantasy_liga.fantasyTimList) gt 0}">
        <div id="utakmice">
            <h4>Učesnici lige:</h4>
            <table>
                <thead>
                    <tr><th>Naziv tima</th><th>Vlasnik</th><th>Broj poena</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="current_team" items="${fantasy_liga.fantasyTimList}">
                        <tr>
                            <td><a href="./fantasy?action=fantasyTim&tim=${current_team.fantasyTimId}" target="_blank">${current_team.timNaziv}</a></td>
                            <td class="centriraj">${current_team.vlasnik}</td>
                            <td class="centriraj">${current_team.brojPoena}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>
</c:choose>
<c:if test="${fantasy_liga.status.statusLigeNaziv == 'Sezona'}">
    <a href="./fantasy?action=fantasyUtakmice&liga=${fantasy_liga.ligaId}" class="ui-button ui-widget ui-corner-all">Utakmice</a>
</c:if>
<%  boolean imaTim = false;
    Korisnik k = (Korisnik) request.getSession().getAttribute(WebConstants.USER_LOGIN);
    List<FantasyTim> lista = ((FantasyLiga) request.getAttribute(WebConstants.FANTASY_LIGA)).getFantasyTimList();

    if (k == null || lista == null) {
        return;
    }

    for (FantasyTim ft : lista) {
        if (ft.getVlasnik().equals(k)) {
            imaTim = true;
        }
    }
    if (!imaTim && lista.size() < 4 && k.getTip().getTipNaziv().equals("Registrovani korisnik") && k.getFantasyTimList().size() < 10) { %>
<form method="POST" action="./fantasy" onsubmit="return validacijaUnosTima()">
    <table>
        <tr>
            <td>Naziv tima:</td>
            <td><input type="text" name="nazivTima" value="" onkeyup="proveriNazivTima(this.value)"></td>
            <td><p id="validacija-nazivTima" class="validacija">${validacija_naziv_tima}</p></td>
        </tr>
    </table>
    <input type="hidden" name="action" value="noviTim">
    <input type="hidden" name="liga" value="${fantasy_liga.ligaId}">
    <input type="submit" class="ui-button ui-widget ui-corner-all" value="Kreiraj tim" style="margin-top: 10px;">
</form>
<% }
    FantasyLiga fl = (FantasyLiga) request.getAttribute(WebConstants.FANTASY_LIGA);
    if (imaTim && lista.size() == 4 && fl.getStatus().getStatusLigeNaziv().equals("Pre-draft")) {%>
<a href="./fantasy?action=draft&liga=${fantasy_liga.ligaId}" class="ui-button ui-widget ui-corner-all">Draft</a>
<% }%>
<br />
<c:if test="${fantasy_liga.administrator == user_login}">
    <a href="./fantasy?action=obrisiLigu&liga=${fantasy_liga.ligaId}" class="ui-button ui-widget ui-corner-all" onclick="return potvrdiBrisanje()" style="margin-top: 10px;">Obriši ligu</a>
</c:if>
