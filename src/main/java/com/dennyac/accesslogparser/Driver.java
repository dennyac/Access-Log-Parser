package com.dennyac.accesslogparser;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



// Basic logging like when the parser started logging, when it ended, etc

public class Driver {
  private static final Logger logger = Logger.getLogger(Driver.class.getName());
  
  public static void main(String[] args) throws IOException{
    //logger.addHandler(new ConsoleHandler());
    // if the arguments are not specified properly exit. define usage function
//    if (args.length != 2) {
//      System.err.println("Incorrect number of arguments");
//      System.err.println("Usage: input_path output_path");
//      System.exit(1);
//    }
//    String inFile = args[0];
//    String outFile = args[1];
    logger.log(Level.INFO, "Started Driver program");
    //java -cp acesslog-parser-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.dennyac.accesslogparser.Driver /home/dennyac/java/workspace/acesslog-parser/access_subset.log /home/dennyac/java/workspace/acesslog-parser/access_out.log
     Parser accessLogParser = new Parser();
     logger.log(Level.INFO, "Instantiated Parser Object");
     accessLogParser.parseLog("/home/dennyac/java/workspace/acesslog-parser/access_subset.log","/home/dennyac/java/workspace/acesslog-parser/access_out.log");
  }

}