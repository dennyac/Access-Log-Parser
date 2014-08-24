package com.dennyac.accesslogparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogSerialize implements Runnable {
  private static final Logger logger = Logger.getLogger(LogSerialize.class.getName());
  private final BlockingQueue<LogEntry> writeQueue;
  private final File file;
  private final FileWriter fw;
  private final BufferedWriter bw;
  private final JobStatus jobStatus;

  public LogSerialize(BlockingQueue<LogEntry> writeQueue, String filePath, JobStatus jobStatus) throws IOException {
    //logger.addHandler(new ConsoleHandler());
    this.writeQueue = writeQueue;
    this.jobStatus = jobStatus;
    file = new File(filePath);
    // if file doesnt exists, then create it
    if (!file.exists()) {
      logger.log(Level.INFO, "File doesn't exist. Creating..");
      file.createNewFile();
      logger.log(Level.INFO, "File created");
    }
    logger.log(Level.INFO, "Initializing fileWriters");
    fw = new FileWriter(file.getAbsoluteFile());
    bw = new BufferedWriter(fw);
    logger.log(Level.INFO, "Initialized fileWriters");
    
    //fileRead should be complete
    //update should be complete
    //writequeue should be empty
  }

  public void run() {
    try {
      logger.log(Level.INFO, "Entered LogSerialize run method");
      logger.log(Level.INFO, "jobStatus.isFileReadComplete() is " + jobStatus.isIpUpdateComplete());
      logger.log(Level.INFO, "writeQueue.size() is " + writeQueue.size());
      while (!writeQueue.isEmpty() || !jobStatus.isIpUpdateComplete() || !jobStatus.isFileReadComplete()) {
        logger.log(Level.INFO, "(Inside) jobStatus.isFileReadComplete() is " + jobStatus.isIpUpdateComplete());
        logger.log(Level.INFO, "(Inside) writeQueue.size() is " + writeQueue.size());
        LogEntry logEntry = writeQueue.poll(1000, TimeUnit.MILLISECONDS);  //I guess it waits over here
        if(logEntry != null){
          logger.log(Level.INFO, "Adding log entry to file");
          bw.write(logEntry.toString());
          bw.newLine();
          logger.log(Level.INFO, "Added log entry to file");
        }
        
      }
      jobStatus.setWriteComplete(true);
    } catch (IOException e1) {
      logger.log(Level.SEVERE, "LogSerialize encountered IOException", e1);
    } catch (InterruptedException e) {
      logger.log(Level.SEVERE, "LogSerialize encountered InterruptedException", e);
    } finally {
      try {
        bw.close();
      } catch (IOException e) {
        logger.log(Level.SEVERE, "LogSerialize encountered IOException", e);
      }

    }

  }

}
