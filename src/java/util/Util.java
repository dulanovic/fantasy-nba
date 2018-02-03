package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyIgrac;
import domen.FantasyTim;
import domen.FantasyUtakmica;
import domen.Igrac;
import domen.Korisnik;
import domen.Tim;
import domen.Utakmica;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.core.MediaType;

public class Util {

    public static List<FantasyUtakmica> generisiRaspored(List<FantasyTim> listaTimova) throws ParseException {
        List<FantasyUtakmica> listaUtakmica = new ArrayList<>();

        Calendar datumPocetka = Calendar.getInstance();
        datumPocetka.setTime(WebConstants.vratiDatumPocetkaIgre());

        FantasyUtakmica fu0 = new FantasyUtakmica(1, datumPocetka.getTime(), listaTimova.get(0), listaTimova.get(3));
        FantasyUtakmica fu1 = new FantasyUtakmica(1, datumPocetka.getTime(), listaTimova.get(1), listaTimova.get(2));
        datumPocetka.add(Calendar.DAY_OF_MONTH, 6);
        fu0.setDatumKraj(datumPocetka.getTime());
        fu1.setDatumKraj(datumPocetka.getTime());
        listaUtakmica.add(fu0);
        listaUtakmica.add(fu1);

        datumPocetka.add(Calendar.DAY_OF_MONTH, 1);
        FantasyUtakmica fu2 = new FantasyUtakmica(2, datumPocetka.getTime(), listaTimova.get(0), listaTimova.get(2));
        FantasyUtakmica fu3 = new FantasyUtakmica(2, datumPocetka.getTime(), listaTimova.get(1), listaTimova.get(3));
        datumPocetka.add(Calendar.DAY_OF_MONTH, 6);
        fu2.setDatumKraj(datumPocetka.getTime());
        fu3.setDatumKraj(datumPocetka.getTime());
        listaUtakmica.add(fu2);
        listaUtakmica.add(fu3);

        datumPocetka.add(Calendar.DAY_OF_MONTH, 1);
        FantasyUtakmica fu4 = new FantasyUtakmica(3, datumPocetka.getTime(), listaTimova.get(0), listaTimova.get(1));
        FantasyUtakmica fu5 = new FantasyUtakmica(3, datumPocetka.getTime(), listaTimova.get(2), listaTimova.get(3));
        datumPocetka.add(Calendar.DAY_OF_MONTH, 6);
        fu4.setDatumKraj(datumPocetka.getTime());
        fu5.setDatumKraj(datumPocetka.getTime());
        listaUtakmica.add(fu4);
        listaUtakmica.add(fu5);

        int brojac = 0;
        List<FantasyUtakmica> listaRevans = new ArrayList<>();
        for (FantasyUtakmica fu : listaUtakmica) {
            if (brojac % 2 == 0) {
                datumPocetka.add(Calendar.DAY_OF_MONTH, 1);
            }
            int kolo = fu.getKolo() + 3;
            FantasyTim domacin = fu.getGost();
            FantasyTim gost = fu.getDomacin();
            FantasyUtakmica ut = new FantasyUtakmica(kolo, datumPocetka.getTime(), domacin, gost);
            datumPocetka.add(Calendar.DAY_OF_MONTH, 6);
            ut.setDatumKraj(datumPocetka.getTime());
            if (brojac % 2 == 0) {
                datumPocetka.add(Calendar.DAY_OF_MONTH, -6);
            }
            listaRevans.add(ut);
            brojac++;
        }
        listaUtakmica.addAll(listaRevans);

        return listaUtakmica;
    }

    public static boolean validacijaFantasyLiga(String nazivLige) {
        return DatabaseBroker.getInstance().zauzetNazivLige(nazivLige);
    }

    public static boolean validacijaFantasyTim(String nazivTima) {
        return DatabaseBroker.getInstance().zauzetNazivTima(nazivTima);
    }

