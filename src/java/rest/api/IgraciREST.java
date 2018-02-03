package rest.api;

import db.DatabaseBroker;
import domen.Igrac;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.RestOdgovor;
import util.Util;

@Path("/igraci")
public class IgraciREST {

    @GET
    @Path("/svi_igraci.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiSveIgrace(@PathParam("format") String format) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        List<Igrac> listaIgraca = DatabaseBroker.getInstance().vratiSveIgrace("");
        GenericEntity<List<Igrac>> ge = new GenericEntity<List<Igrac>>(listaIgraca) {
        };
        return Response.ok(ge, zahtevaniFormat).status(Response.Status.OK).build();
    }

    @GET
    @Path("/igrac/{igrac}.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiIgraca(@PathParam("format") String format, @PathParam("igrac") int igrac) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        Igrac i = DatabaseBroker.getInstance().vratiIgraca(igrac);
        if (i == null) {
            RestOdgovor ro = new RestOdgovor("Traženi igrač ne postoji!");
            return Response.ok(ro, zahtevaniFormat).build();
        }
        return Response.ok(i, zahtevaniFormat).status(Response.Status.OK).build();
    }

    @GET
    @Path("/pretraga.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiIgracePoPoziciji(@PathParam("format") String format, @QueryParam("pozicija") String pozicija) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        if (!pozicija.equalsIgnoreCase("pg") && !pozicija.equalsIgnoreCase("sg") && !pozicija.equalsIgnoreCase("sf") && !pozicija.equalsIgnoreCase("pf") && !pozicija.equalsIgnoreCase("")) {
            RestOdgovor ro = new RestOdgovor("Nepropisan unos pozicije igrača!");
            return Response.ok(ro, zahtevaniFormat).build();
        }
        List<Igrac> listaIgracaPoPoziciji = DatabaseBroker.getInstance().vratiSveIgrace(pozicija, "svi");
        GenericEntity<List<Igrac>> ge = new GenericEntity<List<Igrac>>(listaIgracaPoPoziciji) {
        };
        return Response.ok(ge, zahtevaniFormat).status(Response.Status.OK).build();
    }

}
