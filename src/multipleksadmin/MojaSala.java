package multipleksadmin;

public class MojaSala{

    private String naziv;
    private Integer brojVrsta;
    private Integer brojKolona;
    private Integer sjedista;

    public MojaSala() {
    }

    public MojaSala(String naziv, Integer brojVrsta, Integer brojKolona, Integer sjedista) {
        this.naziv = naziv;
        this.brojVrsta = brojVrsta;
        this.brojKolona = brojKolona;
        this.sjedista = sjedista;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getSjedista() {
        return sjedista;
    }

    public void setSjedista(int sjedista) {
        this.sjedista = sjedista;
    }

    public Integer getBrojVrsta() {
        return brojVrsta;
    }

    public void setBrojVrsta(Integer brojVrsta) {
        this.brojVrsta = brojVrsta;
    }

    public Integer getBrojKolona() {
        return brojKolona;
    }

    public void setBrojKolona(Integer brojKolona) {
        this.brojKolona = brojKolona;
    }
}
