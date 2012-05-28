package com.github.nethad.clustermeister.example.async;

import com.github.nethad.clustermeister.api.impl.Task;

/**
 *
 * @author thomas
 */
public class ReturnNumberTask extends Task<Integer> {
    private final int numberToReturn;
    
    public ReturnNumberTask(int numberToReturn) {
        this.numberToReturn = numberToReturn;
    }
    
    @Override
    public Integer execute() throws Exception {
        return numberToReturn;
    }
    
}
