package word_repeat_count;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

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
    	File processFile = new File(chooseFileLabel.getText());
    	File exlusionsFile = new File(exclusionsLabel.getText());
    	Pattern pattern = Pattern.compile("[a-zA-Z]{3,}");
    	
    	FileReader reader;
    	String line;
		try {
			reader = new FileReader(chooseFileLabel.getText());
			BufferedReader bufferedReader = new BufferedReader(reader);
			 while ((line = bufferedReader.readLine()) != null) {
			     for (String word : line.split("[a-zA-Z]{3,}")) {
			    	 wordMap.put(word, wordMap.get(word)!=null?wordMap.get(word)+1 : 1);
			     }	     
			    }
			 reader.close();
			 Iterator it = wordMap.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        System.out.println(pair.getKey() + " = " + pair.getValue());
			    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
//    	try {
//    		Files.lines(Paths.get(chooseFileLabel.getText()))
//    		.forEach(a -> a.split("[a-zA-Z]{3,}"));			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	//processFile.
    }

}
