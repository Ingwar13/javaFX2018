package word_repeat_count;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.HashMap;

public class TextController {

    private MainWordApp mainWordApp;

    @FXML
    private Label chooseFileLabel;

    @FXML
    private Label exclusionsLabel;

    @FXML
    private TableView<String> wordTable;

    @FXML
    private TableColumn<String,String> wordColumn;

    @FXML
    private TableColumn<String,Integer> quantityColumn;

    private HashMap<String,Integer> wordMap;


    public void setMainWordApp(MainWordApp maiWordApp){
        this.mainWordApp = maiWordApp;
    }

    @FXML
    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File for analysis");
        File fileToCopy = fileChooser.showOpenDialog(new Stage());
        chooseFileLabel.setText(fileToCopy.getAbsolutePath());
    }

    @FXML
    private void handleChooseFileExclusions() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose File with exclusions");
        File fileExclusions = fileChooser.showOpenDialog(new Stage());
        exclusionsLabel.setText(fileExclusions.getAbsolutePath());
    }

    @FXML
    private void handleProcess(){

    }

}
