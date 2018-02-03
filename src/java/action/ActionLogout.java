package action;

import constants.WebConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionLogout extends AbstractAction {

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return WebConstants.PAGE_INDEX;
    }

}
