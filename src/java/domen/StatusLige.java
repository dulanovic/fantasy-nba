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
@Table(name = "status_lige")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusLige.findAll", query = "SELECT s FROM StatusLige s")
    , @NamedQuery(name = "StatusLige.findByStatusLigeId", query = "SELECT s FROM StatusLige s WHERE s.statusLigeId = :statusLigeId")
    , @NamedQuery(name = "StatusLige.findByStatusLigeNaziv", query = "SELECT s FROM StatusLige s WHERE s.statusLigeNaziv = :statusLigeNaziv")})
public class StatusLige implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_lige_id")
    private Integer statusLigeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status_lige_naziv")
    private String statusLigeNaziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private List<FantasyLiga> fantasyLigaList;

    public StatusLige() {
    }

    public StatusLige(Integer statusLigeId) {
        this.statusLigeId = statusLigeId;
    }

    public StatusLige(Integer statusLigeId, String statusLigeNaziv) {
        this.statusLigeId = statusLigeId;
        this.statusLigeNaziv = statusLigeNaziv;
    }

    public Integer getStatusLigeId() {
        return statusLigeId;
    }

    public void setStatusLigeId(Integer statusLigeId) {
        this.statusLigeId = statusLigeId;
    }

    public String getStatusLigeNaziv() {
        return statusLigeNaziv;
    }

    public void setStatusLigeNaziv(String statusLigeNaziv) {
        this.statusLigeNaziv = statusLigeNaziv;
    }

    @XmlTransient
    public List<FantasyLiga> getFantasyLigaList() {
        return fantasyLigaList;
    }

    public void setFantasyLigaList(List<FantasyLiga> fantasyLigaList) {
        this.fantasyLigaList = fantasyLigaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusLigeId != null ? statusLigeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusLige)) {
            return false;
        }
        StatusLige other = (StatusLige) object;
        if ((this.statusLigeId == null && other.statusLigeId != null) || (this.statusLigeId != null && !this.statusLigeId.equals(other.statusLigeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return statusLigeNaziv;
    }

}
