package com.dennyac.accesslogparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
  private String remoteHost;
  // Check whether date datatype makes sense
  private String timeStamp;
  private String request;
  private String statusCode;
  private String organization;
  private String latitude;
  private String longitude;
  private String ispName;
  private int size;
  private String referer;
  private String userAgent;
  private StringBuffer errorMessage;
    
  public LogEntry(String entry){
    parse(entry);
  }

  public String getErrorMessage() {
    if (errorMessage == null) return null;
    return errorMessage.toString();

  }

  public void appendError(String errorMessage) {
    if (this.errorMessage == null) this.errorMessage = new StringBuffer();
    this.errorMessage.append(errorMessage);
  }

  public String getRemoteHost() {
    return remoteHost;
  }

  public void setRemoteHost(String remoteHost) {
    this.remoteHost = remoteHost;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public String getReferer() {
    return referer;
  }

  public void setReferer(String referer) {
    this.referer = referer;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getIspName() {
    return ispName;
  }

  public void setIspName(String ispName) {
    this.ispName = ispName;
  }

  // Make this function throw exception
  // write exceptions to string directly
  public void parse(String logEntry) {
    String logEntryPattern =
        "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
    int NUM_FIELDS = 9;

    // System.out.println("Using RE Pattern:");
    // System.out.println(logEntryPattern);
    //
    // System.out.println("Input line is:");
    // System.out.println(logEntry);

    Pattern p = Pattern.compile(logEntryPattern);
    Matcher matcher = p.matcher(logEntry);
    if (!matcher.matches() || NUM_FIELDS != matcher.groupCount()) {
      errorMessage.append("Incorrect number of fields");
    }

    remoteHost = matcher.group(1);
    // Check whether date datatype makes sense
    timeStamp = matcher.group(4);
    request = matcher.group(5);
    statusCode = matcher.group(6);
    // probably include these in the setter functions and throw exceptions over there
    size = Integer.parseInt(matcher.group(7));
    if (!matcher.group(8).equals("-")) referer = matcher.group(8);
    userAgent = matcher.group(9);

  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "LogEntry [remoteHost=" + remoteHost + ", timeStamp=" + timeStamp + ", request="
        + request + ", statusCode=" + statusCode + ", organization=" + organization + ", latitude="
        + latitude + ", longitude=" + longitude + ", ispName=" + ispName + ", size=" + size
        + ", referer=" + referer + ", userAgent=" + userAgent + ", errorMessage=" + errorMessage
        + "]";
  }

}
