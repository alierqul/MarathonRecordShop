package com.aliergul.fx.admin;

import java.io.IOException;
import com.aliergul.FXMain;
import com.aliergul.dao.CategoriesControllerImpls;
import com.aliergul.entity.CategoryEntity;
import com.aliergul.util.MyDialogHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class NewCategoryAddedController {
  private CategoriesControllerImpls singerController = new CategoriesControllerImpls();
  ObservableList<CategoryEntity> categories = FXCollections.observableArrayList();
  private CategoryEntity chooseCategory = null;

  @FXML
  private Button category_btn_back;

  @FXML
  private Button category_btn_delete;

  @FXML
  private Button category_btn_new;

  @FXML
  private Button category_btn_save;

  @FXML
  private TextField category_edt_name;

  @FXML
  private TableColumn<CategoryEntity, String> category_table_colum_name;

  @FXML
  private TableView<CategoryEntity> category_table_view;

  @FXML
  private TextArea singer_edt_searchtext;

  private FXMain main;

  @FXML
  void onBackMainMenu(MouseEvent event) {
    try {
      main.loadAdminPage();
    } catch (IOException e) {

    }
  }

  @FXML
  void onDeletedCategory(MouseEvent event) {
    if (chooseCategory.getId() > 0) {

      if (MyDialogHelper.getInstance.alertDialog("Silme İşlemi",
          chooseCategory.getCategory() + " Kategorisi Silinecektir",
          "Silme İşlemini Onaylıyor musunuz?")) {
        if (singerController.delete(chooseCategory)) {
          onNewCategory(null);
          categories.removeAll();
          categories.setAll(singerController.list());

        }
      }

    }
  }

  @FXML
  void onNewCategory(MouseEvent event) {
    chooseCategory = new CategoryEntity();
    category_btn_save.setDisable(false);
    category_btn_new.setDisable(true);
    category_btn_delete.setDisable(true);
    category_edt_name.setText("");

  }

  @FXML
  void onSaveCategory(MouseEvent event) {
    chooseCategory.setCategory(category_edt_name.getText());


    if (chooseCategory.getCategory().trim().length() >= 2) {
      boolean result = false;
      if (chooseCategory.getId() <= 0) {
        result = singerController.create(chooseCategory);
      } else {
        result = singerController.update(chooseCategory);
      }

      if (result) {
        categories.setAll(singerController.list());
        onNewCategory(null);
      } else {
        MyDialogHelper.getInstance.showErrorMessage("HATA", chooseCategory.getCategory(),
            "Kaydedilemedi");

      }

    }
  }

  public void initAdminPanel(FXMain main) {
    this.main = main;
    onNewCategory(null);

    categories.setAll(singerController.list());
    category_table_view.setItems(categories);
    category_table_colum_name
        .setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getCategory()));


    category_table_view.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<CategoryEntity>() {



          @Override
          public void changed(ObservableValue<? extends CategoryEntity> observable,
              CategoryEntity oldValue, CategoryEntity newValue) {
            if (newValue != null) {
              chooseCategory = newValue;
              category_edt_name.setText(newValue.getCategory());

              category_btn_delete.setDisable(false);
              category_btn_new.setDisable(false);
            }


          }
        });


  }

}
