package rest.klijent;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyLiga;
import domen.Korisnik;
import domen.StatusLige;
import domen.TipKorisnika;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class KlijentREST {

    private static final String REST_URI = "http://localhost:8080/FantasyNBA/rest_api/fantasy_liga/sve_lige.json";
    private Client c;
    private WebTarget wt;
    Invocation.Builder b;
    private ObjectMapper om;

    public KlijentREST() {
        this.c = ClientBuilder.newClient();
        this.wt = c.target(REST_URI);
        this.b = wt.request(MediaType.APPLICATION_JSON);
        this.om = new ObjectMapper();
    }

    public List<FantasyLiga> vratiFantasyLige() {
        try {
            String jsonOdgovor = b.get(String.class);
            JsonNode nizFantasyLiga = om.readTree(jsonOdgovor);
            List<FantasyLiga> listaFantasyLiga = new ArrayList<>();
            for (int i = 0; i < nizFantasyLiga.size(); i++) {
                JsonNode cvorLiga = nizFantasyLiga.get(i);
                JsonNode cvorAdministrator = cvorLiga.get("administrator");
                JsonNode cvorStatusLige = cvorLiga.get("status");

                int statusLigeId = cvorStatusLige.get("statusLigeId").asInt();
                String statusLigeNaziv = cvorStatusLige.get("statusLigeNaziv").asText();
                StatusLige sl = new StatusLige(statusLigeId, statusLigeNaziv);

                int korisnikId = cvorAdministrator.get("korisnikId").asInt();
                String ime = cvorAdministrator.get("ime").asText();
                String prezime = cvorAdministrator.get("prezime").asText();
                Date datumRodjenja = WebConstants.sdf.parse(cvorAdministrator.get("datumRodjenja").asText());
                String email = cvorAdministrator.get("email").asText();
                String korisnickoIme = cvorAdministrator.get("korisnickoIme").asText();
                String korisnickaSifra = cvorAdministrator.get("korisnickaSifra").asText();
                JsonNode cvorTipKorisnika = cvorAdministrator.get("tip");
                int tipKorisnikaId = cvorTipKorisnika.get("tipKorisnikaId").asInt();
                String tipNaziv = cvorTipKorisnika.get("tipNaziv").asText();
                TipKorisnika tk = new TipKorisnika(tipKorisnikaId, tipNaziv);
                Korisnik k = new Korisnik(korisnikId, ime, prezime, datumRodjenja, email, korisnickoIme, korisnickaSifra);

                int ligaId = cvorLiga.get("ligaId").asInt();
                String ligaNaziv = cvorLiga.get("ligaNaziv").asText();
                Date datumAzuriranja = WebConstants.sdf.parse(cvorLiga.get("datumAzuriranja").asText());
                FantasyLiga fl = new FantasyLiga(ligaId, ligaNaziv, datumAzuriranja, sl, k);
                long brojTimovaLige = DatabaseBroker.getInstance().vratiBrojTimovaLige(ligaId);
                fl.setBrojTimova(brojTimovaLige);
                listaFantasyLiga.add(fl);
            }

            return listaFantasyLiga;
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
