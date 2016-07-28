/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.recipe;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nao
 */
public class IngrediantsModel extends AbstractTableModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Ingrediant> ingrediants;
    private final String [] headers = {"Ingrediant","Quantité","Type"};
    
    public IngrediantsModel(){
        this(new ArrayList<>());
    }
    
    public IngrediantsModel(ArrayList<Ingrediant> ingrediants){
        this.ingrediants = (ArrayList<Ingrediant>)ingrediants.clone();
    }
    
    public void addRow(){
        ingrediants.add(new Ingrediant());
        fireTableDataChanged();
    }
    
    public void delete(int index){
        ingrediants.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public ArrayList<Ingrediant> getIngrediants() {
        return ingrediants;
    }

    @Override
    public int getRowCount() {
        return ingrediants.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            case 0: return ingrediants.get(rowIndex).getName();
            case 1: return ingrediants.get(rowIndex).getQuantity();
            case 2: return ingrediants.get(rowIndex).getType().name();
            default: return new Object();
        }    
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: 
                ingrediants.get(rowIndex).setName(aValue.toString());
                break;
            case 1: 
                try {
                    ingrediants.get(rowIndex).setQuantity(Double.valueOf((String)aValue));
                } catch (NumberFormatException ex) {
                    
                }
                break;
            case 2: 
                ingrediants.get(rowIndex).setType((Ingrediant.Type)aValue);
                break;
        }  
    }
}
