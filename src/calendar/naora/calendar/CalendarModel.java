/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.calendar;

import calendar.naora.recipe.Recipe;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private HashMap<Integer, Recipe> recipes;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyMMdd");
    private int mealPerDay;

    public CalendarModel() {
        this(Calendar.getInstance());
    }

    public CalendarModel(Calendar c) {
        calendar = c;
        mealPerDay = 2;
        recipes = new HashMap<>();
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
        }
    }
    
    public void removeValueAt(int rowIndex, int columnIndex){
        int day = calendar.getFirstDayOfWeek() + columnIndex;
        calendar.set(Calendar.DAY_OF_WEEK, day);
        int index = Integer.parseInt(sdf.format(calendar.getTime()) + rowIndex);
        recipes.remove(index);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void setMealPerDay(int mealPerDay) {
        this.mealPerDay = mealPerDay;
        fireTableDataChanged();
    }

    public int getMealPerDay() {
        return mealPerDay;
    }

    @SuppressWarnings("empty-statement")
    public void delete(Recipe deletedRecipe) {
        while(recipes.values().remove(deletedRecipe));
        fireTableDataChanged();
    }
}
