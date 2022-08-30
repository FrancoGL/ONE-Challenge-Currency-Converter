package org.one.converter.service.abstraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.one.converter.error.ErrorResponse;
import org.one.converter.model.request.CurrencyRequest;
import org.one.converter.model.response.CurrencyResponse;

public interface GetCurrency {

  public CurrencyResponse get(CurrencyRequest currencyRequest)
      throws ErrorResponse, JsonProcessingException;
}
