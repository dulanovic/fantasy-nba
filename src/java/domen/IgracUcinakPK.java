package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class IgracUcinakPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "utakmica_id")
    private int utakmicaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "igrac_id")
    private int igracId;

    public IgracUcinakPK() {
    }

    public IgracUcinakPK(int utakmicaId, int igracId) {
        this.utakmicaId = utakmicaId;
        this.igracId = igracId;
    }

    public int getUtakmicaId() {
        return utakmicaId;
    }

    public void setUtakmicaId(int utakmicaId) {
        this.utakmicaId = utakmicaId;
    }

    public int getIgracId() {
        return igracId;
    }

    public void setIgracId(int igracId) {
        this.igracId = igracId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) utakmicaId;
        hash += (int) igracId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IgracUcinakPK)) {
            return false;
        }
        IgracUcinakPK other = (IgracUcinakPK) object;
        if (this.utakmicaId != other.utakmicaId) {
            return false;
        }
        if (this.igracId != other.igracId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.IgracUcinakPK[ utakmicaId=" + utakmicaId + ", igracId=" + igracId + " ]";
    }

}
