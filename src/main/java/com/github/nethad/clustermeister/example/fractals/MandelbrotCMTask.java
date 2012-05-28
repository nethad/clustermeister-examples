/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nethad.clustermeister.example.fractals;

import com.github.nethad.clustermeister.api.impl.Task;

/**
 *
 * @author thomas
 */
public class MandelbrotCMTask extends Task<MandelbrotResult> {

    private final int b;
    private final FractalConfiguration config;

    public MandelbrotCMTask(final int b, FractalConfiguration configuration) {
        this.b = b;
        this.config = configuration;
    }

    @Override
    public MandelbrotResult execute() throws Exception {
//        int[] values = new int[config.asize];
        int[] colors = new int[config.asize];
        double bval = config.bmin
                + b * (config.bmax - config.bmin) / config.bsize;
        double astep = (config.amax - config.amin) / config.asize;
        double aval = config.amin;
        for (int i = 0; i < config.asize; i++) {
            double x = aval;
            double y = bval;
            int iteration = 0;
            boolean escaped = false;
            while (!escaped && (iteration < config.nmax)) {
                double x1 = x * x - y * y + aval;
                y = 2 * x * y + bval;
                x = x1;
                if (x * x + y * y > 4) {
                    escaped = true;
                }
                iteration++;
            }
//            values[i] = iteration;
            colors[i] = computeRGB(iteration, config.nmax);
            aval += astep;
        }

        return new MandelbrotResult(null, colors, b);
    }

    /**
     * Compute a RGB value for a specific point.
     *
     * @param value the escape time value for the point.
     * @param max the max escapte time value.
     * @return an int value representing the rgb components for the point.
     */
    private int computeRGB(final int value, final int max) {
        if (value >= max) {
            return 0;
        }
        double x, y, z, t;
        t = 2 * Math.PI * value / max;
        x = 2 * t * (Math.cos(value) + 1);
        y = 2 * t * (Math.sin(t) + 1);
        z = t;
        int rgb[] = new int[3];
        rgb[0] = (int) (230 * x);
        rgb[1] = (int) (230 * y);
        rgb[2] = (int) (230 * z);
        for (int i = 0; i < 3; i++) {
            if (rgb[i] > 460) {
                rgb[i] = rgb[i] % 460;
            }
            if (rgb[i] > 230) {
                rgb[i] = 460 - rgb[i];
            }
            rgb[i] += 25;
        }
        int n = rgb[2];
        n = 256 * n + rgb[1];
        n = 256 * n + rgb[0];
        return n;
    }
}
