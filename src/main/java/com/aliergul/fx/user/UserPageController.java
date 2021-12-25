package com.aliergul.fx.user;


import com.aliergul.FXMain;
import com.aliergul.dao.AlbumController;
import com.aliergul.entity.AlbumEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class UserPageController {
  ObservableList<AlbumEntity> albums = FXCollections.observableArrayList();
  AlbumController controllerAlbumController = new AlbumController();
  private FXMain main;
  @FXML
  private ListView<AlbumEntity> user_list_albums;

  public void initUserPageLoad(FXMain main) {
    this.main = main;
    albums.setAll(controllerAlbumController.list());
    user_list_albums.setItems(albums);
    // Album Listesi içine kendi yaptığımız view i yerleştirlim
    user_list_albums.setCellFactory(new Callback<ListView<AlbumEntity>, ListCell<AlbumEntity>>() {

      @Override
      public ListCell<AlbumEntity> call(ListView<AlbumEntity> param) {
        // Her Hücre için o hücre ile alakalı bir sınıf oluşturalm
        return new AlbumListViewCell();
      }
    });
  }
}
