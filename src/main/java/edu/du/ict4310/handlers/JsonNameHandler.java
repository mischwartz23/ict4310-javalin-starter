/*
 * Course: ICT4310
 * File: JsonNameHandler.java
 * Author: Instructor
 */
package edu.du.ict4310.handlers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author schwartz
 */
public class JsonNameHandler implements Handler {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonNameHandler.class);

  @Override
  public void handle(Context context) throws Exception {
    LOGGER.info(context.method() + " request on " + context.host() + " from " + context.req().
        getRemoteHost());
    String body = context.body();
    LOGGER.info("Body: " + body);
    Gson gson = new Gson();
    Name name = gson.fromJson(body, Name.class);
    NameResult result = new NameResult(name.name(), LocalDateTime.now().toString());
    LOGGER.info("Name: " + name);
    LOGGER.info("Name result: " + result);
    context.result(gson.toJson(result));
  }

  // These records are provided to ease converting Strings to JSON and vice versa.
  record Name(String name) {  };
  record NameResult (String name, String timestamp) {  };
}
