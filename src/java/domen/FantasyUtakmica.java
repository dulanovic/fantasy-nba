package domen;

import constants.WebConstants;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "fantasy_utakmica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FantasyUtakmica.findAll", query = "SELECT f FROM FantasyUtakmica f")
    , @NamedQuery(name = "FantasyUtakmica.findByFantasyUtakmicaId", query = "SELECT f FROM FantasyUtakmica f WHERE f.fantasyUtakmicaId = :fantasyUtakmicaId")
    , @NamedQuery(name = "FantasyUtakmica.findByKolo", query = "SELECT f FROM FantasyUtakmica f WHERE f.kolo = :kolo")
    , @NamedQuery(name = "FantasyUtakmica.findByPoeniDomacin", query = "SELECT f FROM FantasyUtakmica f WHERE f.poeniDomacin = :poeniDomacin")
    , @NamedQuery(name = "FantasyUtakmica.findByPoeniGost", query = "SELECT f FROM FantasyUtakmica f WHERE f.poeniGost = :poeniGost")
    , @NamedQuery(name = "FantasyUtakmica.findByNereseno", query = "SELECT f FROM FantasyUtakmica f WHERE f.nereseno = :nereseno")
    , @NamedQuery(name = "FantasyUtakmica.findByDatumPocetak", query = "SELECT f FROM FantasyUtakmica f WHERE f.datumPocetak = :datumPocetak")
    , @NamedQuery(name = "FantasyUtakmica.findByDatumKraj", query = "SELECT f FROM FantasyUtakmica f WHERE f.datumKraj = :datumKraj")
    , @NamedQuery(name = "FantasyUtakmica.findByDatumAndLiga", query = "SELECT fu FROM FantasyUtakmica fu WHERE fu.datumKraj >= :datum AND fu.datumPocetak <= :datum AND fu.domacin.liga = :liga AND fu.gost.liga = :liga")
    , @NamedQuery(name = "FantasyUtakmica.findByFantasyTim", query = "SELECT fu FROM FantasyUtakmica fu WHERE fu.domacin = :fantasyTim OR fu.gost = :fantasyTim ORDER BY fu.datumPocetak ASC")})
public class FantasyUtakmica implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fantasyUtakmica")
    private List<FantasyIgracUcestvovanje> fantasyIgracUcestvovanjeList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fantasy_utakmica_id")
    private Integer fantasyUtakmicaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kolo")
    private int kolo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni_domacin")
    private int poeniDomacin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni_gost")
    private int poeniGost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nereseno")
    private int nereseno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datum_pocetak")
    @Temporal(TemporalType.DATE)
    private Date datumPocetak;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datum_kraj")
    @Temporal(TemporalType.DATE)
    private Date datumKraj;
    @JoinColumn(name = "domacin", referencedColumnName = "fantasy_tim_id")
    @ManyToOne(optional = false)
    private FantasyTim domacin;
    @JoinColumn(name = "gost", referencedColumnName = "fantasy_tim_id")
    @ManyToOne(optional = false)
    private FantasyTim gost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fantasyUtakmica")
    private List<FantasyTimUcinak> fantasyTimUcinakList;

    public FantasyUtakmica() {
    }

    public FantasyUtakmica(Integer fantasyUtakmicaId) {
        this.fantasyUtakmicaId = fantasyUtakmicaId;
    }

    public FantasyUtakmica(Integer fantasyUtakmicaId, int kolo, int poeniDomacin, int poeniGost, int nereseno, Date datumPocetak, Date datumKraj) {
        this.fantasyUtakmicaId = fantasyUtakmicaId;
        this.kolo = kolo;
        this.poeniDomacin = poeniDomacin;
        this.poeniGost = poeniGost;
        this.nereseno = nereseno;
        this.datumPocetak = datumPocetak;
        this.datumKraj = datumKraj;
    }

    public FantasyUtakmica(int kolo, Date datumPocetak, FantasyTim domacin, FantasyTim gost) {
        this.kolo = kolo;
        this.datumPocetak = datumPocetak;
        this.domacin = domacin;
        this.gost = gost;
    }

    public Integer getFantasyUtakmicaId() {
        return fantasyUtakmicaId;
    }

    public void setFantasyUtakmicaId(Integer fantasyUtakmicaId) {
        this.fantasyUtakmicaId = fantasyUtakmicaId;
    }

    public int getKolo() {
        return kolo;
    }

    public void setKolo(int kolo) {
        this.kolo = kolo;
    }

    public int getPoeniDomacin() {
        return poeniDomacin;
    }

    public void setPoeniDomacin(int poeniDomacin) {
        this.poeniDomacin = poeniDomacin;
    }

    public int getPoeniGost() {
        return poeniGost;
    }

    public void setPoeniGost(int poeniGost) {
        this.poeniGost = poeniGost;
    }

    public int getNereseno() {
        return nereseno;
    }

    public void setNereseno(int nereseno) {
        this.nereseno = nereseno;
    }

    public String getDatumPocetak() {
        return WebConstants.sdf.format(datumPocetak);
    }

    public void setDatumPocetak(Date datumPocetak) {
        this.datumPocetak = datumPocetak;
    }

    public String getDatumKraj() {
        return WebConstants.sdf.format(datumKraj);
    }

    public Date getDatumKrajDate() {
        return datumKraj;
    }

    public void setDatumKraj(Date datumKraj) {
        this.datumKraj = datumKraj;
    }

    public FantasyTim getDomacin() {
        return domacin;
    }

    public void setDomacin(FantasyTim domacin) {
        this.domacin = domacin;
    }

    public FantasyTim getGost() {
        return gost;
    }

    public void setGost(FantasyTim gost) {
        this.gost = gost;
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
        hash += (fantasyUtakmicaId != null ? fantasyUtakmicaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FantasyUtakmica)) {
            return false;
        }
        FantasyUtakmica other = (FantasyUtakmica) object;
        if ((this.fantasyUtakmicaId == null && other.fantasyUtakmicaId != null) || (this.fantasyUtakmicaId != null && !this.fantasyUtakmicaId.equals(other.fantasyUtakmicaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Kolo ---> " + kolo + " --- od " + datumPocetak + " do " + datumKraj + " Domacin ---> " + domacin + " Gost ---> " + gost;
    }

    @XmlTransient
    public List<FantasyIgracUcestvovanje> getFantasyIgracUcestvovanjeList() {
        return fantasyIgracUcestvovanjeList;
    }

    public void setFantasyIgracUcestvovanjeList(List<FantasyIgracUcestvovanje> fantasyIgracUcestvovanjeList) {
        this.fantasyIgracUcestvovanjeList = fantasyIgracUcestvovanjeList;
    }

    @Transient
    private FantasyTimUcinak ftuDomacin;
    @Transient
    private FantasyTimUcinak ftuGost;

    public FantasyTimUcinak getFtuDomacin() {
        return ftuDomacin;
    }

    public void setFtuDomacin(FantasyTimUcinak ftuDomacin) {
        this.ftuDomacin = ftuDomacin;
    }

    public FantasyTimUcinak getFtuGost() {
        return ftuGost;
    }

    public void setFtuGost(FantasyTimUcinak ftuGost) {
        this.ftuGost = ftuGost;
    }

}
