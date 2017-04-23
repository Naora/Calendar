/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import calendar.naora.calendar.CalendarModel;
import calendar.naora.calendar.CalendarView;
import calendar.naora.export.ExportDialog;
import calendar.naora.recipe.Recipe;
import calendar.naora.recipe.RecipeModel;
import calendar.naora.recipe.RecipeView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Nao
 */
public class Calendar extends javax.swing.JFrame {

    private CalendarModel calendarModel;
    private RecipeModel recipeModel;
    private String document;
    
    private class CalendarListDataListener implements ListDataListener {

        @Override
        public void intervalAdded(ListDataEvent e) {
        }

        @Override
        public void intervalRemoved(ListDataEvent e) {
            if (e.getSource() instanceof RecipeModel) {
                RecipeModel model = (RecipeModel) e.getSource();
                Recipe deletedRecipe = model.getDeletedRecipe();
                calendarModel.delete(deletedRecipe);
            }
        }

        @Override
        public void contentsChanged(ListDataEvent e) {
            if (e.getSource() instanceof RecipeModel) {
                RecipeModel model = (RecipeModel) e.getSource();
                Recipe recipeUpdated = model.getUpdatedRecipe();
                calendarModel.update(recipeUpdated);
            }
        }

    }
    
    private class CalendarSaveActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            save();
        }
    }

    /**
     * Creates new form Calendar
     */
    public Calendar() {
        document = System.getProperty("user.dir") + System.getProperty("file.separator");
        calendarModel = new CalendarModel();
        recipeModel = new RecipeModel();
        load();
        initComponents();
        postInit();
    }
    
    private void postInit() {
        recipeModel.addListDataListener(new CalendarListDataListener());
        recipeModel.addSaveListener(new CalendarSaveActionListener());
        calendarModel.addSaveListener(new CalendarSaveActionListener());
    }

    private void save() {
        try {
            FileOutputStream fileCalendar = new FileOutputStream(document+"calendar.ser");
            FileOutputStream fileRecipe = new FileOutputStream(document+"recipe.ser");
            try (ObjectOutputStream out = new ObjectOutputStream(fileCalendar)) {
                out.writeObject(calendarModel);
            } catch (IOException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (ObjectOutputStream out = new ObjectOutputStream(fileRecipe)) {
                out.writeObject(recipeModel);
            } catch (IOException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void load() {
        try {
            FileInputStream fileCalendar = new FileInputStream(document+"calendar.ser");
            try (ObjectInputStream in = new ObjectInputStream(fileCalendar)) {
                calendarModel = (CalendarModel) in.readObject();
                calendarModel.actualWeek();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        try {
            FileInputStream fileRecipe = new FileInputStream(document+"recipe.ser");
            try (ObjectInputStream in = new ObjectInputStream(fileRecipe)) {
                recipeModel = (RecipeModel) in.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Calendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendarView = new CalendarView(calendarModel);
        recipeView = new RecipeView(recipeModel);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        save = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        export = new javax.swing.JMenuItem();
        quit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mealPerDay = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calendrier de recette - Aurore");
        setMinimumSize(new java.awt.Dimension(1050, 350));
        setPreferredSize(new java.awt.Dimension(1050, 350));

        jMenu1.setText("Fichier");

        save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save.setText("Enregistrer");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jMenu1.add(save);
        jMenu1.add(jSeparator1);

        export.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        export.setText("Exporter");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });
        jMenu1.add(export);

        quit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quit.setText("Quitter");
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitActionPerformed(evt);
            }
        });
        jMenu1.add(quit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edition");

        mealPerDay.setText("Nombre de Repas");
        mealPerDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mealPerDayActionPerformed(evt);
            }
        });
        jMenu2.add(mealPerDay);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(recipeView, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendarView, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calendarView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
            .addComponent(recipeView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        save();
    }//GEN-LAST:event_saveActionPerformed

    private void quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitActionPerformed
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_quitActionPerformed

    private void mealPerDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mealPerDayActionPerformed
        SpinnerNumberModel model = new SpinnerNumberModel(calendarModel.getMealPerDay(), 0, 10, 1);
        JSpinner spinner = new JSpinner(model);
        int option = JOptionPane.showOptionDialog(this, spinner, "Nombre de repas par jour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) {
            calendarModel.setMealPerDay((int) spinner.getValue());
        }
    }//GEN-LAST:event_mealPerDayActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        ExportDialog dialog = new ExportDialog(this, true, calendarModel);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_exportActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calendar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            Calendar c = new Calendar();
            c.setLocationRelativeTo(null);
            c.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private calendar.naora.calendar.CalendarView calendarView;
    private javax.swing.JMenuItem export;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem mealPerDay;
    private javax.swing.JMenuItem quit;
    private calendar.naora.recipe.RecipeView recipeView;
    private javax.swing.JMenuItem save;
    // End of variables declaration//GEN-END:variables
}
