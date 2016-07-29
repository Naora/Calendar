/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.recipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Nao
 */
public class Recipe implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recipe other = (Recipe) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
