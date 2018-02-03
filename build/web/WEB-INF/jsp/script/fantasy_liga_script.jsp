<%-- 
    Document   : fantasy_liga_script
    Created on : Jun 29, 2017, 11:23:26 AM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    function potvrdiBrisanje() {
        var povratnaVrednost = (confirm("Da li ste sigurni da želite da obrišete ligu?") == true) ? true : false;
        return povratnaVrednost;
    }
</script>