/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nethad.clustermeister.example.fractals;

import com.github.nethad.clustermeister.api.Clustermeister;
import com.github.nethad.clustermeister.api.Job;
import com.github.nethad.clustermeister.api.impl.ClustermeisterFactory;
import com.github.nethad.clustermeister.api.impl.JobFactory;
import com.github.nethad.clustermeister.api.impl.JobImpl;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import org.jppf.client.JPPFClient;
import org.jppf.client.JPPFJob;
import org.jppf.server.protocol.JPPFTask;
import org.jppf.task.storage.DataProvider;
import org.jppf.task.storage.MemoryMapDataProvider;
import org.jppf.utils.StringUtils;

/**
 *
 * @author thomas
 */
public class ComputeMandelbrot {
    private final ControlsPanel controlsPanel;
    private final ImagePanel imagePanel;
    private Clustermeister clustermeister;
    private BufferedImage image;

    public ComputeMandelbrot(ControlsPanel controlsPanel, ImagePanel imagePanel) {
        this.controlsPanel = controlsPanel;
        this.imagePanel = imagePanel;
    }
    
    public void computePicture() {
        double centerX = controlsPanel.getCenterX();
        double centerY = controlsPanel.getCenterY();
        double diameter = controlsPanel.getDiameter();
        int iterations = controlsPanel.getIterations();
        FractalConfiguration fractalConfiguration = new FractalConfiguration(centerX, centerY, diameter, 800, 600, iterations);
        computePicture(fractalConfiguration);
    }
    
    private void log(String message) {
        System.out.println(message);
    }

    void computePicture(FractalConfiguration config) {
        try {
            if (clustermeister == null) {
                clustermeister = ClustermeisterFactory.create();
            }
            int nbTask = config.bsize;
            log("Executing " + nbTask + " tasks");
            
            Map<String, Object> jobData = new HashMap<String, Object>();
            jobData.put("config", config);
            
            Job<MandelbrotResult> job = JobFactory.create("Clustermeister Mandelbrot", jobData);


            image = new BufferedImage(config.asize, config.bsize, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < nbTask; i++) {
                job.addTask(new MandelbrotCMTask(i, config));
            }
            
            long start = System.currentTimeMillis();

            List<MandelbrotResult> results = clustermeister.executeJob(job);

            long elapsed = System.currentTimeMillis() - start;
            log("Computation performed in " + StringUtils.toStringDuration(elapsed));

            image = generateMandelbrotImage(results, config);
            refreshImage();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void refreshImage() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                imagePanel.setImage(image);
                imagePanel.setVisible(false);
                imagePanel.setVisible(true);
            }
        });
    }
    
    private BufferedImage generateMandelbrotImage(final List<MandelbrotResult> taskList, final FractalConfiguration config) throws Exception {
        int max = config.nmax;

        for (int j = 0; j < config.bsize; j++) {
            MandelbrotResult task = taskList.get(j);
//          int[] values = task.getValues();
            int[] colors = task.getColors();
            for (int i = 0; i < config.asize; i++) {
                image.setRGB(i, config.bsize - j - 1, colors[i]);
            }
        }
        try {
//            ImageIO.write(image, "jpeg", new File("data/mandelbrot.jpg"));
        } catch (Exception e) {
            log(e.getMessage());
            e.printStackTrace();
        }
        return image;
    }
    
}
