package domen;

import domen.FantasyIgracUcestvovanje;
import domen.FantasyTim;
import domen.FantasyTimUcinak;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(FantasyUtakmica.class)
public class FantasyUtakmica_ { 

    public static volatile SingularAttribute<FantasyUtakmica, Date> datumKraj;
    public static volatile SingularAttribute<FantasyUtakmica, Integer> kolo;
    public static volatile SingularAttribute<FantasyUtakmica, Integer> poeniDomacin;
    public static volatile SingularAttribute<FantasyUtakmica, Date> datumPocetak;
    public static volatile SingularAttribute<FantasyUtakmica, Integer> fantasyUtakmicaId;
    public static volatile SingularAttribute<FantasyUtakmica, Integer> nereseno;
    public static volatile SingularAttribute<FantasyUtakmica, FantasyTim> gost;
    public static volatile ListAttribute<FantasyUtakmica, FantasyIgracUcestvovanje> fantasyIgracUcestvovanjeList;
    public static volatile ListAttribute<FantasyUtakmica, FantasyTimUcinak> fantasyTimUcinakList;
    public static volatile SingularAttribute<FantasyUtakmica, Integer> poeniGost;
    public static volatile SingularAttribute<FantasyUtakmica, FantasyTim> domacin;

}