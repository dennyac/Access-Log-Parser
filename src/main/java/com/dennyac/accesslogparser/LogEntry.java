package com.dennyac.accesslogparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The LogEntry class is a wrapper around the LogEntryAvro class which is generated from the
 * LogEntryAvro schema file. It also contains a parse method to parse a log entry
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014
 */
public class LogEntry {

  private LogEntryAvro logEntry;

  public LogEntryAvro getAvroObject() {
    return logEntry;
  }

  public LogEntry(String entry) {
    parse(entry);
  }

  public String getErrorMessage() {
    try {
      return logEntry.getErrorMessage().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setError(String errorMessage) {
    logEntry.setErrorMessage(errorMessage);
  }

  public String getRemoteHost() {
    try {
      return logEntry.getRemoteHost().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setRemoteHost(String remoteHost) {
    logEntry.setRemoteHost(remoteHost);
  }

  public String getTimeStamp() {
    try {
      return logEntry.getTimeStamp().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setTimeStamp(String timeStamp) {
    logEntry.setTimeStamp(timeStamp);
  }

  public String getRequest() {
    try {
      return logEntry.getRequest().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setRequest(String request) {
    logEntry.setRequest(request);
  }

  public Integer getStatusCode() {
    try {
      return logEntry.getStatusCode();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setStatusCode(String statusCode) {
    logEntry.setStatusCode(Integer.parseInt(statusCode));
  }

  public Integer getSize() {
    try {
      return logEntry.getSize();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setSize(int size) {
    logEntry.setSize(size);
  }

  public String getReferer() {
    try {
      return logEntry.getReferer().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setReferer(String referer) {
    logEntry.setReferer(referer);
  }

  public String getUserAgent() {
    try {
      return logEntry.getUserAgent().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setUserAgent(String userAgent) {
    logEntry.setUserAgent(userAgent);
  }

  public String getOrganization() {
    try {
      return logEntry.getOrganization().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setOrganization(String organization) {
    logEntry.setOrganization(organization);
  }

  public String getLatitude() {
    try {
      return logEntry.getLatitude().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setLatitude(String latitude) {
    logEntry.setLatitude(latitude);
  }

  public String getLongitude() {
    try {
      return logEntry.getLongitude().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setLongitude(String longitude) {
    logEntry.setLongitude(longitude);
  }

  public String getIspName() {
    try {
      return logEntry.getIspName().toString();
    } catch (NullPointerException e) {
      return null;
    }
  }

  public void setIspName(String ispName) {
    logEntry.setIspName(ispName);
  }
  
  public void setIpDetails(IpDetails ipDet){
    setIspName(ipDet.getIsp());
    setLatitude(ipDet.getLat());
    setLongitude(ipDet.getLon());
    setOrganization(ipDet.getOrg());
  }

  /**
   * Takes a log entry as input, parses it, and initializes the LogEntryAvro object.
   * @param log   Log entry
   */
  public void parse(String log) {
    String logEntryPattern =
        "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";
    Pattern p = Pattern.compile(logEntryPattern);
    Matcher matcher = p.matcher(log);
    if (!matcher.matches() || matcher.groupCount() != 9) {
      logEntry = new LogEntryAvro();
      logEntry.setErrorMessage("Incorrect number of fields");
      logEntry.setLogEntry(log);
    } else {
      logEntry = new LogEntryAvro();
      logEntry.setRemoteHost(matcher.group(1));
      logEntry.setTimeStamp(matcher.group(4));
      logEntry.setRequest(matcher.group(5));
      logEntry.setStatusCode(Integer.parseInt(matcher.group(6)));
      logEntry.setSize(Integer.parseInt(matcher.group(7)));
      logEntry.setUserAgent(matcher.group(9));
      if (!matcher.group(8).equals("-")) logEntry.setReferer(matcher.group(8));
    }

  }

  @Override
  public String toString() {
    return logEntry.toString();
  }

}
