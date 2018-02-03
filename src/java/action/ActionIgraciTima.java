package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Igrac;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ActionIgraciTima extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        try {
            int timId = Integer.valueOf(request.getParameter("tim"));
            List<Igrac> lista = DatabaseBroker.getInstance().vratiIgraceTima(timId);
            if (!lista.isEmpty()) {
                request.setAttribute(WebConstants.PLAYERS_BY_TEAM, lista);
                return WebConstants.PAGE_IGRACI_TIMA;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
