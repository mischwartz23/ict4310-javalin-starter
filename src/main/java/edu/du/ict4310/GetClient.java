/*
 * Course: ICT4310
 * File: GetClient
 * Author: Instructor
 */
package edu.du.ict4310;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author michael
 */
public class GetClient {

  static int PORT = 4310;
  static final String PATH = "/";

  private final HttpClient client;

  public GetClient() {
    client = HttpClient.newHttpClient();
  }

  public HttpResponse getResponse(String uriString) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(uriString))
        .build();
    HttpResponse<String> response
        = client.send(request, HttpResponse.BodyHandlers.ofString());
    return response;
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    String uriString = "http://localhost" + ":" + PORT + PATH + "?" + "name=michael";
    GetClient client = new GetClient();
    HttpResponse response = client.getResponse(uriString);

    System.out.println("Status is " + response.statusCode());

    String result = (String) response.body();

    System.out.println(result);
  }

}
