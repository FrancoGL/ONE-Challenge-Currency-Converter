package org.one.converter.util;

import org.one.converter.model.response.CurrencyResponse;

public class SetError {

  public static CurrencyResponse setErrorResponse(String message) {
    CurrencyResponse currencyResponse = new CurrencyResponse();

    currencyResponse.setError(message);

    return currencyResponse;
  }
}
