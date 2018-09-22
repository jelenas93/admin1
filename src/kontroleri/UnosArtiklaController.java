/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import multipleksadmin.AlertHelper;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.servisi.SkladistarServis;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class UnosArtiklaController implements Initializable {

    public static int id;
   
    public static Artikal artikal;

    @FXML
    private JFXTextField barKodField;

    @FXML
    private JFXTextField nazivField;

    @FXML
    private JFXTextField kolicinaField;

    @FXML
    private JFXButton sacuvajDugme;

    @FXML
    private JFXButton nazadDugme;

    @FXML
    private JFXTextField cijenaField;

    @FXML
    private JFXComboBox<String> tipComboBox;
   
    public static boolean izmjena;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (izmjena) {
            barKodField.setText(artikal.getBarKod());
            nazivField.setText(artikal.getNaziv());
            cijenaField.setText(artikal.getJedinicnaCijena().toString());
            kolicinaField.setText(artikal.getKolicinaNaStanju().toString());
            tipComboBox.getSelectionModel().select(artikal.getTip());
        }
        tipComboBox.getItems().addAll("Hrana", "Pice", "Oprema");
    }

    @FXML
    void nazadStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/skladistar.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void sacuvajStisak(ActionEvent event) throws IOException {
        if ("".equals(barKodField.getText()) || "".equals(nazivField.getText()) || "".equals(kolicinaField.getText())
                || "".equals(cijenaField.getText()) || "".equals(tipComboBox.getSelectionModel().getSelectedItem())) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Morate unijeti sve podatatke o artiklu !");
            return;
        } else {
            try{
            if (Double.parseDouble(cijenaField.getText()) < 0) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Cijena artikla ne moze biti manja od 0 !");
                return;
            }}catch(NumberFormatException e){
                 AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Cijena mora biti broj !");
                return;
            }
            try{
            if (Integer.parseInt(kolicinaField.getText()) < 0) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Kolicina artikla ne moze biti manja od 0 !");
                return;
            }}catch(NumberFormatException e){
                 AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Kolicina mora biti broj !");
                return;
            }
            if (!izmjena) {
                boolean odgovor = SkladistarServis.dodajArtikal(1, nazivField.getText(), Integer.parseInt(kolicinaField.getText()), Double.parseDouble(cijenaField.getText()), tipComboBox.getSelectionModel().getSelectedItem(), barKodField.getText(), new Zaposleni(id));
                if (odgovor) {
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "", "Uspjesno ste dodali novi artikal.");
                } else {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska.");
                    return;
                }
            } else {
                boolean odgovor = SkladistarServis.azurirajArtikal(artikal.getIdArtikla(), nazivField.getText(), Integer.parseInt(kolicinaField.getText()), Double.parseDouble(cijenaField.getText()), tipComboBox.getSelectionModel().getSelectedItem(), barKodField.getText(), new Zaposleni(id));
                if (odgovor) {
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "", "Uspjesno ste izmjenili artikal.");
                } else {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska.");
                    return;
                }
            }
            Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/skladistar.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();

        }

    }

}
