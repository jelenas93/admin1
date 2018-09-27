/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import multipleksadmin.AlertHelper;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOZaposleni;
import projektovanje.login.Login;
import projektovanje.servisi.AdministratorServis;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class PromjenaLozinkeController implements Initializable {

    public static int id;
    
    @FXML
    private JFXPasswordField staraLozinka;

    @FXML
    private JFXPasswordField novaLozinka;

    @FXML
    private JFXButton sacuvajButton;

    @FXML
    private JFXButton nazadButton;

    @FXML
    void nazadStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/admin.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void sacuvajStisak(ActionEvent event) throws IOException {
        //[provjeriti ovo ne radi mi mozak trenutno!!!
        
        List<Zaposleni> listaZaposlenih = AdminController.vratiSveZaposlene();
        for (Zaposleni zaposleni : listaZaposlenih) {
            if (zaposleni.getIdZaposlenog() == this.id) {
                if ("".equals(staraLozinka.getText()) || "".equals(novaLozinka.getText())) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                            "Niste unijeli podatke !");
                    return;
                } else {
                    System.out.println("Stara "+zaposleni.getNalog());
                    System.out.println(staraLozinka.getText().hashCode());
                    if(!zaposleni.getNalog().getLozinkaHash().equals(staraLozinka.getText().hashCode())){
                         AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                                "Unijeli ste pogresnu lozinku !");
                        return;
                    }
                    if (staraLozinka.getText().equals(novaLozinka.getText())) {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                                "Nova lozinka ne moze biti stara lozinka !");
                        return;
                    } else {
                        int hash1 = staraLozinka.getText().hashCode();
                        int hash2 = novaLozinka.getText().hashCode();
                        String odgovor = Login.promjenaLozinke("" + hash1, "" + hash2);
                        if (odgovor.startsWith("OK")) {
                            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "Potvrda !",
                                    "Uspjesno ste promjenili lozinku !");
                            // return;
                        } else {
                            AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                                    "Greska !");
                        }
                        Parent korisnikView = FXMLLoader.load(getClass().getResource("admin.fxml"));
                        Scene korisnikScena = new Scene(korisnikView);
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(korisnikScena);
                        window.centerOnScreen();
                        window.show();

                    }
                }
            }else{
                 AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                                "Unijeli ste pogresnu lozinku !");
                        return;
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Promjena "+id);
    }

}
