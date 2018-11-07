package word_repeat_count;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MainWordApp extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;

    public MainWordApp(){
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("wordCountingApp");
        this.initRootLayout();
        this.showSceneOverview();
    }

    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainWordApp.class.getResource("fxml/RootLayout.fxml"));
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
            loader.setLocation(MainWordApp.class.getResource("fxml/TextDialog.fxml"));
            AnchorPane sceneOverview = loader.load();
            this.rootLayout.setCenter(sceneOverview);
            TextController mainController = loader.getController();
            mainController.setMainWordApp(this);
        } catch (IOException except) {
            except.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }
}
