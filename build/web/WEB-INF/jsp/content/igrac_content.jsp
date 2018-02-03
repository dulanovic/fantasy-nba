<%-- 
    Document   : igrac_content
    Created on : Jun 27, 2017, 1:07:18 AM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 style="text-align: center;">${nba_igrac}</h1>
<div id="podaciIgrac">
    <h4 style="text-align: center;">Podaci o igraču:</h4>
    <div id="podaciIgracLevo">
        <img src="${nba_igrac.igracSlika}" />
    </div>
    <div id="podaciIgracDesno">
        <table id="podaciIgracTabela">
            <tr>
                <td class="pitanje">Ime i prezime:</td>
                <td>${nba_igrac.ime} ${nba_igrac.prezime}</td>
            </tr>
            <tr>
                <td class="pitanje">Visina:</td>
                <td>${nba_igrac.visina}cm</td>
            </tr>
            <tr>
                <td class="pitanje">Težina:</td>
                <td>${nba_igrac.tezina}kg</td>
            </tr>
            <tr>
                <td class="pitanje">Pozicija:</td>
                <td>${nba_igrac.pozicija}</td>
            </tr>
            <tr>
                <td class="pitanje">Broj:</td>
                <td>${nba_igrac.brojDres}</td>
            </tr>
            <tr>
                <td class="pitanje">Iskustvo:</td>
                <td>${nba_igrac.iskustvo}</td>
            </tr>
            <tr>
                <td class="pitanje">Koledž:</td>
                <td>${nba_igrac.koledz}</td>
            </tr>
            <tr>
                <td class="pitanje">Datum rođenja:</td>
                <td>${nba_igrac.getDatumRodjenjaString()}</td>
            </tr>
            <tr>
                <td class="pitanje">Mesto rođenja:</td>
                <td>${nba_igrac.mestoRodjenja}</td>
            </tr>
            <tr>
                <td class="pitanje">Draft:</td>
                <td>${nba_igrac.draftPodaci}</td>
            </tr>
            <tr>
                <td class="pitanje">Tim:</td>
                <td><a href="./fantasy?action=tim&tim=${nba_igrac.tim.timId}" target="_blank">${nba_igrac.tim.timNaziv}</a></td>
            </tr>
        </table>
    </div>
</div>
<div id="timStatistika">
    <h4>Statistika prethodne sezone i projekcija za narednu:</h4>
    <table id="pomocnaStatistika">
        <thead>
            <tr style="background-color: #${nba_igrac.tim.boja1}; color: #${nba_igrac.tim.boja2};">
                <th>Sezona</th>
                <th>MPG</th>
                <th>FG%</th>
                <th>FT%</th>
                <th>3PM</th>
                <th>REB</th>
                <th>AST</th>
                <th>AST/TO</th>
                <th>STL</th>
                <th>BLK</th>
                <th>TO</th>
                <th>PTS</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="centriraj">2015-16</td>
                <td class="centriraj">${nba_igrac.minutazaPrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.sutIzIgrePrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.slobodnaBacanjaPrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.pogodak3pPrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.skokoviPrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.asistencijePrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.asistIzglopPrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.ukradeneLoptePrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.blokadePrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.izgubljeneLoptePrethodnaSezona}</td>
                <td class="centriraj">${nba_igrac.poeniPrethodnaSezona}</td>
            </tr>
            <tr>
                <td>2016-17</td>
                <td class="centriraj">${nba_igrac.minutazaProjekcija}</td>
                <td class="centriraj">${nba_igrac.sutIzIgreProjekcija}</td>
                <td class="centriraj">${nba_igrac.slobodnaBacanjaProjekcija}</td>
                <td class="centriraj">${nba_igrac.pogodak3pProjekcija}</td>
                <td class="centriraj">${nba_igrac.skokoviProjekcija}</td>
                <td class="centriraj">${nba_igrac.asistencijeProjekcija}</td>
                <td class="centriraj">${nba_igrac.asistIzglopProjekcija}</td>
                <td class="centriraj">${nba_igrac.ukradeneLopteProjekcija}</td>
                <td class="centriraj">${nba_igrac.blokadeProjekcija}</td>
                <td class="centriraj">${nba_igrac.izgubljeneLopteProjekcija}</td>
                <td class="centriraj">${nba_igrac.poeniProjekcija}</td>
            </tr>
        </tbody>
    </table>
</div>
<c:if test="${!nba_igrac.igracUcinakList.isEmpty()}">
    <div id="timStatistika">
        <h4>Odigrane utakmice:</h4>
        <table>
            <thead>
                <tr style="background-color: #${nba_igrac.tim.boja2}; color: #${nba_igrac.tim.boja1};">
                    <th>Br.ut.</th>
                    <th>S</th>
                    <th>MP</th>
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
                    <th>+/-</th>
                    <th>PTS</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="trenutni_ucinak" items="${nba_igrac.igracUcinakList}">
                    <tr>
                        <td class="centriraj"></td>
                        <td class="centriraj">${trenutni_ucinak.starter}</td>
                        <td class="centriraj">${trenutni_ucinak.minuti}</td>
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
                        <td class="centriraj">${trenutni_ucinak.plusMinus}</td>
                        <td class="centriraj">${trenutni_ucinak.poeni}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td class="centriraj">${nba_igrac_statistika.ukupnoIgrao}</td>
                    <td class="centriraj">${nba_igrac_statistika.ukupnoStartovao}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekMinuti}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekPogodakIzIgre}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekPokusajIzIgre}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekProcenatIzIgre}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekPogodak3p}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekPokusaj3p}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekProcenat3p}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekPogodak2p}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekPokusaj2p}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekProcenat2p}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekBlokiraniSutevi}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekPogodak1p}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekPokusaj1p}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekProcenat1p}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekSkokoviNapad}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekSkokoviOdbrana}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekSkokoviUkupno}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekAsistencije}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekIzgubljeneLopte}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekUkradeneLopte}</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekBlokade}</td>
                    <td class="centriraj">---</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekLicneGreske}</td>
                    <td class="centriraj">---</td>
                    <td class="centriraj">${nba_igrac_statistika.prosekPoeni}</td>
                </tr>
            </tbody>
        </table>
    </div>
</c:if>
<div id="grafici">
    <h4>Grafički prikaz</h4>
    <div id="sutIzIgreGrafik"></div>
    <div id="slobodnaBacanjaGrafik"></div>
    <div id="pogodak3pGrafik"></div>
    <div id="skokoviGrafik"></div>
    <div id="asistencijeGrafik"></div>
    <div id="ukradeneLopteGrafik"></div>
    <div id="blokadeGrafik"></div>
    <div id="poeniGrafik"></div>    
</div>