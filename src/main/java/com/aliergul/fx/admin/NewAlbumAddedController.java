
package com.aliergul.fx.admin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import com.aliergul.FXMain;
import com.aliergul.dao.AlbumController;
import com.aliergul.dao.SingerController;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.SingerEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class NewAlbumAddedController {

  private SingerController singerController = new SingerController();
  private AlbumController albumController = new AlbumController();
  private ObservableList<SingerEntity> singers = FXCollections.observableArrayList();
  private ObservableList<AlbumEntity> albums = FXCollections.observableArrayList();
  private SingerEntity chooseSinger;
  private AlbumEntity chooseAlbum;
  private String imgPath = "";
  private FXMain main;

  @FXML
  private Button album_btn_delete;

  @FXML
  private Button album_btn_new;

  @FXML
  private Button album_btn_save;

  @FXML
  private TextField album_edt_name;

  @FXML
  private TextArea album_edt_searchtext;

  @FXML
  private ImageView album_img_capture;

  @FXML
  private ListView<?> album_list_type;

  @FXML
  private TableColumn<SingerEntity, String> album_singer_table_colum_name;

  @FXML
  private TableColumn<SingerEntity, String> album_singer_table_colum_surname;

  @FXML
  private TableView<SingerEntity> album_singer_table_view;

  @FXML
  private TableColumn<AlbumEntity, String> album_table_column_date;

  @FXML
  private TableColumn<AlbumEntity, String> album_table_column_name;

  @FXML
  private TableColumn<AlbumEntity, String> album_table_column_type;

  @FXML
  private TableView<AlbumEntity> album_table_view;

  @FXML
  void onDeletedAlbum(MouseEvent event) {

  }

  @FXML
  void onNewAlbum(MouseEvent event) {
    chooseAlbum = new AlbumEntity();
    album_btn_save.setDisable(false);
    album_btn_new.setDisable(true);
    album_btn_delete.setDisable(true);
    album_edt_name.setText("");

  }

  @FXML
  void onBackAdminPage(MouseEvent event) {
    try {
      main.loadAdminPage();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void onSaveAlbum(MouseEvent event) throws IOException {
    chooseAlbum.setName(album_edt_name.getText());
    chooseAlbum.setImgAlbum(imgPath);
    if (chooseAlbum.getName().length() > 0) {
      boolean result = false;
      if (chooseAlbum.getId() > 0) {

        result = albumController.update(chooseAlbum);
      } else {
        AlbumController controller = new AlbumController();
        result = albumController.create(chooseAlbum);

      }
      if (result) {
        albums.setAll(albumController.list());
        onNewAlbum(null);
      } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText("Kayıt Başarısız");
        alert.setTitle("HATA");
        alert.show();
      }

    }

  }

  @FXML
  void onOpenChooseFlePath(MouseEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Resim Seç");
    fileChooser.getExtensionFilters().addAll(
        // new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
        // new FileChooser.ExtensionFilter("ZIP", "*.zip"),
        // new FileChooser.ExtensionFilter("PDF", "*.pdf"),
        // new FileChooser.ExtensionFilter("TEXT", "*.txt"),
        new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));


    File file = fileChooser.showOpenDialog(main.getScene().getWindow());

    if (file != null) {
      imgPath = file.getPath();
      album_img_capture.setImage(new Image(imgPath));
    } else {
      System.out.println("error"); // or something else
    }
  }



  public void initAdminPanel(FXMain main) {
    this.main = main;
    singers.setAll(singerController.list());
    album_table_view.setItems(albums);
    album_singer_table_view.setItems(singers);
    album_singer_table_colum_name
        .setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getName()));
    album_singer_table_colum_surname
        .setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getSurname()));

    album_singer_table_view.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<SingerEntity>() {

          @Override
          public void changed(ObservableValue<? extends SingerEntity> observable,
              SingerEntity oldValue, SingerEntity newValue) {
            chooseSinger = newValue;

          }
        });
    onSelectedSingerTable();
    onSelectedAlbumTable();
  }

  private void onSelectedAlbumTable() {
    album_table_view.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<AlbumEntity>() {

          @Override
          public void changed(ObservableValue<? extends AlbumEntity> observable,
              AlbumEntity oldValue, AlbumEntity newValue) {

            if (newValue != null) {
              chooseAlbum = newValue;
              album_edt_name.setText(newValue.getName());
              album_btn_delete.setDisable(false);
              album_btn_new.setDisable(false);

              byte[] image = newValue.getImgAlbum();

              if (image != null && image.length > 0) {
                // database'den byte[]'ini InputStrema a çevirip yeni bir image nesnesi
                // oluşturuyorum
                InputStream myImage = new ByteArrayInputStream(image);
                album_img_capture.setImage(new Image(myImage));

              }
            }

          }
        });

  }

  private void onSelectedSingerTable() {

    album_singer_table_view.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<SingerEntity>() {

          @Override
          public void changed(ObservableValue<? extends SingerEntity> observable,
              SingerEntity oldValue, SingerEntity newValue) {
            if (newValue != null) {
              chooseSinger = newValue;
              albums.setAll(newValue.getAlbums());
              album_table_column_date.setCellValueFactory(
                  v -> new SimpleStringProperty(v.getValue().getCreateDate().toString()));
              album_table_column_name
                  .setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getName()));
              album_table_column_type.setCellValueFactory(
                  v -> new SimpleStringProperty(v.getValue().getCategories().toString()));
            }

          }
        });

  }

}
