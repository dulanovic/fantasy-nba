package domen;

import domen.FantasyLiga;
import domen.FantasyTim;
import domen.TipKorisnika;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(Korisnik.class)
public class Korisnik_ { 

    public static volatile SingularAttribute<Korisnik, String> ime;
    public static volatile SingularAttribute<Korisnik, String> prezime;
    public static volatile SingularAttribute<Korisnik, Date> datumRodjenja;
    public static volatile SingularAttribute<Korisnik, String> korisnickaSifra;
    public static volatile ListAttribute<Korisnik, FantasyTim> fantasyTimList;
    public static volatile SingularAttribute<Korisnik, TipKorisnika> tip;
    public static volatile SingularAttribute<Korisnik, String> korisnickoIme;
    public static volatile SingularAttribute<Korisnik, Integer> korisnikId;
    public static volatile ListAttribute<Korisnik, FantasyLiga> fantasyLigaList;
    public static volatile SingularAttribute<Korisnik, String> email;

}