/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.text.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import projektovanje.dto.DTOArtikal;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class ProdavacHraneiPicaController implements Initializable {

    public static int id;
    @FXML
    private Label ukupnoCijena;

    @FXML
    private JFXButton stampajButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private JFXComboBox<String> sifraComboBox;

    @FXML
    private JFXTextArea racunArea;

    @FXML
    private JFXTextField kolicinaFiled;

    @FXML
    private JFXButton dodajNaRacunButton;

    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private JFXButton obrisiButton;
    int ukupno = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<DTOArtikal> listaArtikala=new ArrayList<>();
        VBox cjenovnik = new VBox();
        cjenovnik.setAlignment(Pos.TOP_LEFT);
        cjenovnik.setSpacing(10);
        cjenovnik.setPadding(new Insets(20, 10, 10, 10));
        Label artikal[] = new Label[12];

        for (int j = 0; j < 12; j++) {
            artikal[j] = new Label("58456" + " Artikal: " + j + "...Cijena 3 (KM)");
            artikal[j].setFont(Font.font("System", 17));
        }
        cjenovnik.getChildren().addAll(artikal);

        borderPane.setLeft(cjenovnik);

    }

    @FXML void obrisistisak(ActionEvent event){
        
    }
    @FXML
    void stampajStisak(ActionEvent event) {
        racunArea.clear();
        ukupnoCijena.setText("");
      
    }

    @FXML
    void dodajNaRacunStisak(ActionEvent event) {
        String sifra = sifraComboBox.getItems().toString();
        int kolicina = Integer.parseInt(kolicinaFiled.getText());
        racunArea.appendText("\nSifra " + sifra);
        racunArea.appendText("  " + kolicina);
     //   sifraComboBox.getItems().set(0, "");
        kolicinaFiled.setText("");
        ukupno += kolicina;
        ukupnoCijena.setText("" + ukupno);
    }

}
