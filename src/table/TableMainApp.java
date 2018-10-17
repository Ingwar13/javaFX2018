package table;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import table.controllers.EmployeeEditController;
import table.controllers.MainController;
import table.Employee;

import java.io.IOException;


public class TableMainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Employee> employeeData = FXCollections.observableArrayList();

    public TableMainApp(){
        this.employeeData.add(new Employee("Pete",22,3000));
        this.employeeData.add(new Employee("Ann",24,4000));
        this.employeeData.add(new Employee("Bruce",30,6500));
    }

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EmployeesApp");
        this.initRootLayout();
        this.showSceneOverview();
    }

    public ObservableList<Employee> getEmployeeData(){
        return employeeData;
    }

    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TableMainApp.class.getResource("fxml/RootLayout.fxml"));
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
            loader.setLocation(TableMainApp.class.getResource("fxml/TableEmployees.fxml"));
            AnchorPane sceneOverview = loader.load();
            this.rootLayout.setCenter(sceneOverview);
            MainController mainController = loader.getController();
            mainController.setTableMainApp(this);
        } catch (IOException except) {
            except.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public boolean showEmployeeEditDialog(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TableMainApp.class.getResource("fxml/EmployeeDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Employee");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EmployeeEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEmployee(employee);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
