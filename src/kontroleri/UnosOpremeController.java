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
public class UnosOpremeController implements Initializable {


    @FXML
    private JFXTextField nazivField;

    @FXML
    private JFXComboBox<String> ispravnostComboBox;
   
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
        

    }

}
