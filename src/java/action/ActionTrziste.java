package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyTim;
import domen.Igrac;
import domen.Korisnik;
import domen.Tim;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import util.PozicijaValidator;

public class ActionTrziste extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {

        int liga = Integer.valueOf(request.getParameter("liga"));
        List<Igrac> lista = DatabaseBroker.getInstance().vratiDostupneIgrace(liga, "svi", "svi", false);
        Korisnik k = (Korisnik) request.getSession().getAttribute(WebConstants.USER_LOGIN);
        FantasyTim ft = DatabaseBroker.getInstance().vratiTimKorisnikaIzLige(liga, k);
        List<Tim> listaTimova = DatabaseBroker.getInstance().vratiSveTimove();
        ft.setValidator(new PozicijaValidator(ft));
        request.setAttribute(WebConstants.TRZISTE_IGRACI, lista);
        request.setAttribute(WebConstants.FANTASY_TIM, ft);
        request.setAttribute(WebConstants.ALL_TEAMS, listaTimova);

        return WebConstants.PAGE_TRZISTE_IGRACA;
    }

}
