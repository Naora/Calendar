/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.recipe;

/**
 *
 * @author Nao
 */
public class Ingrediant {

    public enum Type {
        MG("Milligramme"),
        G("Gramme"),
        KG("Kilogramme"),
        L("Litre"),
        ML("Millilitre");
        
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
