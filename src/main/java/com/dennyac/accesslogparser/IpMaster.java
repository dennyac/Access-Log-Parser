package com.dennyac.accesslogparser;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * The IpMaster class manages a pool of APIs from which ip details can be requests. The APIs are maintained
 * in a priority queue, and the order is decided by the number of remaining requests the API can handle.
 * All APIs added have to implement the IpDetailsAPI interface.
 * 
 * @author Denny Abraham Cheriyan
 * @version 1.0, Sep 2014
 */
public class IpMaster {
  
  private static final Logger logger = Logger.getLogger(IpMaster.class.getName());
  private final BlockingQueue<IpDetailsAPI> pQueue;

  public IpMaster() {
    pQueue = new PriorityBlockingQueue<IpDetailsAPI>();
    pQueue.add(new IpInfo());
    pQueue.add(new IpAPI());
    pQueue.add(new FreeGeoIp());
    
  }

  /**
   * Makes a request to the API which has max remaining requests to fetch the ip details.
   *
   * @param The ip for which details are required
   * @return An IpDetails object which contains information about the ip
   * @throws Exception the exception
   */
  public IpDetails getIpDetails(String ip) throws Exception {
    logger.entering(getClass().getName(), "getIpDetails");
    int remainingRequests;
    IpDetails ipDet;
    IpDetailsAPI api;
    do {
      api = pQueue.take();
      remainingRequests = api.remainingRequests();
      if (remainingRequests < 1) {
        Thread.sleep(1000);
        pQueue.put(api);
      }
    } while (remainingRequests < 1);
    ipDet = api.getIpDetails(ip);
    pQueue.put(api);
    logger.exiting(getClass().getName(), "getIpDetails");
    return ipDet;
  }
}
