/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLEraseCareerController implements Initializable {

    DoublyLinkedList careerList;
    FileManage fileManage = new FileManage();

    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button btnDelete;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Text txtMessage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.careerList = util.Utility.getDoublyList();
        ObservableList<String> list = FXCollections.observableArrayList(convertListToArray());
        combo.setItems(list);
    }

    @FXML
    private void comboSelection(ActionEvent event) {
        this.btnDelete.setVisible(true);
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        try {
            String output = combo.getSelectionModel().getSelectedItem();
            Career auxCareer = new Career(output);

            for (int i = 1; i <= careerList.size(); i++) {
                Career career = (Career) careerList.getNode(i).data;

                if (career.getDescription().equalsIgnoreCase(output)) {
                    this.careerList.remove(career);
                }
            }
            fileManage.modifyCareer(careerList);
            
            ObservableList<String> list = FXCollections.observableArrayList(convertListToArray());
            combo.setItems(list);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("EraseCareer");
            alert.setHeaderText("Success");
            alert.setContentText("The career has been successfully deleted");
            alert.showAndWait();

        } catch (ListException | IOException ex) {
            Logger.getLogger(FXMLEraseCareerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] convertListToArray() {

        if (careerList.isEmpty()) {
            this.txtMessage.setVisible(false);
            this.combo.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("EraseCareer");
            alert.setHeaderText("Alert");
            alert.setContentText("You need to add careers");

            alert.showAndWait();
        } else {

            try {
                String[] array = new String[careerList.size()];
                Career c;
                for (int i = 1; i <= careerList.size(); i++) {
                    //array[i - 1] = String.valueOf(careerList.getNode(i).data);
                    c =  (Career)careerList.getNode(i).data;
                    array[i - 1] = c.getDescription();
                }
                return array;
            } catch (ListException ex) {
                Logger.getLogger(FXMLEraseCareerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}
