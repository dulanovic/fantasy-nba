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
@Table(name = "konferencija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Konferencija.findAll", query = "SELECT k FROM Konferencija k")
    , @NamedQuery(name = "Konferencija.findByKonferencijaId", query = "SELECT k FROM Konferencija k WHERE k.konferencijaId = :konferencijaId")
    , @NamedQuery(name = "Konferencija.findByKonferencijaNaziv", query = "SELECT k FROM Konferencija k WHERE k.konferencijaNaziv = :konferencijaNaziv")})
public class Konferencija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "konferencija_id")
    private Integer konferencijaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "konferencija_naziv")
    private String konferencijaNaziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "konferencija")
    private List<Divizija> divizijaList;

    public Konferencija() {
    }

    public Konferencija(Integer konferencijaId) {
        this.konferencijaId = konferencijaId;
    }

    public Konferencija(Integer konferencijaId, String konferencijaNaziv) {
        this.konferencijaId = konferencijaId;
        this.konferencijaNaziv = konferencijaNaziv;
    }

    public Integer getKonferencijaId() {
        return konferencijaId;
    }

    public void setKonferencijaId(Integer konferencijaId) {
        this.konferencijaId = konferencijaId;
    }

    public String getKonferencijaNaziv() {
        return konferencijaNaziv;
    }

    public void setKonferencijaNaziv(String konferencijaNaziv) {
        this.konferencijaNaziv = konferencijaNaziv;
    }

    @XmlTransient
    public List<Divizija> getDivizijaList() {
        return divizijaList;
    }

    public void setDivizijaList(List<Divizija> divizijaList) {
        this.divizijaList = divizijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (konferencijaId != null ? konferencijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Konferencija)) {
            return false;
        }
        Konferencija other = (Konferencija) object;
        if ((this.konferencijaId == null && other.konferencijaId != null) || (this.konferencijaId != null && !this.konferencijaId.equals(other.konferencijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen_rest.Konferencija[ konferencijaId=" + konferencijaId + " ]";
    }

}
