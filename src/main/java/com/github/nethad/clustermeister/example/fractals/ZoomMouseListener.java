/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nethad.clustermeister.example.fractals;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author thomas
 */
public class ZoomMouseListener extends MouseAdapter {
    private final ControlsPanel controlsPanel;
    private final AbstractComputeMandelbrot computeMandelbrot;

    public ZoomMouseListener(ControlsPanel controlsPanel, AbstractComputeMandelbrot computeMandelbrot) {
        this.controlsPanel = controlsPanel;
        this.computeMandelbrot = computeMandelbrot;
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Component comp = event.getComponent();
        if (!(comp instanceof ImagePanel)) {
            return;
        }

        int button = event.getButton();
        if ((button == MouseEvent.BUTTON1) || (button == MouseEvent.BUTTON3)) {
            int mouseX = event.getX();
            int mouseY = event.getY();
            double centerX = controlsPanel.getCenterX();
            double centerY = controlsPanel.getCenterY();
            double d = controlsPanel.getDiameter();
            double minX = centerX - d / 2;
            double x = mouseX * d / 800d + minX;
            double minY = centerY - d / 2;
            double y = (600 - mouseY - 1) * d / 600d + minY;
            int f = controlsPanel.getZoomFactor();

            d = (button == MouseEvent.BUTTON1) ? d / f : d * f;
            controlsPanel.setCenterX(x);
            controlsPanel.setCenterY(y);
            controlsPanel.setDiameter(d);
            
            int iter = controlsPanel.getIterations();
            try {
//                FractalRunner.perform(true, new FractalConfiguration(x, y, d, 800, 600, iter), getOption());
                computeMandelbrot.computePicture(new FractalConfiguration(x, y, d, 800, 600, iter));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
