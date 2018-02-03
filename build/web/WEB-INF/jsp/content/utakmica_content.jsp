<%-- 
    Document   : utakmica_content
    Created on : Jun 28, 2017, 12:41:09 AM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="utakmicaOsnovniPodaci">
    <h4>Osnovni podaci:</h4>
    <table>
        <tr>
            <td class="pitanje">Datum odigravanja:</td>
            <td>${nba_utakmica.datum}</td>
        </tr>
        <tr>
            <td class="pitanje">Mesto odigravanja:</td>
            <td>${nba_utakmica.domacin.arena}, ${nba_utakmica.domacin.grad}</td>
        </tr>
        <tr>
            <td class="pitanje">Broj gledalaca:</td>
            <td>${nba_utakmica.brojGledalaca}</td>
        </tr>
        <tr>
            <td class="pitanje">Trajanje:</td>
            <td>${nba_utakmica.trajanje}</td>
        </tr>
    </table>
</div>
<div id="semafor">
    <table>
        <tr>
            <td class="centriraj"><a href="./fantasy?action=tim&tim=${nba_utakmica.domacin.timId}" class="timSemafor" target="_blank">${nba_utakmica.domacin.timNaziv}</a><br /><a href="./fantasy?action=tim&tim=${nba_utakmica.domacin.timId}" target="_blank"><img src="${nba_utakmica.domacin.timLogo}" /></a><br /><span class="rezultatSemafor">${nba_utakmica.poeniDomacin}</span></td>
            <td class="centriraj"><a href="./fantasy?action=tim&tim=${nba_utakmica.gost.timId}" class="timSemafor" target="_blank">${nba_utakmica.gost.timNaziv}</a><br /><a href="./fantasy?action=tim&tim=${nba_utakmica.domacin.timId}" target="_blank"><img src="${nba_utakmica.gost.timLogo}" /></a><br /><span class="rezultatSemafor">${nba_utakmica.poeniGost}</span></td>
        </tr>
    </table>
</div>
<div id="utakmice">
    <h4>Rezultat po četvrtinama:</h4>
    <table>
        <thead>
            <tr>
                <th></th>
                <th>1</th>
                <th>2</th>
                <th>3</th>
                <th>4</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${nba_utakmica.domacin.skraceniNaziv}</td>
                <td>${ucinak_domacin.poeniCetvrtina1}</td>
                <td>${ucinak_domacin.poeniCetvrtina2}</td>
                <td>${ucinak_domacin.poeniCetvrtina3}</td>
                <td>${ucinak_domacin.poeniCetvrtina4}</td>
                <td class="centriraj"><strong>${ucinak_domacin.poeni}</strong></td>
            </tr>
            <tr>
                <td>${nba_utakmica.gost.skraceniNaziv}</td>
                <td>${ucinak_gost.poeniCetvrtina1}</td>
                <td>${ucinak_gost.poeniCetvrtina2}</td>
                <td>${ucinak_gost.poeniCetvrtina3}</td>
                <td>${ucinak_gost.poeniCetvrtina4}</td>
                <td class="centriraj"><strong>${ucinak_gost.poeni}</strong></td>
            </tr>
        </tbody>
    </table>
