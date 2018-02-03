package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "igrac_ucinak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IgracUcinak.findAll", query = "SELECT i FROM IgracUcinak i")
    , @NamedQuery(name = "IgracUcinak.findByUtakmicaId", query = "SELECT i FROM IgracUcinak i WHERE i.igracUcinakPK.utakmicaId = :utakmicaId")
    , @NamedQuery(name = "IgracUcinak.findByIgracId", query = "SELECT i FROM IgracUcinak i WHERE i.igracUcinakPK.igracId = :igracId")
    , @NamedQuery(name = "IgracUcinak.findByStarter", query = "SELECT i FROM IgracUcinak i WHERE i.starter = :starter")
    , @NamedQuery(name = "IgracUcinak.findByMinuti", query = "SELECT i FROM IgracUcinak i WHERE i.minuti = :minuti")
    , @NamedQuery(name = "IgracUcinak.findByPogodakIzIgre", query = "SELECT i FROM IgracUcinak i WHERE i.pogodakIzIgre = :pogodakIzIgre")
    , @NamedQuery(name = "IgracUcinak.findByPokusajIzIgre", query = "SELECT i FROM IgracUcinak i WHERE i.pokusajIzIgre = :pokusajIzIgre")
    , @NamedQuery(name = "IgracUcinak.findByProcenatIzIgre", query = "SELECT i FROM IgracUcinak i WHERE i.procenatIzIgre = :procenatIzIgre")
    , @NamedQuery(name = "IgracUcinak.findByPogodak3p", query = "SELECT i FROM IgracUcinak i WHERE i.pogodak3p = :pogodak3p")
    , @NamedQuery(name = "IgracUcinak.findByPokusaj3p", query = "SELECT i FROM IgracUcinak i WHERE i.pokusaj3p = :pokusaj3p")
    , @NamedQuery(name = "IgracUcinak.findByProcenat3p", query = "SELECT i FROM IgracUcinak i WHERE i.procenat3p = :procenat3p")
    , @NamedQuery(name = "IgracUcinak.findByPogodak2p", query = "SELECT i FROM IgracUcinak i WHERE i.pogodak2p = :pogodak2p")
    , @NamedQuery(name = "IgracUcinak.findByPokusaj2p", query = "SELECT i FROM IgracUcinak i WHERE i.pokusaj2p = :pokusaj2p")
    , @NamedQuery(name = "IgracUcinak.findByProcenat2p", query = "SELECT i FROM IgracUcinak i WHERE i.procenat2p = :procenat2p")
    , @NamedQuery(name = "IgracUcinak.findByBlokiraniSutevi", query = "SELECT i FROM IgracUcinak i WHERE i.blokiraniSutevi = :blokiraniSutevi")
    , @NamedQuery(name = "IgracUcinak.findByPogodak1p", query = "SELECT i FROM IgracUcinak i WHERE i.pogodak1p = :pogodak1p")
    , @NamedQuery(name = "IgracUcinak.findByPokusaj1p", query = "SELECT i FROM IgracUcinak i WHERE i.pokusaj1p = :pokusaj1p")
    , @NamedQuery(name = "IgracUcinak.findByProcenat1p", query = "SELECT i FROM IgracUcinak i WHERE i.procenat1p = :procenat1p")
    , @NamedQuery(name = "IgracUcinak.findBySkokoviNapad", query = "SELECT i FROM IgracUcinak i WHERE i.skokoviNapad = :skokoviNapad")
    , @NamedQuery(name = "IgracUcinak.findBySkokoviOdbrana", query = "SELECT i FROM IgracUcinak i WHERE i.skokoviOdbrana = :skokoviOdbrana")
    , @NamedQuery(name = "IgracUcinak.findBySkokoviUkupno", query = "SELECT i FROM IgracUcinak i WHERE i.skokoviUkupno = :skokoviUkupno")
    , @NamedQuery(name = "IgracUcinak.findByAsistencije", query = "SELECT i FROM IgracUcinak i WHERE i.asistencije = :asistencije")
    , @NamedQuery(name = "IgracUcinak.findByIzgubljeneLopte", query = "SELECT i FROM IgracUcinak i WHERE i.izgubljeneLopte = :izgubljeneLopte")
    , @NamedQuery(name = "IgracUcinak.findByUkradeneLopte", query = "SELECT i FROM IgracUcinak i WHERE i.ukradeneLopte = :ukradeneLopte")
    , @NamedQuery(name = "IgracUcinak.findByBlokade", query = "SELECT i FROM IgracUcinak i WHERE i.blokade = :blokade")
    , @NamedQuery(name = "IgracUcinak.findByOdnosAsistIzglop", query = "SELECT i FROM IgracUcinak i WHERE i.odnosAsistIzglop = :odnosAsistIzglop")
    , @NamedQuery(name = "IgracUcinak.findByLicneGreske", query = "SELECT i FROM IgracUcinak i WHERE i.licneGreske = :licneGreske")
    , @NamedQuery(name = "IgracUcinak.findByPlusMinus", query = "SELECT i FROM IgracUcinak i WHERE i.plusMinus = :plusMinus")
    , @NamedQuery(name = "IgracUcinak.findByPoeni", query = "SELECT i FROM IgracUcinak i WHERE i.poeni = :poeni")})
