package util;

import db.DatabaseBroker;
import domen.FantasyIgrac;
import domen.FantasyTim;

public class PozicijaValidator {

    private FantasyTim fantasyTim;

    private int brojPlejmejkera;
    private int brojBekova;
    private int brojKrila;
    private int brojKrilnihCentara;
    private int brojCentara;

    private int brojAktivnihPlejmejkera;
    private int brojAktivnihBekova;
    private int brojAktivnihKrila;
    private int brojAktivnihKrilnihCentara;
    private int brojAktivnihCentara;

    public PozicijaValidator(FantasyTim fantasyTim) {
        this.fantasyTim = fantasyTim;
        prebrojIgrace(fantasyTim);
        prebrojAktivneIgrace(fantasyTim);
    }

    private int vratiUkupanBrojIgraca() {
        return (this.brojPlejmejkera + this.brojBekova + this.brojKrila + this.brojKrilnihCentara + this.brojCentara);
    }

    private void prebrojIgrace(FantasyTim ft) {
        for (FantasyIgrac fi : ft.getFantasyIgracList()) {
            switch (fi.getIgrac().getPozicija()) {
                case "Plejmejker":
                    this.brojPlejmejkera++;
                    break;
                case "Bek":
                    this.brojBekova++;
                    break;
                case "Krilo":
                    this.brojKrila++;
                    break;
                case "Krilni centar":
                    this.brojKrilnihCentara++;
                    break;
                case "Centar":
                    this.brojCentara++;
                    break;
            }
        }
    }

    private void prebrojAktivneIgrace(FantasyTim ft) {
        for (FantasyIgrac fi : ft.getFantasyIgracList()) {
            if (fi.getStatus() == null) {
                continue;
            }
            switch (fi.getIgrac().getPozicija()) {
                case "Plejmejker":
                    this.brojAktivnihPlejmejkera = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihPlejmejkera + 1 : brojAktivnihPlejmejkera;
                    break;
                case "Bek":
                    this.brojAktivnihBekova = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihBekova + 1 : brojAktivnihBekova;
                    break;
                case "Krilo":
                    this.brojAktivnihKrila = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihKrila + 1 : brojAktivnihKrila;
                    break;
                case "Krilni centar":
                    this.brojAktivnihKrilnihCentara = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihKrilnihCentara + 1 : brojAktivnihKrilnihCentara;
                    break;
                case "Centar":
                    this.brojAktivnihCentara = (fi.getStatus().getStatusIgracaNaziv().equals("Aktivan")) ? brojAktivnihCentara + 1 : brojAktivnihCentara;
                    break;
            }
        }
    }

    public boolean mozeNoviIgrac(String pozicija) {
        if (vratiUkupanBrojIgraca() == 10) {
            return false;
        }
        if (DatabaseBroker.getInstance().vratiBrojAngazmana(fantasyTim) >= 3) {
            return false;
        }
        switch (pozicija) {
            case "Plejmejker":
                return (this.brojPlejmejkera < 3);
            case "Bek":
                return (this.brojBekova < 3);
            case "Krilo":
                return (this.brojKrila < 3);
            case "Krilni centar":
                return (this.brojKrilnihCentara < 3);
            case "Centar":
                return (this.brojCentara < 3);
            default:
                return false;
        }
    }

    public boolean mozeOtkaz(String pozicija) {
        if ((brojPlejmejkera + brojBekova + brojKrila + brojKrilnihCentara + brojCentara) == 8) {
            return false;
        }
        switch (pozicija) {
            case "Plejmejker":
                return (this.brojPlejmejkera > 1);
            case "Bek":
                return (this.brojBekova > 1);
            case "Krilo":
                return (this.brojKrila > 1);
            case "Krilni centar":
                return (this.brojKrilnihCentara > 1);
            case "Centar":
                return (this.brojCentara > 1);
            default:
                return false;
        }
    }

    public boolean mozeAktivacija(String pozicija) {
        if (vratiUkupanBrojIgraca() == 10) {
            return false;
        }
        switch (pozicija) {
            case "Plejmejker":
                return (this.brojAktivnihPlejmejkera < 3);
            case "Bek":
                return (this.brojAktivnihBekova < 3);
            case "Krilo":
                return (this.brojAktivnihKrila < 3);
            case "Krilni centar":
                return (this.brojAktivnihKrilnihCentara < 3);
            case "Centar":
                return (this.brojAktivnihCentara < 3);
            default:
                return false;
        }
    }

    public boolean mozeURezerve(String pozicija) {
        switch (pozicija) {
            case "Plejmejker":
                return (this.brojAktivnihPlejmejkera > 1);
            case "Bek":
                return (this.brojAktivnihBekova > 1);
            case "Krilo":
                return (this.brojAktivnihKrila > 1);
            case "Krilni centar":
                return (this.brojAktivnihKrilnihCentara > 1);
            case "Centar":
                return (this.brojAktivnihCentara > 1);
            default:
                return false;
        }
    }

}
