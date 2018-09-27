/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import multipleksadmin.AlertHelper;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.servisi.AdministratorServis;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class IzmjenaZaposlenogController implements Initializable {

    static Zaposleni zaposleni;
    @FXML
    private JFXTextField imeField;

    @FXML
    private JFXTextField prezimeField;

    @FXML
    private JFXComboBox<String> pozicijaComboBox;

    @FXML
    private JFXButton otkaziButton;

    @FXML
    private JFXButton sacuvajButtom;

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
    void sacuvajstisak(ActionEvent event) throws IOException {
        if ("".equals(imeField.getText()) || "".equals(prezimeField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "Upozorenje !",
                    "Morate unijeti podatke !");
            return;
        } else {
            boolean odgovor = AdministratorServis.azurirajZaposlenog(zaposleni.getIdZaposlenog(), zaposleni.getPlata(), imeField.getText(), prezimeField.getText(), zaposleni.getJMBG(), zaposleni.getAktivan(), zaposleni.getNalog());
            System.out.println(odgovor);
            if (odgovor) {
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "Potvrda !",
                        "Uspjesno ste dodali novog zaposlenog !");
                Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/admin.fxml"));
                Scene korisnikScena = new Scene(korisnikView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(korisnikScena);
                window.centerOnScreen();
                window.show();
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                        "Greska !");
                return;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imeField.setText(zaposleni.getIme());
        prezimeField.setText(zaposleni.getPrezime());
        pozicijaComboBox.getItems().addAll("Administrator", "Kinooperater", "Menadzer", "Racunovodja", "ProdavacKarata", "ProdavacHraneIPica", "Skladistar");
        pozicijaComboBox.getSelectionModel().selectFirst();
    }

}
