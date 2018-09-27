package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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
import multipleksadmin.AlertHelper;
import projektovanje.dto.DTOZaposleni;
import projektovanje.konekcija.KonekcijaNET;
import projektovanje.servisi.AdministratorServis;

public class UnosZaposlenogController implements Initializable {

    @FXML
    private JFXButton nazadDugme;

    @FXML
    private JFXTextField imeField;

    @FXML
    private JFXTextField prezimeField;

    @FXML
    private JFXTextField maticniField;

    @FXML
    private JFXComboBox<String> pozicijaComboBox;

    @FXML
    private JFXButton sacuvajDugme;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField korisnickoImeFIeld;
    @FXML
    private JFXTextField lozinkaField;

    @FXML
    void nazadStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/admin.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void sacuvajStisak(ActionEvent event) throws IOException {
        if ("".equals(imeField.getText()) || "".equals(prezimeField.getText()) || "".equals(maticniField.getText())
                || "".equals(emailField.getText()) || "".equals(korisnickoImeFIeld.getText()) || "".equals(lozinkaField.getText()) || "".equals(pozicijaComboBox.getSelectionModel().getSelectedItem())) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "Greska !",
                    "Niste unijeli podatke !");
            return;
        } else {
          /*  if(AdminController.nadjiZaposlenogSaMaticnim(maticniField.getText())!=null){
                 AlertHelper.showAlert(Alert.AlertType.INFORMATION, "", "Zaposleni sa tim maticnim brojem vev postoji!");
                 return;
            }*/
            int hash = lozinkaField.getText().hashCode();
            String a = "" + hash;
            String odgovor = AdministratorServis.dodajZaposlenog(imeField.getText(), prezimeField.getText(), maticniField.getText(), korisnickoImeFIeld.getText(), a, pozicijaComboBox.getSelectionModel().getSelectedItem());
            if (odgovor.equals("OK")) {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Potvrda !",
                        "Uspjesno ste dodali novog zaposlenog !");
            } else {
                String razlog = odgovor.split("#")[1];
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", razlog);
                return;
            }
            Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/admin.fxml"));
            Scene korisnikScena = new Scene(korisnikView);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(korisnikScena);
            window.centerOnScreen();
            window.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pozicijaComboBox.getItems().addAll("Administrator", "Kinooperater", "Menadzer", "Racunovodja", "ProdavacKarata", "ProdavacHraneIPica", "Skladistar");
        pozicijaComboBox.getSelectionModel().selectFirst();
    }

}
