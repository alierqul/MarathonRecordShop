package com.aliergul;


import java.io.IOException;
import com.aliergul.entity.UserEntity;
import com.aliergul.fx.admin.AdminHomePageController;
import com.aliergul.fx.admin.NewAlbumAddedController;
import com.aliergul.fx.admin.NewRecordTypeController;
import com.aliergul.fx.admin.NewSingerAddedController;
import com.aliergul.fx.controller.LoginPageController;
import com.aliergul.fx.user.UserPageController;
import com.aliergul.util.HibernateUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

// --module-path "D:\Eclipse2021\library\openjfx-17.0.1_windows-x64_bin-sdk\javafx-sdk-17.0.1\lib"
// --add-modules javafx.controls,javafx.fxml
public class FXMain extends Application {

  private static Scene scene;
  private static Stage stage;

  public static void main(String[] args) throws InterruptedException {
    // Test Verilerini FX den Önce Yüklemek için bir kere çalıştırmalısınız.
    // TestMenu.getInstance.showMenu();
    HibernateUtils.getSessionFactory();
    launch();
  }

  @Override
  public void start(Stage stage) throws Exception {
    scene = new Scene(loadFXML("LoginPage"), 700, 600);
    scene.getStylesheets().add(getClass().getResource("myStyle.css").toExternalForm()); // .add("myStyle.css");
    FXMain.stage = stage;
    stage.setScene(scene);
    stage.show();
    loadLoginPage();
    // loadUserPage();
    // loadAdminPage();
    // loadNewAlbum();
    // loadNewSinger();
    // loadAdminPage();
    // loadRecordType();
  }

  public void loadAdminPage() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("AdminPage.fxml"));
    FXMain.stage.setWidth(1300);
    FXMain.stage.setHeight(800);
    scene.setRoot(fxmlLoader.load());
    AdminHomePageController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public void loadUserPage(UserEntity chooseUser) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("UserPanel.fxml"));
    FXMain.stage.setWidth(1100);
    FXMain.stage.setHeight(950);
    scene.setRoot(fxmlLoader.load());
    UserPageController controller = fxmlLoader.getController();
    controller.initUserPageLoad(this, chooseUser);


  }

  public void loadRecordType() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("NewRecordTypeAdded.fxml"));
    FXMain.stage.setWidth(700);
    FXMain.stage.setHeight(800);
    scene.setRoot(fxmlLoader.load());
    NewRecordTypeController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public void loadNewSinger() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("NewSingerAdded.fxml"));
    FXMain.stage.setWidth(700);
    FXMain.stage.setHeight(800);
    scene.setRoot(fxmlLoader.load());
    NewSingerAddedController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public void loadNewCategory() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("NewCategory.fxml"));
    FXMain.stage.setWidth(700);
    FXMain.stage.setHeight(800);
    scene.setRoot(fxmlLoader.load());
    NewSingerAddedController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public static Scene getScene() {
    return scene;
  }

  public void loadNewAlbum() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("NewAlbum.fxml"));
    FXMain.stage.setWidth(900);
    FXMain.stage.setHeight(800);
    scene.setRoot(fxmlLoader.load());
    NewAlbumAddedController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  public void loadLoginPage() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource("LoginPage.fxml"));
    FXMain.stage.getIcons().add(new Image(FXMain.class.getResourceAsStream("img/logo.png")));
    FXMain.stage.setTitle("Record Store");
    FXMain.stage.setWidth(800);
    FXMain.stage.setHeight(600);
    scene.setRoot(fxmlLoader.load());
    LoginPageController controller = fxmlLoader.getController();
    controller.initAdminPanel(this);


  }

  private Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(FXMain.class.getResource(fxml + ".fxml"));

    return fxmlLoader.load();
  }

}
