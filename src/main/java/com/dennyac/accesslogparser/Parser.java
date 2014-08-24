package com.dennyac.accesslogparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {
  private static final Logger logger = Logger.getLogger(Parser.class.getName());

  // Mention JavaDoc
  public JobStatus parseLog(String inPath, String outPath) throws IOException {
    logger.entering(getClass().getName(), "parseLog");
    logger.log(Level.INFO, "Entered parseLog");
    BlockingQueue<LogEntry> intermediateQueue = new LinkedBlockingQueue<LogEntry>();
    BlockingQueue<LogEntry> writeQueue = new LinkedBlockingQueue<LogEntry>();
    JobStatus jobStatus = new JobStatus();
    logger.log(Level.INFO, "Instantiated blocking queues");
    try {
      //LineReader is responsible for reading from the file and writing to the intermediate queue
      LineReader lr = new LineReader(intermediateQueue,writeQueue,inPath,jobStatus);
      
      //IpUpdate reads from the intermediate queue and updates 
      IpUpdate ipUpd = new IpUpdate(intermediateQueue,writeQueue,jobStatus);
      LogSerialize ls = new LogSerialize(writeQueue,outPath,jobStatus);
      logger.log(Level.INFO, "Instantiated thread objects");
      new Thread(lr).start();
      new Thread(ipUpd).start();
      new Thread(ls).start();
      logger.log(Level.INFO, "Started all threads");
      
      //http://tutorials.jenkov.com/java-util-concurrent/arrayblockingqueue.html
      //BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>();
      //BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);
      //BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
      logger.exiting(getClass().getName(), "parseLog");
      return jobStatus;
    } catch (IOException e) {
      //To terminate threads if they are in progress
      logger.log(Level.INFO, "Reached IOException");
      logger.log(Level.SEVERE, "Error occured during parseLog", e);
      throw e;
    }
    
  }

  
}
