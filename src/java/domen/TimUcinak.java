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
@Table(name = "tim_ucinak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimUcinak.findAll", query = "SELECT t FROM TimUcinak t")
    , @NamedQuery(name = "TimUcinak.findByUtakmica", query = "SELECT t FROM TimUcinak t WHERE t.timUcinakPK.utakmica = :utakmica")
    , @NamedQuery(name = "TimUcinak.findByTim", query = "SELECT t FROM TimUcinak t WHERE t.timUcinakPK.tim = :tim")
    , @NamedQuery(name = "TimUcinak.findByPoeni", query = "SELECT t FROM TimUcinak t WHERE t.poeni = :poeni")
    , @NamedQuery(name = "TimUcinak.findByPoeniCetvrtina1", query = "SELECT t FROM TimUcinak t WHERE t.poeniCetvrtina1 = :poeniCetvrtina1")
    , @NamedQuery(name = "TimUcinak.findByPoeniCetvrtina2", query = "SELECT t FROM TimUcinak t WHERE t.poeniCetvrtina2 = :poeniCetvrtina2")
    , @NamedQuery(name = "TimUcinak.findByPoeniCetvrtina3", query = "SELECT t FROM TimUcinak t WHERE t.poeniCetvrtina3 = :poeniCetvrtina3")
    , @NamedQuery(name = "TimUcinak.findByPoeniCetvrtina4", query = "SELECT t FROM TimUcinak t WHERE t.poeniCetvrtina4 = :poeniCetvrtina4")
    , @NamedQuery(name = "TimUcinak.findByPoeniProduzetak", query = "SELECT t FROM TimUcinak t WHERE t.poeniProduzetak = :poeniProduzetak")
    , @NamedQuery(name = "TimUcinak.findByMinuti", query = "SELECT t FROM TimUcinak t WHERE t.minuti = :minuti")
    , @NamedQuery(name = "TimUcinak.findByPogodakIzIgre", query = "SELECT t FROM TimUcinak t WHERE t.pogodakIzIgre = :pogodakIzIgre")
    , @NamedQuery(name = "TimUcinak.findByPokusajIzIgre", query = "SELECT t FROM TimUcinak t WHERE t.pokusajIzIgre = :pokusajIzIgre")
    , @NamedQuery(name = "TimUcinak.findByProcenatIzIgre", query = "SELECT t FROM TimUcinak t WHERE t.procenatIzIgre = :procenatIzIgre")
    , @NamedQuery(name = "TimUcinak.findByPogodak3p", query = "SELECT t FROM TimUcinak t WHERE t.pogodak3p = :pogodak3p")
    , @NamedQuery(name = "TimUcinak.findByPokusaj3p", query = "SELECT t FROM TimUcinak t WHERE t.pokusaj3p = :pokusaj3p")
    , @NamedQuery(name = "TimUcinak.findByProcenat3p", query = "SELECT t FROM TimUcinak t WHERE t.procenat3p = :procenat3p")
    , @NamedQuery(name = "TimUcinak.findByPogodak2p", query = "SELECT t FROM TimUcinak t WHERE t.pogodak2p = :pogodak2p")
    , @NamedQuery(name = "TimUcinak.findByPokusaj2p", query = "SELECT t FROM TimUcinak t WHERE t.pokusaj2p = :pokusaj2p")
    , @NamedQuery(name = "TimUcinak.findByProcenat2p", query = "SELECT t FROM TimUcinak t WHERE t.procenat2p = :procenat2p")
    , @NamedQuery(name = "TimUcinak.findByBlokiraniSutevi", query = "SELECT t FROM TimUcinak t WHERE t.blokiraniSutevi = :blokiraniSutevi")
    , @NamedQuery(name = "TimUcinak.findByPogodak1p", query = "SELECT t FROM TimUcinak t WHERE t.pogodak1p = :pogodak1p")
    , @NamedQuery(name = "TimUcinak.findByPokusaj1p", query = "SELECT t FROM TimUcinak t WHERE t.pokusaj1p = :pokusaj1p")
    , @NamedQuery(name = "TimUcinak.findByProcenat1p", query = "SELECT t FROM TimUcinak t WHERE t.procenat1p = :procenat1p")
    , @NamedQuery(name = "TimUcinak.findBySkokoviNapad", query = "SELECT t FROM TimUcinak t WHERE t.skokoviNapad = :skokoviNapad")
    , @NamedQuery(name = "TimUcinak.findBySkokoviOdbrana", query = "SELECT t FROM TimUcinak t WHERE t.skokoviOdbrana = :skokoviOdbrana")
    , @NamedQuery(name = "TimUcinak.findBySkokoviUkupno", query = "SELECT t FROM TimUcinak t WHERE t.skokoviUkupno = :skokoviUkupno")
    , @NamedQuery(name = "TimUcinak.findByAsistencije", query = "SELECT t FROM TimUcinak t WHERE t.asistencije = :asistencije")
    , @NamedQuery(name = "TimUcinak.findByIzgubljeneLopte", query = "SELECT t FROM TimUcinak t WHERE t.izgubljeneLopte = :izgubljeneLopte")
    , @NamedQuery(name = "TimUcinak.findByUkradeneLopte", query = "SELECT t FROM TimUcinak t WHERE t.ukradeneLopte = :ukradeneLopte")
    , @NamedQuery(name = "TimUcinak.findByBlokade", query = "SELECT t FROM TimUcinak t WHERE t.blokade = :blokade")
    , @NamedQuery(name = "TimUcinak.findByOdnosAsistIzglop", query = "SELECT t FROM TimUcinak t WHERE t.odnosAsistIzglop = :odnosAsistIzglop")
    , @NamedQuery(name = "TimUcinak.findByLicneGreske", query = "SELECT t FROM TimUcinak t WHERE t.licneGreske = :licneGreske")
    , @NamedQuery(name = "TimUcinak.findByPoeniIzKontre", query = "SELECT t FROM TimUcinak t WHERE t.poeniIzKontre = :poeniIzKontre")
    , @NamedQuery(name = "TimUcinak.findByPoeniIzReketa", query = "SELECT t FROM TimUcinak t WHERE t.poeniIzReketa = :poeniIzReketa")
    , @NamedQuery(name = "TimUcinak.findByPoeniIzIzgubljenihLopti", query = "SELECT t FROM TimUcinak t WHERE t.poeniIzIzgubljenihLopti = :poeniIzIzgubljenihLopti")
    , @NamedQuery(name = "TimUcinak.findByTimUcinakPK", query = "SELECT tu FROM TimUcinak tu WHERE tu.timUcinakPK = :timUcinakPK")})
