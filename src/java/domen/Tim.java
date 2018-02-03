package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import util.UtakmicaSkracenIspis;

@Entity
@Table(name = "tim")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tim.findAll", query = "SELECT t FROM Tim t ORDER BY t.timNaziv")
    , @NamedQuery(name = "Tim.findByTimId", query = "SELECT t FROM Tim t WHERE t.timId = :timId")
    , @NamedQuery(name = "Tim.findByTimNaziv", query = "SELECT t FROM Tim t WHERE t.timNaziv = :timNaziv")
    , @NamedQuery(name = "Tim.findByTimLogo", query = "SELECT t FROM Tim t WHERE t.timLogo = :timLogo")
    , @NamedQuery(name = "Tim.findBySkraceniNaziv", query = "SELECT t FROM Tim t WHERE t.skraceniNaziv = :skraceniNaziv")
    , @NamedQuery(name = "Tim.findByTrener", query = "SELECT t FROM Tim t WHERE t.trener = :trener")
    , @NamedQuery(name = "Tim.findByGrad", query = "SELECT t FROM Tim t WHERE t.grad = :grad")
    , @NamedQuery(name = "Tim.findByArena", query = "SELECT t FROM Tim t WHERE t.arena = :arena")
    , @NamedQuery(name = "Tim.findByGodinaOsnivanja", query = "SELECT t FROM Tim t WHERE t.godinaOsnivanja = :godinaOsnivanja")
    , @NamedQuery(name = "Tim.findByKapacitet", query = "SELECT t FROM Tim t WHERE t.kapacitet = :kapacitet")
    , @NamedQuery(name = "Tim.findByTimApiId", query = "SELECT t FROM Tim t WHERE t.timApiId = :timApiId")})
