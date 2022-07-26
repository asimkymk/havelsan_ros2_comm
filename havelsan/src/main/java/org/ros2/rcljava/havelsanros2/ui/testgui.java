/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.ros2.rcljava.havelsanros2.ui;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import org.ros2.rcljava.havelsanros2.models.Status;
import org.ros2.rcljava.havelsanros2.publishers.StatusPubs;

/**
 *
 * @author asimkaymak
 */
public class testgui extends javax.swing.JPanel {

    /**
     * Creates new form testgui
     */
    public testgui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setText("deneme");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jButton1)
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jButton1)
                .addContainerGap(154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        File diskPartition = new File("/");
        long totalCapacity = diskPartition.getTotalSpace(); 
        long freePartitionSpace = diskPartition.getFreeSpace(); 
        long usablePatitionSpace = diskPartition.getUsableSpace();
        Boolean[] systemLiveStatus = new Boolean[] {true, true, true};
        Boolean[] consoleRecordStatus = new Boolean[] {true, false, true}; // konsol durum
        Boolean[] displayRecordStatus = new Boolean[] {true, true, true};
        Status status = new Status(totalCapacity,freePartitionSpace,usablePatitionSpace,systemLiveStatus,consoleRecordStatus,displayRecordStatus);
        StatusPubs testCases = new StatusPubs();
        testCases.publishTest(status);
    }//GEN-LAST:event_jButton1MouseClicked

    public static void main(String[] args) {
        System.out.println("starting");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new testgui());
        frame.pack();
        frame.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
