package com.premiumminds.internship.screenlocking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by aamado on 05-05-2022.
 */
@RunWith(JUnit4.class)
public class ScreenLockinPatternTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public ScreenLockinPatternTest() {
  };


  @Test
  public void ScreenLockinPatternTestFirst3Length2Test()  throws InterruptedException, ExecutionException, TimeoutException {
    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(3, 2);
    Integer result = count.get(10, TimeUnit.SECONDS);
    assertEquals(5, result.intValue());
  }

  @Test
  public void ScreenLockinPatternTestFirst3Length5Test()  throws InterruptedException, ExecutionException, TimeoutException {
    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(3, 5);
    Integer result = count.get(10, TimeUnit.SECONDS);
    assertEquals(684, result.intValue());
  }

  @Test
  public void ScreenLockinPatternTestFirst3Length8Test()  throws InterruptedException, ExecutionException, TimeoutException {
    Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(3, 8);
    Integer result = count.get(10, TimeUnit.SECONDS);
    assertEquals(13792, result.intValue());
  }

  @Test
  public void ScreenLockinPatternTestFirst3Length8EqualsFirst3Length9Test()  throws InterruptedException, ExecutionException, TimeoutException {
    Future<Integer> count8  = new ScreenLockinPattern().countPatternsFrom(3, 8);
    Integer result8 = count8.get(10, TimeUnit.SECONDS);
    Future<Integer> count9  = new ScreenLockinPattern().countPatternsFrom(3, 9);
    Integer result9 = count9.get(10, TimeUnit.SECONDS);
    assertEquals(result8.intValue(), result9.intValue());
  }

  @Test
  public void ScreenLockinPatternTestFirst3LengthNegativeTest()  throws InterruptedException, ExecutionException, TimeoutException {
    Exception e = assertThrows(RuntimeException.class, ()->{
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(3, -1);
      Integer result = count.get(10, TimeUnit.SECONDS);
    });
    String message = "Length cannot be less than 1";
    assertEquals(message, e.getMessage());
  }

  @Test
  public void ScreenLockinPatternTestFirst3LengthLargerThan9Test()  throws InterruptedException, ExecutionException, TimeoutException {
    Exception e = assertThrows(RuntimeException.class, ()->{
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(3, 12);
      Integer result = count.get(10, TimeUnit.SECONDS);
    });
    String message = "Length cannot be more than 9";
    assertEquals(message, e.getMessage());
  }

  @Test
  public void ScreenLockinPatternTestFirst11Length2Test()  throws InterruptedException, ExecutionException, TimeoutException {
    Exception e = assertThrows(RuntimeException.class, ()->{
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(11, 2);
      Integer result = count.get(10, TimeUnit.SECONDS);
    });
    String message = "Invalid point, must be between 1 and 9";
    assertEquals(message, e.getMessage());
  }

  @Test
  public void ScreenLockinPatternTestFirst0Length2Test()  throws InterruptedException, ExecutionException, TimeoutException {
    Exception e = assertThrows(RuntimeException.class, ()->{
      Future<Integer> count  = new ScreenLockinPattern().countPatternsFrom(0, 2);
      Integer result = count.get(10, TimeUnit.SECONDS);
    });
    String message = "Invalid point, must be between 1 and 9";
    assertEquals(message, e.getMessage());
  }

}