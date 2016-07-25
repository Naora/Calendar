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
    
    public static final int TYPE_MG = 0;
    public static final int TYPE_G = 1;
    public static final int TYPE_KG = 2;
    public static final int TYPE_L = 3;
    public static final int TYPE_ML = 4;
    
    private String name;
    private float quantity;
    private int type;
    
    public Ingrediant(){
        this("Ingrediant", 0.0f, TYPE_G);
    }
    
    public Ingrediant(String name, float quantity, int type){
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

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public String getTypeName(){
        switch(type){
            case TYPE_G: return "Gramme";
            case TYPE_KG: return "Kilogramme";
            case TYPE_L: return "Litre";
            case TYPE_MG: return "Milligramme";
            case TYPE_ML: return "Millilitre";
            default: return "Type Inconnue";
        }
    }
    
}
