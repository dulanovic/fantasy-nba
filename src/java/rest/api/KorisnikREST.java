package rest.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.WebConstants;
import db.DatabaseBroker;
import domen.Korisnik;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.RestOdgovor;
import util.Util;

@Path("/korisnici")
public class KorisnikREST {

    @GET
    @Path("/svi_korisnici.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiSveKorisnike(@PathParam("format") String format) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        List<Korisnik> listaKorisnika = DatabaseBroker.getInstance().vratiSveKorisnike();
        GenericEntity<List<Korisnik>> ge = new GenericEntity<List<Korisnik>>(listaKorisnika) {
        };
        return Response.ok(ge, zahtevaniFormat).status(Response.Status.OK).build();
    }

    @GET
    @Path("/korisnik/{korisnik}.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiKorisnika(@PathParam("korisnik") int korisnik, @PathParam("format") String format) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        Korisnik k = DatabaseBroker.getInstance().vratiKorisnika(korisnik);
        if (k == null) {
            RestOdgovor ro = new RestOdgovor("Traženi korisnik ne postoji!");
            return Response.ok(ro, zahtevaniFormat).build();
        }
        return Response.ok(k, zahtevaniFormat).status(Response.Status.OK).build();
    }

    @POST
    @Path("/unos.json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sacuvajKorisnika(String podaci) throws IOException, ParseException {
        ObjectMapper om = new ObjectMapper();
        JsonNode podaciJson = om.readValue(podaci, JsonNode.class);
        if (podaciJson.get("ime") == null || podaciJson.get("prezime") == null || podaciJson.get("datumRodjenja") == null || podaciJson.get("email") == null || podaciJson.get("korisnickoIme") == null || podaciJson.get("korisnickaSifra") == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        String ime = podaciJson.get("ime").asText();
        String prezime = podaciJson.get("prezime").asText();
        Date datumRodjenja = WebConstants.sdf.parse(podaciJson.get("datumRodjenja").asText());
        String email = podaciJson.get("email").asText();
        String korisnickoIme = podaciJson.get("korisnickoIme").asText();
        String korisnickaSifra = podaciJson.get("korisnickaSifra").asText();
        Korisnik k = new Korisnik(ime, prezime, datumRodjenja, email, korisnickoIme, korisnickaSifra);
        String porukaValidacija = Util.validirajKorisnika(k);
        if (porukaValidacija.length() != 0) {
            RestOdgovor ro = new RestOdgovor(porukaValidacija);
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().sacuvajKorisnika(k);
        RestOdgovor ro = new RestOdgovor("Korisnik je uspešno sačuvan.");
        return Response.ok(ro).build();
    }

    @POST
    @Path("/unos.xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response sacuvajKorisnika(Korisnik k) {
        if (k.getIme() == null || k.getPrezime() == null || k.getDatumRodjenja() == null || k.getEmail() == null || k.getKorisnickoIme() == null || k.getKorisnickaSifra() == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        String porukaValidacija = Util.validirajKorisnika(k);
        if (porukaValidacija.length() != 0) {
            RestOdgovor ro = new RestOdgovor(porukaValidacija);
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().sacuvajKorisnika(k);
        RestOdgovor ro = new RestOdgovor("Korisnik je uspešno sačuvan.");
        return Response.ok(ro).build();
    }

    @PUT
    @Path("/izmeni/{korisnik}.json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response izmeniKorisnika(@PathParam("korisnik") int korisnik, String podaci, @QueryParam("korisnickoIme") String korisnickoIme, @QueryParam("korisnickaSifra") String korisnickaSifra) throws IOException, ParseException {
        ObjectMapper om = new ObjectMapper();
        JsonNode podaciJson = om.readValue(podaci, JsonNode.class);
        if (podaciJson.get("ime") == null || podaciJson.get("prezime") == null || podaciJson.get("datumRodjenja") == null || podaciJson.get("email") == null || podaciJson.get("korisnickoIme") == null || podaciJson.get("korisnickaSifra") == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        if (!DatabaseBroker.getInstance().postojiLiKorisnik(korisnik)) {
            RestOdgovor ro = new RestOdgovor("Prosleđeni korisnik nije pronađen!");
            return Response.ok(ro).build();
        }
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        if (k.getKorisnikId() != korisnik) {
            RestOdgovor ro = new RestOdgovor("Nemate ovlašćenja da menjate tuđe podatke!");
            return Response.ok(ro).build();
        }
        String ime = podaciJson.get("ime").asText();
        String prezime = podaciJson.get("prezime").asText();
        Date datumRodjenja = WebConstants.sdf.parse(podaciJson.get("datumRodjenja").asText());
        String email = podaciJson.get("email").asText();
        String korisnickoImeNovo = podaciJson.get("korisnickoIme").asText();
        String korisnickaSifraNova = podaciJson.get("korisnickaSifra").asText();
        Korisnik kNovi = new Korisnik(k.getKorisnikId(), ime, prezime, datumRodjenja, email, korisnickoImeNovo, korisnickaSifraNova);
        String porukaValidacija = Util.validirajKorisnika(kNovi);
        if (porukaValidacija.length() != 0) {
            RestOdgovor ro = new RestOdgovor(porukaValidacija);
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().izmeniKorisnika(kNovi);
        RestOdgovor ro = new RestOdgovor("Izmene su uspešno sačuvane.");
        return Response.ok(ro).build();
    }

    @PUT
    @Path("/izmeni/{korisnik}.xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response izmeniKorisnika(@PathParam("korisnik") int korisnik, Korisnik k, @QueryParam("korisnickoIme") String korisnickoIme, @QueryParam("korisnickaSifra") String korisnickaSifra) {
        if (k.getIme() == null || k.getPrezime() == null || k.getDatumRodjenja() == null || k.getEmail() == null || k.getKorisnickoIme() == null || k.getKorisnickaSifra() == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        if (!DatabaseBroker.getInstance().postojiLiKorisnik(korisnik)) {
            RestOdgovor ro = new RestOdgovor("Prosleđeni korisnik nije pronađen!");
            return Response.ok(ro).build();
        }
        Korisnik kNovi = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (kNovi == null || kNovi.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        if (kNovi.getKorisnikId() != korisnik) {
            RestOdgovor ro = new RestOdgovor("Nemate ovlašćenja da menjate tuđe podatke!");
            return Response.ok(ro).build();
        }
        k.setKorisnikId(kNovi.getKorisnikId());
        String porukaValidacija = Util.validirajKorisnika(k);
        if (porukaValidacija.length() != 0) {
            RestOdgovor ro = new RestOdgovor(porukaValidacija);
            return Response.ok(ro).build();
        }
        k.setKorisnikId(kNovi.getKorisnikId());
        DatabaseBroker.getInstance().izmeniKorisnika(k);
        RestOdgovor ro = new RestOdgovor("Izmene su uspešno sačuvane.");
        return Response.ok(ro).build();
    }

    @DELETE
    @Path("/obrisi/{korisnik}.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response obrisiKorisnika(@PathParam("korisnik") int korisnik, @PathParam("format") String format, @QueryParam("korisnickoIme") String korisnickoIme, @QueryParam("korisnickaSifra") String korisnickaSifra) {
        String izlazniFormat = Util.vratiIzlazniFormat(format);
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro, izlazniFormat).build();
        }
        if (k.getKorisnikId() != korisnik) {
            RestOdgovor ro = new RestOdgovor("Nemate ovlašćenja da brišete tuđi nalog!");
            return Response.ok(ro, izlazniFormat).build();
        }
        DatabaseBroker.getInstance().obrisiKorisnika(korisnik);
        RestOdgovor ro = new RestOdgovor("Korisnik je uspešno obrisan!");
        return Response.ok(ro, izlazniFormat).build();
    }

}
