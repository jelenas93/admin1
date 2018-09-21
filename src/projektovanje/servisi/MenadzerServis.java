package projektovanje.servisi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.bin.film.Film;
import projektovanje.bin.film.Ponuda;
import projektovanje.bin.film.Zanr;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.konekcija.KonekcijaNET;
import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOPonuda;
import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.DTOSala;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

public class MenadzerServis {
 private static final KonekcijaNET konekcija=KonekcijaNET.getInstance();
    public MenadzerServis() {

    }

    public boolean dodajProjekciju(Integer idProjekcije, Film film, Date vrijeme, Zaposleni zaposleni) {
        Projekcija projekcija = new Projekcija(idProjekcije, film, vrijeme, zaposleni);
       
        DTOProjekcija dtoProjekcija = new DTOProjekcija(projekcija);
        try {
            konekcija.os.writeObject(new String("ADD_PROJECTION"));
            if (((String) konekcija.is.readObject()).equals("WHICHONE")) {
                konekcija.os.writeObject(dtoProjekcija);
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

    public boolean azurirajProjekciju(Integer idProjekcije, Film film, Date vrijeme, Zaposleni zaposleni) {
        Projekcija projekcija = new Projekcija(idProjekcije, film, vrijeme, zaposleni);
      
        DTOProjekcija dtoProjekcija = new DTOProjekcija(projekcija);
        try {
            konekcija.os.writeObject(new String("UPDATE_PROJECTION"));
            if (((String) konekcija.is.readObject()).equals("WHICHONE")) {
                konekcija.os.writeObject(dtoProjekcija);
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
    
    public static boolean dodajSalu(Integer idSale, Integer brojVrsta, Integer brojKolona, List<Sjediste> sjedista) {
        Sala sala = new Sala(idSale,brojVrsta,brojKolona,sjedista);
      
        DTOSala dtoSala = new DTOSala(sala);
        try {
            konekcija.os.writeObject(new String("ADD_MOVIE_HALL"));
            if (((String) konekcija.is.readObject()).equals("WHICHONE")) {
                konekcija.os.writeObject(dtoSala);
                if (((String) konekcija.is.readObject()).startsWith("OK")) {
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
    
   public static List<List<? extends IDTO>> prikaziZaposlene() {
        List<List<? extends IDTO>> listaZaposlenih = new ArrayList<>();
        try {
            konekcija.os.writeObject(new String("LIST_EMPLOYEES"));
            listaZaposlenih = (ArrayList<List<?extends IDTO>>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaZaposlenih;
    }
    
   public static boolean dodajFilm(Integer idFilma, Zaposleni zaposleni, String naziv, Integer trajanje,  String opis, String linkTrailera, String tipFilma,  List<Zanr> zanrovi  ) {
        Film film=new Film(idFilma,zaposleni,naziv,trajanje,opis, linkTrailera,tipFilma,zanrovi);
      
        DTOFilm dtoFilm=new DTOFilm(film);
        try {
             System.out.println(" TU sam");
            konekcija.os.writeObject(new String("ADD_MOVIE"));
            String odg=(String) konekcija.is.readObject();
            if (odg.equals("WHICHONE")) {
                System.out.println(odg);
                konekcija.os.writeObject(dtoFilm);
                System.out.println("projektovanje.servisi.MenadzerServis.dodajFilm()");
                String odgovor=(String)konekcija.is.readObject();
                 System.out.println(odgovor);
                if (odgovor.startsWith("OK")) {             
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
    
    public static String azurirajFilm(Integer idFilma, Zaposleni zaposleni, String naziv, Integer trajanje,  String opis, String linkTrailera, String tipFilma,  List<Zanr> zanrovi ) {
        Film film=new Film(idFilma,zaposleni,naziv,trajanje,opis, linkTrailera,tipFilma, zanrovi);
     
        DTOFilm dtoFilm=new DTOFilm(film);
        String odgovor="NOK";
        try {
            konekcija.os.writeObject(new String("UPDATE_MOVIE"));
            if (((String) konekcija.is.readObject()).equals("WICHONE")) {
                konekcija.os.writeObject(dtoFilm);
                if (((String) konekcija.is.readObject()).equals("OK")) {
                    return odgovor;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odgovor;
    }
    public List<DTOFilm> pregledFilma(String imeFilma) {
      
        ArrayList<DTOFilm> film = new ArrayList<>();
        try {
            konekcija.os.writeObject(new String("GIVE_ME_MOVIE#"+imeFilma));
            film = (ArrayList<DTOFilm>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return film;
    }
    
     public static boolean dodajPonudu(Integer idPonude, Film film, Date datumPonude, Zaposleni zaposleni) {
        Ponuda ponuda=new Ponuda(idPonude,film,datumPonude,zaposleni);
  
        DTOPonuda dtoPonuda=new DTOPonuda(ponuda);
        try {
            konekcija.os.writeObject(new String("ADD_OFFER"));
            if (((String) konekcija.is.readObject()).equals("WICHONE")) {
                konekcija.os.writeObject(dtoPonuda);
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
     
     public static List<DTOPonuda> pregledPonuda() {
     
        ArrayList<DTOPonuda> ponuda = new ArrayList<>();
        try {
            konekcija.os.writeObject(new String("LIST_OFFERS"));
            ponuda = (ArrayList<DTOPonuda>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ponuda;
    }
    

}