@SqlResultSetMappings({
    @SqlResultSetMapping(
            name = "standingsMapping",
            classes = @ConstructorResult(
                    targetClass = Tim.class,
                    columns = {
                        @ColumnResult(name = "tim0", type = Integer.class)
                        ,
                    @ColumnResult(name = "logo")
                        ,
                    @ColumnResult(name = "naziv")
                        ,                    
                    @ColumnResult(name = "pobede", type = Integer.class)
                        ,
                    @ColumnResult(name = "porazi", type = Integer.class)
                        ,
                    @ColumnResult(name = "uspesnost")
                        ,
                    @ColumnResult(name = "skor_konf")
                        ,
                    @ColumnResult(name = "skor_div")
                        ,
                    @ColumnResult(name = "skor_domacin")
                        ,
                    @ColumnResult(name = "skor_gost")
                        ,
                    @ColumnResult(name = "prosek_datih_poena", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_primljenih_poena", type = Double.class)
                        ,
                    @ColumnResult(name = "prosecna_razlika", type = Double.class)
                    }
            )
    )
    ,
@SqlResultSetMapping(
            name = "timStatistikaMapping",
            classes = @ConstructorResult(
                    targetClass = Tim.class,
                    columns = {
                        @ColumnResult(name = "tim", type = Integer.class)
                        ,
                    @ColumnResult(name = "ukupno_utakmica", type = Integer.class)
                        ,
                    @ColumnResult(name = "prosek_poeni", type = Double.class)
                        ,                    
                    @ColumnResult(name = "prosek_pogodak_iz_igre", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_pokusaj_iz_igre", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_procenat_iz_igre", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_pogodak_3p", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_pokusaj_3p", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_procenat_3p", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_pogodak_2p", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_pokusaj_2p", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_procenat_2p", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_blokirani_sutevi", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_pogodak_1p", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_pokusaj_1p", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_procenat_1p", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_skokovi_napad", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_skokovi_odbrana", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_skokovi_ukupno", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_asistencije", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_izgubljene_lopte", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_ukradene_lopte", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_blokade", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_licne_greske", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_poeni_iz_kontre", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_poeni_iz_reketa", type = Double.class)
                        ,
                    @ColumnResult(name = "prosek_poeni_iz_izgubljenih_lopti", type = Double.class)
                    }
            )
    )
})
public class Tim implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "godina_osnivanja")
    private int godinaOsnivanja;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tim")
    private List<TimUcinak> timUcinakList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "domacin")
    private List<Utakmica> utakmicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gost")
    private List<Utakmica> utakmicaList1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tim")
    private List<Igrac> igracList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tim_id")
    private Integer timId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tim_naziv")
    private String timNaziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tim_logo")
    private String timLogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "skraceni_naziv")
    private String skraceniNaziv;
    @Basic(optional = false)
    @NotNull
    @Size(max = 50)
    @Column(name = "trener")
    private String trener;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "grad")
    private String grad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "arena")
    private String arena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kapacitet")
    private int kapacitet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tim_api_id")
    private String timApiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "boja1")
    private String boja1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "boja2")
    private String boja2;
    @JoinColumn(name = "divizija", referencedColumnName = "divizija_id")
    @ManyToOne(optional = false)
    private Divizija divizija;

    public Tim() {
    }

    public Tim(Integer timId) {
        this.timId = timId;
    }

    public Tim(Integer timId, String timLogo, String timNaziv, String skraceniNaziv, String grad, String arena, int kapacitet, String timApiId, String boja1, String boja2) {
        this.timId = timId;
        this.timLogo = timLogo;
        this.timNaziv = timNaziv;
        this.skraceniNaziv = skraceniNaziv;
        this.grad = grad;
        this.arena = arena;
        this.kapacitet = kapacitet;
        this.timApiId = timApiId;
        this.boja1 = boja1;
        this.boja2 = boja2;
    }

    public Integer getTimId() {
        return timId;
    }

    public void setTimId(Integer timId) {
        this.timId = timId;
    }

    public String getTimNaziv() {
        return timNaziv;
    }

    public void setTimNaziv(String timNaziv) {
        this.timNaziv = timNaziv;
    }

    public String getTimLogo() {
        return timLogo;
    }

    public void setTimLogo(String timLogo) {
        this.timLogo = timLogo;
    }

    public String getSkraceniNaziv() {
        return skraceniNaziv;
    }

    public void setSkraceniNaziv(String skraceniNaziv) {
        this.skraceniNaziv = skraceniNaziv;
    }

    public String getTrener() {
        return trener;
    }

    public void setTrener(String trener) {
        this.trener = trener;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public Integer getGodinaOsnivanja() {
        return godinaOsnivanja;
    }

    public void setGodinaOsnivanja(Integer godinaOsnivanja) {
        this.godinaOsnivanja = godinaOsnivanja;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }

    public String getTimApiId() {
        return timApiId;
    }

    public void setTimApiId(String timApiId) {
        this.timApiId = timApiId;
    }

    public String getBoja1() {
        return boja1;
    }

    public void setBoja1(String boja1) {
        this.boja1 = boja1;
    }

    public String getBoja2() {
        return boja2;
    }

    public void setBoja2(String boja2) {
        this.boja2 = boja2;
    }

    public Divizija getDivizija() {
        return divizija;
    }

    public void setDivizija(Divizija divizija) {
        this.divizija = divizija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (timId != null ? timId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tim)) {
            return false;
        }
        Tim other = (Tim) object;
        if ((this.timId == null && other.timId != null) || (this.timId != null && !this.timId.equals(other.timId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return timNaziv + " (" + skraceniNaziv + ")";
    }

    @XmlTransient
    public List<Igrac> getIgracList() {
        return igracList;
    }

    public void setIgracList(List<Igrac> igracList) {
        this.igracList = igracList;
    }

    @Transient
    private int pozicija;
    @Transient
    private int brojPobeda;
    @Transient
    private int brojPoraza;
    @Transient
    private String procenatUspesnosti;
    @Transient
    private double zaostatak;
    @Transient
    private String skorKonferencija;
    @Transient
    private String skorDivizija;
    @Transient
    private String skorDomacin;
    @Transient
    private String skorGost;
    @Transient
    private double prosekDatihPoena;
    @Transient
    private double prosekPrimljenihPoena;
    @Transient
    private double prosekRazlika;

    public Tim(int timId, String timLogo, String timNaziv, int brojPobeda, int brojPoraza, String procenatUspesnosti, String skorKonferencija, String skorDivizija, String skorDomacin, String skorGost, double prosekDatihPoena, double prosekPrimljenihPoena, double prosekRazlika) {
        this.timId = timId;
        this.timLogo = timLogo;
        this.timNaziv = timNaziv;
        this.brojPobeda = brojPobeda;
        this.brojPoraza = brojPoraza;
        this.procenatUspesnosti = procenatUspesnosti;
        this.skorKonferencija = skorKonferencija;
        this.skorDivizija = skorDivizija;
        this.skorDomacin = skorDomacin;
        this.skorGost = skorGost;
        this.prosekDatihPoena = prosekDatihPoena;
        this.prosekPrimljenihPoena = prosekPrimljenihPoena;
        this.prosekRazlika = prosekRazlika;
    }

    @XmlTransient
    public int getBrojPobeda() {
        return brojPobeda;
    }

    public void setBrojPobeda(int brojPobeda) {
        this.brojPobeda = brojPobeda;
    }

    @XmlTransient
    public int getBrojPoraza() {
        return brojPoraza;
    }

    public void setBrojPoraza(int brojPoraza) {
        this.brojPoraza = brojPoraza;
    }

    @XmlTransient
    public String getProcenatUspesnosti() {
        return procenatUspesnosti.startsWith("0.") ? procenatUspesnosti.substring(1) : procenatUspesnosti;
    }

    public void setProcenatUspesnosti(String procenatUspesnosti) {
        this.procenatUspesnosti = procenatUspesnosti;
    }

    @XmlTransient
    public String getSkorKonferencija() {
        return skorKonferencija;
    }

    public void setSkorKonferencija(String skorKonferencija) {
        this.skorKonferencija = skorKonferencija;
    }

    @XmlTransient
    public String getSkorDivizija() {
        return skorDivizija;
    }

    public void setSkorDivizija(String skorDivizija) {
        this.skorDivizija = skorDivizija;
    }

    @XmlTransient
    public double getProsekDatihPoena() {
        return prosekDatihPoena;
    }

    public void setProsekDatihPoena(double prosekDatihPoena) {
        this.prosekDatihPoena = prosekDatihPoena;
    }

    @XmlTransient
    public double getProsekPrimljenihPoena() {
        return prosekPrimljenihPoena;
    }

    public void setProsekPrimljenihPoena(double prosekPrimljenihPoena) {
        this.prosekPrimljenihPoena = prosekPrimljenihPoena;
    }

    @XmlTransient
    public double getProsekRazlika() {
        return prosekRazlika;
    }

    public void setProsekRazlika(double prosekRazlika) {
        this.prosekRazlika = prosekRazlika;
    }

    @XmlTransient
    public int getPozicija() {
        return pozicija;
    }

    public void setPozicija(int pozicija) {
        this.pozicija = pozicija;
    }

    @XmlTransient
    public double getZaostatak() {
        return zaostatak;
    }

    public void setZaostatak(double zaostatak) {
        this.zaostatak = zaostatak;
    }

    @XmlTransient
    public String getSkorDomacin() {
        return skorDomacin;
    }

    public void setSkorDomacin(String skorDomacin) {
        this.skorDomacin = skorDomacin;
    }

    @XmlTransient
    public String getSkorGost() {
        return skorGost;
    }

    public void setSkorGost(String skorGost) {
        this.skorGost = skorGost;
    }

    @XmlTransient
    public List<Utakmica> getUtakmicaList() {
        return utakmicaList;
    }

    public void setUtakmicaList(List<Utakmica> utakmicaList) {
        this.utakmicaList = utakmicaList;
    }

    @XmlTransient
    public List<Utakmica> getUtakmicaList1() {
        return utakmicaList1;
    }

    public void setUtakmicaList1(List<Utakmica> utakmicaList1) {
        this.utakmicaList1 = utakmicaList1;
    }

    @XmlTransient
    public List<TimUcinak> getTimUcinakList() {
        return timUcinakList;
    }

    public void setTimUcinakList(List<TimUcinak> timUcinakList) {
        this.timUcinakList = timUcinakList;
    }

    @Transient
    private List<Utakmica> listaSvihUtakmica;

    @XmlTransient
    public List<Utakmica> getListaSvihUtakmica() {
        return listaSvihUtakmica;
    }

    public void setListaSvihUtakmica(List<Utakmica> listaSvihUtakmica) {
        this.listaSvihUtakmica = listaSvihUtakmica;
    }

    @Transient
    private int ukupnoUtakmica;
    @Transient
    private double prosekPoeni;
    @Transient
    private double prosekPogodakIzIgre;
    @Transient
    private double prosekPokusajIzIgre;
    @Transient
    private double prosekProcenatIzIgre;
    @Transient
    private double prosekPogodak3p;
    @Transient
    private double prosekPokusaj3p;
    @Transient
    private double prosekProcenat3p;
    @Transient
    private double prosekPogodak2p;
    @Transient
    private double prosekPokusaj2p;
    @Transient
    private double prosekProcenat2p;
    @Transient
    private double prosekBlokiraniSutevi;
    @Transient
    private double prosekPogodak1p;
    @Transient
    private double prosekPokusaj1p;
    @Transient
    private double prosekProcenat1p;
    @Transient
    private double prosekSkokoviNapad;
    @Transient
    private double prosekSkokoviOdbrana;
    @Transient
    private double prosekSkokoviUkupno;
    @Transient
    private double prosekAsistencije;
    @Transient
    private double prosekIzgubljeneLopte;
    @Transient
    private double prosekUkradeneLopte;
    @Transient
    private double prosekBlokade;
    @Transient
    private double prosekLicneGreske;
    @Transient
    private double prosekPoeniIzKontre;
    @Transient
    private double prosekPoeniIzReketa;
    @Transient
    private double prosekPoeniIzIzgubljenihLopti;

    public Tim(Integer timId, int ukupnoUtakmica, double prosekPoeni, double prosekPogodakIzIgre, double prosekPokusajIzIgre, double prosekProcenatIzIgre, double prosekPogodak3p, double prosekPokusaj3p, double prosekProcenat3p, double prosekPogodak2p, double prosekPokusaj2p, double prosekProcenat2p, double prosekBlokiraniSutevi, double prosekPogodak1p, double prosekPokusaj1p, double prosekProcenat1p, double prosekSkokoviNapad, double prosekSkokoviOdbrana, double prosekSkokoviUkupno, double prosekAsistencije, double prosekIzgubljeneLopte, double prosekUkradeneLopte, double prosekBlokade, double prosekLicneGreske, double prosekPoeniIzKontre, double prosekPoeniIzReketa, double prosekPoeniIzIzgubljenihLopti) {
        this.timId = timId;
        this.ukupnoUtakmica = ukupnoUtakmica;
        this.prosekPoeni = prosekPoeni;
        this.prosekPogodakIzIgre = prosekPogodakIzIgre;
        this.prosekPokusajIzIgre = prosekPokusajIzIgre;
        this.prosekProcenatIzIgre = prosekProcenatIzIgre;
        this.prosekPogodak3p = prosekPogodak3p;
        this.prosekPokusaj3p = prosekPokusaj3p;
        this.prosekProcenat3p = prosekProcenat3p;
        this.prosekPogodak2p = prosekPogodak2p;
        this.prosekPokusaj2p = prosekPokusaj2p;
        this.prosekProcenat2p = prosekProcenat2p;
        this.prosekBlokiraniSutevi = prosekBlokiraniSutevi;
        this.prosekPogodak1p = prosekPogodak1p;
        this.prosekPokusaj1p = prosekPokusaj1p;
        this.prosekProcenat1p = prosekProcenat1p;
        this.prosekSkokoviNapad = prosekSkokoviNapad;
        this.prosekSkokoviOdbrana = prosekSkokoviOdbrana;
        this.prosekSkokoviUkupno = prosekSkokoviUkupno;
        this.prosekAsistencije = prosekAsistencije;
        this.prosekIzgubljeneLopte = prosekIzgubljeneLopte;
        this.prosekUkradeneLopte = prosekUkradeneLopte;
        this.prosekBlokade = prosekBlokade;
        this.prosekLicneGreske = prosekLicneGreske;
        this.prosekPoeniIzKontre = prosekPoeniIzKontre;
        this.prosekPoeniIzReketa = prosekPoeniIzReketa;
        this.prosekPoeniIzIzgubljenihLopti = prosekPoeniIzIzgubljenihLopti;
    }

    @XmlTransient
    public int getUkupnoUtakmica() {
        return ukupnoUtakmica;
    }

    public void setUkupnoUtakmica(int ukupnoUtakmica) {
        this.ukupnoUtakmica = ukupnoUtakmica;
    }

    @XmlTransient
    public double getProsekPoeni() {
        return prosekPoeni;
    }

    public void setProsekPoeni(double prosekPoeni) {
        this.prosekPoeni = prosekPoeni;
    }

    @XmlTransient
    public double getProsekPogodakIzIgre() {
        return prosekPogodakIzIgre;
    }

    public void setProsekPogodakIzIgre(double prosekPogodakIzIgre) {
        this.prosekPogodakIzIgre = prosekPogodakIzIgre;
    }

    @XmlTransient
    public double getProsekPokusajIzIgre() {
        return prosekPokusajIzIgre;
    }

    public void setProsekPokusajIzIgre(double prosekPokusajIzIgre) {
        this.prosekPokusajIzIgre = prosekPokusajIzIgre;
    }

    @XmlTransient
    public double getProsekProcenatIzIgre() {
        return prosekProcenatIzIgre;
    }

    public void setProsekProcenatIzIgre(double prosekProcenatIzIgre) {
        this.prosekProcenatIzIgre = prosekProcenatIzIgre;
    }

    @XmlTransient
    public double getProsekPogodak3p() {
        return prosekPogodak3p;
    }

    public void setProsekPogodak3p(double prosekPogodak3p) {
        this.prosekPogodak3p = prosekPogodak3p;
    }

    @XmlTransient
    public double getProsekPokusaj3p() {
        return prosekPokusaj3p;
    }

    public void setProsekPokusaj3p(double prosekPokusaj3p) {
        this.prosekPokusaj3p = prosekPokusaj3p;
    }

    @XmlTransient
    public double getProsekProcenat3p() {
        return prosekProcenat3p;
    }

    public void setProsekProcenat3p(double prosekProcenat3p) {
        this.prosekProcenat3p = prosekProcenat3p;
    }

    @XmlTransient
    public double getProsekPogodak2p() {
        return prosekPogodak2p;
    }

    public void setProsekPogodak2p(double prosekPogodak2p) {
        this.prosekPogodak2p = prosekPogodak2p;
    }

    @XmlTransient
    public double getProsekPokusaj2p() {
        return prosekPokusaj2p;
    }

    public void setProsekPokusaj2p(double prosekPokusaj2p) {
        this.prosekPokusaj2p = prosekPokusaj2p;
    }

    @XmlTransient
    public double getProsekProcenat2p() {
        return prosekProcenat2p;
    }

    public void setProsekProcenat2p(double prosekProcenat2p) {
        this.prosekProcenat2p = prosekProcenat2p;
    }

    @XmlTransient
    public double getProsekBlokiraniSutevi() {
        return prosekBlokiraniSutevi;
    }

    public void setProsekBlokiraniSutevi(double prosekBlokiraniSutevi) {
        this.prosekBlokiraniSutevi = prosekBlokiraniSutevi;
    }

    @XmlTransient
    public double getProsekPogodak1p() {
        return prosekPogodak1p;
    }

    public void setProsekPogodak1p(double prosekPogodak1p) {
        this.prosekPogodak1p = prosekPogodak1p;
    }

    @XmlTransient
    public double getProsekPokusaj1p() {
        return prosekPokusaj1p;
    }

    public void setProsekPokusaj1p(double prosekPokusaj1p) {
        this.prosekPokusaj1p = prosekPokusaj1p;
    }

    @XmlTransient
    public double getProsekProcenat1p() {
        return prosekProcenat1p;
    }

    public void setProsekProcenat1p(double prosekProcenat1p) {
        this.prosekProcenat1p = prosekProcenat1p;
    }

    @XmlTransient
    public double getProsekSkokoviNapad() {
        return prosekSkokoviNapad;
    }

    public void setProsekSkokoviNapad(double prosekSkokoviNapad) {
        this.prosekSkokoviNapad = prosekSkokoviNapad;
    }

    @XmlTransient
    public double getProsekSkokoviOdbrana() {
        return prosekSkokoviOdbrana;
    }

    public void setProsekSkokoviOdbrana(double prosekSkokoviOdbrana) {
        this.prosekSkokoviOdbrana = prosekSkokoviOdbrana;
    }

    @XmlTransient
    public double getProsekSkokoviUkupno() {
        return prosekSkokoviUkupno;
    }

    public void setProsekSkokoviUkupno(double prosekSkokoviUkupno) {
        this.prosekSkokoviUkupno = prosekSkokoviUkupno;
    }

    @XmlTransient
    public double getProsekAsistencije() {
        return prosekAsistencije;
    }

    public void setProsekAsistencije(double prosekAsistencije) {
        this.prosekAsistencije = prosekAsistencije;
    }

    @XmlTransient
    public double getProsekIzgubljeneLopte() {
        return prosekIzgubljeneLopte;
    }

    public void setProsekIzgubljeneLopte(double prosekIzgubljeneLopte) {
        this.prosekIzgubljeneLopte = prosekIzgubljeneLopte;
    }

    @XmlTransient
    public double getProsekUkradeneLopte() {
        return prosekUkradeneLopte;
    }

    public void setProsekUkradeneLopte(double prosekUkradeneLopte) {
        this.prosekUkradeneLopte = prosekUkradeneLopte;
    }

    @XmlTransient
    public double getProsekBlokade() {
        return prosekBlokade;
    }

    public void setProsekBlokade(double prosekBlokade) {
        this.prosekBlokade = prosekBlokade;
    }

    @XmlTransient
    public double getProsekLicneGreske() {
        return prosekLicneGreske;
    }

    public void setProsekLicneGreske(double prosekLicneGreske) {
        this.prosekLicneGreske = prosekLicneGreske;
    }

    @XmlTransient
    public double getProsekPoeniIzKontre() {
        return prosekPoeniIzKontre;
    }

    public void setProsekPoeniIzKontre(double prosekPoeniIzKontre) {
        this.prosekPoeniIzKontre = prosekPoeniIzKontre;
    }

    @XmlTransient
    public double getProsekPoeniIzReketa() {
        return prosekPoeniIzReketa;
    }

    public void setProsekPoeniIzReketa(double prosekPoeniIzReketa) {
        this.prosekPoeniIzReketa = prosekPoeniIzReketa;
    }

    @XmlTransient
    public double getProsekPoeniIzIzgubljenihLopti() {
        return prosekPoeniIzIzgubljenihLopti;
    }

    public void setProsekPoeniIzIzgubljenihLopti(double prosekPoeniIzIzgubljenihLopti) {
        this.prosekPoeniIzIzgubljenihLopti = prosekPoeniIzIzgubljenihLopti;
    }

    @Transient
    private String trenutniSkor;

    @XmlTransient
    public String getTrenutniSkor() {
        return trenutniSkor;
    }

    public void setTrenutniSkor(String trenutniSkor) {
        this.trenutniSkor = trenutniSkor;
    }

    @Transient
    private UtakmicaSkracenIspis usi;

    @XmlTransient
    public UtakmicaSkracenIspis getUsi() {
        return usi;
    }

    public void setUsi(UtakmicaSkracenIspis usi) {
        this.usi = usi;
    }

}
