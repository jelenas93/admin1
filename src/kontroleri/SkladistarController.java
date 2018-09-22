/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import multipleksadmin.AlertHelper;
import projektovanje.bin.oprema.Artikal;
import projektovanje.dto.DTOArtikal;
import projektovanje.servisi.SkladistarServis;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class SkladistarController implements Initializable {

    public static int id;
    @FXML
    private TableView<Artikal> tabela;

    @FXML
    private TableColumn<Artikal, String> barKodKolona;

    @FXML
    private TableColumn<Artikal, String> nazivKolona;

    @FXML
    private TableColumn<Artikal, Double> cijenaKolona;

    @FXML
    private TableColumn<Artikal, Integer> koliocinaKolona;

    @FXML
    private TableColumn<Artikal, String> tipKolona;

    @FXML
    private JFXButton dodajButton;

    @FXML
    private JFXButton izmjeniButton;

    @FXML
    private JFXButton obrisiButton;

    @FXML
    private JFXTextField traziArtikal;

    @FXML
    void dodajStisak(ActionEvent event) throws IOException {
        UnosArtiklaController.id = id;
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosArtikla.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void izmjeniStisak(ActionEvent event) throws IOException {
        if (!tabela.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            UnosArtiklaController.izmjena = true;
            UnosArtiklaController.artikal=tabela.getSelectionModel().getSelectedItem();
            Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosArtikla.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }else{
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Niste izabrali artikal koji zelite promjeniti.");
            return;
        }
    }

    
    @FXML
    void obrisiStisak(ActionEvent event) {
       
        Artikal zaBrisati;
        if (!tabela.getSelectionModel().getSelectedItems().toString().equals("[]")) {
           // sviSaposleni = tabela.getItems();
            zaBrisati = tabela.getSelectionModel().getSelectedItem();
            List<DTOArtikal> listaArtikala = SkladistarServis.prikaziStanje();
            for (DTOArtikal artikal : listaArtikala) {
                if (artikal.getArtikal().getBarKod().equals(zaBrisati.getBarKod())) {
                 /*   String odgovor = SkladistarServis.
                    if (odgovor.startsWith("OK")) {
                        tabela.getItems().remove(moj);
                        postaviTabelu();
                        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "", "Uspjesno ste obrisali zaposlenog.");
                        return;
                    } else {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska.");
                        return;
                    }*/

                }
            }

        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali artikal za brisanje.");
            return;
        }
    }

    private void postaviTabelu() {
        barKodKolona.setCellValueFactory(new PropertyValueFactory<>("barKod"));
        nazivKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        tipKolona.setCellValueFactory(new PropertyValueFactory<>("tip"));
        cijenaKolona.setCellValueFactory(new PropertyValueFactory<>("jedinicnaCijena"));
        koliocinaKolona.setCellValueFactory(new PropertyValueFactory<>("kolicinaNaStanju"));
        this.tabela.setItems(getArtiakl());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        postaviTabelu();
    }

    public ObservableList<Artikal> getArtiakl() {
        List<DTOArtikal> listaArtikala = SkladistarServis.prikaziStanje();
        ObservableList<Artikal> listaZaPrikaz = FXCollections.observableArrayList();
        for (DTOArtikal artikal : listaArtikala) {
            listaZaPrikaz.add(artikal.getArtikal());
            System.out.println(artikal.getArtikal());
        }
        return listaZaPrikaz;
    }

}
