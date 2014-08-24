package com.dennyac.accesslogparser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.JsonObject;

public class HttpRequestTest {

  @Test
  public void getTest(){
    String url = "http://ip-api.com/json/";
    try {
      JsonObject response = HttpRequest.getJson(url);
      assertTrue("Has member status",response.has("status"));
      assertTrue("Has member country",response.has("country"));
      assertTrue("Has member countryCode",response.has("countryCode"));
      assertTrue("Has member region",response.has("region"));
      assertTrue("Has member regionName",response.has("regionName"));
      assertTrue("Has member city",response.has("city"));
      assertTrue("Has member zip",response.has("zip"));
      assertTrue("Has member lat",response.has("lat"));
      assertTrue("Has member lon",response.has("lon"));
      assertTrue("Has member timezone",response.has("timezone"));
      assertTrue("Has member isp",response.has("isp"));
      assertTrue("Has member org",response.has("org"));
      assertTrue("Has member as",response.has("as"));
      assertTrue("Has member query",response.has("query"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    
  }

}
