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
public class TagsModel extends AbstractTableModel implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private ArrayList<String> tags;

    public TagsModel() {
        this(new ArrayList<>());
    }

    public TagsModel(ArrayList<String> tags) {
        this.tags = (ArrayList<String>) tags.clone();
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void addRow() {
        tags.add("Nouveau tag");
        fireTableDataChanged();
    }

    void delete(int selectedRow) {
        if (selectedRow != -1) {
            tags.remove(selectedRow);
            fireTableRowsDeleted(selectedRow, selectedRow);
        }
    }

    @Override
    public int getRowCount() {
        return tags.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tags.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return "Tags";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue.toString().isEmpty()) {
            tags.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        } else {
            tags.set(rowIndex, aValue.toString());
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
}
