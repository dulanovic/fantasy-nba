function validacijaIme(unos) {
    var ispis = document.getElementById("validacija-ime");
    if (unos == "") {
        ispis.style.color = "#FF0000";
        ispis.innerHTML = "Niste uneli ime!";
    } else {
        ispis.style.color = "#008000";
        ispis.innerHTML = "OK!";
    }
}

function validacijaPrezime(unos) {
    var ispis = document.getElementById("validacija-prezime");
    if (unos == "") {
        ispis.style.color = "#FF0000";
        ispis.innerHTML = "Niste uneli prezime!";
    } else {
        ispis.style.color = "#008000";
        ispis.innerHTML = "OK!";
    }
}

function validacijaDatumRodjenja(unos) {
    var datumRodjenja = new Date(unos);
    var trenutniDatum = new Date("2017/02/23");
    var razlika = trenutniDatum - datumRodjenja;
    var brojGodina = Math.floor(razlika / (1000 * 60 * 60 * 24 * 365.25));
    var ispis = document.getElementById("validacija-datumRodjenja");
    if (brojGodina >= 18) {
        ispis.style.color = "#008000";
        ispis.innerHTML = "OK!";
    } else {
        ispis.style.color = "#FF0000";
        ispis.innerHTML = "Morate biti stariji od 18 godina!";
    }
}

function validacijaEmail(unos) {
    var ispis = document.getElementById("validacija-email");
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(unos)) {
        ispis.style.color = "#008000";
        ispis.innerHTML = "OK!";
    } else {
        ispis.style.color = "#FF0000";
        ispis.innerHTML = "E-mail nije u korektnom formatu!";
    }
}

function validacijaKorisnickaSifra(unos) {
    var korisnickaSifra = document.getElementById("korisnickaSifra").value;
    if (unos != korisnickaSifra) {
        ispis.style.color = "#FF0000";
        ispis.innerHTML = "Unete sifre nisu iste!";
    } else {
        if (!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,20}$/.test(korisnickaSifra)) {
            ispis.style.color = "#FF0000";
            ispis.innerHTML = "Šifra sadrži nedozvoljene simbole!";
        } else {
            ispis.style.color = "#008000";
            ispis.innerHTML = "OK!";
        }
    }
    var ispis = document.getElementById("validacija-korisnickaSifra");
    ispis.style.color = "#008000";
    ispis.innerHTML = "OK!";
}

function validacijaRegistracija() {
    var ispisi = document.getElementsByClassName("validacija");
    for (var i = 0; i < ispisi.length; i++) {
        if (ispisi[i].innerHTML != "OK!" || ispisi[i].innerHTML == "") {
            alert("Niste ispravno popunili sva polja!");
            return false;
        }
    }
    return true;
}

function validirajSvaPolja() {
    var ime = document.getElementsByName("ime")[0].value;
    var prezime = document.getElementsByName("prezime")[0].value;
    var datumRodjenja = document.getElementsByName("datumRodjenja")[0].value;
    var email = document.getElementsByName("email")[0].value;
    var korisnickoIme = document.getElementsByName("korisnickoIme")[0].value;
    var korisnickaSifraPonovljena = document.getElementsByName("korisnickaSifraPonovljena")[0].value;

    validacijaIme(ime);
    validacijaPrezime(prezime);
    validacijaDatumRodjenja(datumRodjenja);
    validacijaEmail(email);
    proveriKorisnickoIme(korisnickoIme);
    validacijaKorisnickaSifra(korisnickaSifraPonovljena);
}

function validacijaUnosLige() {
    var ispis = document.getElementById("validacija-nazivLige");
    if (ispis.innerHTML != "OK!") {
        return false;
    } else {
        return true;
    }
}

function validacijaUnosTima() {
    var ispis = document.getElementById("validacija-nazivTima");
    if (ispis.innerHTML != "OK!") {
        return false;
    } else {
        return true;
    }
}