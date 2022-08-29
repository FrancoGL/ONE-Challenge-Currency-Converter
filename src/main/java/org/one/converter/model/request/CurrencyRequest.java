package org.one.converter.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRequest {

  private String url;

  private String from;

  private String to;

  private double amount;

  public String buildUrlToConvertRequest() {
    return String.format("%s?from=%s&to=%s&amount=%s",
        this.url, this.from.substring(0,3), this.to.substring(0,3), this.amount);
  }
}
