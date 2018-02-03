package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tip_transakcije")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipTransakcije.findAll", query = "SELECT t FROM TipTransakcije t")
    , @NamedQuery(name = "TipTransakcije.findByTipTransakcijeId", query = "SELECT t FROM TipTransakcije t WHERE t.tipTransakcijeId = :tipTransakcijeId")
    , @NamedQuery(name = "TipTransakcije.findByNazivTransakcije", query = "SELECT t FROM TipTransakcije t WHERE t.nazivTransakcije = :nazivTransakcije")})
public class TipTransakcije implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tip_transakcije_id")
    private Integer tipTransakcijeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "naziv_transakcije")
    private String nazivTransakcije;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tip")
    private List<Transakcija> transakcijaList;

    public TipTransakcije() {
    }

    public TipTransakcije(Integer tipTransakcijeId) {
        this.tipTransakcijeId = tipTransakcijeId;
    }

    public TipTransakcije(Integer tipTransakcijeId, String nazivTransakcije) {
        this.tipTransakcijeId = tipTransakcijeId;
        this.nazivTransakcije = nazivTransakcije;
    }

    public Integer getTipTransakcijeId() {
        return tipTransakcijeId;
    }

    public void setTipTransakcijeId(Integer tipTransakcijeId) {
        this.tipTransakcijeId = tipTransakcijeId;
    }

    public String getNazivTransakcije() {
        return nazivTransakcije;
    }

    public void setNazivTransakcije(String nazivTransakcije) {
        this.nazivTransakcije = nazivTransakcije;
    }

    @XmlTransient
    public List<Transakcija> getTransakcijaList() {
        return transakcijaList;
    }

    public void setTransakcijaList(List<Transakcija> transakcijaList) {
        this.transakcijaList = transakcijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipTransakcijeId != null ? tipTransakcijeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipTransakcije)) {
            return false;
        }
        TipTransakcije other = (TipTransakcije) object;
        if ((this.tipTransakcijeId == null && other.tipTransakcijeId != null) || (this.tipTransakcijeId != null && !this.tipTransakcijeId.equals(other.tipTransakcijeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivTransakcije;
    }

}
