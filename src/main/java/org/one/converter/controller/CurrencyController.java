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
import org.one.converter.util.GenerateCurrencyRequest;
import org.one.converter.util.SetOutput;

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

  private String setUrl;

  public void currencyConvert() {

    setUrl = "https://api.exchangerate.host/convert";
    String getFromCurrency = fromCurrency.getValue();
    String getToCurrency = toCurrency.getValue();
    String getAmount = textField.getText();

    try {
      if (!getAmount.isEmpty() && getFromCurrency != null && getToCurrency != null) {

        CurrencyRequest currencyRequest = GenerateCurrencyRequest.generateCurrencyRequest(
            setUrl, getFromCurrency, getToCurrency, getAmount);

        CurrencyResponse result = currencyService.get(currencyRequest);

        SetOutput.setOutput(output,
            String.format("%s %s", result.getAmount(), result.getCode().substring(1, 4)),
            Color.BLACK);
      } else {
        SetOutput.setOutput(output, "You have to provide amount and currencies", Color.RED);
      }
    } catch (Exception e) {
      SetOutput.setOutput(output, e.getMessage(), Color.RED);
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {

      setUrl = "https://api.exchangerate.host/symbols";

      List<CurrencyResponse> currencyResponses = currencyService.getAll(setUrl);

      currencyResponses.forEach(e -> {
        fromCurrency.getItems().add(String.format("%s (%s)", e.getCode(), e.getDescription()));
        toCurrency.getItems().add(String.format("%s (%s)", e.getCode(), e.getDescription()));
      });

    } catch (Exception e) {
      SetOutput.setOutput(output, e.getMessage(), Color.RED);
    }
  }
}
