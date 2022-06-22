package com.premiumminds.internship.screenlocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2022.
 */
class ScreenLockinPattern implements IScreenLockinPattern {

    private static final int POINTS = 9;

    private boolean canConnect(int curr, int i, boolean[] visited){
        if(Math.abs((i-1)/3 - (curr-1)/3)==1)
            return true;
        else if ((Math.abs((i-1)/3 - (curr-1)/3)==2 && Math.abs((i-1)%3 - (curr-1)%3)==1))
            return true;
        else if (((i-1)/3==(curr-1)/3 && Math.abs(i-curr)==1))
            return true;
        else if ((visited[(curr+i)/2]))
            return true;

        return false;
    }

    private int getAllPatterns(boolean[] visited, int curr, int length) {
        if(length <= 1 )
            return 1;

        int paths = 0;

        visited[curr] = true;

        for(int i = 1; i<= POINTS; i++) {
            if(!visited[i] && canConnect(curr,i, visited)) {
                paths += getAllPatterns(visited.clone(), i, length-1);
            }
        }
        return paths;
    }

 /**
  * Method to count patterns from firstPoint with the length
  * @param firstPoint initial matrix position
  * @param length the number of points used in pattern
  * @return number of patterns
  */
  public Future<Integer> countPatternsFrom(final int firstPoint, int length) {
      //throw new RuntimeException("Not Implemented Yet");
      if (length <= 0)
          throw new RuntimeException("Length cannot be less than 1");

      if (length >= 10)
          throw new RuntimeException("Length cannot be more than 9");

      if (firstPoint<=0 || firstPoint>=10)
          throw new RuntimeException("Invalid point, must be between 1 and 9");

      ExecutorService executor = Executors.newFixedThreadPool(4);

      boolean[] visited = new boolean[POINTS+1];

      Callable<Integer> getAllPatterns = () -> getAllPatterns(visited, firstPoint,length);

      return executor.submit(getAllPatterns);
  }
}
