/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.Enrollment;
import domain.FileManage;
import domain.ListException;
import domain.Node;
import domain.SinglyLinkedList;
import domain.Student;
import domain.TimeTable;
//import framework.annotation.Time;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
public class FXMLAvailableCoursesController implements Initializable {

    CircularDoublyLinkedList coursesList;
    CircularDoublyLinkedList enrollmentList;
    SinglyLinkedList list;
    SinglyLinkedList sheduleList;
    SinglyLinkedList studentSchedules;
    String studentIDTable;
    String seletedCourse;
    String courseID;
    SinglyLinkedList idsAdded;
    

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private TableView<String> tableCourses;
    @FXML
    //private TableColumn<List<String>, String> colCourses;
    private TableColumn colCourses;
    @FXML
    private Button btnAddSchedule;

    //private List<String> arrayList = new ArrayList<>();
    private String[] listNames;
    @FXML
    private ComboBox comboSchedule;
    @FXML
    private Button btnChooseCourse;
    @FXML
    private Button btnFinish;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // TODO
            coursesList = util.Utility.getCircularDoublyList();
            list = util.Utility.getSinglyList();
            sheduleList = util.Utility.getSinglyListSchedule();
            enrollmentList = util.Utility.getEnrollmentList();
            studentIDTable = util.Utility.getStudentIDTable();
            studentSchedules = new SinglyLinkedList();
            idsAdded = new SinglyLinkedList();

