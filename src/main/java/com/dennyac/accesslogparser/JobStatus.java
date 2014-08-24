package com.dennyac.accesslogparser;

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
  /**
   * @return the fileReadComplete
   */
  public synchronized boolean isFileReadComplete() {
    return fileReadComplete;
  }
  /**
   * @param fileReadComplete the fileReadComplete to set
   */
  public synchronized void setFileReadComplete(boolean fileReadComplete) {
    this.fileReadComplete = fileReadComplete;
  }
  /**
   * @return the ipUpdateComplete
   */
  public synchronized boolean isIpUpdateComplete() {
    return IpUpdateComplete;
  }
  /**
   * @param ipUpdateComplete the ipUpdateComplete to set
   */
  public synchronized void setIpUpdateComplete(boolean ipUpdateComplete) {
    IpUpdateComplete = ipUpdateComplete;
  }
  
}
