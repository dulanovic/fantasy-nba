package action;

import db.DatabaseBroker;
import javax.servlet.http.HttpServletRequest;

public class ActionObrisiTim extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        int tim = Integer.valueOf(request.getParameter("tim"));
        DatabaseBroker.getInstance().obrisiTim(tim);
        return new ActionFantasy().execute(request);
    }

}
