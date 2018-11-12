package word_repeat_count;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextController {

    private MainWordApp mainWordApp;

    @FXML
    private Label chooseFileLabel;

    @FXML
    private Label exclusionsLabel;

    @FXML
    private TableView<WordData> wordTable;

    @FXML
    private TableColumn<WordData,String> wordColumn;

    @FXML
    private TableColumn<WordData,String> quantityColumn;

    private ObservableList<WordData> wordDataList = FXCollections.observableArrayList();


    public void setMainWordApp(MainWordApp maiWordApp){
        this.mainWordApp = maiWordApp;
    }

    public void initialize() {
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("Word"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Count"));
        chooseFileLabel.setText("No file");
        exclusionsLabel.setText("No file");
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
    	Pattern pattern = Pattern.compile("[a-zA-Z']{3,}");

    	String line;
		try {
            ArrayList<String> exclusions = new ArrayList<>();
		    if (exclusionsLabel.getText()!="No file") {
                Files.lines(Paths.get(exclusionsLabel.getText()))
                .forEach(a -> exclusions.add(a));
		    }
            wordDataList = FXCollections.observableArrayList();
            HashMap<String,Integer> wordMap = new HashMap<>();
            FileInputStream fileStream = new FileInputStream(chooseFileLabel.getText());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
			 while ((line = bufferedReader.readLine()) != null) {
			     String[] words = line.toLowerCase().split(" ");
			     for (String word : words) {
			         if (pattern.matcher(word).matches() && !exclusions.contains(word)) {
                         wordMap.put(word, wordMap.containsKey(word) ? wordMap.get(word) + 1 : 1);
                     }
			     }
			 }

//            HashMap<String,Integer> wordMap =
//                    Files.lines(Paths.get(chooseFileLabel.getText()))
//                    .forEach(a -> () -> Stream.of(a.toLowerCase().split(" "))
//                        .filter(b -> pattern.matcher(b).matches() && !exclusions.contains(b))
//                        .collect(b -> Collectors.toMap(b, Collectors.counting()));
                        //.forEach(b -> wordMap.put(b, wordMap.containsKey(b) ? wordMap.get(b) + 1 : 1)));

           // Stream<Map.Entry<String,Integer>> sorted =
            wordMap.entrySet().stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .forEach(a -> wordDataList.add(new WordData(a.getKey(),a.getValue().toString())));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
         wordTable.setItems(wordDataList);
    }
}
