package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.Tim;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ActionUtakmice extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {

        Date trenutniDatum = DatabaseBroker.getInstance().vratiDatumZadnjegAzuriranja();
        List<Tim> listaTimova = DatabaseBroker.getInstance().vratiSveTimove();
        request.setAttribute(WebConstants.TRENUTNI_DATUM, trenutniDatum);
        request.setAttribute(WebConstants.ALL_TEAMS, listaTimova);

        return WebConstants.PAGE_NBA_UTAKMICE;
    }

}
