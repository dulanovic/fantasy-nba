<%-- 
    Document   : tabela_content
    Created on : May 22, 2017, 5:08:04 AM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="tabelePoredak">
    <h3 class="podnaslov">Istočna konferencija</h3>
    <table class="istok">
        <thead>
            <tr>
                <th>Poz</th>
                <th></th>
                <th>Tim</th>
                <th>W</th>
                <th>L</th>
                <th>PCT(%)</th>
                <th>GB</th>
                <th>CONF</th>
                <th>DIV</th>
                <th>HOME</th>
                <th>ROAD</th>
                <th>PPG</th>
                <th>OPP PPG</th>
                <th>DIFF</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="current_team" items="${tabela_istok}">
                <tr>
                    <td>${current_team.pozicija}</td>
                    <td><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank"><img src ="${current_team.timLogo}" style="width: 40px;" /></a></td>
                    <td class="levo"><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank">${current_team.timNaziv}</a></td>
                    <td>${current_team.brojPobeda}</td>
                    <td>${current_team.brojPoraza}</td>
                    <td>${current_team.procenatUspesnosti}</td>
                    <td>${current_team.zaostatak}</td>
                    <td>${current_team.skorKonferencija}</td>
                    <td>${current_team.skorDivizija}</td>
                    <td>${current_team.skorDomacin}</td>
                    <td>${current_team.skorGost}</td>
                    <td>${current_team.prosekDatihPoena}</td>
                    <td>${current_team.prosekPrimljenihPoena}</td>
                    <td>${current_team.prosekRazlika}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h3 class="podnaslov">Divizija Atlantik</h3>
    <table class="istok">
        <thead>
            <tr>
                <th>Poz</th>
                <th></th>
                <th>Tim</th>
                <th>W</th>
                <th>L</th>
                <th>PCT(%)</th>
                <th>GB</th>
                <th>CONF</th>
                <th>DIV</th>
                <th>HOME</th>
                <th>ROAD</th>
                <th>PPG</th>
                <th>OPP PPG</th>
                <th>DIFF</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="current_team" items="${tabela_atlantik}">
                <tr>
                    <td>${current_team.pozicija}</td>
                    <td><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank"><img src ="${current_team.timLogo}" style="width: 40px;" /></a></td>
                    <td class="levo"><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank">${current_team.timNaziv}</a></td>
                    <td>${current_team.brojPobeda}</td>
                    <td>${current_team.brojPoraza}</td>
                    <td>${current_team.procenatUspesnosti}</td>
                    <td>${current_team.zaostatak}</td>
                    <td>${current_team.skorKonferencija}</td>
                    <td>${current_team.skorDivizija}</td>
                    <td>${current_team.skorDomacin}</td>
                    <td>${current_team.skorGost}</td>
                    <td>${current_team.prosekDatihPoena}</td>
                    <td>${current_team.prosekPrimljenihPoena}</td>
                    <td>${current_team.prosekRazlika}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h3 class="podnaslov">Divizija Central</h3>
    <table class="istok">
        <thead>
            <tr>
                <th>Poz</th>
                <th></th>
                <th>Tim</th>
                <th>W</th>
                <th>L</th>
                <th>PCT(%)</th>
                <th>GB</th>
                <th>CONF</th>
                <th>DIV</th>
                <th>HOME</th>
                <th>ROAD</th>
                <th>PPG</th>
                <th>OPP PPG</th>
                <th>DIFF</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="current_team" items="${tabela_central}">
                <tr>
                    <td>${current_team.pozicija}</td>
                    <td><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank"><img src ="${current_team.timLogo}" style="width: 40px;" /></a></td>
                    <td class="levo"><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank">${current_team.timNaziv}</a></td>
                    <td>${current_team.brojPobeda}</td>
                    <td>${current_team.brojPoraza}</td>
                    <td>${current_team.procenatUspesnosti}</td>
                    <td>${current_team.zaostatak}</td>
                    <td>${current_team.skorKonferencija}</td>
                    <td>${current_team.skorDivizija}</td>
                    <td>${current_team.skorDomacin}</td>
                    <td>${current_team.skorGost}</td>
                    <td>${current_team.prosekDatihPoena}</td>
                    <td>${current_team.prosekPrimljenihPoena}</td>
                    <td>${current_team.prosekRazlika}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h3 class="podnaslov">Divizija Jugoistok</h3>
    <table class="istok">
        <thead>
            <tr>
                <th>Poz</th>
                <th></th>
                <th>Tim</th>
                <th>W</th>
                <th>L</th>
                <th>PCT(%)</th>
                <th>GB</th>
                <th>CONF</th>
                <th>DIV</th>
                <th>HOME</th>
                <th>ROAD</th>
                <th>PPG</th>
                <th>OPP PPG</th>
                <th>DIFF</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="current_team" items="${tabela_jugoistok}">
                <tr>
                    <td>${current_team.pozicija}</td>
                    <td><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank"><img src ="${current_team.timLogo}" style="width: 40px;" /></a></td>
                    <td class="levo"><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank">${current_team.timNaziv}</a></td>
                    <td>${current_team.brojPobeda}</td>
                    <td>${current_team.brojPoraza}</td>
                    <td>${current_team.procenatUspesnosti}</td>
                    <td>${current_team.zaostatak}</td>
                    <td>${current_team.skorKonferencija}</td>
                    <td>${current_team.skorDivizija}</td>
                    <td>${current_team.skorDomacin}</td>
                    <td>${current_team.skorGost}</td>
                    <td>${current_team.prosekDatihPoena}</td>
                    <td>${current_team.prosekPrimljenihPoena}</td>
                    <td>${current_team.prosekRazlika}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h3 class="podnaslov">Zapadna konferencija</h3>
    <table class="zapad">
        <thead>
            <tr>
                <th>Poz</th>
                <th></th>
                <th>Tim</th>
                <th>W</th>
                <th>L</th>
                <th>PCT(%)</th>
                <th>GB</th>
                <th>CONF</th>
                <th>DIV</th>
                <th>HOME</th>
                <th>ROAD</th>
                <th>PPG</th>
                <th>OPP PPG</th>
                <th>DIFF</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="current_team" items="${tabela_zapad}">
                <tr>
                    <td>${current_team.pozicija}</td>
                    <td><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank"><img src ="${current_team.timLogo}" style="width: 40px;" /></a></td>
                    <td class="levo"><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank">${current_team.timNaziv}</a></td>
                    <td>${current_team.brojPobeda}</td>
                    <td>${current_team.brojPoraza}</td>
                    <td>${current_team.procenatUspesnosti}</td>
                    <td>${current_team.zaostatak}</td>
                    <td>${current_team.skorKonferencija}</td>
                    <td>${current_team.skorDivizija}</td>
                    <td>${current_team.skorDomacin}</td>
                    <td>${current_team.skorGost}</td>
                    <td>${current_team.prosekDatihPoena}</td>
                    <td>${current_team.prosekPrimljenihPoena}</td>
                    <td>${current_team.prosekRazlika}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h3 class="podnaslov">Divizija Pacifik</h3>
    <table class="zapad">
        <thead>
            <tr>
                <th>Poz</th>
                <th></th>
                <th>Tim</th>
                <th>W</th>
                <th>L</th>
                <th>PCT(%)</th>
                <th>GB</th>
                <th>CONF</th>
                <th>DIV</th>
                <th>HOME</th>
                <th>ROAD</th>
                <th>PPG</th>
                <th>OPP PPG</th>
                <th>DIFF</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="current_team" items="${tabela_pacifik}">
                <tr>
                    <td>${current_team.pozicija}</td>
                    <td><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank"><img src ="${current_team.timLogo}" style="width: 40px;" /></a></td>
                    <td class="levo"><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank">${current_team.timNaziv}</a></td>
                    <td>${current_team.brojPobeda}</td>
                    <td>${current_team.brojPoraza}</td>
                    <td>${current_team.procenatUspesnosti}</td>
                    <td>${current_team.zaostatak}</td>
                    <td>${current_team.skorKonferencija}</td>
                    <td>${current_team.skorDivizija}</td>
                    <td>${current_team.skorDomacin}</td>
                    <td>${current_team.skorGost}</td>
                    <td>${current_team.prosekDatihPoena}</td>
                    <td>${current_team.prosekPrimljenihPoena}</td>
                    <td>${current_team.prosekRazlika}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h3 class="podnaslov">Divizija Jugozapad</h3>
    <table class="zapad">
        <thead>
            <tr>
                <th>Poz</th>
                <th></th>
                <th>Tim</th>
                <th>W</th>
                <th>L</th>
                <th>PCT(%)</th>
                <th>GB</th>
                <th>CONF</th>
                <th>DIV</th>
                <th>HOME</th>
                <th>ROAD</th>
                <th>PPG</th>
                <th>OPP PPG</th>
                <th>DIFF</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="current_team" items="${tabela_jugozapad}">
                <tr>
                    <td>${current_team.pozicija}</td>
                    <td><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank"><img src ="${current_team.timLogo}" style="width: 40px;" /></a></td>
                    <td class="levo"><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank">${current_team.timNaziv}</a></td>
                    <td>${current_team.brojPobeda}</td>
                    <td>${current_team.brojPoraza}</td>
                    <td>${current_team.procenatUspesnosti}</td>
                    <td>${current_team.zaostatak}</td>
                    <td>${current_team.skorKonferencija}</td>
                    <td>${current_team.skorDivizija}</td>
                    <td>${current_team.skorDomacin}</td>
                    <td>${current_team.skorGost}</td>
                    <td>${current_team.prosekDatihPoena}</td>
                    <td>${current_team.prosekPrimljenihPoena}</td>
                    <td>${current_team.prosekRazlika}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h3 class="podnaslov">Divizija Severozapad</h3>
    <table class="zapad">
        <thead>
            <tr>
                <th>Poz</th>
                <th></th>
                <th>Tim</th>
                <th>W</th>
                <th>L</th>
                <th>PCT(%)</th>
                <th>GB</th>
                <th>CONF</th>
                <th>DIV</th>
                <th>HOME</th>
                <th>ROAD</th>
                <th>PPG</th>
                <th>OPP PPG</th>
                <th>DIFF</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="current_team" items="${tabela_severozapad}">
                <tr>
                    <td>${current_team.pozicija}</td>
                    <td><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank"><img src ="${current_team.timLogo}" style="width: 40px;" /></a></td>
                    <td class="levo"><a href="./fantasy?action=tim&tim=${current_team.timId}" target="_blank">${current_team.timNaziv}</a></td>
                    <td>${current_team.brojPobeda}</td>
                    <td>${current_team.brojPoraza}</td>
                    <td>${current_team.procenatUspesnosti}</td>
                    <td>${current_team.zaostatak}</td>
                    <td>${current_team.skorKonferencija}</td>
                    <td>${current_team.skorDivizija}</td>
                    <td>${current_team.skorDomacin}</td>
                    <td>${current_team.skorGost}</td>
                    <td>${current_team.prosekDatihPoena}</td>
                    <td>${current_team.prosekPrimljenihPoena}</td>
                    <td>${current_team.prosekRazlika}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>