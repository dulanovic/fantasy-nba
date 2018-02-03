package action;

public class ActionFactory {

    public static AbstractAction createAction(String actionInput) {
        AbstractAction action = null;
        if (actionInput.equals("login")) {
            action = new ActionLogin();
        }
        if (actionInput.equals("timovi")) {
            action = new ActionTimovi();
        }
        if (actionInput.equals("igraci")) {
            action = new ActionIgraciTima();
        }
        if (actionInput.equals("logout")) {
            action = new ActionLogout();
        }
        if (actionInput.equals("tabela")) {
            action = new ActionTabela();
        }
        if (actionInput.equals("registracija")) {
            action = new ActionRegistracija();
        }
        if (actionInput.equals("registracijaKorisnika")) {
            action = new ActionRegistracijaKorisnika();
        }
        if (actionInput.equals("admin")) {
            action = new ActionAdmin();
        }
        if (actionInput.equals("fantasy")) {
            action = new ActionFantasy();
        }
        if (actionInput.equals("izmenaKorisnik")) {
            action = new ActionIzmenaKorisnik();
        }
        if (actionInput.equals("izmenaKorisnika")) {
            action = new ActionIzmenaKorisnika();
        }
        if (actionInput.equals("novaLiga")) {
            action = new ActionNovaLiga();
        }
        if (actionInput.equals("fantasyLiga")) {
            action = new ActionFantasyLiga();
        }
        if (actionInput.equals("noviTim")) {
            action = new ActionNoviTim();
        }
        if (actionInput.equals("draft")) {
            action = new ActionDraft();
        }
        if (actionInput.equals("fantasyTim")) {
            action = new ActionFantasyTim();
        }
        if (actionInput.equals("trziste")) {
            action = new ActionTrziste();
        }
        if (actionInput.equals("igraci")) {
            action = new ActionIgraci();
        }
        if (actionInput.equals("igrac")) {
            action = new ActionIgrac();
        }
        if (actionInput.equals("tim")) {
            action = new ActionTim();
        }
        if (actionInput.equals("utakmice")) {
            action = new ActionUtakmice();
        }
        if (actionInput.equals("utakmica")) {
            action = new ActionUtakmica();
        }
        if (actionInput.equals("fantasyUtakmice")) {
            action = new ActionFantasyUtakmice();
        }
        if (actionInput.equals("obrisiLigu")) {
            action = new ActionObrisiLigu();
        }
        if (actionInput.equals("obrisiTim")) {
            action = new ActionObrisiTim();
        }
        if (actionInput.equals("obrisiKorisnika")) {
            action = new ActionObrisiKorisnika();
        }
        if (actionInput.equals("igraciTabela")) {
            action = new ActionIgraciTabela();
        }
        return action;
    }

}
