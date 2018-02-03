<%-- 
    Document   : registracija_content
    Created on : May 22, 2017, 5:57:32 PM
    Author     : Korisnik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form action="./fantasy" method="POST" onsubmit="">
    <table style="margin-top: 20px; margin-bottom: 10px;">
        <tbody>
            <tr>
                <td class="pitanje">Ime:</td>
                <td><input type="text" name="ime" value="<c:choose><c:when test="${user_login != null}">${user_login.ime}</c:when><c:when test="${user_login == null}">${forma_vrednost_ime}</c:when></c:choose>" autofocus onkeyup=""></td>
                <td><p id="validacija-ime" class="validacija">${validacija_ime}</p></td>
            </tr>
            <tr>
                <td class="pitanje">Prezime:</td>
                <td><input type="text" name="prezime" value="<c:choose><c:when test="${user_login != null}">${user_login.prezime}</c:when><c:when test="${user_login == null}">${forma_vrednost_prezime}</c:when></c:choose>" onkeyup=""></td>
                <td><p id="validacija-prezime" class="validacija">${validacija_prezime}</p></td>
            </tr>
            <tr>
                <td class="pitanje">Datum rođenja:</td>
                <td><input type="text" name="datumRodjenja" value="<c:choose><c:when test="${user_login != null}">${user_login.datumRodjenjaString}</c:when><c:when test="${user_login == null}">${forma_vrednost_datum_rodjenja}</c:when></c:choose>" id="datepicker" onchange=""></td>
                <td><p id="validacija-datumRodjenja" class="validacija">${validacija_datum_rodjenja}</p></td>
            </tr>
            <tr>
                <td class="pitanje">E-mail:</td>
                <td><input type="text" name="email" value="<c:choose><c:when test="${user_login != null}">${user_login.email}</c:when><c:when test="${user_login == null}">${forma_vrednost_email}</c:when></c:choose>" onkeyup=""></td>
                <td><p id="validacija-email" class="validacija">${validacija_email}</p></td>
            </tr>
            <tr>
                <td class="pitanje">Korisničko ime:</td>
                <td><input type="text" name="korisnickoIme" value="<c:choose><c:when test="${user_login != null}">${user_login.korisnickoIme}</c:when><c:when test="${user_login == null}">${forma_vrednost_korisnicko_ime}</c:when></c:choose>" onkeyup=""></td>
                <td><p id="validacija-korisnickoIme" class="validacija">${validacija_korisnicko_ime}</p></td>
            </tr>
            <tr>
                <td class="pitanje">Korisnička šifra:</td>
                <td><input type="password" name="korisnickaSifra" id="korisnickaSifra" value=""></td>
                <td><p id="validacija-korisnickaSifra" class="validacija">${validacija_korisnicka_sifra}</p></td>
            </tr>
            <tr>
                <td class="pitanje">Ponovite šifru:</td>
                <td><input type="password" name="korisnickaSifraPonovljena" value="" onkeyup=""></td>
                <td></td>
            </tr>
            <tr>
                <c:choose>
                    <c:when test="${user_login != null}">
                        <td>
                            <input type="submit" value="Izmeni podatke" class="ui-button ui-widget ui-corner-all">
                        </td>
                    </c:when>
                    <c:when test="${user_login == null}">
                        <td>
                            <input type="submit" value="Registruj se" class="ui-button ui-widget ui-corner-all">
                        </td>
                    </c:when>
                </c:choose>
                <c:if test="${user_login == null}">
                    <td>
                        <input type="reset" value="Resetuj polja" class="ui-button ui-widget ui-corner-all">
                    </td>
                </c:if>
            </tr>
        </tbody>
    </table>
    <c:choose>
        <c:when test="${user_login == null}">
            <input type="hidden" name="action" value="registracijaKorisnika">
        </c:when>
        <c:when test="${user_login != null}">
            <input type="hidden" name="action" value="izmenaKorisnika">
        </c:when>
    </c:choose>
</form><br />
<c:if test="${user_login != null}">
    <a href="./fantasy?action=obrisiKorisnika&korisnik=${user_login.korisnikId}" class="ui-button ui-widget ui-corner-all" onclick="return potvrdiBrisanje()">Obriši nalog</a>
</c:if>