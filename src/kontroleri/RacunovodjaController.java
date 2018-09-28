/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import multipleksadmin.AlertHelper;
import multipleksadmin.MojaPlata;
import projektovanje.dto.DTOPlata;
import projektovanje.dto.DTOZaposleni;
import projektovanje.servisi.AdministratorServis;
import projektovanje.servisi.RacunovodjaServis;

public class RacunovodjaController implements Initializable {
    
    public static int id;
    @FXML
    private JFXButton dodajPlatuButton;
    
    @FXML
    private JFXComboBox<?> zaposleniComboBox;
    
    @FXML
    private TableView<MojaPlata> tabelaPlata;
    
    @FXML
    private TableColumn<MojaPlata, String> maticniKolona;
    
    @FXML
    private TableColumn<MojaPlata, String> imeKolona;
    
    @FXML
    private TableColumn<MojaPlata, String> prezimeKolona;
    
    @FXML
    private TableColumn<MojaPlata, Double> brutoKolona;
    
    @FXML
    private TableColumn<MojaPlata, Double> doprinosiKolona;
    
    @FXML
    private TableColumn<MojaPlata, Double> plataKolona;
    
    @FXML
    private JFXComboBox<String> prikaziTransakcijuComboBox;
    
    @FXML
    private JFXTextArea prikazTransakcijeText;
    
    @FXML
    private JFXButton dodajTransakcijuButton;
    
    @FXML
    private JFXButton dodajFakturuButton;
    
    @FXML
    private JFXButton azurirajFakturu;
    
    @FXML
    private JFXComboBox<String> prikazFaktureComboBox;
    
    @FXML
    private JFXTextArea fakturaText;
    
    @FXML
    private Label datumLabel;
    
    @FXML
    private Label brojRacunaLabel;
    
    @FXML
    private Label vrstaTransakcijeLabel;
    
    @FXML
    private Label kolicinaLabel;
    
    @FXML
    private Label jedinicaMjereLabel;
    
    @FXML
    private Label cijenaLabel;
    
    @FXML
    private Label kupaclabel;
    
    @FXML
    private Label robbaLabel;
    
    @FXML
    private JFXDatePicker datumFaktuere;
    
    @FXML
    private JFXTextField racunField;
    
    @FXML
    private JFXComboBox<String> transakcijaComboBox;
    
    @FXML
    private JFXTextField kolicinaRobeField;
    
    @FXML
    private JFXTextField cijenaField;
    
    @FXML
    private JFXTextField kupacField;
    
    @FXML
    private JFXComboBox<String> jedinicaMjereComboBox;
    
    @FXML
    private JFXButton sacuvajFakturuButton;
    
    @FXML
    private JFXButton promjenaLozinkeButton;
    
    @FXML
    private ScrollPane robaScrool;
    List<DTOZaposleni> bezPlate = new ArrayList<>();
    
    @FXML
    void dodajFakturuStisak(ActionEvent event) {
        otkrijPodatkeFaktura();
    }
    
    @FXML
    void azurirajFakturuStisak(ActionEvent event) {
        otkrijPodatkeFaktura();
    }
    
    @FXML
    void sacuvajFakturuStisak(ActionEvent event) {
        sakrijPodatkeFaktura();
    }
    
    @FXML
    void zaposleniIzbor(ActionEvent event) {
        
    }
    
    @FXML
    void dodajPlatuStisak(ActionEvent event) throws IOException {
        
        List<DTOZaposleni> listaSvih = RacunovodjaServis.pregledajListuPlata();
        for (DTOZaposleni zaposleni : listaSvih) {
            if (zaposleni.getZaposleni().getPlata().getIDPlate() == 1) {
                bezPlate.add(zaposleni);
            }
        }
        PlataController.listaZaposlenih = bezPlate;
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/plata.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }
    
