package com.dennyac.accesslogparser;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.JsonObject;

/**
 * The IpUpdate class consumes parsed log entries from the intermediate queue, updates the ip
 * related details for each log entry, and then places the updated log entry on the write queue.
 * Maintains an in-memory HashMap containing ip details to reduce the number of api calls to fetch the
 * details
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014
 */
public class IpUpdate implements Runnable {

  private static final Logger logger = Logger.getLogger(IpUpdate.class.getName());
  private final BlockingQueue<LogEntry> intermediateQueue;
  private final BlockingQueue<LogEntry> writeQueue;
  private final JobStatus jobStatus;

  // In-memory HashMap
  HashMap<String, JsonObject> cachedIP = new HashMap<String, JsonObject>();

  /**
   * The constructor initializes the intermediate queue, write queue and the job status object
   * 
   * @param intermediateQueue queue of log entries for which ip details have to be updated
   * @param writeQueue queue of log entries to be serialized
   * @param jobStatus object which contains the status of the threads
   */
  public IpUpdate(BlockingQueue<LogEntry> intermediateQueue, BlockingQueue<LogEntry> writeQueue,
      JobStatus jobStatus) {
    this.intermediateQueue = intermediateQueue;
    this.writeQueue = writeQueue;
    this.jobStatus = jobStatus;
  }

  /**
   * The ipUpdate thread will continue polling the intermediate queue for
   * log entries to process, as long as the intermediate queue is not empty 
   * or the fileReader thread hasn't completed reading the input file
   */
  public void run() {

    logger.log(Level.INFO, "Entered IpUpdate run method");

    while (!intermediateQueue.isEmpty() || !jobStatus.isFileReadComplete()) {

      LogEntry log;
      try {
        log = intermediateQueue.poll(1000, TimeUnit.MILLISECONDS);
        if (log != null) {
          updateIpDetails(log);
          writeQueue.put(log);

        }
      } catch (InterruptedException e1) {
        logger.log(Level.SEVERE, "IpUpdate encountered InterruptedException", e1);
      }

    }
    logger.log(Level.INFO, "Setting IpUpdateComplete to true");
    jobStatus.setIpUpdateComplete(true);
  }

  /**
   * Takes a log entry as input and updates the log entry with the ip related details
   * @param LogEntry A single log entry
   */
  public void updateIpDetails(LogEntry log) {
    logger.entering(getClass().getName(), "updateIpDetails");

    String ip = log.getRemoteHost();
    JsonObject ipDetails;

    try {
      if (cachedIP.containsKey(ip)) {
        // Cache hit!
        ipDetails = cachedIP.get(ip);
      } else {
        ipDetails = getIpDetails(ip);
        cachedIP.put(ip, ipDetails);
      }

      log.setIspName(ipDetails.get("isp").getAsString());
      log.setLatitude(ipDetails.get("lat").getAsString());
      log.setLongitude(ipDetails.get("lon").getAsString());
      log.setOrganization(ipDetails.get("org").getAsString());

    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error occured during updateIpDetails", e);
      log.setError("Unable to update ip details\n" + e);
    } catch (Exception e) {

    }
    logger.exiting(getClass().getName(), "updateIpDetails");
  }

  /**
   * Takes an ip address as input and returns a Json object that contains the ip details
   * @param ip ip address for which details are required
   * @return Json object containing ip details
   * @exception IOException (thrown from HttpRequest.getJson(url)
   */
  public JsonObject getIpDetails(String ip) throws IOException {
    String url = "http://ip-api.com/json/" + ip;
    return HttpRequest.getJson(url);

  }
}