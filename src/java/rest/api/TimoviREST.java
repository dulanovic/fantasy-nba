package rest.api;

import db.DatabaseBroker;
import domen.Igrac;
import domen.Tim;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.RestOdgovor;
import util.Util;

@Path("/timovi")
public class TimoviREST {

    @GET
    @Path("/svi_timovi.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiSveTimove(@PathParam("format") String format) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        List<Tim> listaTimova = DatabaseBroker.getInstance().vratiSveTimove();
        GenericEntity<List<Tim>> ge = new GenericEntity<List<Tim>>(listaTimova) {
        };
        return Response.ok(ge, zahtevaniFormat).status(Response.Status.OK).build();
    }

    @GET
    @Path("/tim/{tim}.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiTim(@PathParam("format") String format, @PathParam("tim") int tim) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        Tim t = DatabaseBroker.getInstance().vratiTim(tim);
        if (t == null) {
            RestOdgovor ro = new RestOdgovor("Traženi tim nije pronađen!");
            return Response.ok(ro, zahtevaniFormat).build();
        }
        return Response.ok(t, zahtevaniFormat).status(Response.Status.OK).build();
    }

    @GET
    @Path("/tim/{tim}/sastav.{format}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response vratiSastavTima(@PathParam("format") String format, @PathParam("tim") String tim) {
        String zahtevaniFormat = Util.vratiIzlazniFormat(format);
        if (DatabaseBroker.getInstance().vratiTim(Integer.valueOf(tim)) == null) {
            RestOdgovor ro = new RestOdgovor("Traženi tim nije pronađen!");
            return Response.ok(ro, zahtevaniFormat).build();
        }
        List<Igrac> listaIgracaTima = DatabaseBroker.getInstance().vratiSveIgrace("svi", tim);
        GenericEntity<List<Igrac>> ge = new GenericEntity<List<Igrac>>(listaIgracaTima) {
        };
        return Response.ok(ge, zahtevaniFormat).status(Response.Status.OK).build();
    }

}
