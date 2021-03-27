package hyflex.chesc2011;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hyflex.chesc2011.metrics.BenchmarkMetricCalculator;

//import jdk.jfr.Timestamp;

import org.junit.jupiter.api.Test;

// See https://dzone.com/articles/junit-tutorial-for-beginners-in-5-steps

/**
  * Few unit tests for getMetric method of the BenchmarkMetricCalculator class.
  * @author David Omrai
  */

public class MetricTest {
  BenchmarkMetricCalculator calculator = 
      new BenchmarkMetricCalculator();

  @Test
  void testLowerBound() throws Exception {
    assertEquals(calculator.intervalTo, calculator.getMetric(1, 42, 1), 0.1);
  }

  @Test
  void tesUpperBound() throws Exception {
    assertEquals(calculator.intervalFrom, calculator.getMetric(1, 42, 42), 0.1);
  }

  @Test
  void testMiddleValue() throws Exception {
    assertEquals(
        (calculator.intervalTo - calculator.intervalFrom) / 2, 
        calculator.getMetric(42, 0, 21), 
        0.1);
  }

  @Test
  void testBadRandomInput() throws Exception {
    assertThrows(Exception.class, () -> calculator.getMetric(1, -10, 1));
    ;
  }

  @Test
  void testBadOptimalInput() throws Exception {
    assertThrows(Exception.class, () -> calculator.getMetric(-1, 10, 1));
  }

  @Test
  void testBadWorstInput() throws Exception {
    assertThrows(Exception.class, () -> calculator.getMetric(-1, 10, 1));
  }

  @Test
  void testBadCurrentInput() throws Exception {
    assertThrows(Exception.class, () -> calculator.getMetric(1, 10, -1));
  }

  @Test
  void testBadIntervalInput() throws Exception {
    assertThrows(Exception.class, () -> calculator.getMetric(42, 3, 1));
  }

  @Test
  void testBadOutOfIntervaInput() throws Exception {
    assertThrows(Exception.class, () -> calculator.getMetric(3, 42, 1));
  }
}
