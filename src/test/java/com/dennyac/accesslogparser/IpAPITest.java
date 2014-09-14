package com.dennyac.accesslogparser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class IpAPITest {

  @Test
  public void parseTest() throws IOException, InterruptedException {
    IpAPI ipAPI = new IpAPI();
    IpDetails ipDetails = ipAPI.getIpDetails("108.223.242.32");
    assertTrue("Checking latitude",
      Double.parseDouble(ipDetails.getLat()) >= -90.0
          && Double.parseDouble(ipDetails.getLat()) <= 90.0);
    assertTrue("Checking longitude",
      Double.parseDouble(ipDetails.getLon()) >= -180.0
          && Double.parseDouble(ipDetails.getLon()) <= 180.0);
    
  }

}
