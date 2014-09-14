package com.dennyac.accesslogparser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonObject;

/**
 * The IpUpdate class consumes parsed log entries from the intermediate queue, updates the ip
 * related details for each log entry, and then places the updated log entry on the write queue.
 * 
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014
 */
public class IpUpdate implements Runnable {

  private static final Logger logger = Logger.getLogger(IpUpdate.class.getName());
  private final BlockingQueue<LogEntry> intermediateQueue;
  private final BlockingQueue<LogEntry> writeQueue;
  private final JobStatus jobStatus;
  private final IpCache ipCache;

  /**
   * The constructor initializes the intermediate queue, write queue and the job status object
   * @param intermediateQueue queue of log entries for which ip details have to be updated
   * @param writeQueue queue of log entries to be serialized
   * @param ipCache Cache that stores ip related details
   * @param jobStatus object which contains the status of the threads
   */
  public IpUpdate(BlockingQueue<LogEntry> intermediateQueue, BlockingQueue<LogEntry> writeQueue,
      IpCache ipCache, JobStatus jobStatus) {
    this.intermediateQueue = intermediateQueue;
    this.writeQueue = writeQueue;
    this.jobStatus = jobStatus;
    this.ipCache = ipCache;
  }

  /**
   * The ipUpdate thread will continue polling the intermediate queue for log entries to process, as
   * long as the intermediate queue is not empty or the fileReader thread hasn't completed reading
   * the input file
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
    IpDetails ipDetails;

    try {
      ipDetails = ipCache.getIpDetails(ip);
      log.setIpDetails(ipDetails);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Exception encountered during updateIpDetails", e);
      log.setError("Unable to update ip details\n" + e);
    }
    logger.exiting(getClass().getName(), "updateIpDetails");
  }

}