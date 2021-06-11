/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Cristopher.za
 */
public class Course {
    
    private String id, name;
    private int credits, carrerID;

    public Course(String id, String name, int credits, int carrerID) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.carrerID = carrerID;
    }

    public Course() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getCarrerID() {
        return carrerID;
    }

    public void setCarrerID(int carrerID) {
        this.carrerID = carrerID;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", credits=" + credits + ", carrerID=" + carrerID + '}';
    }  
}
