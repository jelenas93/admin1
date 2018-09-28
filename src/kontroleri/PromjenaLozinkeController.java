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
import projektovanje.login.Login;


public class PromjenaLozinkeController implements Initializable {

    public static int id;
    public static String povratak;

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
        Parent korisnikView = FXMLLoader.load(getClass().getResource(povratak));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void sacuvajStisak(ActionEvent event) throws IOException {
        if ("".equals(staraLozinka.getText()) || "".equals(novaLozinka.getText())) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                    "Niste unijeli podatke !");
            return;
        } else {
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
                } else {
                    String razlog = odgovor.split("#")[1];
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                            razlog);
                    return;
                }
                Parent korisnikView = FXMLLoader.load(getClass().getResource(povratak));
                Scene korisnikScena = new Scene(korisnikView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(korisnikScena);
                window.centerOnScreen();
                window.show();

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
