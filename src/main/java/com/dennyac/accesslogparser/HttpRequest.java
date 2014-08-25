package com.dennyac.accesslogparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * The HttpRequest class contains methods for making GET requests
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014
 */
public class HttpRequest {

  /**
   * Makes a GET request to the specified url, and returns the Json response
   * @param urlStr Input url
   * @return Json response
   */
  public static JsonObject getJson(String urlStr) throws IOException {

    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    if (conn.getResponseCode() != 200) {
      throw new IOException(conn.getResponseMessage());
    }

    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = rd.readLine()) != null) {
      sb.append(line);
    }
    rd.close();

    conn.disconnect();
    return new Gson().fromJson(sb.toString(), JsonObject.class);

  }
}
