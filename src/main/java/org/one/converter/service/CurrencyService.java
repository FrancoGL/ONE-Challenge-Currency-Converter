package org.one.converter.service;


import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.one.converter.mapper.JsonMapper;
import org.one.converter.method.GetRequest;
import org.one.converter.model.request.CurrencyRequest;
import org.one.converter.model.response.CurrencyResponse;
import org.one.converter.service.abstraction.GetAllCurrency;
import org.one.converter.service.abstraction.GetCurrency;

public class CurrencyService implements GetCurrency, GetAllCurrency {

  private final JsonMapper mapper;

  public CurrencyService() {
    this.mapper = new JsonMapper();
  }

  @Override
  public CurrencyResponse get(CurrencyRequest currencyRequest) {

    try {
      HttpResponse<String> json = GetRequest.getRequest(currencyRequest.buildUrlToConvertRequest());

      if (json.statusCode() == 200) {
        return mapper.jsonToCurrencyResponse(json.body());
      } else {
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setError("Error" + json.statusCode());
        return currencyResponse;
      }
    } catch (Exception e) {
      CurrencyResponse currencyResponse = new CurrencyResponse();
      currencyResponse.setError(e.getMessage());
      return currencyResponse;
    }
  }

  @Override
  public List<CurrencyResponse> getAll(CurrencyRequest currencyRequest) {

    try {
      HttpResponse<String> json = GetRequest.getRequest(currencyRequest.getUrl());

      if (json.statusCode() == 200) {
        return mapper.jsonToCurrencyResponseList(json.body());
      } else {
        List<CurrencyResponse> currencyResponses = new ArrayList<>();
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setError("Error" + json.statusCode());
        currencyResponses.add(currencyResponse);
        return currencyResponses;
      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      List<CurrencyResponse> currencyResponses = new ArrayList<>();
      CurrencyResponse currencyResponse = new CurrencyResponse();
      currencyResponse.setError(e.getMessage());
      currencyResponses.add(currencyResponse);
      return currencyResponses;
    }
  }
}
