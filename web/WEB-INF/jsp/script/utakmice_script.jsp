<%-- 
    Document   : utakmice_script
    Created on : Jun 27, 2017, 6:26:15 PM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    vratiUtakmiceParametar("datum", "${trenutni_datum}");

    $("#datepicker").datepicker({
        dateFormat: "yy-mm-dd",
        minDate: new Date(2017, 1, 23),
        maxDate: new Date(2017, 3, 12)
    });

    $("#izborTim").selectmenu({
        change: function (event, ui) {
            vratiUtakmiceParametar("tim", this.value);
        }
    });
</script>