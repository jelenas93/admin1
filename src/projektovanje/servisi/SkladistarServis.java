package projektovanje.servisi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.konekcija.KonekcijaNET;
import projektovanje.dto.DTOArtikal;

public class SkladistarServis {
     private static final KonekcijaNET konekcija=KonekcijaNET.getInstance();
    public SkladistarServis(){
        
    }
    
    public static boolean dodajArtikal(Integer idArtikla, String naziv, Integer kolicinaNaStanju, Double jedinicnaCijena,  String tip, String barKod, Zaposleni zaposleni){
        Artikal artikal=new Artikal(idArtikla,naziv,kolicinaNaStanju,jedinicnaCijena,tip,barKod,zaposleni);
        
        DTOArtikal dtoArtikal=new DTOArtikal(artikal);
        try{
            konekcija.os.writeObject(new String("ADD_PRODUCT"));
            if (((String) konekcija.is.readObject()).equals("WHICHONE")){
                konekcija.os.writeObject(dtoArtikal);
                 if (((String) konekcija.is.readObject()).equals("OK")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean azurirajArtikal(Integer idArtikla, String naziv, Integer kolicinaNaStanju, Double jedinicnaCijena,  String tip, String barKod, Zaposleni zaposleni){
        Artikal artikal=new Artikal(idArtikla,naziv,kolicinaNaStanju,jedinicnaCijena,tip,barKod,zaposleni);
    
        DTOArtikal dtoArtikal=new DTOArtikal(artikal);
        try{
            konekcija.os.writeObject(new String("UPDATE_PRODUCT"));
            if (((String) konekcija.is.readObject()).equals("WHICHONE")){
                konekcija.os.writeObject(dtoArtikal);
                 if (((String) konekcija.is.readObject()).equals("OK")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<DTOArtikal> prikaziStanje(){
      
        ArrayList<DTOArtikal> artikli=new ArrayList<>();
        try{
            konekcija.os.writeObject(new String("LIST_PRODUCTS"));
            artikli=(ArrayList<DTOArtikal>)konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artikli;
    }
    
}
