/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.FileManage;
import domain.ListException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLEraseCoursesController implements Initializable {

    CircularDoublyLinkedList coursesList;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private ComboBox combo;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        coursesList = util.Utility.getCircularDoublyList();
        ObservableList<String> list = FXCollections.observableArrayList(convertListToArray());
        combo.setItems(list);
    }

    @FXML
    private void combo(ActionEvent event) {
        this.btnDelete.setVisible(true);
    }

    @FXML
    private void btnDelete(ActionEvent event) throws ListException, IOException {
        
        FileManage fileManage = new FileManage();
        
        String output = (String) combo.getSelectionModel().getSelectedItem();
        
        Course course;
     
        if(coursesList.size()!=1){
            for (int i = 1; i <= coursesList.size(); i++) {
                course = (Course) coursesList.getNode(i).data;
                if (util.Utility.equals(course.getId(), output)) {
                    this.coursesList.remove(course);
                }
            }
        }else{
            course = (Course) coursesList.getNode(1).data;//Comprueba el único elemento existente
                if (util.Utility.equals(course.getId(), output)) {
                    this.coursesList.remove(course);
            }
        }
        
        if (!coursesList.isEmpty()) {
            fileManage.modifyCourses(coursesList);
        } else {
            fileManage.cleanFile("coursesFile.txt");//Limpia el archivo
        }
        
        if(!coursesList.isEmpty()){//Actualiza el combo
            ObservableList<String> list = FXCollections.observableArrayList(convertListToArray());
            combo.setItems(list);
        }else{
            ObservableList<String> list = FXCollections.observableArrayList();
            combo.setItems(list);
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("EraseCourse");
            alert.setHeaderText("Success");
            alert.setContentText("The course has been successfully deleted");
            alert.showAndWait();
        
    }

    public String[] convertListToArray() {

        if (coursesList.isEmpty()) {
            this.combo.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erase Course");
            alert.setHeaderText("Alert");
            alert.setContentText("You need to add courses");

            alert.showAndWait();
        } else {

            try {
                String[] array = new String[coursesList.size()];

                Course course = new Course();
                for (int i = 1; i <= coursesList.size(); i++) {
                    course = (Course) coursesList.getNode(i).data;
                    array[i - 1] = course.getId();
                }
                return array;
            } catch (ListException ex) {
                Logger.getLogger(FXMLEraseCareerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}
