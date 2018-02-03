package applicationcontroller;

import action.AbstractAction;
import action.ActionFactory;
import javax.servlet.http.HttpServletRequest;

public class ApplicationController {

    private static ApplicationController instance;

    private ApplicationController() {
    }

    public static ApplicationController getInstance() {
        if (instance == null) {
            instance = new ApplicationController();
        }
        return instance;
    }

    public String processRequest(String actionInput, HttpServletRequest request) {
        AbstractAction action = ActionFactory.createAction(actionInput);
        return action.execute(request);
    }

}
