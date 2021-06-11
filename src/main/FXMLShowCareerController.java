/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.sun.javafx.collections.ElementObservableListDecorator;
import com.sun.javafx.logging.PlatformLogger.Level;
import domain.Career;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Node;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jaziel
 */
public class FXMLShowCareerController implements Initializable {
    
    DoublyLinkedList careerList;
    
 //    private TableView<List<String>> careerTable;
//    private TableColumn<List<String>, String> columID;
//    private TableColumn<List<String>, String> columDescription;
    @FXML
    private TableView<List<String>> careerTable;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private TableColumn<List<String>, String> ID;
    @FXML
    private TableColumn<List<String>, String> Description;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            careerList = util.Utility.getDoublyList();
            
            this.ID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyObjectWrapper(data.getValue().get(0));
                }
            });
            
            this.Description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                    return new ReadOnlyObjectWrapper(data.getValue().get(1));
                }
            });
            
            this.careerTable.setItems(getData());
        } catch (ListException ex) {
            Logger.getLogger(FXMLShowCareerController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public ObservableList<List<String>> getData() throws ListException{
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        if (!careerList.isEmpty()) {
            for (int i = 1; i <= this.careerList.size(); i++) {
                Career c = (Career) careerList.getNode(i).getData();
                List<String> list = new ArrayList<>();
                list.add(String.valueOf(i));// Valor 0 para el getData
                list.add(c.getDescription());// Valor 1 para el getData
                data.add(list);

            }
        }
        return data;
    }
    

       
} 
  

    

