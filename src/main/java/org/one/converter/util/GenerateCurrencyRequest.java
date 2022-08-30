package org.one.converter.util;

import org.one.converter.model.request.CurrencyRequest;

public class GenerateCurrencyRequest {

  public static CurrencyRequest generateCurrencyRequest(String url, String fromCurrency, String toCurrency,
      String amount) {
    CurrencyRequest currencyRequest = new CurrencyRequest();

    currencyRequest.setUrl(url);
    currencyRequest.setFrom(fromCurrency);
    currencyRequest.setTo(toCurrency);
    currencyRequest.setAmount(Double.parseDouble(amount));

    return currencyRequest;
  }
}
