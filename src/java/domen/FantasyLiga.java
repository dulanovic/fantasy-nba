package domen;

import constants.WebConstants;
import java.io.Serializable;
import java.text.ParseException;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Cacheable(false)
@Table(name = "fantasy_liga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FantasyLiga.findAll", query = "SELECT f FROM FantasyLiga f")
    , @NamedQuery(name = "FantasyLiga.findByLigaId", query = "SELECT f FROM FantasyLiga f WHERE f.ligaId = :ligaId")
    , @NamedQuery(name = "FantasyLiga.findByLigaNaziv", query = "SELECT f FROM FantasyLiga f WHERE f.ligaNaziv = :ligaNaziv")
    , @NamedQuery(name = "FantasyLiga.findByDatumAzuriranja", query = "SELECT f FROM FantasyLiga f WHERE f.datumAzuriranja = :datumAzuriranja")
    , @NamedQuery(name = "FantasyLiga.findByStatus", query = "SELECT fl FROM FantasyLiga fl WHERE fl.status = :status")})
public class FantasyLiga implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liga")
    private List<FantasyIgrac> fantasyIgracList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "liga_id")
    private Integer ligaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "liga_naziv")
    private String ligaNaziv;
    @Column(name = "datum_azuriranja")
    @Temporal(TemporalType.DATE)
    private Date datumAzuriranja;
    @JoinColumn(name = "status", referencedColumnName = "status_lige_id")
    @ManyToOne(optional = false)
    private StatusLige status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liga")
    private List<FantasyTim> fantasyTimList;
    @JoinColumn(name = "administrator", referencedColumnName = "korisnik_id")
    @ManyToOne(optional = false)
    private Korisnik administrator;

    public FantasyLiga() {
    }

    public FantasyLiga(Integer ligaId) {
        this.ligaId = ligaId;
    }

    public FantasyLiga(Integer ligaId, String ligaNaziv, Date datumAzuriranja, StatusLige status) {
        this.ligaId = ligaId;
        this.ligaNaziv = ligaNaziv;
        this.datumAzuriranja = datumAzuriranja;
        this.status = status;
    }

    public FantasyLiga(String ligaNaziv, StatusLige status, Korisnik administrator) {
        this.ligaNaziv = ligaNaziv;
        this.status = status;
        this.administrator = administrator;
    }

    public FantasyLiga(String ligaNaziv, Date datumAzuriranja, StatusLige status, Korisnik administrator) {
        this.ligaNaziv = ligaNaziv;
        this.datumAzuriranja = datumAzuriranja;
        this.status = status;
        this.administrator = administrator;
    }

    public FantasyLiga(Integer ligaId, String ligaNaziv, Date datumAzuriranja, StatusLige status, Korisnik administrator) {
        this.ligaId = ligaId;
        this.ligaNaziv = ligaNaziv;
        this.datumAzuriranja = datumAzuriranja;
        this.status = status;
        this.administrator = administrator;
    }

    public Integer getLigaId() {
        return ligaId;
    }

    public void setLigaId(Integer ligaId) {
        this.ligaId = ligaId;
    }

    public String getLigaNaziv() {
        return ligaNaziv;
    }

    public void setLigaNaziv(String ligaNaziv) {
        this.ligaNaziv = ligaNaziv;
    }

    public Date getDatumAzuriranja() {
        return datumAzuriranja;
    }

    @XmlTransient
    public String getDatumAzuriranjaString() throws ParseException {
        return (datumAzuriranja != null) ? WebConstants.sdf.format(datumAzuriranja) : WebConstants.sdf.format(WebConstants.vratiSistemskiDatum());
    }

    public void setDatumAzuriranja(Date datumAzuriranja) {
        this.datumAzuriranja = datumAzuriranja;
    }

    public StatusLige getStatus() {
        return status;
    }

    public void setStatus(StatusLige status) {
        this.status = status;
    }

    @XmlTransient
    public List<FantasyTim> getFantasyTimList() {
        return fantasyTimList;
    }

    public void setFantasyTimList(List<FantasyTim> fantasyTimList) {
        this.fantasyTimList = fantasyTimList;
    }

    public Korisnik getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Korisnik administrator) {
        this.administrator = administrator;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ligaId != null ? ligaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FantasyLiga)) {
            return false;
        }
        FantasyLiga other = (FantasyLiga) object;
        if ((this.ligaId == null && other.ligaId != null) || (this.ligaId != null && !this.ligaId.equals(other.ligaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.FantasyLiga[ ligaId=" + ligaId + " ]";
    }

    @XmlTransient
    public List<FantasyIgrac> getFantasyIgracList() {
        return fantasyIgracList;
    }

    public void setFantasyIgracList(List<FantasyIgrac> fantasyIgracList) {
        this.fantasyIgracList = fantasyIgracList;
    }

    @Transient
    private long brojTimova;

    @XmlTransient
    public long getBrojTimova() {
        return brojTimova;
    }

    public void setBrojTimova(long brojTimova) {
        this.brojTimova = brojTimova;
    }

}
