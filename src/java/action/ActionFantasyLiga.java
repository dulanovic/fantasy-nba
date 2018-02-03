package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyLiga;
import javax.servlet.http.HttpServletRequest;

public class ActionFantasyLiga extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        int ligaId = Integer.valueOf(request.getParameter("liga"));
        FantasyLiga fl = DatabaseBroker.getInstance().vratiLigu(ligaId);
        request.setAttribute(WebConstants.FANTASY_LIGA, fl);
        return WebConstants.PAGE_FANTASY_LIGA;
    }

}
