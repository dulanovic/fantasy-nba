package util;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "odgovor")
public class RestOdgovor implements Serializable {

    private String tekst;

    public RestOdgovor() {
    }

    public RestOdgovor(String tekst) {
        this.tekst = tekst;
    }

    @XmlElement(name = "poruka")
    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public String toString() {
        return "RestOdgovor ---> " + tekst;
    }

}
