package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FantasyTimUcinakPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "utakmica")
    private int utakmica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tim")
    private int tim;

    public FantasyTimUcinakPK() {
    }

    public FantasyTimUcinakPK(int utakmica, int tim) {
        this.utakmica = utakmica;
        this.tim = tim;
    }

    public int getUtakmica() {
        return utakmica;
    }

    public void setUtakmica(int utakmica) {
        this.utakmica = utakmica;
    }

    public int getTim() {
        return tim;
    }

    public void setTim(int tim) {
        this.tim = tim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) utakmica;
        hash += (int) tim;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FantasyTimUcinakPK)) {
            return false;
        }
        FantasyTimUcinakPK other = (FantasyTimUcinakPK) object;
        if (this.utakmica != other.utakmica) {
            return false;
        }
        if (this.tim != other.tim) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.FantasyTimUcinakPK[ utakmica=" + utakmica + ", tim=" + tim + " ]";
    }

}