public class TimUcinak implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TimUcinakPK timUcinakPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni")
    private int poeni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni_cetvrtina_1")
    private int poeniCetvrtina1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni_cetvrtina_2")
    private int poeniCetvrtina2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni_cetvrtina_3")
    private int poeniCetvrtina3;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni_cetvrtina_4")
    private int poeniCetvrtina4;
    @Column(name = "poeni_produzetak")
    private Integer poeniProduzetak;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
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
    @Column(name = "poeni_iz_kontre")
    private int poeniIzKontre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni_iz_reketa")
    private int poeniIzReketa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "poeni_iz_izgubljenih_lopti")
    private int poeniIzIzgubljenihLopti;
    @JoinColumn(name = "utakmica", referencedColumnName = "utakmica_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Utakmica utakmica1;
    @JoinColumn(name = "tim", referencedColumnName = "tim_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tim tim;

    public TimUcinak() {
    }

    public TimUcinak(TimUcinakPK timUcinakPK) {
        this.timUcinakPK = timUcinakPK;
    }

    public TimUcinak(TimUcinakPK timUcinakPK, int poeni, int poeniCetvrtina1, int poeniCetvrtina2, int poeniCetvrtina3, int poeniCetvrtina4, String minuti, int pogodakIzIgre, int pokusajIzIgre, double procenatIzIgre, int pogodak3p, int pokusaj3p, double procenat3p, int pogodak2p, int pokusaj2p, double procenat2p, int blokiraniSutevi, int pogodak1p, int pokusaj1p, double procenat1p, int skokoviNapad, int skokoviOdbrana, int skokoviUkupno, int asistencije, int izgubljeneLopte, int ukradeneLopte, int blokade, double odnosAsistIzglop, int licneGreske, int poeniIzKontre, int poeniIzReketa, int poeniIzIzgubljenihLopti) {
        this.timUcinakPK = timUcinakPK;
        this.poeni = poeni;
        this.poeniCetvrtina1 = poeniCetvrtina1;
        this.poeniCetvrtina2 = poeniCetvrtina2;
        this.poeniCetvrtina3 = poeniCetvrtina3;
        this.poeniCetvrtina4 = poeniCetvrtina4;
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
        this.poeniIzKontre = poeniIzKontre;
        this.poeniIzReketa = poeniIzReketa;
        this.poeniIzIzgubljenihLopti = poeniIzIzgubljenihLopti;
    }

    public TimUcinak(int utakmica, int tim) {
        this.timUcinakPK = new TimUcinakPK(utakmica, tim);
    }

    public TimUcinakPK getTimUcinakPK() {
        return timUcinakPK;
    }

    public void setTimUcinakPK(TimUcinakPK timUcinakPK) {
        this.timUcinakPK = timUcinakPK;
    }

    public int getPoeni() {
        return poeni;
    }

    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }

    public int getPoeniCetvrtina1() {
        return poeniCetvrtina1;
    }

    public void setPoeniCetvrtina1(int poeniCetvrtina1) {
        this.poeniCetvrtina1 = poeniCetvrtina1;
    }

    public int getPoeniCetvrtina2() {
        return poeniCetvrtina2;
    }

    public void setPoeniCetvrtina2(int poeniCetvrtina2) {
        this.poeniCetvrtina2 = poeniCetvrtina2;
    }

    public int getPoeniCetvrtina3() {
        return poeniCetvrtina3;
    }

    public void setPoeniCetvrtina3(int poeniCetvrtina3) {
        this.poeniCetvrtina3 = poeniCetvrtina3;
    }

    public int getPoeniCetvrtina4() {
        return poeniCetvrtina4;
    }

    public void setPoeniCetvrtina4(int poeniCetvrtina4) {
        this.poeniCetvrtina4 = poeniCetvrtina4;
    }

    public Integer getPoeniProduzetak() {
        return poeniProduzetak;
    }

    public void setPoeniProduzetak(Integer poeniProduzetak) {
        this.poeniProduzetak = poeniProduzetak;
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

    public int getPoeniIzKontre() {
        return poeniIzKontre;
    }

    public void setPoeniIzKontre(int poeniIzKontre) {
        this.poeniIzKontre = poeniIzKontre;
    }

    public int getPoeniIzReketa() {
        return poeniIzReketa;
    }

    public void setPoeniIzReketa(int poeniIzReketa) {
        this.poeniIzReketa = poeniIzReketa;
    }

    public int getPoeniIzIzgubljenihLopti() {
        return poeniIzIzgubljenihLopti;
    }

    public void setPoeniIzIzgubljenihLopti(int poeniIzIzgubljenihLopti) {
        this.poeniIzIzgubljenihLopti = poeniIzIzgubljenihLopti;
    }

    public Utakmica getUtakmica1() {
        return utakmica1;
    }

    public void setUtakmica1(Utakmica utakmica1) {
        this.utakmica1 = utakmica1;
    }

    public Tim getTim() {
        return tim;
    }

    public void setTim(Tim tim) {
        this.tim = tim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timUcinakPK != null ? timUcinakPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimUcinak)) {
            return false;
        }
        TimUcinak other = (TimUcinak) object;
        if ((this.timUcinakPK == null && other.timUcinakPK != null) || (this.timUcinakPK != null && !this.timUcinakPK.equals(other.timUcinakPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.TimUcinak[ timUcinakPK=" + timUcinakPK + " ]";
    }

}
