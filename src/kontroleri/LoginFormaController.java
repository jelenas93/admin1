package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import multipleksadmin.AlertHelper;
import projektovanje.login.Login;

public class LoginFormaController {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton loginButton;

    @FXML
    void exitStisak(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void enter(KeyEvent e) throws IOException {
        loginButton.setOnKeyPressed(
                event -> {
                    try {
                        if (username.getText().equals("admin")) {
                            Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/admin.fxml"));
                            Scene korisnikScena = new Scene(korisnikView);
                            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            window.setScene(korisnikScena);
                            window.centerOnScreen();
                            window.show();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(LoginFormaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
    }

    @FXML
    void stisakDugmeta(ActionEvent event) throws IOException {
        if (username.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                    "Unesite korisnicko ime !");
            return;
        } else if (password.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !",
                    "Unesite lozinku !");
            return;
        } else {
            String korisnickoIme = username.getText();
            String lozinka = password.getText();
            int hash = lozinka.hashCode();
            String pomocni = "" + hash;
            String korisnik = Login.login(korisnickoIme, pomocni);
            if (korisnik.startsWith("OK")) {
                if (korisnik.split("#")[1].equals("ADMINISTRATOR")) {
                    AdminController.id = Integer.parseInt(korisnik.split("#")[2]);
                    Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/admin.fxml"));
                    Scene korisnikScena = new Scene(korisnikView);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(korisnikScena);
                    window.centerOnScreen();
                    window.show();
                } else if (korisnik.split("#")[1].equals("KINOOPERATER")) {
                    KinooperaterController.id = Integer.parseInt(korisnik.split("#")[2]);
                    Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/kinooperater.fxml"));
                    Scene korisnikScena = new Scene(korisnikView);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(korisnikScena);
                    window.centerOnScreen();
                    window.show();
                } else if (korisnik.split("#")[1].equals("MENADZER")) {
                    MenadzerController.id = Integer.parseInt(korisnik.split("#")[2]);
                    System.out.println("lOGIN ID=" + MenadzerController.id);
                    Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/menadzer.fxml"));
                    Scene korisnikScena = new Scene(korisnikView);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(korisnikScena);
                    window.centerOnScreen();
                    window.show();
                } else if (korisnik.split("#")[1].equals("PRODAVACHRANEIPICA")) {
                    ProdavacHraneiPicaController.id = Integer.parseInt(korisnik.split("#")[2]);
                    Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/prodavacHraneiPica.fxml"));
                    Scene korisnikScena = new Scene(korisnikView);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(korisnikScena);
                    window.centerOnScreen();
                    window.show();
                } else if (korisnik.split("#")[1].equals("PRODAVACKARATA")) {
                    ProdavacKarataController.id = Integer.parseInt(korisnik.split("#")[2]);
                    Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/prodavacKarata.fxml"));
                    Scene korisnikScena = new Scene(korisnikView);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(korisnikScena);
                    window.centerOnScreen();
                    window.show();
                } else if (korisnik.split("#")[1].equals("SKLADISTAR")) {
                    SkladistarController.id = Integer.parseInt(korisnik.split("#")[2]);
                    Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/skladistar.fxml"));
                    Scene korisnikScena = new Scene(korisnikView);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(korisnikScena);
                    window.centerOnScreen();
                    window.show();
                } else if (korisnik.split("#")[1].equals("RACUNOVODJA")) {
                    RacunovodjaController.id = Integer.parseInt(korisnik.split("#")[2]);
                    Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/racunovodja.fxml"));
                    Scene korisnikScena = new Scene(korisnikView);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(korisnikScena);
                    window.centerOnScreen();
                    window.show();
                }

            } else {
                String razlog = korisnik.split("#")[1];
                AlertHelper.showAlert(Alert.AlertType.ERROR, "Greska !", razlog);
                return;
            }

        }
    }
}
