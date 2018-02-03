package resolver;

import constants.WebConstants;

public class PageResolver {

    public static String getPage(String view) {
        String page = "index.jsp";
        switch (view) {
            case WebConstants.PAGE_INDEX:
                page = "/index.jsp";
                break;
            case WebConstants.PAGE_NBA_TIMOVI:
                page = "/WEB-INF/jsp/timovi.jsp";
                break;
            case WebConstants.PAGE_IGRACI_TIMA:
                page = "/WEB-INF/jsp/igraci.jsp";
                break;
            case WebConstants.PAGE_STANDINGS:
                page = "/WEB-INF/jsp/tabela.jsp";
                break;
            case WebConstants.PAGE_REGISTRACIJA:
                page = "/WEB-INF/jsp/registracija.jsp";
                break;
            case WebConstants.PAGE_ADMIN:
                page = "/WEB-INF/jsp/admin.jsp";
                break;
            case WebConstants.PAGE_ERROR:
                page = "/WEB-INF/jsp/error.jsp";
                break;
            case WebConstants.PAGE_FANTASY:
                page = "/WEB-INF/jsp/fantasy.jsp";
                break;
            case WebConstants.PAGE_FANTASY_LIGA:
                page = "/WEB-INF/jsp/fantasy_liga.jsp";
                break;
            case WebConstants.PAGE_DRAFT:
                page = "/WEB-INF/jsp/draft.jsp";
                break;
            case WebConstants.PAGE_FANTASY_TIM:
                page = "/WEB-INF/jsp/fantasy_tim.jsp";
                break;
            case WebConstants.PAGE_TRZISTE_IGRACA:
                page = "/WEB-INF/jsp/trziste.jsp";
                break;
            case WebConstants.PAGE_IGRACI:
                page = "/WEB-INF/jsp/igraci.jsp";
                break;
            case WebConstants.PAGE_IGRAC:
                page = "/WEB-INF/jsp/igrac.jsp";
                break;
            case WebConstants.PAGE_TIM:
                page = "/WEB-INF/jsp/tim.jsp";
                break;
            case WebConstants.PAGE_NBA_UTAKMICE:
                page = "/WEB-INF/jsp/utakmice.jsp";
                break;
            case WebConstants.PAGE_NBA_UTAKMICA:
                page = "/WEB-INF/jsp/utakmica.jsp";
                break;
            case WebConstants.PAGE_FANTASY_UTAKMICE:
                page = "/WEB-INF/jsp/fantasy_utakmice.jsp";
                break;
            case WebConstants.PAGE_IGRACI_TABELA:
                page = "/WEB-INF/jsp/igraci_tabela.jsp";
                break;
        }
        return page;
    }

}
