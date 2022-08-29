package org.one.converter.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.one.converter.model.response.CurrencyResponse;

public class JsonMapper {

  private final ObjectMapper objectMapper;

  public JsonMapper() {
    this.objectMapper = new ObjectMapper();
  }

  public List<CurrencyResponse> jsonToCurrencyResponseList(String json)
      throws JsonProcessingException {

    JsonNode rootNode = objectMapper.readTree(json);
    JsonNode currencies = rootNode.path("symbols");
    List<CurrencyResponse> currencyResponses = new ArrayList<>();

    currencies.forEach(currency -> {
      CurrencyResponse currencyResponse = new CurrencyResponse();
      currencyResponse.setDescription(
          currency.get("description").toString().
              substring(1, currency.get("description").toString().length()-1));
      currencyResponse.setCode(currency.get("code").toString().substring(1,4));
      currencyResponses.add(currencyResponse);
    });

    return currencyResponses;
  }

  public CurrencyResponse jsonToCurrencyResponse(String json) throws JsonProcessingException {

    JsonNode rootNode = objectMapper.readTree(json);
    JsonNode code = rootNode.path("query").get("to");
    JsonNode result = rootNode.get("result");

    CurrencyResponse currencyResponse = new CurrencyResponse();

    currencyResponse.setCode(code.toString());
    currencyResponse.setAmount(String.format("%.2f",Double.parseDouble(result.toString())));

    return currencyResponse;
  }
}
