package com.aliergul.fx.user;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.aliergul.FXMain;
import com.aliergul.dao.CategoriesControllerImpls;
import com.aliergul.dao.ProductControllerImpl;
import com.aliergul.entity.CategoryEntity;
import com.aliergul.entity.OrderEntity;
import com.aliergul.entity.ProductEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class UserPageController implements IOrderToCart {
  private static final String TAG = "UserPageController";
  private static final Logger logger = LogManager.getLogger(UserPageController.class);
  private ObservableList<ProductEntity> products = FXCollections.observableArrayList();
  private ObservableList<CategoryEntity> categories = FXCollections.observableArrayList();
  private ObservableList<OrderEntity> orders = FXCollections.observableArrayList();
  private ProductControllerImpl controllerProduct = new ProductControllerImpl();
  private CategoriesControllerImpls controllerCategory = new CategoriesControllerImpls();
  private FXMain main;

  @FXML
  private Label user_lbl_sum_count;

  @FXML
  private Label user_lbl_sum_pierce;
  @FXML
  private ListView<ProductEntity> user_list_products;

  @FXML
  private ListView<CategoryEntity> user_list_categories;

  @FXML
  private TableView<OrderEntity> user_table_order;

  @FXML
  private TableColumn<OrderEntity, String> user_table_order_column_allbum_name;

  @FXML
  private TableColumn<OrderEntity, String> user_table_order_column_count;

  @FXML
  private TableColumn<OrderEntity, String> user_table_order_column_discount_rate;
  @FXML
  private TableColumn<OrderEntity, String> user_table_column_discounted_pierce;

  @FXML
  private TableColumn<OrderEntity, String> user_table_order_pierce;

  @FXML
  private TableColumn<OrderEntity, String> user_table_order_sum_pierce;

  public void initUserPageLoad(FXMain main) {
    this.main = main;

    productDetailController();
    categoryDeatilController();
    orderDetailController();

  }

  private void productDetailController() {
    // Ürünleri Yerleştirme
    products.setAll(controllerProduct.list());
    user_list_products.setItems(products);
    // Album Listesi içine kendi yaptığımız view i yerleştirlim
    user_list_products
        .setCellFactory(new Callback<ListView<ProductEntity>, ListCell<ProductEntity>>() {

          @Override
          public ListCell<ProductEntity> call(ListView<ProductEntity> param) {
            // Her Hücre için o hücre ile alakalı bir sınıf oluşturalm
            return new AlbumListViewCell(UserPageController.this);
          }
        });

  }

  private void categoryDeatilController() {
    // Kategoriye Göre Arama

    categories.setAll(controllerCategory.list());
    user_list_categories.setItems(categories);
    user_list_categories.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    user_list_categories.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<CategoryEntity>() {


          @Override
          public void changed(ObservableValue<? extends CategoryEntity> observable,
              CategoryEntity oldValue, CategoryEntity newValue) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < categories.size(); i++) {
              if (user_list_categories.getSelectionModel().isSelected(i)) {
                stringBuilder.append(categories.get(i).getCategory()).append(",");
              }

            }

            products.setAll(controllerProduct
                .getProductByCategories(stringBuilder.substring(0, stringBuilder.length() - 1)));

          }
        });

  }

  private void orderDetailController() {
    user_table_order.setItems(orders);
    user_table_order_column_allbum_name.setCellValueFactory(
        v -> new SimpleStringProperty(v.getValue().getProduct().getAlbum().getName() + "- "
            + v.getValue().getProduct().getTypeProduct()));
    user_table_order_column_count
        .setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getCount() + " adt"));
    user_table_order_column_discount_rate
        .setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getDiscountRate() + " %"));
    user_table_order_pierce.setCellValueFactory(v -> new SimpleStringProperty(
        String.format("%.2f TL", v.getValue().getProduct().getPierce())));
    user_table_column_discounted_pierce.setCellValueFactory(v -> new SimpleStringProperty(
        String.format("%.2f TL", v.getValue().getDiscounted_pierce())));
    user_table_order_sum_pierce.setCellValueFactory(v -> new SimpleStringProperty(
        String.format("%.2f TL", v.getValue().getDiscounted_pierce() * v.getValue().getCount())));

  }

  @FXML
  void onBestSellers(MouseEvent event) {
    products.removeAll();
    products.setAll(controllerProduct.getBestSellers());

  }

  @FXML
  void onFifteenOnSales(MouseEvent event) {
    products.removeAll();
    products.setAll(controllerProduct.getFifteenOnSales());
  }

  @FXML
  void onTheLastAddedAlbums(MouseEvent event) {
    products.removeAll();
    products.setAll(controllerProduct.getTheLastAddedAlbums());
  }

  private long sum_count = 0;
  private double sum_pierce = 0;

  @Override
  public void addToCartinOrderList(OrderEntity order) {

    logger.info(TAG + " - orders : " + order.toString());
    if (orders.contains(order)) {
      int idx = orders.indexOf(order);
      sum_count -= orders.get(idx).getCount();
      sum_pierce -= orders.get(idx).getDiscounted_pierce() * order.getCount();
      orders.set(idx, order);
    } else {
      orders.add(order);
    }
    sum_count += order.getCount();
    sum_pierce += order.getDiscounted_pierce() * order.getCount();
    user_lbl_sum_count.setText("Toplam Adet= " + sum_count + " adt");
    user_lbl_sum_pierce.setText(String.format("Toplam Tutar= %.02f TL", sum_pierce));

  }

}
