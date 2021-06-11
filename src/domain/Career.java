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
public class Career {

    private static int id;
    private String description;
    public Career() {
    }

    public Career(String description) {
        this.id = ++id;
        this.description = description;
    }
    
    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
//        return description;
        return "Career{id=" + id + " description=" + description + '}';
    }

}
