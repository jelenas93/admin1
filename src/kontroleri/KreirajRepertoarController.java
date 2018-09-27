/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOProjekcija;
import projektovanje.servisi.MenadzerServis;

public class KreirajRepertoarController implements Initializable {

    @FXML
    private JFXDatePicker datumOd;

    @FXML
    private JFXDatePicker datumDo;

    @FXML
    private JFXButton sacuvajButton;

    @FXML
    private JFXButton otkaziButton;

    @FXML
    void otkaziStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/menadzer.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();

    }

    @FXML
    void sacuvajStisak(ActionEvent event) throws IOException {
        if (datumOd.getValue()==null || datumDo.getValue()==null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli podatke.");
            return;
        } else {
            Date datum1 = Date.from(datumOd.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date datum2 = Date.from(datumDo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<Projekcija> lista = new ArrayList<>();
            boolean odg = MenadzerServis.dodajRepertoar(1, lista, new Zaposleni(MenadzerController.id), datum1, datum2);
            if (odg) {
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "", "Uspjenso ste dodali repertoar ! ");
              //  return;
                Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/menadzer.fxml"));
                Scene korisnikScena = new Scene(korisnikView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(korisnikScena);
                window.centerOnScreen();
                window.show();
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska ! ");
                return;
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
