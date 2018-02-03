package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyLiga;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import rest.klijent.KlijentREST;

public class ActionFantasy extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        //List<FantasyLiga> listaFantasyLiga = DatabaseBroker.getInstance().vratiSveLige();
        List<FantasyLiga> listaFantasyLiga = new KlijentREST().vratiFantasyLige();
        request.setAttribute(WebConstants.ALL_LEAGUES, listaFantasyLiga);
        return WebConstants.PAGE_FANTASY;
    }

}
