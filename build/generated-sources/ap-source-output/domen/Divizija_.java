package domen;

import domen.Konferencija;
import domen.Tim;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-03T03:21:50")
@StaticMetamodel(Divizija.class)
public class Divizija_ { 

    public static volatile SingularAttribute<Divizija, Konferencija> konferencija;
    public static volatile ListAttribute<Divizija, Tim> timList;
    public static volatile SingularAttribute<Divizija, String> divizijaNaziv;
    public static volatile SingularAttribute<Divizija, Integer> divizijaId;

}