/*
 * Course: ICT4310
 * File: SimpleServer
 * Author: Instructor
 */
package edu.du.ict4310.initial;

import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple http server, responds to GET on /
 * @author schwartz
 */
public class SimpleServer {

  private static final int PORT = 4310; // Default port number
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServer.class);

  private int port;      // This apps port
  private Javalin jApp;  // This apps Javalin instance

  private static int getPort(String[] args) {
    int port = PORT;

    if (args.length > 0 && !args[0].isBlank()) {
      int newPort = Integer.parseInt(args[0]);

      if (newPort > 1 && newPort < 65000) {
        LOGGER.info("Server port " + newPort + " requested");
        port = newPort;
      }

    }
    return port;
  }

  public static void main(String[] args) {
    int startPort = getPort(args);
    SimpleServer server = SimpleServer.create(startPort);
    server.start();
  }

  // Contructors / Builders
  private SimpleServer(int port) {
    this.port = port;
    this.jApp = Javalin.create();
  }

  public static SimpleServer create() {
    return create(PORT);
  }

  public static SimpleServer create(int port) {
    SimpleServer app = new SimpleServer(port);
    return app;
  }

  // Server start and stop methods
  public void start() {
    jApp.start(port);
  }

  public void stop() {
    jApp.stop();
  }
}
