package table.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import table.Employee;

public class EmployeeEditController {

    @FXML
    private TextField nameCU;

    @FXML
    private TextField ageCU;

    @FXML
    private TextField salaryCU;

    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        nameCU.setText(employee.getName());
        ageCU.setText(employee.getAge().toString());
        salaryCU.setText(employee.getSalary().toString());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            employee.setName(nameCU.getText());
            employee.setAge(Integer.parseInt(ageCU.getText()));
            employee.setSalary(Integer.parseInt(salaryCU.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameCU.getText() == null || nameCU.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (ageCU.getText() == null || ageCU.getText().length() == 0) {
            errorMessage += "No valid age!\n";
        }

        if (salaryCU.getText() == null || salaryCU.getText().length() == 0) {
            errorMessage += "No valid salary!\n";

        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