    @FXML
    void izmjeniPlatuStisak(ActionEvent event) throws IOException {
        if (!tabelaPlata.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            List<DTOZaposleni> svi = RacunovodjaServis.pregledajListuPlata();
            for (DTOZaposleni z : svi) {
                if (z.getZaposleni().getJMBG().equals(tabelaPlata.getSelectionModel().getSelectedItem().getJMBG())) {
                    PlataController.zaposleni = z;
                    PlataController.izmjena=true;
                    Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/plata.fxml"));
                    Scene korisnikScena = new Scene(korisnikView);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(korisnikScena);
                    window.centerOnScreen();
                    window.show();
                }
            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali zaposlenog za izmjenu plate.");
            return;
        }
    }
    
    @FXML
    void prikaziFakturu(ActionEvent event) {
        
    }
    
    
    @FXML
    void sacuvajFakturustisak(ActionEvent event) {

    }
    
    
    @FXML
    void dodajNaFakturuStisak(ActionEvent event) {

    }
    @FXML
    void obrisiSaFaktureStisak(ActionEvent event) {

    }
    
    
    
    @FXML
    void prikazTabele() {
        maticniKolona.setCellValueFactory(new PropertyValueFactory<>("JMBG"));
        imeKolona.setCellValueFactory(new PropertyValueFactory<>("ime"));
        prezimeKolona.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        brutoKolona.setCellValueFactory(new PropertyValueFactory<>("bruto"));
        doprinosiKolona.setCellValueFactory(new PropertyValueFactory<>("doprinosi"));
        plataKolona.setCellValueFactory(new PropertyValueFactory<>("neto"));
        tabelaPlata.setItems(getMojaPlata());
    }
    
    public ObservableList<MojaPlata> getMojaPlata() {
        List<DTOZaposleni> listaZaposlenihSaPlatom = RacunovodjaServis.pregledajListuPlata();
        
        List<MojaPlata> listaMoja = new ArrayList<>();
        for (DTOZaposleni zaposleni : listaZaposlenihSaPlatom) {
            listaMoja.add(new MojaPlata(zaposleni.getZaposleni().getJMBG(),zaposleni.getZaposleni().getIme(), zaposleni.getZaposleni().getPrezime(), zaposleni.getZaposleni().getPlata().getBruto(), zaposleni.getZaposleni().getPlata().getDoprinosi(), zaposleni.getZaposleni().getPlata().getIsplataRadniku()));
        }
        ObservableList<MojaPlata> listaZaPrikaz = FXCollections.observableArrayList();
        for (MojaPlata p : listaMoja) {
            listaZaPrikaz.add(p);
        }
        return listaZaPrikaz;
    }

    private void sakrijPodatkeFaktura() {
        fakturaText.setVisible(false);
        datumLabel.setVisible(false);
        brojRacunaLabel.setVisible(false);
        vrstaTransakcijeLabel.setVisible(false);
        kolicinaLabel.setVisible(false);
        jedinicaMjereLabel.setVisible(false);
        cijenaLabel.setVisible(false);
        kupaclabel.setVisible(false);
        robbaLabel.setVisible(false);
        datumFaktuere.setVisible(false);
        racunField.setVisible(false);
        transakcijaComboBox.setVisible(false);
        kolicinaRobeField.setVisible(false);
        cijenaField.setVisible(false);
        kupacField.setVisible(false);
        jedinicaMjereComboBox.setVisible(false);
        sacuvajFakturuButton.setVisible(false);
        robaScrool.setVisible(false);
        
    }
    
    private void otkrijPodatkeFaktura() {
        fakturaText.setVisible(true);
        datumLabel.setVisible(true);
        brojRacunaLabel.setVisible(true);
        vrstaTransakcijeLabel.setVisible(true);
        kolicinaLabel.setVisible(true);
        jedinicaMjereLabel.setVisible(true);
        cijenaLabel.setVisible(true);
        kupaclabel.setVisible(true);
        robbaLabel.setVisible(true);
        datumFaktuere.setVisible(true);
        racunField.setVisible(true);
        transakcijaComboBox.setVisible(true);
        kolicinaRobeField.setVisible(true);
        cijenaField.setVisible(true);
        kupacField.setVisible(true);
        jedinicaMjereComboBox.setVisible(true);
        sacuvajFakturuButton.setVisible(true);
        robaScrool.setVisible(true);
    }
    
    @FXML
    void promjenaLozinkeStisak(ActionEvent event) throws IOException {
        PromjenaLozinkeController.id=this.id;
        PromjenaLozinkeController.povratak="/gui/racunovodja.fxml";
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/promjenaLozinke.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prikazTabele();
        //sakrijPodatkeFaktura();
       
    }
    
}
