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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "divizija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Divizija.findAll", query = "SELECT d FROM Divizija d")
    , @NamedQuery(name = "Divizija.findByDivizijaId", query = "SELECT d FROM Divizija d WHERE d.divizijaId = :divizijaId")
    , @NamedQuery(name = "Divizija.findByDivizijaNaziv", query = "SELECT d FROM Divizija d WHERE d.divizijaNaziv = :divizijaNaziv")})
public class Divizija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "divizija_id")
    private Integer divizijaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "divizija_naziv")
    private String divizijaNaziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "divizija")
    private List<Tim> timList;
    @JoinColumn(name = "konferencija", referencedColumnName = "konferencija_id")
    @ManyToOne(optional = false)
    private Konferencija konferencija;

    public Divizija() {
    }

    public Divizija(Integer divizijaId) {
        this.divizijaId = divizijaId;
    }

    public Divizija(Integer divizijaId, String divizijaNaziv) {
        this.divizijaId = divizijaId;
        this.divizijaNaziv = divizijaNaziv;
    }

    public Integer getDivizijaId() {
        return divizijaId;
    }

    public void setDivizijaId(Integer divizijaId) {
        this.divizijaId = divizijaId;
    }

    public String getDivizijaNaziv() {
        return divizijaNaziv;
    }

    public void setDivizijaNaziv(String divizijaNaziv) {
        this.divizijaNaziv = divizijaNaziv;
    }

    @XmlTransient
    public List<Tim> getTimList() {
        return timList;
    }

    public void setTimList(List<Tim> timList) {
        this.timList = timList;
    }

    public Konferencija getKonferencija() {
        return konferencija;
    }

    public void setKonferencija(Konferencija konferencija) {
        this.konferencija = konferencija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (divizijaId != null ? divizijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Divizija)) {
            return false;
        }
        Divizija other = (Divizija) object;
        if ((this.divizijaId == null && other.divizijaId != null) || (this.divizijaId != null && !this.divizijaId.equals(other.divizijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return divizijaNaziv;
    }

}
