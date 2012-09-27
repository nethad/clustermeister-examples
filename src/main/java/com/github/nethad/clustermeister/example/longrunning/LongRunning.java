package com.github.nethad.clustermeister.example.longrunning;

import java.util.List;

import org.jppf.utils.StringUtils;

import com.github.nethad.clustermeister.api.Clustermeister;
import com.github.nethad.clustermeister.api.impl.ClustermeisterFactory;
import com.github.nethad.clustermeister.api.impl.Job;
import com.github.nethad.clustermeister.api.impl.JobFactory;

public class LongRunning {
	
    public static void main(String... args) {
    	int numberOfTasks = 1;
    	int secondsToSleep = 10;
    	if (args.length == 1) {
    		try {
    			numberOfTasks = Integer.parseInt(args[0]);
    		} catch (NumberFormatException e) {
    			// ignore
    		}
    	} else if (args.length == 2) {
    		try {
    			numberOfTasks = Integer.parseInt(args[0]);
    			secondsToSleep = Integer.parseInt(args[1]);
    		} catch (NumberFormatException e) {
    			// ignore
    		}
    	}
        new LongRunning().execute(numberOfTasks, secondsToSleep);
    }

	private Clustermeister clustermeister;

	private void execute(int numberOfTasks, int secondsToSleep) {
		try {
            if (clustermeister == null) {
                clustermeister = ClustermeisterFactory.create();
            }
            
            System.out.println("Number of tasks: "+numberOfTasks+", seconds to sleep: "+secondsToSleep);
            
            Job<String> job = JobFactory.create("Clustermeister Mandelbrot", null);
            
            for (int i=0; i<numberOfTasks; i++) {
            	job.addTask(new LongRunningTask(secondsToSleep));
            }
            
            long start = System.currentTimeMillis();

            List<String> results = clustermeister.executeJob(job);
            
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("Computation performed in " + StringUtils.toStringDuration(elapsed));
            
            for (String result : results) {
                if (!result.equals(LongRunningTask.RESULT)) {
                	System.err.println("Wrong result! result was: "+result);
                }
            }
            
            System.out.println("Done.");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (clustermeister != null) {
                clustermeister.shutdown();
            }
        }
	}

}
