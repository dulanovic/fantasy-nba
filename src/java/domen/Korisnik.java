package domen;

import constants.WebConstants;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Cacheable(false)
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")
    , @NamedQuery(name = "Korisnik.findByKorisnikId", query = "SELECT k FROM Korisnik k WHERE k.korisnikId = :korisnikId")
    , @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime")
    , @NamedQuery(name = "Korisnik.findByPrezime", query = "SELECT k FROM Korisnik k WHERE k.prezime = :prezime")
    , @NamedQuery(name = "Korisnik.findByDatumRodjenja", query = "SELECT k FROM Korisnik k WHERE k.datumRodjenja = :datumRodjenja")
    , @NamedQuery(name = "Korisnik.findByEmail", query = "SELECT k FROM Korisnik k WHERE k.email = :email")
    , @NamedQuery(name = "Korisnik.findByKorisnickoIme", query = "SELECT k FROM Korisnik k WHERE k.korisnickoIme = :korisnickoIme")
    , @NamedQuery(name = "Korisnik.findByKorisnickaSifra", query = "SELECT k FROM Korisnik k WHERE k.korisnickaSifra = :korisnickaSifra")
    , @NamedQuery(name = "Korisnik.findByTip", query = "SELECT k FROM Korisnik k WHERE k.tip = :tip")})
public class Korisnik implements Serializable {

    @JoinColumn(name = "tip", referencedColumnName = "tip_korisnika_id")
    @ManyToOne(optional = false)
    private TipKorisnika tip;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vlasnik")
    private List<FantasyTim> fantasyTimList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "administrator")
    private List<FantasyLiga> fantasyLigaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "korisnik_id")
    private Integer korisnikId;
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
    @Column(name = "datum_rodjenja")
    @Temporal(TemporalType.DATE)
    private Date datumRodjenja;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "korisnicko_ime")
    private String korisnickoIme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "korisnicka_sifra")
    private String korisnickaSifra;

    public Korisnik() {
    }

    public Korisnik(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public Korisnik(Integer korisnikId, String ime, String prezime, Date datumRodjenja, String email, String korisnickoIme, String korisnickaSifra) {
        this.korisnikId = korisnikId;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.korisnickaSifra = korisnickaSifra;
    }

    public Korisnik(String ime, String prezime, Date datumRodjenja, String email, String korisnickoIme, String korisnickaSifra) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.korisnickaSifra = korisnickaSifra;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
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

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    @XmlTransient
    public String getDatumRodjenjaString() {
        return WebConstants.sdf.format(datumRodjenja);
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (korisnikId != null ? korisnikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.korisnikId == null && other.korisnikId != null) || (this.korisnikId != null && !this.korisnikId.equals(other.korisnikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " (" + korisnickoIme + ")";
    }

    @XmlTransient
    public List<FantasyTim> getFantasyTimList() {
        return fantasyTimList;
    }

    public void setFantasyTimList(List<FantasyTim> fantasyTimList) {
        this.fantasyTimList = fantasyTimList;
    }

    @XmlTransient
    public List<FantasyLiga> getFantasyLigaList() {
        return fantasyLigaList;
    }

    public void setFantasyLigaList(List<FantasyLiga> fantasyLigaList) {
        this.fantasyLigaList = fantasyLigaList;
    }

    public TipKorisnika getTip() {
        return tip;
    }

    public void setTip(TipKorisnika tip) {
        this.tip = tip;
    }

}
