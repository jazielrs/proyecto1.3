/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.CircularLinkedList;
import domain.FileManage;
import domain.ListException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLLoginController implements Initializable {

    //CircularLinkedList managers = new CircularLinkedList();
    @FXML
    private AnchorPane ap;
    @FXML
    private BorderPane bp;
    @FXML
    private Button btnLogin;
    @FXML
    private Text profileTxt;
    @FXML
    private CheckBox checkButtonStudent;
    @FXML
    private Text txtUsername;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private Text txtPassword;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private Text txtStudentID;
    @FXML
    private TextField txtFIeldStudentID;
    @FXML
    private CheckBox checkButtonManager;
    @FXML
    private Text txtStudentPassword;
    @FXML
    private TextField txtFieldStudentPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void loadPage(String page) throws IOException {
        Parent root = null;//Crea un nodo en la que almacena la pagina    
        root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        this.bp.setCenter(root);//Asigna la pagina al componente que la maneja
    }

    @FXML
    private void loginBtn(ActionEvent event) throws IOException, ListException {
        this.ap.setVisible(false);
        if (this.checkButtonManager.isSelected()) {//Si se selecciono el perfil de estudiante
            FileManage manage = new FileManage();
            if (!manage.existFile("managersFile.txt")) {
                try {
                    //Si no hay managers guardados

                    String password = this.txtFieldPassword.getText();
                    String keyWord = "keyWord";
                    
                    String encryptedKeyWord = manage.encrypt(keyWord, "DR54");

                    String encryptedPassword = manage.encrypt(password, keyWord);

                    manage.addManager(this.txtFieldUsername.getText(), encryptedPassword, encryptedKeyWord);
                    
                    loadPage("FXMLMENU");
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
                    Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {//Si ya existen managers
                if (verifyManagers()) {
                    loadPage("FXMLMENU");
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Login");//Alert title
                    alert.setHeaderText("Error");//Alert header
                    alert.setContentText("Username or password is incorrect");
                    alert.showAndWait();
                    this.txtFieldUsername.setText("");
                    this.txtFieldPassword.setText("");
                    this.ap.setVisible(true);
                }
            }
        } else if (this.checkButtonStudent.isSelected()) {//Si se selecciono el perfil de estudiante

        }
    }

    @FXML
    private void managerSelection(ActionEvent event) {
        if (this.checkButtonManager.isSelected() && !this.checkButtonStudent.isSelected()) {//Seleccionado correcto
            txtUsername.setVisible(true);
            txtFieldUsername.setVisible(true);
            txtPassword.setVisible(true);
            txtFieldPassword.setVisible(true);
            btnLogin.setVisible(true);
        }
        if (!this.checkButtonManager.isSelected() && !this.checkButtonStudent.isSelected()) {//Ninguno seleccionado
            txtUsername.setVisible(false);
            txtFieldUsername.setVisible(false);
            txtPassword.setVisible(false);
            txtFieldPassword.setVisible(false);
            txtStudentID.setVisible(false);
            txtFIeldStudentID.setVisible(false);
            txtStudentPassword.setVisible(false);
            txtFieldStudentPassword.setVisible(false);
            btnLogin.setVisible(false);

        }
        if (this.checkButtonManager.isSelected() && this.checkButtonStudent.isSelected()) {//2 seleccionados
            txtUsername.setVisible(false);
            txtFieldUsername.setVisible(false);
            txtPassword.setVisible(false);
            txtFieldPassword.setVisible(false);
            btnLogin.setVisible(false);
            txtStudentID.setVisible(false);
            txtFIeldStudentID.setVisible(false);
            txtStudentPassword.setVisible(false);
            txtFieldStudentPassword.setVisible(false);
            btnLogin.setVisible(false);
        }
        if (!this.checkButtonManager.isSelected() && this.checkButtonStudent.isSelected()) {//Seleccionado correcto
            txtStudentID.setVisible(true);
            txtFIeldStudentID.setVisible(true);
            this.txtStudentPassword.setVisible(true);
            this.txtFieldStudentPassword.setVisible(true);
            btnLogin.setVisible(true);
        }

    }

    @FXML
    private void studentSelection(ActionEvent event) {
        if (this.checkButtonStudent.isSelected() && !this.checkButtonManager.isSelected()) {
            txtStudentID.setVisible(true);
            txtFIeldStudentID.setVisible(true);
            this.txtStudentPassword.setVisible(true);
            this.txtFieldStudentPassword.setVisible(true);
            btnLogin.setVisible(true);
        }
        if (!this.checkButtonManager.isSelected() && !this.checkButtonStudent.isSelected()) {
            txtUsername.setVisible(false);
            txtFieldUsername.setVisible(false);
            txtPassword.setVisible(false);
            txtFieldPassword.setVisible(false);
            btnLogin.setVisible(false);
            txtStudentID.setVisible(false);
            txtFIeldStudentID.setVisible(false);
            txtStudentPassword.setVisible(false);
            txtFieldStudentPassword.setVisible(false);
            btnLogin.setVisible(false);
        }
        if (this.checkButtonManager.isSelected() && this.checkButtonStudent.isSelected()) {
            txtUsername.setVisible(false);
            txtFieldUsername.setVisible(false);
            txtPassword.setVisible(false);
            txtFieldPassword.setVisible(false);
            txtStudentID.setVisible(false);
            txtFIeldStudentID.setVisible(false);
            txtStudentPassword.setVisible(false);
            txtFieldStudentPassword.setVisible(false);
            btnLogin.setVisible(false);
        }
        if (this.checkButtonManager.isSelected() && !this.checkButtonStudent.isSelected()) {//Seleccionado correcto
            txtUsername.setVisible(true);
            txtFieldUsername.setVisible(true);
            txtPassword.setVisible(true);
            txtFieldPassword.setVisible(true);
            btnLogin.setVisible(true);
        }

    }

    public boolean verifyManagers() {
        
        FileManage manage = new FileManage();
        boolean foundManager = false;
        if (manage.existFile("managersFile.txt")) {
            try {
                //Si hay managers
                if (manage.readManagers("managersFile.txt", this.txtFieldUsername.getText(), this.txtFieldPassword.getText())) {//Busca manager registrado
                    foundManager = true;
                }
            } catch (IOException ex) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return foundManager;
    }

}
