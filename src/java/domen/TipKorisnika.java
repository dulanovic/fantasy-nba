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
@Table(name = "tip_korisnika")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipKorisnika.findAll", query = "SELECT t FROM TipKorisnika t")
    , @NamedQuery(name = "TipKorisnika.findByTipKorisnikaId", query = "SELECT t FROM TipKorisnika t WHERE t.tipKorisnikaId = :tipKorisnikaId")
    , @NamedQuery(name = "TipKorisnika.findByTipNaziv", query = "SELECT t FROM TipKorisnika t WHERE t.tipNaziv = :tipNaziv")})
public class TipKorisnika implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tip_korisnika_id")
    private Integer tipKorisnikaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tip_naziv")
    private String tipNaziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tip")
    private List<Korisnik> korisnikList;

    public TipKorisnika() {
    }

    public TipKorisnika(Integer tipKorisnikaId) {
        this.tipKorisnikaId = tipKorisnikaId;
    }

    public TipKorisnika(Integer tipKorisnikaId, String tipNaziv) {
        this.tipKorisnikaId = tipKorisnikaId;
        this.tipNaziv = tipNaziv;
    }

    public Integer getTipKorisnikaId() {
        return tipKorisnikaId;
    }

    public void setTipKorisnikaId(Integer tipKorisnikaId) {
        this.tipKorisnikaId = tipKorisnikaId;
    }

    public String getTipNaziv() {
        return tipNaziv;
    }

    public void setTipNaziv(String tipNaziv) {
        this.tipNaziv = tipNaziv;
    }

    @XmlTransient
    public List<Korisnik> getKorisnikList() {
        return korisnikList;
    }

    public void setKorisnikList(List<Korisnik> korisnikList) {
        this.korisnikList = korisnikList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipKorisnikaId != null ? tipKorisnikaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipKorisnika)) {
            return false;
        }
        TipKorisnika other = (TipKorisnika) object;
        if ((this.tipKorisnikaId == null && other.tipKorisnikaId != null) || (this.tipKorisnikaId != null && !this.tipKorisnikaId.equals(other.tipKorisnikaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipNaziv;
    }

}
