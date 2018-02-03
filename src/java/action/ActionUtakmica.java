package action;

import constants.WebConstants;
import db.DatabaseBroker;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ActionUtakmica extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {

        int utakmica = Integer.valueOf(request.getParameter("utakmica"));
        Map<String, Object> mapaStatistike = DatabaseBroker.getInstance().vratiStatistikuUtakmice(utakmica);
        request.setAttribute(WebConstants.NBA_UTAKMICA, mapaStatistike.get("utakmica"));
        request.setAttribute(WebConstants.UCINAK_DOMACIN, mapaStatistike.get("ucinakDomacin"));
        request.setAttribute(WebConstants.UCINAK_GOST, mapaStatistike.get("ucinakGost"));
        request.setAttribute(WebConstants.UCINAK_IGRACA_DOMACIN, mapaStatistike.get("ucinakIgracaDomacin"));
        request.setAttribute(WebConstants.UCINAK_IGRACA_GOST, mapaStatistike.get("ucinakIgracaGost"));

        return WebConstants.PAGE_NBA_UTAKMICA;
    }

}
