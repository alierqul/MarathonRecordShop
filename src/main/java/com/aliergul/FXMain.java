package com.aliergul;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMain extends Application {

  private static Scene scene;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    scene = new Scene(loadFXML("LoginPage"), 650, 533);
    stage.setScene(scene);
    stage.show();

  }

  private Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

}