public class IgracUcinak implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IgracUcinakPK igracUcinakPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "starter")
    private boolean starter;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "minuti")
    private String minuti;
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
    @Column(name = "procenat_iz_igre")
    private double procenatIzIgre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pogodak_3p")
    private int pogodak3p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pokusaj_3p")
    private int pokusaj3p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "procenat_3p")
    private double procenat3p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pogodak_2p")
    private int pogodak2p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pokusaj_2p")
    private int pokusaj2p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "procenat_2p")
    private double procenat2p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "blokirani_sutevi")
    private int blokiraniSutevi;
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
    @Column(name = "procenat_1p")
    private double procenat1p;
    @Basic(optional = false)
    @NotNull
    @Column(name = "skokovi_napad")
    private int skokoviNapad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "skokovi_odbrana")
    private int skokoviOdbrana;
    @Basic(optional = false)
    @NotNull
    @Column(name = "skokovi_ukupno")
    private int skokoviUkupno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "asistencije")
    private int asistencije;
    @Basic(optional = false)
    @NotNull
    @Column(name = "izgubljene_lopte")
    private int izgubljeneLopte;
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
    @Column(name = "odnos_asist_izglop")
    private double odnosAsistIzglop;
    @Basic(optional = false)
    @NotNull
    @Column(name = "licne_greske")
    private int licneGreske;
    @Basic(optional = false)
    @NotNull
    @Column(name = "plus_minus")
    private int plusMinus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni")
    private int poeni;
    @JoinColumn(name = "utakmica_id", referencedColumnName = "utakmica_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Utakmica utakmica;
    @JoinColumn(name = "igrac_id", referencedColumnName = "igrac_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Igrac igrac;

    public IgracUcinak() {
    }

    public IgracUcinak(IgracUcinakPK igracUcinakPK) {
        this.igracUcinakPK = igracUcinakPK;
    }

    public IgracUcinak(IgracUcinakPK igracUcinakPK, boolean starter, String minuti, int pogodakIzIgre, int pokusajIzIgre, double procenatIzIgre, int pogodak3p, int pokusaj3p, double procenat3p, int pogodak2p, int pokusaj2p, double procenat2p, int blokiraniSutevi, int pogodak1p, int pokusaj1p, double procenat1p, int skokoviNapad, int skokoviOdbrana, int skokoviUkupno, int asistencije, int izgubljeneLopte, int ukradeneLopte, int blokade, double odnosAsistIzglop, int licneGreske, int plusMinus, int poeni) {
        this.igracUcinakPK = igracUcinakPK;
        this.starter = starter;
        this.minuti = minuti;
        this.pogodakIzIgre = pogodakIzIgre;
        this.pokusajIzIgre = pokusajIzIgre;
        this.procenatIzIgre = procenatIzIgre;
        this.pogodak3p = pogodak3p;
        this.pokusaj3p = pokusaj3p;
        this.procenat3p = procenat3p;
        this.pogodak2p = pogodak2p;
        this.pokusaj2p = pokusaj2p;
        this.procenat2p = procenat2p;
        this.blokiraniSutevi = blokiraniSutevi;
        this.pogodak1p = pogodak1p;
        this.pokusaj1p = pokusaj1p;
        this.procenat1p = procenat1p;
        this.skokoviNapad = skokoviNapad;
        this.skokoviOdbrana = skokoviOdbrana;
        this.skokoviUkupno = skokoviUkupno;
        this.asistencije = asistencije;
        this.izgubljeneLopte = izgubljeneLopte;
        this.ukradeneLopte = ukradeneLopte;
        this.blokade = blokade;
        this.odnosAsistIzglop = odnosAsistIzglop;
        this.licneGreske = licneGreske;
        this.plusMinus = plusMinus;
        this.poeni = poeni;
    }

    public IgracUcinak(int utakmicaId, int igracId) {
        this.igracUcinakPK = new IgracUcinakPK(utakmicaId, igracId);
    }

    public IgracUcinakPK getIgracUcinakPK() {
        return igracUcinakPK;
    }

    public void setIgracUcinakPK(IgracUcinakPK igracUcinakPK) {
        this.igracUcinakPK = igracUcinakPK;
    }

    public String getStarter() {
        return starter ? "*" : "";
    }

    public void setStarter(boolean starter) {
        this.starter = starter;
    }

    public String getMinuti() {
        return minuti;
    }

    public void setMinuti(String minuti) {
        this.minuti = minuti;
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

    public double getProcenatIzIgre() {
        return procenatIzIgre;
    }

    public void setProcenatIzIgre(double procenatIzIgre) {
        this.procenatIzIgre = procenatIzIgre;
    }

    public int getPogodak3p() {
        return pogodak3p;
    }

    public void setPogodak3p(int pogodak3p) {
        this.pogodak3p = pogodak3p;
    }

    public int getPokusaj3p() {
        return pokusaj3p;
    }

    public void setPokusaj3p(int pokusaj3p) {
        this.pokusaj3p = pokusaj3p;
    }

    public double getProcenat3p() {
        return procenat3p;
    }

    public void setProcenat3p(double procenat3p) {
        this.procenat3p = procenat3p;
    }

    public int getPogodak2p() {
        return pogodak2p;
    }

    public void setPogodak2p(int pogodak2p) {
        this.pogodak2p = pogodak2p;
    }

    public int getPokusaj2p() {
        return pokusaj2p;
    }

    public void setPokusaj2p(int pokusaj2p) {
        this.pokusaj2p = pokusaj2p;
    }

    public double getProcenat2p() {
        return procenat2p;
    }

    public void setProcenat2p(double procenat2p) {
        this.procenat2p = procenat2p;
    }

    public int getBlokiraniSutevi() {
        return blokiraniSutevi;
    }

    public void setBlokiraniSutevi(int blokiraniSutevi) {
        this.blokiraniSutevi = blokiraniSutevi;
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

    public double getProcenat1p() {
        return procenat1p;
    }

    public void setProcenat1p(double procenat1p) {
        this.procenat1p = procenat1p;
    }

    public int getSkokoviNapad() {
        return skokoviNapad;
    }

    public void setSkokoviNapad(int skokoviNapad) {
        this.skokoviNapad = skokoviNapad;
    }

    public int getSkokoviOdbrana() {
        return skokoviOdbrana;
    }

    public void setSkokoviOdbrana(int skokoviOdbrana) {
        this.skokoviOdbrana = skokoviOdbrana;
    }

    public int getSkokoviUkupno() {
        return skokoviUkupno;
    }

    public void setSkokoviUkupno(int skokoviUkupno) {
        this.skokoviUkupno = skokoviUkupno;
    }

    public int getAsistencije() {
        return asistencije;
    }

    public void setAsistencije(int asistencije) {
        this.asistencije = asistencije;
    }

    public int getIzgubljeneLopte() {
        return izgubljeneLopte;
    }

    public void setIzgubljeneLopte(int izgubljeneLopte) {
        this.izgubljeneLopte = izgubljeneLopte;
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

    public double getOdnosAsistIzglop() {
        return odnosAsistIzglop;
    }

    public void setOdnosAsistIzglop(double odnosAsistIzglop) {
        this.odnosAsistIzglop = odnosAsistIzglop;
    }

    public int getLicneGreske() {
        return licneGreske;
    }

    public void setLicneGreske(int licneGreske) {
        this.licneGreske = licneGreske;
    }

    public String getPlusMinus() {
        String ispis = String.valueOf(plusMinus);
        if (plusMinus > 0) {
            ispis = "+" + plusMinus;
        }
        return ispis;
    }

    public void setPlusMinus(int plusMinus) {
        this.plusMinus = plusMinus;
    }

    public int getPoeni() {
        return poeni;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }

    public Utakmica getUtakmica() {
        return utakmica;
    }

    public void setUtakmica(Utakmica utakmica) {
        this.utakmica = utakmica;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (igracUcinakPK != null ? igracUcinakPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IgracUcinak)) {
            return false;
        }
        IgracUcinak other = (IgracUcinak) object;
        if ((this.igracUcinakPK == null && other.igracUcinakPK != null) || (this.igracUcinakPK != null && !this.igracUcinakPK.equals(other.igracUcinakPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.IgracUcinak[ igracUcinakPK=" + igracUcinakPK + " ]";
    }

}
