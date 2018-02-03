package domen;

import domen.FantasyIgrac;
import domen.FantasyTim;
import domen.Korisnik;
import domen.StatusLige;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(FantasyLiga.class)
public class FantasyLiga_ { 

    public static volatile SingularAttribute<FantasyLiga, Date> datumAzuriranja;
    public static volatile SingularAttribute<FantasyLiga, Integer> ligaId;
    public static volatile SingularAttribute<FantasyLiga, Korisnik> administrator;
    public static volatile SingularAttribute<FantasyLiga, String> ligaNaziv;
    public static volatile ListAttribute<FantasyLiga, FantasyTim> fantasyTimList;
    public static volatile ListAttribute<FantasyLiga, FantasyIgrac> fantasyIgracList;
    public static volatile SingularAttribute<FantasyLiga, StatusLige> status;

}