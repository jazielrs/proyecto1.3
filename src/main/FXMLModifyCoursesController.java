/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.Course;
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
public class FXMLModifyCoursesController implements Initializable {

    CircularDoublyLinkedList coursesList;
    DoublyLinkedList careerList;

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Text txtCourseID;
    @FXML
    private Text txtCourseName;
    @FXML
    private Text txtCredits;
    @FXML
    private Text txtCareerID;
    @FXML
    private Text txtCareer;
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
    private Button btnClean;
    @FXML
    private ComboBox comboChooseID;
    @FXML
    private Button btnModifyCourse;

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
            comboCareer.setItems(list);//Carga id carreras

            ObservableList<String> list1 = FXCollections.observableArrayList(convertIDCoursesToArray());
            comboChooseID.setItems(list1);//Carga id cursos
        } catch (ListException ex) {
            Logger.getLogger(FXMLAddCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] convertListToArray() throws ListException {
        String[] array = new String[careerList.size()];
        Career c;
        for (int i = 1; i <= careerList.size(); i++) {
            c  = (Career) careerList.getNode(i).data;
            array[i - 1] = c.getDescription();
        }
        return array;
    }

    public String[] convertIDCoursesToArray() throws ListException {
        String[] a = new String[coursesList.size()];

        Course course = new Course();
        for (int i = 1; i <= coursesList.size(); i++) {
            course = (Course) coursesList.getNode(i).data;
            a[i - 1] = course.getId();
        }
        return a;
    }

    @FXML
    private void btnModifyCourse(ActionEvent event) throws ListException, IOException {
        FileManage fileManage = new FileManage();
        String output = (String) comboChooseID.getSelectionModel().getSelectedItem();

        if (!"".equals(this.txtFieldCourseID.getText()) && !"".equals(this.txtFieldCourseName.getText())
                && !"".equals(Integer.parseInt(this.txtFieldCredits.getText()))
                && !"".equals(Integer.parseInt(this.txtFieldCareerID.getText()))) {

            Course course = new Course();

            for (int i = 1; i <= coursesList.size(); i++) {

                course = (Course) coursesList.getNode(i).data;

                if (util.Utility.equals(course.getId(), output)) {
                    course.setId(this.txtFieldCourseID.getText());
                    course.setName(this.txtFieldCourseName.getText());
                    course.setCredits(Integer.parseInt(this.txtFieldCredits.getText()));
                    course.setCarrerID(Integer.parseInt(this.txtFieldCareerID.getText()));
                }
            }

            fileManage.modifyCourses(coursesList);
            
            ObservableList<String> list1 = FXCollections.observableArrayList(convertIDCoursesToArray());
            comboChooseID.setItems(list1);
            
            this.txtFieldCourseID.setText("");
            this.txtFieldCourseName.setText("");
            this.txtFieldCredits.setText("");
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ModifyCourses");
            alert.setHeaderText("Success");
            alert.setContentText("The course has been successfully modified");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Please, fill the blank space");
            alert.showAndWait();
        }

    }

    @FXML
    private void btnClean(ActionEvent event) {
        this.txtFieldCourseID.setText("");
        this.txtFieldCourseName.setText("");
        this.txtFieldCredits.setText("");
        this.txtFieldCareerID.setText("");

    }

    @FXML
    private void comboChooseID(ActionEvent event) throws ListException {//Carga la informacion del curso

        String output = (String) this.comboChooseID.getSelectionModel().getSelectedItem();

        this.txtCourseID.setVisible(true);
        this.txtCourseName.setVisible(true);
        this.txtCredits.setVisible(true);
        this.txtCareer.setVisible(true);
        this.txtCareerID.setVisible(true);
        this.txtFieldCourseID.setVisible(true);
        this.txtFieldCourseName.setVisible(true);
        this.txtFieldCredits.setVisible(true);
        this.comboCareer.setVisible(true);
        this.txtFieldCareerID.setVisible(true);
        this.btnModifyCourse.setVisible(true);
        this.btnClean.setVisible(true);

        Course course = new Course();

        if (output != null) {
            for (int i = 1; i <= coursesList.size(); i++) {
                course = (Course) coursesList.getNode(i).data;
                if (output.equalsIgnoreCase(course.getId())) {
                    this.txtFieldCourseID.setText(course.getId());
                    this.txtFieldCourseName.setText(course.getName());
                    this.txtFieldCredits.setText(String.valueOf(course.getCredits()));
                }
            }
        }
    }

    @FXML
    private void comboCareer(ActionEvent event) throws ListException, IOException {      
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
