package com.dennyac.accesslogparser;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogEntryTest {

  @Test
  public void parseLogTest() {
    LogEntry log= new LogEntry("198.0.200.105 - - [14/Jan/2014:09:36:51 -0800] \"GET /svds.com/rockandroll/js/libs/modernizr-2.6.2.min.js HTTP/1.1\" 200 8768 \"http://www.svds.com/rockandroll/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36\"");
    assertTrue("Parse Log Entry",log.getErrorMessage()==null);
    assertEquals("Checking remote host","198.0.200.105",log.getRemoteHost());
  }

}
