package action;

import db.DatabaseBroker;
import javax.servlet.http.HttpServletRequest;

public class ActionObrisiLigu extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        int liga = Integer.valueOf(request.getParameter("liga"));
        DatabaseBroker.getInstance().obrisiLigu(liga);
        return new ActionFantasy().execute(request);
    }

}
