/*
 * JPPF.
 * Copyright (C) 2005-2012 JPPF Team.
 * http://www.jppf.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nethad.clustermeister.example.fractals;

import org.jppf.server.protocol.JPPFTask;

/**
 * Instances of this task compute the Mandelbrot algorithm (number of iterations to escape the
 * Mandelbrot set) for each point of a line in the resulting image.
 * @author Laurent Cohen
 */
public class MandelbrotTask extends JPPFTask
{
  /**
   * The line number, for which to compute the escape value for each point in the line.
   */
  private int b = -1;
  /**
   * The computed colors for each computed point.
   */
  private int[] colors = null;

  /**
   * Initialize this task with the specified line number.
   * @param b the line number as an int value.
   */
  public MandelbrotTask(final int b)
  {
    this.b = b;
  }

  /**
   * Execute the task.
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run()
  {
    try
    {
      // retrieve the configuration from the data provider
      FractalConfiguration config =
        (FractalConfiguration) getDataProvider().getValue("config");
      int[] iter = new int[config.asize];
      colors = new int[config.asize];
      double bval = config.bmin +
      b * (config.bmax - config.bmin) / config.bsize;
      double astep = (config.amax - config.amin) / config.asize;
      double aval = config.amin;
      for (int i=0; i<config.asize; i++)
      {
        double x = aval;
        double y = bval;
        int iteration = 0;
        boolean escaped = false;
        while (!escaped && (iteration < config.nmax))
        {
          double x1 = x*x - y*y + aval;
          y = 2*x*y + bval;
          x = x1;
          if (x*x + y*y > 4) escaped = true;
          iteration++;
        }
        iter[i] = iteration;
        colors[i] = computeRGB(iteration, config.nmax);
        aval += astep;
      }
      // set the results
      setResult(iter);
    }
    catch(Exception e)
    {
      setException(e);
    }
  }

  /**
   * Compute a RGB value for a specific point.
   * @param value the escape time value for the point.
   * @param max the max escapte time value.
   * @return an int value representing the rgb components for the point.
   */
  private int computeRGB(final int value, final int max)
  {
    if (value >= max) return 0;
    double x, y, z, t;
    t = 2 * Math.PI * value / max;
    x = 2 * t * (Math.cos(value) + 1);
    y = 2 * t * (Math.sin(t) + 1);
    z = t;
    int rgb[] = new int[3];
    rgb[0] = (int) (230 * x);
    rgb[1] = (int) (230 * y);
    rgb[2] = (int) (230 * z);
    for (int i=0; i<3; i++)
    {
      if (rgb[i] > 460) rgb[i] = rgb[i] % 460;
      if (rgb[i] > 230) rgb[i] = 460 - rgb[i];
      rgb[i] += 25;
    }
    int n = rgb[2];
    n = 256 * n + rgb[1];
    n = 256 * n + rgb[0];
    return n;
  }

  /**
   * Get the computed colors for each computed point.
   * @return an array of int values.
   */
  public int[] getColors()
  {
    return colors;
  }

  /*
	private static synchronized void redefineOutput()
	{
		try
		{
			// check if output has already been redefined
			Boolean redefined = (Boolean) NodeRunner.getPersistentData("output.redefined");
			if (redefined == null)
			{
				System.setOut(new PrintStream("myapp.out"));
				System.setErr(new PrintStream("myapp.err"));
				// to ensure other tasks won't redefine it again
				NodeRunner.setPersistentData("output.redefined", true);
			}
		}
		catch(Exception e)
		{
			// ...
		}
	}
   */
}
