/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.recipe;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Nao
 */
public class Recipe implements Serializable {
    
    private String description;
    private String name;
    private ArrayList<Ingrediant> ingrediants;
    private ArrayList<String> tags;
    
    public Recipe(){
        this("");
    }

    Recipe(String n) {
        name = n;
        description = "";
        ingrediants = new ArrayList<>();
        tags = new ArrayList<>();
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingrediant> getIngrediants() {
        return ingrediants;
    }

    public void setIngrediants(ArrayList<Ingrediant> ingrediants) {
        this.ingrediants = ingrediants;
    }
    
}
