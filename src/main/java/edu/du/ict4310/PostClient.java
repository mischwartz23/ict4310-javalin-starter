/*
 * Course: ICT4310
 * File: PostClient
 * Author: Instructor
 */
package edu.du.ict4310;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

/**
 *
 * @author michael
 */
public class PostClient {

  static int PORT = 4310;
  static final String PATH = "/";
  static final String PATHPARAM = "Elvis";

  private final HttpClient client;

  public PostClient() {
    client = HttpClient.newHttpClient();
  }

  public HttpResponse getResponse(String uriString, String dataString) throws IOException,
      InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .POST(BodyPublishers.ofString(dataString))
        .uri(URI.create(uriString))
        .build();

    HttpResponse<String> response
        = client.send(request, BodyHandlers.ofString());
    
    return response;
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    String uriString = "http://localhost" + ":" + PORT + PATH + "greet/" + PATHPARAM;
    String dataString = "";
    
    PostClient client = new PostClient();
    HttpResponse response = client.getResponse(uriString, dataString);
    
    System.out.println("Status is " + response.statusCode());

    String result = (String)response.body();
    System.out.println("Result is: " + result);
  }

}
