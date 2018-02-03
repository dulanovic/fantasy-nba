package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Korisnik;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionLogin extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            Korisnik k = DatabaseBroker.getInstance().login(request.getParameter("korisnickoIme"), request.getParameter("korisnickaSifra"));
            HttpSession session = request.getSession(true);
            session.setAttribute(WebConstants.USER_LOGIN, k);
            return WebConstants.PAGE_INDEX;
        } catch (Exception ex) {
            ex.printStackTrace();
            return WebConstants.PAGE_LOGIN;
        }
    }

}
