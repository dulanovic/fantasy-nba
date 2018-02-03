package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Korisnik;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import util.Util;

public class ActionRegistracijaKorisnika extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            if (validacija(request)) {
                String ime = request.getParameter("ime");
                String prezime = request.getParameter("prezime");
                Date datumRodjenja = WebConstants.sdf.parse(request.getParameter("datumRodjenja"));
                String email = request.getParameter("email");
                String korisnickoIme = request.getParameter("korisnickoIme");
                String korisnickaSifra = request.getParameter("korisnickaSifra");

                Korisnik k = new Korisnik(ime, prezime, datumRodjenja, email, korisnickoIme, korisnickaSifra);
                DatabaseBroker.getInstance().sacuvajKorisnika(k);

                return new ActionLogin().execute(request);
            } else {
                return WebConstants.PAGE_REGISTRACIJA;
            }
        } catch (ParseException pex) {
            pex.printStackTrace();
            return WebConstants.PAGE_REGISTRACIJA;
        }
    }

    private boolean validacija(HttpServletRequest request) throws ParseException {
        boolean imaGreska = false;

        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String email = request.getParameter("email");
        String korisnickoIme = request.getParameter("korisnickoIme");
        String korisnickaSifra = request.getParameter("korisnickaSifra");
        String korisnickaSifraPonovljena = request.getParameter("korisnickaSifraPonovljena");

        if (ime.equals("")) {
            request.setAttribute(WebConstants.VALIDACIJA_IME, "Niste uneli ime!");
            imaGreska = true;
        }
        request.setAttribute(WebConstants.FORMA_VREDNOST_IME, ime);
        if (prezime.equals("")) {
            request.setAttribute(WebConstants.VALIDACIJA_PREZIME, "Niste uneli prezime!");
            imaGreska = true;
        }
        request.setAttribute(WebConstants.FORMA_VREDNOST_PREZIME, prezime);
        if (request.getParameter("datumRodjenja").equals("")) {
            request.setAttribute(WebConstants.VALIDACIJA_DATUM_RODJENJA, "Niste uneli datum rođenja!");
            imaGreska = true;
        }
        try {
            Date datumRodjenja = WebConstants.sdf.parse(request.getParameter("datumRodjenja"));
            if (Util.izracunajBrojGodina(datumRodjenja) < 18) {
                request.setAttribute(WebConstants.VALIDACIJA_DATUM_RODJENJA, "Morate biti stariji od 18 godina!");
                imaGreska = true;
                request.setAttribute(WebConstants.FORMA_VREDNOST_DATUM_RODJENJA, datumRodjenja);
            }
        } catch (ParseException pex) {
            pex.printStackTrace();
            imaGreska = true;
        }
        request.setAttribute(WebConstants.FORMA_VREDNOST_DATUM_RODJENJA, request.getParameter("datumRodjenja"));
        if (email.equals("")) {
            request.setAttribute(WebConstants.VALIDACIJA_EMAIL, "Niste uneli e-mail!");
            imaGreska = true;
        } else {
            if (!Util.proveriEmail(email)) {
                request.setAttribute(WebConstants.VALIDACIJA_EMAIL, "E-mail nije u korektnom formatu!");
                imaGreska = true;
            }
        }
        request.setAttribute(WebConstants.FORMA_VREDNOST_EMAIL, email);
        if (korisnickoIme.equals("")) {
            request.setAttribute(WebConstants.VALIDACIJA_KORISNICKO_IME, "Niste uneli korisničko ime!");
            imaGreska = true;
        } else if (!DatabaseBroker.getInstance().zauzetoKorisnickoIme(korisnickoIme)) {
            request.setAttribute(WebConstants.VALIDACIJA_KORISNICKO_IME, "Korisničko ime je zauzeto!");
            imaGreska = true;
        } else {
            if (!Util.proveriKorisnickoIme(korisnickoIme)) {
                request.setAttribute(WebConstants.VALIDACIJA_KORISNICKO_IME, "Nepropisno korisničko ime!");
                imaGreska = true;
            }
        }
        request.setAttribute(WebConstants.FORMA_VREDNOST_KORISNICKO_IME, korisnickoIme);
        if (korisnickaSifra.equals("")) {
            request.setAttribute(WebConstants.VALIDACIJA_KORISNICKA_SIFRA, "Niste uneli šifru!");
            imaGreska = true;
        } else if (!Util.proveriSifre(korisnickaSifra, korisnickaSifraPonovljena)) {
            request.setAttribute(WebConstants.VALIDACIJA_KORISNICKA_SIFRA, "Unete šifre nisu iste!");
            imaGreska = true;
        } else {
            if (!Util.proveriKorisnickuSifru(korisnickaSifra)) {
                request.setAttribute(WebConstants.VALIDACIJA_KORISNICKA_SIFRA, "Šifra nije u korektnom formatu!");
                imaGreska = true;
            }
        }
        return (!imaGreska);
    }

}
