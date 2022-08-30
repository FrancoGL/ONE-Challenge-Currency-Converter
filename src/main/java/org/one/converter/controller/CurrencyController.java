package org.one.converter.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.one.converter.model.request.CurrencyRequest;
import org.one.converter.model.response.CurrencyResponse;
import org.one.converter.service.CurrencyService;

public class CurrencyController implements Initializable {

  @FXML
  private TextField textField;

  @FXML
  private ChoiceBox<String> fromCurrency;

  @FXML
  private ChoiceBox<String> toCurrency;

  @FXML
  private Label output;

  private final CurrencyService currencyService;

  public CurrencyController() {
    this.currencyService = new CurrencyService();
  }

  public void currencyConvert() {

    if(fromCurrency.getValue() != null && toCurrency.getValue() != null && !textField.getText().isEmpty()) {

      CurrencyRequest currencyRequest = new CurrencyRequest();

      currencyRequest.setUrl("https://api.exchangerate.host/convert");
      currencyRequest.setFrom(fromCurrency.getValue());
      currencyRequest.setTo(toCurrency.getValue());
      currencyRequest.setAmount(Double.parseDouble(textField.getText()));

      CurrencyResponse result = currencyService.get(currencyRequest);

      output.setText(String.format("%s %s", result.getAmount(), result.getCode().substring(1,4)));
    } else {
      output.setText("You have to provide amount and currencies");
      output.setTextFill(Color.RED);
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {

      CurrencyRequest currencyRequest = new CurrencyRequest();

      currencyRequest.setUrl("https://api.exchangerate.host/symbols");

      List<CurrencyResponse> currencyResponses = currencyService.getAll(currencyRequest);

      currencyResponses.forEach(e -> {
        fromCurrency.getItems().add(String.format("%s (%s)", e.getCode(), e.getDescription()));
        toCurrency.getItems().add(String.format("%s (%s)", e.getCode(), e.getDescription()));
      });

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
