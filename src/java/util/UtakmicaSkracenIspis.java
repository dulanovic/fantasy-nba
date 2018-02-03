package util;

import domen.Igrac;
import domen.Tim;
import domen.Utakmica;

public class UtakmicaSkracenIspis {

    public UtakmicaSkracenIspis() {
    }

    public String[] vratiIspis(Igrac i, Utakmica u) {
        String[] ispis = new String[2];
        if (i.getTim().equals(u.getDomacin())) {
            ispis[0] = "vs " + u.getGost().getSkraceniNaziv();
            ispis[1] = u.getDatum();
        } else {
            ispis[0] = "@ " + u.getDomacin().getSkraceniNaziv();
            ispis[1] = u.getDatum();
        }
        return ispis;
    }

    public String vratiSkracenIspis(Tim t, Utakmica u) {
        String ispis = "";
        if (t.equals(u.getDomacin())) {
            if (Integer.valueOf(u.getPoeniDomacin()) > Integer.valueOf(u.getPoeniGost())) {
                ispis = "W vs " + u.getGost().getSkraceniNaziv() + "\\n" + u.getDatum() + "\\n" + u.getPoeniDomacin() + "-" + u.getPoeniGost();
            } else {
                ispis = "L vs " + u.getGost().getSkraceniNaziv() + "\\n" + u.getDatum() + "\\n" + u.getPoeniDomacin() + "-" + u.getPoeniGost();
            }
        } else {
            if (Integer.valueOf(u.getPoeniDomacin()) > Integer.valueOf(u.getPoeniGost())) {
                ispis = "L @ " + u.getDomacin().getSkraceniNaziv() + "\\n" + u.getDatum() + "\\n" + u.getPoeniDomacin() + "-" + u.getPoeniGost();
            } else {
                ispis = "W @ " + u.getDomacin().getSkraceniNaziv() + "\\n" + u.getDatum() + "\\n" + u.getPoeniDomacin() + "-" + u.getPoeniGost();
            }
        }
        return ispis;
    }

}
