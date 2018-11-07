package file_copy;

import javafx.application.Application;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import table.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MainCopyApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public MainCopyApp(){
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CopyApp");
        this.initRootLayout();
        this.showSceneOverview();
    }

    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainCopyApp.class.getResource("fxml/RootLayout.fxml"));
            this.rootLayout = loader.load();
            Scene scene = new Scene(this.rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException except){
            except.printStackTrace();
        }
    }

    public void showSceneOverview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainCopyApp.class.getResource("fxml/FileDialog.fxml"));
            AnchorPane sceneOverview = loader.load();
            this.rootLayout.setCenter(sceneOverview);
            CopyController mainController = loader.getController();
            mainController.setMainCopyApp(this);
            mainController.getProgressBar().setVisible(true);
        } catch (IOException except) {
            except.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }
}
