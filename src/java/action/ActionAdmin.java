package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyLiga;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ActionAdmin extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        Date datumZadnjegAzuriranja = DatabaseBroker.getInstance().vratiDatumZadnjegAzuriranja();
        request.setAttribute(WebConstants.DATUM_ZADNJEG_AZURIRANJA, WebConstants.sdf.format(datumZadnjegAzuriranja));
        List<FantasyLiga> lista = DatabaseBroker.getInstance().vratiSveLige();
        request.setAttribute(WebConstants.ALL_LEAGUES, lista);
        return WebConstants.PAGE_ADMIN;
    }

}