            this.colCourses.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> p) {
                    return new SimpleObjectProperty<>((p.getValue()));
                }
            });

            this.tableCourses.setItems(getData());

        } catch (ListException ex) {
            Logger.getLogger(FXMLAvailableCoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] fillCombo() throws ListException {
        Course course;
        String courseID;
        TimeTable time;
        String a[] = new String[20];
        String schedule1 = "";
        String schedule2 = "";

        int counterPos = 0;
        for (int i = 1; i <= coursesList.size(); i++) {
            course = (Course) coursesList.getNode(i).data;

            courseID = course.getId();

            if (seletedCourse.equalsIgnoreCase(course.getName())) {

                for (int j = 1; j <= this.sheduleList.size(); j++) {

                    time = (TimeTable) sheduleList.getNode(j).data;
                    schedule1 = time.getSchedule();
                    schedule2 = time.getSchedule2();

                    if (courseID.equalsIgnoreCase(time.getCourseId())) {
                        a[counterPos] = schedule1 + "," + schedule2 + ","+time.getCourseId();
                        counterPos++;
                    }
                }
            }
        }
        //Descarta posiciones null;
        int counterValues = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                //arrayC[i] = a[i];
                counterValues++;
            }
        }
        String[] arrayC = new String[counterValues];
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                arrayC[i] = a[i];
            }
        }
        return arrayC;
    }

    @FXML
    private void btnChooseCourse(ActionEvent event) {
        try {
            seletedCourse = tableCourses.getSelectionModel().selectedItemProperty().get();
            ObservableList<String> list = FXCollections.observableArrayList(fillCombo());
            comboSchedule.setItems(list);
        } catch (ListException ex) {
            Logger.getLogger(FXMLAvailableCoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAddSchedule(ActionEvent event) throws ListException { 
        Alert alert1 = new Alert(AlertType.CONFIRMATION);
        alert1.setTitle("Alert!!!");
        alert1.setContentText("This operation is not reversible. ¿Do you want to continue?");

        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.NO);
        alert1.getDialogPane().getButtonTypes().clear();
        alert1.getDialogPane().getButtonTypes().add(buttonTypeNo);
        alert1.getDialogPane().getButtonTypes().add(buttonTypeYes);

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.isPresent()) {
            if (result.get() == buttonTypeYes) {
                String scheduleSelection = (String) comboSchedule.getValue();
                String scheduleAdded;
                boolean safeSchedule, isAdded = false;
                String[] dataScheduleList;
                String[] dataScheduleCombo;
                if (studentSchedules.isEmpty()) {
                    studentSchedules.add(scheduleSelection);
                    dataScheduleCombo = scheduleSelection.split(",");
                    idsAdded.add(dataScheduleCombo[2]);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Safe selection");
                    alert.setContentText("Course added");
                    alert.showAndWait();
                } else {
                    for (int i = 1; (i <= studentSchedules.size() && isAdded == false); i++) {
                        scheduleAdded = (String) studentSchedules.getNode(i).getData();
                        dataScheduleCombo = scheduleSelection.split(",");
                        for (int j = 1; j <= idsAdded.size(); j++) {
                            String id = (String) idsAdded.getNode(j).getData();
                            if (util.Utility.equals(dataScheduleCombo[2], id)) {//Si encuentra un id agregado anteriormente
                                isAdded = true;
                            }
                        }
                        if (!isAdded) {//Si no hay un horario previamente agregado a ese curso
                            safeSchedule = compareSchedule(scheduleAdded, scheduleSelection);
                            if (safeSchedule) {//Si el calendario es seguro de agregar //Agrega el horario
                                studentSchedules.add(scheduleSelection);//agrego a la lista
                                idsAdded.add(dataScheduleCombo[2]);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Alert");
                                alert.setHeaderText("Safe selection");
                                alert.setContentText("Course added");
                                alert.showAndWait();
                                isAdded = true;
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);//Horario colisiona
                                alert.setTitle("Alert");
                                alert.setHeaderText("Unsafe selection");
                                alert.setContentText("Course schedule collide");
                                alert.showAndWait();
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);////Horario agregado anteriormente
                            alert.setTitle("Alert");
                            alert.setHeaderText("Unsafe selection");
                            alert.setContentText("Course already added");
                            alert.showAndWait();
                        }
                    }
                }
            }
        }
    }
    
    @FXML
    private void btnFinish(ActionEvent event) {
        FileManage fileManage = new FileManage();
        Date date = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");//("HH:mm:ss dd/MM/yyyy")
        String formattedDate = simpleDate.format(date);//simpleDate.format(fecha);
        System.out.println(formattedDate);
        try {
            date = simpleDate.parse(formattedDate);
        } catch (ParseException ex) {
            Logger.getLogger(FXMLAvailableCoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String schedule, courseID, finalSchedule;
        String scheduleData[];
        try {
            for(int i = 1; i <= studentSchedules.size();i++){     
                schedule = (String) studentSchedules.getNode(i).getData();
                scheduleData = schedule.split(",");
                finalSchedule=scheduleData[0]+","+scheduleData[1];
                Enrollment enroll = new Enrollment(date,studentIDTable,scheduleData[2],finalSchedule);
                enrollmentList.add(enroll);
                try {
                    fileManage.addEnrollment(enroll);
                } catch (IOException ex) {
                    Logger.getLogger(FXMLAvailableCoursesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            //Crear txt
           
            this.ap.setVisible(false);
            //Debe actualizar txt
            Alert alert = new Alert(Alert.AlertType.INFORMATION);//Horario agregado anteriormente
            alert.setTitle("Information");
            alert.setHeaderText("Enrollment procedure");
            alert.setContentText("Enrollment succeded");
            alert.showAndWait();
        } catch (ListException ex) {
            Logger.getLogger(FXMLAvailableCoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadPage(String page) {//Realiza una carga de una pagina generica
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMENUController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bp.setCenter(root);//Realiza la asignacion de la nueva pagina al menu principal
    }

    public ObservableList<String> getData() throws ListException {
        ObservableList<String> data = FXCollections.observableArrayList();
        Student student = null;
        for (int i = 1; i <= list.size(); i++) {//en la lista de estudiantes extraido el estudiante encontrado
            student = (Student) list.getNode(i).data;
            if (String.valueOf(student.getStudentID()).equalsIgnoreCase(studentIDTable)) {
                returnCourse(student);//llena con los datos del curso
            }
        }
        for (int i = 0; i < listNames.length; i++) {//Controla cuantos elementos meto para sre leidos
            if (listNames[i] != null) {
                data.add(listNames[i]);//Pasa los valores del array a la lista observable            
            }
        }
        return data;
    }

    public void returnCourse(Student stu) throws ListException {
        Course course;
        listNames = new String[coursesList.size() + 1];
        int counter = 0;
        if (stu.getStudentID().equalsIgnoreCase(studentIDTable)) {
            for (int i = 1; i <= coursesList.size(); i++) {
                course = (Course) coursesList.getNode(i).getData();
                if (course.getCarrerID() == (stu.getCarrerID())) {
                    listNames[counter] = course.getName();
                    courseID = course.getId();
                    counter++;
                }
            }
        }
    }
    
    public boolean compareSchedule(String addedSchedule, String newSchedule){
        
        boolean isSafe = false;
       
        //variables del horario en la lista
        String[] arr1AddedSchedule1, arr1AddedSchedule2, arr1AddedSchedule3, arr1AddedSchedule4;
        String schedule1Day1, schedule1Day2;
        int startHourSchedule1, endHourSchedule1, startHourSchedule2, endHourSchedule2;
        
        //variables del horario en el combobox
        String schedule2Day1, schedule2Day2;
        int startHourSchedule3, endHourSchedule3, startHourSchedule4, endHourSchedule4;
        
        
        //Obtener valores del horario en la lista
        arr1AddedSchedule1 = addedSchedule.split(",");//Lunes/9:00-10:00 * Martes/11:00-15:00 
        arr1AddedSchedule2 = arr1AddedSchedule1[0].split("/"); //Lunes*9:00-10:00 
        arr1AddedSchedule3 = arr1AddedSchedule1[1].split("/"); //Martes*11:00-15:00 
        schedule1Day1 =  arr1AddedSchedule2[0];//Lunes
        schedule1Day2 = arr1AddedSchedule3[0];//Martes
        arr1AddedSchedule2 = arr1AddedSchedule2[1].split("-");//9:00*10:00
        arr1AddedSchedule3 = arr1AddedSchedule3[1].split("-");//11:00*15:00
        arr1AddedSchedule1 = arr1AddedSchedule2[0].split(":");//9*00
        startHourSchedule1 = Integer.parseInt(arr1AddedSchedule1[0]);//9
        arr1AddedSchedule1 = arr1AddedSchedule2[1].split(":");//10*00
        endHourSchedule1 = Integer.parseInt(arr1AddedSchedule1[0]);//10
        arr1AddedSchedule1 = arr1AddedSchedule3[0].split(":");//11*00
        startHourSchedule2 = Integer.parseInt(arr1AddedSchedule1[0]);//11
        arr1AddedSchedule1 = arr1AddedSchedule3[1].split(":");//15*00
        endHourSchedule2 = Integer.parseInt(arr1AddedSchedule1[0]);//15
        
         //Obtener valores del horario en el combobox
        arr1AddedSchedule1 = newSchedule.split(",");//Miercoles/8:00-11:00 * Jueves/10:00-12:00 
        arr1AddedSchedule2 = arr1AddedSchedule1[0].split("/"); //Miercoles*8:00-11:00 
        arr1AddedSchedule3 = arr1AddedSchedule1[1].split("/"); //Jueves*10:00-12:00 
        schedule2Day1 =  arr1AddedSchedule2[0];//Miercoles
        schedule2Day2 = arr1AddedSchedule3[0];//Jueves
        arr1AddedSchedule2 = arr1AddedSchedule2[1].split("-");//8:00*11:00
        arr1AddedSchedule3 = arr1AddedSchedule3[1].split("-");//10:00*12:00
        arr1AddedSchedule1 = arr1AddedSchedule2[0].split(":");//8*00
        startHourSchedule3 = Integer.parseInt(arr1AddedSchedule1[0]);//8
        arr1AddedSchedule1 = arr1AddedSchedule2[1].split(":");//11*00
        endHourSchedule3 = Integer.parseInt(arr1AddedSchedule1[0]);//11
        arr1AddedSchedule1 = arr1AddedSchedule3[0].split(":");//10*00
        startHourSchedule4 = Integer.parseInt(arr1AddedSchedule1[0]);//10
        arr1AddedSchedule1 = arr1AddedSchedule3[1].split(":");//12*00
        endHourSchedule4 = Integer.parseInt(arr1AddedSchedule1[0]);//12
         
        //Compara rangos
        //Primero verifico los dias
        //Si no hay choques de dias
        //Casos días L  K
                   //M  V
         if (!(util.Utility.equals(schedule1Day1, schedule2Day1)) && !(util.Utility.equals(schedule1Day1, schedule2Day2)) //Dia 1
                && !(util.Utility.equals(schedule1Day2, schedule2Day1)) && !(util.Utility.equals(schedule1Day2, schedule2Day2)) //Dia 2
                && !(util.Utility.equals(schedule2Day1, schedule1Day1)) && !(util.Utility.equals(schedule2Day1, schedule1Day2)) //Dia 3
                && !(util.Utility.equals(schedule2Day2, schedule1Day1)) && !(util.Utility.equals(schedule2Day1, schedule1Day2))) {//Dia 4 
             return true;
         }else{
             if (util.Utility.equals(schedule1Day1, schedule2Day1)) {//Si hay conflicto en dia 1 y 3
                //Verifico rango
                if (rangeSchedules(startHourSchedule1, endHourSchedule1, startHourSchedule3, endHourSchedule3)) {
                    return true;
                }
            }
            if (util.Utility.equals(schedule1Day1, schedule2Day2)) {//Si hay conflicto en dia 1 y 4
                if (rangeSchedules(startHourSchedule1, endHourSchedule1, startHourSchedule4, endHourSchedule4)) {
                    return true;
                }
            }
            if (util.Utility.equals(schedule1Day2, schedule2Day1)) {//Si hay conflicto en dia 2 y 3
                if (rangeSchedules(startHourSchedule2, endHourSchedule2, startHourSchedule3, endHourSchedule3)) {
                    return true;
                }
            }
            if (util.Utility.equals(schedule1Day2, schedule2Day2)) {//Si hay conflicto en dia 2 y 4
                if (rangeSchedules(startHourSchedule2, endHourSchedule2, startHourSchedule4, endHourSchedule4)) {
                    return true;
                }
            }
        }
         
        return false;
    }

    public boolean rangeSchedules(int startHour1, int endHour1, int startHour2, int endHour2
    ){//4 horas a comprobar
          
         //Caso 1: hora final del dia 1 menor a la hora final del dia 2
        if (startHour1 == startHour2) {//9-12,9/10
            return false;//Choca horario
        }
        if (endHour1 == endHour2) {//9-1,10-1
            return false;
        }
        if (endHour1 <= endHour2 && endHour1>=startHour2) {//8-11,10-12
            return false;
        }
        if (endHour2 >= startHour1 && endHour2 <= endHour1) {//10-12,8-11
            return false;
        }
          
          return true;
    }

}
