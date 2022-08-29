package org.one.converter.service.abstraction;

import org.one.converter.model.request.CurrencyRequest;
import org.one.converter.model.response.CurrencyResponse;

public interface GetCurrency {

  public CurrencyResponse get(CurrencyRequest currencyRequest);
}
