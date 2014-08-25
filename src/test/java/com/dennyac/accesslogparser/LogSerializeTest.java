package com.dennyac.accesslogparser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.After;
import org.junit.Test;

public class LogSerializeTest {

  @Test
  public void LogSerializeConsumer() {
    BlockingQueue<LogEntry> writeQueue = new LinkedBlockingQueue<LogEntry>();
    writeQueue
        .add(new LogEntry(
            "198.0.200.105 - - [14/Jan/2014:09:36:50 -0800] \"GET /svds.com/rockandroll HTTP/1.1\" 301 241 \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\""));
    writeQueue
        .add(new LogEntry(
            "198.0.200.105 - - [14/Jan/2014:09:36:50 -0800] \"GET /svds.com/rockandroll/ HTTP/1.1\" 200 8301 \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\""));

    JobStatus status = new JobStatus();
    try {
      LogSerialize lr = new LogSerialize(writeQueue, "test_out.log", status);
      new Thread(lr).start();
      status.setFileReadComplete(true);
      status.setIpUpdateComplete(true);
      while (!status.isWriteComplete()) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }
      assertTrue("Intermediate Queue should be empty", writeQueue.size() == 0);
      File f = new File("test_out.log");
      assertTrue("Checking whether the output file exists", f.exists() && !f.isDirectory());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Deleting test file and generated output files
  @After
  public void unInitialize() {
    new File("test_out.log").delete();
  }

}
