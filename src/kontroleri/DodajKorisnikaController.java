/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import multipleksadmin.AlertHelper;
import projektovanje.servisi.AdministratorServis;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class DodajKorisnikaController implements Initializable {

    @FXML
    private JFXTextField imeField;

    @FXML
    private JFXTextField prezimeField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField korisnickoImeField;

    @FXML
    private JFXTextField lozinkaField;

    @FXML
    private JFXButton sacuvajButton;

    @FXML
    private JFXButton otkaziButton;

    @FXML
    void otkaziStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/admin.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void sacuvajStisak(ActionEvent event) throws IOException {
        if ("".equals(imeField.getText()) || "".equals(prezimeField.getText()) || "".equals(emailField.getText())
                || "".equals(korisnickoImeField.getText()) || "".equals(lozinkaField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "Greska !",
                    "Niste unijeli sve podatke !");
            return;
        } else {
            String odgovor = AdministratorServis.dodajKorisnika(imeField.getText(), prezimeField.getText(), emailField.getText(), korisnickoImeField.getText(), "" + lozinkaField.getText().hashCode());
            if (odgovor.startsWith("OK")) {
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "Potvrda !",
                        "Uspjesno ste dodali korisnika !");
            } else {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "Greska !",
                        "Greska !");
            }
            Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/admin.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
