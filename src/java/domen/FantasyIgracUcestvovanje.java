package domen;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Cacheable(false)
@Table(name = "fantasy_igrac_ucestvovanje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FantasyIgracUcestvovanje.findAll", query = "SELECT f FROM FantasyIgracUcestvovanje f")
    , @NamedQuery(name = "FantasyIgracUcestvovanje.findByIgrac", query = "SELECT f FROM FantasyIgracUcestvovanje f WHERE f.fantasyIgracUcestvovanjePK.igrac = :igrac")
    , @NamedQuery(name = "FantasyIgracUcestvovanje.findByTim", query = "SELECT f FROM FantasyIgracUcestvovanje f WHERE f.fantasyIgracUcestvovanjePK.tim = :tim")
    , @NamedQuery(name = "FantasyIgracUcestvovanje.findByUtakmica", query = "SELECT f FROM FantasyIgracUcestvovanje f WHERE f.fantasyIgracUcestvovanjePK.utakmica = :utakmica")
    , @NamedQuery(name = "FantasyIgracUcestvovanje.findByDatumOd", query = "SELECT f FROM FantasyIgracUcestvovanje f WHERE f.datumOd = :datumOd")
    , @NamedQuery(name = "FantasyIgracUcestvovanje.findByDatumDo", query = "SELECT f FROM FantasyIgracUcestvovanje f WHERE f.datumDo = :datumDo")})
public class FantasyIgracUcestvovanje implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FantasyIgracUcestvovanjePK fantasyIgracUcestvovanjePK;
    @Column(name = "datum_od")
    @Temporal(TemporalType.DATE)
    private Date datumOd;
    @Column(name = "datum_do")
    @Temporal(TemporalType.DATE)
    private Date datumDo;
    @JoinColumn(name = "igrac", referencedColumnName = "fantasy_igrac_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FantasyIgrac fantasyIgrac;
    @JoinColumn(name = "utakmica", referencedColumnName = "fantasy_utakmica_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FantasyUtakmica fantasyUtakmica;
    @JoinColumn(name = "tim", referencedColumnName = "fantasy_tim_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FantasyTim fantasyTim;

    public FantasyIgracUcestvovanje() {
    }

    public FantasyIgracUcestvovanje(FantasyIgracUcestvovanjePK fantasyIgracUcestvovanjePK) {
        this.fantasyIgracUcestvovanjePK = fantasyIgracUcestvovanjePK;
    }

    public FantasyIgracUcestvovanje(int igrac, int tim, int utakmica) {
        this.fantasyIgracUcestvovanjePK = new FantasyIgracUcestvovanjePK(igrac, tim, utakmica);
    }

    public FantasyIgracUcestvovanje(FantasyIgracUcestvovanjePK fantasyIgracUcestvovanjePK, Date datumOd, Date datumDo, FantasyIgrac fantasyIgrac, FantasyUtakmica fantasyUtakmica, FantasyTim fantasyTim) {
        this.fantasyIgracUcestvovanjePK = fantasyIgracUcestvovanjePK;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.fantasyIgrac = fantasyIgrac;
        this.fantasyUtakmica = fantasyUtakmica;
        this.fantasyTim = fantasyTim;
    }

    public void postaviNoveVrednosti(FantasyIgracUcestvovanje fiu) {
        setFantasyIgracUcestvovanjePK(fiu.getFantasyIgracUcestvovanjePK());
        setFantasyUtakmica(fiu.getFantasyUtakmica());
        setFantasyTim(fiu.getFantasyTim());
        setFantasyIgrac(fiu.getFantasyIgrac());
        setDatumOd(fiu.getDatumOd());
        setDatumDo(fiu.getDatumDo());
    }

    public FantasyIgracUcestvovanjePK getFantasyIgracUcestvovanjePK() {
        return fantasyIgracUcestvovanjePK;
    }

    public void setFantasyIgracUcestvovanjePK(FantasyIgracUcestvovanjePK fantasyIgracUcestvovanjePK) {
        this.fantasyIgracUcestvovanjePK = fantasyIgracUcestvovanjePK;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public FantasyIgrac getFantasyIgrac() {
        return fantasyIgrac;
    }

    public void setFantasyIgrac(FantasyIgrac fantasyIgrac) {
        this.fantasyIgrac = fantasyIgrac;
    }

    public FantasyUtakmica getFantasyUtakmica() {
        return fantasyUtakmica;
    }

    public void setFantasyUtakmica(FantasyUtakmica fantasyUtakmica) {
        this.fantasyUtakmica = fantasyUtakmica;
    }

    public FantasyTim getFantasyTim() {
        return fantasyTim;
    }

    public void setFantasyTim(FantasyTim fantasyTim) {
        this.fantasyTim = fantasyTim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fantasyIgracUcestvovanjePK != null ? fantasyIgracUcestvovanjePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FantasyIgracUcestvovanje)) {
            return false;
        }
        FantasyIgracUcestvovanje other = (FantasyIgracUcestvovanje) object;
        if ((this.fantasyIgracUcestvovanjePK == null && other.fantasyIgracUcestvovanjePK != null) || (this.fantasyIgracUcestvovanjePK != null && !this.fantasyIgracUcestvovanjePK.equals(other.fantasyIgracUcestvovanjePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.FantasyIgracUcestvovanje[ fantasyIgracUcestvovanjePK=" + fantasyIgracUcestvovanjePK + " ]";
    }

}
