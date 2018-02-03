package domen;

import domen.IgracUcinak;
import domen.Tim;
import domen.TimUcinak;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(Utakmica.class)
public class Utakmica_ { 

    public static volatile SingularAttribute<Utakmica, Date> datum;
    public static volatile SingularAttribute<Utakmica, String> utakmicaApiId;
    public static volatile ListAttribute<Utakmica, IgracUcinak> igracUcinakList;
    public static volatile SingularAttribute<Utakmica, Integer> poeniDomacin;
    public static volatile SingularAttribute<Utakmica, Integer> brojGledalaca;
    public static volatile SingularAttribute<Utakmica, String> trajanje;
    public static volatile SingularAttribute<Utakmica, Integer> utakmicaId;
    public static volatile SingularAttribute<Utakmica, Tim> gost;
    public static volatile SingularAttribute<Utakmica, Boolean> odigrana;
    public static volatile SingularAttribute<Utakmica, Integer> poeniGost;
    public static volatile SingularAttribute<Utakmica, Tim> domacin;
    public static volatile ListAttribute<Utakmica, TimUcinak> timUcinakList;

}