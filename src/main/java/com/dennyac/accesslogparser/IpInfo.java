package com.dennyac.accesslogparser;

import java.io.IOException;
import java.util.logging.Logger;

import com.google.gson.JsonObject;


/**
 * The IpInfo class is a wrapper around the IpInfo service for making requests to fetch the ip
 * details.
 * @author Denny Abraham Cheriyan
 * @version 1.0, Sep 2014
 */
public class IpInfo implements IpDetailsAPI {
  private static final Logger logger = Logger.getLogger(IpInfo.class.getName());
  double rate; // Messages
  double per; // Seconds
  double allowance;
  double last_check;
  double current;
  final String url;

  public IpInfo() {
    rate = 10.0;
    per = 864.0;
    url = "http://ipinfo.io/%s/json";
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
    String requestUrl = String.format(url, ip);
    JsonObject ipJson = HttpRequest.getJson(requestUrl);
    String[] location = ipJson.get("loc").getAsString().split(",");
    ipDet.setIsp(ipJson.get("org").getAsString());
    ipDet.setLat(location[0]);
    ipDet.setLon(location[1]);
    ipDet.setOrg(ipJson.get("org").getAsString());
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
