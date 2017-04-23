/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.calendar;

import calendar.naora.recipe.Ingrediant;
import calendar.naora.recipe.Recipe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nao
 */
public class CalendarModel extends AbstractTableModel implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Calendar calendar;
    private HashMap<Integer, Recipe> recipes  = new HashMap<>();
    private final SimpleDateFormat sdf;
    private transient ActionListener saveListener;
    private int mealPerDay = 2;
    
    public CalendarModel() {
        this(Calendar.getInstance());
    }

    public CalendarModel(Calendar c) {
        calendar = c;
        sdf = new SimpleDateFormat("yyyMMdd");
    }
    
    public void addSaveListener(ActionListener l) {
        saveListener = l;
    }

    public void nextWeek() {
        calendar.add(Calendar.WEEK_OF_MONTH, 1);
        fireTableStructureChanged();
    }

    public void previousWeek() {
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        fireTableStructureChanged();
    }

    public void actualWeek() {
        calendar = Calendar.getInstance();
        fireTableStructureChanged();
    }

    public String getMonth() {
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG_FORMAT, Locale.getDefault());
    }

    public String getYear() {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    @Override
    public int getRowCount() {
        return mealPerDay;
    }

    @Override
    public int getColumnCount() {
        return calendar.getActualMaximum(Calendar.DAY_OF_WEEK);
    }

    @Override
    public String getColumnName(int column) {
        int day = calendar.getFirstDayOfWeek() + column;
        calendar.set(Calendar.DAY_OF_WEEK, day);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT_FORMAT, Locale.getDefault()) + calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int day = calendar.getFirstDayOfWeek() + columnIndex;
        calendar.set(Calendar.DAY_OF_WEEK, day);
        int index = Integer.parseInt(sdf.format(calendar.getTime()) + rowIndex);

        String result = "";
        if (recipes.get(index) != null) {
            result = recipes.get(index).getName();
        }
        return result;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue instanceof Recipe) {
            int day = calendar.getFirstDayOfWeek() + columnIndex;
            calendar.set(Calendar.DAY_OF_WEEK, day);
            int index = Integer.parseInt(sdf.format(calendar.getTime()) + rowIndex);

            recipes.put(index, (Recipe) aValue);
            saveListener.actionPerformed(null);
        }
    }
    
    public void removeValueAt(int rowIndex, int columnIndex){
        int day = calendar.getFirstDayOfWeek() + columnIndex;
        calendar.set(Calendar.DAY_OF_WEEK, day);
        int index = Integer.parseInt(sdf.format(calendar.getTime()) + rowIndex);
        recipes.remove(index);
        fireTableCellUpdated(rowIndex, columnIndex);
        saveListener.actionPerformed(null);
    }

    public void setMealPerDay(int mealPerDay) {
        this.mealPerDay = mealPerDay;
        fireTableDataChanged();
        saveListener.actionPerformed(null);
    }

    public int getMealPerDay() {
        return mealPerDay;
    }

    @SuppressWarnings("empty-statement")
    public void delete(Recipe deletedRecipe) {
        while(recipes.values().remove(deletedRecipe));
        fireTableDataChanged();
    }
    
    public void update(Recipe recipeUpdated) {
        if(recipes.containsValue(recipeUpdated)){
            for(Recipe r : recipes.values()) {
                if(r.equals(recipeUpdated))
                    r.copy(recipeUpdated);
            }
        }
    }
    
    public String export(Date begin, Date end){
        
        Calendar c = Calendar.getInstance();
        c.setTime(begin);
        ArrayList<Recipe> recipesTmp = new ArrayList<>();
        
        while(c.getTime().before(end) || c.getTime().equals(end)){
            for(int i=0; i < mealPerDay; i++){
                int index = Integer.parseInt(sdf.format(c.getTime()) + i);
                Recipe tmp = recipes.get(index);
                if(tmp != null)
                    recipesTmp.add(tmp);
            }
            c.add(Calendar.DATE, 1);
        }
        
        ArrayList<Ingrediant> ingrediantsTmp = new ArrayList<>();
        
        for( Recipe r : recipesTmp){
            for(Ingrediant i : r.getIngrediants()){
                if(ingrediantsTmp.contains(i)){
                    int index = ingrediantsTmp.indexOf(i);
                    double quantity = ingrediantsTmp.get(index).getQuantity();
                    quantity += i.getQuantity();
                    ingrediantsTmp.get(index).setQuantity(quantity);
                } else {
                    Ingrediant clone = new Ingrediant(i);
                    ingrediantsTmp.add(clone);
                }
            }
        }
        String newline = System.getProperty("line.separator");
        String list = "";
        for(Ingrediant i : ingrediantsTmp){
            list += i.getName() + " " 
                    + String.valueOf(i.getQuantity()) 
                    + " " + i.getType().name() 
                    + newline;
        }
        return list;
    }
    
    public String getDescription(int rowIndex, int columnIndex){
        String result = "";
        
        int day = calendar.getFirstDayOfWeek() + columnIndex;
        calendar.set(Calendar.DAY_OF_WEEK, day);
        int index = Integer.parseInt(sdf.format(calendar.getTime()) + rowIndex);
        Recipe r = recipes.get(index);
        if(r != null)
            result = r.getDescription();
        
        return result;
    }
    
}
