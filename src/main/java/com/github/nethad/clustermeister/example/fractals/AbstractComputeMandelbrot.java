/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nethad.clustermeister.example.fractals;

import com.github.nethad.clustermeister.api.Clustermeister;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;

/**
 *
 * @author thomas
 */
public abstract class AbstractComputeMandelbrot {
    
    protected final ControlsPanel controlsPanel;
    protected final ImagePanel imagePanel;
    private BufferedImage image;

    protected AbstractComputeMandelbrot(ControlsPanel controlsPanel, ImagePanel imagePanel) {
        this.controlsPanel = controlsPanel;
        this.imagePanel = imagePanel;
    }
    
    public static AbstractComputeMandelbrot create(ControlsPanel controlsPanel, ImagePanel imagePanel, boolean async) {
        if (async) {
            return new ComputeMandelbrotAsync(controlsPanel, imagePanel);
        } else {
            return new ComputeMandelbrotBlocking(controlsPanel, imagePanel);
        }
    }
    
    protected BufferedImage getImage() {
        return image;
    }
    
    protected void newImage(FractalConfiguration config) {
        image = new BufferedImage(config.asize, config.bsize, BufferedImage.TYPE_INT_RGB);
    }
    
    public void computePicture() {
        double centerX = controlsPanel.getCenterX();
        double centerY = controlsPanel.getCenterY();
        double diameter = controlsPanel.getDiameter();
        int iterations = controlsPanel.getIterations();
        FractalConfiguration fractalConfiguration = new FractalConfiguration(centerX, centerY, diameter, 800, 600, iterations);
        computePicture(fractalConfiguration);
    }
    
    public abstract void computePicture(FractalConfiguration config);
    
    protected void refreshImage() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                imagePanel.setImage(image);
                imagePanel.setVisible(false);
                imagePanel.setVisible(true);
            }
        });
    }
    
}
