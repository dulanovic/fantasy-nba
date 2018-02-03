package domen;

import domen.FantasyIgrac;
import domen.FantasyTim;
import domen.TipTransakcije;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(Transakcija.class)
public class Transakcija_ { 

    public static volatile SingularAttribute<Transakcija, Date> datumTransakcije;
    public static volatile SingularAttribute<Transakcija, FantasyTim> tim;
    public static volatile SingularAttribute<Transakcija, TipTransakcije> tip;
    public static volatile SingularAttribute<Transakcija, Integer> transakcijaId;
    public static volatile SingularAttribute<Transakcija, FantasyIgrac> igrac;

}