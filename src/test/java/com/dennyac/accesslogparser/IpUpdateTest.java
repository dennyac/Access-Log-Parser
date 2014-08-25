package com.dennyac.accesslogparser;

import static org.junit.Assert.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Test;


public class IpUpdateTest {

  @Test
  public void updateIpDetailsTest() {

    BlockingQueue<LogEntry> intermediateQueue = new LinkedBlockingQueue<LogEntry>();
    BlockingQueue<LogEntry> writeQueue = new LinkedBlockingQueue<LogEntry>();
    JobStatus status = new JobStatus();
    IpUpdate ipUpdate = new IpUpdate(intermediateQueue, writeQueue, status);

    LogEntry log =
        new LogEntry(
            "198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/js/libs/modernizr-2.6.2.min.js HTTP/1.1\" 200 8768 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"");
    ipUpdate.updateIpDetails(log);
    assertEquals("Checking latitude", "37.555", log.getLatitude());
    assertEquals("Checking longitude", "-122.2687", log.getLongitude());
    assertEquals("Checking organization", "Comcast Business Communications", log.getOrganization());
    assertEquals("Checking IspName", "Comcast Business Communications", log.getIspName());
  }

  @Test
  public void ipUpdateConsumeTest() {
    BlockingQueue<LogEntry> intermediateQueue = new LinkedBlockingQueue<LogEntry>();
    intermediateQueue
        .add(new LogEntry(
            "198.0.200.105 - - [14/Jan/2014:09:36:50 -0800] \"GET /svds.com/rockandroll HTTP/1.1\" 301 241 \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\""));
    intermediateQueue
        .add(new LogEntry(
            "198.0.200.105 - - [14/Jan/2014:09:36:50 -0800] \"GET /svds.com/rockandroll/ HTTP/1.1\" 200 8301 \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\""));
    BlockingQueue<LogEntry> writeQueue = new LinkedBlockingQueue<LogEntry>();
    JobStatus status = new JobStatus();
    IpUpdate ipUpdate = new IpUpdate(intermediateQueue, writeQueue, status);
    new Thread(ipUpdate).start();
    status.setFileReadComplete(true);
    while(!status.isIpUpdateComplete()){
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    assertTrue("Intermediate Queue should be empty",intermediateQueue.size()==0);
    assertTrue("Write Queue should have two entries",writeQueue.size()==2);
    assertTrue("Update complete flag should be set",status.isIpUpdateComplete());
    
  }

}
