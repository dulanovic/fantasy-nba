package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Tim;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import util.FormaTim;
import util.Util;

public class ActionTim extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {

        int tim = Integer.valueOf(request.getParameter("tim"));
        Tim t = DatabaseBroker.getInstance().vratiTim(tim);
        Tim t_ = DatabaseBroker.getInstance().vratiStatistikuTima(tim);
        List<FormaTim> listaForma = Util.vratiFormuTima(t);
        request.setAttribute(WebConstants.FORMA_NBA_TIM, listaForma);
        request.setAttribute(WebConstants.NBA_TIM, t);
        request.setAttribute(WebConstants.NBA_TIM_STATISTIKA, t_);

        return WebConstants.PAGE_TIM;
    }

}
