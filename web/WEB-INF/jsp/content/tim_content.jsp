<%-- 
    Document   : tim_content
    Created on : Jun 27, 2017, 3:41:47 AM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="naslov">${nba_tim.timNaziv}</h1>
<img src="${nba_tim.timLogo}" class="logoVeliki" />
<div id="podaciTim">
    <h4>Podaci o franšizi:</h4>
    <table>
        <tr>
            <td class="pitanje">Grad:</td>
            <td>${nba_tim.grad}</td>
        </tr>
        <tr>
            <td class="pitanje">Arena:</td>
            <td>${nba_tim.arena} (${nba_tim.kapacitet} mesta)</td>
        </tr>
        <tr>
            <td class="pitanje">Godina osnivanja:</td>
            <td>${nba_tim.godinaOsnivanja}</td>
        </tr>
        <tr>
            <td class="pitanje">Divizija:</td>
            <td>${nba_tim.divizija.divizijaNaziv}</td>
        </tr>
        <tr>
            <td class="pitanje">Konferencija:</td>
            <td>${nba_tim.divizija.konferencija.konferencijaNaziv}</td>
        </tr>
        <tr>
            <td class="pitanje">Trenutni skor:</td>
            <td>${nba_tim.trenutniSkor}</td>
        </tr>
        <tr>
            <td class="pitanje">Trener:</td>
            <td>${nba_tim.trener}</td>
        </tr>
    </table>
