<%-- 
    Document   : tim_script
    Created on : Jun 27, 2017, 3:44:37 AM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
    google.load("visualization", "1.0", {"packages": ["corechart"]});
    google.setOnLoadCallback(generisiGrafik);
    <c:if test="${nba_tim.timUcinakList.size() == 0}">
    document.getElementById("grafici").style.display = "none";
    return;
    </c:if>

    function generisiGrafik() {

        var nizPodaciForma = [];
        nizPodaciForma.push(["Utakmica", "Rezultat", {role: 'style'}, "utakmicaId"]);
    <c:forEach var="trenutna_utakmica" items="${forma_nba_tim}">
        nizPodaciForma.push(["${trenutna_utakmica.utakmicaIspis}", ${trenutna_utakmica.razlika}, "${trenutna_utakmica.boja}", ${trenutna_utakmica.utakmicaId}]);
    </c:forEach>

        var opcije = {"title": "Forma - ${nba_tim.timNaziv}",
            "width": 800,
            "fontName": "Verdana",
            "hAxis": {
                "textStyle": {
                    "fontSize": 10
                }
            },
            "bar": {"groupWidth": "20%"},
            "legend": {"position": "none"}
        };
        var podaciForma = new google.visualization.arrayToDataTable(nizPodaciForma, false);
        podaciForma.setColumnProperty(2, 'role', 'style');
        var pogledForma = new google.visualization.DataView(podaciForma);
        pogledForma.setColumns([0, 1, {"calc": "stringify", "sourceColumn": 1, "type": "string", "role": "annotation"}, 2]);
        var grafikForma = new google.visualization.ColumnChart(document.getElementById("formaGrafik"));
        grafikForma.draw(pogledForma, opcije);
        function idiNaStranicu() {
            var izabrani = grafikForma.getSelection()[0];
            if (izabrani) {
                window.open("http://localhost:8080/FantasyNBA/fantasy?action=utakmica&utakmica=" + podaciForma.getValue(izabrani.row, 3));
            }
        }

        google.visualization.events.addListener(grafikForma, "select", idiNaStranicu);
    }
</script>