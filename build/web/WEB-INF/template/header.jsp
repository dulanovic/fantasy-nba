<%-- 
    Document   : header
    Created on : May 22, 2017, 4:36:13 AM
    Author     : Korisnik
--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="header">
    <div id="logo">
        <div id="logo_text">
            <h1><a href="./index.jsp"><span class="logo_no_colour">Fantasy</span><span class="logo_colour">NBA</span></a></h1>
            <h2>Online igra na temu NBA lige</h2>
        </div>
        <c:choose>
            <c:when test = "${user_login == null}">
                <div id="login_form">
                    <h4>Prijava</h4>
                    <form action="./fantasy" method="POST">
                        <table id="table-login">
                            <tr>
                                <td>Korisničko ime:<td>
                            </tr>
                            <tr>
                                <td colspan="2"><input type="text" name="korisnickoIme" size="20"></td>
                            </tr>
                            <tr>
                                <td>Korisnička šifra:</td>
                            </tr>
                            <tr>
                                <td colspan="2"><input type="password" name="korisnickaSifra" size="20"></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Prijavi se" class="ui-button ui-widget ui-corner-all"></td>
                                <td><a href="./fantasy?action=registracija">Registracija</a></td>
                            </tr>
                        </table>
                        <input type="hidden" name="action" value="login">
                    </form>
                </div>
            </c:when>
            <c:when test = "${user_login != null}">
                <div id="ulogovanKorisnik">
                    <h4>Ulogovani korisnik:</h4>
                    <table id="table-login">
                        <tr>
                            <td><a href="./fantasy?action=izmenaKorisnik">${user_login.ime} ${user_login.prezime}</a> <span style="float: right; color: #FFFFFF;">(<a href="./fantasy?action=logout">Logout</a>)</span></td>
                        </tr>
                    </table>
                </div>
            </c:when>
        </c:choose>
    </div>
    <div id="menubar">
        <ul id="nav-menu">
            <li><a href="./fantasy?action=timovi">Timovi</a></li>
            <li><a href="./fantasy?action=igraci">Igrači</a></li>
            <li><a href="./fantasy?action=tabela">Tabela</a></li>
            <li><a href="./fantasy?action=utakmice">Utakmice</a></li>
            <li><a href="./fantasy?action=fantasy">Fantasy</a></li>
                <c:if test = "${user_login.tip.tipNaziv == 'Administrator'}">
                <li><a href="./fantasy?action=admin">Admin</a><br /></li>
                </c:if>
        </ul>
    </div>
</div>