package file_copy;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class CopyController {
    private MainCopyApp mainCopyApp;
    private String fileName;

    @FXML
    private Label fromLabel;

    @FXML
    private Label toLabel;

    public CopyController() {
    }

    @FXML
    private void initialiaze() {
    }

    public void setMainCopyApp(MainCopyApp mainCopyApp){
        this.mainCopyApp = mainCopyApp;
    }

    @FXML
    private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File fileToCopy = fileChooser.showOpenDialog(new Stage());
        fromLabel.setText(fileToCopy.getAbsolutePath());
        fileName = fileToCopy.getName();
        toLabel.setText(fileToCopy.getName());
    }

    @FXML
    private void handleDestination() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose Destination Folder");
        File destinationFolder = directoryChooser.showDialog(new Stage());
        toLabel.setText(destinationFolder.getAbsolutePath());
    }

    @FXML
    private void handleCopy() {
        File source = new File(fromLabel.getText());
        File destination = new File(toLabel.getText()+fileName);
        try {
            InputStream inputStream = new FileInputStream(source);
            OutputStream outputStream = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
