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
import com.aliergul.entity.OrderEntity;
import com.aliergul.entity.ProductEntity;
import com.aliergul.util.EStatus;
import com.aliergul.util.MyDialogHelper;
import com.aliergul.util.exception.ExceptionDiscountError;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AlbumLineItemViewController implements Initializable {

  private static final String TAG = "AlbumLineItemViewController";
  private Logger logger = LogManager.getLogger(AlbumLineItemViewController.class);
  private AnchorPane anchorPane;
  private IOrderToCart orderToCart;
  private ProductEntity productEntity;

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
  private Label item_product_discount_rate;

  @FXML
  private Label item_product_discounted_pierce;

  @FXML
  private Label item_product_pierce;

  @FXML
  private Label item_product_stock_count;
  @FXML
  private Label item_product_type;

  @FXML
  private Button item_btn_add_to_cart;


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

    try {
      if (item_combo_product_count.getValue() != null) {
        orderToCart.addToCartinOrderList(

            new OrderEntity(productEntity, null, item_combo_product_count.getValue()));
      } else {
        MyDialogHelper.getInstance.showErrorMessage("Hata", "Önce Adet Seçmelisiniz.", "");
      }

    } catch (ExceptionDiscountError e) {
      e.printStackTrace();
    }
  }

  public Node getView() {

    return anchorPane;
  }

  public void setTicket(ProductEntity product, UserPageController context) {
    AlbumEntity album = product.getAlbum();
    this.productEntity = product;

    this.orderToCart = context;
    byte[] image = album.getImgAlbum();
    if (image != null) {
      InputStream myImage = new ByteArrayInputStream(image);
      item_img_album.setImage(new Image(myImage));
    } else {
      item_img_album.setImage(null);
    }

    item_lbl_album_name.setText(album.getName());
    item_lbl_album_SingerName
        .setText(album.getSinger().getName() + " " + album.getSinger().getSurname());

    item_lbl_album_categories.setText(album.getCategories().toString().replace(",", "#"));
    item_product_type.setText(product.getType().toString());
    item_product_discount_rate.setText("% " + product.getDiscountRate());
    Double discounted_pierce = product.getPierce() * ((100 - product.getDiscountRate()) / 100);
    item_product_discounted_pierce.setText(String.format("%.2f TL", discounted_pierce));
    item_product_pierce.setText(String.format("%.2f TL", product.getPierce()));
    item_product_stock_count.setText(product.getStockCount() + " adt");
    ObservableList<Integer> countIntegers = FXCollections.observableArrayList();
    for (int i = 1; i <= product.getStockCount(); i++) {
      countIntegers.add(i);
    }

    if (product.getStatus() == EStatus.ACTIVE) {
      item_combo_product_count.setDisable(false);
      item_btn_add_to_cart.setDisable(false);
    } else {
      item_combo_product_count.setDisable(true);
      item_btn_add_to_cart.setDisable(true);
    }
    item_combo_product_count.setItems(countIntegers);

  }


}
