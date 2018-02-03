package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Cacheable(false)
@Table(name = "fantasy_tim_ucinak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FantasyTimUcinak.findAll", query = "SELECT f FROM FantasyTimUcinak f")
    , @NamedQuery(name = "FantasyTimUcinak.findByUtakmica", query = "SELECT f FROM FantasyTimUcinak f WHERE f.fantasyTimUcinakPK.utakmica = :utakmica")
    , @NamedQuery(name = "FantasyTimUcinak.findByTim", query = "SELECT f FROM FantasyTimUcinak f WHERE f.fantasyTimUcinakPK.tim = :tim")
    , @NamedQuery(name = "FantasyTimUcinak.findByPogodakIzIgre", query = "SELECT f FROM FantasyTimUcinak f WHERE f.pogodakIzIgre = :pogodakIzIgre")
    , @NamedQuery(name = "FantasyTimUcinak.findByPokusajIzIgre", query = "SELECT f FROM FantasyTimUcinak f WHERE f.pokusajIzIgre = :pokusajIzIgre")
    , @NamedQuery(name = "FantasyTimUcinak.findByPogodak1p", query = "SELECT f FROM FantasyTimUcinak f WHERE f.pogodak1p = :pogodak1p")
    , @NamedQuery(name = "FantasyTimUcinak.findByPokusaj1p", query = "SELECT f FROM FantasyTimUcinak f WHERE f.pokusaj1p = :pokusaj1p")
    , @NamedQuery(name = "FantasyTimUcinak.findByPogodak3p", query = "SELECT f FROM FantasyTimUcinak f WHERE f.pogodak3p = :pogodak3p")
    , @NamedQuery(name = "FantasyTimUcinak.findBySkokovi", query = "SELECT f FROM FantasyTimUcinak f WHERE f.skokovi = :skokovi")
    , @NamedQuery(name = "FantasyTimUcinak.findByAsistencije", query = "SELECT f FROM FantasyTimUcinak f WHERE f.asistencije = :asistencije")
    , @NamedQuery(name = "FantasyTimUcinak.findByUkradeneLopte", query = "SELECT f FROM FantasyTimUcinak f WHERE f.ukradeneLopte = :ukradeneLopte")
    , @NamedQuery(name = "FantasyTimUcinak.findByBlokade", query = "SELECT f FROM FantasyTimUcinak f WHERE f.blokade = :blokade")
    , @NamedQuery(name = "FantasyTimUcinak.findByPoeni", query = "SELECT f FROM FantasyTimUcinak f WHERE f.poeni = :poeni")})
