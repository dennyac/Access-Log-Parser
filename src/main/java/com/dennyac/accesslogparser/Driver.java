package com.dennyac.accesslogparser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Driver class is responsible for parsing the access log, 
 * fetches the ip related details and stores the data
 * in Avro file format
 * 
 * @author Denny Abraham Cheriyan
 * @version 1.0, Aug 2014 
 * Example Usage - 
 * java -cp acesslog-parser-0.0.1-SNAPSHOT-jar-with-dependencies.jar
 *          com.dennyac.accesslogparser.Driver svds/datasets/access.log log.avro
 */
public class Driver {
  private static final Logger logger = Logger.getLogger(Driver.class.getName());

  public static void main(String[] args) throws IOException {

    if (args.length != 2) {
      System.err.println("Incorrect number of arguments");
      System.err.println("Usage: input_path output_path");
      System.exit(1);
    }

    String inFile = args[0];
    String outFile = args[1];
    logger.log(Level.INFO, "Started Driver program");

    Parser accessLogParser = new Parser();
    logger.log(Level.INFO, "Instantiated Parser Object");

    accessLogParser.parseLog(inFile, outFile);
    logger.log(Level.INFO, "Completed parsing access log");
  }

}