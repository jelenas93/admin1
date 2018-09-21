package multipleksadmin;


public class MojaPlata {
 
    private String ime;
    private String prezime;
    private Double doprinosi;
    private Double neto;
    private Double bruto;

    public MojaPlata() {
    }

    public MojaPlata(String ime, String prezime, Double doprinosi, Double bruto, Double neto){
        this.ime=ime;
        this.prezime=prezime;
        this.neto=neto;
        this.doprinosi=doprinosi;
        this.bruto = bruto;
        
    }

   

    public Double getBruto() {
        return bruto;
    }

    public void setBruto(Double bruto) {
        this.bruto = bruto;
    }


    @Override
    public String toString() {
        return "Plata{" +
                "ime=" + ime +
                ",\n prezime=" + prezime +
                ",\n doprinosi=" + doprinosi +
                ",\n bruto=" + bruto +
                ",\n neto="+neto+
                '}';
    }
}
