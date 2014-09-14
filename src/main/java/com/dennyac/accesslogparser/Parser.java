package com.dennyac.accesslogparser;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonObject;

/**
 * The Parser class instantiates the shared queues, threads for reading from the file, \ updating
 * the ip details, and serializing to Avro file format
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014
 */
public class Parser {
  private static final Logger logger = Logger.getLogger(Parser.class.getName());
  private static final int NTHREDS = 5;

  /**
   * Takes an ip address as input and returns a Json object that contains the ip details
   * @param inPath input access log file to parse
   * @param outPath output Avro file to write to
   * @return JobStatus object containing status of threads
   * @exception IOException
   * @throws InterruptedException
   */
  public JobStatus parseLog(String inPath, String outPath) throws IOException, InterruptedException {
    logger.entering(getClass().getName(), "parseLog");
    ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
    BlockingQueue<LogEntry> intermediateQueue = new LinkedBlockingQueue<LogEntry>();
    BlockingQueue<LogEntry> writeQueue = new LinkedBlockingQueue<LogEntry>();
    IpCache ipCache = new IpCache();
    JobStatus jobStatus = new JobStatus();
    try {
      // LineReader is responsible for reading from the file and writing to the intermediate queue
      LineReader lr = new LineReader(intermediateQueue, writeQueue, inPath, jobStatus);

      // LogSerialize is responsible for writing the log entries to disk using Avro serialization
      // format
      LogSerialize ls = new LogSerialize(writeQueue, outPath, jobStatus);
      
      executor.execute(lr);
      for (int i = 0; i < 3; i++) {
        // IpUpdate reads from the intermediate queue and updates
        executor.execute(new IpUpdate(intermediateQueue, writeQueue, ipCache, jobStatus));
      }
      executor.execute(ls);
      executor.shutdown();
      executor.awaitTermination(1, TimeUnit.DAYS);

      logger.exiting(getClass().getName(), "parseLog");
      return jobStatus;
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error occured during parseLog", e);
      throw e;
    }

  }

}
