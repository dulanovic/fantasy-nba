<%-- 
    Document   : draft
    Created on : Jun 5, 2017, 4:20:20 AM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Draft - ${fantasy_tim.liga.ligaNaziv} (${fantasy_tim.timNaziv})</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style.css" />

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.css">
        <link href="https://fonts.googleapis.com/css?family=Merriweather" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui/external/jquery/jquery.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/ajax.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/validacija.js"></script>

    </head>
    <body>
        <div id="trenutnoNaRedu">
            <h3 class="hdraft" id="trenutnoBira">Trenutno bira: </h3>
            <h4 class="hdraft" id="sledeciBira">Sledeći bira: </h4>
        </div>
        <input type="hidden" id="igrac_id" value="">
        <input type="hidden" id="naziv_tima" value="${fantasy_tim.timNaziv}">
        <input type="hidden" id="liga_id" value="${fantasy_tim.liga.ligaId}">
        <input type="hidden" id="poslednja_runda" value="0">
        <input type="hidden" id="pozicija_trenutnog" class="pozicijaIzabranogIgraca" value="">
        <div id="vreme" style="display: none;"></div>
        <div id="kontrolniPanel">
            <textarea id="poruke" cols="90" rows="15" readonly></textarea>
            <div id="dugmiciPanel">
                <button id="pokreniDraft" class="ui-button ui-widget ui-corner-all" style="display: none;" onclick="pokreniDraft()">Počni draft</button>
                <button id="potvrdiDraft" class="ui-button ui-widget ui-corner-all" style="display: none;" onclick="potvrdiDraft()">Potvrdi draft</button>
                <button id="ponistiDraft" class="ui-button ui-widget ui-corner-all" style="display: none;" onclick="ponistiDraft()">Poništi draft</button>
            </div>
        </div>
        <div id="izbor">
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
            <input type="text" id="igracPretraga" value="" size="20" onkeyup="vratiIgracePretraga('draft')">
            <button id="izborIgrac" class="ui-button ui-widget ui-corner-all" style="display: none;" onclick="posaljiIzbor()">Izaberi igrača</button>
            <div id="tabelaIzbor"></div>
        </div>
        <h4 style="text-align: center; margin-top: 40px;">Izabrani igrači:</h4>
        <div id="izabraniIgraci"></div>
    </body>
    <script type="text/javascript">
        var websocket = new WebSocket("ws://localhost:8080/FantasyNBA/fantasy/draft/${fantasy_tim.liga.ligaId}/${fantasy_tim.timNaziv}");
        websocket.onmessage = function processMessage(message) {
            if (message != null) {
                var poruka = JSON.parse(message.data);
                switch (poruka.tip_poruke) {
                    case "pokreni_draft":
                    {
                        document.getElementById("pokreniDraft").style.display = "inline-block";
                        break;
                    }
                    case "zaustavi_draft":
                    {
                        document.getElementById("pokreniDraft").style.display = "none";
                        break;
                    }
                    case "onemoguci_pristup":
                    {
                        alert("Već ste uključeni u ovu draft sesiju na drugom web-browser-u ili računaru!");
                        location.href = "http://localhost:8080/FantasyNBA/fantasy?action=fantasyLiga&liga=${fantasy_tim.liga.ligaId}";
                        break;
                    }
                    case "omoguci_izbor":
                    {
                        document.getElementById("izborIgrac").style.display = "inline-block";
                        document.getElementById("izborIgrac").disabled = true;
                        document.getElementById("pozicija_trenutnog").value = "";
                        if (poruka.napomena == "poslednji_izbor") {
                            alert("Poslednji izbor!!!");
                            document.getElementById("poslednja_runda").value = "1";
                            validirajPozicije();
                        }
                        vratiIgraceKriterijum("draft");
                        break;
                    }
                    case "izvrsen_izbor":
                    {
                        document.getElementById("poruke").innerHTML += "Tim ---> " + poruka.tim_izabrao + " je izabrao igrača ---> " + poruka.izabran_igrac + "\n";
                        if (poruka.tim_izabrao == document.getElementById("naziv_tima").value) {
                            var podaci = document.getElementById("podaciIgrac_" + poruka.igrac_id).innerHTML;
                            var divIspis = document.getElementById("izabraniIgraci");
                            var pozicijaIzabranogIgraca = document.createElement("input");
                            pozicijaIzabranogIgraca.setAttribute("type", "hidden");
                            pozicijaIzabranogIgraca.setAttribute("class", "pozicijaIzabranogIgraca");
                            pozicijaIzabranogIgraca.setAttribute("value", poruka.pozicija);
                            divIspis.innerHTML += "<div class=\"prikazIzabranih\">" + podaci + "</div>";
                            divIspis.appendChild(pozicijaIzabranogIgraca);
                        }
                        vratiIgraceKriterijum("draft");
                        break;
                    }
                    case "obavestenje":
                    {
                        var log = document.getElementById("poruke");
                        log.innerHTML += poruka.tekst_poruke + "\n";
                        log.scrollTop = log.scrollHeight;
                        break;
                    }
                    case "azuriraj_log":
                    {
                        var log = document.getElementById("poruke");
                        log.innerHTML = poruka.log;
                        log.scrollTop = log.scrollHeight;
                        if ("dosad_izabrani_igraci" in poruka) {
                            var izabraniIgraciNiz = JSON.parse(poruka.dosad_izabrani_igraci);
                            var divIspis = document.getElementById("izabraniIgraci");
                            for (var i = 0; i < izabraniIgraciNiz.length; i++) {
                                divIspis.innerHTML += "<div class=\"prikazIzabranih\"><img src=\"" + izabraniIgraciNiz[i].igrac_slika + "\" style=\"width: 200px;\" /><br /><strong>#" + izabraniIgraciNiz[i].igrac_broj + " - " + izabraniIgraciNiz[i].igrac_ime + " " + izabraniIgraciNiz[i].igrac_prezime + "</strong></div>";
                                var pozicijaIzabranogIgraca = document.createElement("input");
                                pozicijaIzabranogIgraca.setAttribute("type", "hidden");
                                pozicijaIzabranogIgraca.setAttribute("class", "pozicijaIzabranogIgraca");
                                pozicijaIzabranogIgraca.setAttribute("value", izabraniIgraciNiz[i].igrac_pozicija);
                                divIspis.appendChild(pozicijaIzabranogIgraca);
                            }
                        }
                        break;
                    }
                    case "azuriranje_stanja":
                    {
                        document.getElementById("trenutnoBira").innerHTML = "Trenutno bira: <strong>" + poruka.trenutno_bira + "</strong>";
                        document.getElementById("sledeciBira").innerHTML = "Sledeći bira: " + poruka.sledeci_bira;
                        console.log("Trenutni ---> " + poruka.trenutno_bira + "\nSledeci ---> " + poruka.sledeci_bira);
                        if (poruka.trenutno_bira == document.getElementById("naziv_tima").value) {
                            document.getElementById("trenutnoNaRedu").style.backgroundColor = "#DD0000";
                            alert("Vaš tim je na redu!");
                            vratiIgraceKriterijum("draft");
                            vreme();
                        }
                        break;
                    }
                    case "kraj_drafta":
                    {
                        document.getElementById("potvrdiDraft").style.display = "inline-block";
                        document.getElementById("ponistiDraft").style.display = "inline-block";
                        break;
                    }
                    case "napusti_draft":
                    {
                        location.href = "http://localhost:8080/FantasyNBA/fantasy?action=fantasyLiga&liga=${fantasy_tim.liga.ligaId}";
                        break;
                    }
                    case "idi_na_stranicu":
                    {
                        location.href = "http://localhost:8080/FantasyNBA/fantasy?action=fantasyTim&tim=" + poruka.tim_id + "";
                        break;
                    }
                }
            }
        };

        var timer;
        function vreme() {
            var vreme = 60;
            timer = setInterval(function () {
                document.getElementById("vreme").style.display = "block";
                document.getElementById("vreme").innerHTML = vreme;
                if (--vreme < 0) {
                    zaustaviVreme();
                    var poruka = "{\"tip_poruke\": \"isteklo_vreme\"}";
                    websocket.send(poruka);
                }
            }, 1000);
        }

        function zaustaviVreme() {
            clearInterval(timer);
            document.getElementById("vreme").style.display = "none";
            document.getElementById("trenutnoNaRedu").style.backgroundColor = "#072840";
        }

        function posaljiIzbor() {
            var igrac_id = document.getElementById("igrac_id").value;
            if (igrac_id == "") {
                return;
            }
            var naziv_tima = document.getElementById("naziv_tima").value;
            var liga_id = document.getElementById("liga_id").value;
            var poruka = "{\"tip_poruke\": \"izabran_igrac\", \"igrac_id\": \"" + igrac_id + "\", \"naziv_tima\": \"" + naziv_tima + "\", \"liga_id\": \"" + liga_id + "\"}";
            websocket.send(poruka);
            zaustaviVreme();
            document.getElementById("izborIgrac").style.display = "none";
            document.getElementById("trenutnoNaRedu").style.backgroundColor = "#072840";
        }

        function pokreniDraft() {
            var poruka = "{\"tip_poruke\": \"pokreni_draft\"}";
            websocket.send(poruka);
            document.getElementById("pokreniDraft").style.display = "none";
        }

        function postaviPoziciju(id) {
            var pozicijaTrenutnog = document.getElementById("pozicija_trenutnog");
            var pozicija = document.getElementById("pozicija" + id).innerHTML;
            pozicija = pozicija.substr(27);
            pozicijaTrenutnog.value = pozicija;
        }

        function prebrojPozicije() {
            var pozicijeNiz = document.getElementsByClassName("pozicijaIzabranogIgraca");
            var brojPlejmejkera = 0;
            var brojBekova = 0;
            var brojKrila = 0;
            var brojKrilnihCentara = 0;
            var brojCentara = 0;

            for (var i = 0; i < pozicijeNiz.length; i++) {
                var pozicija = pozicijeNiz[i].value;
                switch (pozicija) {
                    case "PG":
                        brojPlejmejkera++;
                        break;
                    case "SG":
                        brojBekova++;
                        break;
                    case "SF":
                        brojKrila++;
                        break;
                    case "PF":
                        brojKrilnihCentara++;
                        break;
                    case "C":
                        brojCentara++;
                        break;
                }
            }
            return [brojPlejmejkera, brojBekova, brojKrila, brojKrilnihCentara, brojCentara];
        }

        function validirajPozicije() {
            var brojNaPozicijiNiz = prebrojPozicije();
            if (brojNaPozicijiNiz[0] < 1) {
                alert("Nemate nijednog plejmejkera u rosteru! Da bi Vaš roster bio validan, morate imati bar po 1 igrača na svakoj poziciji, zato u ovoj poslednoj rundi drafta izaberite plejmejkera.");
                return false;
            }
            if (brojNaPozicijiNiz[1] < 1) {
                alert("Nemate nijednog beka u rosteru! Da bi Vaš roster bio validan, morate imati bar po 1 igrača na svakoj poziciji, zato u ovoj poslednoj rundi drafta izaberite beka.");
                return false;
            }
            if (brojNaPozicijiNiz[2] < 1) {
                alert("Nemate nijedno krilo u rosteru! Da bi Vaš roster bio validan, morate imati bar po 1 igrača na svakoj poziciji, zato u ovoj poslednoj rundi drafta izaberite krilo.");
                return false;
            }
            if (brojNaPozicijiNiz[3] < 1) {
                alert("Nemate nijednog krilnog centra u rosteru! Da bi Vaš roster bio validan, morate imati bar po 1 igrača na svakoj poziciji, zato u ovoj poslednoj rundi drafta izaberite krilnog centra.");
                return false;
            }
            if (brojNaPozicijiNiz[4] < 1) {
                alert("Nemate nijednog centra u rosteru! Da bi Vaš roster bio validan, morate imati bar po 1 igrača na svakoj poziciji, zato u ovoj poslednoj rundi drafta izaberite centra.");
                return false;
            }
            return true;
        }

        function potvrdiDraft() {
            var poruka = "{\"tip_poruke\": \"potvrdi_draft\"}";
            websocket.send(poruka);
        }

        function ponistiDraft() {
            var poruka = "{\"tip_poruke\": \"ponisti_draft\"}";
            websocket.send(poruka);
        }

        $("#izborPozicija").selectmenu({
            change: function (event, ui) {
                vratiIgraceKriterijum("draft");
            }
        });
        $("#izborTim").selectmenu({
            change: function (event, ui) {
                vratiIgraceKriterijum("draft");
            }
        });
        $(function () {
            $("#tabelaIzbor").selectable({
                filter: ".zaIzbor",
                selected: function (event, ui) {
                    var id = $(ui.selected).attr("id");
                    $("#igrac_id").val(id.substr(9));
                    postaviPoziciju(id.substr(9));
                    validacijaFantasyTim();
                }
            });
        });
    </script>
</html>
