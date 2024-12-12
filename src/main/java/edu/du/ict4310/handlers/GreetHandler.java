/*
 * Course: ICT4310
 * File: GreetHandler
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
public class GreetHandler implements Handler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GreetHandler.class);

  @Override
  public void handle(Context context) throws Exception {
    LOGGER.info(context.method() + " request on " + context.host()
        + " " + context.req().getContextPath()
        + "- from " + context.req().getRemoteHost());
    String result = String.format("Hello %s!", context.pathParam("name"));
    LOGGER.info("Path: " + context.path() + " Params: " + context.pathParamMap());
    context.result(result);
  }

}
