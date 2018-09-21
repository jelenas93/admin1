package multipleksadmin;


public class MojZaposleni{
    private String ime;
    private String prezime;
    private String JMBG;
    private String pozicija; 
 
    public MojZaposleni(){}

    public MojZaposleni(String JMBG ,String ime, String prezime, String pozicija){
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.pozicija=pozicija;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public String getIme() {
        return ime;
    }

    public String getJMBG() {
        return JMBG;
    }

    public String getPrezime() {
        return prezime;
    }


    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }


    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return "Zaposleni{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", JMBG='" + JMBG + '\'' +
                '}';
    }
}
