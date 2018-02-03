package domen;

import domen.FantasyIgracUcestvovanje;
import domen.FantasyLiga;
import domen.FantasyTim;
import domen.Igrac;
import domen.StatusIgraca;
import domen.Transakcija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(FantasyIgrac.class)
public class FantasyIgrac_ { 

    public static volatile SingularAttribute<FantasyIgrac, StatusIgraca> statusIgraca;
    public static volatile SingularAttribute<FantasyIgrac, FantasyLiga> liga;
    public static volatile ListAttribute<FantasyIgrac, Transakcija> transakcijaList;
    public static volatile SingularAttribute<FantasyIgrac, FantasyTim> tim;
    public static volatile ListAttribute<FantasyIgrac, FantasyIgracUcestvovanje> fantasyIgracUcestvovanjeList;
    public static volatile SingularAttribute<FantasyIgrac, Integer> fantasyIgracId;
    public static volatile SingularAttribute<FantasyIgrac, Igrac> igrac;

}