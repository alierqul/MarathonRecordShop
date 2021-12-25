package com.aliergul.fx.user;


import com.aliergul.FXMain;
import com.aliergul.dao.AlbumController;
import com.aliergul.dao.CategoriesControllerImpls;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.CategoryEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class UserPageController {
  private ObservableList<AlbumEntity> albums = FXCollections.observableArrayList();
  private ObservableList<CategoryEntity> categories = FXCollections.observableArrayList();
  private AlbumController controllerAlbumController = new AlbumController();
  private CategoriesControllerImpls controllerCategory = new CategoriesControllerImpls();
  private FXMain main;
  @FXML
  private ListView<AlbumEntity> user_list_albums;

  @FXML
  private ListView<CategoryEntity> user_list_categories;

  public void initUserPageLoad(FXMain main) {
    this.main = main;
    albums.setAll(controllerAlbumController.list());

    categories.setAll(controllerCategory.list());
    user_list_categories.setItems(categories);

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

  @FXML
  void onBestSellers(MouseEvent event) {
    albums.removeAll();
    albums.setAll(controllerAlbumController.getBestSellers());

  }

  @FXML
  void onFifteenOnSales(MouseEvent event) {
    albums.removeAll();
    albums.setAll(controllerAlbumController.getFifteenOnSales());
  }

  @FXML
  void onTheLastAddedAlbums(MouseEvent event) {
    albums.removeAll();
    albums.setAll(controllerAlbumController.getTheLastAddedAlbums());
  }
}
