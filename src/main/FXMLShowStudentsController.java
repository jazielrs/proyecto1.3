/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLShowStudentsController implements Initializable {

    @FXML
    private TableColumn cId;
    @FXML
    private TableColumn cName;
    @FXML
    private TableColumn cFirstName;
    @FXML
    private TableColumn cEmail;
    @FXML
    private TableColumn cIdCarrer;
    @FXML
    private TableColumn cIdStudent;
    @FXML
    private TableColumn cMovil;
    @FXML
    private TableColumn cAddress;
    @FXML
    private TableColumn cAge;
    @FXML
    private TableView<Student> tableShow;

    private ObservableList<Student> students;
    SinglyLinkedList list = util.Utility.getSinglyList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        students = FXCollections.observableArrayList();
        this.cId.setCellValueFactory(new PropertyValueFactory("id"));
        this.cFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        this.cAddress.setCellValueFactory(new PropertyValueFactory("address"));
        this.cEmail.setCellValueFactory(new PropertyValueFactory("email"));
        this.cIdCarrer.setCellValueFactory(new PropertyValueFactory("carrerID"));
        this.cIdStudent.setCellValueFactory(new PropertyValueFactory("studentID"));
        this.cMovil.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        this.cName.setCellValueFactory(new PropertyValueFactory("lastName"));
        this.cAge.setCellValueFactory(new PropertyValueFactory("birthday"));
        if (list.isEmpty()) {
            Alert outAlert = new Alert(Alert.AlertType.INFORMATION);
            outAlert.setContentText("No hay estudiantes matriculados");
            outAlert.show();
        } else {
            try {
                studentsToList();
            } catch (ListException ex) {
                Logger.getLogger(FXMLShowStudentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.tableShow.setItems(students);
        }
    }

    public void studentsToList() throws ListException {
        for (int i = 1; i <= list.size(); i++) {
            students.add((Student) list.getNode(i).getData());
        }
        System.out.println("Entra a toList");
    }

}
