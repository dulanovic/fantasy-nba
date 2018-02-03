package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Korisnik;
import javax.servlet.http.HttpServletRequest;
import util.Util;

public class ActionNovaLiga extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        String nazivLige = request.getParameter("nazivLige");
        if (Util.validacijaFantasyLiga(nazivLige)) {
            Korisnik k = (Korisnik) request.getSession().getAttribute(WebConstants.USER_LOGIN);
            DatabaseBroker.getInstance().sacuvajLigu(nazivLige, k);
        }
        return new ActionFantasy().execute(request);
    }

}
