package domen;

import domen.Transakcija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(TipTransakcije.class)
public class TipTransakcije_ { 

    public static volatile SingularAttribute<TipTransakcije, Integer> tipTransakcijeId;
    public static volatile SingularAttribute<TipTransakcije, String> nazivTransakcije;
    public static volatile ListAttribute<TipTransakcije, Transakcija> transakcijaList;

}