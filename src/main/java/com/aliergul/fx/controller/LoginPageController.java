package com.aliergul.fx.controller;

import java.io.IOException;
import java.util.Optional;
import com.aliergul.FXMain;
import com.aliergul.dao.user.UserControllerImpl;
import com.aliergul.entity.UserEntity;
import com.aliergul.util.MyDialogHelper;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class LoginPageController {
  private static final String ADMIN = "admin";
  private static final String PASSWORD = "qwerty";
  private FXMain main;

  @FXML
  private ProgressIndicator login_progressbar;

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
    changedButtonAndProgresbar(true);
    PauseTransition pt = new PauseTransition(Duration.seconds(3));
    pt.setOnFinished(event1 -> {
      try {
        checkLogin();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    pt.play();
    /*****************/

  }

  @FXML
  void onClickBtnRegister(MouseEvent event) {

    changedButtonAndProgresbar(true);
    PauseTransition pt = new PauseTransition(Duration.seconds(3));
    pt.setOnFinished(event1 -> {
      checkRegister();
    });
    pt.play();

  }

  private void checkRegister() {
    boolean result = false;
    UserControllerImpl dao = new UserControllerImpl();
    UserEntity regUser = validationsPasswordAndEmail();
    if (regUser != null) {
      result = dao.onRegister(regUser);

    }
    if (result) {
      changedButtonAndProgresbar(false);
      MyDialogHelper.getInstance.showMessage("Kayıt Başarılı", "Teblikler. Kaydınız alındı.",
          "Kaydınız tamamlandı. Lütfen E-posta adresinizi kontrol ediniz.");

    } else {
      changedButtonAndProgresbar(false);
      MyDialogHelper.getInstance.showErrorMessage("Kayıt Başarısız",
          "Bilgilerin Tamamının doğru girdiğinizden emin olun", "");

    }
    changedButtonAndProgresbar(false);
  }

  public void initAdminPanel(FXMain main) {
    this.main = main;

  }

  private void checkLogin() throws IOException {
    UserControllerImpl dao = new UserControllerImpl();

    String email = edt_user_email.getText().trim().toLowerCase();
    String password = edt_user_password.getText();

    Optional<UserEntity> optUser = dao.onLogin(new UserEntity(email, password));
    if (optUser.isEmpty()) {
      if (email.equals(ADMIN) && password.equals(PASSWORD)) {
        main.loadAdminPage();
      } else {
        MyDialogHelper.getInstance.showErrorMessage("Giriş Başarısız", "Email ya da Şifre Hatalı",
            "");
        changedButtonAndProgresbar(false);

      }

    } else {
      main.loadUserPage(optUser.get());
      changedButtonAndProgresbar(false);
      MyDialogHelper.getInstance.showMessage("Başarılı Giriş", "Giriş başarılı",
          "Hoş Geldiniz ! " + optUser.get().getName());

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

  private void changedButtonAndProgresbar(boolean active) {
    login_progressbar.setVisible(active);
    btn_login.setDisable(active);
    register_btn.setDisable(active);
  }
}