</div>
<div id="timStatistika">
    <h4 style="margin-top: 20px;">${nba_utakmica.domacin.timNaziv}</h4>
    <table>
        <thead>
            <tr style="background-color: #${nba_utakmica.domacin.boja1}; color: #${nba_utakmica.domacin.boja2};">
                <th></th>
                <th>Igrač</th>
                <th>Poz</th>
                <th>MIN</th>
                <th>S</th>
                <th>FG</th>
                <th>FG%</th>
                <th>3P</th>
                <th>3P%</th>
                <th>2P</th>
                <th>2P%</th>
                <th>BA</th>
                <th>FT</th>
                <th>FT%</th>
                <th>ORB</th>
                <th>DRB</th>
                <th>TRB</th>
                <th>AST</th>
                <th>TOV</th>
                <th>STL</th>
                <th>BLK</th>
                <th>A/T</th>
                <th>PF</th>
                <th>+/-</th>
                <th>PTS</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="trenutni_ucinak" items="${ucinak_igraca_domacin}">
                <tr>
                    <td class="centriraj">${trenutni_ucinak.igrac.brojDres}</td>
                    <td><a href="./fantasy?action=igrac&igrac=${trenutni_ucinak.igrac.igracId}" target="_blank">${trenutni_ucinak.igrac.ime} ${trenutni_ucinak.igrac.prezime}</a></td>
                    <td class="centriraj">${trenutni_ucinak.igrac.pozicijaKrace}</td>
                    <td class="centriraj">${trenutni_ucinak.minuti}</td>
                    <td class="centriraj">${trenutni_ucinak.starter}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodakIzIgre}-${trenutni_ucinak.pokusajIzIgre}</td>
                    <td class="centriraj">${trenutni_ucinak.procenatIzIgre}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodak3p}-${trenutni_ucinak.pokusaj3p}</td>
                    <td class="centriraj">${trenutni_ucinak.procenat3p}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodak2p}-${trenutni_ucinak.pokusaj2p}</td>
                    <td class="centriraj">${trenutni_ucinak.procenat2p}</td>
                    <td class="centriraj">${trenutni_ucinak.blokiraniSutevi}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodak1p}-${trenutni_ucinak.pokusaj1p}</td>
                    <td class="centriraj">${trenutni_ucinak.procenat1p}</td>
                    <td class="centriraj">${trenutni_ucinak.skokoviNapad}</td>
                    <td class="centriraj">${trenutni_ucinak.skokoviOdbrana}</td>
                    <td class="centriraj">${trenutni_ucinak.skokoviUkupno}</td>
                    <td class="centriraj">${trenutni_ucinak.asistencije}</td>
                    <td class="centriraj">${trenutni_ucinak.izgubljeneLopte}</td>
                    <td class="centriraj">${trenutni_ucinak.ukradeneLopte}</td>
                    <td class="centriraj">${trenutni_ucinak.blokade}</td>
                    <td class="centriraj">${trenutni_ucinak.odnosAsistIzglop}</td>
                    <td class="centriraj">${trenutni_ucinak.licneGreske}</td>
                    <td class="centriraj">${trenutni_ucinak.plusMinus}</td>
                    <td class="centriraj">${trenutni_ucinak.poeni}</td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td class="centriraj">Tim</td>
                <td></td>
                <td class="centriraj">${ucinak_domacin.minuti}</td>
                <td></td>
                <td class="centriraj">${ucinak_domacin.pogodakIzIgre}-${ucinak_domacin.pokusajIzIgre}</td>
                <td class="centriraj">${ucinak_domacin.procenatIzIgre}</td>
                <td class="centriraj">${ucinak_domacin.pogodak3p}-${ucinak_domacin.pokusaj3p}</td>
                <td class="centriraj">${ucinak_domacin.procenat3p}</td>
                <td class="centriraj">${ucinak_domacin.pogodak2p}-${ucinak_domacin.pokusaj2p}</td>
                <td class="centriraj">${ucinak_domacin.procenat2p}</td>
                <td class="centriraj">${ucinak_domacin.blokiraniSutevi}</td>
                <td class="centriraj">${ucinak_domacin.pogodak1p}-${ucinak_domacin.pokusaj1p}</td>
                <td class="centriraj">${ucinak_domacin.procenat1p}</td>
                <td class="centriraj">${ucinak_domacin.skokoviNapad}</td>
                <td class="centriraj">${ucinak_domacin.skokoviOdbrana}</td>
                <td class="centriraj">${ucinak_domacin.skokoviUkupno}</td>
                <td class="centriraj">${ucinak_domacin.asistencije}</td>
                <td class="centriraj">${ucinak_domacin.izgubljeneLopte}</td>
                <td class="centriraj">${ucinak_domacin.ukradeneLopte}</td>
                <td class="centriraj">${ucinak_domacin.blokade}</td>
                <td class="centriraj">${ucinak_domacin.odnosAsistIzglop}</td>
                <td class="centriraj">${ucinak_domacin.licneGreske}</td>
                <td class="centriraj">--</td>
                <td class="centriraj">${ucinak_domacin.poeni}</td>
            </tr>
        </tbody>
    </table>
    <h4 style="margin-top: 20px;">${nba_utakmica.gost.timNaziv}</h4>
    <table>
        <thead>
            <tr style="background-color: #${nba_utakmica.gost.boja2}; color: #${nba_utakmica.gost.boja1};">
                <th></th>
                <th>Igrač</th>
                <th>Poz</th>
                <th>MIN</th>
                <th>S</th>
                <th>FG</th>
                <th>FG%</th>
                <th>3P</th>
                <th>3P%</th>
                <th>2P</th>
                <th>2P%</th>
                <th>BA</th>
                <th>FT</th>
                <th>FT%</th>
                <th>ORB</th>
                <th>DRB</th>
                <th>TRB</th>
                <th>AST</th>
                <th>TOV</th>
                <th>STL</th>
                <th>BLK</th>
                <th>A/T</th>
                <th>PF</th>
                <th>+/-</th>
                <th>PTS</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="trenutni_ucinak" items="${ucinak_igraca_gost}">
                <tr>
                    <td class="centriraj">${trenutni_ucinak.igrac.brojDres}</td>
                    <td><a href="./fantasy?action=igrac&igrac=${trenutni_ucinak.igrac.igracId}" target="_blank">${trenutni_ucinak.igrac.ime} ${trenutni_ucinak.igrac.prezime}</a></td>
                    <td class="centriraj">${trenutni_ucinak.igrac.pozicijaKrace}</td>
                    <td class="centriraj">${trenutni_ucinak.minuti}</td>
                    <td class="centriraj">${trenutni_ucinak.starter}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodakIzIgre}-${trenutni_ucinak.pokusajIzIgre}</td>
                    <td class="centriraj">${trenutni_ucinak.procenatIzIgre}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodak3p}-${trenutni_ucinak.pokusaj3p}</td>
                    <td class="centriraj">${trenutni_ucinak.procenat3p}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodak2p}-${trenutni_ucinak.pokusaj2p}</td>
                    <td class="centriraj">${trenutni_ucinak.procenat2p}</td>
                    <td class="centriraj">${trenutni_ucinak.blokiraniSutevi}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodak1p}-${trenutni_ucinak.pokusaj1p}</td>
                    <td class="centriraj">${trenutni_ucinak.procenat1p}</td>
                    <td class="centriraj">${trenutni_ucinak.skokoviNapad}</td>
                    <td class="centriraj">${trenutni_ucinak.skokoviOdbrana}</td>
                    <td class="centriraj">${trenutni_ucinak.skokoviUkupno}</td>
                    <td class="centriraj">${trenutni_ucinak.asistencije}</td>
                    <td class="centriraj">${trenutni_ucinak.izgubljeneLopte}</td>
                    <td class="centriraj">${trenutni_ucinak.ukradeneLopte}</td>
                    <td class="centriraj">${trenutni_ucinak.blokade}</td>
                    <td class="centriraj">${trenutni_ucinak.odnosAsistIzglop}</td>
                    <td class="centriraj">${trenutni_ucinak.licneGreske}</td>
                    <td class="centriraj">${trenutni_ucinak.plusMinus}</td>
                    <td class="centriraj">${trenutni_ucinak.poeni}</td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td class="centriraj">Tim</td>
                <td></td>
                <td class="centriraj">${ucinak_gost.minuti}</td>
                <td></td>
                <td class="centriraj">${ucinak_gost.pogodakIzIgre}-${ucinak_gost.pokusajIzIgre}</td>
                <td class="centriraj">${ucinak_gost.procenatIzIgre}</td>
                <td class="centriraj">${ucinak_gost.pogodak3p}-${ucinak_gost.pokusaj3p}</td>
                <td class="centriraj">${ucinak_gost.procenat3p}</td>
                <td class="centriraj">${ucinak_gost.pogodak2p}-${ucinak_gost.pokusaj2p}</td>
                <td class="centriraj">${ucinak_gost.procenat2p}</td>
                <td class="centriraj">${ucinak_gost.blokiraniSutevi}</td>
                <td class="centriraj">${ucinak_gost.pogodak1p}-${ucinak_gost.pokusaj1p}</td>
                <td class="centriraj">${ucinak_gost.procenat1p}</td>
                <td class="centriraj">${ucinak_gost.skokoviNapad}</td>
                <td class="centriraj">${ucinak_gost.skokoviOdbrana}</td>
                <td class="centriraj">${ucinak_gost.skokoviUkupno}</td>
                <td class="centriraj">${ucinak_gost.asistencije}</td>
                <td class="centriraj">${ucinak_gost.izgubljeneLopte}</td>
                <td class="centriraj">${ucinak_gost.ukradeneLopte}</td>
                <td class="centriraj">${ucinak_gost.blokade}</td>
                <td class="centriraj">${ucinak_gost.odnosAsistIzglop}</td>
                <td class="centriraj">${ucinak_gost.licneGreske}</td>
                <td class="centriraj">--</td>
                <td class="centriraj">${ucinak_gost.poeni}</td>
            </tr>
        </tbody>
    </table>
