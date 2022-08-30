package org.one.converter.util;


import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class SetOutput {

  public static void setOutput(Label output,String message, Color color) {
    output.setText(message);
    output.setTextFill(color);
  }
}