</div>
<div id="timRoster">
    <h4>Sastav tima:</h4>
    <table>
        <thead>
            <tr>
                <th>Broj</th>
                <th>Ime i prezime</th>
                <th>Pozicija</th>
                <th>Visina</th>
                <th>Težina</th>
                <th>Iskustvo</th>
                <th>Koledž</th>
                <th>Datum rođenja</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="trenutni_igrac" items="${nba_tim.igracList}">
                <tr>
                    <td class="centriraj">${trenutni_igrac.brojDres}</td>
                    <td><a href="./fantasy?action=igrac&igrac=${trenutni_igrac.igracId}" target="_blank">${trenutni_igrac.ime} ${trenutni_igrac.prezime}</a></td>
                    <td class="centriraj">${trenutni_igrac.pozicija}</td>
                    <td class="centriraj">${trenutni_igrac.visina}</td>
                    <td class="centriraj">${trenutni_igrac.tezina}</td>
                    <td class="centriraj">${trenutni_igrac.iskustvo}</td>
                    <td class="centriraj">${trenutni_igrac.koledz}</td>
                    <td class="centriraj">${trenutni_igrac.getDatumRodjenjaString()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div id="utakmice">
    <h4>Odigrane utakmice:</h4>
    <table>
        <thead>
            <tr>
                <th>Datum</th>
                <th colspan="2">Domaćin</th>
                <th>Rezultat</th>
                <th colspan="2">Gost</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="trenutna_utakmica" items="${nba_tim.listaSvihUtakmica}">
                <tr>
                    <td class="centriraj">${trenutna_utakmica.datum}</td>
                    <td style="text-align: right;"><a href="./fantasy?action=tim&tim=${trenutna_utakmica.domacin.timId}" target="_blank">${trenutna_utakmica.domacin.timNaziv}</a></td>
                    <td><a href="./fantasy?action=tim&tim=${trenutna_utakmica.domacin.timId}" target="_blank"><img src="${trenutna_utakmica.domacin.timLogo}" /></a></td>
                    <td class="centriraj"><a href="./fantasy?action=utakmica&utakmica=${trenutna_utakmica.utakmicaId}" target="_blank">${trenutna_utakmica.poeniDomacin}-${trenutna_utakmica.poeniGost}</a></td>
                    <td><a href="./fantasy?action=tim&tim=${trenutna_utakmica.gost.timId}" target="_blank"><img src="${trenutna_utakmica.gost.timLogo}" /></a></td>
                    <td style="text-align: left;"><a href="./fantasy?action=tim&tim=${trenutna_utakmica.gost.timId}" target="_blank">${trenutna_utakmica.gost.timNaziv}</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div id="timStatistika">
    <h4>Statistika:</h4>
    <table>
        <thead>
            <tr style="background-color: #${nba_tim.boja1}; color: #${nba_tim.boja2};">
                <th>Br.ut.</th>
                <th>PTS</th>
                <th>FG</th>
                <th>FGA</th>
                <th>FG%</th>
                <th>3P</th>
                <th>3PA</th>
                <th>3P%</th>
                <th>2P</th>
                <th>2PA</th>
                <th>2P%</th>
                <th>BA</th>
                <th>FT</th>
                <th>FTA</th>
                <th>FT%</th>
                <th>ORB</th>
                <th>DRB</th>
                <th>TRB</th>
                <th>AST</th>
                <th>TOV</th>
                <th>STL</th>
                <th>BLK</th>
                <th>AST/TOV</th>
                <th>PF</th>
                <th>FBKP</th>
                <th>PPNT</th>
                <th>OPPP</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="trenutni_ucinak" items="${nba_tim.timUcinakList}">
                <tr>
                    <td></td>
                    <td class="centriraj">${trenutni_ucinak.poeni}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodakIzIgre}</td>
                    <td class="centriraj">${trenutni_ucinak.pokusajIzIgre}</td>
                    <td class="centriraj">${trenutni_ucinak.procenatIzIgre}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodak3p}</td>
                    <td class="centriraj">${trenutni_ucinak.pokusaj3p}</td>
                    <td class="centriraj">${trenutni_ucinak.procenat3p}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodak2p}</td>
                    <td class="centriraj">${trenutni_ucinak.pokusaj2p}</td>
                    <td class="centriraj">${trenutni_ucinak.procenat2p}</td>
                    <td class="centriraj">${trenutni_ucinak.blokiraniSutevi}</td>
                    <td class="centriraj">${trenutni_ucinak.pogodak1p}</td>
                    <td class="centriraj">${trenutni_ucinak.pokusaj1p}</td>
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
                    <td class="centriraj">${trenutni_ucinak.poeniIzKontre}</td>
                    <td class="centriraj">${trenutni_ucinak.poeniIzReketa}</td>
                    <td class="centriraj">${trenutni_ucinak.poeniIzIzgubljenihLopti}</td></tr>
                </c:forEach>
            <tr>
                <td class="centriraj">${nba_tim_statistika.ukupnoUtakmica}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPoeni}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPogodakIzIgre}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPokusajIzIgre}</td>
                <td class="centriraj">${nba_tim_statistika.prosekProcenatIzIgre}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPogodak3p}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPokusaj3p}</td>
                <td class="centriraj">${nba_tim_statistika.prosekProcenat3p}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPogodak2p}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPokusaj2p}</td>
                <td class="centriraj">${nba_tim_statistika.prosekProcenat2p}</td>
                <td class="centriraj">${nba_tim_statistika.prosekBlokiraniSutevi}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPogodak1p}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPokusaj1p}</td>
                <td class="centriraj">${nba_tim_statistika.prosekProcenat1p}</td>
                <td class="centriraj">${nba_tim_statistika.prosekSkokoviNapad}</td>
                <td class="centriraj">${nba_tim_statistika.prosekSkokoviOdbrana}</td>
                <td class="centriraj">${nba_tim_statistika.prosekSkokoviUkupno}</td>
                <td class="centriraj">${nba_tim_statistika.prosekAsistencije}</td>
                <td class="centriraj">${nba_tim_statistika.prosekIzgubljeneLopte}</td>
                <td class="centriraj">${nba_tim_statistika.prosekUkradeneLopte}</td>
                <td class="centriraj">${nba_tim_statistika.prosekBlokade}</td>
                <td class="centriraj">---</td>
                <td class="centriraj">${nba_tim_statistika.prosekLicneGreske}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPoeniIzKontre}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPoeniIzReketa}</td>
                <td class="centriraj">${nba_tim_statistika.prosekPoeniIzIzgubljenihLopti}</td>
            </tr>
        </tbody>
    </table>
</div>
<div id="grafici">
    <h4>Forma:</h4>
    <div id="formaGrafik"></div>
</div>