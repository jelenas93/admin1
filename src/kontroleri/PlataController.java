package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import multipleksadmin.AlertHelper;
import projektovanje.bin.plata.Plata;
import projektovanje.dto.DTOZaposleni;
import projektovanje.servisi.RacunovodjaServis;

public class PlataController implements Initializable {

    public static DTOZaposleni zaposleni;
    public static boolean izmjena = false;

    @FXML
    private Label zaposleniLabel;

    @FXML
    private JFXTextField doprinosZaPenzionoField;

    @FXML
    private JFXTextField doprinosZaZdavstvenoField;

    @FXML
    private JFXTextField doprinosZaDJecijuZastituField;

    @FXML
    private JFXTextField doprinosZaZaposljavanjeField;

    @FXML
    private JFXTextField neto;

    @FXML
    private JFXTextField doprinosiUkupno;

    @FXML
    private JFXTextField brutoField;

    @FXML
    private JFXDatePicker datumOdDate;

    @FXML
    private JFXDatePicker datumDoDate;

    @FXML
    private JFXButton sacuvajPlatuButtton, otkaziButton;

    @FXML
    private JFXComboBox<String> noviZaposleniComboBox;

    public static List<DTOZaposleni> listaZaposlenih;

    @FXML
    void sacuvajPlatuStisak(ActionEvent event) throws IOException {
        if (datumDoDate.getValue() == null || datumOdDate.getValue() == null || "".equals(brutoField.getText())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "Greska !",
                    "Niste unijeli sve podatke !");
            return;
        } else {
            Date datum1 = Date.from(datumOdDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date datum2 = Date.from(datumDoDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (datum1.compareTo(datum2) > 0) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska", "Datum zavrsetka radnog dnosa ne moze biti prije datuma pocetka.");
                return;
            }
            Plata plata = new Plata(1, Double.parseDouble(brutoField.getText()), datum1, datum2);
            if (!izmjena) {
                DTOZaposleni novi = null;
                for (DTOZaposleni z : listaZaposlenih) {
                    if (z.getZaposleni().getPrezime().equals(noviZaposleniComboBox.getSelectionModel().getSelectedItem().split(", ")[0])
                            && z.getZaposleni().getIme().equals(noviZaposleniComboBox.getSelectionModel().getSelectedItem().split(", ")[1])) {
                        novi = z;
                    }
                }
                boolean odgovor = RacunovodjaServis.dodajPlatu(plata, novi);
                if (odgovor) {
                    AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Potvrda !",
                            "Uspjesno ste dodali platu !");
                } else {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", "Greska!");
                    return;
                }
                Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/racunovodja.fxml"));
                Scene korisnikScena = new Scene(korisnikView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(korisnikScena);
                window.centerOnScreen();
                window.show();
            } else {
                 boolean odgovor = RacunovodjaServis.azurirajPlatu(plata);
                if (odgovor) {
                    AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Potvrda !",
                            "Uspjesno ste azurirali platu !");
                } else {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", "Greska!");
                    return;
                }
                Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/racunovodja.fxml"));
                Scene korisnikScena = new Scene(korisnikView);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(korisnikScena);
                window.centerOnScreen();
                window.show();
            }
        }
    }

    @FXML
    public void otkaziStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/racunovodja.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!izmjena) {
            for (DTOZaposleni zaposleni : listaZaposlenih) {
                noviZaposleniComboBox.getItems().add(zaposleni.getZaposleni().getPrezime() + ", " + zaposleni.getZaposleni().getIme());
            }
            provjeraBruto();
        } else {
            zaposleniLabel.setVisible(false);
            noviZaposleniComboBox.setVisible(false);
            brutoField.setText(zaposleni.getZaposleni().getPlata().getBruto().toString());
            provjeraBruto();
            datumDoDate.setValue((zaposleni.getZaposleni().getPlata().getDatumDo()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            datumOdDate.setValue((zaposleni.getZaposleni().getPlata().getDatumOd()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
    }

    private void provjeraBruto() {  
        brutoField.textProperty().addListener((observable, oldValue, newValue) -> {
             if(!("".equals(brutoField.getText()))){
            doprinosZaDJecijuZastituField.setText(String.valueOf((Double.parseDouble(brutoField.getText())) * Plata.STOPA_ZA_DJECIJI));
            doprinosiUkupno.setText(String.valueOf((Double.parseDouble(brutoField.getText())) * Plata.STOPA_ZA_DOPRINOSE));
            doprinosZaZaposljavanjeField.setText(String.valueOf((Double.parseDouble(brutoField.getText())) * Plata.STOPA_ZA_NEZAPOSLENE));
            doprinosZaPenzionoField.setText(String.valueOf((Double.parseDouble(brutoField.getText())) * Plata.STOPA_ZA_PIO));
            doprinosZaZdavstvenoField.setText(String.valueOf((Double.parseDouble(brutoField.getText())) * Plata.STOPA_ZA_ZDRAVSTVENO));
            neto.setText(String.valueOf((Double.parseDouble(brutoField.getText())) - Double.parseDouble(doprinosiUkupno.getText())));
             }});
    }
}
