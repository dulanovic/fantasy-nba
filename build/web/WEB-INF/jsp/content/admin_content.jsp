<%-- 
    Document   : admin_content
    Created on : May 23, 2017, 3:50:07 PM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="tabs">
    <ul>
        <li><a href="#tabs-1">NBA</a></li>
        <li><a href="#tabs-2">Fantasy</a></li>
    </ul>
    <div id="tabs-1">
        <table>
            <tr>
                <td>Datum poslednjeg preuzimanja rezultata:</td>
                <td><span class="datumTabela">${datum_zadnjeg_azuriranja}</span></td>
                <td><button class="ui-button ui-widget ui-corner-all" style="margin-left: 5px;" onclick="azurirajRezultate()">Ažuriraj rezultate</button></td>
            </tr>
        </table>
        <button class="ui-button ui-widget" onclick="preuzmiRaspored()" style="margin-top: 10px;">Unesi respored</button>
    </div>
    <div id="tabs-2">
        <table id="adminTabela">
            <thead>
                <tr>
                    <th>Naziv lige</th>
                    <th>Administrator</th>
                    <th>Status lige</th>
                    <th>Dat. posl. ažur.</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="current_league" items="${all_leagues}">
                    <tr>
                        <td><a href="./fantasy?action=fantasyLiga&liga=${current_league.ligaId}" target="_blank">${current_league.ligaNaziv}</a></td>
                        <td class="centriraj">${current_league.administrator}</td>
                        <td class="centriraj">${current_league.status}</td>
                        <td class="centriraj">${current_league.datumAzuriranjaString}</td>
                        <td class="centriraj">
                            <c:if test="${current_league.status.statusLigeNaziv == 'Post-draft'}">
                                <button class="ui-button ui-widget ui-corner-all" onclick="odobriLigu(${current_league.ligaId})">Odobri ligu</button>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${(current_league.datumAzuriranja != datum_zadnjeg_azuriranja) and (current_league.status.statusLigeNaziv == 'Sezona')}">
                                <button class="ui-button ui-widget ui-corner-all" onclick="azurirajLigu(${current_league.ligaId})">Ažuriraj ligu</button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>