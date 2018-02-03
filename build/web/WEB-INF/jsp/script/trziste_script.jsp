<%-- 
    Document   : trziste_script
    Created on : Jun 26, 2017, 6:42:08 PM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    vratiIgraceKriterijum("trziste");

    $("#izborPozicija").selectmenu({
        change: function (event, ui) {
            vratiIgraceKriterijum("trziste");
        }
    });

    $("#izborTim").selectmenu({
        change: function (event, ui) {
            vratiIgraceKriterijum("trziste");
        }
    });
</script>