/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multipleksadmin;

/**
 *
 * @author jelen
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class AlertHelper {

    public static void showAlert(Alert.AlertType alertType/*, Window owner*/, String title, String message) {
   
        Alert alert = new Alert(alertType);
        alert.initModality(Modality.APPLICATION_MODAL); // blokirajuce za druge prozore, dok se ovaj ne zatvori
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
       // alert.initOwner(owner);
        alert.showAndWait();
    }
}