/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.dnd;

import calendar.naora.calendar.CalendarModel;
import calendar.naora.recipe.Recipe;
import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.TableModel;

/**
 *
 * @author u139279
 */
public class RecipeTransferHandler extends TransferHandler {

    private CalendarModel calendarModel;
    private JTable calendarTable;

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = null;

        if (c instanceof JList) {
            JList list = (JList) c;
            Object value = list.getSelectedValue();
            if (value instanceof Recipe) {
                t = new RecipeTransferable((Recipe) value);
            }
        }
        return t;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        boolean result = false;
        Component component = support.getComponent();
        if (component instanceof JTable) {
            calendarTable = (JTable) component;
            TableModel model = calendarTable.getModel();
            if (model instanceof CalendarModel) {
                calendarModel = (CalendarModel) model;
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean importData(TransferSupport support) {
        boolean result = false;

        if (canImport(support)) {
            try {
                Transferable t = support.getTransferable();
                Object value = t.getTransferData(RecipeTransferable.RECIPE_FLAVOR);
                if (value instanceof Recipe) {
                    Point point = support.getDropLocation().getDropPoint();
                    int row = calendarTable.rowAtPoint(point);
                    int col = calendarTable.columnAtPoint(point);

                    if (row != -1 && col != -1) {
                        calendarModel.setValueAt(value, row, col);
                        result = true;
                    }

                }
            } catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

}
