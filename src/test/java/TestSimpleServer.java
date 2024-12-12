/*
 * Course: ICT4310
 * File: TestSimplServer
 * Author: Instructor
 */

import edu.du.ict4310.GetClient;
import edu.du.ict4310.PostClient;
import edu.du.ict4310.SimpleServer;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author michael
 */
public class TestSimpleServer {

  private final static int PORT = 4130; // Alternative port to avoid conflict
  private final static SimpleServer server = SimpleServer.create(PORT);

  // Start the server before any test, wait 1s to ensure it is ready
  @BeforeAll
  static void setup() {
    server.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // ignore
    }
  }

  // Test a GET request
  @Test
  void testGet() {
    final int port = PORT;
    String uriString = "http://localhost:" + port + "/hello/?name=michael";

    try {
      GetClient client = new GetClient();
      HttpResponse<String> response = client.getResponse(uriString);
      // System.out.println(response.body());
      assertEquals(200, response.statusCode());
      assertEquals("Hello from Javelin, michael!", response.body());
    } catch (Exception e) {
      fail(e);
    }
  }

  // Test a POST request
  @Test
  void testPost() {
    final int port = PORT;
    String uriString = "http://localhost" + ":" + port + "/greet/Elvis";
    String dataString = "";

    try {
      PostClient client = new PostClient();
      HttpResponse response = client.getResponse(uriString, dataString);
      // System.out.println(response.getBody());
      assertEquals(200, response.statusCode());
      assertEquals("Hello Elvis!", response.body());
    } catch (Exception e) {
      fail(e);
    }
  }

  @Test
  void testJson() {
    final int port = PORT;
    String uriString = "http://localhost" + ":" + port + "/name";
    String dataString = "{\"name\":\"michael\"}";
    try {
      PostClient client = new PostClient();
      HttpResponse response = client.getResponse(uriString, dataString);
      // System.out.println(response.getBody());
      assertEquals(200, response.statusCode());
      // Result will be { name: "michael", timestamp: ....}
      String now = LocalDate.now().toString();
      String body = response.body().toString();
      assertTrue(body.startsWith("{\"name\":\"michael\""));
      assertTrue(body.contains("\"timestamp\":\"" + now));

    } catch (Exception e) {
      fail(e);
    }
  }

  // Shut down the server after all the tests are complete
  @AfterAll
  static void tearDown() {
    server.stop();
  }

}
