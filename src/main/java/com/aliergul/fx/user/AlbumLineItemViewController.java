package com.aliergul.fx.user;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.ProductEntity;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AlbumLineItemViewController implements Initializable {

  private static final String TAG = "AlbumLineItemViewController";
  private Logger logger = LogManager.getLogger(AlbumLineItemViewController.class);
  private AnchorPane anchorPane;

  @FXML
  private ImageView item_img_album;

  @FXML
  private ComboBox<Integer> item_combo_product_count;

  @FXML
  private Label item_lbl_album_SingerName;

  @FXML
  private Label item_lbl_album_categories;

  @FXML
  private Label item_lbl_album_name;

  @FXML
  private ListView<ProductEntity> item_llist_product;

  @FXML
  private Label item_product_discount_rate;

  @FXML
  private Label item_product_discounted_pierce;

  @FXML
  private Label item_product_pierce;

  @FXML
  private Label item_product_stock_count;



  public AlbumLineItemViewController() {
    try {
      // assumes FXML file is in same package as this controller
      // (also make sure name of FXML resource is correct)
      FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemProductList.fxml"));
      loader.setController(this);
      anchorPane = loader.load();
    } catch (IOException exc) {
      // pretty much fatal here...
      throw new UncheckedIOException(exc);
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    BasicConfigurator.configure();

  }

  @FXML
  void onClickAddCart(MouseEvent event) {

  }

  public Node getView() {

    return anchorPane;
  }

  public void setTicket(AlbumEntity item) {
    ObservableList<ProductEntity> products = FXCollections.observableArrayList();
    byte[] image = item.getImgAlbum();
    if (image != null) {
      InputStream myImage = new ByteArrayInputStream(image);
      item_img_album.setImage(new Image(myImage));
    } else {
      item_img_album.setImage(null);
    }

    item_lbl_album_name.setText(item.getName());
    item_lbl_album_SingerName
        .setText(item.getSinger().getName() + " " + item.getSinger().getSurname());

    item_lbl_album_categories.setText(item.getCategories().toString().replace(",", "#"));

    products.setAll(item.getProducts());
    item_llist_product.setItems(products);
    if (products.size() > 0 && item_llist_product.getSelectionModel().getSelectedIndex() < 0) {
      item_llist_product.getSelectionModel().select(0);
    }

    item_llist_product.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<ProductEntity>() {

          @Override
          public void changed(ObservableValue<? extends ProductEntity> observable,
              ProductEntity oldValue, ProductEntity newValue) {
            if (newValue != null) {
              item_product_discount_rate.setText("% " + newValue.getDiscountRate());
              Double discounted_pierce =
                  newValue.getPierce() * ((100 - newValue.getDiscountRate()) / 100);
              item_product_discounted_pierce.setText(String.format("%.2f", discounted_pierce));
              item_product_pierce.setText(String.format("%.2f", newValue.getPierce()));

              ObservableList<Integer> countIntegers = FXCollections.observableArrayList();
              for (int i = 1; i <= newValue.getStockCount(); i++) {
                countIntegers.add(i);
              }
              item_combo_product_count.setItems(countIntegers);
            }



          }
        });

  }


}
