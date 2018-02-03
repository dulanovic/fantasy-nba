package domen;

import domen.FantasyIgrac;
import domen.FantasyIgracUcestvovanje;
import domen.FantasyLiga;
import domen.FantasyTimUcinak;
import domen.FantasyUtakmica;
import domen.Korisnik;
import domen.Transakcija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(FantasyTim.class)
public class FantasyTim_ { 

    public static volatile ListAttribute<FantasyTim, FantasyUtakmica> fantasyUtakmicaList;
    public static volatile SingularAttribute<FantasyTim, String> timNaziv;
    public static volatile SingularAttribute<FantasyTim, FantasyLiga> liga;
    public static volatile SingularAttribute<FantasyTim, Integer> fantasyTimId;
    public static volatile SingularAttribute<FantasyTim, Double> brojPoena;
    public static volatile ListAttribute<FantasyTim, Transakcija> transakcijaList;
    public static volatile ListAttribute<FantasyTim, FantasyIgracUcestvovanje> fantasyIgracUcestvovanjeList;
    public static volatile ListAttribute<FantasyTim, FantasyTimUcinak> fantasyTimUcinakList;
    public static volatile ListAttribute<FantasyTim, FantasyUtakmica> fantasyUtakmicaList1;
    public static volatile ListAttribute<FantasyTim, FantasyIgrac> fantasyIgracList;
    public static volatile SingularAttribute<FantasyTim, Korisnik> vlasnik;

}