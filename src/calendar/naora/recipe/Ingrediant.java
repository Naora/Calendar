/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.recipe;

import java.io.Serializable;

/**
 *
 * @author Nao
 */
public class Ingrediant implements Serializable{

    private static final long serialVersionUID = 1L;
    
    public enum Type {
        MG("Milligramme"),
        G("Gramme"),
        KG("Kilogramme"),
        L("Litre"),
        CL("Centilitre"),
        ML("Millilitre"),
        U("Unité"),
        Boite("Boite"),
        CaS("Cuillère a Soupe"),
        CaC("Cuillère a Café");
        
        private String name = "";

        private Type(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private String name;
    private double quantity;
    private Type type;

    public Ingrediant() {
        this("Ingrediant", 0.0f, Type.G);
    }

    public Ingrediant(String name, double quantity, Type type) {
        this.name = name;
        this.quantity = quantity;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
