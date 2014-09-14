package com.dennyac.accesslogparser;

import java.io.IOException;
import java.util.logging.Logger;

import com.google.gson.JsonObject;

/**
 * The FreeGeoIp class is a wrapper around the FreeGeoIp service for making requests to fetch the ip
 * details.
 * @author Denny Abraham Cheriyan
 * @version 1.0, Sep 2014
 */
public class FreeGeoIp implements IpDetailsAPI {
  
  private static final Logger logger = Logger.getLogger(FreeGeoIp.class.getName());

  double rate; // Messages
  double per; // Seconds
  double allowance;
  double last_check;
  double current;
  final String url;

  public FreeGeoIp() {
    rate = 100.0;
    per = 36.0;
    url = "http://freegeoip.net/json/";
    allowance = rate;
    last_check = System.currentTimeMillis() / 1000.0;
  }

  /*
   * (non-Javadoc)
   * @see com.dennyac.accesslogparser.IpDetailsAPI#getIpDetails(java.lang.String)
   */
  @Override
  public IpDetails getIpDetails(String ip) throws IOException {
    logger.entering(getClass().getName(), "getIpDetails");
    synchronized (this) {
      allowance -= 1.0;
    }
    IpDetails ipDet = new IpDetails();
    JsonObject ipJson = HttpRequest.getJson(url + ip);
    ipDet.setLat(ipJson.get("latitude").getAsString());
    ipDet.setLon(ipJson.get("longitude").getAsString());
    logger.exiting(getClass().getName(), "getIpDetails");
    return ipDet;
  }

  /*
   * (non-Javadoc)
   * @see com.dennyac.accesslogparser.IpDetailsAPI#remainingRequests()
   */
  @Override
  public synchronized Integer remainingRequests() {
    logger.entering(getClass().getName(), "remainingRequests");
    double current = System.currentTimeMillis() / 1000.0;
    double time_passed = current - last_check;
    last_check = current;
    allowance += time_passed * (rate / per);
    if (allowance > rate) allowance = rate; // throttle
    logger.exiting(getClass().getName(), "getIpDetails");
    return (int) Math.floor(allowance);
  }
}
