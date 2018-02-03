package util;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "zahtev")
@XmlAccessorType(XmlAccessType.NONE)
public class RestZahtev implements Serializable {

    private String korisnickoIme;
    private String korisnickaSifra;
    private String parametar;

    public RestZahtev() {
    }

    public RestZahtev(String korisnickoIme, String korisnickaSifra, String parametar) {
        this.korisnickoIme = korisnickoIme;
        this.korisnickaSifra = korisnickaSifra;
        this.parametar = parametar;
    }

    @XmlElement(name = "korisnickoIme")
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    @XmlElement(name = "korisnickaSifra")
    public String getKorisnickaSifra() {
        return korisnickaSifra;
    }

    public void setKorisnickaSifra(String korisnickaSifra) {
        this.korisnickaSifra = korisnickaSifra;
    }

    @XmlElement(name = "parametar")
    public String getParametar() {
        return parametar;
    }

    public void setParametar(String nazivLige) {
        this.parametar = nazivLige;
    }

}
