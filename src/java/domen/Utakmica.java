package domen;

import constants.WebConstants;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "utakmica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utakmica.findAll", query = "SELECT u FROM Utakmica u")
    , @NamedQuery(name = "Utakmica.findByUtakmicaId", query = "SELECT u FROM Utakmica u WHERE u.utakmicaId = :utakmicaId")
    , @NamedQuery(name = "Utakmica.findByDatum", query = "SELECT u FROM Utakmica u WHERE u.datum = :datum")
    , @NamedQuery(name = "Utakmica.findByBrojGledalaca", query = "SELECT u FROM Utakmica u WHERE u.brojGledalaca = :brojGledalaca")
    , @NamedQuery(name = "Utakmica.findByTrajanje", query = "SELECT u FROM Utakmica u WHERE u.trajanje = :trajanje")
    , @NamedQuery(name = "Utakmica.findByPoeniDomacin", query = "SELECT u FROM Utakmica u WHERE u.poeniDomacin = :poeniDomacin")
    , @NamedQuery(name = "Utakmica.findByPoeniGost", query = "SELECT u FROM Utakmica u WHERE u.poeniGost = :poeniGost")
    , @NamedQuery(name = "Utakmica.findByUtakmicaApiId", query = "SELECT u FROM Utakmica u WHERE u.utakmicaApiId = :utakmicaApiId")
    , @NamedQuery(name = "Utakmica.findByTim", query = "SELECT u FROM Utakmica u WHERE u.domacin = :tim OR u.gost = :tim")})
public class Utakmica implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "odigrana")
    private boolean odigrana;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utakmica1")
    private List<TimUcinak> timUcinakList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utakmica")
    private List<IgracUcinak> igracUcinakList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "utakmica_id")
    private Integer utakmicaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @Column(name = "broj_gledalaca")
    private Integer brojGledalaca;
    @Size(max = 10)
    @Column(name = "trajanje")
    private String trajanje;
    @Column(name = "poeni_domacin")
    private Integer poeniDomacin;
    @Column(name = "poeni_gost")
    private Integer poeniGost;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "utakmica_api_id")
    private String utakmicaApiId;
    @JoinColumn(name = "domacin", referencedColumnName = "tim_id")
    @ManyToOne(optional = false)
    private Tim domacin;
    @JoinColumn(name = "gost", referencedColumnName = "tim_id")
    @ManyToOne(optional = false)
    private Tim gost;

    public Utakmica() {
    }

    public Utakmica(Integer utakmicaId) {
        this.utakmicaId = utakmicaId;
    }

    public Utakmica(Integer utakmicaId, Date datum, String utakmicaApiId) {
        this.utakmicaId = utakmicaId;
        this.datum = datum;
        this.utakmicaApiId = utakmicaApiId;
    }

    public Utakmica(boolean odigrana, Date datum, String utakmicaApiId, Tim domacin, Tim gost) {
        this.odigrana = odigrana;
        this.datum = datum;
        this.utakmicaApiId = utakmicaApiId;
        this.domacin = domacin;
        this.gost = gost;
    }

    public Integer getUtakmicaId() {
        return utakmicaId;
    }

    public void setUtakmicaId(Integer utakmicaId) {
        this.utakmicaId = utakmicaId;
    }

    public String getDatum() {
        return WebConstants.sdf.format(datum);
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Integer getBrojGledalaca() {
        return brojGledalaca;
    }

    public void setBrojGledalaca(Integer brojGledalaca) {
        this.brojGledalaca = brojGledalaca;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getPoeniDomacin() {
        return (poeniDomacin != null) ? String.valueOf(poeniDomacin) : "";
    }

    public void setPoeniDomacin(Integer poeniDomacin) {
        this.poeniDomacin = poeniDomacin;
    }

    public String getPoeniGost() {
        return (poeniGost != null) ? String.valueOf(poeniGost) : "";
    }

    public void setPoeniGost(Integer poeniGost) {
        this.poeniGost = poeniGost;
    }

    public String getUtakmicaApiId() {
        return utakmicaApiId;
    }

    public void setUtakmicaApiId(String utakmicaApiId) {
        this.utakmicaApiId = utakmicaApiId;
    }

    public Tim getDomacin() {
        return domacin;
    }

    public void setDomacin(Tim domacin) {
        this.domacin = domacin;
    }

    public Tim getGost() {
        return gost;
    }

    public void setGost(Tim gost) {
        this.gost = gost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (utakmicaId != null ? utakmicaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utakmica)) {
            return false;
        }
        Utakmica other = (Utakmica) object;
        if ((this.utakmicaId == null && other.utakmicaId != null) || (this.utakmicaId != null && !this.utakmicaId.equals(other.utakmicaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Utakmica[ utakmicaId=" + utakmicaId + " ]";
    }

    @XmlTransient
    public List<TimUcinak> getTimUcinakList() {
        return timUcinakList;
    }

    public void setTimUcinakList(List<TimUcinak> timUcinakList) {
        this.timUcinakList = timUcinakList;
    }

    @XmlTransient
    public List<IgracUcinak> getIgracUcinakList() {
        return igracUcinakList;
    }

    public void setIgracUcinakList(List<IgracUcinak> igracUcinakList) {
        this.igracUcinakList = igracUcinakList;
    }

    public boolean getOdigrana() {
        return odigrana;
    }

    public void setOdigrana(boolean odigrana) {
        this.odigrana = odigrana;
    }

}
