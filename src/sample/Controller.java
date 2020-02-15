package sample;

//import com.sun.corba.se.spi.orbutil.threadpool.Work;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private ListView<Employee> employeeListView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    Button clearButton;
    @FXML
    Button addButton;
    @FXML
    Button removeButton;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        employeeListView.getSelectionModel().selectedItemProperty().addListener((
                ObservableValue < ? extends Worker> ov, Worker old_val, Worker new_val)->
                {
                    Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();

                    firstNameTextField.setText(((Employee)selectedItem).firstName);
                    lastNameTextField.setText(((Employee)selectedItem).lastName);
                    isActiveCheckBox.setSelected(((Employee)selectedItem).isActive);
                }
                );

        ObservableList<Employee> items = employeeListView.getItems();
        for(int i = 0; i < 10; i++)
        {
            Employee employee = new Employee();
            employee.firstName = "Generic";
            employee.lastName = "Employee " + i;
            employee.hire();
            items.add(employee);
        }

        clearButton.setOnAction(actionEvent-> {
            firstNameTextField.clear();
            lastNameTextField.clear();
            isActiveCheckBox.setSelected(false);
        });

        addButton.setOnAction(actionEvent-> {
            Employee newEmp = new Employee();
            newEmp.firstName = firstNameTextField.getText();
            newEmp.lastName = lastNameTextField.getText();
            newEmp.isActive = isActiveCheckBox.isSelected();
            items.add(newEmp);
        });

        removeButton.setOnAction(actionEvent->{
           Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();
           items.remove(selectedItem);
        });

    }

}
