package com.aliergul.fx.admin;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.controlsfx.control.ToggleSwitch;
import com.aliergul.FXMain;
import com.aliergul.dao.SingerController;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.OrderEntity;
import com.aliergul.entity.ProductEntity;
import com.aliergul.entity.SingerEntity;
import com.aliergul.util.EStatus;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdminHomePageController {

  private SingerController singerController = new SingerController();
  private ProductEntity chooseProduct = null;
  private AlbumEntity chooseAlbum = null;
  private FXMain main;
  ObservableList<AlbumEntity> albums = FXCollections.observableArrayList();
  ObservableList<ProductEntity> products = FXCollections.observableArrayList();
  ObservableList<SingerEntity> singers = FXCollections.observableArrayList();
  ObservableList<OrderEntity> orders = FXCollections.observableArrayList();
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

  /* Product Table information */
  @FXML
  private Button product_btn_delete;

  @FXML
  private Button product_btn_new;

  @FXML
  private Button product_btn_save;

  @FXML
  private TextField product_edt_discount;

  @FXML
  private TextField product_edt_price;

  @FXML
  private TextField product_edt_sales_count;

  @FXML
  private TextField product_edt_stock;

  @FXML
  private ToggleSwitch product_switch_status;

  @FXML
  private ImageView admin_img_album;


  @FXML
  private TableColumn<OrderEntity, String> table_column_count;

  @FXML
  private TableColumn<OrderEntity, String> table_column_date;

  @FXML
  private TableColumn<OrderEntity, String> table_column_discount;

  @FXML
  private TableColumn<OrderEntity, String> table_column_price;

  @FXML
  private TableColumn<OrderEntity, String> table_column_sum_piarce;

  @FXML
  private TableView<OrderEntity> table_orders;

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
              chooseAlbum = newValue;


              byte[] image = chooseAlbum.getImgAlbum();
              if (image != null && image.length > 0) {
                // database'den byte[]'ini InputStrema a çevirip yeni bir image nesnesi
                // oluşturuyorum
                InputStream myImage = new ByteArrayInputStream(image);
                admin_img_album.setImage(new Image(myImage));
              } else {
                admin_img_album.setImage(null);
              }
              products.setAll(chooseAlbum.getProducts());
              admin_colum_product.setCellValueFactory(
                  v -> new SimpleStringProperty(v.getValue().getType().getType() + " "
                      + v.getValue().getType().getDescriptions()));

            }
          }
        });

    admin_table_product.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<ProductEntity>() {

          @Override
          public void changed(ObservableValue<? extends ProductEntity> observable,
              ProductEntity oldValue, ProductEntity newValue) {

            if (newValue != null) {
              chooseProduct = newValue;

              product_edt_discount.setText(chooseProduct.getDiscountRate() + "");
              product_edt_price.setText(chooseProduct.getPierce() + "");
              product_edt_sales_count.setText(chooseProduct.getSalesCount() + "");
              product_edt_stock.setText(chooseProduct.getStockCount() + "");
              product_switch_status
                  .setSelected(chooseProduct.getStatus() == EStatus.ACTIVE ? true : false);
              getOrderTableItemsALL();

            }
          }

          private void getOrderTableItemsALL() {
            orders.setAll(chooseProduct.getOrders());
            table_orders.setItems(orders);

            table_column_date.setCellValueFactory(
                v -> new SimpleStringProperty(v.getValue().getCreateDate().toString()));

            table_column_count
                .setCellValueFactory(v -> new SimpleStringProperty((v.getValue().getCount()) + ""));

            table_column_price.setCellValueFactory(v -> new SimpleStringProperty(
                (v.getValue().getSumPierce() / v.getValue().getCount())
                    / v.getValue().getDiscountRate() + ""));

            table_column_discount.setCellValueFactory(
                v -> new SimpleStringProperty((v.getValue().getDiscountRate()) + ""));

            table_column_sum_piarce.setCellValueFactory(
                v -> new SimpleStringProperty(v.getValue().getSumPierce() + ""));
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
