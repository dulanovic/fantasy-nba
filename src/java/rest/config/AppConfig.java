package rest.config;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import rest.api.FantasyLigaREST;
import rest.api.FantasyTimREST;
import rest.api.IgraciREST;
import rest.api.KorisnikREST;
import rest.api.TimoviREST;

@ApplicationPath("/rest_api")
public class AppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(MoxyJsonFeature.class);
        resources.add(TimoviREST.class);
        resources.add(IgraciREST.class);
        resources.add(FantasyLigaREST.class);
        resources.add(FantasyTimREST.class);
        resources.add(KorisnikREST.class);
        return resources;
    }

}
