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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLEraseStudentsController implements Initializable {

    Student student;
    int counter = 0;
    SinglyLinkedList list;
    @FXML
    private TextField idStudent;
    @FXML
    private TextArea txtOut;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button search;
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
    private void deleteBtn(ActionEvent event) throws ListException, IOException {
        if (list.isEmpty()) {
            Alert outAlert = new Alert(Alert.AlertType.INFORMATION);
            outAlert.setContentText("No hay estudiantes matriculados");
            outAlert.show();
        } else {
            ButtonType type = new ButtonType("Aceptar");
            Alert out = new Alert(Alert.AlertType.CONFIRMATION, "Se eliminará un Estudiante de la lista de matriculados", type);
            out.showAndWait();
            list.remove(student);
            txtOut.setText("");
            idStudent.setText("");
            util.Utility.setSinglyList(list);
            file.modifyStudent(list);
        }
    }

    @FXML
    private void search(ActionEvent event) throws ListException, IOException {
        boolean out = true;
        if (list.isEmpty()) {
            Alert outAlert = new Alert(Alert.AlertType.INFORMATION);
            outAlert.setContentText("No hay estudiantes matriculados");
            outAlert.show();
        } else {
            if (idStudent.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No ha ingresado un Número de cédula");
            } else {
                SinglyLinkedList listAux = list;
                Node aux = listAux.getNode(1);
                System.out.println(aux.data);
                while (out) {
                    counter++;
                    if (((Student) aux.getData()).getId() == Integer.parseInt(idStudent.getText())) {
                        student = (Student) aux.getData();
                        String name, firstNaString, id, idCarrer, idStudent;
                        name = student.getLastName();
                        firstNaString = student.getLastName();
                        id = String.valueOf(student.getId());
                        idCarrer = String.valueOf(student.getCarrerID());
                        idStudent = student.getStudentID();
                        txtOut.setText("DATOS PERSONALES DEL ESTUDIANTE A ELIMINAR\nNombre: " + name
                                + "\n Apellidos: " + firstNaString
                                + "\n Número de identificación: " + id + "\nIdentificación de Carrera: " + idCarrer + ""
                                + "\n Número de Carnet: " + idStudent);
                        out = false;
                    }
                    try {
                        aux = aux.next; 
                    } catch (NullPointerException err) {
                        out = false;
                        JOptionPane.showMessageDialog(null, "No se ha encontrado al estudiante dentro de la lista de matriculados");
                    }
                }
            }
        }
    }

}
