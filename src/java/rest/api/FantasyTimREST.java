package rest.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.DatabaseBroker;
import domen.FantasyIgrac;
import domen.FantasyLiga;
import domen.FantasyTim;
import domen.Igrac;
import domen.Korisnik;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.RestZahtev;
import util.RestOdgovor;
import util.Util;

@Path("/fantasy_tim")
public class FantasyTimREST {

    @POST
    @Path("/unos/{liga}.json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sacuvajTim(@PathParam("liga") int liga, String podaci) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode podaciJson = om.readValue(podaci, JsonNode.class);
        if (podaciJson.get("korisnickoIme") == null || podaciJson.get("korisnickaSifra") == null || podaciJson.get("parametar") == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        String korisnickoIme = podaciJson.get("korisnickoIme").asText();
        String korisnickaSifra = podaciJson.get("korisnickaSifra").asText();
        String fantasyTimNaziv = podaciJson.get("parametar").asText();
        FantasyLiga fl = DatabaseBroker.getInstance().vratiLigu(liga);
        if (fl == null) {
            RestOdgovor ro = new RestOdgovor("Liga koju ste prosledili ne postoji!");
            return Response.ok(ro).build();
        }
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        if (fl.getFantasyTimList().size() >= 4) {
            RestOdgovor ro = new RestOdgovor("Liga koju ste prosledili je popunjena!");
            return Response.ok(ro).build();
        }
        for (FantasyTim ft : k.getFantasyTimList()) {
            if (ft.getLiga().equals(fl)) {
                RestOdgovor ro = new RestOdgovor("Imate registrovan tim u prosleđenoj ligi!");
                return Response.ok(ro).build();
            }
        }
        if (fantasyTimNaziv.length() < 5) {
            RestOdgovor ro = new RestOdgovor("Naziv tima koji ste prosledili je prekratak, mora imati bar 5 karaktera!");
            return Response.ok(ro).build();
        }
        if (!Util.validacijaFantasyTim(fantasyTimNaziv)) {
            RestOdgovor ro = new RestOdgovor("Naziv tima koji ste prosledili je zauzet!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = new FantasyTim(fantasyTimNaziv, 0, fl, k);
        DatabaseBroker.getInstance().sacuvajTim(ft);
        RestOdgovor ro = new RestOdgovor("Tim je uspešno kreiran.");
        return Response.status(Response.Status.OK).entity(ro).build();
    }

    @POST
    @Path("/unos/{liga}.xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response sacuvajTim(@PathParam("liga") int liga, RestZahtev zahtev) {
        if (zahtev.getKorisnickoIme() == null || zahtev.getKorisnickaSifra() == null || zahtev.getParametar() == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        FantasyLiga fl = DatabaseBroker.getInstance().vratiLigu(liga);
        if (fl == null) {
            RestOdgovor ro = new RestOdgovor("Liga koju ste prosledili ne postoji!");
            return Response.ok(ro).build();
        }
        Korisnik k = DatabaseBroker.getInstance().login(zahtev.getKorisnickoIme(), zahtev.getKorisnickaSifra());
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        if (fl.getFantasyTimList().size() >= 4) {
            RestOdgovor ro = new RestOdgovor("Liga koju ste prosledili je popunjena!");
            return Response.ok(ro).build();
        }
        for (FantasyTim ft : k.getFantasyTimList()) {
            if (ft.getLiga().equals(fl)) {
                RestOdgovor ro = new RestOdgovor("Imate registrovan tim u prosleđenoj ligi!");
                return Response.ok(ro).build();
            }
        }
        if (zahtev.getParametar().length() < 5) {
            RestOdgovor ro = new RestOdgovor("Naziv tima koji ste prosledili je prekratak, mora imati bar 5 karaktera!");
            return Response.ok(ro).build();
        }
        if (!Util.validacijaFantasyTim(zahtev.getParametar())) {
            RestOdgovor ro = new RestOdgovor("Naziv tima koji ste prosledili je zauzet!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = new FantasyTim(zahtev.getParametar(), 0, fl, k);
        DatabaseBroker.getInstance().sacuvajTim(ft);
        RestOdgovor ro = new RestOdgovor("Tim je uspešno kreiran.");
        return Response.status(Response.Status.OK).entity(ro).build();
    }

    @PUT
    @Path("/{tim}/naklupu.json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response igracNaKlupu(@PathParam("tim") int tim, String podaci) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode podaciJson = om.readValue(podaci, JsonNode.class);
        if (podaciJson.get("korisnickoIme") == null || podaciJson.get("korisnickaSifra") == null || podaciJson.get("parametar") == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        String korisnickoIme = podaciJson.get("korisnickoIme").asText();
        String korisnickaSifra = podaciJson.get("korisnickaSifra").asText();
        int fantasyIgrac = podaciJson.get("parametar").asInt();
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(tim, k);
        if (ft == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen Vaš tim!");
            return Response.ok(ro).build();
        }
        if (!ft.getVlasnik().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili nije član Vašeg tima!");
            return Response.ok(ro).build();
        }
        FantasyIgrac fi = DatabaseBroker.getInstance().vratiFantasyIgraca(fantasyIgrac);
        if (fi == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen igrač koga ste prosledili!");
            return Response.ok(ro).build();
        }
        if (!ft.getFantasyIgracList().contains(fi)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili nije član tima koji ste prosledili!");
            return Response.ok(ro).build();
        }
        if (fi.getStatus().getStatusIgracaNaziv().equals("Rezerva")) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili je već rezerva!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().staviNaKlupu(fantasyIgrac);
        RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili je uspešno prebačen u rezerve!");
        return Response.ok(ro).build();
    }

    @PUT
    @Path("/{tim}/naklupu.xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response igracNaKlupu(@PathParam("tim") int tim, RestZahtev zahtev) {
        if (zahtev.getKorisnickoIme() == null || zahtev.getKorisnickaSifra() == null || zahtev.getParametar() == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        Korisnik k = DatabaseBroker.getInstance().login(zahtev.getKorisnickoIme(), zahtev.getKorisnickaSifra());
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(tim, k);
        if (ft == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen Vaš tim!");
            return Response.ok(ro).build();
        }
        if (!ft.getVlasnik().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili nije član Vašeg tima!");
            return Response.ok(ro).build();
        }
        FantasyIgrac fi = DatabaseBroker.getInstance().vratiFantasyIgraca(Integer.valueOf(zahtev.getParametar()));
        if (fi == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen igrač koga ste prosledili!");
            return Response.ok(ro).build();
        }
        if (!ft.getFantasyIgracList().contains(fi)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili nije član tima koji ste prosledili!");
            return Response.ok(ro).build();
        }
        if (fi.getStatus().getStatusIgracaNaziv().equals("Rezerva")) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili je već rezerva!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().staviNaKlupu(Integer.valueOf(zahtev.getParametar()));
        RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili je uspešno prebačen u rezerve!");
        return Response.ok(ro).build();
    }

    @PUT
    @Path("/{tim}/aktiviraj.json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response aktivacijaIgraca(@PathParam("tim") int tim, String podaci) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode podaciJson = om.readValue(podaci, JsonNode.class);
        if (podaciJson.get("korisnickoIme") == null || podaciJson.get("korisnickaSifra") == null || podaciJson.get("parametar") == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        String korisnickoIme = podaciJson.get("korisnickoIme").asText();
        String korisnickaSifra = podaciJson.get("korisnickaSifra").asText();
        int fantasyIgrac = podaciJson.get("parametar").asInt();
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(tim, k);
        if (ft == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen Vaš tim!");
            return Response.ok(ro).build();
        }
        if (!ft.getVlasnik().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili nije član Vašeg tima!");
            return Response.ok(ro).build();
        }
        FantasyIgrac fi = DatabaseBroker.getInstance().vratiFantasyIgraca(fantasyIgrac);
        if (fi == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen igrač koga ste prosledili!");
            return Response.ok(ro).build();
        }
        if (!ft.getFantasyIgracList().contains(fi)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili nije član tima koji ste prosledili!");
            return Response.ok(ro).build();
        }
        if (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili je već aktivan!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().aktivirajIgraca(fantasyIgrac);
        RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili je uspešno aktiviran!");
        return Response.ok(ro).build();
    }

    @PUT
    @Path("/{tim}/aktiviraj.xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response aktivacijaIgraca(@PathParam("tim") int tim, RestZahtev zahtev) {
        if (zahtev.getKorisnickoIme() == null || zahtev.getKorisnickaSifra() == null || zahtev.getParametar() == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        Korisnik k = DatabaseBroker.getInstance().login(zahtev.getKorisnickoIme(), zahtev.getKorisnickaSifra());
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(tim, k);
        if (ft == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen Vaš tim!");
            return Response.ok(ro).build();
        }
        if (!ft.getVlasnik().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili nije član Vašeg tima!");
            return Response.ok(ro).build();
        }
        FantasyIgrac fi = DatabaseBroker.getInstance().vratiFantasyIgraca(Integer.valueOf(zahtev.getParametar()));
        if (fi == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen igrač koga ste prosledili!");
            return Response.ok(ro).build();
        }
        if (!ft.getFantasyIgracList().contains(fi)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili nije član tima koji ste prosledili!");
            return Response.ok(ro).build();
        }
        if (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili je već aktivan!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().aktivirajIgraca(Integer.valueOf(zahtev.getParametar()));
        RestOdgovor ro = new RestOdgovor("Igrač kojeg ste prosledili je uspešno aktiviran!");
        return Response.ok(ro).build();
    }

    @PUT
    @Path("/{tim}/angazuj.json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response angazujIgraca(@PathParam("tim") int tim, String podaci) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode podaciJson = om.readValue(podaci, JsonNode.class);
        if (podaciJson.get("korisnickoIme") == null || podaciJson.get("korisnickaSifra") == null || podaciJson.get("parametar") == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        String korisnickoIme = podaciJson.get("korisnickoIme").asText();
        String korisnickaSifra = podaciJson.get("korisnickaSifra").asText();
        int igrac = podaciJson.get("parametar").asInt();
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(tim, k);
        if (ft == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen Vaš tim!");
            return Response.ok(ro).build();
        }
        if (!ft.getVlasnik().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Niste vlasnik tima koji ste prosledili!");
            return Response.ok(ro).build();
        }
        Igrac i = DatabaseBroker.getInstance().vratiIgraca(igrac);
        if (i == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen traženi igrač!");
            return Response.ok(ro).build();
        }
        List<Igrac> listaDostupnihIgraca = DatabaseBroker.getInstance().vratiDostupneIgrace(ft.getLiga().getLigaId(), "svi", "svi", false);
        if (!listaDostupnihIgraca.contains(i)) {
            RestOdgovor ro = new RestOdgovor("Traženi igrač je već angažovan u Vašoj ligi!");
            return Response.ok(ro).build();
        }
        if (!ft.getValidator().mozeNoviIgrac(i.getPozicija())) {
            RestOdgovor ro = new RestOdgovor("Ne možete angažovati ovog igrača zbog ograničenja u strukturi tima!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().sacuvajAngazman(tim, igrac);
        RestOdgovor ro = new RestOdgovor("Angažman je uspešno sačuvan!");
        return Response.ok(ro).build();
    }

    @PUT
    @Path("/{tim}/angazuj.xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response angazujIgraca(@PathParam("tim") int tim, RestZahtev zahtev) {
        if (zahtev.getKorisnickoIme() == null || zahtev.getKorisnickaSifra() == null || zahtev.getParametar() == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        Korisnik k = DatabaseBroker.getInstance().login(zahtev.getKorisnickoIme(), zahtev.getKorisnickaSifra());
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(tim, k);
        if (ft == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen Vaš tim!");
            return Response.ok(ro).build();
        }
        if (!ft.getVlasnik().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Niste vlasnik tima koji ste prosledili!");
            return Response.ok(ro).build();
        }
        Igrac i = DatabaseBroker.getInstance().vratiIgraca(Integer.valueOf(zahtev.getParametar()));
        if (i == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen traženi igrač!");
            return Response.ok(ro).build();
        }
        List<Igrac> listaDostupnihIgraca = DatabaseBroker.getInstance().vratiDostupneIgrace(ft.getLiga().getLigaId(), "svi", "svi", false);
        if (!listaDostupnihIgraca.contains(i)) {
            RestOdgovor ro = new RestOdgovor("Traženi igrač je već angažovan u Vašoj ligi!");
            return Response.ok(ro).build();
        }
        if (!ft.getValidator().mozeNoviIgrac(i.getPozicija())) {
            RestOdgovor ro = new RestOdgovor("Ne možete angažovati ovog igrača zbog ograničenja u strukturi tima!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().sacuvajAngazman(tim, Integer.valueOf(zahtev.getParametar()));
        RestOdgovor ro = new RestOdgovor("Angažman je uspešno sačuvan!");
        return Response.ok(ro).build();
    }

    @PUT
    @Path("/{tim}/otpusti.json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response otpustiIgraca(@PathParam("tim") int tim, String podaci) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode podaciJson = om.readValue(podaci, JsonNode.class);
        if (podaciJson.get("korisnickoIme") == null || podaciJson.get("korisnickaSifra") == null || podaciJson.get("parametar") == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        String korisnickoIme = podaciJson.get("korisnickoIme").asText();
        String korisnickaSifra = podaciJson.get("korisnickaSifra").asText();
        int igrac = podaciJson.get("parametar").asInt();
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(tim, k);
        if (ft == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen Vaš tim!");
            return Response.ok(ro).build();
        }
        if (!ft.getVlasnik().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Niste vlasnik tima koji ste prosledili!");
            return Response.ok(ro).build();
        }
        FantasyIgrac fi = DatabaseBroker.getInstance().vratiFantasyIgraca(igrac);
        if (fi == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen traženi igrač!");
            return Response.ok(ro).build();
        }
        if (!ft.getFantasyIgracList().contains(fi)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg želite da otpustite nije član Vašeg tima!");
            return Response.ok(ro).build();
        }
        if (!ft.getValidator().mozeOtkaz(fi.getIgrac().getPozicija())) {
            RestOdgovor ro = new RestOdgovor("Ne možete otpustiti ovog igrača zbog ograničenja u strukturi tima!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().otpustiIgraca(igrac);
        RestOdgovor ro = new RestOdgovor("Igrač je uspešno otpušten!");
        return Response.ok(ro).build();
    }

    @PUT
    @Path("/{tim}/otpusti.xml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response otpustiIgraca(@PathParam("tim") int tim, RestZahtev zahtev) {
        if (zahtev.getKorisnickoIme() == null || zahtev.getKorisnickaSifra() == null || zahtev.getParametar() == null) {
            RestOdgovor ro = new RestOdgovor("Niste prosledili neophodne parametre!!!");
            return Response.ok(ro).build();
        }
        Korisnik k = DatabaseBroker.getInstance().login(zahtev.getKorisnickoIme(), zahtev.getKorisnickaSifra());
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro).build();
        }
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(tim, k);
        if (ft == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen Vaš tim!");
            return Response.ok(ro).build();
        }
        if (!ft.getVlasnik().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Niste vlasnik tima koji ste prosledili!");
            return Response.ok(ro).build();
        }
        FantasyIgrac fi = DatabaseBroker.getInstance().vratiFantasyIgraca(Integer.valueOf(zahtev.getParametar()));
        if (fi == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen traženi igrač!");
            return Response.ok(ro).build();
        }
        if (!ft.getFantasyIgracList().contains(fi)) {
            RestOdgovor ro = new RestOdgovor("Igrač kojeg želite da otpustite nije član Vašeg tima!");
            return Response.ok(ro).build();
        }
        if (!ft.getValidator().mozeOtkaz(fi.getIgrac().getPozicija())) {
            RestOdgovor ro = new RestOdgovor("Ne možete otpustiti ovog igrača zbog ograničenja u strukturi tima!");
            return Response.ok(ro).build();
        }
        DatabaseBroker.getInstance().otpustiIgraca(Integer.valueOf(zahtev.getParametar()));
        RestOdgovor ro = new RestOdgovor("Igrač je uspešno otpušten!");
        return Response.ok(ro).build();
    }

    @DELETE
    @Path("/obrisi/{tim}.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response obrisiTim(@PathParam("tim") int tim, @PathParam("format") String format, @QueryParam("korisnickoIme") String korisnickoIme, @QueryParam("korisnickaSifra") String korisnickaSifra) throws IOException {
        String izlazniFormat = Util.vratiIzlazniFormat(format);
        Korisnik k = DatabaseBroker.getInstance().login(korisnickoIme, korisnickaSifra);
        if (k == null || k.getTip().getTipNaziv().equals("Administrator")) {
            RestOdgovor ro = new RestOdgovor("Korisnik sa prosleđenim kredencijalima nije pronađen!");
            return Response.ok(ro, izlazniFormat).build();
        }
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(tim, k);
        if (ft == null) {
            RestOdgovor ro = new RestOdgovor("Nije pronađen Vaš tim!");
            return Response.ok(ro, izlazniFormat).build();
        }
        if (!ft.getVlasnik().equals(k)) {
            RestOdgovor ro = new RestOdgovor("Niste vlasnik tima koji ste prosledili!");
            return Response.ok(ro, izlazniFormat).build();
        }
        DatabaseBroker.getInstance().obrisiTim(tim);
        RestOdgovor ro = new RestOdgovor("Tim je uspešno obrisan!");
        return Response.ok(ro, izlazniFormat).build();
    }

}
