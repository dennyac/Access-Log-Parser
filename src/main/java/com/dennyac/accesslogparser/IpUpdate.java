package com.dennyac.accesslogparser;

import java.io.IOException;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
// import java.util.logging.Logger;

import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonObject;

public class IpUpdate implements Runnable {
  // private static final Logger logger = Logger.getLogger(Parser.class.getName());
  private static final Logger logger = Logger.getLogger(IpUpdate.class.getName());
  private final BlockingQueue<LogEntry> intermediateQueue;
  private final BlockingQueue<LogEntry> writeQueue;
  private final JobStatus jobStatus;
  HashMap<String, JsonObject> cachedIP = new HashMap<String, JsonObject>();

  public IpUpdate(BlockingQueue<LogEntry> intermediateQueue, BlockingQueue<LogEntry> writeQueue,
      JobStatus jobStatus) {
   // logger.addHandler(new ConsoleHandler());
    this.intermediateQueue = intermediateQueue;
    this.writeQueue = writeQueue;
    this.jobStatus = jobStatus;
  }

  public void run() {
    // Will have to implement this later on
    // while (logQueue.size() != 0 || !lineReadComplete) {
    logger.log(Level.INFO, "Entered IpUpdate run method");
    logger.log(Level.INFO, "jobStatus.isFileReadComplete() is " + jobStatus.isFileReadComplete());
    logger.log(Level.INFO, "intermediateQueue.size() is " + intermediateQueue.size());
    while (!intermediateQueue.isEmpty() || !jobStatus.isFileReadComplete()) {
      logger.log(Level.INFO,
        "(Inside) jobStatus.isFileReadComplete() is " + jobStatus.isFileReadComplete());
      logger.log(Level.INFO, "(Inside) intermediateQueue.size() is " + intermediateQueue.size());
      LogEntry log;
      // what if the queue becomes empty at this point. And there are no more records, that
      // will be added to the intermediate queue, Then when take() is invoked
      try {
        log = intermediateQueue.poll(1000, TimeUnit.MILLISECONDS);
        // try {
        if (log != null) {
          logger.log(Level.INFO, "Updating ipDetails");
          updateIpDetails(log);
          logger.log(Level.INFO, "ipDetails updated");
          writeQueue.put(log);
          logger.log(Level.INFO, "Added logEntry to writeQueue");

        }
        // } catch (Exception e) {
        // badQueue.add(new BadEntry(log.toString(), e.toString()));
        // }

      } catch (InterruptedException e1) {
        logger.log(Level.SEVERE, "IpUpdate encountered InterruptedException", e1);
      }

    }
    logger.log(Level.INFO, "Setting IpUpdateComplete to true");
    jobStatus.setIpUpdateComplete(true);
  }

  public void updateIpDetails(LogEntry log) {
    logger.entering(getClass().getName(), "updateIpDetails");
    logger.log(Level.INFO, "Trying to update details for ip " + log.getRemoteHost());
    String ip = log.getRemoteHost();
    JsonObject ipDetails;
    // Check what causes the IOException and handle appropriately
    try {
      if (cachedIP.containsKey(ip)) {
        logger.log(Level.INFO, "Cache hit!");
        ipDetails = cachedIP.get(ip);
      } else {
        logger.log(Level.INFO, "Cache miss. Making REST API call");
        ipDetails = getIpDetails(ip);
        logger.log(Level.INFO, "Fetched ip details");
        cachedIP.put(ip, ipDetails);
      }
      logger.log(Level.INFO, "Updating logEntry with ipDetails");
      log.setIspName(ipDetails.get("isp").getAsString());
      log.setLatitude(ipDetails.get("lat").getAsString());
      log.setLongitude(ipDetails.get("lon").getAsString());
      log.setOrganization(ipDetails.get("org").getAsString());
      logger.log(Level.INFO, "Updated logEntry with ipDetails");
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error occured during updateIpDetails", e);
      log.appendError("Unable to update ip details\n" + e);
    }
    logger.exiting(getClass().getName(), "updateIpDetails");
  }

  // Should I move this to another class??
  // Change it to opening and closing the connection only once
 

  public JsonObject getIpDetails(String ip) throws IOException {
    logger.entering(getClass().getName(), "getIpDetails");
    String url = "http://ip-api.com/json/" + ip;
    logger.log(Level.INFO, "Going to make rest call to " + url);
    return HttpRequest.getJson(url);
//    logger.log(Level.INFO, "Obtained json as string result");
//    logger.exiting(getClass().getName(), "getIpDetails");
    //return new Gson().fromJson(result, JsonObject.class);

  }
}

// JSON response
// {
// status: "success",
// country: "United States",
// countryCode: "US",
// region: "CA",
// regionName: "California",
// city: "Foster City",
// zip: "94404",
// lat: "37.555",
// lon: "-122.2687",
// timezone: "America/Los_Angeles",
// isp: "Comcast Business Communications",
// org: "Comcast Business Communications",
// as: "AS7922 Comcast Cable Communications, Inc.",
// query: "198.0.200.105"
// }