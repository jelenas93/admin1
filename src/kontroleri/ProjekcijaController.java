package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;
import multipleksadmin.AlertHelper;
import projektovanje.bin.repertoar.Repertoar;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.dto.DTOFilm;
import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.DTORepertoar;
import projektovanje.dto.DTOSala;
import projektovanje.servisi.MenadzerServis;
import projektovanje.servisi.ProdavacKarataServis;

public class ProjekcijaController implements Initializable {

    @FXML
    private JFXComboBox<String> filmComboBox;

    @FXML
    private JFXComboBox<String> salaComboBox;

    @FXML
    private JFXDatePicker datum;

    @FXML
    private JFXTextField daniField;

    @FXML
    private JFXTimePicker vrijeme;

    @FXML
    private JFXButton sacuvajButton;

    @FXML
    private JFXButton otkaziButton;

    @FXML
    void otkaziStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/menadzer.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();

    }

    @FXML
    void sacuvajStisak(ActionEvent event) throws IOException {
        if (filmComboBox.getSelectionModel().getSelectedIndex() == -1 || salaComboBox.getSelectionModel().getSelectedIndex() == -1 || "".equals(vrijeme.getValue().toString()) || "".equals(datum.getValue().toString()) || "".equals(daniField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Niste unijeli podatke.");
            return;
        } else {
            try {
                DTOFilm film = MenadzerController.nadjiFilm(filmComboBox.getSelectionModel().getSelectedItem());
                if (film != null) {
                    LocalDate pomocno = datum.getValue();
                    int brojac=0;
                    for (int i = 0; i < Integer.parseInt(daniField.getText()); i++) {
                        LocalDateTime local = LocalDateTime.of(pomocno, vrijeme.getValue());
                        Date date = Date.from(local.atZone(ZoneId.systemDefault()).toInstant());
                        System.out.println( pomocno.plusDays(1));
                        pomocno=pomocno.plusDays(1);
                        int idSale = Integer.parseInt(salaComboBox.getSelectionModel().getSelectedItem().split(" ")[1]);
                        DTORepertoar rep = ProdavacKarataServis.pregledTrenutongRepertoara();
                        boolean odg = MenadzerServis.dodajProjekcijuNaRepertoar(1, film.getFilm(), date, new Zaposleni(MenadzerController.id), rep, idSale);
                        if (odg) {
                            brojac++;
                        } else {
                            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Greska.");
                            return;
                        }
                    }
                    if(brojac==Integer.parseInt(daniField.getText())){
                        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, "", "Dodata projekcija.");
                    }
                }
            } catch (NumberFormatException e) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, "", "Broj dana mora biti broj.");
                return;
            }

        }
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/menadzer.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<DTOFilm> filmovi = MenadzerServis.pregledFilma();
        for (DTOFilm film : filmovi) {
            filmComboBox.getItems().add(film.getFilm().getNaziv());
        }

        List<DTOSala> listaSala = MenadzerServis.prikaziSale();
        for (DTOSala s : listaSala) {
            String sala = "Sala " + s.getSala().getIdSale();
            salaComboBox.getItems().add(sala);
        }
    }

}
