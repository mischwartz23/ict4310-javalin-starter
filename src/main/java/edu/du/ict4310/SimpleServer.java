/*
 * Course: ICT4310
 * File: SimpleServer
 * Author: Instructor
 */
package edu.du.ict4310;

import edu.du.ict4310.handlers.GreetHandler;
import edu.du.ict4310.handlers.HelloHandler;
import edu.du.ict4310.handlers.JsonNameHandler;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinThymeleaf;
import java.time.LocalDate;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple HTTP server Responds to GET /, GET name (urlencoded), POST name path, POST JSON
 *
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
    this.jApp = Javalin.create(config -> {
      config.staticFiles.add("/public", Location.CLASSPATH);
      config.bundledPlugins.enableCors(cors -> {
        cors.addRule(it -> {
          it.anyHost();
        });
      });
      // Register a renderer
      config.fileRenderer(new JavalinThymeleaf());
    });
  }

  public static SimpleServer create() {
    return create(PORT);
  }

  public static SimpleServer create(int port) {
    SimpleServer app = new SimpleServer(port);
    app.addRoutes();
    return app;
  }

  // Server start and stop methods
  public void start() {
    jApp.start(port);
  }

  public void stop() {
    jApp.stop();
  }

  public void addRoutes() {
    // jApp.get("/", new HelloHandler()); // add GET for / or GET /?name=xxxx
    jApp.get("/hello", new HelloHandler()); // same as root

    // Add a GET and a POST route with a name path. 
    Handler handler = new GreetHandler();
    jApp.get("greet/{name}", handler);
    jApp.post("/greet/{name}", handler);

    // Add a POST route with a JSON document ({ "name" : "value"})
    jApp.post("/name", new JsonNameHandler());

    // Add a template route (using arrow notation)
    jApp.get("/", ctx -> {
      Map<String, Object> model = Map.of("date", LocalDate.now());
      ctx.render("templates/indexTemplate.html", model);
    });
  }

}
