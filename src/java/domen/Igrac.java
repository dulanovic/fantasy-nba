package domen;

import constants.WebConstants;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import util.UtakmicaSkracenIspis;

@Entity
@Table(name = "igrac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Igrac.findAll", query = "SELECT i FROM Igrac i ORDER BY i.prezime DESC")
    , @NamedQuery(name = "Igrac.findByIgracId", query = "SELECT i FROM Igrac i WHERE i.igracId = :igracId")
    , @NamedQuery(name = "Igrac.findByIme", query = "SELECT i FROM Igrac i WHERE i.ime = :ime")
    , @NamedQuery(name = "Igrac.findByPrezime", query = "SELECT i FROM Igrac i WHERE i.prezime = :prezime")
    , @NamedQuery(name = "Igrac.findByIgracSlika", query = "SELECT i FROM Igrac i WHERE i.igracSlika = :igracSlika")
    , @NamedQuery(name = "Igrac.findByVisina", query = "SELECT i FROM Igrac i WHERE i.visina = :visina")
    , @NamedQuery(name = "Igrac.findByTezina", query = "SELECT i FROM Igrac i WHERE i.tezina = :tezina")
    , @NamedQuery(name = "Igrac.findByPozicija", query = "SELECT i FROM Igrac i WHERE i.pozicija = :pozicija")
    , @NamedQuery(name = "Igrac.findByBrojDres", query = "SELECT i FROM Igrac i WHERE i.brojDres = :brojDres")
    , @NamedQuery(name = "Igrac.findByIskustvo", query = "SELECT i FROM Igrac i WHERE i.iskustvo = :iskustvo")
    , @NamedQuery(name = "Igrac.findByKoledz", query = "SELECT i FROM Igrac i WHERE i.koledz = :koledz")
    , @NamedQuery(name = "Igrac.findByMestoRodjenja", query = "SELECT i FROM Igrac i WHERE i.mestoRodjenja = :mestoRodjenja")
    , @NamedQuery(name = "Igrac.findByDatumRodjenja", query = "SELECT i FROM Igrac i WHERE i.datumRodjenja = :datumRodjenja")
    , @NamedQuery(name = "Igrac.findByDraftPik", query = "SELECT i FROM Igrac i WHERE i.draftPik = :draftPik")
    , @NamedQuery(name = "Igrac.findByDraftGodina", query = "SELECT i FROM Igrac i WHERE i.draftGodina = :draftGodina")
    , @NamedQuery(name = "Igrac.findByIgracApiId", query = "SELECT i FROM Igrac i WHERE i.igracApiId = :igracApiId")})
