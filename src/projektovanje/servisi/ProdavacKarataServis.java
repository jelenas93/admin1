package projektovanje.servisi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.bin.izdavanje.Izdavanje;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOIzdavanje;
import projektovanje.konekcija.KonekcijaNET;
import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.DTORepertoar;

public class ProdavacKarataServis {
 private static final KonekcijaNET konekcija=KonekcijaNET.getInstance();
    public ProdavacKarataServis() {

    }

    public static boolean prodajKartu(Karta karta, Sjediste sjediste, Sala sala, 
            Projekcija projekcija, Zaposleni zaposleni) {
     
        Izdavanje izdavanje=new Izdavanje(karta,sjediste,sala,projekcija,zaposleni);
        DTOIzdavanje dtoIzdavanje=new DTOIzdavanje(izdavanje);
        try {
            konekcija.os.writeObject("SELL_TICKET");
            String odgovor=(String)konekcija.is.readObject();
            if(odgovor.startsWith("WHICHONE")){
                konekcija.os.writeObject(dtoIzdavanje);
                odgovor=(String)konekcija.is.readObject();
                if(odgovor.startsWith("OK")){
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ProdavacKarataServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
         Logger.getLogger(ProdavacKarataServis.class.getName()).log(Level.SEVERE, null, ex);
     }
        return false;
    }

    public static boolean rezervisiKartu(Karta karta, Sjediste sjediste, Sala sala, 
            Projekcija projekcija, Zaposleni zaposleni) {
  
        Izdavanje izdavanje=new Izdavanje(karta,sjediste,sala,projekcija,zaposleni);
        DTOIzdavanje dtoIzdavanje=new DTOIzdavanje(izdavanje);
        try {
            konekcija.os.writeObject("RESERVE_TICKET");
            String odgovor=(String)konekcija.is.readObject();
            if(odgovor.startsWith("WHICHONE")){
                konekcija.os.writeObject(dtoIzdavanje);
                odgovor=(String)konekcija.is.readObject();
                if(odgovor.startsWith("OK")){
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ProdavacKarataServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
         Logger.getLogger(ProdavacKarataServis.class.getName()).log(Level.SEVERE, null, ex);
     }
        return false;
    }

    public  static boolean ponistiRezervaciju(Karta karta, Sjediste sjediste, Sala sala, 
            Projekcija projekcija, Zaposleni zaposleni) {
       
        Izdavanje izdavanje=new Izdavanje(karta,sjediste,sala,projekcija,zaposleni);
        DTOIzdavanje dtoIzdavanje=new DTOIzdavanje(izdavanje);
        try {
            konekcija.os.writeObject("CANCEL_RESERVATION");
            String odgovor=(String)konekcija.is.readObject();
            if(odgovor.startsWith("WHICHONE")){
                konekcija.os.writeObject(dtoIzdavanje);
                odgovor=(String)konekcija.is.readObject();
                if(odgovor.startsWith("OK")){
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ProdavacKarataServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
         Logger.getLogger(ProdavacKarataServis.class.getName()).log(Level.SEVERE, null, ex);
     }
        return false;
    }
    
    public List<DTOProjekcija> pregledProjekcija(){
    
        ArrayList<DTOProjekcija> projekcije=new ArrayList<>();
        try{
            konekcija.os.writeObject("LIST_PROJECTIONS");
            projekcije=(ArrayList<DTOProjekcija>)konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projekcije;
    }
    
    public static List<DTORepertoar> pregledRepertoara(){
        
        ArrayList<DTORepertoar> repertoar=new ArrayList<>();
        try{
            konekcija.os.writeObject("LIST_REPERTOIRE");
            repertoar=(ArrayList<DTORepertoar>)konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repertoar;
    }
    
    public  static DTORepertoar pregledTrenutongRepertoara(){
        
        DTORepertoar repertoar=new DTORepertoar();
        try{
            konekcija.os.writeObject("GET_CURRENT_REPERTOIRE");
            repertoar=(DTORepertoar)konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repertoar;
    }

}