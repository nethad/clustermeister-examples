/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nethad.clustermeister.example.fractals;

import java.io.Serializable;

/**
 *
 * @author thomas
 */
public class MandelbrotResult implements Serializable {
    private final int[] values;
    private final int[] colors;
    private final int row;

    public MandelbrotResult(int[] values, int[] colors, int row) {
        this.values = values;
        this.colors = colors;
        this.row = row;
    }

    /**
     * @return the values
     */
    public int[] getValues() {
        return values;
    }

    /**
     * @return the colors
     */
    public int[] getColors() {
        return colors;
    }

    public int getRow() {
        return row;
    }
    
    
    
}
