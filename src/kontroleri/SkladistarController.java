/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import projektovanje.dto.DTOArtikal;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class SkladistarController implements Initializable {
    
    public static int id;
@FXML
    private TableView<?> tabela;

    @FXML
    private JFXButton dodajButton;

    @FXML
    private JFXButton izmjeniButton;

    @FXML
    private JFXButton obrisiButton;
   
    @FXML
    private JFXTextField traziArtikal;

    @FXML
    void dodajStisak(ActionEvent event) throws IOException {
        Parent korisnikView = FXMLLoader.load(getClass().getResource("/gui/unosArtikla.fxml"));
        Scene korisnikScena = new Scene(korisnikView);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(korisnikScena);
        window.centerOnScreen();
        window.show();
    }

    @FXML
    void izmjeniStisak(ActionEvent event) {
        UnosArtiklaController.izmjena=true;
    }

    @FXML
    void obrisiStisak(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    
}
