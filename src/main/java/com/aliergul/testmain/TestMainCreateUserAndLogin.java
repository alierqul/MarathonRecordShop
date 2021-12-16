package com.aliergul.testmain;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.aliergul.controller.album.AlbumController;
import com.aliergul.controller.singer.SingerController;
import com.aliergul.controller.user.UserControllerImpl;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.SingerEntity;
import com.aliergul.entity.UserEntity;

public class TestMainCreateUserAndLogin {
  private static final String TAG = "TestMainCreateUserAndLogin";
  private static final Logger logger = LogManager.getLogger(TestMainCreateUserAndLogin.class);

  public static void main(String[] args) {

    test_01_loginAndRegister();
    test_02_addNewAlbum();

  }

  private static void test_02_addNewAlbum() {
    AlbumController albumController = new AlbumController();
    SingerController singerController = new SingerController();
    // String name, double pierce, long stockCount
    AlbumEntity a1 = new AlbumEntity("Sevmemeliz", 29.90, 50);
    AlbumEntity a2 = new AlbumEntity("insan gelir insan geçer", 29.90, 50);
    AlbumEntity a3 = new AlbumEntity("Yerine Sevemem", 19.90, 50);
    // String name, String surname, String bio
    SingerEntity s1 = new SingerEntity("Sena", "Şener", "kadın - yaşıyor");
    SingerEntity s2 = new SingerEntity("Gökhan", "Kırdar", "erkek - yaşıyor");

    singerController.create(s1);
    logger.info(TAG + " - CREATE  SENA ŞENER ADD TABLE -  ");
    singerController.create(s2);
    logger.info(TAG + " - CREATE GÖKHAN KIRDAR ADD TABLE - ");

    s1 = singerController.find(3);
    a1.setSinger(s1);
    a2.setSinger(s1);
    a3.setSinger(singerController.find(4));
    albumController.create(a1);
    albumController.create(a2);
    albumController.create(a3);


  }

  /**
   * Login ve register kayıt işlemi
   */
  private static void test_01_loginAndRegister() {
    UserControllerImpl uController = new UserControllerImpl();
    // String name, String surname, String email, String passsword
    UserEntity u1_noAdress = new UserEntity("Ali", "ergul", "ali", "123");
    UserEntity u1_yesAdress = new UserEntity("Ali", "ergul", "ali1", "123");
    u1_yesAdress.setAddress("malatya");
    u1_yesAdress.setPhone("543 123 45 67");

    uController.onRegister(u1_yesAdress);
    uController.onRegister(u1_noAdress);

    logger.info(TAG + " - LOGIN TRY NUMBER ONE -  ");
    if (!uController.onLogin(new UserEntity("ali", "123")).isEmpty()) {
      logger.info(TAG + " - Login is Successful ");
    } else {
      logger.warn(TAG + " - Login failed");
    }

    logger.info(TAG + " - LOGIN TRY NUMBER TWO - ");
    if (!uController.onLogin(new UserEntity("ali", "1234")).isEmpty()) {
      logger.info(TAG + " - Login is Successful ");
    } else {
      logger.warn(TAG + " - Login failed");
    }

  }


}
