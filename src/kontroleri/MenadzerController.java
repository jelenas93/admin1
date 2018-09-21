package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import multipleksadmin.AlertHelper;
import projektovanje.bin.film.Zanr;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOMenadzer;
import projektovanje.dto.DTOPonuda;
import projektovanje.dto.DTOZanr;
import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;
import projektovanje.servisi.AdministratorServis;
import projektovanje.servisi.MenadzerServis;

public class MenadzerController implements Initializable {

    public static int id;
    public Zaposleni zaposleni;

    //Film
    @FXML
    private Label nazivFilmaLabel, opisFilmaLabel, tipFilmaLabel, trajanjeFilmaLabel, slikaFilmaLabel, trejlerFilmaLabel;

    @FXML
    private JFXButton dodajFilmButton;

    @FXML
    private JFXComboBox<String> izmjeniFilmComboBox;

    @FXML
    private JFXComboBox<String> obrisiFilmComboBox;
    @FXML
    private ImageView slikaFilmaView;

    @FXML
    private JFXTextField trejlerFilmaField, trajanjeFilmaField, nazivFilmaField;

    @FXML
    private JFXTextArea opisFilmaArea;

    @FXML
    private JFXComboBox<String> tipFilma;

    @FXML
    private JFXButton sacuvajFilmButton;

    @FXML
    private JFXButton otkaziFilmButton;

    @FXML
    private JFXButton obrisiFilmButton;

    @FXML
    private JFXCheckBox akcija, horor, triler;

    //Sala
    @FXML
    private Label nazivSaleLabel;

    @FXML
    private Label brojredovaLabel;

    @FXML
    private Label brojKolonaLabel;

    @FXML
    private JFXButton sacuvajSaluButton;

    @FXML
    private JFXButton otkaziSaluButton;

    @FXML
    private JFXButton obrisiSaluButton;

    @FXML
    private JFXTextField nazivSaleField;

    @FXML
    private JFXTextField brojRedovaField;

    @FXML
    private JFXTextField brojKolonaField;

    @FXML
    private JFXButton dodajSaluButton;

    @FXML
    private JFXComboBox<String> izmjeniSaluComboBox;

    @FXML
    private JFXComboBox<String> obrisiSaluComboBox;
    //Ponuda

    @FXML
    private TableColumn<?, ?> filmKolona;

    @FXML
    private TableColumn<?, ?> datumKolona;

    @FXML
    private Label ponudaFilmLabel;

    @FXML
    private Label datumPonudaLabel;

    @FXML
    private JFXButton sacuvajPonuduButton;

    @FXML
    private JFXButton otkaziPonuduButton;

    @FXML
    private JFXButton obrisiPonuduButton;

    @FXML
    private JFXTextField ponudaFilmField;

    @FXML
    private JFXDatePicker datumPonudedate;

    @FXML
    private TableView<DTOPonuda> tabelaPonude;

    @FXML
    private JFXButton dodajPonudu;

    @FXML
    private JFXComboBox<String> ponudaComboBox;

    @FXML
    private JFXButton prikazPonudaButton;

    private List<DTOZaposleni> listaZaposlenih;

