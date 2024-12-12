/*
 * Course: ICT4319
 * File: HelloHandler
 * Author: Instructor
 */
package edu.du.ict4310.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author schwartz
 */
public class HelloHandler implements Handler {

  private static final Logger LOGGER = LoggerFactory.getLogger(HelloHandler.class);

  @Override
  public void handle(Context ctx) throws Exception {
    String query = ctx.queryString();
    LOGGER.info(ctx.method() + " request on " + ctx.host() + " from " + ctx.req().getRemoteHost());
    LOGGER.info(String.format("Context: %s Query: %s", ctx.contextPath(), query));
    String name = ctx.queryParam("name");
    LOGGER.info(String.format("  Name is %s", name));
    if (name == null || name.isEmpty()) {
      name = "Unknown";
    }
    String result = "Hello from Javelin, " + name + "!";
    ctx.result(result);
  }

}
