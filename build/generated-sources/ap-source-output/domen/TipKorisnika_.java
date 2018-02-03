package domen;

import domen.Korisnik;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(TipKorisnika.class)
public class TipKorisnika_ { 

    public static volatile SingularAttribute<TipKorisnika, Integer> tipKorisnikaId;
    public static volatile SingularAttribute<TipKorisnika, String> tipNaziv;
    public static volatile ListAttribute<TipKorisnika, Korisnik> korisnikList;

}