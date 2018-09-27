package kontroleri;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import projektovanje.bin.film.Film;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.DTORepertoar;
import projektovanje.servisi.ProdavacKarataServis;

public class ProdavacKarataController implements Initializable {

    public static int id;
    @FXML
    private JFXComboBox<String> filmComboBox;

    @FXML
    private JFXComboBox<String> terminComboBox;  //mozda kao date

    @FXML
    private JFXButton rezervisiButton;

    @FXML
    private JFXButton kupiButton;

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
        for (Projekcija projekcija : listaProjekcija) {
            if (filmComboBox.getSelectionModel().getSelectedItem().equals(projekcija.getFilm().getNaziv())) {
                termin.add(projekcija.getVrijeme().toString());
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
       /* filmComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {

            //      DTORepertoar trenutniRepertoar = ProdavacKarataServis.pregledTrenutongRepertoara();
            // List<Projekcija> listaProjekcija = trenutniRepertoar.getRepertoar().getProjekcija();
            
            for (Projekcija projekcija : listaProjekcija) {
                if (filmComboBox.getSelectionModel().getSelectedItem().equals(projekcija.getFilm().getNaziv())) {
                    termin.add(projekcija.getVrijeme().toString());
                }

            }
        }
        );*/
       
        // List<String> termin=new ArrayList<>();
        for(Projekcija projekcija: listaProjekcija){
         //   if(projekcija.getVrijeme()){
            
                termin.add(projekcija.getVrijeme().toString());
           // }
                   
        }
         terminComboBox.getItems().addAll(termin);

    }

}