</div>
<h4 style="margin-top: 30px;">Grafički prikaz</h4>
<div id="grafickiPrikaz">
    <div id="skokovi" class="grafici"></div>
    <div id="asistencije" class="grafici"></div>
    <div id="ukradeneLopte" class="grafici"></div>
    <div id="blokade" class="grafici"></div>
    <div id="sutGrafika"></div>
</div>
<div id="utakmice">
    <h4>Dodatna statistika:</h4>
    <table>
        <thead>
            <tr>
                <th></th>
                <th>${nba_utakmica.domacin.skraceniNaziv}</th>
                <th>${nba_utakmica.gost.skraceniNaziv}</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td style="text-align: right;">Poeni iz kontre:</td>
                <td class="centriraj">${ucinak_domacin.poeniIzKontre}</td>
                <td class="centriraj">${ucinak_gost.poeniIzKontre}</td>
            </tr>
            <tr>
                <td style="text-align: right;">Poeni iz reketa:</td>
                <td class="centriraj">${ucinak_domacin.poeniIzReketa}</td>
                <td class="centriraj">${ucinak_gost.poeniIzReketa}</td>
            </tr>
            <tr>
                <td style="text-align: right;">Poeni iz izgubljenih lopti:</td>
                <td class="centriraj">${ucinak_domacin.poeniIzIzgubljenihLopti}</td>
                <td class="centriraj">${ucinak_gost.poeniIzIzgubljenihLopti}</td>
            </tr>
        </tbody>
    </table>
</div>