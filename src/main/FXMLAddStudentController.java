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
//import domain.Mail;
import domain.Node;
import domain.PdfFile;
import domain.SinglyLinkedList;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLAddStudentController implements Initializable {

    FileManage fileManage = new FileManage();
    SinglyLinkedList list;
    //Mail mail = new Mail();
    ObservableList listObservableList = FXCollections.observableArrayList();
    private DoublyLinkedList listed = util.Utility.getDoublyList();

    @FXML
    private TextField studentAddress;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentFirstName;
    @FXML
    private TextField studentIdPerson;
    @FXML
    private TextField studentMovil;
    @FXML
    private TextField studentEmail;
    @FXML
    private TextField studentIdCarne;
    @FXML
    private ComboBox<Integer> studentIdCarrer;
    @FXML
    private DatePicker studentAge;
    @FXML
    private Button addButton;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        list = util.Utility.getSinglyList();
        try {
            loadData();
        } catch (ListException ex) {
            Logger.getLogger(FXMLAddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addButton(ActionEvent event) throws InterruptedException, IOException, ParseException {
        Date date = util.Utility.convertToDateViaInstant(studentAge.getValue());
        try {
            Student student = new Student(Integer.parseInt(studentIdPerson.getText()), studentIdCarne.getText(),
                    studentFirstName.getText(), studentName.getText(), date,
                    studentMovil.getText(), studentEmail.getText(), studentAddress.getText(),
                    studentIdCarrer.getSelectionModel().getSelectedItem());

            list.add(student);
            fileManage.addStudent(student);
//            mail.run(studentEmail.getText(), "Información de Matricula", "Felicidades " + studentName.getText() + " " + studentFirstName.getText() + ".\nNúmero de cédula: " + studentIdPerson.getText() + "\nUsted ha sido admitido por la Universidad de Costa Rica\n"
//                    + "En la carrera con la identificación: " + studentIdCarrer.getValue() + "\nTú número de Carnet es: " + studentIdCarne.getText() + "\nAhora Formas Parte de esta Gran comunidad Universitaria\n");
            //outMessage("Se ha registrado un nuevo estudiante");
            //PdfFile.filePdf(student.getToString(), "Estudiantes");
        } catch (NumberFormatException err) {
            outMessage("Ha ingresado datos incorrectos (NumberFormatException)");
        }

    }

    public void outMessage(String content) {
        Alert out = new Alert(Alert.AlertType.INFORMATION);
        out.setTitle("Alerta");
        out.setContentText(content);
        out.show();
    }

    private void loadData() throws ListException {
        int counter = 1;
        listObservableList.removeAll(listObservableList);
        Node aux = listed.getNode(1);
        while (aux.getData() != listed.getLast()) {
            listObservableList.addAll(counter);//Cargar Enlaces de datos del módulo de carreras
            aux = aux.next;
            counter++;
        }
        listObservableList.addAll(counter);//Cargar Enlaces de datos del módulo de carreras
        studentIdCarrer.getItems().addAll(listObservableList);
    }

}
