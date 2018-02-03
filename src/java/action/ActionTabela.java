package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Tim;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ActionTabela extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {

        Map<String, List<Tim>> mapaTabele = DatabaseBroker.getInstance().generisiTabelu();
        request.setAttribute(WebConstants.TABELA_ISTOK, mapaTabele.get("Istok"));
        request.setAttribute(WebConstants.TABELA_ZAPAD, mapaTabele.get("Zapad"));
        request.setAttribute(WebConstants.TABELA_ATLANTIK, mapaTabele.get("Atlantik"));
        request.setAttribute(WebConstants.TABELA_CENTRAL, mapaTabele.get("Central"));
        request.setAttribute(WebConstants.TABELA_JUGOISTOK, mapaTabele.get("Jugoistok"));
        request.setAttribute(WebConstants.TABELA_PACIFIK, mapaTabele.get("Pacifik"));
        request.setAttribute(WebConstants.TABELA_JUGOZAPAD, mapaTabele.get("Jugozapad"));
        request.setAttribute(WebConstants.TABELA_SEVEROZAPAD, mapaTabele.get("Severozapad"));

        return WebConstants.PAGE_STANDINGS;
    }

}
