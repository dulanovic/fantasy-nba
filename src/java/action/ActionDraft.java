package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyTim;
import domen.Korisnik;
import domen.Tim;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ActionDraft extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {

        int ligaId = Integer.valueOf(request.getParameter("liga"));
        Korisnik k = (Korisnik) request.getSession().getAttribute(WebConstants.USER_LOGIN);
        FantasyTim ft = DatabaseBroker.getInstance().vratiTimKorisnikaIzLige(ligaId, k);
        if (ft == null) {
            request.setAttribute(WebConstants.PORUKA_NEDOZVOLJEN_PRISTUP, "Nemate tim u ovoj ligi i ne možete prisustvovati draftu.");
            return WebConstants.PAGE_ERROR;
        }
        List<Tim> listaTimova = DatabaseBroker.getInstance().vratiSveTimove();
        request.setAttribute(WebConstants.ALL_TEAMS, listaTimova);
        request.setAttribute(WebConstants.FANTASY_TIM, ft);

        return WebConstants.PAGE_DRAFT;
    }

}
