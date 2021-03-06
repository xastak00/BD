/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.vutbr.fit.pdb.interf;

import cz.vutbr.fit.pdb.Base.ReloadDatabaseModel;
import cz.vutbr.fit.pdb.hlavni.DataBase;
import cz.vutbr.fit.pdb.system.Identifikace;
import cz.vutbr.fit.pdb.system.Loader;

/**
 *
 * @author Iuliia
 */
public class ParkMainPanel1 extends javax.swing.JFrame {

    /**
     * Creates new form ParkMainPanel1
     */
    public ParkMainPanel1() {
        initComponents();
    }

    /**
     * Metoda pro nastaveni viditelnosti panelu
     * @param b viditelnost
     */
    public void setPanelVisibility(boolean b) {
        jTabbedPane1.setEnabled(b);
    }
    
      /**
     * Zkotroluje, zda se ma zazakat prepinani panelu
     */
    public void checkPanelAvailability() {
        if (!Loader.existsLocalConfig() && !DataBase.getIdentifikace().getIdentity().isLoggendIn()) {
            jTabbedPane1.setEnabled(false);
        } else if (ReloadDatabaseModel.isReloadRequired()) {
            jTabbedPane1.setEnabled(false);
        } else {
            jTabbedPane1.setEnabled(true);
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

        registrace1 = new cz.vutbr.fit.pdb.interf.Registrace();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        registrace2 = new cz.vutbr.fit.pdb.interf.Registrace();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addTab("tab1", registrace2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParkMainPanel1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParkMainPanel1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParkMainPanel1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParkMainPanel1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParkMainPanel1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private cz.vutbr.fit.pdb.interf.Registrace registrace1;
    private cz.vutbr.fit.pdb.interf.Registrace registrace2;
    // End of variables declaration//GEN-END:variables
}
