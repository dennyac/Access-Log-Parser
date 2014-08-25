/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.dennyac.accesslogparser;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class LogEntryAvro extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"LogEntryAvro\",\"namespace\":\"com.dennyac.accesslogparser\",\"fields\":[{\"name\":\"remoteHost\",\"type\":[\"string\",\"null\"]},{\"name\":\"timeStamp\",\"type\":[\"string\",\"null\"]},{\"name\":\"request\",\"type\":[\"string\",\"null\"]},{\"name\":\"statusCode\",\"type\":[\"int\",\"null\"]},{\"name\":\"organization\",\"type\":[\"string\",\"null\"]},{\"name\":\"latitude\",\"type\":[\"string\",\"null\"]},{\"name\":\"longitude\",\"type\":[\"string\",\"null\"]},{\"name\":\"ispName\",\"type\":[\"string\",\"null\"]},{\"name\":\"size\",\"type\":[\"int\",\"null\"]},{\"name\":\"referer\",\"type\":[\"string\",\"null\"]},{\"name\":\"userAgent\",\"type\":[\"string\",\"null\"]},{\"name\":\"logEntry\",\"type\":[\"string\",\"null\"]},{\"name\":\"errorMessage\",\"type\":[\"string\",\"null\"]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence remoteHost;
  @Deprecated public java.lang.CharSequence timeStamp;
  @Deprecated public java.lang.CharSequence request;
  @Deprecated public java.lang.Integer statusCode;
  @Deprecated public java.lang.CharSequence organization;
  @Deprecated public java.lang.CharSequence latitude;
  @Deprecated public java.lang.CharSequence longitude;
  @Deprecated public java.lang.CharSequence ispName;
  @Deprecated public java.lang.Integer size;
  @Deprecated public java.lang.CharSequence referer;
  @Deprecated public java.lang.CharSequence userAgent;
  @Deprecated public java.lang.CharSequence logEntry;
  @Deprecated public java.lang.CharSequence errorMessage;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public LogEntryAvro() {}

  /**
   * All-args constructor.
   */
  public LogEntryAvro(java.lang.CharSequence remoteHost, java.lang.CharSequence timeStamp, java.lang.CharSequence request, java.lang.Integer statusCode, java.lang.CharSequence organization, java.lang.CharSequence latitude, java.lang.CharSequence longitude, java.lang.CharSequence ispName, java.lang.Integer size, java.lang.CharSequence referer, java.lang.CharSequence userAgent, java.lang.CharSequence logEntry, java.lang.CharSequence errorMessage) {
    this.remoteHost = remoteHost;
    this.timeStamp = timeStamp;
    this.request = request;
    this.statusCode = statusCode;
    this.organization = organization;
    this.latitude = latitude;
    this.longitude = longitude;
    this.ispName = ispName;
    this.size = size;
    this.referer = referer;
    this.userAgent = userAgent;
    this.logEntry = logEntry;
    this.errorMessage = errorMessage;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return remoteHost;
    case 1: return timeStamp;
    case 2: return request;
    case 3: return statusCode;
    case 4: return organization;
    case 5: return latitude;
    case 6: return longitude;
    case 7: return ispName;
    case 8: return size;
    case 9: return referer;
    case 10: return userAgent;
    case 11: return logEntry;
    case 12: return errorMessage;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: remoteHost = (java.lang.CharSequence)value$; break;
    case 1: timeStamp = (java.lang.CharSequence)value$; break;
    case 2: request = (java.lang.CharSequence)value$; break;
    case 3: statusCode = (java.lang.Integer)value$; break;
    case 4: organization = (java.lang.CharSequence)value$; break;
    case 5: latitude = (java.lang.CharSequence)value$; break;
    case 6: longitude = (java.lang.CharSequence)value$; break;
    case 7: ispName = (java.lang.CharSequence)value$; break;
    case 8: size = (java.lang.Integer)value$; break;
    case 9: referer = (java.lang.CharSequence)value$; break;
    case 10: userAgent = (java.lang.CharSequence)value$; break;
    case 11: logEntry = (java.lang.CharSequence)value$; break;
    case 12: errorMessage = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'remoteHost' field.
   */
  public java.lang.CharSequence getRemoteHost() {
    return remoteHost;
  }

  /**
   * Sets the value of the 'remoteHost' field.
   * @param value the value to set.
   */
  public void setRemoteHost(java.lang.CharSequence value) {
    this.remoteHost = value;
  }

  /**
   * Gets the value of the 'timeStamp' field.
   */
  public java.lang.CharSequence getTimeStamp() {
    return timeStamp;
  }

  /**
   * Sets the value of the 'timeStamp' field.
   * @param value the value to set.
   */
  public void setTimeStamp(java.lang.CharSequence value) {
    this.timeStamp = value;
  }

  /**
   * Gets the value of the 'request' field.
   */
  public java.lang.CharSequence getRequest() {
    return request;
  }

  /**
   * Sets the value of the 'request' field.
   * @param value the value to set.
   */
  public void setRequest(java.lang.CharSequence value) {
    this.request = value;
  }

  /**
   * Gets the value of the 'statusCode' field.
   */
  public java.lang.Integer getStatusCode() {
    return statusCode;
  }

  /**
   * Sets the value of the 'statusCode' field.
   * @param value the value to set.
   */
  public void setStatusCode(java.lang.Integer value) {
    this.statusCode = value;
  }

  /**
   * Gets the value of the 'organization' field.
   */
  public java.lang.CharSequence getOrganization() {
    return organization;
  }

  /**
   * Sets the value of the 'organization' field.
   * @param value the value to set.
   */
  public void setOrganization(java.lang.CharSequence value) {
    this.organization = value;
  }

  /**
   * Gets the value of the 'latitude' field.
   */
  public java.lang.CharSequence getLatitude() {
    return latitude;
  }

  /**
   * Sets the value of the 'latitude' field.
   * @param value the value to set.
   */
  public void setLatitude(java.lang.CharSequence value) {
    this.latitude = value;
  }

  /**
   * Gets the value of the 'longitude' field.
   */
  public java.lang.CharSequence getLongitude() {
    return longitude;
  }

  /**
   * Sets the value of the 'longitude' field.
   * @param value the value to set.
   */
  public void setLongitude(java.lang.CharSequence value) {
    this.longitude = value;
  }

  /**
   * Gets the value of the 'ispName' field.
   */
  public java.lang.CharSequence getIspName() {
    return ispName;
  }

  /**
   * Sets the value of the 'ispName' field.
   * @param value the value to set.
   */
  public void setIspName(java.lang.CharSequence value) {
    this.ispName = value;
  }

  /**
   * Gets the value of the 'size' field.
   */
  public java.lang.Integer getSize() {
    return size;
  }

  /**
   * Sets the value of the 'size' field.
   * @param value the value to set.
   */
  public void setSize(java.lang.Integer value) {
    this.size = value;
  }

  /**
   * Gets the value of the 'referer' field.
   */
  public java.lang.CharSequence getReferer() {
    return referer;
  }

  /**
   * Sets the value of the 'referer' field.
   * @param value the value to set.
   */
  public void setReferer(java.lang.CharSequence value) {
    this.referer = value;
  }

  /**
   * Gets the value of the 'userAgent' field.
   */
  public java.lang.CharSequence getUserAgent() {
    return userAgent;
  }

  /**
   * Sets the value of the 'userAgent' field.
   * @param value the value to set.
   */
  public void setUserAgent(java.lang.CharSequence value) {
    this.userAgent = value;
  }

  /**
   * Gets the value of the 'logEntry' field.
   */
  public java.lang.CharSequence getLogEntry() {
    return logEntry;
  }

  /**
   * Sets the value of the 'logEntry' field.
   * @param value the value to set.
   */
  public void setLogEntry(java.lang.CharSequence value) {
    this.logEntry = value;
  }

  /**
   * Gets the value of the 'errorMessage' field.
   */
  public java.lang.CharSequence getErrorMessage() {
    return errorMessage;
  }

  /**
   * Sets the value of the 'errorMessage' field.
   * @param value the value to set.
   */
  public void setErrorMessage(java.lang.CharSequence value) {
    this.errorMessage = value;
  }

  /** Creates a new LogEntryAvro RecordBuilder */
  public static com.dennyac.accesslogparser.LogEntryAvro.Builder newBuilder() {
    return new com.dennyac.accesslogparser.LogEntryAvro.Builder();
  }
  
  /** Creates a new LogEntryAvro RecordBuilder by copying an existing Builder */
  public static com.dennyac.accesslogparser.LogEntryAvro.Builder newBuilder(com.dennyac.accesslogparser.LogEntryAvro.Builder other) {
    return new com.dennyac.accesslogparser.LogEntryAvro.Builder(other);
  }
  
  /** Creates a new LogEntryAvro RecordBuilder by copying an existing LogEntryAvro instance */
  public static com.dennyac.accesslogparser.LogEntryAvro.Builder newBuilder(com.dennyac.accesslogparser.LogEntryAvro other) {
    return new com.dennyac.accesslogparser.LogEntryAvro.Builder(other);
  }
  
  /**
   * RecordBuilder for LogEntryAvro instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<LogEntryAvro>
    implements org.apache.avro.data.RecordBuilder<LogEntryAvro> {

    private java.lang.CharSequence remoteHost;
    private java.lang.CharSequence timeStamp;
    private java.lang.CharSequence request;
    private java.lang.Integer statusCode;
    private java.lang.CharSequence organization;
    private java.lang.CharSequence latitude;
    private java.lang.CharSequence longitude;
    private java.lang.CharSequence ispName;
    private java.lang.Integer size;
    private java.lang.CharSequence referer;
    private java.lang.CharSequence userAgent;
    private java.lang.CharSequence logEntry;
    private java.lang.CharSequence errorMessage;

    /** Creates a new Builder */
    private Builder() {
      super(com.dennyac.accesslogparser.LogEntryAvro.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.dennyac.accesslogparser.LogEntryAvro.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.remoteHost)) {
        this.remoteHost = data().deepCopy(fields()[0].schema(), other.remoteHost);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timeStamp)) {
        this.timeStamp = data().deepCopy(fields()[1].schema(), other.timeStamp);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.request)) {
        this.request = data().deepCopy(fields()[2].schema(), other.request);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.statusCode)) {
        this.statusCode = data().deepCopy(fields()[3].schema(), other.statusCode);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.organization)) {
        this.organization = data().deepCopy(fields()[4].schema(), other.organization);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.latitude)) {
        this.latitude = data().deepCopy(fields()[5].schema(), other.latitude);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.longitude)) {
        this.longitude = data().deepCopy(fields()[6].schema(), other.longitude);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.ispName)) {
        this.ispName = data().deepCopy(fields()[7].schema(), other.ispName);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.size)) {
        this.size = data().deepCopy(fields()[8].schema(), other.size);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.referer)) {
        this.referer = data().deepCopy(fields()[9].schema(), other.referer);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.userAgent)) {
        this.userAgent = data().deepCopy(fields()[10].schema(), other.userAgent);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.logEntry)) {
        this.logEntry = data().deepCopy(fields()[11].schema(), other.logEntry);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other.errorMessage)) {
        this.errorMessage = data().deepCopy(fields()[12].schema(), other.errorMessage);
        fieldSetFlags()[12] = true;
      }
    }
    
    /** Creates a Builder by copying an existing LogEntryAvro instance */
    private Builder(com.dennyac.accesslogparser.LogEntryAvro other) {
            super(com.dennyac.accesslogparser.LogEntryAvro.SCHEMA$);
      if (isValidValue(fields()[0], other.remoteHost)) {
        this.remoteHost = data().deepCopy(fields()[0].schema(), other.remoteHost);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.timeStamp)) {
        this.timeStamp = data().deepCopy(fields()[1].schema(), other.timeStamp);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.request)) {
        this.request = data().deepCopy(fields()[2].schema(), other.request);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.statusCode)) {
        this.statusCode = data().deepCopy(fields()[3].schema(), other.statusCode);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.organization)) {
        this.organization = data().deepCopy(fields()[4].schema(), other.organization);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.latitude)) {
        this.latitude = data().deepCopy(fields()[5].schema(), other.latitude);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.longitude)) {
        this.longitude = data().deepCopy(fields()[6].schema(), other.longitude);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.ispName)) {
        this.ispName = data().deepCopy(fields()[7].schema(), other.ispName);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.size)) {
        this.size = data().deepCopy(fields()[8].schema(), other.size);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.referer)) {
        this.referer = data().deepCopy(fields()[9].schema(), other.referer);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.userAgent)) {
        this.userAgent = data().deepCopy(fields()[10].schema(), other.userAgent);
        fieldSetFlags()[10] = true;
      }
      if (isValidValue(fields()[11], other.logEntry)) {
        this.logEntry = data().deepCopy(fields()[11].schema(), other.logEntry);
        fieldSetFlags()[11] = true;
      }
      if (isValidValue(fields()[12], other.errorMessage)) {
        this.errorMessage = data().deepCopy(fields()[12].schema(), other.errorMessage);
        fieldSetFlags()[12] = true;
      }
    }

    /** Gets the value of the 'remoteHost' field */
    public java.lang.CharSequence getRemoteHost() {
      return remoteHost;
    }
    
    /** Sets the value of the 'remoteHost' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setRemoteHost(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.remoteHost = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'remoteHost' field has been set */
    public boolean hasRemoteHost() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'remoteHost' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearRemoteHost() {
      remoteHost = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'timeStamp' field */
    public java.lang.CharSequence getTimeStamp() {
      return timeStamp;
    }
    
    /** Sets the value of the 'timeStamp' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setTimeStamp(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.timeStamp = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'timeStamp' field has been set */
    public boolean hasTimeStamp() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'timeStamp' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearTimeStamp() {
      timeStamp = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'request' field */
    public java.lang.CharSequence getRequest() {
      return request;
    }
    
    /** Sets the value of the 'request' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setRequest(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.request = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'request' field has been set */
    public boolean hasRequest() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'request' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearRequest() {
      request = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'statusCode' field */
    public java.lang.Integer getStatusCode() {
      return statusCode;
    }
    
    /** Sets the value of the 'statusCode' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setStatusCode(java.lang.Integer value) {
      validate(fields()[3], value);
      this.statusCode = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'statusCode' field has been set */
    public boolean hasStatusCode() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'statusCode' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearStatusCode() {
      statusCode = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'organization' field */
    public java.lang.CharSequence getOrganization() {
      return organization;
    }
    
    /** Sets the value of the 'organization' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setOrganization(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.organization = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'organization' field has been set */
    public boolean hasOrganization() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'organization' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearOrganization() {
      organization = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'latitude' field */
    public java.lang.CharSequence getLatitude() {
      return latitude;
    }
    
    /** Sets the value of the 'latitude' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setLatitude(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.latitude = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'latitude' field has been set */
    public boolean hasLatitude() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'latitude' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearLatitude() {
      latitude = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'longitude' field */
    public java.lang.CharSequence getLongitude() {
      return longitude;
    }
    
    /** Sets the value of the 'longitude' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setLongitude(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.longitude = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'longitude' field has been set */
    public boolean hasLongitude() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'longitude' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearLongitude() {
      longitude = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'ispName' field */
    public java.lang.CharSequence getIspName() {
      return ispName;
    }
    
    /** Sets the value of the 'ispName' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setIspName(java.lang.CharSequence value) {
      validate(fields()[7], value);
      this.ispName = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'ispName' field has been set */
    public boolean hasIspName() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'ispName' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearIspName() {
      ispName = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'size' field */
    public java.lang.Integer getSize() {
      return size;
    }
    
    /** Sets the value of the 'size' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setSize(java.lang.Integer value) {
      validate(fields()[8], value);
      this.size = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'size' field has been set */
    public boolean hasSize() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'size' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearSize() {
      size = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /** Gets the value of the 'referer' field */
    public java.lang.CharSequence getReferer() {
      return referer;
    }
    
    /** Sets the value of the 'referer' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setReferer(java.lang.CharSequence value) {
      validate(fields()[9], value);
      this.referer = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the 'referer' field has been set */
    public boolean hasReferer() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the 'referer' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearReferer() {
      referer = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /** Gets the value of the 'userAgent' field */
    public java.lang.CharSequence getUserAgent() {
      return userAgent;
    }
    
    /** Sets the value of the 'userAgent' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setUserAgent(java.lang.CharSequence value) {
      validate(fields()[10], value);
      this.userAgent = value;
      fieldSetFlags()[10] = true;
      return this; 
    }
    
    /** Checks whether the 'userAgent' field has been set */
    public boolean hasUserAgent() {
      return fieldSetFlags()[10];
    }
    
    /** Clears the value of the 'userAgent' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearUserAgent() {
      userAgent = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    /** Gets the value of the 'logEntry' field */
    public java.lang.CharSequence getLogEntry() {
      return logEntry;
    }
    
    /** Sets the value of the 'logEntry' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setLogEntry(java.lang.CharSequence value) {
      validate(fields()[11], value);
      this.logEntry = value;
      fieldSetFlags()[11] = true;
      return this; 
    }
    
    /** Checks whether the 'logEntry' field has been set */
    public boolean hasLogEntry() {
      return fieldSetFlags()[11];
    }
    
    /** Clears the value of the 'logEntry' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearLogEntry() {
      logEntry = null;
      fieldSetFlags()[11] = false;
      return this;
    }

    /** Gets the value of the 'errorMessage' field */
    public java.lang.CharSequence getErrorMessage() {
      return errorMessage;
    }
    
    /** Sets the value of the 'errorMessage' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder setErrorMessage(java.lang.CharSequence value) {
      validate(fields()[12], value);
      this.errorMessage = value;
      fieldSetFlags()[12] = true;
      return this; 
    }
    
    /** Checks whether the 'errorMessage' field has been set */
    public boolean hasErrorMessage() {
      return fieldSetFlags()[12];
    }
    
    /** Clears the value of the 'errorMessage' field */
    public com.dennyac.accesslogparser.LogEntryAvro.Builder clearErrorMessage() {
      errorMessage = null;
      fieldSetFlags()[12] = false;
      return this;
    }

    @Override
    public LogEntryAvro build() {
      try {
        LogEntryAvro record = new LogEntryAvro();
        record.remoteHost = fieldSetFlags()[0] ? this.remoteHost : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.timeStamp = fieldSetFlags()[1] ? this.timeStamp : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.request = fieldSetFlags()[2] ? this.request : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.statusCode = fieldSetFlags()[3] ? this.statusCode : (java.lang.Integer) defaultValue(fields()[3]);
        record.organization = fieldSetFlags()[4] ? this.organization : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.latitude = fieldSetFlags()[5] ? this.latitude : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.longitude = fieldSetFlags()[6] ? this.longitude : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.ispName = fieldSetFlags()[7] ? this.ispName : (java.lang.CharSequence) defaultValue(fields()[7]);
        record.size = fieldSetFlags()[8] ? this.size : (java.lang.Integer) defaultValue(fields()[8]);
        record.referer = fieldSetFlags()[9] ? this.referer : (java.lang.CharSequence) defaultValue(fields()[9]);
        record.userAgent = fieldSetFlags()[10] ? this.userAgent : (java.lang.CharSequence) defaultValue(fields()[10]);
        record.logEntry = fieldSetFlags()[11] ? this.logEntry : (java.lang.CharSequence) defaultValue(fields()[11]);
        record.errorMessage = fieldSetFlags()[12] ? this.errorMessage : (java.lang.CharSequence) defaultValue(fields()[12]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}