/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.naora.calendar;

/**
 *
 * @author Nao
 */
public class CalendarView extends javax.swing.JPanel {

    private CalendarModel calendarModel;

    public CalendarView() {
        this(new CalendarModel());
    }
    
    /**
     * Creates new form CalendarView
     * @param c
     */
    public CalendarView(CalendarModel c) {
        calendarModel = c;
        initComponents();
        updateWindow();
    }
    
    private void updateWindow() {
        month.setText(calendarModel.getMonth());
        year.setText(calendarModel.getYear());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        nextWeek = new javax.swing.JButton();
        previousWeek = new javax.swing.JButton();
        actualWeek = new javax.swing.JButton();
        month = new javax.swing.JLabel();
        year = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(650, 200));

        jTable1.setModel(calendarModel);
        jTable1.setRowHeight(30);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        nextWeek.setText(">>");
        nextWeek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextWeekMouseClicked(evt);
            }
        });

        previousWeek.setText("<<");
        previousWeek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previousWeekMouseClicked(evt);
            }
        });

        actualWeek.setText("Cette Semaine");
        actualWeek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actualWeekMouseClicked(evt);
            }
        });

        month.setText("Mois");

        year.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        year.setText("Année");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actualWeek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(year))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nextWeek)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(previousWeek)
                            .addComponent(actualWeek))
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void previousWeekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousWeekMouseClicked
        calendarModel.previousWeek();
        updateWindow();
    }//GEN-LAST:event_previousWeekMouseClicked

    private void nextWeekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextWeekMouseClicked
        calendarModel.nextWeek();
        updateWindow();
    }//GEN-LAST:event_nextWeekMouseClicked

    private void actualWeekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actualWeekMouseClicked
        calendarModel.actualWeek();
        updateWindow();
    }//GEN-LAST:event_actualWeekMouseClicked
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualWeek;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel month;
    private javax.swing.JButton nextWeek;
    private javax.swing.JButton previousWeek;
    private javax.swing.JLabel year;
    // End of variables declaration//GEN-END:variables
}