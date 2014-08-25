package com.dennyac.accesslogparser;

/**
 * The JobStatus class contains flags for sending signals across threads.
 * 
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014
 */
public class JobStatus {
  private boolean fileReadComplete;
  private boolean IpUpdateComplete;
  private boolean writeComplete;
  
  public synchronized boolean isWriteComplete() {
    return writeComplete;
  }
  public synchronized void setWriteComplete(boolean writeComplete) {
    this.writeComplete = writeComplete;
  }

  public synchronized boolean isFileReadComplete() {
    return fileReadComplete;
  }
 
  public synchronized void setFileReadComplete(boolean fileReadComplete) {
    this.fileReadComplete = fileReadComplete;
  }

  public synchronized boolean isIpUpdateComplete() {
    return IpUpdateComplete;
  }

  public synchronized void setIpUpdateComplete(boolean ipUpdateComplete) {
    IpUpdateComplete = ipUpdateComplete;
  }
  
}
