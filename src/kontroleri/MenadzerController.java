package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import multipleksadmin.AlertHelper;
import multipleksadmin.MojZaposleni;
import multipleksadmin.MojaSala;
import projektovanje.bin.film.Zanr;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOMenadzer;
import projektovanje.dto.DTOPonuda;
import projektovanje.dto.DTOSala;
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
    private JFXComboBox<String> izmjeniFilmComboBox;

    @FXML
    private ScrollPane scroll;

    @FXML
    private JFXButton izmjeniFilmButton, dodajSlikuButton;

    @FXML
    private JFXTextField trejlerFilmaField, trajanjeFilmaField, nazivFilmaField, slikaLinkField;

    @FXML
    private JFXTextArea opisFilmaArea;

    @FXML
    private JFXComboBox<String> tipFilma;

    @FXML
    private JFXButton sacuvajFilmButton;

    @FXML
    private JFXButton otkaziFilmButton;

    @FXML
    private JFXButton dodajProjekcijuButton;

    @FXML
    private JFXButton izmjeniProjekcijuButton;

    @FXML
    private JFXButton pregledProjekcijaButton;

    @FXML
    private JFXButton kreirajRepertoarButton;

    @FXML
    private JFXButton pregledRepertoaraButton;

    //Sala
    @FXML
    private JFXButton sacuvajSaluButton;

    @FXML
    private JFXButton otkaziSaluButton;

    @FXML
    private JFXTextField nazivSaleField;

    @FXML
    private JFXTextField brojRedovaField;

    @FXML
    private JFXTextField brojKolonaField;

    @FXML
    private TableView<MojaSala> tabelaSala;

    @FXML
    private TableColumn<MojaSala, String> nazivSaleKolona;

    @FXML
    private TableColumn<MojaSala, Integer> brojRedovaKolona;

    @FXML
    private TableColumn<MojaSala, Integer> brojKolona;

    @FXML
    private TableColumn<MojaSala, Integer> brojSjedistaKolona;

    @FXML
    private ImageView slika;
    //Ponuda

    @FXML
    private TableColumn<?, ?> filmKolona;

    @FXML
    private TableColumn<?, ?> datumKolona;

    @FXML
    private JFXButton sacuvajPonuduButton;

    @FXML
    private JFXButton otkaziPonuduButton;

    @FXML
    private JFXTextField ponudaFilmField;

    @FXML
    private JFXDatePicker datumPonudedate;

    @FXML
    private TableView<DTOPonuda> tabelaPonude;

    @FXML
    private JFXComboBox<String> ponudaComboBox;

    private List<DTOZaposleni> listaZaposlenih;

    @FXML
    private GridPane gridPane;

    @FXML
    private JFXCheckBox zanr[];

    @FXML
    private BufferedImage bfslika;
    private byte[] slikaUbajtovima;

    @FXML
    void dodajProjekcijuStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/projekcija.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void kreirajRepertoarStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/kreirajRepertoar.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void dodajSlikuStisak(ActionEvent event) {
         System.out.println(/*"\""+slikaLinkField.getText()+"\""*/"http://digitalspyuk.cdnds.net/18/32/980x490/landscape-1533822689-nun-cp-001r.jpg");
            slika.setImage(new Image(/*"\""+slikaLinkField.getText()+"\""*/"http://digitalspyuk.cdnds.net/18/32/980x490/landscape-1533822689-nun-cp-001r.jpg"));
            bfslika = SwingFXUtils.fromFXImage(slika.getImage(), null);
       
    }

    @FXML
    void izmjeniProjekcijuStisak(ActionEvent event) {

    }

    @FXML
    void pregledProjekcijeStisak(ActionEvent event) {

    }

    @FXML
    void pregledrepertoaraStisak(ActionEvent event) {

    }

    private void postaviSalaTabelu() {
        nazivSaleKolona.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        brojRedovaKolona.setCellValueFactory(new PropertyValueFactory<>("brojVrsta"));
        brojKolona.setCellValueFactory(new PropertyValueFactory<>("brojKolona"));
        brojSjedistaKolona.setCellValueFactory(new PropertyValueFactory<>("sjediste"));
        tabelaSala.setItems(getMojaSala());
    }

    private ObservableList<MojaSala> getMojaSala() {
        List<DTOSala> listaSala = MenadzerServis.prikaziSale();

        List<MojaSala> listaMoja = new ArrayList<>();
        for (DTOSala sala : listaSala) {
            listaMoja.add(new MojaSala(sala.getSala().getIdSale().toString(), sala.getSala().getBrojVrsta(), sala.getSala().getBrojKolona(), sala.getSala().getBrojVrsta() * sala.getSala().getBrojKolona()));
        }
        ObservableList<MojaSala> listaZaPrikaz = FXCollections.observableArrayList();
        for (MojaSala sala : listaMoja) {
            listaZaPrikaz.add(sala);
        }
        return listaZaPrikaz;
    }

    @FXML
    void sacuvajFilmStisak(ActionEvent event) {
        boolean izabrano = false;
        for (JFXCheckBox z : zanr) {
            if (z.isSelected()) {
                izabrano = true;
            }
        }
        if (!izabrano) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli zanr. ");
            return;
        }
        if ("".equals(nazivFilmaField.getText()) || "".equals(opisFilmaArea.getText())
                || "".equals(trejlerFilmaField.getText()) || "".equals(trajanjeFilmaField.getText()) || "".equals(slikaLinkField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli sve podatke. ");
            return;
        } else {
            try {
                if (Integer.parseInt(trajanjeFilmaField.getText()) < 0) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", "Film mora trajati bar 1 minutu. ");
                    return;
                }
                List<Zanr> zanrovi = new ArrayList<>();
                System.out.println(zanr.length);
                for (int i = 0; i < zanr.length; i++) {
                    if (zanr[i].isSelected()) {
                        System.out.println(zanr[i].isSelected());
                        zanrovi.add(new Zanr(1, zanr[i].getText()));
                    }
                }
               // System.out.println(zanrovi);
                boolean odgovor = MenadzerServis.dodajFilm(1, new Zaposleni(id), nazivFilmaField.getText(), Integer.parseInt(trajanjeFilmaField.getText()), opisFilmaArea.getText(), trejlerFilmaField.getText(), tipFilma.getSelectionModel().getSelectedItem(), zanrovi, slikaLinkField.getText());
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

    private void setujSveNaPrazno() {
        nazivFilmaField.setText("");
        opisFilmaArea.setText("");
        trejlerFilmaField.setText("");
        trajanjeFilmaField.setText("");
        for (JFXCheckBox zanr1 : zanr) {
            if (zanr1.isSelected()) {
                zanr1.setSelected(false);
            }
        }
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

    private void setujSaluNaPrazno() {
        nazivSaleField.setText("");
        brojKolonaField.setText("");
        brojRedovaField.setText("");
    }

    @FXML
    void sacuvajPonuduStisak(ActionEvent event) {

    }

    @FXML
    void izmjeniFilmStisak(ActionEvent event) {
        System.out.println("kontroleri.MenadzerController.izmjeniFilmStisak()");
        if (izmjeniFilmComboBox.getSelectionModel().getSelectedIndex() == -1) {
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, "", "Niste izabrali film.");
            return;
        } else {
            DTOFilm film = nadjiFilm(izmjeniFilmComboBox.getSelectionModel().getSelectedItem());
            if (film != null) {
                List<Zanr> listaZanrova = film.getFilm().getZanrovi();
                nazivFilmaField.setText(film.getFilm().getNaziv());
                tipFilma.getSelectionModel().select(film.getFilm().getTipFilma());
                opisFilmaArea.setText(film.getFilm().getOpis());
                trejlerFilmaField.setText(film.getFilm().getLinkTrailera());
                trajanjeFilmaField.setText(film.getFilm().getTrajanje().toString());
                for (JFXCheckBox z : zanr) {
                    for (Zanr fz : listaZanrova) {
                        if (z.getText().equals(fz.getNazivZanra())) {
                            z.setSelected(true);
                        }

                    }
                }
            }
        }
    }

    @FXML
    void sacuvajSaluStisak(ActionEvent event) {
        if ("".equals(nazivSaleField.getText()) || "".equals(brojKolonaField.getText()) || "".equals(brojRedovaField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli podatke");
            return;
        } else {
            try {
                if (Integer.parseInt(brojKolonaField.getText()) < 0) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", ". ");
                    return;
                }
                if (Integer.parseInt(brojRedovaField.getText()) < 0) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", ". ");
                    return;
                }
                Sala sala = new Sala(1, Integer.parseInt(brojRedovaField.getText()), Integer.parseInt(brojKolonaField.getText()), null);
                List<Sjediste> listaSjedista = new ArrayList<>();
                for (int i = 0; i < Integer.parseInt(brojRedovaField.getText()); i++) {
                    for (int j = 0; j < Integer.parseInt(brojKolonaField.getText()); j++) {
                        listaSjedista.add(new Sjediste(1, sala, i, j));
                    }
                }
                sala.setSjedista(listaSjedista);
                boolean odgovor = MenadzerServis.dodajSalu(1, sala.getBrojVrsta(), sala.getBrojKolona(), listaSjedista);
                if (odgovor) {
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "", "Dodata. ");
                    setujSaluNaPrazno();
                    return;
                }
            } catch (NumberFormatException e) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "nije broj. ");
                return;
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //  postaviSalaTabelu();
        tipFilma.getItems().addAll("2D", "3D");
        tipFilma.getSelectionModel().selectFirst();
        dodajZanrove();
        List<DTOFilm> filmovi = MenadzerServis.pregledFilma();
        for (DTOFilm film : filmovi) {
            izmjeniFilmComboBox.getItems().add(film.getFilm().getNaziv());
        }
        //trebam dodati filmove pa onda projekcije

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

    private void dodajZanrove() {
        VBox vBox1 = new VBox();
        ScrollPane s = new ScrollPane();
        String listaZanrova[] = new String[]{"Akcija", "Romantika", "Drama", "Triler", "Komedija", "Horor", "Naucna fantastika", "Avantura", "Animirani", "Kriminalisticki", "Porodicni", "Misterija"};
        zanr = new JFXCheckBox[listaZanrova.length];
        System.out.println(listaZanrova.length);
        for (int i = 0; i < listaZanrova.length; i++) {
            zanr[i] = new JFXCheckBox(listaZanrova[i]);
            vBox1.getChildren().add(zanr[i]);
            vBox1.setSpacing(10);
        }
        s.setContent(vBox1);
        gridPane.add(s, 1, 4);

    }

    public static DTOFilm nadjiFilm(String imeFilma) {
        List<DTOFilm> filmovi = MenadzerServis.pregledFilma();
        for (DTOFilm film : filmovi) {
            if (film.getFilm().getNaziv().equals(imeFilma)) {
                return film;
            }
        }
        return null;
    }
}
