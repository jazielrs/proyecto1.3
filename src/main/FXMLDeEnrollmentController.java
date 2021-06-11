/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.DeEnrollment;
import domain.Enrollment;
import domain.FileManage;
import domain.ListException;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLDeEnrollmentController implements Initializable {

    CircularDoublyLinkedList enrollmentList;
    CircularDoublyLinkedList deEnrollmentList;
    CircularDoublyLinkedList coursesList;
    String courseID;

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField txtFieldStudentID;
    @FXML
    private TableView table;
    @FXML
    private TableColumn colCourse;
    @FXML
    private TableColumn colSchedule;
    @FXML
    private TextField txtFieldRemark;
    @FXML
    private Button btnDeEnroll;
    @FXML
    private Button btnSearch;

    ObservableList<List<String>> data;
    @FXML
    private Button btnFinishDeEnroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        enrollmentList = util.Utility.getEnrollmentList();
        deEnrollmentList = util.Utility.getDeEnrollmentList();
        coursesList = util.Utility.getCircularDoublyList();
    }

    @FXML
    private void btnSearch(ActionEvent event) {
        Enrollment enroll;
        boolean haveEnroll = false;
        if (!this.txtFieldStudentID.getText().equalsIgnoreCase("")) {
            try {
                for (int i = 1; i <= enrollmentList.size(); i++) {
                    enroll = (Enrollment) enrollmentList.getNode(i).getData();
                    if (util.Utility.equals(this.txtFieldStudentID.getText(), enroll.getStudentID())) {
                        haveEnroll = true;
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLDeEnrollmentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!haveEnroll) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);//Horario agregado anteriormente
                alert.setTitle("Information");
                alert.setHeaderText("DeEnrollment procedure");
                alert.setContentText("No courses enrolled");
                alert.showAndWait();
                data = FXCollections.observableArrayList();
                this.table.setItems(data);
            } else {
                this.colCourse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                        return new ReadOnlyStringWrapper(data.getValue().get(0));
                    }
                });

                this.colSchedule.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                        return new ReadOnlyStringWrapper(data.getValue().get(1));
                    }
                });

                try {
                    this.table.setItems(getData());
                } catch (ListException ex) {
                    Logger.getLogger(FXMLDeEnrollmentController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    public ObservableList<List<String>> getData() throws ListException {

        data = FXCollections.observableArrayList();
        Enrollment enroll;
        Course course;
        String description;
        List<String> arrayList;

        for (int i = 1; i <= enrollmentList.size(); i++) {
            arrayList = new ArrayList<>();
            enroll = (Enrollment) enrollmentList.getNode(i).data;

            for (int j = 1; j <= coursesList.size(); j++) {
                course = (Course) coursesList.getNode(j).getData();
                if (util.Utility.equals(enroll.getCourseID(), course.getId()) && util.Utility.equals(this.txtFieldStudentID.getText(), enroll.getStudentID())) {
                    description = course.getName();
                    arrayList.add(description);
                    arrayList.add(enroll.getSchedule());
                    data.add(arrayList);
                }
            }
        }
        return data;
    }

    @FXML
    private void btnDeEnroll(ActionEvent event) {

        FileManage fileManage = new FileManage();

        Object selection = this.table.getSelectionModel().selectedItemProperty().get();

        ArrayList<String> array = new ArrayList();

        array = (ArrayList) selection;
        String courseDescription = array.get(0);

        Enrollment enroll;
        DeEnrollment deEnroll;
        Course course;
        boolean courseIDFound = false;

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Alert!!!");
        alert1.setContentText("This operation is not reversible. ¿Do you want to continue?");

        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert1.getDialogPane().getButtonTypes().clear();
        alert1.getDialogPane().getButtonTypes().add(buttonTypeNo);
        alert1.getDialogPane().getButtonTypes().add(buttonTypeYes);

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonTypeYes) {
                if (!"".equals(courseDescription) && !"".equals(this.txtFieldRemark.getText()) && !"".equals(this.txtFieldStudentID.getText())) {
                    try {
                        if (!coursesList.isEmpty()) {
                            for (int i = 1; i <= coursesList.size(); i++) {
                                course = (Course) coursesList.getNode(i).getData();

                                if (util.Utility.equals(course.getName(), courseDescription)) {
                                    courseID = course.getId();
                                    courseIDFound = true;
                                }
                            }
                        }
                        if (courseIDFound) {
                            if (!enrollmentList.isEmpty()) {
                                for (int i = 1; i <= enrollmentList.size(); i++) {
                                    enroll = (Enrollment) enrollmentList.getNode(i).getData();

                                    if (util.Utility.equals(enroll.getCourseID(), courseID)) {
                                        deEnroll = new DeEnrollment(enroll.getDate(), enroll.getStudentID(),
                                                enroll.getCourseID(), enroll.getSchedule(), this.txtFieldRemark.getText());

                                        deEnrollmentList.add(deEnroll);
                                        fileManage.addDeEnrollment(deEnroll);

                                        this.enrollmentList.remove(enroll);
                                        //modificar la lista de matricula
                                        fileManage.modifyEnrollment(enrollmentList);
                                        //actualizar la tabla 

                                        //enviar mensaje de eliminacion
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);//Horario agregado anteriormente
                                        alert.setTitle("Information");
                                        alert.setHeaderText("DeEnrollment procedure");
                                        alert.setContentText("Course successfully DeEnrolled");
                                        alert.showAndWait();
                                    }
                                }
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);//Horario agregado anteriormente
                                alert.setTitle("Information");
                                alert.setHeaderText("DeEnrollment procedure");
                                alert.setContentText("Enrolled a course");
                                alert.showAndWait();
                            }

                        }
                    } catch (ListException | IOException ex) {
                        Logger.getLogger(FXMLDeEnrollmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);//Horario agregado anteriormente
                    alert.setTitle("Information");
                    alert.setHeaderText("DeEnrollment procedure");
                    alert.setContentText("Please, fill the spaces");
                    alert.showAndWait();
                }

            }
        }

    }

    @FXML
    private void btnFinishDeEnroll(ActionEvent event) {
        this.ap.setVisible(false);
    }

}
