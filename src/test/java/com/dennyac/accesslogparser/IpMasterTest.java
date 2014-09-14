package com.dennyac.accesslogparser;

import static org.junit.Assert.*;


import org.junit.Test;

public class IpMasterTest {
  
  @Test
  public void parseTest() throws Exception {
    IpMaster ipM = new IpMaster();
    IpDetails ipDetails = ipM.getIpDetails("108.223.242.32");
    assertTrue("Checking latitude",
      Double.parseDouble(ipDetails.getLat()) >= -90.0
          && Double.parseDouble(ipDetails.getLat()) <= 90.0);
    assertTrue("Checking longitude",
      Double.parseDouble(ipDetails.getLon()) >= -180.0
          && Double.parseDouble(ipDetails.getLon()) <= 180.0);
    
  }

}
