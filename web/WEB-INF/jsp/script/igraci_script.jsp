<%-- 
    Document   : igraci_script
    Created on : Jun 26, 2017, 6:49:05 PM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    vratiIgraceKriterijum("prikaz");

    $("#izborPozicija").selectmenu({
        change: function (event, ui) {
            vratiIgraceKriterijum("prikaz");
        }
    });
    $("#izborTim").selectmenu({
        change: function (event, ui) {
            vratiIgraceKriterijum("prikaz");
        }
    });
</script>