package com.dennyac.accesslogparser;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;


/**
 * The LogSerialize class consumes log entries from the write queue,
 * and serializes these log entries to Avro file format
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014
 */
public class LogSerialize implements Runnable {
  private static final Logger logger = Logger.getLogger(LogSerialize.class.getName());
  private final BlockingQueue<LogEntry> writeQueue;
  private final JobStatus jobStatus;
  DatumWriter<LogEntryAvro> logDatumWriter = new SpecificDatumWriter<LogEntryAvro>(
      LogEntryAvro.class);
  DataFileWriter<LogEntryAvro> dataFileWriter = new DataFileWriter<LogEntryAvro>(logDatumWriter);

  /**
   * The constructor initializes the write queue, filePath for the avro file and the job status object
   * 
   * @param writeQueue  Queue of log entries to be serialized
   * @param filePath    Avro file to write to
   * @param jobStatus   Object which contains the status of the threads
   */
  public LogSerialize(BlockingQueue<LogEntry> writeQueue, String filePath, JobStatus jobStatus)
      throws IOException {
    this.writeQueue = writeQueue;
    this.jobStatus = jobStatus;
    dataFileWriter.create(LogEntryAvro.getClassSchema(), new File(filePath));
  }

  /**
   * This thread consumes log entries from the write queue, as long as it is
   * not empty, or either the lineReader/IpUpdate threads are still in progress 
   */
  public void run() {
    try {
      logger.log(Level.INFO, "Entered LogSerialize run method");
      while (!writeQueue.isEmpty() || !jobStatus.isIpUpdateComplete()
          || !jobStatus.isFileReadComplete()) {

        LogEntry logEntry = writeQueue.poll(1000, TimeUnit.MILLISECONDS);
        if (logEntry != null) {
          dataFileWriter.append(logEntry.getAvroObject());
        }
      }
      dataFileWriter.close();
      logger.log(Level.INFO, "Setting WriteComplete to true");
      jobStatus.setWriteComplete(true);
    } catch (IOException e1) {
      logger.log(Level.SEVERE, "LogSerialize encountered IOException", e1);
    } catch (InterruptedException e) {
      logger.log(Level.SEVERE, "LogSerialize encountered InterruptedException", e);
    }

  }

}
