<%-- 
    Document   : utakmica_script
    Created on : Jul 5, 2017, 4:30:31 AM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load("visualization", "1.0", {"packages": ["corechart"]});
    google.load("visualization", "1.0", {"packages": ["bar"]});
    google.setOnLoadCallback(generisiGrafike);

    function generisiGrafike() {

        var podaciSkokovi = new google.visualization.DataTable();
        podaciSkokovi.addColumn("string", "tim");
        podaciSkokovi.addColumn("number", "ukupnoSkokova");
        podaciSkokovi.addRows([
            ["${ucinak_domacin.tim.skraceniNaziv}", ${ucinak_domacin.skokoviUkupno}],
            ["${ucinak_gost.tim.skraceniNaziv}", ${ucinak_gost.skokoviUkupno}]
        ]);

        var opcijeSkokovi = {"title": "Odnos u skokovima",
            "is3D": true,
            "width": 400,
            "height": 300,
            "fontName": "Verdana",
            "colors": ["#${ucinak_domacin.tim.boja1}", "#${ucinak_gost.tim.boja2}"]};

        var podaciAsistencije = new google.visualization.DataTable();
        podaciAsistencije.addColumn("string", "tim");
        podaciAsistencije.addColumn("number", "ukupnoAsistencija");
        podaciAsistencije.addRows([
            ["${ucinak_domacin.tim.skraceniNaziv}", ${ucinak_domacin.asistencije}],
            ["${ucinak_gost.tim.skraceniNaziv}", ${ucinak_gost.asistencije}]
        ]);

        var opcijeAsistencije = {"title": "Odnos u asistencijama",
            "is3D": true,
            "width": 400,
            "height": 300,
            "fontName": "Verdana",
            "colors": ["#${ucinak_domacin.tim.boja1}", "#${ucinak_gost.tim.boja2}"]};

        var podaciUkradeneLopte = new google.visualization.DataTable();
        podaciUkradeneLopte.addColumn("string", "tim");
        podaciUkradeneLopte.addColumn("number", "ukupnoUkradenihLopti");
        podaciUkradeneLopte.addRows([
            ["${ucinak_domacin.tim.skraceniNaziv}", ${ucinak_domacin.ukradeneLopte}],
            ["${ucinak_gost.tim.skraceniNaziv}", ${ucinak_gost.ukradeneLopte}]
        ]);

        var opcijeUkradeneLopte = {"title": "Odnos u ukradenim loptama",
            "is3D": true,
            "width": 400,
            "height": 300,
            "fontName": "Verdana",
            "colors": ["#${ucinak_domacin.tim.boja2}", "#${ucinak_gost.tim.boja1}"]};

        var podaciBlokade = new google.visualization.DataTable();
        podaciBlokade.addColumn("string", "tim");
        podaciBlokade.addColumn("number", "ukupnoBlokada");
        podaciBlokade.addRows([
            ["${ucinak_domacin.tim.skraceniNaziv}", ${ucinak_domacin.blokade}],
            ["${ucinak_gost.tim.skraceniNaziv}", ${ucinak_gost.blokade}]
        ]);

        var opcijeBlokade = {"title": "Odnos u blokadama",
            "is3D": true,
            "width": 400,
            "height": 300,
            "fontName": "Verdana",
            "colors": ["#${ucinak_domacin.tim.boja2}", "#${ucinak_gost.tim.boja1}"]};

        var podaciSut = new google.visualization.arrayToDataTable([
            ["Kategorija", "${ucinak_domacin.tim.skraceniNaziv}", "${ucinak_gost.tim.skraceniNaziv}"],
            ["Šut za 2p", ${ucinak_domacin.procenat2p}, ${ucinak_gost.procenat2p}],
            ["Šut za 3p", ${ucinak_domacin.procenat3p}, ${ucinak_gost.procenat3p}],
            ["Šut iz igre", ${ucinak_domacin.procenatIzIgre}, ${ucinak_gost.procenatIzIgre}],
            ["Slobodna bacanja", ${ucinak_domacin.procenat1p}, ${ucinak_gost.procenat1p}]
        ]);

        var pogledSut = new google.visualization.DataView(podaciSut);
        pogledSut.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}, 2, {"calc": "stringify", "sourceColumn": 2, "type": "string", "role": "annotation"}]);

        var opcijeSut = {"title": "Procenti šuta",
            "width": 800,
            "fontName": "Verdana",
            "colors": ["#${ucinak_domacin.tim.boja1}", "#${ucinak_gost.tim.boja2}"]
        };

        var grafikSkokovi = new google.visualization.PieChart(document.getElementById("skokovi"));
        grafikSkokovi.draw(podaciSkokovi, opcijeSkokovi);
        var grafikAsistencije = new google.visualization.PieChart(document.getElementById("asistencije"));
        grafikAsistencije.draw(podaciAsistencije, opcijeAsistencije);
        var grafikUkradeneLopte = new google.visualization.PieChart(document.getElementById("ukradeneLopte"));
        grafikUkradeneLopte.draw(podaciUkradeneLopte, opcijeUkradeneLopte);
        var grafikBlokade = new google.visualization.PieChart(document.getElementById("blokade"));
        grafikBlokade.draw(podaciBlokade, opcijeBlokade);
        var grafikSut = new google.visualization.ColumnChart(document.getElementById("sutGrafika"));
        grafikSut.draw(pogledSut, opcijeSut);
    }

</script>