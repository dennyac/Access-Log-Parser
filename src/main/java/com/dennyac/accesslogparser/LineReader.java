package com.dennyac.accesslogparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The LineReader class reads from the access log file, and writes data to the intermediate queue if
 * the log entry is parsed successfully, otherwise updates the error message and writes data to the
 * serialization queue.
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014
 */
public class LineReader implements Runnable {
  private static final Logger logger = Logger.getLogger(LineReader.class.getName());

  private final BlockingQueue<LogEntry> intermediateQueue;
  private final JobStatus jobStatus;
  private final BlockingQueue<LogEntry> writeQueue;
  private BufferedReader br;

  /**
   * The constructor initializes the intermediate queue, write queue and the job status object
   * 
   * @param intermediateQueue queue of log entries for which ip details have to be updated
   * @param writeQueue queue of log entries to be serialized
   * @param input access log file to parse
   * @param jobStatus object which contains the status of the threads
   */
  public LineReader(BlockingQueue<LogEntry> intermediateQueue, BlockingQueue<LogEntry> writeQueue,
      String filePath, JobStatus jobStatus) throws FileNotFoundException {
    this.intermediateQueue = intermediateQueue;
    this.writeQueue = writeQueue;
    this.jobStatus = jobStatus;
    br = new BufferedReader(new FileReader(filePath));
  }

  /**
   * This thread will read from the file, parse each line and write 
   * the result to the intermediate queue/write queue, as long as the
   * input file has not been completely read
   */
  public void run() {
    logger.log(Level.INFO, "Entered LineReader run");
    String line;
    try {
      while ((line = br.readLine()) != null) {
        LogEntry logEntry = new LogEntry(line);
        if (logEntry.getErrorMessage() == null) {
          intermediateQueue.put(logEntry);
        } else {
          writeQueue.put(logEntry);
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
