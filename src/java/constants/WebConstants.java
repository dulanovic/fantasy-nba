package constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WebConstants {

    public static final String PAGE_INDEX = "page_index";
    public static final String PAGE_LOGIN = "page_login";
    public static final String PAGE_NBA_TIMOVI = "page_nba_timovi";
    public static final String PAGE_IGRACI_TIMA = "page_igraci_tima";
    public static final String PAGE_STANDINGS = "page_standings";
    public static final String PAGE_REGISTRACIJA = "page_registracija";
    public static final String PAGE_ADMIN = "page_admin";
    public static final String PAGE_ERROR = "page_error";
    public static final String PAGE_FANTASY = "page_fantasy";
    public static final String PAGE_FANTASY_LIGA = "page_fantasy_liga";
    public static final String PAGE_DRAFT = "page_draft";
    public static final String PAGE_FANTASY_TIM = "page_fantasy_tim";
    public static final String PAGE_TRZISTE_IGRACA = "page_trziste_igraca";
    public static final String PAGE_IGRACI = "page_igraci";
    public static final String PAGE_IGRACI_TABELA = "page_igraci_tabela";
    public static final String PAGE_IGRAC = "page_igrac";
    public static final String PAGE_TIM = "page_tim";
    public static final String PAGE_NBA_UTAKMICE = "page_nba_utakmice";
    public static final String PAGE_NBA_UTAKMICA = "page_nba_utakmica";
    public static final String PAGE_FANTASY_UTAKMICE = "page_fantasy_utakmice";

    public static final String USER_LOGIN = "user_login";
    public static final String ALL_TEAMS = "all_teams";
    public static final String PLAYERS_BY_TEAM = "players_by_team";
    public static final String ALL_TEAMS_STANDINGS = "teams_standings";
    public static final String ALL_LEAGUES = "all_leagues";
    public static final String FANTASY_LIGA = "fantasy_liga";
    public static final String FANTASY_TIM = "fantasy_tim";
    public static final String FANTASY_TEAMS_BY_LEAGUE = "fantasy_teams_by_league";
    public static final String DATUM_ZADNJEG_AZURIRANJA = "datum_zadnjeg_azuriranja";
    public static final String TRZISTE_IGRACI = "trziste_igraci";
    public static final String NBA_IGRAC = "nba_igrac";
    public static final String NBA_IGRAC_STATISTIKA = "nba_igrac_statistika";
    public static final String NBA_KONFERENCIJE = "nba_konferencije";
    public static final String NBA_TIM = "nba_tim";
    public static final String NBA_TIM_STATISTIKA = "nba_tim_statistika";
    public static final String TRENUTNI_DATUM = "trenutni_datum";
    public static final String LISTA_UTAKMICA = "lista_utakmica";
    public static final String NBA_UTAKMICA = "nba_utakmica";
    public static final String UCINAK_DOMACIN = "ucinak_domacin";
    public static final String UCINAK_GOST = "ucinak_gost";
    public static final String UCINAK_IGRACA_DOMACIN = "ucinak_igraca_domacin";
    public static final String UCINAK_IGRACA_GOST = "ucinak_igraca_gost";
    public static final String LISTA_FANTASY_UTAKMICA = "lista_fantasy_utakmica";
    public static final String DATUM_AZURIRANJA_LIGE = "datum_azuriranja_lige";
    public static final String ISPIS_TABELA_UTAKMICA = "ispis_tabela_utakmica";
    public static final String TABELA_ISTOK = "tabela_istok";
    public static final String TABELA_ZAPAD = "tabela_zapad";
    public static final String TABELA_ATLANTIK = "tabela_atlantik";
    public static final String TABELA_CENTRAL = "tabela_central";
    public static final String TABELA_JUGOISTOK = "tabela_jugoistok";
    public static final String TABELA_PACIFIK = "tabela_pacifik";
    public static final String TABELA_JUGOZAPAD = "tabela_jugozapad";
    public static final String TABELA_SEVEROZAPAD = "tabela_severozapad";
    public static final String FORMA_NBA_TIM = "forma_nba_tim";
    public static final String BOJA_POBEDA = "#008800";
    public static final String BOJA_PORAZ = "#FF0000";

    public static final String VALIDACIJA_NAZIV_LIGE = "validacija_naziv_lige";
    public static final String VALIDACIJA_NAZIV_TIMA = "validacija_naziv_tima";
    public static final String VALIDACIJA_IME = "validacija_ime";
    public static final String VALIDACIJA_PREZIME = "validacija_prezime";
    public static final String VALIDACIJA_DATUM_RODJENJA = "validacija_datum_rodjenja";
    public static final String VALIDACIJA_EMAIL = "validacija_email";
    public static final String VALIDACIJA_KORISNICKO_IME = "validacija_korisnicko_ime";
    public static final String VALIDACIJA_KORISNICKA_SIFRA = "validacija_korisnicka_sifra";
    public static final String PORUKA_NEDOZVOLJEN_PRISTUP = "poruka_nedozvoljen_pristup";

    public static final String FORMA_VREDNOST_IME = "forma_vrednost_ime";
    public static final String FORMA_VREDNOST_PREZIME = "forma_vrednost_prezime";
    public static final String FORMA_VREDNOST_DATUM_RODJENJA = "forma_vrednost_datum_rodjenja";
    public static final String FORMA_VREDNOST_EMAIL = "forma_vrednost_email";
    public static final String FORMA_VREDNOST_KORISNICKO_IME = "forma_vrednost_korisnicko_ime";

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Date vratiSistemskiDatum() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.set(2017, 1, 22);
        return c.getTime();
    }

    public static Date vratiDatumPocetkaIgre() throws ParseException {
        return sdf.parse("2017-02-23");
    }

    public static Date vratiDatumPocetkaPlejofa() throws ParseException {
        return sdf.parse("2017-02-23");
    }

    public static Date vratiDatumZavrsetkaIgre() throws ParseException {
        return sdf.parse("2017-04-12");
    }

}
