<%-- 
    Document   : igrac_script
    Created on : Jun 27, 2017, 1:07:06 AM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load("visualization", "1.0", {"packages": ["corechart"]});
    google.setOnLoadCallback(generisiGrafike);

    function generisiGrafike() {

    <c:if test="${nba_igrac.igracUcinakList.size() == 0}">
        document.getElementById("grafici").style.display = "none";
        return;
    </c:if>

        var opcije = {"title": "",
            "width": 800,
            "fontName": "Verdana",
            "hAxis": {
                "textStyle": {
                    "fontSize": 10
                }
            },
            "bar": {"groupWidth": "40%"},
            "legend": {"position": "none"},
            "colors": ["#${nba_igrac.tim.boja1}"]
        };

        var nizPodaciSutIzIgre = [];
        nizPodaciSutIzIgre.push(["Utakmica", "Šut iz igre"]);
    <c:forEach var="trenutni_ucinak" items="${nba_igrac.igracUcinakList}">
        nizPodaciSutIzIgre.push(["${trenutni_ucinak.igrac.usi.vratiSkracenIspis(nba_igrac.tim, trenutni_ucinak.utakmica)}", ${trenutni_ucinak.procenatIzIgre}]);
    </c:forEach>

        var podaciSutIzIgre = new google.visualization.arrayToDataTable(nizPodaciSutIzIgre, false);
        var pogledSutIzIgre = new google.visualization.DataView(podaciSutIzIgre);
        pogledSutIzIgre.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}]);

        opcije.title = "Procenti šuta iz igre";
        var grafikSutIzIgre = new google.visualization.ColumnChart(document.getElementById("sutIzIgreGrafik"));
        grafikSutIzIgre.draw(pogledSutIzIgre, opcije);

        var nizPodaciSlobodnaBacanja = [];
        nizPodaciSlobodnaBacanja.push(["Utakmica", "Slobodna bacanja"]);
    <c:forEach var="trenutni_ucinak" items="${nba_igrac.igracUcinakList}">
        nizPodaciSlobodnaBacanja.push(["${trenutni_ucinak.igrac.usi.vratiSkracenIspis(nba_igrac.tim, trenutni_ucinak.utakmica)}", ${trenutni_ucinak.procenat1p}]);
    </c:forEach>

        var podaciSlobodnaBacanja = new google.visualization.arrayToDataTable(nizPodaciSlobodnaBacanja, false);
        var pogledSlobodnaBacanja = new google.visualization.DataView(podaciSlobodnaBacanja);
        pogledSlobodnaBacanja.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}]);

        opcije.title = "Procenti slobodnih bacanja";
        var grafikSlobodnaBacanja = new google.visualization.ColumnChart(document.getElementById("slobodnaBacanjaGrafik"));
        grafikSlobodnaBacanja.draw(pogledSlobodnaBacanja, opcije);

        var nizPodaciPogodak3p = [];
        nizPodaciPogodak3p.push(["Utakmica", "Pogodak 3p"]);
    <c:forEach var="trenutni_ucinak" items="${nba_igrac.igracUcinakList}">
        nizPodaciPogodak3p.push(["${trenutni_ucinak.igrac.usi.vratiSkracenIspis(nba_igrac.tim, trenutni_ucinak.utakmica)}", ${trenutni_ucinak.pogodak3p}]);
    </c:forEach>

        var podaciPogodak3p = new google.visualization.arrayToDataTable(nizPodaciPogodak3p, false);
        var pogledPogodak3p = new google.visualization.DataView(podaciPogodak3p);
        pogledPogodak3p.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}]);

        opcije.title = "Pogodak za 3p";
        var grafikPogodak3p = new google.visualization.ColumnChart(document.getElementById("pogodak3pGrafik"));
        grafikPogodak3p.draw(pogledPogodak3p, opcije);

        var nizPodaciSkokovi = [];
        nizPodaciSkokovi.push(["Utakmica", "Skokovi"]);
    <c:forEach var="trenutni_ucinak" items="${nba_igrac.igracUcinakList}">
        nizPodaciSkokovi.push(["${trenutni_ucinak.igrac.usi.vratiSkracenIspis(nba_igrac.tim, trenutni_ucinak.utakmica)}", ${trenutni_ucinak.skokoviUkupno}]);
    </c:forEach>

        var podaciSkokovi = new google.visualization.arrayToDataTable(nizPodaciSkokovi, false);
        var pogledSkokovi = new google.visualization.DataView(podaciSkokovi);
        pogledSkokovi.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}]);

        opcije.title = "Skokovi";
        var grafikSkokovi = new google.visualization.ColumnChart(document.getElementById("skokoviGrafik"));
        grafikSkokovi.draw(pogledSkokovi, opcije);

        var nizPodaciAsistencije = [];
        nizPodaciAsistencije.push(["Utakmica", "Asistencije"]);
    <c:forEach var="trenutni_ucinak" items="${nba_igrac.igracUcinakList}">
        nizPodaciAsistencije.push(["${trenutni_ucinak.igrac.usi.vratiSkracenIspis(nba_igrac.tim, trenutni_ucinak.utakmica)}", ${trenutni_ucinak.asistencije}]);
    </c:forEach>

        var podaciAsistencije = new google.visualization.arrayToDataTable(nizPodaciAsistencije, false);
        var pogledAsistencije = new google.visualization.DataView(podaciAsistencije);
        pogledAsistencije.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}]);

        opcije.title = "Asistencije";
        var grafikAsistencije = new google.visualization.ColumnChart(document.getElementById("asistencijeGrafik"));
        grafikAsistencije.draw(pogledAsistencije, opcije);

        var nizPodaciUkradeneLopte = [];
        nizPodaciUkradeneLopte.push(["Utakmica", "Ukradene lopte"]);
    <c:forEach var="trenutni_ucinak" items="${nba_igrac.igracUcinakList}">
        nizPodaciUkradeneLopte.push(["${trenutni_ucinak.igrac.usi.vratiSkracenIspis(nba_igrac.tim, trenutni_ucinak.utakmica)}", ${trenutni_ucinak.ukradeneLopte}]);
    </c:forEach>

        var podaciUkradeneLopte = new google.visualization.arrayToDataTable(nizPodaciUkradeneLopte, false);
        var pogledUkradeneLopte = new google.visualization.DataView(podaciUkradeneLopte);
        pogledUkradeneLopte.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}]);

        opcije.title = "Ukradene lopte";
        var grafikUkradeneLopte = new google.visualization.ColumnChart(document.getElementById("ukradeneLopteGrafik"));
        grafikUkradeneLopte.draw(pogledUkradeneLopte, opcije);

        var nizPodaciBlokade = [];
        nizPodaciBlokade.push(["Utakmica", "Blokade"]);
    <c:forEach var="trenutni_ucinak" items="${nba_igrac.igracUcinakList}">
        nizPodaciBlokade.push(["${trenutni_ucinak.igrac.usi.vratiSkracenIspis(nba_igrac.tim, trenutni_ucinak.utakmica)}", ${trenutni_ucinak.blokade}]);
    </c:forEach>

        var podaciBlokade = new google.visualization.arrayToDataTable(nizPodaciBlokade, false);
        var pogledBlokade = new google.visualization.DataView(podaciBlokade);
        pogledBlokade.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}]);

        opcije.title = "Blokade";
        var grafikBlokade = new google.visualization.ColumnChart(document.getElementById("blokadeGrafik"));
        grafikBlokade.draw(pogledBlokade, opcije);

        var nizPodaciPoeni = [];
        nizPodaciPoeni.push(["Utakmica", "Poeni"]);
    <c:forEach var="trenutni_ucinak" items="${nba_igrac.igracUcinakList}">
        nizPodaciPoeni.push(["${trenutni_ucinak.igrac.usi.vratiSkracenIspis(nba_igrac.tim, trenutni_ucinak.utakmica)}", ${trenutni_ucinak.poeni}]);
    </c:forEach>

        var podaciPoeni = new google.visualization.arrayToDataTable(nizPodaciPoeni, false);
        var pogledPoeni = new google.visualization.DataView(podaciPoeni);
        pogledPoeni.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}]);

        opcije.title = "Poeni";
        var grafikPoeni = new google.visualization.ColumnChart(document.getElementById("poeniGrafik"));
        grafikPoeni.draw(pogledPoeni, opcije);

    }

</script>