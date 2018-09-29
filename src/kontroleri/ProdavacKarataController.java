package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import projektovanje.bin.film.Film;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.bin.sala.Sala;
import projektovanje.bin.sala.Sjediste;
import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.DTORepertoar;
import projektovanje.dto.DTOSala;
import projektovanje.servisi.MenadzerServis;
import projektovanje.servisi.ProdavacKarataServis;

public class ProdavacKarataController implements Initializable {

    public static int id;

    private int salaID;
    @FXML
    private JFXComboBox<String> filmComboBox;

    @FXML
    private BorderPane pozadina;

    @FXML
    private JFXComboBox<String> terminComboBox;  //mozda kao date

    @FXML
    private JFXComboBox<String> datumComboBox;

    @FXML
    private JFXButton rezervisiButton;

    @FXML
    private JFXButton kupiButton;

    private Sala sala;

    @FXML
    private MaterialIconView icon;

    @FXML
    private Button[][] sjediste;

    @FXML
    void kupiStisak(ActionEvent event) {

    }

    @FXML
    void rezervisiStisak(ActionEvent event) {

    }

    @FXML
    void izaberiFilm(ActionEvent event) {
        DTORepertoar trenutniRepertoar = ProdavacKarataServis.pregledTrenutongRepertoara();
        List<Projekcija> listaProjekcija = trenutniRepertoar.getRepertoar().getProjekcija();
        List<String> termin = new ArrayList<>();
        List<String> dat = new ArrayList<>();
        SimpleDateFormat vrijeme = new SimpleDateFormat("HH:mm");
        SimpleDateFormat datum = new SimpleDateFormat("dd.MM");
        for (Projekcija projekcija : listaProjekcija) {
            if (filmComboBox.getSelectionModel().getSelectedItem().equals(projekcija.getFilm().getNaziv())) {
                if (!(termin.contains(vrijeme.format(projekcija.getVrijeme())))) {
                    termin.add(vrijeme.format(projekcija.getVrijeme()));
                }
                if (!(dat.contains(datum.format(projekcija.getVrijeme())))) {
                    dat.add(datum.format(projekcija.getVrijeme()));
                }
            }
        }
        terminComboBox.getItems().addAll(termin);
        datumComboBox.getItems().addAll(dat);
    }

    @FXML
    void prikaziSalu(ActionEvent event) {
        DTORepertoar trenutniRepertoar = ProdavacKarataServis.pregledTrenutongRepertoara();
        List<Projekcija> listaProjekcija = trenutniRepertoar.getRepertoar().getProjekcija();
        SimpleDateFormat vrijeme = new SimpleDateFormat("HH:mm");
        SimpleDateFormat datum = new SimpleDateFormat("dd.MM");
        for (Projekcija projekcija : listaProjekcija) {
            if (projekcija.getFilm().getNaziv().equals(filmComboBox.getSelectionModel().getSelectedItem())
                    && vrijeme.format(projekcija.getVrijeme()).equals(terminComboBox.getSelectionModel().getSelectedItem())
                    && datum.format(projekcija.getVrijeme()).equals(datumComboBox.getSelectionModel().getSelectedItem())) {
                salaID = projekcija.getIdSale();
            }
        }
        GridPane gridpane = new GridPane();
        List<DTOSala> listaSala = ProdavacKarataServis.prikaziSale(); //dodati u prodavca
        for (DTOSala s : listaSala) {
            if (s.getSala().getIdSale() == salaID) {

                sjediste = new Button[s.getSala().getBrojVrsta()][s.getSala().getBrojKolona()];
                icon = new MaterialIconView(MaterialIcon.EVENT_SEAT);
                for (int i = 0; i < s.getSala().getBrojVrsta(); i++) {
                    for (int j = 0; j < s.getSala().getBrojKolona(); j++) {

                        //if (Sala.seat[i][j].getBookingStatus()) {
                        icon = new MaterialIconView(MaterialIcon.EVENT_SEAT);
                        icon.setStyle("-fx-fill: #000000; -fx-font-family: 'Material Icons'; -fx-font-size: 25.0;");
                        sjediste[i][j] = new Button("", icon);
                        /*   } else {
                            icon = new MaterialIconView(MaterialIcon.EVENT_SEAT);
                            icon.setStyle("-fx-fill: #75dd75; -fx-font-family: 'Material Icons'; -fx-font-size: 35.0;");
                            sjediste[i][j] = new Button("", icon);
                        }*/
                        sjediste[i][j].setBackground(Background.EMPTY);

                        gridpane.add(sjediste[i][j], i, j);

                    }
                }
                BorderPane.setAlignment(gridpane, Pos.BOTTOM_CENTER);
                pozadina.setCenter(gridpane);

            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources
    ) {
        DTORepertoar trenutniRepertoar = ProdavacKarataServis.pregledTrenutongRepertoara();
        List<Projekcija> listaProjekcija = trenutniRepertoar.getRepertoar().getProjekcija();
        List<String> listaFilmovaSaRepertoara = new ArrayList<>();
        for (Projekcija projekcija : listaProjekcija) {
            if (!listaFilmovaSaRepertoara.contains(projekcija.getFilm().getNaziv())) {
                listaFilmovaSaRepertoara.add(projekcija.getFilm().getNaziv());
            }
        }
        filmComboBox.getItems().addAll(listaFilmovaSaRepertoara);
        List<String> termin = new ArrayList<>();
        filmComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            for (Projekcija projekcija : listaProjekcija) {
                if (filmComboBox.getSelectionModel().getSelectedItem().equals(projekcija.getFilm().getNaziv())) {
                    termin.add(projekcija.getVrijeme().toString());
                }
            }
        }
        );
    }

}
