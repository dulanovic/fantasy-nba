package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import util.PozicijaValidator;

@Entity
@Cacheable(false)
@Table(name = "fantasy_tim")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FantasyTim.findAll", query = "SELECT f FROM FantasyTim f")
    , @NamedQuery(name = "FantasyTim.findByFantasyTimId", query = "SELECT f FROM FantasyTim f WHERE f.fantasyTimId = :fantasyTimId")
    , @NamedQuery(name = "FantasyTim.findByTimNaziv", query = "SELECT f FROM FantasyTim f WHERE f.timNaziv = :timNaziv")
    , @NamedQuery(name = "FantasyTim.findByTimNazivAndLiga", query = "SELECT f FROM FantasyTim f WHERE f.timNaziv = :timNaziv AND f.liga = :liga")
    , @NamedQuery(name = "FantasyTim.findByBrojPoena", query = "SELECT f FROM FantasyTim f WHERE f.brojPoena = :brojPoena")})
public class FantasyTim implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fantasyTim")
    private List<FantasyIgracUcestvovanje> fantasyIgracUcestvovanjeList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tim")
    private List<Transakcija> transakcijaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fantasy_tim_id")
    private Integer fantasyTimId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tim_naziv")
    private String timNaziv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "broj_poena")
    private double brojPoena;
    @JoinColumn(name = "liga", referencedColumnName = "liga_id")
    @ManyToOne(optional = false)
    private FantasyLiga liga;
    @JoinColumn(name = "vlasnik", referencedColumnName = "korisnik_id")
    @ManyToOne(optional = false)
    private Korisnik vlasnik;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tim")
    private List<FantasyIgrac> fantasyIgracList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "domacin")
    private List<FantasyUtakmica> fantasyUtakmicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gost")
    private List<FantasyUtakmica> fantasyUtakmicaList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fantasyTim")
    private List<FantasyTimUcinak> fantasyTimUcinakList;

    public FantasyTim() {
    }

    public FantasyTim(Integer fantasyTimId) {
        this.fantasyTimId = fantasyTimId;
    }

    public FantasyTim(Integer fantasyTimId, String timNaziv, double brojPoena) {
        this.fantasyTimId = fantasyTimId;
        this.timNaziv = timNaziv;
        this.brojPoena = brojPoena;
    }

    public FantasyTim(String timNaziv, double brojPoena, FantasyLiga liga, Korisnik vlasnik) {
        this.timNaziv = timNaziv;
        this.brojPoena = brojPoena;
        this.liga = liga;
        this.vlasnik = vlasnik;
    }

    public Integer getFantasyTimId() {
        return fantasyTimId;
    }

    public void setFantasyTimId(Integer fantasyTimId) {
        this.fantasyTimId = fantasyTimId;
    }

    public String getTimNaziv() {
        return timNaziv;
    }

    public void setTimNaziv(String timNaziv) {
        this.timNaziv = timNaziv;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public FantasyLiga getLiga() {
        return liga;
    }

    public void setLiga(FantasyLiga liga) {
        this.liga = liga;
    }

    public Korisnik getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(Korisnik vlasnik) {
        this.vlasnik = vlasnik;
    }

    @XmlTransient
    public List<FantasyIgrac> getFantasyIgracList() {
        return fantasyIgracList;
    }

    public void setFantasyIgracList(List<FantasyIgrac> fantasyIgracList) {
        this.fantasyIgracList = fantasyIgracList;
    }

    @XmlTransient
    public List<FantasyUtakmica> getFantasyUtakmicaList() {
        return fantasyUtakmicaList;
    }

    public void setFantasyUtakmicaList(List<FantasyUtakmica> fantasyUtakmicaList) {
        this.fantasyUtakmicaList = fantasyUtakmicaList;
    }

    @XmlTransient
    public List<FantasyUtakmica> getFantasyUtakmicaList1() {
        return fantasyUtakmicaList1;
    }

    public void setFantasyUtakmicaList1(List<FantasyUtakmica> fantasyUtakmicaList1) {
        this.fantasyUtakmicaList1 = fantasyUtakmicaList1;
    }

    @XmlTransient
    public List<FantasyTimUcinak> getFantasyTimUcinakList() {
        return fantasyTimUcinakList;
    }

    public void setFantasyTimUcinakList(List<FantasyTimUcinak> fantasyTimUcinakList) {
        this.fantasyTimUcinakList = fantasyTimUcinakList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fantasyTimId != null ? fantasyTimId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FantasyTim)) {
            return false;
        }
        FantasyTim other = (FantasyTim) object;
        if ((this.fantasyTimId == null && other.fantasyTimId != null) || (this.fantasyTimId != null && !this.fantasyTimId.equals(other.fantasyTimId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return timNaziv;
    }

    @Transient
    private List<FantasyUtakmica> listaSvihUtakmica;

    public List<FantasyUtakmica> getListaSvihUtakmica() {
        return listaSvihUtakmica;
    }

    public void setListaSvihUtakmica(List<FantasyUtakmica> listaSvihUtakmica) {
        this.listaSvihUtakmica = listaSvihUtakmica;
    }

    @Transient
    private PozicijaValidator validator;

    @XmlTransient
    public PozicijaValidator getValidator() {
        return validator;
    }

    public void setValidator(PozicijaValidator validator) {
        this.validator = validator;
    }

    @XmlTransient
    public List<Transakcija> getTransakcijaList() {
        return transakcijaList;
    }

    public void setTransakcijaList(List<Transakcija> transakcijaList) {
        this.transakcijaList = transakcijaList;
    }

    @XmlTransient
    public List<FantasyIgracUcestvovanje> getFantasyIgracUcestvovanjeList() {
        return fantasyIgracUcestvovanjeList;
    }

    public void setFantasyIgracUcestvovanjeList(List<FantasyIgracUcestvovanje> fantasyIgracUcestvovanjeList) {
        this.fantasyIgracUcestvovanjeList = fantasyIgracUcestvovanjeList;
    }

}
