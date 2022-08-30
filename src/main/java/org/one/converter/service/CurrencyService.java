package org.one.converter.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.one.converter.error.ErrorResponse;
import org.one.converter.mapper.JsonMapper;
import org.one.converter.method.GetRequest;
import org.one.converter.model.request.CurrencyRequest;
import org.one.converter.model.response.CurrencyResponse;
import org.one.converter.service.abstraction.GetAllCurrency;
import org.one.converter.service.abstraction.GetCurrency;
import org.one.converter.util.SetError;

public class CurrencyService implements GetCurrency, GetAllCurrency {

  private final JsonMapper mapper;

  public CurrencyService() {
    this.mapper = new JsonMapper();
  }

  @Override
  public CurrencyResponse get(CurrencyRequest currencyRequest)
      throws ErrorResponse, JsonProcessingException {

    HttpResponse<String> json = GetRequest.getRequest(currencyRequest.buildUrlToConvertRequest());

    if (json.statusCode() == 200) {
      return mapper.jsonToCurrencyResponse(json.body());
    } else {
      return SetError.setErrorResponse("Error" + json.statusCode());
    }
  }

  @Override
  public List<CurrencyResponse> getAll(String url) throws JsonProcessingException, ErrorResponse {

    HttpResponse<String> json = GetRequest.getRequest(url);

    if (json.statusCode() == 200) {
      return mapper.jsonToCurrencyResponseList(json.body());
    } else {
      List<CurrencyResponse> currencyResponses = new ArrayList<>();
      currencyResponses.add(SetError.setErrorResponse("Error" + json.statusCode()));
      return currencyResponses;
    }
  }
}
