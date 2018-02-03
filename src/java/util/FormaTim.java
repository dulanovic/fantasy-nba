package util;

public class FormaTim {

    private int utakmicaId;
    private String utakmicaIspis;
    private int razlika;
    private String boja;

    public FormaTim() {
    }

    public FormaTim(int utakmicaId, String utakmicaIspis, int razlika, String boja) {
        this.utakmicaId = utakmicaId;
        this.utakmicaIspis = utakmicaIspis;
        this.razlika = razlika;
        this.boja = boja;
    }

    public int getUtakmicaId() {
        return utakmicaId;
    }

    public void setUtakmicaId(int utakmicaId) {
        this.utakmicaId = utakmicaId;
    }

    public String getUtakmicaIspis() {
        return utakmicaIspis;
    }

    public void setUtakmicaIspis(String utakmicaIspis) {
        this.utakmicaIspis = utakmicaIspis;
    }

    public int getRazlika() {
        return razlika;
    }

    public void setRazlika(int razlika) {
        this.razlika = razlika;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

}
