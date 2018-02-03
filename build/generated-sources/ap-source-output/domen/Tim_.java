package domen;

import domen.Divizija;
import domen.Igrac;
import domen.TimUcinak;
import domen.Utakmica;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(Tim.class)
public class Tim_ { 

    public static volatile SingularAttribute<Tim, String> timLogo;
    public static volatile SingularAttribute<Tim, String> timApiId;
    public static volatile SingularAttribute<Tim, Integer> kapacitet;
    public static volatile SingularAttribute<Tim, Integer> timId;
    public static volatile SingularAttribute<Tim, String> arena;
    public static volatile SingularAttribute<Tim, String> timNaziv;
    public static volatile SingularAttribute<Tim, String> boja1;
    public static volatile SingularAttribute<Tim, String> boja2;
    public static volatile ListAttribute<Tim, Utakmica> utakmicaList;
    public static volatile SingularAttribute<Tim, String> skraceniNaziv;
    public static volatile ListAttribute<Tim, Utakmica> utakmicaList1;
    public static volatile SingularAttribute<Tim, Integer> godinaOsnivanja;
    public static volatile ListAttribute<Tim, Igrac> igracList;
    public static volatile SingularAttribute<Tim, String> trener;
    public static volatile SingularAttribute<Tim, Divizija> divizija;
    public static volatile SingularAttribute<Tim, String> grad;
    public static volatile ListAttribute<Tim, TimUcinak> timUcinakList;

}