    public static boolean validanRoster(FantasyTim fantasyTim) {
        int brojAktivnihPlejmejkera = 0;
        int brojAktivnihBekova = 0;
        int brojAktivnihKrila = 0;
        int brojAktivnihKrilnihCentara = 0;
        int brojAktivnihCentara = 0;
        for (FantasyIgrac fi : fantasyTim.getFantasyIgracList()) {
            if (fi.getStatus() == null) {
                continue;
            }
            switch (fi.getIgrac().getPozicija()) {
                case "Plejmejker":
                    brojAktivnihPlejmejkera = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihPlejmejkera + 1 : brojAktivnihPlejmejkera;
                    break;
                case "Bek":
                    brojAktivnihBekova = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihBekova + 1 : brojAktivnihBekova;
                    break;
                case "Krilo":
                    brojAktivnihKrila = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihKrila + 1 : brojAktivnihKrila;
                    break;
                case "Krilni centar":
                    brojAktivnihKrilnihCentara = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihKrilnihCentara + 1 : brojAktivnihKrilnihCentara;
                    break;
                case "Centar":
                    brojAktivnihCentara = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihCentara + 1 : brojAktivnihCentara;
                    break;
            }
        }
        int[] nizPozicije = new int[]{brojAktivnihPlejmejkera, brojAktivnihBekova, brojAktivnihKrila, brojAktivnihKrilnihCentara, brojAktivnihCentara};
        if ((brojAktivnihPlejmejkera + brojAktivnihBekova + brojAktivnihKrila + brojAktivnihKrilnihCentara + brojAktivnihCentara) != 8) {
            return false;
        }
        for (int i : nizPozicije) {
            if (i < 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean mozeNoviAngazman(FantasyTim ft) throws ParseException {
        return (DatabaseBroker.getInstance().vratiBrojAngazmana(ft) < 4);
    }

    public static int izracunajBrojGodina(Date datum) {
        LocalDate datumRodjenja = LocalDate.parse(WebConstants.sdf.format(datum));
        LocalDate trenutniDatum = LocalDate.of(2017, 2, 23);
        return Period.between(datumRodjenja, trenutniDatum).getYears();
    }

    public static boolean proveriEmail(String email) {
        Pattern p = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean proveriKorisnickoIme(String korisnickoIme) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9.\\-_$@*!]{8,20}$");
        Matcher m = p.matcher(korisnickoIme);
        return m.matches();
    }

    public static boolean proveriSifre(String korisnickaSifra, String korisnickaSifraPonovljena) {
        return korisnickaSifra.equals(korisnickaSifraPonovljena);
    }

    public static boolean proveriKorisnickuSifru(String korisnickaSifra) {
        Pattern p = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,20}$");
        Matcher m = p.matcher(korisnickaSifra);
        return m.matches();
    }

    public static String generisiTabeluDraft(List<Igrac> lista) {
        String tabela = "<table id='tabelaIgraci'>";
        int brojIgracaURedu = 0;
        for (Igrac i : lista) {
            if (brojIgracaURedu % 2 == 0) {
                tabela += "<tr>";
            }
            tabela += "<td class='zaIzbor' id='igrac_id-" + i.getIgracId() + "'><table class='igracPodaci'>\n"
                    + "                            <tr>\n"
                    + "                                <td rowspan='8' id='podaciIgrac_" + i.getIgracId() + "'><img src='" + i.getIgracSlika() + "' style='width: 200px;' /><br /><strong>#" + i.getBrojDres() + " - " + i.getIme() + " " + i.getPrezime() + "</strong></td>\n"
                    + "                                <td colspan='11' style='text-align: left; font-weight: bold'>Statistika prethodne sezone:</td>\n"
                    + "                                <td rowspan='8' style='width: 200px;'><img src='" + i.getTim().getTimLogo() + "' style='width: 100px;' /></td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td>MPG</td>\n"
                    + "                                <td>FG%</td>\n"
                    + "                                <td>FT%</td>\n"
                    + "                                <td>3PM</td>\n"
                    + "                                <td>REB</td>\n"
                    + "                                <td>AST</td>\n"
                    + "                                <td>AST/TO</td>\n"
                    + "                                <td>STL</td>\n"
                    + "                                <td>BLK</td>\n"
                    + "                                <td>TO</td>\n"
                    + "                                <td>PTS</td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td>" + i.getMinutazaPrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getSutIzIgrePrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getSlobodnaBacanjaPrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getPogodak3pPrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getSkokoviPrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getAsistencijePrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getAsistIzglopPrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getUkradeneLoptePrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getBlokadePrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getIzgubljeneLoptePrethodnaSezona() + "</td>\n"
                    + "                                <td>" + i.getPoeniPrethodnaSezona() + "</td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td colspan='11' style='text-align: left; font-weight: bold'>Projekcija za narednu sezonu:</td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td>MPG</td>\n"
                    + "                                <td>FG%</td>\n"
                    + "                                <td>FT%</td>\n"
                    + "                                <td>3PM</td>\n"
                    + "                                <td>REB</td>\n"
                    + "                                <td>AST</td>\n"
                    + "                                <td>AST/TO</td>\n"
                    + "                                <td>STL</td>\n"
                    + "                                <td>BLK</td>\n"
                    + "                                <td>TO</td>\n"
                    + "                                <td>PTS</td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td>" + i.getMinutazaProjekcija() + "</td>\n"
                    + "                                <td>" + i.getSutIzIgreProjekcija() + "</td>\n"
                    + "                                <td>" + i.getSlobodnaBacanjaProjekcija() + "</td>\n"
                    + "                                <td>" + i.getPogodak3pProjekcija() + "</td>\n"
                    + "                                <td>" + i.getSkokoviProjekcija() + "</td>\n"
                    + "                                <td>" + i.getAsistencijeProjekcija() + "</td>\n"
                    + "                                <td>" + i.getAsistIzglopProjekcija() + "</td>\n"
                    + "                                <td>" + i.getUkradeneLopteProjekcija() + "</td>\n"
                    + "                                <td>" + i.getBlokadeProjekcija() + "</td>\n"
                    + "                                <td>" + i.getIzgubljeneLopteProjekcija() + "</td>\n"
                    + "                                <td>" + i.getPoeniProjekcija() + "</td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td colspan='3' id='pozicija" + i.getIgracId() + "'><strong>Pozicija:</strong> " + i.getPozicijaKrace() + "</td>\n"
                    + "                                <td colspan='4'><strong>Visina:</strong> " + i.getVisina() + "cm</td>\n"
                    + "                                <td colspan='4'><strong>Težina:</strong> " + i.getTezina() + "kg</td>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td colspan='6'><strong>Datum rođenja:</strong> " + i.getDatumRodjenja() + "</td>\n"
                    + "                                <td colspan='5'><strong>Godina u NBA:</strong> " + i.getIskustvo() + "</td>\n"
                    + "                            </tr>\n"
                    + "                        </table></td>";
            brojIgracaURedu++;
            if (brojIgracaURedu % 2 == 0) {
                tabela += "</tr>";
            }
        }
        tabela += "</table>";

        return tabela;
    }

    public static String generisiTabeluTrziste(FantasyTim ft, List<Igrac> lista) {
        String tabela = "<table class='tabelaPrikazIgraca'><thead>\n"
                + "            <tr>\n"
                + "                <th></th>\n"
                + "                <th>Ime i prezime</th>\n"
                + "                <th>Tim</th>\n"
                + "                <th>Pozicija</th>\n"
                + "                <th></th>\n"
                + "            </tr>\n"
                + "        </thead>\n"
                + "        <tbody>";
        for (Igrac i : lista) {
            tabela += "<tr>\n"
                    + "                    <td><a href='./fantasy?action=igrac&igrac=" + i.getIgracId() + "' target='_blank'><img src='" + i.getIgracSlika() + "' style='width: 150px;' /></a></td>\n"
                    + "                    <td class='centriraj'><a href='./fantasy?action=igrac&igrac=" + i.getIgracId() + "' target='_blank'>" + i + "</a></td>\n"
                    + "                    <td class='centriraj'><a href='./fantasy?action=tim&tim=" + i.getTim().getTimId() + "' target='_blank'><img src='" + i.getTim().getTimLogo() + "' style='width: 80px;' /></a></td>\n"
                    + "                    <td class='centriraj'>" + i.getPozicija() + "</td>";
            if (ft.getValidator().mozeNoviIgrac(i.getPozicija())) {
                tabela += "<td><button class='ui-button ui-widget ui-corner-all' onclick='angazujIgraca(" + ft.getFantasyTimId() + ", " + i.getIgracId() + ")'>Angažuj</button></td>";
            }
            tabela += "</tr>";
        }
        tabela += "</tbody>\n"
                + "    </table>";

        return tabela;
    }

    public static String generisiTabeluPrikaz(List<Igrac> lista) {
        String tabela = "<table class='tabelaNBA'>";
        int brojIgracaURedu = 0;
        for (Igrac i : lista) {
            if (brojIgracaURedu % 4 == 0) {
                tabela += "<tr>";
            }
            tabela += "<td class='centriraj'><a href='./fantasy?action=igrac&igrac=" + i.getIgracId() + "'><img src='" + i.getIgracSlika() + "' class='igracSlikaPrikaz' /></a><br /><a href='./fantasy?action=igrac&igrac=" + i.getIgracId() + "'>" + i + "</a><br /><img src='" + i.getTim().getTimLogo() + "' class='timLogoPrikaz' /></td>";
            brojIgracaURedu++;
            if (brojIgracaURedu % 4 == 0) {
                tabela += "</tr>";
            }
        }
        tabela += "</table>";
        return tabela;
    }

    public static String generisiTabeluUtakmica(List<Utakmica> lista) {
        String tabela = "<h4>" + lista.get(lista.size() - 1).getDatum() + "</h4>\n"
                + "    <table><thead>\n"
                + "            <tr>\n"
                + "                <th colspan=\"2\">Domaćin</th>\n"
                + "                <th>Rezultat</th>\n"
                + "                <th colspan=\"2\">Gost</th>\n"
                + "            </tr>\n"
                + "        </thead>";
        for (Utakmica u : lista) {
            tabela += "<tr>\n"
                    + "                <td style=\"text-align: right;\"><a href=\"./fantasy?action=tim&tim=" + u.getDomacin().getTimId() + "\" target='_blank'>" + u.getDomacin().getTimNaziv() + "</a></td>\n"
                    + "                <td><a href=\"./fantasy?action=tim&tim=" + u.getDomacin().getTimId() + "\" target='_blank'><img src='" + u.getDomacin().getTimLogo() + "' /></td>"
                    + "                <td class='centriraj'>";
            if (u.getOdigrana()) {
                tabela += "<a href='./fantasy?action=utakmica&utakmica=" + u.getUtakmicaId() + "'>";
            }
            tabela += u.getPoeniDomacin() + "-" + u.getPoeniGost();
            if (u.getOdigrana()) {
                tabela += "</a>";
            }
            tabela += "</td>\n"
                    + "                <td><a href=\"./fantasy?action=tim&tim=" + u.getGost().getTimId() + "\" target='_blank'><img src='" + u.getGost().getTimLogo() + "' /></a></td>"
                    + "                <td style=\"text-align: left;\"><a href=\"./fantasy?action=tim&tim=" + u.getGost().getTimId() + "\" target='_blank'>" + u.getGost().getTimNaziv() + "</a></td>\n"
                    + "            </tr>";
        }
        tabela += "</table>";
        return tabela;
    }

    public static String generisiTabeluUtakmicaTima(List<Utakmica> lista) {
        String tabela = "<table><thead>\n"
                + "            <tr>\n"
                + "                <th>Datum</th>\n"
                + "                <th colspan=\"2\">Domaćin</th>\n"
                + "                <th>Rezultat</th>\n"
                + "                <th colspan=\"2\">Gost</th>\n"
                + "            </tr>\n"
                + "        </thead>";
        for (Utakmica u : lista) {
            tabela += "<tr><td class=\"centriraj\">" + u.getDatum() + "</td><td  style=\"text-align: right;\"><a href=\"./fantasy?action=tim&tim=" + u.getDomacin().getTimId() + "\" target='_blank'>" + u.getDomacin().getTimNaziv() + "</a></td><td><a href=\"./fantasy?action=tim&tim=" + u.getDomacin().getTimId() + "\" target=\"_blank\"><img src=\"" + u.getDomacin().getTimLogo() + "\" /></a></td><td class='centriraj'>";
            if (u.getOdigrana()) {
                tabela += "<a href='./fantasy?action=utakmica&utakmica=" + u.getUtakmicaId() + "'>";
            }
            tabela += u.getPoeniDomacin() + "-" + u.getPoeniGost();
            if (u.getOdigrana()) {
                tabela += "</a>";
            }
            tabela += "</td><td><a href='./fantasy?action=tim&tim=" + u.getGost().getTimId() + "' target='_blank'><img src='" + u.getGost().getTimLogo() + "' /></a></td><td><a href='./fantasy?action=tim&tim=" + u.getGost().getTimId() + "' target='_blank'>" + u.getGost().getTimNaziv() + "</a></td></tr>";
        }
        tabela += "</table>";
        return tabela;
    }

    public static String generisiTabeluFantasyUtakmica(List<FantasyUtakmica> listaUtakmica) {
        String ispis = "";
        int poslednjeKolo = 0;
        for (FantasyUtakmica fu : listaUtakmica) {
            if (fu.getKolo() != poslednjeKolo) {
                ispis += "<h4 style='margin-top: 20px;'>" + fu.getDatumPocetak() + " - " + fu.getDatumKraj() + "(" + fu.getKolo() + ". kolo)</h4>";
            }
            poslednjeKolo = fu.getKolo();
            DecimalFormat df = new DecimalFormat("#.00%");
            String procenatIzIgreDomacin = (fu.getFtuDomacin().getPokusajIzIgre() != 0) ? String.valueOf(df.format((double) fu.getFtuDomacin().getPogodakIzIgre() / (double) fu.getFtuDomacin().getPokusajIzIgre())) : "0";
            String procenat1pDomacin = (fu.getFtuDomacin().getPokusaj1p() != 0) ? String.valueOf(df.format((double) fu.getFtuDomacin().getPogodak1p() / (double) fu.getFtuDomacin().getPokusaj1p())) : "0";
            String procenatIzIgreGost = (fu.getFtuGost().getPokusajIzIgre() != 0) ? String.valueOf(df.format((double) fu.getFtuGost().getPogodakIzIgre() / (double) fu.getFtuGost().getPokusajIzIgre())) : "0";
            String procenat1pGost = (fu.getFtuGost().getPokusaj1p() != 0) ? String.valueOf(df.format((double) fu.getFtuGost().getPogodak1p() / (double) fu.getFtuGost().getPokusaj1p())) : "0";
            ispis += "<table id='utakmice_" + fu.getDatumPocetak() + "_" + fu.getDatumKraj() + "' class='centriraj utakmice'><thead><tr><th style='width: 100px;'>Tim</th><th style='width: 60px;'>FG</th><th>FG%</th><th style='width: 60px;'>FT</th><th>FT%</th><th>3PM</th><th>REB</th><th>AST</th><th>STL</th><th>BLK</th><th>PTS</th><th>Rezultat</th></tr></thead><tbody><tr><td><a href='./fantasy?action=fantasyTim&tim=" + fu.getDomacin().getFantasyTimId() + "' target='_blank'>" + fu.getDomacin().getTimNaziv() + "</a></td><td>" + fu.getFtuDomacin().getPogodakIzIgre() + "-" + fu.getFtuDomacin().getPokusajIzIgre() + "</td><td>" + procenatIzIgreDomacin + "</td><td>" + fu.getFtuDomacin().getPogodak1p() + "-" + fu.getFtuDomacin().getPokusaj1p() + "</td><td>" + procenat1pDomacin + "</td><td>" + fu.getFtuDomacin().getPogodak3p() + "</td><td>" + fu.getFtuDomacin().getSkokovi() + "</td><td>" + fu.getFtuDomacin().getAsistencije() + "</td><td>" + fu.getFtuDomacin().getUkradeneLopte() + "</td><td>" + fu.getFtuDomacin().getBlokade() + "</td><td>" + fu.getFtuDomacin().getPoeni() + "</td><td>" + fu.getPoeniDomacin() + "-" + fu.getPoeniGost() + "-" + fu.getNereseno() + "</td></tr>"
                    + "<tr><td><a href='./fantasy?action=fantasyTim&tim=" + fu.getGost().getFantasyTimId() + "' target='_blank'>" + fu.getGost().getTimNaziv() + "</a></td><td>" + fu.getFtuGost().getPogodakIzIgre() + "-" + fu.getFtuGost().getPokusajIzIgre() + "</td><td>" + procenatIzIgreGost + "</td><td>" + fu.getFtuGost().getPogodak1p() + "-" + fu.getFtuGost().getPokusaj1p() + "</td><td>" + procenat1pGost + "</td><td>" + fu.getFtuGost().getPogodak3p() + "</td><td>" + fu.getFtuGost().getSkokovi() + "</td><td>" + fu.getFtuGost().getAsistencije() + "</td><td>" + fu.getFtuGost().getUkradeneLopte() + "</td><td>" + fu.getFtuGost().getBlokade() + "</td><td>" + fu.getFtuGost().getPoeni() + "</td><td>" + fu.getPoeniGost() + "-" + fu.getPoeniDomacin() + "-" + fu.getNereseno() + "</td></tr></tbody></table>";
        }
        return ispis;
    }

    public static String vratiIzlazniFormat(String format) {
        String izlazniFormat = "";
        switch (format) {
            case "json":
                izlazniFormat = MediaType.APPLICATION_JSON;
                break;
            case "xml":
                izlazniFormat = MediaType.APPLICATION_XML;
                break;
            default:
                return "";
        }
        return izlazniFormat;
    }

    public static List<FormaTim> vratiFormuTima(Tim t) {
        List<FormaTim> listaForma = new ArrayList<>();
        for (Utakmica u : t.getListaSvihUtakmica()) {
            String boja = "";
            int razlika = 0;
            if (t.equals(u.getDomacin())) {
                razlika = Integer.valueOf(u.getPoeniDomacin()) - Integer.valueOf(u.getPoeniGost());
                if (Integer.valueOf(u.getPoeniDomacin()) > Integer.valueOf(u.getPoeniGost())) {
                    boja = WebConstants.BOJA_POBEDA;
                } else {
                    boja = WebConstants.BOJA_PORAZ;
                }
            }
            if (t.equals(u.getGost())) {
                razlika = Integer.valueOf(u.getPoeniGost()) - Integer.valueOf(u.getPoeniDomacin());
                if (Integer.valueOf(u.getPoeniDomacin()) > Integer.valueOf(u.getPoeniGost())) {
                    boja = WebConstants.BOJA_PORAZ;
                } else {
                    boja = WebConstants.BOJA_POBEDA;
                }
            }
            FormaTim ft = new FormaTim(u.getUtakmicaId(), t.getUsi().vratiSkracenIspis(t, u), razlika, boja);
            listaForma.add(ft);
        }
        return listaForma;
    }

    public static String vratiVestiHTML(String vestiJSON) {
        String vestiHTML = "";
        try {
            ObjectMapper om = new ObjectMapper();
            JsonNode cvorVesti = om.readTree(vestiJSON).get("articles");
            for (int i = 0; i < cvorVesti.size(); i++) {
                JsonNode cvorVest = cvorVesti.get(i);
                if (!cvorVest.get("url").asText().contains("/nba/")) {
                    continue;
                }
                vestiHTML += "<div class='vest'><a href='" + cvorVest.get("url").asText() + "' target='_blank'><h4>" + cvorVest.get("title").asText() + "</h4></a><h6>" + cvorVest.get("author").asText() + "</h6><a href='" + cvorVest.get("url").asText() + "' target='_blank'><img src='" + cvorVest.get("urlToImage").asText() + "' /></a><p>" + cvorVest.get("description").asText() + "</p><span class='datumVest'>Datum objavljivanja: " + cvorVest.get("publishedAt").asText() + "</span></div>";
            }
            return vestiHTML;
        } catch (IOException ioex) {
            ioex.printStackTrace();
            return vestiHTML;
        }
    }

    public static String validirajKorisnika(Korisnik k) {
        String poruka = "";
        if (k.getIme().equals("")) {
            poruka += "Niste uneli ime!";
        }
        if (k.getPrezime().equals("")) {
            poruka += " Niste uneli prezime!";
        }
        if (Util.izracunajBrojGodina(k.getDatumRodjenja()) < 18) {
            poruka += " Morate biti stariji od 18 godina!";
        }
        if (k.getEmail().equals("")) {
            poruka += " Niste uneli e-mail!";
        } else {
            if (!Util.proveriEmail(k.getEmail())) {
                poruka += " E-mail nije u korektnom formatu!";
            }
        }
        if (k.getKorisnickoIme().equals("")) {
            poruka += " Niste uneli korisničko ime!";
        } else if (DatabaseBroker.getInstance().zauzetoKorisnickoIme(k)) {
            poruka += " Korisničko ime je zauzeto!";
        } else {
            if (!Util.proveriKorisnickoIme(k.getKorisnickoIme())) {
                poruka += " Nepropisno korisničko ime!";
            }
        }
        if (k.getKorisnickaSifra().equals("")) {
            poruka += " Niste uneli šifru!";
        } else {
            if (!Util.proveriKorisnickuSifru(k.getKorisnickaSifra())) {
                poruka += " Šifra nije u korektnom formatu!";
            }
        }
        return poruka;
    }

}
