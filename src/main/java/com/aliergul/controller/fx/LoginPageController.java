package com.aliergul.controller.fx;

import java.util.Optional;
import com.aliergul.controller.entity.user.UserControllerImpl;
import com.aliergul.entity.UserEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginPageController {
  // Login View items
  @FXML
  private Button btn_login;

  @FXML
  private TextField edt_user_email;

  @FXML
  private PasswordField edt_user_password;

  // Register View items
  @FXML
  private Button register_btn;

  @FXML
  private TextField register_edt_email;

  @FXML
  private PasswordField register_edt_pasword;

  @FXML
  private PasswordField register_edt_re_password;

  @FXML
  private TextField register_edt_username;
  @FXML
  private TextField register_edt_surname;

  // Button On Click Listener Progress

  @FXML
  void onClickBtnLogin(MouseEvent event) {
    UserControllerImpl dao = new UserControllerImpl();

    String email = edt_user_email.getText().trim().toLowerCase();
    String password = edt_user_password.getText();

    Optional<UserEntity> optUser = dao.onLogin(new UserEntity(email, password));
    if (optUser.isEmpty()) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setHeaderText("Email ya da Şifre Hatalı");
      alert.setTitle("Giriş Başarısız");
      alert.show();
    } else {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setHeaderText("Email ya da Şifre Doğrudur");
      alert.setTitle("Giriş başarılı");
      alert.show();
    }
  }

  @FXML
  void onClickBtnRegister(MouseEvent event) {
    UserControllerImpl dao = new UserControllerImpl();
    UserEntity regUser = validationsPasswordAndEmail();
    if (regUser != null) {
      dao.onRegister(regUser);

    } else {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setHeaderText("Bilgilerin Tamamının doğru girdiğinizden emin olun");
      alert.setTitle("Kayıt Başarısız");
      alert.show();
    }


  }

  private UserEntity validationsPasswordAndEmail() {
    boolean valid = true;

    String userName = register_edt_username.getText().trim();
    String userSurname = register_edt_surname.getText().trim();
    String userEmail = register_edt_email.getText().trim().toLowerCase();
    String userPassword = register_edt_pasword.getText();
    String userRePassword = register_edt_re_password.getText();

    if (userName.isEmpty()) {
      valid = false;
    } else if (userEmail.isEmpty()) {
      valid = false;
    } else if (userPassword.isEmpty()) {
      valid = false;
    } else if (!userPassword.equals(userRePassword)) {
      valid = false;
    }

    if (valid) {
      return new UserEntity(userName, userSurname, userEmail, userPassword);
    } else {
      return null;
    }
  }

}
