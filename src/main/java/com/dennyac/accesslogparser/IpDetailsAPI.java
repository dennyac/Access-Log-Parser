package com.dennyac.accesslogparser;

/**
 * The IpDetailsAPI allows new APIs to be added to the system as long as 
 * they define the logic for parsing the API response and how many requests can be made
 * at a given point, so that the API is not overwhelmed with requests
 * 
 * @author Denny Abraham Cheriyan
 * @version 1.0, Sep 2014
 */
public interface IpDetailsAPI extends Comparable<Object> {

  /**
   * For a given ip, it makes the API call, parses the ip details and updates the IpDetails object
   * @param The ip for which details are required
   * @return An IpDetails object which contains information about the ip
   * @throws Exception the exception
   */
  IpDetails getIpDetails(String ip) throws Exception;

  /**
   * Returns how many requests can be made at a given point based on the rate limit
   * @return the integer
   */
  Integer remainingRequests();

  /*
   * (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  default int compareTo(Object o) {
    IpDetailsAPI compareToAPI = (IpDetailsAPI) o;
    return compareToAPI.remainingRequests().compareTo(this.remainingRequests());
  }
}
