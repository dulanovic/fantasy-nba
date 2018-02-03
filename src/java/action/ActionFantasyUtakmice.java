package action;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyUtakmica;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import util.Util;

public class ActionFantasyUtakmice extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {

        int liga = Integer.valueOf(request.getParameter("liga"));
        List<FantasyUtakmica> listaUtakmica = DatabaseBroker.getInstance().vratiUtakmiceLige(liga);
        Date datumAzuriranjaLige = DatabaseBroker.getInstance().vratiDatumZadnjegAzuriranjaLige(liga);
        String ispisTabela = Util.generisiTabeluFantasyUtakmica(listaUtakmica);
        request.setAttribute(WebConstants.DATUM_AZURIRANJA_LIGE, WebConstants.sdf.format(datumAzuriranjaLige));
        request.setAttribute(WebConstants.ISPIS_TABELA_UTAKMICA, ispisTabela);

        return WebConstants.PAGE_FANTASY_UTAKMICE;
    }

}
