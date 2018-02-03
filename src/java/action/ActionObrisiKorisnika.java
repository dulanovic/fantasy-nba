package action;

import db.DatabaseBroker;
import javax.servlet.http.HttpServletRequest;

public class ActionObrisiKorisnika extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        int korisnik = Integer.valueOf(request.getParameter("korisnik"));
        DatabaseBroker.getInstance().obrisiKorisnika(korisnik);
        return new ActionLogout().execute(request);
    }

}
