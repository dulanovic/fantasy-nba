package domen;

import domen.FantasyIgrac;
import domen.FantasyIgracUcestvovanjePK;
import domen.FantasyTim;
import domen.FantasyUtakmica;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(FantasyIgracUcestvovanje.class)
public class FantasyIgracUcestvovanje_ { 

    public static volatile SingularAttribute<FantasyIgracUcestvovanje, Date> datumDo;
    public static volatile SingularAttribute<FantasyIgracUcestvovanje, FantasyIgracUcestvovanjePK> fantasyIgracUcestvovanjePK;
    public static volatile SingularAttribute<FantasyIgracUcestvovanje, Date> datumOd;
    public static volatile SingularAttribute<FantasyIgracUcestvovanje, FantasyUtakmica> fantasyUtakmica;
    public static volatile SingularAttribute<FantasyIgracUcestvovanje, FantasyTim> fantasyTim;
    public static volatile SingularAttribute<FantasyIgracUcestvovanje, FantasyIgrac> fantasyIgrac;

}