package com.github.nethad.clustermeister.example.async;

import com.github.nethad.clustermeister.api.impl.Task;
import java.util.Random;

/**
 *
 * @author thomas
 */
public class ReturnNumberTaskWithSleep extends Task<String> {
    private final int numberToReturn;
    private final int maximumSleepInMS;
    
    public ReturnNumberTaskWithSleep(int numberToReturn, int maximumSleepInMS) {
        this.numberToReturn = numberToReturn;
        this.maximumSleepInMS = maximumSleepInMS;
    }
    
    @Override
    public String execute() throws Exception {
        int waitInMS = new Random().nextInt(maximumSleepInMS);
        Thread.sleep(waitInMS);
        return numberToReturn + " (slept in ms: "+waitInMS+")";
    }
    
}
