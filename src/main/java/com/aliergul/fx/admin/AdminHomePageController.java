package com.aliergul.fx.admin;

import java.io.IOException;
import com.aliergul.FXMain;
import com.aliergul.dao.SingerController;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.ProductEntity;
import com.aliergul.entity.SingerEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminHomePageController {

  private SingerController singerController = new SingerController();

  private FXMain main;
  ObservableList<AlbumEntity> albums = FXCollections.observableArrayList();
  ObservableList<ProductEntity> products = FXCollections.observableArrayList();
  ObservableList<SingerEntity> singers = FXCollections.observableArrayList();
  @FXML
  private TableColumn<AlbumEntity, String> admin_colum_album;

  @FXML
  private TableColumn<ProductEntity, String> admin_colum_product;

  @FXML
  private TableColumn<SingerEntity, String> admin_colum_singer;

  @FXML
  private TableView<AlbumEntity> admin_table_album;

  @FXML
  private TableView<ProductEntity> admin_table_product;

  @FXML
  private TableView<SingerEntity> admin_table_singer;

  public void initAdminPanel(FXMain main) {
    this.main = main;
    singers.setAll(singerController.list());

    admin_table_album.setItems(albums);
    admin_table_product.setItems(products);
    admin_table_singer.setItems(singers);



    admin_colum_singer.setCellValueFactory(
        v -> new SimpleStringProperty(v.getValue().getName() + " " + v.getValue().getSurname()));

    admin_table_singer.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<SingerEntity>() {

          @Override
          public void changed(ObservableValue<? extends SingerEntity> observable,
              SingerEntity oldValue, SingerEntity newValue) {
            if (newValue != null) {
              albums.setAll(newValue.getAlbums());
              admin_colum_album
                  .setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getName()));
            }

          }
        });

    admin_table_album.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<AlbumEntity>() {

          @Override
          public void changed(ObservableValue<? extends AlbumEntity> observable,
              AlbumEntity oldValue, AlbumEntity newValue) {
            if (newValue != null) {
              products.setAll(newValue.getProducts());
              admin_colum_product.setCellValueFactory(
                  v -> new SimpleStringProperty(v.getValue().getType().getType() + " "
                      + v.getValue().getType().getDescriptions()));
            }
          }
        });
  }

  @FXML
  void onLogOut(ActionEvent event) {
    try {
      main.loadLoginPage();
    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  @FXML
  void onOpenNewCategoryPage(ActionEvent event) {
    try {
      main.loadNewCategory();
    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  @FXML
  void onOpenNewAlbumPage(ActionEvent event) {
    try {
      main.loadNewAlbum();
    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  @FXML
  void onOpenNewSingerPage(ActionEvent event) {
    try {
      main.loadNewSinger();
    } catch (IOException e) {

      e.printStackTrace();
    }
  }
}
