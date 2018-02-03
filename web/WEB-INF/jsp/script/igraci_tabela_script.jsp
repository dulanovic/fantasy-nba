<%-- 
    Document   : igraci_tabela_script
    Created on : Jul 7, 2017, 6:52:43 PM
    Author     : Korisnik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="//cdn.datatables.net/plug-ins/1.10.15/pagination/input.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/DataTables-1.10.15/extensions/Plugins/integration/jqueryui/dataTables.jqueryui.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/DataTables-1.10.15/extensions/Plugins/integration/jqueryui/dataTables.jqueryui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-ui-themes-1.12.1/themes/blitzer/jquery-ui.css">
<script type="text/javascript">
    $(document).ready(function () {
        $("#igraciTabelarno").DataTable({
            "order": [],
            "pageLength": 30,
            "columns": [
                {"title": "Igrač"},
                {"title": "Tim"},
                {"title": "Visina"},
                {"title": "Težina"},
                {"title": "Pozicija"},
                {"title": "Iskustvo"},
                {"title": "Koledž"},
                {"title": "Datum i mesto rođenja"},
                {"title": "Draft"}
            ],
            "pagingType": "input",
            "language": {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Serbian.json"
            },
            "ajax": "./ajax/prikazIgracaTabela"
        });
    });
</script>