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
        if (!tabela.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            List<Zaposleni> svi = vratiSveZaposlene();
            for (Zaposleni z : svi) {
                if (z.getJMBG().equals(tabela.getSelectionModel().getSelectedItem().getJMBG())) {
                    IzmjenaZaposlenogController.zaposleni = z;
                    Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/izmjenaZaposlenog.fxml"));
                    Scene korisnikScena = new Scene(korisnikView);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(korisnikScena);
                    window.centerOnScreen();
                    window.show();
                }
            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste izabrali zaposlenog za brisanje.");
            return;
        }

    }

    @FXML
    void promjenaLozinkeStisak(ActionEvent event) throws IOException {
        PromjenaLozinkeController.id=this.id;
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/promjenaLozinke.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void obrisiStisak(ActionEvent event) {
     ObservableList<MojZaposleni> mojZaposleni;
        MojZaposleni moj;
        if (!tabela.getSelectionModel().getSelectedItems().toString().equals("[]")) {
            moj = tabela.getSelectionModel().getSelectedItem();
            List<Zaposleni> listaZaposlenih = vratiSveZaposlene();
            for (Zaposleni zaposleni : listaZaposlenih) {
                if (zaposleni.getJMBG().equals(tabela.getSelectionModel().getSelectedItem().getJMBG())) {
                    String odgovor = AdministratorServis.brisanjeZaposlenog(zaposleni.getNalog().getKorisnickiNalog());
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
        System.out.println("Admin "+id);
    }

    public static List<Zaposleni> vratiSveZaposlene(){
        List<List<? extends IDTO>> listaZaposlenih = (List<List<? extends IDTO>>) AdministratorServis.prikaziZaposlene();
        List<DTOAdministrator> listaAktivnihAdministratora = (List<DTOAdministrator>) listaZaposlenih.get(0);
        List<DTOMenadzer> listaAktivnihMenadzera = (List<DTOMenadzer>) listaZaposlenih.get(1);
        List<DTORacunovodja> listaAktivnihRacunovodja = (List<DTORacunovodja>) listaZaposlenih.get(2);
        List<DTOSkladistar> listaAktivnihSkladistara = (List<DTOSkladistar>) listaZaposlenih.get(3);
        List<DTOProdavacKarata> listaAktivnihProdavacaKarata = (List<DTOProdavacKarata>) listaZaposlenih.get(4);
        List<DTOProdavacHraneIPica> listaAktivnihProdavacaHraneIPica = (List<DTOProdavacHraneIPica>) listaZaposlenih.get(5);
        List<DTOKinooperater> listaAktivnihKinooperatera = (List<DTOKinooperater>) listaZaposlenih.get(6);

        List<Zaposleni> listaMoja = new ArrayList<>();
        for (DTOAdministrator zaposleni : listaAktivnihAdministratora) {
            listaMoja.add(zaposleni.getAdministrator());     
        }
        for (DTOMenadzer zaposleni : listaAktivnihMenadzera) {
            listaMoja.add(zaposleni.getMenadzer());
        }
        for (DTORacunovodja zaposleni : listaAktivnihRacunovodja) {
            listaMoja.add(zaposleni.getRacunovodja());
        }
        for (DTOSkladistar zaposleni : listaAktivnihSkladistara) {
            listaMoja.add(zaposleni.getSkladistar());
        }
        for (DTOProdavacKarata zaposleni : listaAktivnihProdavacaKarata) {
            listaMoja.add(zaposleni.getProdavacKarata());
        }
        for (DTOProdavacHraneIPica zaposleni : listaAktivnihProdavacaHraneIPica) {
            listaMoja.add(zaposleni.getProdavacHraneIPica());
        }
        for (DTOKinooperater zaposleni : listaAktivnihKinooperatera) {
            listaMoja.add(zaposleni.getKinooperater());     
        }
        return listaMoja;
    }
   
    public ObservableList<MojZaposleni> getMojZaposleni() {

        List<List<? extends IDTO>> listaZaposlenih = (List<List<? extends IDTO>>) AdministratorServis.prikaziZaposlene();
        List<DTOAdministrator> listaAktivnihAdministratora = (List<DTOAdministrator>) listaZaposlenih.get(0);
        List<DTOMenadzer> listaAktivnihMenadzera = (List<DTOMenadzer>) listaZaposlenih.get(1);
        List<DTORacunovodja> listaAktivnihRacunovodja = (List<DTORacunovodja>) listaZaposlenih.get(2);
        List<DTOSkladistar> listaAktivnihSkladistara = (List<DTOSkladistar>) listaZaposlenih.get(3);
        List<DTOProdavacKarata> listaAktivnihProdavacaKarata = (List<DTOProdavacKarata>) listaZaposlenih.get(4);
        List<DTOProdavacHraneIPica> listaAktivnihProdavacaHraneIPica = (List<DTOProdavacHraneIPica>) listaZaposlenih.get(5);
        List<DTOKinooperater> listaAktivnihKinooperatera = (List<DTOKinooperater>) listaZaposlenih.get(6);

        List<MojZaposleni> listaMoja = new ArrayList<>();
        for (DTOAdministrator zaposleni : listaAktivnihAdministratora) {
            listaMoja.add(new MojZaposleni(zaposleni.getAdministrator().getJMBG(), zaposleni.getAdministrator().getIme(), zaposleni.getAdministrator().getPrezime(), "Administrator"));
            
        }
        for (DTOMenadzer zaposleni : listaAktivnihMenadzera) {
            listaMoja.add(new MojZaposleni(zaposleni.getMenadzer().getJMBG(), zaposleni.getMenadzer().getIme(), zaposleni.getMenadzer().getPrezime(), "Menadzer"));
            
        }
        for (DTORacunovodja zaposleni : listaAktivnihRacunovodja) {
            listaMoja.add(new MojZaposleni(zaposleni.getRacunovodja().getJMBG(), zaposleni.getRacunovodja().getIme(), zaposleni.getRacunovodja().getPrezime(), "Racunovodja"));
            
        }
        for (DTOSkladistar zaposleni : listaAktivnihSkladistara) {
            listaMoja.add(new MojZaposleni(zaposleni.getSkladistar().getJMBG(), zaposleni.getSkladistar().getIme(), zaposleni.getSkladistar().getPrezime(), "Skladistar"));
            
        }
        for (DTOProdavacKarata zaposleni : listaAktivnihProdavacaKarata) {
            listaMoja.add(new MojZaposleni(zaposleni.getProdavacKarata().getJMBG(), zaposleni.getProdavacKarata().getIme(), zaposleni.getProdavacKarata().getPrezime(), "Prodavac karata"));
            
        }
        for (DTOProdavacHraneIPica zaposleni : listaAktivnihProdavacaHraneIPica) {
            listaMoja.add(new MojZaposleni(zaposleni.getProdavacHraneIPica().getJMBG(), zaposleni.getProdavacHraneIPica().getIme(), zaposleni.getProdavacHraneIPica().getPrezime(), "Prodavac hrane i pica"));
            
        }
        for (DTOKinooperater zaposleni : listaAktivnihKinooperatera) {
            listaMoja.add(new MojZaposleni(zaposleni.getKinooperater().getJMBG(), zaposleni.getKinooperater().getIme(), zaposleni.getKinooperater().getPrezime(), "Kinooperater"));
            
        }
        ObservableList<MojZaposleni> listaZaPrikaz = FXCollections.observableArrayList();
        for (MojZaposleni zaposleni : listaMoja) {
            listaZaPrikaz.add(zaposleni);
        }
        return listaZaPrikaz;
        
        /*  List<DTOZaposleni> listaZaposlenih = AdministratorServis.prikaziZaposlene();
        System.out.println(listaZaposlenih);
        List<MojZaposleni> listaMoja = new ArrayList<>();
        for (DTOZaposleni zaposleni : listaZaposlenih) {
            listaMoja.add(new MojZaposleni(zaposleni.getZaposleni().getJMBG(), zaposleni.getZaposleni().getIme(), zaposleni.getZaposleni().getPrezime(), ""));
        }
        ObservableList<MojZaposleni> listaZaPrikaz = FXCollections.observableArrayList();
        for (MojZaposleni zaposleni : listaMoja) {
            listaZaPrikaz.add(zaposleni);
        }
        return listaZaPrikaz;*/
    }

    public static Zaposleni nadjiZaposlenogSaMaticnim(String maticni) {
        List<Zaposleni> svi=vratiSveZaposlene();
        for (Zaposleni zaposleni : svi) {
            if (zaposleni.getJMBG().equals(maticni)) {
                return zaposleni;
            }
        }
        return null;
    }

}
