/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author Cristopher.za
 */
public class Student {
    private int id; 
    private int carrerID;
    private String  phoneNumber, email, address, studentID, lastName, firstName;
    private Date birthday;

public Student(int id,String studentID, String lastName, String firstName, 
            Date birthday, String phoneNumber, String email, String address, int careerID) {        
        this.id = id;
        this.carrerID = careerID;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.studentID = studentID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarrerID() {
        return carrerID;
    }

    public void setCarrerID(int carrerID) {
        this.carrerID = carrerID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", studentID=" + studentID + ", lastName=" + lastName + ", firstName=" + firstName + ", birthday=" + birthday + '}';
    }
    
    public String getToString(){
        return  id+","+ studentID+","+  lastName+","+  firstName+","+ 
             birthday+","+ phoneNumber+","+  email+","+  address+","+carrerID;
    }
}