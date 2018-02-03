var xmlHttp;
function getXmlHttpRequest() {
    var xmlHttp = null;
    try {
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    return xmlHttp;
}

function proveriKorisnickoIme(unos) {
    xmlHttp = getXmlHttpRequest();
    if (xmlHttp == null) {
        alert("Vaš browser ne podržava AJAX HTTP zahteve!");
        return;
    }

    var ispis = document.getElementById("validacija-korisnickoIme");
    if (!/^[a-zA-Z0-9.\-_$@*!]{8,20}$/.test(unos)) {
        ispis.style.color = "#FF0000";
        ispis.innerHTML = "Nepropisno korisnicko ime!";
        return;
    }

    var url = "./ajax/korisnickoIme?korisnickoIme=" + unos;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                if (xmlHttp.responseText == "0") {
                    ispis.style.color = "#FF0000";
                    ispis.innerHTML = "Korisnicko ime je zauzeto!";
                } else {
                    ispis.style.color = "#008000";
                    ispis.innerHTML = "OK!";
                }
            }
        }
    };
    var ispis = document.getElementById("validacija-korisnickoIme");
    ispis.style.color = "#008000";
    ispis.innerHTML = "OK!";
}

function proveriNazivLige(unos) {
    xmlHttp = getXmlHttpRequest();
    if (xmlHttp == null) {
        alert("Vaš browser ne podržava AJAX HTTP zahteve!");
        return;
    }

    var ispis = document.getElementById("validacija-nazivLige");
    if (unos.length < 5) {
        ispis.style.color = "#FF0000";
        ispis.innerHTML = "Nepropisan naziv lige!";
        return;
    }

    var url = "./ajax/nazivLige?nazivLige=" + unos;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                if (xmlHttp.responseText == "0") {
                    ispis.style.color = "#FF0000";
                    ispis.innerHTML = "Liga sa tim nazivom već postoji!";
                } else {
                    ispis.style.color = "#008000";
                    ispis.innerHTML = "OK!";
                }
            }
        }
    };
}

function proveriNazivTima(unos) {
    xmlHttp = getXmlHttpRequest();
    if (xmlHttp == null) {
        alert("Vaš browser ne podržava AJAX HTTP zahteve!");
        return;
    }

    var ispis = document.getElementById("validacija-nazivTima");
    if (unos.length < 5) {
        ispis.style.color = "#FF0000";
        ispis.innerHTML = "Nepropisan naziv tima!";
        return;
    }

    var url = "./ajax/nazivTima?nazivTima=" + unos;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                if (xmlHttp.responseText == "0") {
                    ispis.style.color = "#FF0000";
                    ispis.innerHTML = "Tim sa tim nazivom već postoji!";
                } else {
                    ispis.style.color = "#008000";
                    ispis.innerHTML = "OK!";
                }
            }
        }
    };
}

function vratiIgraceKriterijum(tip) {
    var ligaId;
    if (tip != "prikaz") {
        ligaId = document.getElementById("liga_id").value;
    }
    var selectPozicija = document.getElementById("izborPozicija");
    var unosPozicija = (selectPozicija.options[selectPozicija.selectedIndex].value != "Izaberite poziciju") ? selectPozicija.options[selectPozicija.selectedIndex].value : "svi";
    var selectTim = document.getElementById("izborTim");
    var unosTim = (selectTim.options[selectTim.selectedIndex].value != "Izaberite tim") ? selectTim.options[selectTim.selectedIndex].value : "svi";

    xmlHttp = getXmlHttpRequest();
    if (xmlHttp == null) {
        alert("Vaš browser ne podržava AJAX HTTP zahteve!");
        return;
    }

    var adresa = "./ajax/draftPrikaz?liga=" + ligaId + "&pozicija=" + unosPozicija + "&tim=" + unosTim + "&tip=" + tip;
    xmlHttp.open("GET", adresa, true);
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                var divTabela = document.getElementById("tabelaIzbor");
                divTabela.innerHTML = xmlHttp.responseText;
            }
        }
    };
}

function vratiIgracePretraga(tip) {
    var ligaId;
    if (tip != "prikaz") {
        ligaId = document.getElementById("liga_id").value;
    }
    var unos = document.getElementById("igracPretraga").value;
    if (unos == "") {
        vratiIgraceKriterijum(tip);
        return;
    }

    xmlHttp = getXmlHttpRequest();
    if (xmlHttp == null) {
        alert("Vaš browser ne podržava AJAX HTTP zahteve!");
        return;
    }

    var adresa = "./ajax/draftPrikaz?liga=" + ligaId + "&unos=" + unos + "&tip=" + tip;
    xmlHttp.open("GET", adresa, true);
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                var divTabela = document.getElementById("tabelaIzbor");
                divTabela.innerHTML = xmlHttp.responseText;
            }
        }
    };
}

