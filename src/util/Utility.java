/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Profesor Lic. Gilberth Chaves Avila
 */
public class Utility {

    private static SinglyLinkedList singlyList = new SinglyLinkedList();
    private static SinglyLinkedList singlyListSchedule = new SinglyLinkedList();
    private static DoublyLinkedList doublyList = new DoublyLinkedList();
    private static CircularLinkedList circularList = new CircularLinkedList();
    private static CircularDoublyLinkedList circularDoublyList = new CircularDoublyLinkedList();
    private static CircularDoublyLinkedList enrollmentList = new CircularDoublyLinkedList();
    private static CircularDoublyLinkedList deEnrollmentList = new CircularDoublyLinkedList();
    private static String studentIDTable = new String();

    public static CircularDoublyLinkedList getDeEnrollmentList() {
        return deEnrollmentList;
    }

    public static void setDeEnrollmentList(CircularDoublyLinkedList list) {
        if (Utility.deEnrollmentList.isEmpty()) {
            Utility.deEnrollmentList = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.deEnrollmentList.contains(list.getNode(i).data)) {
                        Utility.deEnrollmentList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String getStudentIDTable() {
        return studentIDTable;
    }

    public static void setStudentIDTable(String studentIDTable) {
        Utility.studentIDTable = studentIDTable;
    }

    public static SinglyLinkedList getSinglyList() {
        return singlyList;
    }

    public static void setSinglyList(SinglyLinkedList list) {
        if (Utility.singlyList.isEmpty()) {
            Utility.singlyList = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.singlyList.contains(list.getNode(i).data)) {
                        Utility.singlyList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static DoublyLinkedList getDoublyList() {
        return doublyList;
    }

    public static void setDoublyList(DoublyLinkedList list) {
        if (Utility.doublyList.isEmpty()) {
            Utility.doublyList = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.doublyList.contains(list.getNode(i).data)) {
                        Utility.doublyList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static CircularLinkedList getCircularList() {
        return circularList;
    }

    public static void setCircularList(CircularLinkedList list) {
        if (Utility.circularList.isEmpty()) {
            Utility.circularList = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.circularList.contains(list.getNode(i).data)) {
                        Utility.circularList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static CircularDoublyLinkedList getCircularDoublyList() {
        return circularDoublyList;
    }

    public static void setCircularDoublyList(CircularDoublyLinkedList list) {
        if (Utility.circularDoublyList.isEmpty()) {
            Utility.circularDoublyList = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.circularDoublyList.contains(list.getNode(i).data)) {
                        Utility.circularDoublyList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static CircularDoublyLinkedList getEnrollmentList() {
        return enrollmentList;
    }

    public static void setEnrollmentList(CircularDoublyLinkedList list) {
        if (Utility.circularDoublyList.isEmpty()) {
            Utility.circularDoublyList = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.circularDoublyList.contains(list.getNode(i).data)) {
                        Utility.circularDoublyList.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

    public static int random() {
        return 1 + (int) Math.floor(Math.random() * 99);
    }

    public static int random(int bound) {
        //return 1+random.nextInt(bound);
        return 1 + (int) Math.floor(Math.random() * bound);
    }

    public static int random(int boundLow, int boundMax) {
        //return 1+random.nextInt(bound);
        return (int) ((Math.random() * (boundMax + 1 - boundLow)) + boundLow);
    }

    public static String format(double value) {
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }

    public static String $format(double value) {
        return new DecimalFormat("$###,###,###,###.##")
                .format(value);
    }

    public static String perFormat(double value) {
        //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }

    //Puede funcionar para estudiante y curso
    public static boolean equals(Object a, Object b) {

        switch (instanceOf(a, b)) {//Devuelve String
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x.equals(y);
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                // return s1.compareTo(s2)==0; Op1
                return s1.equalsIgnoreCase(s2); //Op2
            case "career":
                Career c1 = (Career) a;
                Career c2 = (Career) b;
                return c1.equals(c2);
            case "student":
                Student stu1 = (Student) a;
                Student stu2 = (Student) b;
                return stu1.getId() == (stu2.getId());
            case "course":
                Course cou1 = (Course) a;
                Course cou2 = (Course) b;
                return cou1.getId() == (cou2.getId()); 
        }
        return false;//En cualquier otro caso retorne false;
    }

    private static String instanceOf(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return "integer";
        }
        if (a instanceof String && b instanceof String) {
            return "string";
        }
        if (a instanceof Career && b instanceof Career) {
            return "career";
        }
        if (a instanceof Student && b instanceof Student) {
            return "student";
        }
        if (a instanceof Course && b instanceof Course) {
            return "course";
        }
        
//        if(a instanceof JobPosition && b instanceof JobPosition){return "JobPosition";}//Comparo 2 empleados
        return "unknown";
    }

    public static boolean lessT(Object a, Object b) {
        switch (instanceOf(a, b)) {//Devuelve String
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x < y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) < 0;//Primero mayor que segundo

        }
        return false;//En cualquier otro caso retorne false;
    }

    public static boolean greaterT(Object a, Object b) {
        switch (instanceOf(a, b)) {//Devuelve String
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x > y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) > 0;//Primero mayor que segundo
        }
        return false;//En cualquier otro caso retorne false;
    }

    public static Date dateFormat(String date) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.parse(date);

    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static int months(String month) {
        switch (month.toLowerCase()) {
            case "jan":
                return 1;
            case "feb":
                return 2;
            case "mar":
                return 3;
            case "abr":
                return 4;
            case "may":
                return 5;
            case "jun":
                return 6;
            case "jul":
                return 7;
            case "aug":
                return 8;
            case "sep":
                return 9;
            case "nov":
                return 11;
            case "dec":
                return 12;
            case "oct":
                return 10;
        }
        return -1;
    }

    public static SinglyLinkedList getSinglyListSchedule() {
        return singlyListSchedule;
    }

    public static void setSinglyListSchedule(SinglyLinkedList list) {
        if (Utility.singlyListSchedule.isEmpty()) {
            Utility.singlyListSchedule = list;
        } else {
            try {
                for (int i = 1; i < list.size(); i++) {
                    if (!Utility.singlyListSchedule.contains(list.getNode(i).data)) {
                        Utility.singlyListSchedule.contains(list.getNode(i).data);
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //Puede funcionar para estudiante y curso
    public static String dateFormat(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}
