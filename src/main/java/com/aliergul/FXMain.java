package com.aliergul;


import java.io.IOException;
import com.aliergul.fx.admin.AdminHomePageController;
import com.aliergul.fx.admin.NewAlbumAddedController;
import com.aliergul.fx.admin.NewRecordTypeController;
import com.aliergul.fx.admin.NewSingerAddedController;
import com.aliergul.fx.controller.LoginPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMain extends Application {

  private static Scene scene;

  public static void main(String[] args) throws InterruptedException {
    // Test Verilerini FX den Önce Yüklemek için bir kere çalıştırmalısınız.
    // TestMenu.getInstance.showMenu();
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    scene = new Scene(loadFXML("LoginPage"), 700, 600);
    scene.getStylesheets().add(getClass().getResource("myStyle.css").toExternalForm()); // .add("myStyle.css");

    stage.setScene(scene);
    stage.show();
    loadLoginPage();
    // loadAdminPage();
    // loadNewAlbum();
    // loadNewSinger();
    // loadAdminPage();
    // loadRecordType();
  }

  public void loadAdminPage() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("AdminPage.fxml"));
    scene.setRoot(fxmlLoader.load());
    AdminHomePageController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public void loadRecordType() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("NewRecordTypeAdded.fxml"));
    scene.setRoot(fxmlLoader.load());
    NewRecordTypeController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public void loadNewSinger() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("NewSingerAdded.fxml"));
    scene.setRoot(fxmlLoader.load());
    NewSingerAddedController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public void loadNewCategory() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("NewCategory.fxml"));
    scene.setRoot(fxmlLoader.load());
    NewSingerAddedController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public static Scene getScene() {
    return scene;
  }

  public void loadNewAlbum() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("NewAlbum.fxml"));
    scene.setRoot(fxmlLoader.load());
    NewAlbumAddedController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public void loadLoginPage() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("LoginPage.fxml"));
    scene.setRoot(fxmlLoader.load());
    LoginPageController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  private Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource(fxml + ".fxml"));

    return fxmlLoader.load();
  }

}
