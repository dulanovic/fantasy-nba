package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Igrac;
import javax.servlet.http.HttpServletRequest;

public class ActionIgrac extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {

        int igrac = Integer.valueOf(request.getParameter("igrac"));
        Igrac i = DatabaseBroker.getInstance().vratiIgraca(igrac);
        Igrac i_ = DatabaseBroker.getInstance().vratiStatistikuIgraca(igrac);
        request.setAttribute(WebConstants.NBA_IGRAC, i);
        request.setAttribute(WebConstants.NBA_IGRAC_STATISTIKA, i_);

        return WebConstants.PAGE_IGRAC;
    }

}
