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
import domain.ListException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLShowCoursesController implements Initializable {

    CircularDoublyLinkedList coursesList;
    DoublyLinkedList careerList;

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private TableView<List<String>> tableCourse;
    @FXML
    private TableColumn<List<String>, String> colID;
    @FXML
    private TableColumn<List<String>, String> colName;
    @FXML
    private TableColumn<List<String>, String> colCredits;
    @FXML
    private TableColumn<List<String>, String> colCareer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            coursesList = util.Utility.getCircularDoublyList();
            careerList = util.Utility.getDoublyList();

            this.colID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(0));
                }
            });
            this.colName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(1));
                }
            });
            this.colCredits.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(2));
                }
            });
            this.colCareer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyStringWrapper(data.getValue().get(3));
                }
            });

            this.tableCourse.setItems(getData());
        } catch (ListException ex) {
            Logger.getLogger(FXMLShowCoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<List<String>> getData() throws ListException {

        ObservableList<List<String>> data = FXCollections.observableArrayList();

        Course course = new Course();

        if(coursesList.isEmpty()){
            for (int i = 1; i <= coursesList.size(); i++) {
                List<String> arrayList = new ArrayList<>();
                course = (Course) coursesList.getNode(i).data;
                Career career = (Career) careerList.getNode(course.getCarrerID()).data;
                arrayList.add(course.getId());
                arrayList.add(course.getName());
                arrayList.add(String.valueOf(course.getCredits()));
                arrayList.add(String.valueOf(career.getDescription()));
                data.add(arrayList);
            }
        }
        return data;
    }

}
