/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import multipleksadmin.AlertHelper;

/**
 * FXML Controller class
 *
 * @author jelen
 */
public class UnosArtiklaController implements Initializable {

    @FXML
    private JFXTextField barKodField;

    @FXML
    private JFXTextField nazivField;

    @FXML
    private JFXTextField kolicinaField;

    @FXML
    private JFXButton sacuvajDugme;

    @FXML
    private JFXButton nazadDugme;

    @FXML
    private JFXTextField cijenaField;

    @FXML
    private JFXComboBox<String> tipComboBox;
    public static boolean izmjena;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      /*if(izmjena){
          
      }*/
    }

    @FXML
    void nazadStisak(ActionEvent event) {

    }

    @FXML
    void sacuvajStisak(ActionEvent event) {
        if("".equals(barKodField.getText()) || "".equals(nazivField.getText()) || "".equals(kolicinaField.getText())
        || "".equals(cijenaField.getText())){
            AlertHelper.showAlert(Alert.AlertType.ERROR, "", "Prazno");
            return;      
        }else{
            
            
        }

    }

}
