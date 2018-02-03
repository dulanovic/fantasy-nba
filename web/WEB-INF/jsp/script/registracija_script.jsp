<%-- 
    Document   : registracija_script
    Created on : Jun 26, 2017, 6:27:06 PM
    Author     : Korisnik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    <c:if test="${user_login != null}">
        //validirajSvaPolja();
    </c:if>
    $(".widget input[type=submit]").button();
    $("#datepicker").datepicker({
        dateFormat: "yy-mm-dd"
    });

    function potvrdiBrisanje() {
        var povratnaVrednost = (confirm("Da li ste sigurni da želite da obrišete nalog?") == true) ? true : false;
        return povratnaVrednost;
    }
</script>