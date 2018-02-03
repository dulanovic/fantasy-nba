package domen;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "transakcija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transakcija.findAll", query = "SELECT t FROM Transakcija t")
    , @NamedQuery(name = "Transakcija.findByTransakcijaId", query = "SELECT t FROM Transakcija t WHERE t.transakcijaId = :transakcijaId")
    , @NamedQuery(name = "Transakcija.findByDatumTransakcije", query = "SELECT t FROM Transakcija t WHERE t.datumTransakcije = :datumTransakcije")})
public class Transakcija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transakcija_id")
    private Integer transakcijaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datum_transakcije")
    @Temporal(TemporalType.DATE)
    private Date datumTransakcije;
    @JoinColumn(name = "tim", referencedColumnName = "fantasy_tim_id")
    @ManyToOne(optional = false)
    private FantasyTim tim;
    @JoinColumn(name = "igrac", referencedColumnName = "fantasy_igrac_id")
    @ManyToOne(optional = false)
    private FantasyIgrac igrac;
    @JoinColumn(name = "tip", referencedColumnName = "tip_transakcije_id")
    @ManyToOne(optional = false)
    private TipTransakcije tip;

    public Transakcija() {
    }

    public Transakcija(Integer transakcijaId) {
        this.transakcijaId = transakcijaId;
    }

    public Transakcija(Date datumTransakcije, FantasyTim tim, FantasyIgrac igrac, TipTransakcije tip) {
        this.datumTransakcije = datumTransakcije;
        this.tim = tim;
        this.igrac = igrac;
        this.tip = tip;
    }

    public Integer getTransakcijaId() {
        return transakcijaId;
    }

    public void setTransakcijaId(Integer transakcijaId) {
        this.transakcijaId = transakcijaId;
    }

    public Date getDatumTransakcije() {
        return datumTransakcije;
    }

    public void setDatumTransakcije(Date datumTransakcije) {
        this.datumTransakcije = datumTransakcije;
    }

    public FantasyTim getTim() {
        return tim;
    }

    public void setTim(FantasyTim tim) {
        this.tim = tim;
    }

    public FantasyIgrac getIgrac() {
        return igrac;
    }

    public void setIgrac(FantasyIgrac igrac) {
        this.igrac = igrac;
    }

    public TipTransakcije getTip() {
        return tip;
    }

    public void setTip(TipTransakcije tip) {
        this.tip = tip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transakcijaId != null ? transakcijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transakcija)) {
            return false;
        }
        Transakcija other = (Transakcija) object;
        if ((this.transakcijaId == null && other.transakcijaId != null) || (this.transakcijaId != null && !this.transakcijaId.equals(other.transakcijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Transakcija[ transakcijaId=" + transakcijaId + " ]";
    }

}
