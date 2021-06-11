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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLModifyCareerController implements Initializable {

    DoublyLinkedList careerList;
    FileManage fileManage = new FileManage();

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Text txtMessage;
    @FXML
    private TextField txtFieldModifyCareer;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnClean;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.careerList = util.Utility.getDoublyList();//Carga la lista en la pagina
            ObservableList<String> list = FXCollections.observableArrayList(convertListToArray());
            combo.setItems(list);       
        } catch (ListException ex) {
            Logger.getLogger(FXMLModifyCareerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] convertListToArray() throws ListException {
        String[] array = new String[careerList.size()];
        Career c;
        for (int i = 1; i <= careerList.size(); i++) {
             c =  (Career)careerList.getNode(i).data;
             array[i - 1] = c.getDescription();
        }
        return array;
    }

    @FXML
    private void comboSelection(ActionEvent event) {
        this.txtMessage.setText("Write the new description");
        this.txtFieldModifyCareer.setVisible(true);
        this.btnModify.setVisible(true);
        this.btnClean.setVisible(true);
    }

    @FXML
    private void btnModify(ActionEvent event) throws ListException, IOException {
        String output = combo.getSelectionModel().getSelectedItem();
        Career career = new Career();
        for (int i = 1; i <= careerList.size(); i++) {
            career = (Career) careerList.getNode(i).data;
            if (career.getDescription().equalsIgnoreCase(output)) {
                career.setDescription(this.txtFieldModifyCareer.getText());
            }
        }
        fileManage.modifyCareer(careerList);
        ObservableList<String> list = FXCollections.observableArrayList(convertListToArray());
        combo.setItems(list);//Actualiza el combo  
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ModifyCareer");
        alert.setHeaderText("Success");
        alert.setContentText("The career has been successfully modified");
        alert.showAndWait();
    }

    @FXML
    private void btnClean(ActionEvent event) {
        this.txtFieldModifyCareer.setText("");
    }

}
