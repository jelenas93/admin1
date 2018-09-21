package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import multipleksadmin.MojZaposleni;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.*;

import projektovanje.servisi.AdministratorServis;

public class AdminController implements Initializable {

    public static int id;
    public static DTOZaposleni zaposleni;
    @FXML
    private TableView<MojZaposleni> tabela;

    @FXML
    private TableColumn<MojZaposleni, String> maticniBrojKolona;

    @FXML
    private TableColumn<MojZaposleni, String> imeKolona;

    @FXML
    private TableColumn<MojZaposleni, String> prezimeKolona;

    @FXML
    private TableColumn<MojZaposleni, String> pozicijaKolona;
    
    

    @FXML
    private JFXButton dodajButton;

    @FXML
    private JFXButton IzmjeniButton;

    @FXML
    private JFXButton obrisiZaposlenog;

    @FXML
    private JFXButton dodajKorisnikaButton;

    @FXML
    private JFXTextField traziZaposlenogField;

    @FXML
    void dodajStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosZaposlenog.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void dodajKorisnikaStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/dodajKorisnika.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void izmjeniStisak(ActionEvent event) throws IOException {
        MojZaposleni zaposleni = tabela.getSelectionModel().getSelectedItem();
        List<DTOZaposleni> svi = AdministratorServis.prikaziZaposlene();
        for (DTOZaposleni z : svi) {
            if (z.getZaposleni().getJMBG().equals(zaposleni.getJMBG())) {
                IzmjenaZaposlenogController.zaposleni = z;
                Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/izmjenaZaposlenog.fxml"));
                Scene korisnikScena = new Scene(korisnikView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(korisnikScena);
                window.centerOnScreen();
                window.show();
            }
        }

    }

    @FXML
    void promjenaLozinkeStisak(ActionEvent event) throws IOException {
        IzmjenaZaposlenogController.zaposleni.getZaposleni().setIdZaposlenog(id);
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/promjenaLozinke.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void obrisiStisak(ActionEvent event) {
        ObservableList<MojZaposleni> mojZaposleni, sviSaposleni;
        MojZaposleni moj;
        if (!tabela.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            sviSaposleni = tabela.getItems();
            moj = tabela.getSelectionModel().getSelectedItem();
           // mojZaposleni.forEach(sviSaposleni::remove);
            List<DTOZaposleni> listaZaposlenih = AdministratorServis.prikaziZaposlene();
            for (DTOZaposleni zaposleni : listaZaposlenih) {
                if (zaposleni.getZaposleni().getJMBG().equals(tabela.getSelectionModel().getSelectedItem().getJMBG())) {
                    String odgovor = AdministratorServis.brisanjeZaposlenog(zaposleni.getZaposleni().getNalog().getKorisnickiNalog());
                    if (odgovor.startsWith("OK")) {
                        tabela.getItems().remove(moj);
                        postaviTabelu();
                        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "", "Uspjesno ste obrisali zaposlenog.");
                        return;
                    } else {
                        AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska.");
                        return;
                    }

                }
            }

        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali zaposlenog za brisanje.");
            return;
        }
    }

    private void postaviTabelu() {

        maticniBrojKolona.setCellValueFactory(new PropertyValueFactory<>("JMBG"));

        imeKolona.setCellValueFactory(new PropertyValueFactory<>("ime"));

        prezimeKolona.setCellValueFactory(new PropertyValueFactory<>("prezime"));
       
        pozicijaKolona.setCellValueFactory(new PropertyValueFactory<>("pozicija"));

        this.tabela.setItems(getMojZaposleni());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        postaviTabelu();
    }

    public ObservableList<MojZaposleni> getMojZaposleni() {
        List<DTOZaposleni> listaZaposlenih = AdministratorServis.prikaziZaposlene();

        List<MojZaposleni> listaMoja = new ArrayList<>();
        for (DTOZaposleni zaposleni : listaZaposlenih) {
            listaMoja.add(new MojZaposleni(zaposleni.getZaposleni().getJMBG(), zaposleni.getZaposleni().getIme(), zaposleni.getZaposleni().getPrezime(),""));
        }
        ObservableList<MojZaposleni> listaZaPrikaz = FXCollections.observableArrayList();
        for (MojZaposleni zaposleni : listaMoja) {
            listaZaPrikaz.add(zaposleni);
        }
        return listaZaPrikaz;
    }

    public static DTOZaposleni nadjiZaposlenogSaMaticnim(String maticni) {
        List<DTOZaposleni> listaZaposlenih = AdministratorServis.prikaziZaposlene();
        for (DTOZaposleni zaposleni : listaZaposlenih) {
            if (zaposleni.getZaposleni().getJMBG().equals(maticni)) {
                return zaposleni;
            }
        }
        return null;
    }

}
