package com.dennyac.accesslogparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LineReader implements Runnable {
  private static final Logger logger = Logger.getLogger(LineReader.class.getName());
  
  private final BlockingQueue<LogEntry> intermediateQueue;
  private final JobStatus jobStatus;
  private final BlockingQueue<LogEntry> writeQueue;
  BufferedReader br;

  LineReader(BlockingQueue<LogEntry> intermediateQueue, BlockingQueue<LogEntry> writeQueue,
      String filePath, JobStatus jobStatus) throws FileNotFoundException {
    //logger.addHandler(new ConsoleHandler());
    this.intermediateQueue = intermediateQueue;
    this.writeQueue = writeQueue;
    this.jobStatus = jobStatus;
    br = new BufferedReader(new FileReader(filePath));
  }

  public void run() {
    logger.log(Level.INFO, "Entered LineReader run");
    String line;
    try {
      while ((line = br.readLine()) != null) {
        LogEntry logEntry = new LogEntry(line);
        // try {
//        logEntry.parse();
        if (logEntry.getErrorMessage() == null) {
          logger.log(Level.INFO, "Successfully parsed logEntry");
          intermediateQueue.put(logEntry);
          logger.log(Level.INFO, "Added logEntry to intermediate queue");
        } else {
          writeQueue.put(logEntry);
          logger.log(Level.INFO, "Added logEntry to writeQueue (Failed to parse)");
        }
      }
      logger.log(Level.INFO, "Setting FileReadComplete to true");
      jobStatus.setFileReadComplete(true);
      br.close();
    } catch (IOException e1) {
      logger.log(Level.SEVERE, "LineReader encountered IOException", e1);
    } catch (InterruptedException e) {
      logger.log(Level.SEVERE, "LineReader encountered InterruptedException", e);
    }

  }

}