@SqlResultSetMapping(
        name = "igracStatistikaMapping",
        classes = @ConstructorResult(
                targetClass = Igrac.class,
                columns = {
                    @ColumnResult(name = "igrac_id", type = Integer.class)
                    ,
                    @ColumnResult(name = "ukupno_igrao", type = Integer.class)
                    ,
                    @ColumnResult(name = "ukupno_startovao", type = Integer.class)
                    ,                    
                    @ColumnResult(name = "prosek_minuti", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_pogodak_iz_igre", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_pokusaj_iz_igre", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_procenat_iz_igre", type = String.class)
                    ,
                    @ColumnResult(name = "prosek_pogodak_3p", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_pokusaj_3p", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_procenat_3p", type = String.class)
                    ,
                    @ColumnResult(name = "prosek_pogodak_2p", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_pokusaj_2p", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_procenat_2p", type = String.class)
                    ,
                    @ColumnResult(name = "prosek_blokirani_sutevi", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_pogodak_1p", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_pokusaj_1p", type = Double.class)
                    ,
                    @ColumnResult(name = "prosek_procenat_1p", type = String.class)
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
                    @ColumnResult(name = "prosek_poeni", type = Double.class)
                }
        )
)
public class Igrac implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "minutaza_prethodna_sezona")
    private Double minutazaPrethodnaSezona;
    @Column(name = "sut_iz_igre_prethodna_sezona")
    private Double sutIzIgrePrethodnaSezona;
    @Column(name = "slobodna_bacanja_prethodna_sezona")
    private Double slobodnaBacanjaPrethodnaSezona;
    @Column(name = "pogodak_3p_prethodna_sezona")
    private Double pogodak3pPrethodnaSezona;
    @Column(name = "skokovi_prethodna_sezona")
    private Double skokoviPrethodnaSezona;
    @Column(name = "asistencije_prethodna_sezona")
    private Double asistencijePrethodnaSezona;
    @Column(name = "asist_izglop_prethodna_sezona")
    private Double asistIzglopPrethodnaSezona;
    @Column(name = "ukradene_lopte_prethodna_sezona")
    private Double ukradeneLoptePrethodnaSezona;
    @Column(name = "blokade_prethodna_sezona")
    private Double blokadePrethodnaSezona;
    @Column(name = "izgubljene_lopte_prethodna_sezona")
    private Double izgubljeneLoptePrethodnaSezona;
    @Column(name = "poeni_prethodna_sezona")
    private Double poeniPrethodnaSezona;
    @Column(name = "minutaza_projekcija")
    private Double minutazaProjekcija;
    @Column(name = "sut_iz_igre_projekcija")
    private Double sutIzIgreProjekcija;
    @Column(name = "slobodna_bacanja_projekcija")
    private Double slobodnaBacanjaProjekcija;
    @Column(name = "pogodak_3p_projekcija")
    private Double pogodak3pProjekcija;
    @Column(name = "skokovi_projekcija")
    private Double skokoviProjekcija;
    @Column(name = "asistencije_projekcija")
    private Double asistencijeProjekcija;
    @Column(name = "asist_izglop_projekcija")
    private Double asistIzglopProjekcija;
    @Column(name = "ukradene_lopte_projekcija")
    private Double ukradeneLopteProjekcija;
    @Column(name = "blokade_projekcija")
    private Double blokadeProjekcija;
    @Column(name = "izgubljene_lopte_projekcija")
    private Double izgubljeneLopteProjekcija;
    @Column(name = "poeni_projekcija")
    private Double poeniProjekcija;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "igrac")
    private List<FantasyIgrac> fantasyIgracList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "igrac")
    private List<IgracUcinak> igracUcinakList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "igrac_id")
    private Integer igracId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "igrac_slika")
    private String igracSlika;
    @Basic(optional = false)
    @NotNull
    @Column(name = "visina")
    private int visina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tezina")
    private int tezina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "pozicija")
    private String pozicija;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "broj_dres")
    private String brojDres;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iskustvo")
    private int iskustvo;
    @Size(max = 50)
    @Column(name = "koledz")
    private String koledz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mesto_rodjenja")
    private String mestoRodjenja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datum_rodjenja")
    @Temporal(TemporalType.DATE)
    private Date datumRodjenja;
    @Column(name = "draft_pik")
    private Integer draftPik;
    @Column(name = "draft_godina")
    private Integer draftGodina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "igrac_api_id")
    private String igracApiId;
    @JoinColumn(name = "tim", referencedColumnName = "tim_id")
    @ManyToOne(optional = false)
    private Tim tim;

    public Igrac() {
    }

    public Igrac(Integer igracId) {
        this.igracId = igracId;
    }

    public Igrac(Integer igracId, String ime, String prezime, int visina, int tezina, String pozicija, String brojDres, int iskustvo, String mestoRodjenja, Date datumRodjenja, String igracApiId) {
        this.igracId = igracId;
        this.ime = ime;
        this.prezime = prezime;
        this.visina = visina;
        this.tezina = tezina;
        this.pozicija = pozicija;
        this.brojDres = brojDres;
        this.iskustvo = iskustvo;
        this.mestoRodjenja = mestoRodjenja;
        this.datumRodjenja = datumRodjenja;
        this.igracApiId = igracApiId;
        this.usi = new UtakmicaSkracenIspis();
    }

    public Integer getIgracId() {
        return igracId;
    }

    public void setIgracId(Integer igracId) {
        this.igracId = igracId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIgracSlika() {
        return igracSlika;
    }

    public void setIgracSlika(String igracSlika) {
        this.igracSlika = igracSlika;
    }

    public int getVisina() {
        return visina;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }

    public int getTezina() {
        return tezina;
    }

    public void setTezina(int tezina) {
        this.tezina = tezina;
    }

    public String getPozicija() {
        String pozicijaReturn = "";
        switch (pozicija) {
            case "PG":
                pozicijaReturn = "Plejmejker";
                break;
            case "SG":
                pozicijaReturn = "Bek";
                break;
            case "SF":
                pozicijaReturn = "Krilo";
                break;
            case "PF":
                pozicijaReturn = "Krilni centar";
                break;
            case "C":
                pozicijaReturn = "Centar";
                break;
        }
        return pozicijaReturn;
    }

    @Transient
    private String pozicijaKrace;

    @XmlTransient
    public String getPozicijaKrace() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public String getBrojDres() {
        return brojDres;
    }

    public void setBrojDres(String brojDres) {
        this.brojDres = brojDres;
    }

    public String getIskustvo() {
        if (iskustvo == 0) {
            return "R";
        }
        return String.valueOf(iskustvo);
    }

    public void setIskustvo(int iskustvo) {
        this.iskustvo = iskustvo;
    }

    public String getKoledz() {
        return koledz != null ? koledz : "N/A";
    }

    public void setKoledz(String koledz) {
        this.koledz = koledz;
    }

    public String getMestoRodjenja() {
        return mestoRodjenja;
    }

    public void setMestoRodjenja(String mestoRodjenja) {
        this.mestoRodjenja = mestoRodjenja;
    }

    public String getDatumRodjenjaString() {
        return WebConstants.sdf.format(datumRodjenja);
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Integer getDraftPik() {
        return draftPik;
    }

    public void setDraftPik(Integer draftPik) {
        this.draftPik = draftPik;
    }

    public Integer getDraftGodina() {
        return draftGodina;
    }

    public void setDraftGodina(Integer draftGodina) {
        this.draftGodina = draftGodina;
    }

    public String getDraftPodaci() {
        if (draftGodina == null || draftPik == null) {
            return "N/A";
        }
        return draftPik + ". pik, " + draftGodina + ".";
    }

    public String getIgracApiId() {
        return igracApiId;
    }

    public void setIgracApiId(String igracApiId) {
        this.igracApiId = igracApiId;
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
        hash += (igracId != null ? igracId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Igrac)) {
            return false;
        }
        Igrac other = (Igrac) object;
        if ((this.igracId == null && other.igracId != null) || (this.igracId != null && !this.igracId.equals(other.igracId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "#" + brojDres + " " + ime + " " + prezime;
    }

    @XmlTransient
    public String getMinutazaPrethodnaSezona() {
        return (minutazaPrethodnaSezona != null) ? String.valueOf(minutazaPrethodnaSezona) : "--";
    }

    public void setMinutazaPrethodnaSezona(Double minutazaPrethodnaSezona) {
        this.minutazaPrethodnaSezona = minutazaPrethodnaSezona;
    }

    @XmlTransient
    public String getSutIzIgrePrethodnaSezona() {
        return (sutIzIgrePrethodnaSezona != null) ? String.valueOf(sutIzIgrePrethodnaSezona) : "--";
    }

    public void setSutIzIgrePrethodnaSezona(Double sutIzIgrePrethodnaSezona) {
        this.sutIzIgrePrethodnaSezona = sutIzIgrePrethodnaSezona;
    }

    @XmlTransient
    public String getSlobodnaBacanjaPrethodnaSezona() {
        return (slobodnaBacanjaPrethodnaSezona != null) ? String.valueOf(slobodnaBacanjaPrethodnaSezona) : "--";
    }

    public void setSlobodnaBacanjaPrethodnaSezona(Double slobodnaBacanjaPrethodnaSezona) {
        this.slobodnaBacanjaPrethodnaSezona = slobodnaBacanjaPrethodnaSezona;
    }

    @XmlTransient
    public String getPogodak3pPrethodnaSezona() {
        return (pogodak3pPrethodnaSezona != null) ? String.valueOf(pogodak3pPrethodnaSezona) : "--";
    }

    public void setPogodak3pPrethodnaSezona(Double pogodak3pPrethodnaSezona) {
        this.pogodak3pPrethodnaSezona = pogodak3pPrethodnaSezona;
    }

    @XmlTransient
    public String getSkokoviPrethodnaSezona() {
        return (skokoviPrethodnaSezona != null) ? String.valueOf(skokoviPrethodnaSezona) : "--";
    }

    public void setSkokoviPrethodnaSezona(Double skokoviPrethodnaSezona) {
        this.skokoviPrethodnaSezona = skokoviPrethodnaSezona;
    }

    @XmlTransient
    public String getAsistencijePrethodnaSezona() {
        return (asistencijePrethodnaSezona != null) ? String.valueOf(asistencijePrethodnaSezona) : "--";
    }

    public void setAsistencijePrethodnaSezona(Double asistencijePrethodnaSezona) {
        this.asistencijePrethodnaSezona = asistencijePrethodnaSezona;
    }

    @XmlTransient
    public String getAsistIzglopPrethodnaSezona() {
        return (asistIzglopPrethodnaSezona != null) ? String.valueOf(asistIzglopPrethodnaSezona) : "--";
    }

    public void setAsistIzglopPrethodnaSezona(Double asistIzglopPrethodnaSezona) {
        this.asistIzglopPrethodnaSezona = asistIzglopPrethodnaSezona;
    }

    @XmlTransient
    public String getUkradeneLoptePrethodnaSezona() {
        return (ukradeneLoptePrethodnaSezona != null) ? String.valueOf(ukradeneLoptePrethodnaSezona) : "--";
    }

    public void setUkradeneLoptePrethodnaSezona(Double ukradeneLoptePrethodnaSezona) {
        this.ukradeneLoptePrethodnaSezona = ukradeneLoptePrethodnaSezona;
    }

    @XmlTransient
    public String getBlokadePrethodnaSezona() {
        return (blokadePrethodnaSezona != null) ? String.valueOf(blokadePrethodnaSezona) : "--";
    }

    public void setBlokadePrethodnaSezona(Double blokadePrethodnaSezona) {
        this.blokadePrethodnaSezona = blokadePrethodnaSezona;
    }

    @XmlTransient
    public String getIzgubljeneLoptePrethodnaSezona() {
        return (izgubljeneLoptePrethodnaSezona != null) ? String.valueOf(izgubljeneLoptePrethodnaSezona) : "--";
    }

    public void setIzgubljeneLoptePrethodnaSezona(Double izgubljeneLoptePrethodnaSezona) {
        this.izgubljeneLoptePrethodnaSezona = izgubljeneLoptePrethodnaSezona;
    }

    @XmlTransient
    public String getPoeniPrethodnaSezona() {
        return (poeniPrethodnaSezona != null) ? String.valueOf(poeniPrethodnaSezona) : "--";
    }

    public void setPoeniPrethodnaSezona(Double poeniPrethodnaSezona) {
        this.poeniPrethodnaSezona = poeniPrethodnaSezona;
    }

    @XmlTransient
    public String getMinutazaProjekcija() {
        return (minutazaProjekcija != null) ? String.valueOf(minutazaProjekcija) : "--";
    }

    public void setMinutazaProjekcija(Double minutazaProjekcija) {
        this.minutazaProjekcija = minutazaProjekcija;
    }

    @XmlTransient
    public String getSutIzIgreProjekcija() {
        return (sutIzIgreProjekcija != null) ? String.valueOf(sutIzIgreProjekcija) : "--";
    }

    public void setSutIzIgreProjekcija(Double sutIzIgreProjekcija) {
        this.sutIzIgreProjekcija = sutIzIgreProjekcija;
    }

    @XmlTransient
    public String getSlobodnaBacanjaProjekcija() {
        return (slobodnaBacanjaProjekcija != null) ? String.valueOf(slobodnaBacanjaProjekcija) : "--";
    }

    public void setSlobodnaBacanjaProjekcija(Double slobodnaBacanjaProjekcija) {
        this.slobodnaBacanjaProjekcija = slobodnaBacanjaProjekcija;
    }

    @XmlTransient
    public String getPogodak3pProjekcija() {
        return (pogodak3pProjekcija != null) ? String.valueOf(pogodak3pProjekcija) : "--";
    }

    public void setPogodak3pProjekcija(Double pogodak3pProjekcija) {
        this.pogodak3pProjekcija = pogodak3pProjekcija;
    }

    @XmlTransient
    public String getSkokoviProjekcija() {
        return (skokoviProjekcija != null) ? String.valueOf(skokoviProjekcija) : "--";
    }

    public void setSkokoviProjekcija(Double skokoviProjekcija) {
        this.skokoviProjekcija = skokoviProjekcija;
    }

    @XmlTransient
    public String getAsistencijeProjekcija() {
        return (asistencijeProjekcija != null) ? String.valueOf(asistencijeProjekcija) : "--";
    }

    public void setAsistencijeProjekcija(Double asistencijeProjekcija) {
        this.asistencijeProjekcija = asistencijeProjekcija;
    }

    @XmlTransient
    public String getAsistIzglopProjekcija() {
        return (asistIzglopProjekcija != null) ? String.valueOf(asistIzglopProjekcija) : "--";
    }

    public void setAsistIzglopProjekcija(Double asistIzglopProjekcija) {
        this.asistIzglopProjekcija = asistIzglopProjekcija;
    }

    @XmlTransient
    public String getUkradeneLopteProjekcija() {
        return (ukradeneLopteProjekcija != null) ? String.valueOf(ukradeneLopteProjekcija) : "--";
    }

    public void setUkradeneLopteProjekcija(Double ukradeneLopteProjekcija) {
        this.ukradeneLopteProjekcija = ukradeneLopteProjekcija;
    }

    @XmlTransient
    public String getBlokadeProjekcija() {
        return (blokadeProjekcija != null) ? String.valueOf(blokadeProjekcija) : "--";
    }

    public void setBlokadeProjekcija(Double blokadeProjekcija) {
        this.blokadeProjekcija = blokadeProjekcija;
    }

    @XmlTransient
    public String getIzgubljeneLopteProjekcija() {
        return (izgubljeneLopteProjekcija != null) ? String.valueOf(izgubljeneLopteProjekcija) : "--";
    }

    public void setIzgubljeneLopteProjekcija(Double izgubljeneLopteProjekcija) {
        this.izgubljeneLopteProjekcija = izgubljeneLopteProjekcija;
    }

    @XmlTransient
    public String getPoeniProjekcija() {
        return (poeniProjekcija != null) ? String.valueOf(poeniProjekcija) : "--";
    }

    public void setPoeniProjekcija(Double poeniProjekcija) {
        this.poeniProjekcija = poeniProjekcija;
    }

    @XmlTransient
    public List<FantasyIgrac> getFantasyIgracList() {
        return fantasyIgracList;
    }

    public void setFantasyIgracList(List<FantasyIgrac> fantasyIgracList) {
        this.fantasyIgracList = fantasyIgracList;
    }

    @XmlTransient
    public List<IgracUcinak> getIgracUcinakList() {
        return igracUcinakList;
    }

    public void setIgracUcinakList(List<IgracUcinak> igracUcinakList) {
        this.igracUcinakList = igracUcinakList;
    }

    @Transient
    private Utakmica sledecaUtakmica;

    @XmlTransient
    public Utakmica getSledecaUtakmica() {
        return sledecaUtakmica;
    }

    public void setSledecaUtakmica(Utakmica sledecaUtakmica) {
        this.sledecaUtakmica = sledecaUtakmica;
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

    @Transient
    private int ukupnoIgrao;
    @Transient
    private int ukupnoStartovao;
    @Transient
    private double prosekMinuti;
    @Transient
    private double prosekPogodakIzIgre;
    @Transient
    private double prosekPokusajIzIgre;
    @Transient
    private String prosekProcenatIzIgre;
    @Transient
    private double prosekPogodak3p;
    @Transient
    private double prosekPokusaj3p;
    @Transient
    private String prosekProcenat3p;
    @Transient
    private double prosekPogodak2p;
    @Transient
    private double prosekPokusaj2p;
    @Transient
    private String prosekProcenat2p;
    @Transient
    private double prosekBlokiraniSutevi;
    @Transient
    private double prosekPogodak1p;
    @Transient
    private double prosekPokusaj1p;
    @Transient
    private String prosekProcenat1p;
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
    private double prosekPoeni;

    public Igrac(Integer igracId, int ukupnoIgrao, int ukupnoStartovao, double prosekMinuti, double prosekPogodakIzIgre, double prosekPokusajIzIgre, String prosekProcenatIzIgre, double prosekPogodak3p, double prosekPokusaj3p, String prosekProcenat3p, double prosekPogodak2p, double prosekPokusaj2p, String prosekProcenat2p, double prosekBlokiraniSutevi, double prosekPogodak1p, double prosekPokusaj1p, String prosekProcenat1p, double prosekSkokoviNapad, double prosekSkokoviOdbrana, double prosekSkokoviUkupno, double prosekAsistencije, double prosekIzgubljeneLopte, double prosekUkradeneLopte, double prosekBlokade, double prosekLicneGreske, double prosekPoeni) {
        this.igracId = igracId;
        this.ukupnoIgrao = ukupnoIgrao;
        this.ukupnoStartovao = ukupnoStartovao;
        this.prosekMinuti = prosekMinuti;
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
        this.prosekPoeni = prosekPoeni;
    }

    @XmlTransient
    public int getUkupnoIgrao() {
        return ukupnoIgrao;
    }

    public void setUkupnoIgrao(int ukupnoIgrao) {
        this.ukupnoIgrao = ukupnoIgrao;
    }

    @XmlTransient
    public int getUkupnoStartovao() {
        return ukupnoStartovao;
    }

    public void setUkupnoStartovao(int ukupnoStartovao) {
        this.ukupnoStartovao = ukupnoStartovao;
    }

    @XmlTransient
    public double getProsekMinuti() {
        return prosekMinuti;
    }

    public void setProsekMinuti(double prosekMinuti) {
        this.prosekMinuti = prosekMinuti;
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
    public String getProsekProcenatIzIgre() {
        return (prosekProcenatIzIgre != null) ? String.valueOf(prosekProcenatIzIgre) : "---";
    }

    public void setProsekProcenatIzIgre(String prosekProcenatIzIgre) {
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
    public String getProsekProcenat3p() {
        return (prosekProcenat3p != null) ? String.valueOf(prosekProcenat3p) : "---";
    }

    public void setProsekProcenat3p(String prosekProcenat3p) {
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
    public String getProsekProcenat2p() {
        return (prosekProcenat2p != null) ? String.valueOf(prosekProcenat2p) : "---";
    }

    public void setProsekProcenat2p(String prosekProcenat2p) {
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
    public String getProsekProcenat1p() {
        return (prosekProcenat1p != null) ? String.valueOf(prosekProcenat1p) : "---";
    }

    public void setProsekProcenat1p(String prosekProcenat1p) {
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
    public double getProsekPoeni() {
        return prosekPoeni;
    }

    public void setProsekPoeni(double prosekPoeni) {
        this.prosekPoeni = prosekPoeni;
    }

}
