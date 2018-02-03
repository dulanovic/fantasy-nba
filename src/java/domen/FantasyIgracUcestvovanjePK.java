package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FantasyIgracUcestvovanjePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "igrac")
    private int igrac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tim")
    private int tim;
    @Basic(optional = false)
    @NotNull
    @Column(name = "utakmica")
    private int utakmica;

    public FantasyIgracUcestvovanjePK() {
    }

    public FantasyIgracUcestvovanjePK(int igrac, int tim, int utakmica) {
        this.igrac = igrac;
        this.tim = tim;
        this.utakmica = utakmica;
    }

    public int getIgrac() {
        return igrac;
    }

    public void setIgrac(int igrac) {
        this.igrac = igrac;
    }

    public int getTim() {
        return tim;
    }

    public void setTim(int tim) {
        this.tim = tim;
    }

    public int getUtakmica() {
        return utakmica;
    }

    public void setUtakmica(int utakmica) {
        this.utakmica = utakmica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) igrac;
        hash += (int) tim;
        hash += (int) utakmica;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FantasyIgracUcestvovanjePK)) {
            return false;
        }
        FantasyIgracUcestvovanjePK other = (FantasyIgracUcestvovanjePK) object;
        if (this.igrac != other.igrac) {
            return false;
        }
        if (this.tim != other.tim) {
            return false;
        }
        if (this.utakmica != other.utakmica) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.FantasyIgracUcestvovanjePK[ igrac=" + igrac + ", tim=" + tim + ", utakmica=" + utakmica + " ]";
    }

}
