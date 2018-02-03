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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Cacheable(false)
@Table(name = "fantasy_igrac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FantasyIgrac.findAll", query = "SELECT f FROM FantasyIgrac f")
    , @NamedQuery(name = "FantasyIgrac.findByFantasyIgracId", query = "SELECT f FROM FantasyIgrac f WHERE f.fantasyIgracId = :fantasyIgracId")
    , @NamedQuery(name = "FantasyIgrac.findByStatus", query = "SELECT fi FROM FantasyIgrac fi WHERE fi.statusIgraca = :status")
    , @NamedQuery(name = "FantasyIgrac.findByFantasyTim", query = "SELECT fi FROM FantasyIgrac fi WHERE fi.tim = :tim")})
public class FantasyIgrac implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fantasyIgrac")
    private List<FantasyIgracUcestvovanje> fantasyIgracUcestvovanjeList;

    @JoinColumn(name = "liga", referencedColumnName = "liga_id")
    @ManyToOne(optional = false)
    private FantasyLiga liga;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "igrac")
    private List<Transakcija> transakcijaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fantasy_igrac_id")
    private Integer fantasyIgracId;
    @JoinColumn(name = "status", referencedColumnName = "status_igraca_id")
    @ManyToOne
    private StatusIgraca statusIgraca;
    @JoinColumn(name = "tim", referencedColumnName = "fantasy_tim_id")
    @ManyToOne(optional = false)
    private FantasyTim tim;
    @JoinColumn(name = "igrac", referencedColumnName = "igrac_id")
    @ManyToOne(optional = false)
    private Igrac igrac;

    public FantasyIgrac() {
    }

    public FantasyIgrac(Integer fantasyIgracId) {
        this.fantasyIgracId = fantasyIgracId;
    }

    public FantasyIgrac(Integer fantasyIgracId, StatusIgraca statusIgraca) {
        this.fantasyIgracId = fantasyIgracId;
        this.statusIgraca = statusIgraca;
    }

    public FantasyIgrac(FantasyLiga liga, FantasyTim tim, Igrac igrac, StatusIgraca statusIgraca) {
        this.liga = liga;
        this.tim = tim;
        this.igrac = igrac;
        this.statusIgraca = statusIgraca;
    }

    public Integer getFantasyIgracId() {
        return fantasyIgracId;
    }

    public void setFantasyIgracId(Integer fantasyIgracId) {
        this.fantasyIgracId = fantasyIgracId;
    }

    public StatusIgraca getStatus() {
        return statusIgraca;
    }

    public void setStatus(StatusIgraca statusIgraca) {
        this.statusIgraca = statusIgraca;
    }

    public FantasyTim getTim() {
        return tim;
    }

    public void setTim(FantasyTim tim) {
        this.tim = tim;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fantasyIgracId != null ? fantasyIgracId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FantasyIgrac)) {
            return false;
        }
        FantasyIgrac other = (FantasyIgrac) object;
        if ((this.fantasyIgracId == null && other.fantasyIgracId != null) || (this.fantasyIgracId != null && !this.fantasyIgracId.equals(other.fantasyIgracId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.FantasyIgrac[ fantasyIgracId=" + fantasyIgracId + " ]";
    }

    @XmlTransient
    public List<Transakcija> getTransakcijaList() {
        return transakcijaList;
    }

    public void setTransakcijaList(List<Transakcija> transakcijaList) {
        this.transakcijaList = transakcijaList;
    }

    public FantasyLiga getLiga() {
        return liga;
    }

    public void setLiga(FantasyLiga liga) {
        this.liga = liga;
    }

    @XmlTransient
    public List<FantasyIgracUcestvovanje> getFantasyIgracUcestvovanjeList() {
        return fantasyIgracUcestvovanjeList;
    }

    public void setFantasyIgracUcestvovanjeList(List<FantasyIgracUcestvovanje> fantasyIgracUcestvovanjeList) {
        this.fantasyIgracUcestvovanjeList = fantasyIgracUcestvovanjeList;
    }

}
