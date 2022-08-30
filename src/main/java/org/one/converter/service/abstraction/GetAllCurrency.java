package org.one.converter.service.abstraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.one.converter.error.ErrorResponse;
import org.one.converter.model.request.CurrencyRequest;
import org.one.converter.model.response.CurrencyResponse;

public interface GetAllCurrency {

  public List<CurrencyResponse> getAll(String url) throws JsonProcessingException, ErrorResponse;
}
