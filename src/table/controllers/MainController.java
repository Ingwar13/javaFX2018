package table.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import table.TableMainApp;
import table.Employee;

public class MainController{

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee,String> nameColumn;
    @FXML
    private TableColumn<Employee,Integer> ageColumn;
    @FXML
    private TableColumn<Employee,Integer> salaryColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label salaryLabel;

    private TableMainApp tableMainApp;

    public MainController(){
    }

    @FXML
    private void initialiaze(){
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
        showEmployeeDetails(null);
        employeeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showEmployeeDetails(newValue));
    }

    public  void setTableMainApp(TableMainApp tableMainApp){
        this.tableMainApp = tableMainApp;
        employeeTable.setItems(tableMainApp.getEmployeeData());
    }

    private void showEmployeeDetails(Employee employee){
        if (employee != null){
            nameLabel.setText(employee.getName());
            ageLabel.setText(employee.getAge().toString());
            salaryLabel.setText(employee.getSalary().toString());
        } else {
            nameLabel.setText("");
            ageLabel.setText("");
            salaryLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteEmployee() {
        int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            employeeTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(tableMainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Employee Selected");
            alert.setContentText("Please select an employee in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleReadEmployee(){
        int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            Employee emp = employeeTable.getItems().get(selectedIndex);
            nameLabel.setText(emp.getName());
            ageLabel.setText(emp.getAge().toString());
            salaryLabel.setText(emp.getSalary().toString());
        }
    }

    @FXML
    private void handleNewPerson() {
        Employee tempEmployee = new Employee();
        boolean okClicked = tableMainApp.showEmployeeEditDialog(tempEmployee);
        if (okClicked) {
            tableMainApp.getEmployeeData().add(tempEmployee);
        }
    }

    @FXML
    private void handleEditPerson() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            boolean okClicked = tableMainApp.showEmployeeEditDialog(selectedEmployee);
            if (okClicked) {
                showEmployeeDetails(selectedEmployee);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(tableMainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
