package org.one.converter.method;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class CreateHttpClient {

  public static HttpResponse<String> createHttpClient(HttpRequest httpRequest)
      throws IOException, InterruptedException {
    HttpClient httpClient = HttpClient.newHttpClient();

    return httpClient.send(httpRequest, BodyHandlers.ofString());
  }
}
