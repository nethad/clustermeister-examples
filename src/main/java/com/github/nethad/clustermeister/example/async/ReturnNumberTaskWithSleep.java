/*
 * Copyright 2012 The Clustermeister Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
