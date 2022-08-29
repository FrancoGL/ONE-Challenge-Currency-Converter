package org.one.converter.method;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.one.converter.error.ErrorResponse;

public class GetRequest {

  public static HttpResponse<String> getRequest(String url) throws ErrorResponse {
    try {
      HttpRequest httpRequest = HttpRequest.newBuilder().
          uri(new URI(url))
          .GET()
          .build();

      return CreateHttpClient.createHttpClient(httpRequest);

    } catch (Exception e) {
      throw new ErrorResponse("Error trying getting data");
    }
  }
}
