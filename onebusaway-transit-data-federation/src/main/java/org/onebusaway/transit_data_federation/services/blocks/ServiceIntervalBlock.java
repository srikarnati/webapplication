package org.onebusaway.transit_data_federation.services.blocks;

import java.io.Serializable;

import org.onebusaway.gtfs.model.calendar.ServiceInterval;

/**
 * Specifies an immutable interval of min and max arrival and departure times.
 * 
 * @author bdferris
 * 
 */
public final class ServiceIntervalBlock implements Serializable, Comparable<ServiceIntervalBlock> {

  private static final long serialVersionUID = 1L;

  private final int[] minArrivals;
  private final int[] minDepartures;
  private final int[] maxArrivals;
  private final int[] maxDepartures;

  /**
   * 
   * @param minArrivals min arrival time in seconds since midnight
   * @param minDepartures min departure time in seconds since midnight
   * @param maxArrivals max arrival time in seconds since midnight
   * @param maxDepartures max departue time in seconds since midnight
   */
  public ServiceIntervalBlock(int[] minArrivals, int[] minDepartures,
      int[] maxArrivals, int[] maxDepartures) {
    this.minArrivals = minArrivals;
    this.minDepartures = minDepartures;
    this.maxArrivals = maxArrivals;
    this.maxDepartures = maxDepartures;

    int n = minArrivals.length;
    if (!(n == minDepartures.length && n == maxArrivals.length && n == maxDepartures.length))
      throw new IllegalArgumentException("arrays must have same length");
  }

  /**
   * 
   * @return min arrival time in seconds since midnight
   */
  public int[] getMinArrivals() {
    return minArrivals;
  }

  /**
   * 
   * @return min departure time in seconds since midnight
   */
  public int[] getMinDepartures() {
    return minDepartures;
  }

  /**
   * 
   * @return max arrival time in seconds since midnight
   */
  public int[] getMaxArrivals() {
    return maxArrivals;
  }

  /**
   * 
   * @return max departure time in seconds since midnight
   */
  public int[] getMaxDepartures() {
    return maxDepartures;
  }
  
  public ServiceInterval getRange() {
    int n = maxArrivals.length-1;
    return new ServiceInterval(minArrivals[0], minDepartures[0], maxArrivals[n], maxDepartures[n]);
  }

  @Override
  public int compareTo(ServiceIntervalBlock o) {
    return minArrivals[0] - o.minArrivals[0];
  }
}