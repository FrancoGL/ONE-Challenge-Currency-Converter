package org.one.converter.service.abstraction;

import java.util.List;
import org.one.converter.model.request.CurrencyRequest;
import org.one.converter.model.response.CurrencyResponse;

public interface GetAllCurrency {

  public List<CurrencyResponse> getAll(CurrencyRequest currencyRequest);
}
