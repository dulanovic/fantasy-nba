package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyLiga;
import domen.FantasyTim;
import domen.Korisnik;
import javax.servlet.http.HttpServletRequest;
import util.Util;

public class ActionNoviTim extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        String nazivTima = request.getParameter("nazivTima");
        if (Util.validacijaFantasyTim(nazivTima)) {
            int ligaId = Integer.valueOf(request.getParameter("liga"));
            FantasyLiga fl = DatabaseBroker.getInstance().vratiLigu(ligaId);
            request.setAttribute(WebConstants.FANTASY_LIGA, fl);
            Korisnik k = (Korisnik) request.getSession().getAttribute(WebConstants.USER_LOGIN);
            int brojPoena = 0;
            FantasyTim ft = new FantasyTim(nazivTima, brojPoena, fl, k);
            DatabaseBroker.getInstance().sacuvajTim(ft);
        }
        return new ActionFantasyLiga().execute(request);
    }

}
