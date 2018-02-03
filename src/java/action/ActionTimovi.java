package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Konferencija;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ActionTimovi extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        List<Konferencija> lista = DatabaseBroker.getInstance().vratiKonferencije();
        request.setAttribute(WebConstants.NBA_KONFERENCIJE, lista);
        return WebConstants.PAGE_NBA_TIMOVI;
    }

}
