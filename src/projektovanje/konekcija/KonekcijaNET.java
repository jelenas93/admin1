package projektovanje.konekcija;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KonekcijaNET {

    private static KonekcijaNET konekcija = null;
    public static final String CONFIG="properties.config";

    private Socket socket;
    private InetAddress addr;
    public ObjectInputStream is;
    public ObjectOutputStream os;

    private KonekcijaNET() {
        try {
            Properties prop=new Properties();
            FileInputStream input=new FileInputStream(CONFIG);
            prop.load(input);
            addr = InetAddress.getByName(prop.getProperty("serverAddress"));
            socket = new Socket(addr, Integer.parseInt(prop.getProperty("serverPort")));
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(KonekcijaNET.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(KonekcijaNET.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static KonekcijaNET getInstance() {
        if(konekcija==null){
            konekcija=new KonekcijaNET();
        }
        return konekcija;
    }
    
}