function validacijaFantasyTim() {
    var liga = document.getElementById("liga_id").value;
    var tim = document.getElementById("naziv_tima").value;
    var igrac = document.getElementById("igrac_id").value;
    var ispis = document.getElementById("igrac_id");
    var nizPozicije = prebrojPozicije();
    var brojPozicijaSaTriIgraca = 0;
    for (var i = 0; i < nizPozicije.length; i++) {
        if (nizPozicije[i] > 2) {
            brojPozicijaSaTriIgraca++;
        }
    }
    if (brojPozicijaSaTriIgraca > 2) {
        alert("Nije dozvoljeno izabrati 3 igrača na 3 pozicije!");
        return;
    }

    if (document.getElementById("poslednja_runda").value == "1") {
        if (!validirajPozicije()) {
            return;
        }
    }

    xmlHttp = getXmlHttpRequest();
    if (xmlHttp == null) {
        alert("Vaš browser ne podržava AJAX HTTP zahteve!");
        return;
    }

    var adresa = "./ajax/validacijaFantasyTim?liga=" + liga + "&tim=" + tim + "&igrac=" + igrac;
    xmlHttp.open("GET", adresa, true);
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                if (xmlHttp.responseText == "0") {
                    ispis.value = "";
                    alert("Ne možete izabrati tog igrača, jer već imate 3 igrača na toj poziciji u Vašem timu.");
                } else {
                    document.getElementById("izborIgrac").disabled = false;
                }
            }
        }
    };
}




function preuzmiRaspored() {
    xmlHttp = getXmlHttpRequest();
    if (xmlHttp == null) {
        alert("Vaš browser ne podržava AJAX HTTP zahteve!");
        return;
    }

    //var ispis = document.getElementById("rasporedDatum");
    var url = "./admin/unosRasporeda";
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                alert("Raspored je uspešno unet u bazu podataka.");
                location.reload();
            }
        }
    };
}

function prebrojStatuse(igrac) {
    var nizPozicija = document.getElementsByClassName("pozicijaIgrac");
    var nizStatus = document.getElementsByClassName("izborStatus");
    var nizIgracId = document.getElementsByClassName("fantasy_igrac_id");
    var brojPlejmejkera = 0;
    var brojBekova = 0;
    var brojKrila = 0;
    var brojKrilnihCentara = 0;
    var brojCentara = 0;
    var ispis = "";
    for (var i = 0; i < nizPozicija.length; i++) {
        if (nizIgracId[i].value == igrac) {
            continue;
        }
        if (nizStatus[i].options[nizStatus[i].selectedIndex].value == "Aktivan") {
            var pozicija = nizPozicija[i].innerHTML;
            switch (pozicija) {
                case "Plejmejker":
                    brojPlejmejkera++;
                    break;
                case "Bek":
                    brojBekova++;
                    break;
                case "Krilo":
                    brojKrila++;
                    break;
                case "Krilni centar":
                    brojKrilnihCentara++;
                    break;
                case "Centar":
                    brojCentara++;
                    break;
            }
        }
    }
    var brojIgraca = brojPlejmejkera + brojBekova + brojKrila + brojKrilnihCentara + brojCentara;
    if (brojIgraca != 8) {
        ispis += "Imate " + brojIgraca + " aktivnih igrača u rosteru. Da bi roster bio validan mora imati 8 aktivnih igrača, najmanje po 1 na svakoj poziciji.\n";
    }
    if (brojPlejmejkera < 1) {
        ispis += "Nemate nijednog aktivnog plejmejkera u rosteru!\n";
    }
    if (brojBekova < 1) {
        ispis += "Nemate nijednog aktivnog beka u rosteru!\n";
    }
    if (brojKrila < 1) {
        ispis += "Nemate nijedno aktivno krilo u rosteru!\n";
    }
    if (brojKrilnihCentara < 1) {
        ispis += "Nemate nijednog aktivnog krilnog centra u rosteru!\n";
    }
    if (brojCentara < 1) {
        ispis += "Nemate nijednog aktivnog centra u rosteru!\n";
    }
    if (ispis.length == 0) {
        ispis = "Roster je validan.";
        document.getElementById("ispisValidacija").style.color = "#009900";
    } else {
        document.getElementById("ispisValidacija").style.color = "#FF0000";
    }
    document.getElementById("ispisValidacija").innerHTML = ispis;
    return ispis;
}

function posaljiRoster(igrac) {
    var poruka = prebrojStatuse(igrac);
    if (poruka.length != 0) {
        alert(poruka);
    }
    var tim = document.getElementById("fantasy_tim_id").value;
    var nizStatus = document.getElementsByClassName("izborStatus");
    var roster = "{ \"roster\": [";
    for (var i = 0; i < nizStatus.length; i++) {
        var igracId = nizStatus[i].id.substr(5);
        var status = nizStatus[i].options[nizStatus[i].selectedIndex].value;
        roster += "{\"id\": " + igracId + ", \"status\": \"" + status + "\"}";
        if (i < nizStatus.length - 1) {
            roster += ",";
        }
    }
    roster += "]}";
    $.ajax({
        method: "POST",
        url: "./roster/unosRostera?tim=" + tim,
        dataType: "json",
        data: {roster}
    }).done(function (data) {
        if (data.status == 0) {
            alert(data.poruka);
        } else {
            alert(data.poruka);
            location.reload();
        }
    });
}