    @FXML
    void sacuvajFilmStisak(ActionEvent event) {
        if ("".equals(nazivFilmaField.getText()) || "".equals(opisFilmaArea.getText())
                || "".equals(trejlerFilmaField.getText()) || "".equals(trajanjeFilmaField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli sve podatke. ");

        } else {
            try {
                if (Integer.parseInt(trajanjeFilmaField.getText()) < 0) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", "Film mora trajati bar 1 minutu. ");
                    return;
                }
                System.out.println("kontroleri.MenadzerController.sacuvajFilmStisak()");
                List<Zanr> zanrovi = new ArrayList<>();
                if (horor.isSelected()) {
                    zanrovi.add(new Zanr(1, "horor"));
                }
                if (akcija.isSelected()) {
                    zanrovi.add(new Zanr(2, "akcija"));
                   
                }
                if (triler.isSelected()) {
                    zanrovi.add(new Zanr(3, "triler"));
                }
                System.out.println(zanrovi);
                boolean odgovor = MenadzerServis.dodajFilm(1, new Zaposleni(id), nazivFilmaField.getText(), Integer.parseInt(trajanjeFilmaField.getText()), opisFilmaArea.getText(), trejlerFilmaField.getText(), tipFilma.getSelectionModel().getSelectedItem(), zanrovi);
                if (odgovor) {
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "", "Uspjesno ste dodali novi film !");
                    setujSveNaPrazno();
                    return;

                } else {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "", "NIste dodali film !");
                    setujSveNaPrazno();
                    return;
                }
            } catch (NumberFormatException e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", "Trajanje filma mora biti broj !");
                return;
            }
        }
    }

    @FXML
    void dodajSaluStisak(ActionEvent event) {

    }

    @FXML
    void obrisiFilmStisak(ActionEvent event) {
        setujSveNaPrazno();
    }

    private void setujSveNaPrazno() {
        nazivFilmaField.setText("");
        opisFilmaArea.setText("");
        trejlerFilmaField.setText("");
        trajanjeFilmaField.setText("");
    }

    @FXML
    void otkaziFilmStisak(ActionEvent event) {
        setujSveNaPrazno();
    }

    @FXML
    void otkaziPonuduStisak(ActionEvent event) {
        //sve setujes u text fiels na prazno
    }

    @FXML
    void otkaziSaluStisak(ActionEvent event) {
     setujSaluNaPrazno();
    }

    @FXML
    void obrisiPonuduStisak(ActionEvent event) {
        //sve setujes u text fiels na prazno
    }

    private void setujSaluNaPrazno(){
        nazivSaleField.setText("");
       brojKolonaField.setText("");
       brojRedovaField.setText("");
    }
    @FXML
    void obrisiSaluStisak(ActionEvent event) {
        setujSaluNaPrazno();
    }

    @FXML
    void sacuvajPonuduStisak(ActionEvent event) {

    }

    @FXML
    void sacuvajSaluStisak(ActionEvent event) {
        if("".equals(nazivSaleField.getText()) || "".equals(brojKolonaField.getText()) || "".equals(brojRedovaField.getText())){
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli podatke");
            return;
        }else{
            try{
                if (Integer.parseInt(brojKolonaField.getText()) < 0) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", ". ");
                    return;
                }
                if (Integer.parseInt(brojRedovaField.getText()) < 0) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", ". ");
                    return;
                }
                Sala sala=new Sala(1, Integer.parseInt(brojRedovaField.getText()), Integer.parseInt(brojKolonaField.getText()), null);
                List<Sjediste> listaSjedista=new ArrayList<>();
                for(int i=0;i<Integer.parseInt(brojRedovaField.getText());i++){
                     for(int j=0;j<Integer.parseInt(brojKolonaField.getText());j++){
                         listaSjedista.add(new Sjediste(1, sala, i, j));
                     }
                }
                sala.setSjedista(listaSjedista);
                boolean odgovor=MenadzerServis.dodajSalu(1, sala.getBrojVrsta(), sala.getBrojKolona(), listaSjedista);
                if(odgovor){
                     AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "", "Dodata. ");
                     setujSaluNaPrazno();
                    return;
                }
            }catch(NumberFormatException e){
                  AlertHelper.showAlert(Alert.AlertType.WARNING, "", "nije broj. ");
                    return;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("ID=" + id);

        obrisiFilmButton.setVisible(false);
        obrisiSaluButton.setVisible(false);
        obrisiPonuduButton.setVisible(false);
        tipFilma.getItems().addAll("2D", "3D");
        tipFilma.getSelectionModel().selectFirst();
        
        //izmjeniFilmComboBox.getItems().add(e)

    }

    private Zaposleni nadjiZaposlenog(int id) {
        System.out.println("ID u nadji=" + id);

        for (DTOZaposleni menazer : listaZaposlenih) {
            System.out.println(menazer);
            if (id == menazer.getZaposleni().getIdZaposlenog()) {
                System.out.println("Nasao");
                return menazer.getZaposleni();
            }
        }
        return null;
    }
}
