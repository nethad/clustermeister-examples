/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nethad.clustermeister.example.fractals;

import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author thomas
 */
public class FractalsGUI extends javax.swing.JFrame {
    final static String[] LOOKANDFEEL = new String[]{"GTK", "Nimbus"};
    
    private ComputeMandelbrot computeMandelbrot;
    
    private void initializeMandelbrot() {
        computeMandelbrot = new ComputeMandelbrot(controlsPanel1, imagePanel1);
        controlsPanel1.setComputeMandelBrot(computeMandelbrot);
        imagePanel1.addMouseListener(new ZoomMouseListener(controlsPanel1, computeMandelbrot));
        drawMandelbrotPicture();
        
    }
    
    private void drawMandelbrotPicture() {
        new Thread(new Runnable() {
            public void run() {
                computeMandelbrot.computePicture();
            }
        }).start();
    }

    /**
     * Creates new form FractalsGUI
     */
    public FractalsGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlsPanel1 = new com.github.nethad.clustermeister.example.fractals.ControlsPanel();
        imagePanel1 = new com.github.nethad.clustermeister.example.fractals.ImagePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clustermeister Mandelbrot Viewer");
        setMinimumSize(new java.awt.Dimension(820, 820));

        javax.swing.GroupLayout imagePanel1Layout = new javax.swing.GroupLayout(imagePanel1);
        imagePanel1.setLayout(imagePanel1Layout);
        imagePanel1Layout.setHorizontalGroup(
            imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imagePanel1Layout.setVerticalGroup(
            imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                    .addComponent(imagePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(controlsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(imagePanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            // use GTK look and feel if available, otherwise use Nimbus LAF if available; do nothing otherwise.
            LookAndFeelInfo[] lookAndFeels = javax.swing.UIManager.getInstalledLookAndFeels();
            boolean foundGtk = false;
            LookAndFeelInfo nimbusLookAndFeel = null;
            for (LookAndFeelInfo lookAndFeelInfo : lookAndFeels) {
                if (lookAndFeelInfo.getName().startsWith("GTK")) {
                    foundGtk = true;
                    javax.swing.UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    break;
                } else if (lookAndFeelInfo.getName().equals("Nimbus")) {
                    nimbusLookAndFeel = lookAndFeelInfo;
                }
            }
            if (!foundGtk && nimbusLookAndFeel != null) {
                javax.swing.UIManager.setLookAndFeel(nimbusLookAndFeel.getClassName());
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FractalsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FractalsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FractalsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FractalsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                FractalsGUI fractalsGui = new FractalsGUI();
                fractalsGui.setVisible(true);
                fractalsGui.initializeMandelbrot();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.nethad.clustermeister.example.fractals.ControlsPanel controlsPanel1;
    private com.github.nethad.clustermeister.example.fractals.ImagePanel imagePanel1;
    // End of variables declaration//GEN-END:variables


}