function nadjiZamenu(igrac) {
    var nizPozicija = document.getElementsByClassName("pozicijaIgrac");
    var nizStatus = document.getElementsByClassName("izborStatus");
    var idIgraca = document.getElementsByClassName("fantasy_igrac_id");
    var pozicijaIgraca = "";
    var nizRezerve = [];
    for (var i = 0; i < idIgraca.length; i++) {
        if (idIgraca[i].value == igrac) {
            pozicijaIgraca = nizPozicija[i].innerHTML;
        }
        if ((nizStatus[i].options[nizStatus[i].selectedIndex].value == "Rezerva") && (idIgraca[i].value != igrac)) {
            nizRezerve.push(i);
        }
    }
    if (nizRezerve.length == 0) {
        return;
    }
    var pozicija1 = "Plejmejker";
    var pozicija2 = "Bek";
    var pozicija3 = "Krilo";
    var pozicija4 = "Krilni centar";
    var pozicija5 = "Centar";
    var pozicijeNiz = [];
    switch (pozicijaIgraca) {
        case "Plejmejker":
        {
            pozicijeNiz.push(pozicija1, pozicija2, pozicija3, pozicija4, pozicija5);
            break;
        }
        case "Bek":
        {
            pozicijeNiz.push(pozicija2, pozicija1, pozicija3, pozicija4, pozicija5);
            break;
        }
        case "Krilo":
        {
            pozicijeNiz.push(pozicija3, pozicija2, pozicija4, pozicija1, pozicija5);
            break;
        }
        case "Krilni centar":
        {
            pozicijeNiz.push(pozicija4, pozicija5, pozicija3, pozicija2, pozicija1);
            break;
        }
        case "Centar":
        {
            pozicijeNiz.push(pozicija5, pozicija4, pozicija3, pozicija2, pozicija1);
            break;
        }
    }
    aktivirajZamenu(nizRezerve, pozicijeNiz);
}

function aktivirajZamenu(rezerveNiz, pozicijeNiz) {
    var nizPozicija = document.getElementsByClassName("pozicijaIgrac");
    var nizStatus = document.getElementsByClassName("izborStatus");
    var idIgraca = document.getElementsByClassName("fantasy_igrac_id");
    var aktiviran = false;
    for (var i = 0; i < pozicijeNiz.length; i++) {
        for (var j = 0; j < rezerveNiz.length; j++) {
            if (nizPozicija[rezerveNiz[j]].innerHTML == pozicijeNiz[i]) {
                $("#izbor" + idIgraca[rezerveNiz[j]].value).val("Aktivan");
                $("#izbor" + idIgraca[rezerveNiz[j]].value).selectmenu("refresh");
                aktiviran = true;
            }
        }
        if (aktiviran) {
            break;
        }
    }
}

function otpustiIgraca(igrac) {
    if (confirm("Da li ste sigurni da želite da otpustite igrača?") != true) {
        return;
    }
    nadjiZamenu(igrac);
    $.ajax({
        method: "POST",
        url: "./roster/otkazIgrac?igrac=" + igrac,
        dataType: "json"
    }).done(function (data) {
        if (data.status == 0) {
            alert(data.poruka);
        } else {
            alert(data.poruka);
            //posaljiRoster(igrac);
            location.reload();
        }
    });
}

function odobriLigu(liga) {
    xmlHttp = getXmlHttpRequest();
    if (xmlHttp == null) {
        alert("Vaš browser ne podržava AJAX HTTP zahteve!");
        return;
    }

    var url = "./admin/odobriLigu?liga=" + liga;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                alert("Liga je odobrena i omogućen je početak sezone.");
                location.reload();
            }
        }
    };
}

function azurirajRezultate() {
    $.ajax({
        method: "GET",
        url: "./admin/azuriranjeRezultata"
    }).done(function (data) {
        alert("Uneti su rezultati za datum: " + data);
        location.reload();
    });
}

function azurirajLigu(liga) {
    $.ajax({
        method: "GET",
        url: "./admin/azuriranjeFantasyLige?liga=" + liga
    }).done(function (data) {
        alert("Liga je ažurirana na: " + data);
        location.reload();
    });
}

function angazujIgraca(tim, igrac) {
    $.ajax({
        method: "POST",
        url: "./roster/angazmanIgraca?tim=" + tim + "&igrac=" + igrac,
        dataType: "json"
    }).done(function (data) {
        if (data.status == 0) {
            alert(data.poruka);
        } else {
            alert(data.poruka);
            location.reload();
        }
    });
}

function vratiUtakmiceParametar(tip, parametar) {
    $.ajax({
        method: "POST",
        url: "./utakmice/prikaz?tip=" + tip + "&parametar=" + parametar
    }).done(function (data) {
        var divTabela = document.getElementById("utakmice");
        divTabela.innerHTML = data;
    });
}

function ucitajVesti() {
    $.ajax({
        method: "GET",
        url: "./ajax/prikazVesti"
    }).done(function (data) {
        var divVesti = document.getElementById("vesti");
        divVesti.innerHTML = data;
    });
}