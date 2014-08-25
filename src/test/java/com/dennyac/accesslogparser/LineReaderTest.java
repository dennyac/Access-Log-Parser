package com.dennyac.accesslogparser;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LineReaderTest {

  @Before
  public void initializeData() {

    // Creating test file
    try {
      File file = new File("test.log");
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:50 -0800] \"GET /svds.com/rockandroll HTTP/1.1\" 301 241 \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:50 -0800] \"GET /svds.com/rockandroll/ HTTP/1.1\" 200 8301 \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/js/libs/modernizr-2.6.2.min.js HTTP/1.1\" 200 8768 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/img/logo.png HTTP/1.1\" 200 13728 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/js/libs/gumby.js HTTP/1.1\" 200 6464 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/js/libs/ui/gumby.fittext.js HTTP/1.1\" 200 2893 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/js/libs/ui/gumby.retina.js HTTP/1.1\" 200 1912 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/js/libs/ui/gumby.fixed.js HTTP/1.1\" 200 6171 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/js/libs/ui/gumby.skiplink.js HTTP/1.1\" 200 3727 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.write("198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/js/libs/ui/gumby.toggleswitch.js HTTP/1.1\" 200 6673 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"\n");
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void LineReaderProducer() {
    BlockingQueue<LogEntry> intermediateQueue = new LinkedBlockingQueue<LogEntry>();
    BlockingQueue<LogEntry> writeQueue = new LinkedBlockingQueue<LogEntry>();
    JobStatus status = new JobStatus();
    try {
      LineReader lr = new LineReader(intermediateQueue, writeQueue, "test.log", status);
      new Thread(lr).start();
      while (!status.isFileReadComplete()) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
      }
      assertTrue("Intermediate Queue should be empty", intermediateQueue.size() == 10);
      assertTrue("File Read Complete flag should be set", status.isFileReadComplete());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  // Deleting test file and generated output files
  @After
  public void unInitialize() {
    new File("test.log").delete();
  }

}
