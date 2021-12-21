package com.aliergul.fx.admin;

import java.io.IOException;
import com.aliergul.FXMain;
import com.aliergul.dao.ProductTypeControllerImpl;
import com.aliergul.entity.ProductTypeEntity;
import com.aliergul.util.EDiskDiameter;
import com.aliergul.util.EDiskQuality;
import com.aliergul.util.EProduct;
import com.aliergul.util.MyDialogHelper;
import com.aliergul.util.exception.ExceptionNotImageQuality;
import com.aliergul.util.exception.ExceptionNotInformationRunSpeedOrDisDiameter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class NewRecordTypeController {

  private ProductTypeControllerImpl controllerRecord = new ProductTypeControllerImpl();
  ObservableList<ProductTypeEntity> records = FXCollections.observableArrayList();
  ProductTypeEntity chooseRecordType;

  @FXML
  private Button record_btn_back;

  @FXML
  private Button record_btn_delete;

  @FXML
  private Button record_btn_new;

  @FXML
  private Button record_btn_save;

  @FXML
  private ComboBox<EDiskDiameter> record_combo_diameter;

  @FXML
  private ComboBox<EDiskQuality> record_combo_quality;

  @FXML
  private ComboBox<EDiskDiameter> record_combo_runspeed;

  @FXML
  private ComboBox<EProduct> record_combo_type;

  @FXML
  private TextField record_edt_secription;

  @FXML
  private TableColumn<ProductTypeEntity, String> record_table_colum_name;

  @FXML
  private TableView<ProductTypeEntity> record_table_view;

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
  void onDeletedRecord(MouseEvent event) {
    boolean result = false;
    if (chooseRecordType != null) {

      result = controllerRecord.delete(chooseRecordType);
    }

    if (result) {
      MyDialogHelper.getInstance.showMessage("Başarılı", "Kayıt Silindi", null);
      onNewRecord(null);
      records.setAll(controllerRecord.list());
    } else {
      MyDialogHelper.getInstance.showErrorMessage("Üzgünüm...", "Silinemedi.",
          "Daha sonra tekrar deneyiniz.");
    }
  }

  @FXML
  void onNewRecord(MouseEvent event) {
    chooseRecordType = null;
    record_btn_save.setDisable(false);
    record_btn_new.setDisable(true);
    record_btn_delete.setDisable(true);

    record_combo_diameter.setItems(FXCollections.observableArrayList(EDiskDiameter.values()));
    record_combo_quality.setItems(FXCollections.observableArrayList(EDiskQuality.values()));
    record_combo_type.setItems(FXCollections.observableArrayList(EProduct.values()));
    record_combo_runspeed.setItems(FXCollections.observableArrayList(EDiskDiameter.values()));

  }

  @FXML
  void onSaveRecord(MouseEvent event) {
    try {

      boolean result = false;
      if (chooseRecordType == null) {
        chooseRecordType =
            new ProductTypeEntity(record_combo_type.getSelectionModel().getSelectedItem(),
                record_combo_quality.getSelectionModel().getSelectedItem(),
                record_combo_diameter.getSelectionModel().getSelectedItem(),
                record_combo_runspeed.getSelectionModel().getSelectedItem(),
                record_edt_secription.getText());
        result = controllerRecord.create(chooseRecordType);
      } else {
        chooseRecordType = new ProductTypeEntity(chooseRecordType.getId(),
            record_combo_type.getSelectionModel().getSelectedItem(),
            record_combo_quality.getSelectionModel().getSelectedItem(),
            record_combo_diameter.getSelectionModel().getSelectedItem(),
            record_combo_runspeed.getSelectionModel().getSelectedItem(),
            record_edt_secription.getText(), chooseRecordType.getProducts(),
            chooseRecordType.getCreateDate());
        result = controllerRecord.update(chooseRecordType);
      }

      if (result) {
        MyDialogHelper.getInstance.showMessage("Başarılı", "Kayıt Tamamlandı", null);
        onNewRecord(null);
        records.setAll(controllerRecord.list());
      } else {
        MyDialogHelper.getInstance.showErrorMessage("Üzgünüm...", "Kaydedilemedi.",
            "Daha sonra tekrar deneyiniz.");
      }
    } catch (ExceptionNotImageQuality | ExceptionNotInformationRunSpeedOrDisDiameter e) {
      MyDialogHelper.getInstance.showErrorMessage("Eksik Bilgi",
          "Aşağıdaki bilgileri girmeniz lazım", e.getMessage());

    }
  }

  public void initAdminPanel(FXMain fxMain) {
    this.main = fxMain;

    record_combo_diameter.setItems(FXCollections.observableArrayList(EDiskDiameter.values()));
    record_combo_quality.setItems(FXCollections.observableArrayList(EDiskQuality.values()));
    record_combo_type.setItems(FXCollections.observableArrayList(EProduct.values()));
    record_combo_runspeed.setItems(FXCollections.observableArrayList(EDiskDiameter.values()));

    onNewRecord(null);
    records.setAll(controllerRecord.list());
    record_table_view.setItems(records);
    record_table_colum_name.setCellValueFactory(v -> new SimpleStringProperty(
        v.getValue().getType() + " - " + v.getValue().getDescriptions()));


    record_table_view.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<ProductTypeEntity>() {

          @Override
          public void changed(ObservableValue<? extends ProductTypeEntity> observable,
              ProductTypeEntity oldValue, ProductTypeEntity newValue) {
            if (newValue != null) {
              chooseRecordType = newValue;
              record_combo_diameter.getSelectionModel().select(newValue.getDiscDiameter());
              record_combo_quality.getSelectionModel().select(newValue.getImageQuality());
              record_combo_type.getSelectionModel().select(newValue.getType());
              record_combo_runspeed.getSelectionModel().select(newValue.getRunSpeed());
              record_edt_secription.setText(newValue.getDescriptions());
              record_btn_save.setDisable(false);
              record_btn_new.setDisable(false);
              record_btn_delete.setDisable(false);
            }


          }
        });
  }

}
