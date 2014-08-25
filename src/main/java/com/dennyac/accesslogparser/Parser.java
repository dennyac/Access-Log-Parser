package com.dennyac.accesslogparser;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Parser class instantiates the shared queues, threads for reading from the file, \ updating
 * the ip details, and serializing to Avro file format
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014
 */
public class Parser {
  private static final Logger logger = Logger.getLogger(Parser.class.getName());

  /**
   * Takes an ip address as input and returns a Json object that contains the ip details
   * @param inPath input access log file to parse
   * @param outPath output Avro file to write to
   * @return JobStatus object containing status of threads
   * @exception IOException
   */
  public JobStatus parseLog(String inPath, String outPath) throws IOException {
    logger.entering(getClass().getName(), "parseLog");
    BlockingQueue<LogEntry> intermediateQueue = new LinkedBlockingQueue<LogEntry>();
    BlockingQueue<LogEntry> writeQueue = new LinkedBlockingQueue<LogEntry>();
    JobStatus jobStatus = new JobStatus();
    try {
      // LineReader is responsible for reading from the file and writing to the intermediate queue
      LineReader lr = new LineReader(intermediateQueue, writeQueue, inPath, jobStatus);

      // IpUpdate reads from the intermediate queue and updates
      IpUpdate ipUpd = new IpUpdate(intermediateQueue, writeQueue, jobStatus);
      LogSerialize ls = new LogSerialize(writeQueue, outPath, jobStatus);

      new Thread(lr).start();
      new Thread(ipUpd).start();
      new Thread(ls).start();

      logger.exiting(getClass().getName(), "parseLog");
      return jobStatus;
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error occured during parseLog", e);
      throw e;
    }

  }

}
