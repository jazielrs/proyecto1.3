/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.FileManage;
import domain.ListException;
import domain.SinglyLinkedList;
import domain.TimeTable;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLCreateScheduleController implements Initializable {

    CircularDoublyLinkedList coursesList;
    SinglyLinkedList scheduleList;
    String idCourse;
    String courseDes;

    int startNum;
    int endNum;
    int startNum1;
    int endNum1;

    String comboSelection;
    String schedulesInList;

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private ChoiceBox choiceBoxCourses;
    @FXML
    private Text txtMakeSchedule;
    @FXML
    private Text txtDay;
    @FXML
    private Text txtStartHour;
    @FXML
    private Text txtEndHour;
    @FXML
    private ComboBox comboDay;
    @FXML
    private ComboBox comboStartHour;
    @FXML
    private ComboBox comboEndHour;
    @FXML
    private Button btnCreateSchedule;
    @FXML
    private ComboBox comboDay1;
    @FXML
    private ComboBox comboStartHour1;
    @FXML
    private ComboBox comboEndHour1;
    @FXML
    private Text txtPeriod;
    @FXML
    private TextField txtFieldPeriod;
    @FXML
    private Text txtFirst;
    @FXML
    private Text txtSecond;
    @FXML
    private Button btnSelectCourse;
    @FXML
    private Text txtChooseCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            coursesList = util.Utility.getCircularDoublyList();
            scheduleList = util.Utility.getSinglyListSchedule();

            if (!coursesList.isEmpty()) {
                ObservableList<String> list = FXCollections.observableArrayList(convertIDCoursesToArray());
                choiceBoxCourses.setItems(list);
            } else {
                this.txtChooseCourse.setVisible(false);
                this.choiceBoxCourses.setVisible(false);
                this.btnSelectCourse.setVisible(false);
            }

            ObservableList<String> combDay = FXCollections.observableArrayList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");
            comboDay.setItems(combDay);

            ObservableList<String> combDay1 = FXCollections.observableArrayList("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");
            comboDay1.setItems(combDay1);

            ObservableList<String> combStart = FXCollections.observableArrayList("7:00",
                    "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
                    "16:00", "17:00", "18:00", "19:00", "20:00", "21:00");
            comboStartHour.setItems(combStart);

            ObservableList<String> combStart1 = FXCollections.observableArrayList("7:00",
                    "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
                    "16:00", "17:00", "18:00", "19:00", "20:00", "21:00");
            comboStartHour1.setItems(combStart1);

            ObservableList<String> combEnd = FXCollections.observableArrayList("7:00",
                    "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
                    "16:00", "17:00", "18:00", "19:00", "20:00", "21:00");
            comboEndHour.setItems(combEnd);

            ObservableList<String> combEnd1 = FXCollections.observableArrayList("7:00",
                    "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
                    "16:00", "17:00", "18:00", "19:00", "20:00", "21:00");
            comboEndHour1.setItems(combEnd1);

        } catch (ListException ex) {
            Logger.getLogger(FXMLCreateScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] convertIDCoursesToArray() throws ListException {

        if (!coursesList.isEmpty()) {
            String[] a = new String[coursesList.size()];

            Course course = new Course();
            for (int i = 1; i <= coursesList.size(); i++) {
                course = (Course) coursesList.getNode(i).data;
                a[i - 1] = course.getName();
            }

            return a;

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Select a course");
            alert.showAndWait();
        }

        return null;
    }

    @FXML
    private void btnSelectCourse(ActionEvent event) {

        String choice = (String) this.choiceBoxCourses.getValue();
        if (choice != null) {
            this.txtMakeSchedule.setVisible(true);
            this.txtPeriod.setVisible(true);
            this.txtFieldPeriod.setVisible(true);
            this.txtDay.setVisible(true);
            this.txtStartHour.setVisible(true);
            this.txtEndHour.setVisible(true);
            this.txtFirst.setVisible(true);
            this.txtSecond.setVisible(true);
            this.comboDay.setVisible(true);
            this.comboStartHour.setVisible(true);
            this.comboEndHour.setVisible(true);
            this.comboDay1.setVisible(true);
            this.comboStartHour1.setVisible(true);
            this.comboEndHour1.setVisible(true);
            this.btnCreateSchedule.setVisible(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Select a course");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCreateSchedule(ActionEvent event) throws ListException, IOException {

        FileManage fileManage = new FileManage();

        String schedule1 = this.comboDay.getValue() + "/" + this.comboStartHour.getValue() + "-" + this.comboEndHour.getValue();
        String schedule2 = this.comboDay1.getValue() + "/" + this.comboStartHour1.getValue() + "-" + this.comboEndHour1.getValue();

        TimeTable time;

        boolean exist = false;

        if (this.comboDay != null && this.comboDay1 != null && this.comboStartHour != null
                && this.comboStartHour1 != null && this.comboEndHour != null
                && this.comboEndHour1 != null && !"".equalsIgnoreCase(this.txtFieldPeriod.getText())
                && coursesHoursRange()) {

            if (!this.coursesList.isEmpty()) {
                Course course;
                for (int i = 1; i <= coursesList.size(); i++) {
                    course = (Course) coursesList.getNode(i).data;
                    if (course.getName().equalsIgnoreCase((String) this.choiceBoxCourses.getValue())) {
                        idCourse = course.getId();
                        courseDes = course.getName();
                    }
                }
            }
            TimeTable schedule = new TimeTable(idCourse,
                    this.txtFieldPeriod.getText(), schedule1, schedule2);

            if (scheduleList.isEmpty()) {//si la lista esta vacía
                if (this.comboDay.getValue() == this.comboDay1.getValue()) {
                    if (scheduleRange()) {
                        this.scheduleList.add(schedule);
                        fileManage.addSchedule(schedule);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText("Alert");
                        alert.setContentText("New schedule added");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText("Alert");
                        alert.setContentText("The schedules collide");
                        alert.showAndWait();
                    }
                } else {
                    this.scheduleList.add(schedule);
                    fileManage.addSchedule(schedule);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Alert");
                    alert.setContentText("New schedule added");
                    alert.showAndWait();
                }
            } else {//si la lista no esta vacía

                getComboSelection();

                for (int i = 1; i <= this.scheduleList.size(); i++) {
                    time = (TimeTable) scheduleList.getNode(i).data;
                    if (util.Utility.equals(idCourse, time.getCourseId())) {
                        schedulesInList = time.getSchedule() + "," + time.getSchedule2();
                        exist = true;
                    }
                }
                if (exist) {
                    if (compareSchedule(comboSelection, schedulesInList)) {

                        this.scheduleList.add(schedule);
                        fileManage.addSchedule(schedule);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText("Alert");
                        alert.setContentText("New schedule added");
                        alert.showAndWait();

                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Alert");
                        alert.setHeaderText("Alert");
                        alert.setContentText("The schedules collide");
                        alert.showAndWait();
                    }
                } else {
                    this.scheduleList.add(schedule);
                    fileManage.addSchedule(schedule);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText("Alert");
                    alert.setContentText("New schedule added");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Please, create a correct schedule");
            alert.showAndWait();
        }
    }

    //se obtiene la hora de los combobox
    public int getNumber(ComboBox combo) {
        int hour = 0;
        try {
            String choice = (String) combo.getValue();
            String num[] = choice.split(":");
            hour = Integer.parseInt(num[0]);

        } catch (NullPointerException ex) {
        }
        return hour;
    }

    //Cada horario tenga una máxima duración de 4 horas
    public boolean coursesHoursRange() {
        int startHour = getNumber(this.comboStartHour);
        int startHour1 = getNumber(this.comboStartHour1);
        int endHour = getNumber(this.comboEndHour);
        int endHour1 = getNumber(this.comboEndHour1);

        return ((endHour > startHour && endHour <= (startHour + 4)) && (endHour1 > startHour1 && endHour1 <= (startHour1 + 4)));
    }

    //permite no crear horarios dentro del rango de otro horario
    public boolean scheduleRange() {

        int startHour = getNumber(this.comboStartHour);
        int startHour1 = getNumber(this.comboStartHour1);
        int endHour = getNumber(this.comboEndHour);
        int endHour1 = getNumber(this.comboEndHour1);

        boolean possible = false;

        if (startHour < startHour1 && endHour < startHour1) {
            possible = true;
        }

        if (startHour > startHour1 && startHour > endHour1) {
            possible = true;
        }
        return possible;
    }

    public void getComboSelection() {
        String day1 = (String) this.comboDay.getValue();
        String day2 = (String) this.comboDay1.getValue();
        String startHour = (String) this.comboStartHour.getValue();
        String startHour1 = (String) this.comboStartHour1.getValue();
        String endHour = (String) this.comboEndHour.getValue();
        String endHour1 = (String) this.comboEndHour1.getValue();

        comboSelection = day1 + "/" + startHour + "-" + endHour + "," + day2 + "/" + startHour1 + "-" + endHour1;
    }

    public boolean compareSchedule(String comboSelection, String schedule) {

        boolean isSafe = false;

        //variables del horario en la lista
        String[] arr1AddedSchedule1, arr1AddedSchedule2, arr1AddedSchedule3, arr1AddedSchedule4;
        String schedule1Day1, schedule1Day2;
        int startHourSchedule1, endHourSchedule1, startHourSchedule2, endHourSchedule2;

        //variables del horario en el combobox
        String schedule2Day1, schedule2Day2;
        int startHourSchedule3, endHourSchedule3, startHourSchedule4, endHourSchedule4;

        //Obtener valores del horario en la lista
        arr1AddedSchedule1 = schedule.split(",");//Lunes/9:00-10:00 * Martes/11:00-15:00 
        arr1AddedSchedule2 = arr1AddedSchedule1[0].split("/"); //Lunes*9:00-10:00 
        arr1AddedSchedule3 = arr1AddedSchedule1[1].split("/"); //Martes*11:00-15:00 
        schedule1Day1 = arr1AddedSchedule2[0];//Lunes
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
        arr1AddedSchedule1 = comboSelection.split(",");//Miercoles/8:00-11:00 * Jueves/10:00-12:00 
        arr1AddedSchedule2 = arr1AddedSchedule1[0].split("/"); //Miercoles*8:00-11:00 
        arr1AddedSchedule3 = arr1AddedSchedule1[1].split("/"); //Jueves*10:00-12:00 
        schedule2Day1 = arr1AddedSchedule2[0];//Miercoles
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
        } else {
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

    public boolean rangeSchedules(int startHour1, int endHour1, int startHour2, int endHour2) {//4 horas a comprobar

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
