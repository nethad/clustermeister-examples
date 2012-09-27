package com.github.nethad.clustermeister.example.longrunning;

import com.github.nethad.clustermeister.api.impl.Task;

public class LongRunningTask extends Task<String> {
	
	public static String RESULT = "done.";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int timeToSleep;
	
	public LongRunningTask(int seconds) {
		this.timeToSleep = seconds * 1000;
	}

	@Override
	public String execute() throws Exception {
		Thread.sleep(timeToSleep);
		return RESULT;
	}

}
