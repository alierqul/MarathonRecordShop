package com.aliergul.fx.admin;

import java.util.Optional;
import com.aliergul.dao.SingerController;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class NewSingerAddedController {

  private SingerController singerController = new SingerController();
  ObservableList<SingerEntity> singers = FXCollections.observableArrayList();
  SingerEntity chooseSinger;

  @FXML
  private Button singer_btn_delete;

  @FXML
  private Button singer_btn_new;

  @FXML
  private Button singer_btn_save;

  @FXML
  private TextField singer_edt_bio;

  @FXML
  private TextField singer_edt_name;

  @FXML
  private TextArea singer_edt_searchtext;

  @FXML
  private TextField singer_edt_surname;

  @FXML
  private TableColumn<SingerEntity, String> singer_table_colum_name;

  @FXML
  private TableColumn<SingerEntity, String> singer_table_colum_surname;

  @FXML
  private TableView<SingerEntity> singer_table_view;

  @FXML
  void onDeletedSinger(MouseEvent event) {
    if (chooseSinger.getId() > 0) {
      if (alertDialog("Silme İşlemi",
          chooseSinger.getName() + " " + chooseSinger.getSurname() + " Sanatçısı Silinecektir",
          "Silme İşlemini Onaylıyor musunuz?")) {
        if (singerController.delete(chooseSinger)) {
          singers.removeAll();
          singers.setAll(singerController.list());
        }
      }

    }
  }

  private boolean alertDialog(String title, String header, String footer) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(footer);

    ButtonType yesButton = new ButtonType("Evet");
    ButtonType noButton = new ButtonType("Hayır");


    alert.getButtonTypes().setAll(yesButton, noButton);

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == yesButton) {
      return true;
    } else
      return false;

  }



  @FXML
  void onNewSinger(MouseEvent event) {
    chooseSinger = new SingerEntity();
    singer_btn_save.setDisable(false);
    singer_btn_new.setDisable(true);
    singer_btn_delete.setDisable(true);
    singer_edt_name.setText("");
    singer_edt_surname.setText("");
    singer_edt_bio.setText("");

  }

  @FXML
  void onSaveSinger(MouseEvent event) {
    chooseSinger.setName(singer_edt_name.getText());
    chooseSinger.setSurname(singer_edt_surname.getText());
    chooseSinger.setBio(singer_edt_bio.getText());

    if (chooseSinger.getName().trim().length() >= 2) {
      boolean result = false;
      if (chooseSinger.getId() <= 0) {
        result = singerController.create(chooseSinger);
      } else {
        result = singerController.update(chooseSinger);
      }

      if (result) {
        singers.setAll(singerController.list());
        onNewSinger(null);
      } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText("Kayıt Başarısız");
        alert.setTitle("HATA");
        alert.show();
      }

    }
  }



  public void initAdminPanel() {

    onNewSinger(null);
    singers.setAll(singerController.list());
    singer_table_view.setItems(singers);
    singer_table_colum_name
        .setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getName()));
    singer_table_colum_surname
        .setCellValueFactory(v -> new SimpleStringProperty(v.getValue().getSurname()));

    singer_table_view.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<SingerEntity>() {

          @Override
          public void changed(ObservableValue<? extends SingerEntity> observable,
              SingerEntity oldValue, SingerEntity newValue) {
            if (newValue != null) {
              chooseSinger = newValue;
              singer_edt_name.setText(newValue.getName());
              singer_edt_surname.setText(newValue.getSurname());
              singer_edt_bio.setText(newValue.getBio());
              singer_btn_delete.setDisable(false);
              singer_btn_new.setDisable(false);
            }


          }
        });
  }

}
