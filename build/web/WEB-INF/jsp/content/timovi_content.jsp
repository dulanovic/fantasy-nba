<%-- 
    Document   : timovi_content
    Created on : Jun 27, 2017, 3:46:43 AM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="naslov">TIMOVI</h1>
<c:forEach var="trenutna_konferencija" items="${nba_konferencije}">
    <h2 class="podnaslov" style="margin-top: 15px;">${trenutna_konferencija.konferencijaNaziv}</h2>
    <c:forEach var="trenutna_divizija" items="${trenutna_konferencija.divizijaList}">
        <div class="tabelaKontejner">
            <h3 style="color: #006BB6; margin-top: 15px;" class="podnaslov">${trenutna_divizija.divizijaNaziv}</h3>
            <table class="tabelaNBA">
                <tr>
                    <c:forEach var="trenutni_tim" items="${trenutna_divizija.timList}">
                        <td class="centriraj"><a href="./fantasy?action=tim&tim=${trenutni_tim.timId}" target="_blank"><img src="${trenutni_tim.timLogo}" /><br />${trenutni_tim.timNaziv}</a></td>
                            </c:forEach>
                </tr>
            </table>
        </div>
    </c:forEach>
</c:forEach>