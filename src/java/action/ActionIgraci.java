package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Tim;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ActionIgraci extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        List<Tim> lista = DatabaseBroker.getInstance().vratiSveTimove();
        request.setAttribute(WebConstants.ALL_TEAMS, lista);
        return WebConstants.PAGE_IGRACI;
    }

}
