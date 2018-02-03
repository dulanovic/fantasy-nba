package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyTim;
import domen.Korisnik;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionFantasyTim extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {

        int fantasyTim = Integer.valueOf(request.getParameter("tim"));
        HttpSession session = request.getSession();
        Korisnik k = (Korisnik) session.getAttribute(WebConstants.USER_LOGIN);
        FantasyTim ft = DatabaseBroker.getInstance().vratiFantasyTim(fantasyTim, k);
        request.setAttribute(WebConstants.FANTASY_TIM, ft);

        return WebConstants.PAGE_FANTASY_TIM;
    }

}
