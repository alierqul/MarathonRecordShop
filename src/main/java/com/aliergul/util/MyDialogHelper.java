package com.aliergul.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public enum MyDialogHelper {
  getInstance;

  public boolean alertDialog(String title, String header, String footer) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(footer);

    ButtonType yesButton = new ButtonType("Evet");
    ButtonType noButton = new ButtonType("Hayır");


    alert.getButtonTypes().setAll(yesButton, noButton);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == yesButton) {
      return true;
    } else
      return false;

  }

  public void showMessage(String title, String header, String footer) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(footer);
    alert.show();

  }

  public void showErrorMessage(String title, String header, String footer) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(footer);
    alert.show();

  }

}
