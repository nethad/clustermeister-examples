/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nethad.clustermeister.example.textproc;

import java.io.Serializable;

/**
 *
 * @author thomas
 */
class TextProcessingResult implements Serializable {
    private int wordCount;
    private double avgWordLength;
    private int vowelCountA;
    private int vowelCountE;
    private int vowelCountI;
    private int vowelCountO;
    private int vowelCountU;

    void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }
    
    public int getWordCount() {
        return wordCount;
    }

    /**
     * @return the vowelCountA
     */
    public int getVowelCountA() {
        return vowelCountA;
    }

    /**
     * @param vowelCountA the vowelCountA to set
     */
    public void setVowelCountA(int vowelCountA) {
        this.vowelCountA = vowelCountA;
    }

    /**
     * @return the vowelCountE
     */
    public int getVowelCountE() {
        return vowelCountE;
    }

    /**
     * @param vowelCountE the vowelCountE to set
     */
    public void setVowelCountE(int vowelCountE) {
        this.vowelCountE = vowelCountE;
    }

    /**
     * @return the vowelCountI
     */
    public int getVowelCountI() {
        return vowelCountI;
    }

    /**
     * @param vowelCountI the vowelCountI to set
     */
    public void setVowelCountI(int vowelCountI) {
        this.vowelCountI = vowelCountI;
    }

    /**
     * @return the vowelCountO
     */
    public int getVowelCountO() {
        return vowelCountO;
    }

    /**
     * @param vowelCountO the vowelCountO to set
     */
    public void setVowelCountO(int vowelCountO) {
        this.vowelCountO = vowelCountO;
    }

    /**
     * @return the vowelCountU
     */
    public int getVowelCountU() {
        return vowelCountU;
    }

    /**
     * @param vowelCountU the vowelCountU to set
     */
    public void setVowelCountU(int vowelCountU) {
        this.vowelCountU = vowelCountU;
    }

    /**
     * @return the avgWordLength
     */
    public double getAvgWordLength() {
        return avgWordLength;
    }

    /**
     * @param avgWordLength the avgWordLength to set
     */
    public void setAvgWordLength(double avgWordLength) {
        this.avgWordLength = avgWordLength;
    }
    
}
