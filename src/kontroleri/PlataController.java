/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import multipleksadmin.AlertHelper;
import projektovanje.dto.DTOZaposleni;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class PlataController implements Initializable {

    @FXML
    private JFXTextField doprinosZaPenzionoField;

    @FXML
    private JFXTextField doprinosZaZdavstvenoField;

    @FXML
    private JFXTextField doprinosZaDJecijuZastituField;

    @FXML
    private JFXTextField doprinosZaZaposljavanjeField;

    @FXML
    private JFXTextField stopaPorezaField;

    @FXML
    private JFXTextField stopaZaPenzionoField;

    @FXML
    private JFXTextField stopaZaZdravstvenoField;

    @FXML
    private JFXTextField stopaZaDjecijuZastituField;

    @FXML
    private JFXTextField stopaZaZaposljavanjeField;

    @FXML
    private JFXTextField netoTekuciRadField;

    @FXML
    private JFXTextField netoMinuliRadField;

    @FXML
    private JFXTextField brutoField;

    @FXML
    private JFXTextField porezNaPlatuField;

    @FXML
    private JFXDatePicker datumOdDate;

    @FXML
    private JFXDatePicker datumDoDate;

    @FXML
    private JFXButton sacuvajPlatuButtton;

    @FXML
    private Label zaposleniLabel;

    @FXML
    private JFXComboBox<DTOZaposleni> noviZaposleniComboBox;

     @FXML
    void sacuvajPlatuStisak(ActionEvent event) {
     

        if ("".equals(doprinosZaPenzionoField.getText()) || "".equals(doprinosZaZdavstvenoField.getText()) || "".equals(doprinosZaDJecijuZastituField.getText())
                || "".equals(doprinosZaZaposljavanjeField.getText()) || "".equals(stopaPorezaField.getText()) || "".equals(stopaZaPenzionoField.getText())
                || "".equals(stopaZaZdravstvenoField.getText()) || "".equals(stopaZaDjecijuZastituField.getText())
                || "".equals(stopaZaZaposljavanjeField.getText()) || "".equals(netoTekuciRadField.getText()) || "".equals(netoMinuliRadField.getText())
                || "".equals(porezNaPlatuField.getText()) || "".equals(brutoField.getText()) || "".equals(datumDoDate.getValue()) || "".equals(datumOdDate.getValue())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "Greska !",
                    "Niste unijeli sve podatke !");
            return;
        } else {
            
            //unijeti provjeru za datume !
       /*     boolean odgovovor=RacunovodjaServis.dodajPlatu(id, Double.POSITIVE_INFINITY, Double.MIN_VALUE, Double.POSITIVE_INFINITY, Double.MIN_VALUE, Double.MIN_NORMAL, Double.NaN, Double.MAX_VALUE, Double.POSITIVE_INFINITY, Double.MAX_VALUE, Double.NaN, Double.MIN_NORMAL, Double.NaN, Double.NaN, datumOd, datumDo);
            if (odgovor.equals("OK")) {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Potvrda !",
                        "Uspjesno ste dodali novog zaposlenog !");
            } else {
                String razlog = odgovor.split("#")[1];
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                        razlog);
            }
            Parent korisnikView = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();*/
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
