package multipleksadmin;

public class MojaPlata {

    private String JMBG;
    private String ime;
    private String prezime;
    private Double doprinosi;
    private Double neto;
    private Double bruto;

    public MojaPlata() {
    }

    public MojaPlata(String JMBG, String ime, String prezime, Double bruto, Double doprinosi, Double neto) {
        this.JMBG = JMBG;
        this.ime = ime;
        this.prezime = prezime;
        this.neto = neto;
        this.doprinosi = doprinosi;
        this.bruto = bruto;

    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public Double getBruto() {
        return bruto;
    }

    public void setBruto(Double bruto) {
        this.bruto = bruto;
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

    public Double getDoprinosi() {
        return doprinosi;
    }

    public void setDoprinosi(Double doprinosi) {
        this.doprinosi = doprinosi;
    }

    public Double getNeto() {
        return neto;
    }

    public void setNeto(Double neto) {
        this.neto = neto;
    }

    @Override
    public String toString() {
        return "Plata{"
                + "ime=" + ime
                + ",\n prezime=" + prezime
                + ",\n doprinosi=" + doprinosi
                + ",\n bruto=" + bruto
                + ",\n neto=" + neto
                + '}';
    }
}
