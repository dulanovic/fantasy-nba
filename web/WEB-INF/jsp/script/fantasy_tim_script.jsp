<%-- 
    Document   : fantasy_tim_script
    Created on : Jun 26, 2017, 6:39:49 PM
    Author     : Korisnik
--%>

<%@page import="domen.FantasyTim"%>
<%@page import="constants.WebConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(".izborStatus").selectmenu({
        width: 150,
        change: function () {
            prebrojStatuse(null);
        }
    });

    <%  FantasyTim ft = (FantasyTim) request.getAttribute(WebConstants.FANTASY_TIM);
        if (ft != null) {
            for (int i = 0; i < ft.getFantasyIgracList().size(); i++) {
                if (ft.getFantasyIgracList().get(i).getStatus() != null) {%>
    $("#izbor<%=ft.getFantasyIgracList().get(i).getFantasyIgracId()%>").val("<%=ft.getFantasyIgracList().get(i).getStatus().getStatusIgracaNaziv()%>");
    <% } else {%>
    $("#izbor<%=ft.getFantasyIgracList().get(i).getFantasyIgracId()%>").val("Rezerva");
    <% }%>
    $("#izbor<%=ft.getFantasyIgracList().get(i).getFantasyIgracId()%>").selectmenu("refresh");
    <% }
        }%>

    <%
        if (!ft.getLiga().getStatus().getStatusLigeNaziv().equals("Pre-draft")) { %>
    prebrojStatuse(null);
    <% }%>


    function potvrdiBrisanje() {
        var povratnaVrednost = (confirm("Da li ste sigurni da želite da obrišete Vaš tim?") == true) ? true : false;
        return povratnaVrednost;
    }
</script>