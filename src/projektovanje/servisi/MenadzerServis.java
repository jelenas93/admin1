package projektovanje.servisi;

import java.awt.image.BufferedImage;
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
import projektovanje.bin.repertoar.Repertoar;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.konekcija.KonekcijaNET;
import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOPonuda;
import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.DTORepertoar;
import projektovanje.dto.DTOSala;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

public class MenadzerServis {

    private static final KonekcijaNET konekcija = KonekcijaNET.getInstance();

    public MenadzerServis() {

    }

    /*
    public static boolean dodajProjekciju(Integer idProjekcije, Film film, Date vrijeme, Zaposleni zaposleni) {
        Projekcija projekcija = new Projekcija(idProjekcije, film, vrijeme, zaposleni,idR,idS);
        System.out.println(projekcija);
        DTOProjekcija dtoProjekcija = new DTOProjekcija(projekcija);
        try {
            konekcija.os.writeObject(new String("NEW_PROJECTION"));
            String a=(String) konekcija.is.readObject();
            System.out.println(a);
            if (a.equals("WHICHONE")) {
                konekcija.os.writeObject(dtoProjekcija);
                String odg=(String) konekcija.is.readObject();
                System.out.println(odg);
                if (odg.startsWith("OK")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }*/

 /*public boolean azurirajProjekciju(Integer idProjekcije, Film film, Date vrijeme, Zaposleni zaposleni) {
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
    }*/
    public List<DTOProjekcija> pregledProjekcija() {

        ArrayList<DTOProjekcija> projekcije = new ArrayList<>();
        try {
            konekcija.os.writeObject("LIST_PROJECTIONS");
            projekcije = (ArrayList<DTOProjekcija>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projekcije;
    }

    public static boolean dodajSalu(Integer idSale, Integer brojVrsta, Integer brojKolona, List<Sjediste> sjedista) {
        Sala sala = new Sala(idSale, brojVrsta, brojKolona, sjedista);
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
            listaZaposlenih = (ArrayList<List<? extends IDTO>>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaZaposlenih;
    }

    public static List<DTOSala> prikaziSale() {
        List<DTOSala> listaSala = new ArrayList<>();
        try {
            konekcija.os.writeObject(new String("LIST_MOVIE_HALL"));
            listaSala = (ArrayList<DTOSala>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSala;
    }

    public static boolean dodajFilm(Integer idFilma, Zaposleni zaposleni, String naziv, Integer trajanje, String opis, String linkTrailera, String tipFilma, List<Zanr> zanrovi, String slika) {
        Film film = new Film(idFilma, zaposleni, naziv, trajanje, opis, linkTrailera, tipFilma, zanrovi, slika);
        System.out.println(film);
        DTOFilm dtoFilm = new DTOFilm(film);
        try {
            konekcija.os.writeObject("ADD_MOVIE");
            String odg = (String) konekcija.is.readObject();
            System.out.println(odg);
            if (odg.equals("WHICHONE")) {
                konekcija.os.writeObject(dtoFilm);
                String odgovor = (String) konekcija.is.readObject();
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

    public static String azurirajFilm(Integer idFilma, Zaposleni zaposleni, String naziv, Integer trajanje, String opis, String linkTrailera, String tipFilma, List<Zanr> zanrovi, String slika) {
        Film film = new Film(idFilma, zaposleni, naziv, trajanje, opis, linkTrailera, tipFilma, zanrovi, slika);

        DTOFilm dtoFilm = new DTOFilm(film);
        String odgovor = "NOK";
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

    public static List<DTOFilm> pregledFilma() {

        ArrayList<DTOFilm> film = new ArrayList<>();
        try {
            konekcija.os.writeObject(new String("GIVE_ME_MOVIE"));
            film = (ArrayList<DTOFilm>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return film;
    }

    /*
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
    }*/

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

    public static boolean dodajRepertoar(Integer id, List<Projekcija> projekcije, Zaposleni zaposleni, Date datumOd, Date datumDo) {
        Repertoar repertoar = new Repertoar(id, projekcije, zaposleni, datumOd, datumDo);
        DTORepertoar dtoRepertoar = new DTORepertoar(repertoar);
        try {
            konekcija.os.writeObject("ADD_REPERTOIRE");
            if (((String) konekcija.is.readObject()).equals("WHICHONE")) {
                konekcija.os.writeObject(dtoRepertoar);
                if (((String) konekcija.is.readObject()).equals("OK")) {
                    return true;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /*  public static boolean dodajProjekcijuNaRepertoar(Integer idProjekcije, Film film, Date vrijeme, Zaposleni zaposleni, DTORepertoar dtoRepertoar, int idSale) {
        Projekcija projekcija = new Projekcija(idProjekcije, film, vrijeme, zaposleni, dtoRepertoar.getRepertoar().getIdRepertoara(), idSale);
        System.out.println(projekcija.toString());
        DTOProjekcija dtoProjekcija = new DTOProjekcija(projekcija);
        try {
            konekcija.os.writeObject("ADD_MOVIE_TO_REPERTOIRE");
            if (((String) konekcija.is.readObject()).equals("GIVE_ME_DATA")) {
                konekcija.os.writeObject(dtoProjekcija);
                if (((String) konekcija.is.readObject()).equals("GIVE_ME_REPERTOIRE")) {
                    konekcija.os.writeObject(dtoRepertoar);
                    String odg=((String) konekcija.is.readObject());
                    System.out.println(odg);
                    if (odg.startsWith("OK")) {
                        return true;
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }*/
    public static List<DTORepertoar> pregledRepertoara() {

        ArrayList<DTORepertoar> repertoar = new ArrayList<>();
        try {
            konekcija.os.writeObject("LIST_REPERTOIRE");
            repertoar = (ArrayList<DTORepertoar>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repertoar;
    }

    public static DTORepertoar pregledTrenutongRepertoara() {

        DTORepertoar repertoar = new DTORepertoar();
        try {
            konekcija.os.writeObject("GET_CURRENT_REPERTOIRE");
            repertoar = (DTORepertoar) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repertoar;
    }

    public static boolean salaSlobodna(Projekcija projekcija) {
        DTORepertoar dtoRepertoar = new DTORepertoar();
        dtoRepertoar = pregledTrenutongRepertoara();
        Repertoar repertoar = dtoRepertoar.getRepertoar();
        List<Projekcija> projekcijeNaRepertoaru = new ArrayList<>();
        projekcijeNaRepertoaru = repertoar.getProjekcija();
        boolean slobodna=true;
        long pocetakFilma = projekcija.getVrijeme().getTime();
        long krajFilma = projekcija.getVrijeme().getTime() + (projekcija.getFilm().getTrajanje()) * 60000;
        for (Projekcija p : projekcijeNaRepertoaru) {
            System.out.println(p.getVrijeme());
            System.out.println("Pocetak "+pocetakFilma);
            System.out.println("Kraj "+krajFilma);
            if (projekcija.getIdSale().equals(p.getIdSale())) {
                if ((pocetakFilma > p.getVrijeme().getTime()) && (pocetakFilma < p.getVrijeme().getTime() + ((p.getFilm().getTrajanje()) * 60000))) {
                    System.out.println("Usao u prvi if");
                    slobodna= false;
                } else if ((pocetakFilma < p.getVrijeme().getTime()) && (krajFilma > p.getVrijeme().getTime())) {
                    System.out.println("Usao u else if");
                    slobodna = false;
                }
            }
        }
        return slobodna;
    }

    public static boolean dodajProjekcijuNaRepertoar(Integer idProjekcije, Film film, Date vrijeme, Zaposleni zaposleni, DTORepertoar dtoRepertoar, int idSale) {
        Projekcija projekcija = new Projekcija(idProjekcije, film, vrijeme, zaposleni, dtoRepertoar.getRepertoar().getIdRepertoara(), idSale);
        System.out.println(projekcija.toString());
        DTOProjekcija dtoProjekcija = new DTOProjekcija(projekcija);
        if (salaSlobodna(projekcija)) {
            try {
                konekcija.os.writeObject("ADD_MOVIE_TO_REPERTOIRE");
                if (((String) konekcija.is.readObject()).equals("GIVE_ME_DATA")) {
                    konekcija.os.writeObject(dtoProjekcija);
                    if (((String) konekcija.is.readObject()).equals("GIVE_ME_REPERTOIRE")) {
                        konekcija.os.writeObject(dtoRepertoar);
                        String odg = ((String) konekcija.is.readObject());
                        System.out.println(odg);
                        if (odg.startsWith("OK")) {
                            return true;
                        }
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenadzerServis.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
