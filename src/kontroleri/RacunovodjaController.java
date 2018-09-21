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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<MojaPlata, String> imeKolona;

    @FXML
    private TableColumn<MojaPlata, String> prezimeKolona;

    @FXML
    private TableColumn<MojaPlata, Double> brutoKolona;

    @FXML
    private TableColumn<MojaPlata, Double> netoKolona;

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
    private ScrollPane robaScrool;

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
    void dodajPlatuStisak(ActionEvent event) {
       
        ArrayList<DTOZaposleni> listaSvih=AdministratorServis.prikaziZaposlene();
        for(DTOZaposleni zaposleni:listaSvih){
            if(zaposleni.getZaposleni().getPlata().getIDPlate()==1){
            //    bezPlate.add(zaposleni);
            }
        }
    }

    @FXML
    void izmjeniPlatuStisak(ActionEvent event) {
      
    }

    @FXML
    void prikaziFakturu(ActionEvent event) {

    }

  
    @FXML
    void prikazTabele(List<DTOZaposleni> listaZaposlenih) {
      /*  
        ObservableList<DTOZaposleni> lista = FXCollections.observableArrayList();
        for (DTOZaposleni zaposleni : listaZaposlenih) {
            lista.add(zaposleni);
        }
        tabelaPlata = new TableView<>();
        TableColumn imeKolona = new TableColumn("Ime");
        // imeKolona.setMinWidth(200);
        imeKolona.setCellValueFactory(new PropertyValueFactory<>("Ime"));
        TableColumn prezimeKolona = new TableColumn("Prezime");
        // prezimeKolona.setMinWidth(200);
        prezimeKolona.setCellValueFactory(new PropertyValueFactory<>("Prezime"));
        TableColumn brutoKolona = new TableColumn("Bruto");
        // brutoKolona.setMinWidth(200);
        brutoKolona.setCellValueFactory(new PropertyValueFactory<>("Bruto"));
        TableColumn netoKolona = new TableColumn("Neto");
        // brutoKolona.setMinWidth(200);
        netoKolona.setCellValueFactory(new PropertyValueFactory<>("Neto"));
        TableColumn plataKolona = new TableColumn("Plata");
        // brutoKolona.setMinWidth(200);
        plataKolona.setCellValueFactory(new PropertyValueFactory<>("Plata"));
        tabelaPlata.setItems(lista);
       */

    }
    /*
     public ObservableList<MojaPlata> getMojaPlata() {
      /*  List<DTOZaposleni> listaZaposlenihSaPlatom = RacunovodjaServis.pregledajListuPlata();

        List<MojaPlata> listaMoja = new ArrayList<>();
        for (DTOZaposleni zaposleni : listaZaposlenihSaPlatom) {
            double doprinosi=
            listaMoja.add(new MojaPlata(zaposleni.getZaposleni().getIme(), zaposleni.getZaposleni().getPrezime(),doprinosi,zaposleni.getZaposleni().getPlata().getBruto(),neto);
        }
        ObservableList<MojZaposleni> listaZaPrikaz = FXCollections.observableArrayList();
        for (MojZaposleni zaposleni : listaMoja) {
            listaZaPrikaz.add(zaposleni);
        }
        return listaZaPrikaz;
    }*/

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sakrijPodatkeFaktura();
        List<DTOZaposleni> listaZaposlenih = RacunovodjaServis.pregledajListuPlata();
        prikazTabele(listaZaposlenih);
    }

}
