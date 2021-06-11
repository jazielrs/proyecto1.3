/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.Career;
import domain.DoublyLinkedList;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLAddCourseController implements Initializable {

    CircularDoublyLinkedList coursesList;
    DoublyLinkedList careerList;

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField txtFieldCourseID;
    @FXML
    private TextField txtFieldCourseName;
    @FXML
    private TextField txtFieldCredits;
    @FXML
    private TextField txtFieldCareerID;
    @FXML
    private ComboBox comboCareer;
    @FXML
    private Button btnAddCourse;
    @FXML
    private Button btnClean;
    @FXML
    private Text txtCareerID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            coursesList = util.Utility.getCircularDoublyList();
            careerList = util.Utility.getDoublyList();
            ObservableList<String> list = FXCollections.observableArrayList(convertListToArray());
            comboCareer.setItems(list);
        } catch (ListException ex) {
            Logger.getLogger(FXMLAddCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String[] convertListToArray() throws ListException {
        String[] array = new String[careerList.size()];
        Career c;
        for (int i = 1; i <= careerList.size(); i++) {
            c = (Career) careerList.getNode(i).data;
            array[i - 1] =  c.getDescription();              
        }
        return array;
    }

    @FXML
    private void btnAddCourse(ActionEvent event) throws ListException, IOException {

        FileManage fileManage = new FileManage();

        if (!"".equals(this.txtFieldCourseID.getText()) && !"".equals(this.txtFieldCourseName.getText())
                && !"".equals(this.txtFieldCredits.getText())) {

            Course course = new Course(this.txtFieldCourseID.getText(),
                    this.txtFieldCourseName.getText(), Integer.parseInt(this.txtFieldCredits.getText()),
                    Integer.parseInt(this.txtFieldCareerID.getText()));

            if (!coursesList.isEmpty()) {//Hay al menos una carrera ingresada
                Course aux;
                Object a;
//                Course auxCourse;
                boolean existCourse = false;

                for (int i = 1; i <= coursesList.size(); i++) {
                    a = coursesList.getNode(i).data;
                    aux = (Course) coursesList.getNode(i).data;

                    if (util.Utility.equals(course.getName(), aux.getName())) {
                        existCourse = true;
                    }

//                    auxCourse = (Course) coursesList.getNode(i).data;//Crea nuevo curso
//                    if (course.getName().equalsIgnoreCase(aux.getName())) {//Evita que se agregue curso con misma descripcion
//                        existCourse = true;
//                    }
                }
                if (!existCourse) {
                    coursesList.add(course);
                    fileManage.addCourses(course);
                    this.txtFieldCourseID.setText("");
                    this.txtFieldCourseName.setText("");
                    this.txtFieldCredits.setText("");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Course");
                    alert.setHeaderText("Course");
                    alert.setContentText("New course added");
                    alert.showAndWait();
//                    txtFieldCareerID.setText(String.valueOf(domain.Career.getId() + 1));
                }
            } else {//Lista de cursos vacia
                coursesList.add(course);
                fileManage.addCourses(course);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add Course");
                alert.setHeaderText("Course");
                alert.setContentText("New course added");
                alert.showAndWait();
                this.txtFieldCourseID.setText("");
                this.txtFieldCourseName.setText("");
                this.txtFieldCredits.setText("");
//                txtFieldCareerID.setText(String.valueOf(domain.Career.getId() + 1));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Please, insert a course");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnClean(ActionEvent event) {
        this.txtFieldCareerID.setText("");
        this.txtFieldCourseID.setText("");
        this.txtFieldCourseName.setText("");
        this.txtFieldCredits.setText("");
    }

    @FXML
    private void comboSelection(ActionEvent event) {
        Career c;
        try {
            for(int i = 1; i <= careerList.size(); i++){
                c = (Career) careerList.getNode(i).data;
                if(c.getDescription().equalsIgnoreCase((String)comboCareer.getSelectionModel().getSelectedItem())){
                    this.txtFieldCareerID.setText(String.valueOf(i));
                }
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLAddCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
