package org.one.converter.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyResponse {

  private String code;

  private String description;

  private String amount;

  private String error;
}
