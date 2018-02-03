package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "status_igraca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusIgraca.findAll", query = "SELECT s FROM StatusIgraca s")
    , @NamedQuery(name = "StatusIgraca.findByStatusIgracaId", query = "SELECT s FROM StatusIgraca s WHERE s.statusIgracaId = :statusIgracaId")
    , @NamedQuery(name = "StatusIgraca.findByStatusIgracaNaziv", query = "SELECT s FROM StatusIgraca s WHERE s.statusIgracaNaziv = :statusIgracaNaziv")})
public class StatusIgraca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_igraca_id")
    private Integer statusIgracaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status_igraca_naziv")
    private String statusIgracaNaziv;

    public StatusIgraca() {
    }

    public StatusIgraca(Integer statusIgracaId) {
        this.statusIgracaId = statusIgracaId;
    }

    public StatusIgraca(Integer statusIgracaId, String statusIgracaNaziv) {
        this.statusIgracaId = statusIgracaId;
        this.statusIgracaNaziv = statusIgracaNaziv;
    }

    public Integer getStatusIgracaId() {
        return statusIgracaId;
    }

    public void setStatusIgracaId(Integer statusIgracaId) {
        this.statusIgracaId = statusIgracaId;
    }

    public String getStatusIgracaNaziv() {
        return statusIgracaNaziv;
    }

    public void setStatusIgracaNaziv(String statusIgracaNaziv) {
        this.statusIgracaNaziv = statusIgracaNaziv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (statusIgracaId != null ? statusIgracaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusIgraca)) {
            return false;
        }
        StatusIgraca other = (StatusIgraca) object;
        if ((this.statusIgracaId == null && other.statusIgracaId != null) || (this.statusIgracaId != null && !this.statusIgracaId.equals(other.statusIgracaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.StatusIgraca[ statusIgracaId=" + statusIgracaId + " ]";
    }

}
