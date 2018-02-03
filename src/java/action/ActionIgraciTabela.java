package action;

import constants.WebConstants;
import javax.servlet.http.HttpServletRequest;

public class ActionIgraciTabela extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        return WebConstants.PAGE_IGRACI_TABELA;
    }

}
