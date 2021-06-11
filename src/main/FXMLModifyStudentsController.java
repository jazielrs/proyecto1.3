/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.FileManage;
import domain.ListException;
import domain.Node;
import domain.SinglyLinkedList;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLModifyStudentsController implements Initializable {

    int counter = 0;
    ObservableList listObservableList = FXCollections.observableArrayList();
    SinglyLinkedList list;
    private ChoiceBox<?> shooseChange;
    @FXML
    private Button modifyButton;
    @FXML
    private TextField studentId;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentFirstName;
    @FXML
    private TextField studentIdCarne;
    @FXML
    private TextField studentMovil;
    @FXML
    private TextField studentEmail;
    @FXML
    private TextField studentIdCarrer;
    @FXML
    private TextField studentAddress;
    @FXML
    private Button modifyButton1;
    @FXML
    private DatePicker studentAge;
    FileManage file;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = util.Utility.getSinglyList();
        file = new FileManage();
    }

    @FXML
    private void modifyButton(ActionEvent event) throws ListException {
        boolean out = true;
        if (list.isEmpty()) {
            Alert outAlert = new Alert(Alert.AlertType.INFORMATION);
            outAlert.setContentText("No hay estudiantes matriculados");
            outAlert.show();
        }
        if (studentId.getText().equals("")) {
            Alert outAlert = new Alert(Alert.AlertType.INFORMATION);
            outAlert.setContentText("No ha ingresado un Número de cédula");
            outAlert.show();
        } else {
            SinglyLinkedList listAux = list;
            Node aux = listAux.getNode(1);
            System.out.println(aux.data);
            while (out) {
                counter++;
                if (((Student) aux.getData()).getId() == Integer.parseInt(studentId.getText())) {
                    System.out.println("Encontrado");
                    System.out.println(((Student) aux.getData()).toString());
                    studentId.setDisable(false);
                    studentName.setDisable(false);
                    studentFirstName.setDisable(false);
                    studentIdCarne.setDisable(false);
                    studentMovil.setDisable(false);
                    studentEmail.setDisable(false);
                    studentIdCarrer.setDisable(true);
                    studentIdCarrer.setText(String.valueOf(((Student)aux.getData()).getCarrerID()));
                    studentAddress.setDisable(false);
                    modifyButton1.setVisible(true);
                    modifyButton1.setDisable(false);
                    out = false;
                }
                try {
                    aux = aux.next;
                } catch (NullPointerException err) {
                    out = false;
                    Alert outAlert = new Alert(Alert.AlertType.INFORMATION);
                    outAlert.setContentText("Error en el ingreso de datos");
                    outAlert.show();
                }
            }
        }
    }

    @FXML
    private void modifyButton1(ActionEvent event) throws ListException, IOException {
        Date birthday = Date.from(this.studentAge.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        list.getNode(counter).setData(new Student(Integer.parseInt(studentId.getText()), studentIdCarne.getText(),
                studentFirstName.getText(), studentName.getText(), birthday,
                studentMovil.getText(), studentEmail.getText(), studentAddress.getText(),
                Integer.parseInt(studentIdCarrer.getText())));
        studentId.setText("");
        studentName.setText("");
        studentFirstName.setText("");
        studentIdCarne.setText("");
        studentMovil.setText("");
        studentEmail.setText("");
        studentIdCarrer.setText("");
        studentAddress.setText("");
        modifyButton1.setText("");
        System.out.println(((Student) list.getNode(counter).getData()).toString());
        counter = 1;
        util.Utility.setSinglyList(list);
        file.modifyStudent(list);
    }

}
