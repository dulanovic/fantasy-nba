package rest.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.DatabaseBroker;
import domen.FantasyIgrac;
import domen.FantasyLiga;
import domen.FantasyTim;
import domen.Korisnik;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.RestZahtev;
import util.RestOdgovor;
import util.Util;

@Path("/fantasy_liga")
public class FantasyLigaREST {

    @GET
    @Path("/sve_lige.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiSvefantasyLige(@PathParam("format") String format) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        List<FantasyLiga> listaFantasyLiga = DatabaseBroker.getInstance().vratiSveLige();
        GenericEntity<List<FantasyLiga>> ge = new GenericEntity<List<FantasyLiga>>(listaFantasyLiga) {
        };
        return Response.ok(ge, zahtevaniFormat).status(Response.Status.OK).build();
    }

    @GET
    @Path("/liga/{liga}.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiTimoveLige(@PathParam("format") String format, @PathParam("liga") int liga) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        if (DatabaseBroker.getInstance().vratiLigu(liga) == null) {
            RestOdgovor ro = new RestOdgovor("Tražena liga ne postoji!");
            return Response.ok(ro, zahtevaniFormat).build();
        }
        List<FantasyTim> listaFantasyTimova = DatabaseBroker.getInstance().vratiSveTimoveLige(liga);
        GenericEntity<List<FantasyTim>> ge = new GenericEntity<List<FantasyTim>>(listaFantasyTimova) {
        };
        return Response.ok(ge, zahtevaniFormat).status(Response.Status.OK).build();
    }

    @GET
    @Path("/tim/{tim}.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiSastavTima(@PathParam("format") String format, @PathParam("tim") int tim) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        if (!DatabaseBroker.getInstance().postojiLiFantasyTim(tim)) {
            RestOdgovor ro = new RestOdgovor("Traženi tim ne postoji!");
            return Response.ok(ro, zahtevaniFormat).build();
        }
        List<FantasyIgrac> listaFantasyIgraca = DatabaseBroker.getInstance().vratiSastavFantasyTima(tim);
        GenericEntity<List<FantasyIgrac>> ge = new GenericEntity<List<FantasyIgrac>>(listaFantasyIgraca) {
        };
        return Response.ok(ge, zahtevaniFormat).status(Response.Status.OK).build();
    }

    @POST
    @Path("/unos.json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sacuvajLigu(String podaci) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode podaciJson = om.readValue(podaci, JsonNode.class);
        if (podaciJson.get("korisnickoIme") == null || podaciJson.get("korisnickaSifra") == null || podaciJson.get("parametar") == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        String korisnickoIme = podaciJson.get("korisnickoIme").asText();
        String korisnickaSifra = podaciJson.get("korisnickaSifra").asText();
        String fantasyLigaNaziv = podaciJson.get("parametar").asText();
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        if (k.getFantasyLigaList().size() >= 3) {
            RestOdgovor ro = new RestOdgovor("Nije dozvoljeno administrirati više od 3 lige!");
            return Response.ok(ro).build();
        }
        if (fantasyLigaNaziv.length() < 5) {
            RestOdgovor ro = new RestOdgovor("Naziv lige koji ste prosledili je prekratak, mora imati bar 5 karaktera!");
            return Response.ok(ro).build();
        }
        if (!Util.validacijaFantasyLiga(fantasyLigaNaziv)) {
            RestOdgovor ro = new RestOdgovor("Naziv lige koji ste prosledili je zauzet!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().sacuvajLigu(fantasyLigaNaziv, k);
        RestOdgovor ro = new RestOdgovor("Liga je uspešno kreirana.");
        return Response.status(Response.Status.OK).entity(ro).build();
    }

    @POST
    @Path("/unos.xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response sacuvajLigu(RestZahtev zahtev) {
        if (zahtev.getKorisnickoIme() == null || zahtev.getKorisnickaSifra() == null || zahtev.getParametar() == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        Korisnik k = DatabaseBroker.getInstance().login(zahtev.getKorisnickoIme(), zahtev.getKorisnickaSifra());
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        if (k.getFantasyLigaList().size() >= 3) {
            RestOdgovor ro = new RestOdgovor("Nije dozvoljeno administrirati više od 3 lige!");
            return Response.ok(ro).build();
        }
        if (zahtev.getParametar().length() < 5) {
            RestOdgovor ro = new RestOdgovor("Naziv lige koji ste prosledili je prekratak, mora imati bar 5 karaktera!");
            return Response.ok(ro).build();
        }
        if (!Util.validacijaFantasyLiga(zahtev.getParametar())) {
            RestOdgovor ro = new RestOdgovor("Naziv lige koji ste prosledili je zauzet!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().sacuvajLigu(zahtev.getParametar(), k);
        RestOdgovor ro = new RestOdgovor("Liga je uspešno kreirana.");
        return Response.status(Response.Status.OK).entity(ro).build();
    }

    @DELETE
    @Path("/obrisi/{liga}.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response obrisiLigu(@PathParam("liga") int liga, @PathParam("format") String format, @QueryParam("korisnickoIme") String korisnickoIme, @QueryParam("korisnickaSifra") String korisnickaSifra) throws IOException {
        String izlazniFormat = Util.vratiIzlazniFormat(format);
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro, izlazniFormat).build();
        }
        FantasyLiga fl = DatabaseBroker.getInstance().vratiLigu(liga);
        if (fl == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađena Vaša liga!");
            return Response.ok(ro, izlazniFormat).build();
        }
        if (!fl.getAdministrator().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Niste administrator lige koju ste prosledili!");
            return Response.ok(ro, izlazniFormat).build();
        }
        DatabaseBroker.getInstance().obrisiLigu(liga);
        RestOdgovor ro = new RestOdgovor("Liga je uspešno obrisana!");
        return Response.ok(ro, izlazniFormat).build();
    }

}
