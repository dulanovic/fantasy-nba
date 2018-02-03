package domen;

import domen.Igrac;
import domen.IgracUcinakPK;
import domen.Utakmica;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(IgracUcinak.class)
public class IgracUcinak_ { 

    public static volatile SingularAttribute<IgracUcinak, Integer> blokade;
    public static volatile SingularAttribute<IgracUcinak, Integer> skokoviUkupno;
    public static volatile SingularAttribute<IgracUcinak, Integer> pokusajIzIgre;
    public static volatile SingularAttribute<IgracUcinak, Integer> pokusaj1p;
    public static volatile SingularAttribute<IgracUcinak, Integer> pokusaj3p;
    public static volatile SingularAttribute<IgracUcinak, Integer> pogodak2p;
    public static volatile SingularAttribute<IgracUcinak, Integer> ukradeneLopte;
    public static volatile SingularAttribute<IgracUcinak, Double> procenat1p;
    public static volatile SingularAttribute<IgracUcinak, Integer> skokoviNapad;
    public static volatile SingularAttribute<IgracUcinak, Integer> skokoviOdbrana;
    public static volatile SingularAttribute<IgracUcinak, Igrac> igrac;
    public static volatile SingularAttribute<IgracUcinak, Double> procenat3p;
    public static volatile SingularAttribute<IgracUcinak, Integer> izgubljeneLopte;
    public static volatile SingularAttribute<IgracUcinak, Integer> licneGreske;
    public static volatile SingularAttribute<IgracUcinak, Utakmica> utakmica;
    public static volatile SingularAttribute<IgracUcinak, Integer> blokiraniSutevi;
    public static volatile SingularAttribute<IgracUcinak, Integer> poeni;
    public static volatile SingularAttribute<IgracUcinak, Integer> pogodakIzIgre;
    public static volatile SingularAttribute<IgracUcinak, Double> odnosAsistIzglop;
    public static volatile SingularAttribute<IgracUcinak, IgracUcinakPK> igracUcinakPK;
    public static volatile SingularAttribute<IgracUcinak, Integer> plusMinus;
    public static volatile SingularAttribute<IgracUcinak, String> minuti;
    public static volatile SingularAttribute<IgracUcinak, Boolean> starter;
    public static volatile SingularAttribute<IgracUcinak, Integer> asistencije;
    public static volatile SingularAttribute<IgracUcinak, Integer> pokusaj2p;
    public static volatile SingularAttribute<IgracUcinak, Integer> pogodak3p;
    public static volatile SingularAttribute<IgracUcinak, Double> procenat2p;
    public static volatile SingularAttribute<IgracUcinak, Integer> pogodak1p;
    public static volatile SingularAttribute<IgracUcinak, Double> procenatIzIgre;

}