public class FantasyTimUcinak implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FantasyTimUcinakPK fantasyTimUcinakPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pogodak_iz_igre")
    private int pogodakIzIgre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pokusaj_iz_igre")
    private int pokusajIzIgre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pogodak_1p")
    private int pogodak1p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pokusaj_1p")
    private int pokusaj1p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pogodak_3p")
    private int pogodak3p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "skokovi")
    private int skokovi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "asistencije")
    private int asistencije;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ukradene_lopte")
    private int ukradeneLopte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "blokade")
    private int blokade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni")
    private int poeni;
    @JoinColumn(name = "utakmica", referencedColumnName = "fantasy_utakmica_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FantasyUtakmica fantasyUtakmica;
    @JoinColumn(name = "tim", referencedColumnName = "fantasy_tim_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FantasyTim fantasyTim;

    public FantasyTimUcinak() {
    }

    public FantasyTimUcinak(FantasyTimUcinakPK fantasyTimUcinakPK) {
        this.fantasyTimUcinakPK = fantasyTimUcinakPK;
    }

    public FantasyTimUcinak(FantasyTimUcinakPK fantasyTimUcinakPK, int pogodakIzIgre, int pokusajIzIgre, int pogodak1p, int pokusaj1p, int pogodak3p, int skokovi, int asistencije, int ukradeneLopte, int blokade, int poeni) {
        this.fantasyTimUcinakPK = fantasyTimUcinakPK;
        this.pogodakIzIgre = pogodakIzIgre;
        this.pokusajIzIgre = pokusajIzIgre;
        this.pogodak1p = pogodak1p;
        this.pokusaj1p = pokusaj1p;
        this.pogodak3p = pogodak3p;
        this.skokovi = skokovi;
        this.asistencije = asistencije;
        this.ukradeneLopte = ukradeneLopte;
        this.blokade = blokade;
        this.poeni = poeni;
    }

    public FantasyTimUcinak(int utakmica, int tim) {
        this.fantasyTimUcinakPK = new FantasyTimUcinakPK(utakmica, tim);
    }

    public void postaviNoveVrednosti(FantasyTimUcinak ftu) {
        setFantasyTimUcinakPK(ftu.getFantasyTimUcinakPK());
        setPogodakIzIgre(ftu.getPogodakIzIgre());
        setPokusajIzIgre(ftu.getPokusajIzIgre());
        setPogodak1p(ftu.getPogodak1p());
        setPokusaj1p(ftu.getPokusaj1p());
        setPogodak3p(ftu.getPogodak3p());
        setSkokovi(ftu.getSkokovi());
        setAsistencije(ftu.getAsistencije());
        setUkradeneLopte(ftu.getUkradeneLopte());
        setBlokade(ftu.getBlokade());
        setPoeni(ftu.getPoeni());
    }

    public FantasyTimUcinakPK getFantasyTimUcinakPK() {
        return fantasyTimUcinakPK;
    }

    public void setFantasyTimUcinakPK(FantasyTimUcinakPK fantasyTimUcinakPK) {
        this.fantasyTimUcinakPK = fantasyTimUcinakPK;
    }

    public int getPogodakIzIgre() {
        return pogodakIzIgre;
    }

    public void setPogodakIzIgre(int pogodakIzIgre) {
        this.pogodakIzIgre = pogodakIzIgre;
    }

    public int getPokusajIzIgre() {
        return pokusajIzIgre;
    }

    public void setPokusajIzIgre(int pokusajIzIgre) {
        this.pokusajIzIgre = pokusajIzIgre;
    }

    public int getPogodak1p() {
        return pogodak1p;
    }

    public void setPogodak1p(int pogodak1p) {
        this.pogodak1p = pogodak1p;
    }

    public int getPokusaj1p() {
        return pokusaj1p;
    }

    public void setPokusaj1p(int pokusaj1p) {
        this.pokusaj1p = pokusaj1p;
    }

    public int getPogodak3p() {
        return pogodak3p;
    }

    public void setPogodak3p(int pogodak3p) {
        this.pogodak3p = pogodak3p;
    }

    public int getSkokovi() {
        return skokovi;
    }

    public void setSkokovi(int skokovi) {
        this.skokovi = skokovi;
    }

    public int getAsistencije() {
        return asistencije;
    }

    public void setAsistencije(int asistencije) {
        this.asistencije = asistencije;
    }

    public int getUkradeneLopte() {
        return ukradeneLopte;
    }

    public void setUkradeneLopte(int ukradeneLopte) {
        this.ukradeneLopte = ukradeneLopte;
    }

    public int getBlokade() {
        return blokade;
    }

    public void setBlokade(int blokade) {
        this.blokade = blokade;
    }

    public int getPoeni() {
        return poeni;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }

    public FantasyUtakmica getFantasyUtakmica() {
        return fantasyUtakmica;
    }

    public void setFantasyUtakmica(FantasyUtakmica fantasyUtakmica) {
        this.fantasyUtakmica = fantasyUtakmica;
    }

    public FantasyTim getFantasyTim() {
        return fantasyTim;
    }

    public void setFantasyTim(FantasyTim fantasyTim) {
        this.fantasyTim = fantasyTim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fantasyTimUcinakPK != null ? fantasyTimUcinakPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FantasyTimUcinak)) {
            return false;
        }
        FantasyTimUcinak other = (FantasyTimUcinak) object;
        if ((this.fantasyTimUcinakPK == null && other.fantasyTimUcinakPK != null) || (this.fantasyTimUcinakPK != null && !this.fantasyTimUcinakPK.equals(other.fantasyTimUcinakPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.FantasyTimUcinak[ fantasyTimUcinakPK=" + fantasyTimUcinakPK + " ]";
    }

}
