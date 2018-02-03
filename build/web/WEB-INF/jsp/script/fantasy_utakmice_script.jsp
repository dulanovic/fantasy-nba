<%-- 
    Document   : fantasy_utakmice_script
    Created on : Jun 29, 2017, 9:44:48 AM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    nadjiUtakmiceUToku("${datum_azuriranja_lige}");

    function nadjiUtakmiceUToku(datumAzuriranjaLige) {
        var trenutniDatum = new Date(datumAzuriranjaLige);
        var sveTabele = document.getElementsByClassName("utakmice");
        var trazeneTabele = [];
        for (var i = 0; i < sveTabele.length; i++) {
            var datumOd = new Date(sveTabele[i].id.substring(9, 19));
            var datumDo = new Date(sveTabele[i].id.substring(20));
            if (trenutniDatum >= datumOd && trenutniDatum <= datumDo) {
                trazeneTabele.push(sveTabele[i]);
            }
        }
        for (var i = 0; i < trazeneTabele.length; i++) {
            trazeneTabele[i].rows[1].style.color = "#DD0000";
            trazeneTabele[i].rows[2].style.color = "#DD0000";
        }
    }
</script>