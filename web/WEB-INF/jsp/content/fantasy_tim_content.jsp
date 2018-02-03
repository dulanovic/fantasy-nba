<%-- 
    Document   : fantasy_tim_content
    Created on : Jun 14, 2017, 7:26:34 PM
    Author     : Korisnik
--%>
<%@page import="domen.FantasyIgrac"%>
<%@page import="domen.Igrac"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="podaciFantasyTim">
    <h4>Osnovni podaci:</h4>
    <table>
        <tr>
            <td class="pitanje">Naziv tima:</td>
            <td>${fantasy_tim.timNaziv}</td>
        </tr>
        <tr>
            <td class="pitanje">Vlasnik:</td>
            <td>${fantasy_tim.vlasnik}</td>
        </tr>
        <tr>
            <td class="pitanje">Naziv lige:</td>
            <td><a href="./fantasy?action=fantasyLiga&liga=${fantasy_tim.liga.ligaId}">${fantasy_tim.liga.ligaNaziv}</a></td>
        </tr>
        <tr>
            <td class="pitanje">Broj poena:</td>
            <td>${fantasy_tim.brojPoena}</td>
        </tr>
    </table>
</div>
<div id="ispisValidacija"></div>
<c:if test="${fantasy_tim.liga.status.statusLigeNaziv != 'Pre-draft'}">
    <div id="timRoster" style="margin-top: 30px;">
        <h4 style="margin-left: 55px;">Roster:</h4>
        <table id="igraciTimaTabela">
            <thead>
                <tr>
                    <th></th>
                    <th>Ime i prezime</th>
                    <th>Tim</th>
                    <th>Pozicija</th>
                    <th>Status</th>
                    <th>Sl. utakmica</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="current_player" items="${fantasy_tim.fantasyIgracList}">
                    <c:set var="trenutni_igrac" value="${current_player.igrac}" />
                    <tr>
                        <td class="centriraj"><a href="./fantasy?action=igrac&igrac=${current_player.igrac.igracId}" target="_blank"><img src="${current_player.igrac.igracSlika}" class="slikaFantasyIgrac" /></a></td>
                        <td><a href="./fantasy?action=igrac&igrac=${current_player.igrac.igracId}" target="_blank">${current_player.igrac.ime} ${current_player.igrac.prezime}</a></td>
                        <td class="centriraj"><a href="./fantasy?action=tim&tim=${current_player.igrac.tim.timId}" target="_blank"><img src="${current_player.igrac.tim.timLogo}" class="logoFantasyIgrac" /></a></td>
                        <td class="centriraj pozicijaIgrac">${current_player.igrac.pozicija}</td>
                        <c:choose>
                            <c:when test="${user_login == fantasy_tim.vlasnik}">
                                <td class="centriraj">
                                    <select class="izborStatus" id="izbor${current_player.fantasyIgracId}">
                                        <option selected disabled>Izaberite status</option>
                                        <option value="Aktivan">Aktivan</option>
                                        <option value="Rezerva">Rezerva</option>
                                    </select>
                                </td>
                            </c:when>
                            <c:when test="${user_login != fantasy_tim.vlasnik}">
                                <td class="centriraj">${current_player.status.statusIgracaNaziv}</td>
                            </c:when>
                        </c:choose>
                        <td class="centriraj"><% Igrac i = (Igrac) pageContext.getAttribute("trenutni_igrac");
                            String[] ispis = i.getUsi().vratiIspis(i, i.getSledecaUtakmica());%>
                            <%=ispis[0]%><br /><%=ispis[1]%></td>
                            <c:if test="${user_login == fantasy_tim.vlasnik}">
                            <td>
                                <c:if test="${fantasy_tim.validator.mozeOtkaz(current_player.igrac.pozicija)}">
                                    <button class="ui-button ui-widget ui-corner-all" onclick="otpustiIgraca(${current_player.fantasyIgracId})">Otkaz</button>
                                </c:if>
                            </td>
                    <input type="hidden" class="fantasy_igrac_id" value="${current_player.fantasyIgracId}">
                </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${user_login == fantasy_tim.vlasnik}">
            <div style="margin-top: 20px;">
                <button class="ui-button ui-widget ui-corner-all" onclick="posaljiRoster(null)" style="margin-left: 50px;">Sačuvaj izmene</button>
                <a href="./fantasy?action=trziste&liga=${fantasy_tim.liga.ligaId}" target="_blank" class="ui-button ui-widget ui-corner-all" style="margin-left: 10px;">Tržište</a>
            </div>
        </c:if>
    </div>
    <div id="utakmice">
        <table style="margin: 0 auto;">
            <thead>
                <tr>
                    <th>Kolo</th>
                    <th>Domaćin</th>
                    <th>Gost</th>
                    <th>Rezultat</th>
                    <th>Datum početka</th>
                    <th>Datum završetka</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="current_match" items="${fantasy_tim.listaSvihUtakmica}">
                    <tr>
                        <td class="centriraj">${current_match.kolo}</td>
                        <td class="centriraj"><a href="./fantasy?action=fantasyTim&tim=${current_match.domacin.fantasyTimId}" target="_blank">${current_match.domacin.timNaziv}</a></td>
                        <td class="centriraj"><a href="./fantasy?action=fantasyTim&tim=${current_match.gost.fantasyTimId}" target="_blank">${current_match.gost.timNaziv}</a></td>
                        <td class="centriraj">${current_match.poeniDomacin}-${current_match.poeniGost}-${current_match.nereseno}</td>
                        <td class="centriraj">${current_match.datumPocetak}</td>
                        <td class="centriraj">${current_match.datumKraj}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="hidden" id="fantasy_tim_id" value="${fantasy_tim.fantasyTimId}">
    </div>
</c:if>
<c:if test="${user_login == fantasy_tim.vlasnik}">
    <a href="./fantasy?action=obrisiTim&tim=${fantasy_tim.fantasyTimId}" class="ui-button ui-widget ui-corner-all" onclick="return potvrdiBrisanje()" style="margin-top: 10px; margin-left: 50px;">Obriši tim</a>
</c:if>