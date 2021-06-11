/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.Enrollment;
import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLEnrollmentOptionsController implements Initializable {

    CircularDoublyLinkedList enrollmentList;
    SinglyLinkedList list;
    String studentIDTable;

    @FXML
    private TableView<List<String>> studentTable;
    @FXML
    private TableColumn<List<String>, String> colID;
    @FXML
    private TableColumn<List<String>, String> colStudentID;
    @FXML
    private TableColumn<List<String>, String> colLastName;
    @FXML
    private TableColumn<List<String>, String> colName;
    @FXML
    private Button btnSelectStudent;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            list = util.Utility.getSinglyList();
            enrollmentList = util.Utility.getEnrollmentList();
            studentIDTable = util.Utility.getStudentIDTable();

            this.colID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(0));
                }
            });

            this.colStudentID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(1));
                }
            });

            this.colLastName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(2));
                }
            });

            this.colName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(3));
                }
            });

            this.studentTable.setItems(getData());

        } catch (ListException ex) {
            Logger.getLogger(FXMLEnrollmentOptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnSelectStudent(ActionEvent event) {
        boolean existStudent = false;
        Enrollment enroll;
        Object selection = studentTable.getSelectionModel().selectedItemProperty().get();

        ArrayList<String> array = new ArrayList();

        array = (ArrayList) selection;
        studentIDTable = array.get(1);

        try {
            if (!enrollmentList.isEmpty()) {
                for (int i = 1; i <= this.enrollmentList.size(); i++) {
                    enroll = (Enrollment) enrollmentList.getNode(i).getData();
                    if (util.Utility.equals(enroll.getStudentID(), studentIDTable)) {
                        existStudent = true;
                    }
                }
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLEnrollmentOptionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!existStudent) {
            util.Utility.setStudentIDTable(studentIDTable);
            loadPage("FXMLAvailableCourses");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);//Horario agregado anteriormente
            alert.setTitle("Information");
            alert.setHeaderText("Enrollment procedure");
            alert.setContentText("Student already enrolled");
            alert.showAndWait();
        }

    }

    private void loadPage(String page) {//Realiza una carga de una pagina generica
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMENUController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bp.setCenter(root);//Realiza la asignacion de la nueva pagina al menu principal
    }

    public ObservableList<List<String>> getData() throws ListException {

        ObservableList<List<String>> data = FXCollections.observableArrayList();
        if(!list.isEmpty()){
            for (int i = 1; i <= list.size(); i++) {
                List<String> arrayList = new ArrayList<>();

                Student stu = (Student) list.getNode(i).data;
                arrayList.add(String.valueOf(stu.getId()));
                arrayList.add(stu.getStudentID());
                arrayList.add(stu.getLastName());
                arrayList.add(stu.getFirstName());
                data.add(arrayList);
            }
        }
        return data;
    }

}
