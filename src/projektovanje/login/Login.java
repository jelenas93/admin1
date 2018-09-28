package projektovanje.login;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.konekcija.KonekcijaNET;

public class Login {
    
    private static final KonekcijaNET konekcija=KonekcijaNET.getInstance();

    public Login() {

    }

    public static String login(String username, String password) {
        System.out.println(username+ " "+password);
        String odgovor="NOK";
        try {
            konekcija.os.writeObject(new String("LOGIN#" + username + "#" + password));
            odgovor = (String) konekcija.is.readObject();
            System.out.println(odgovor);//OBRISI
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odgovor;
    }

    public String register(String NAME, String SURNAME, String PASSWORD, String EMAIL, String POSITION) {
        try {
            konekcija.os.writeObject(new String("REGISTER#" + NAME + "#" + SURNAME + "#" + PASSWORD + "#" + EMAIL + "#" + POSITION));
            String odgovor = (String) konekcija.is.readObject();
            if (odgovor.equals("OK")) {
                return "OK";
            } else {
                return odgovor;
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "NOK";
    }
    
    public static String promjenaLozinke(String lozinka1, String Lozinka2){
        try {
            konekcija.os.writeObject(new String("CHANGE_PASSWORD#"+lozinka1+"#"+Lozinka2));
            String odgovor=(String) konekcija.is.readObject();
            if(odgovor.startsWith("OK")){
                return "OK";
            }else{
                return odgovor;
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "NOK";
    }
}