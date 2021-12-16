package com.aliergul.testmain;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.aliergul.controller.album.AlbumController;
import com.aliergul.controller.order.OrderControlerImpl;
import com.aliergul.controller.singer.SingerController;
import com.aliergul.controller.user.UserControllerImpl;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.OrderEntity;
import com.aliergul.entity.SingerEntity;
import com.aliergul.entity.UserEntity;
import com.aliergul.util.ExceptionLowStockCount;
import com.aliergul.util.MenuBuilder;

public enum TestMenu {
  getInstance;

  private static final String TAG = "TestMenu";
  private static final Logger logger = LogManager.getLogger(TestMenu.class);

  private UserControllerImpl uController = new UserControllerImpl();
  private AlbumController albumController = new AlbumController();
  private SingerController singerController = new SingerController();
  private OrderControlerImpl orderController = new OrderControlerImpl();

  public void showMenu() {
    mainTest1().show();
    test_01_loginAndRegister();
    mainTest2().show();
    test_02_addNewAlbum();
    mainTest3().show();
    test_03_addOrderBy();

  }

  void test_03_addOrderBy() {

    // AlbumEntity album, UserEntity user, long count
    AlbumEntity album = albumController.find(6L);
    logger.info(TAG + " -AlbumEntity=  -  " + album);
    UserEntity user = uController.find(1L);
    logger.info(TAG + " - UserEntity -  " + user);
    OrderEntity o1 = null;
    try {
      o1 = new OrderEntity(album, user, 2L);
      orderController.create(o1);
    } catch (ExceptionLowStockCount e) {
      logger.info(TAG + " - OrderBy  -  " + album + e.getMessage());
      e.printStackTrace();
    }



  }

  private MenuBuilder mainTest1() {

    return new MenuBuilder.Builder().title("Record Shop Test APP")
        .body("Adım Bir Önce Database Oluşturalım.")
        .addRow("Database Oluşturduktan sonra 1 e basınız.").build();
  }

  private MenuBuilder mainTest2() {

    return new MenuBuilder.Builder().title("Record Shop Test APP")
        .body("Şarkıcı ve şarkı ekliyoruz....").addRow("devam etmek için 1 e basınız").build();
  }

  private MenuBuilder mainTest3() {

    return new MenuBuilder.Builder().title("Record Shop Test APP")
        .body("Bir Kaç Sipraş Giriyoruz....").addRow("1 e basmayı unutmayın :D ").build();
  }

  /**
   * Login ve register kayıt işlemi
   */
  private void test_01_loginAndRegister() {


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

  private void test_02_addNewAlbum() {

    // String name, double pierce, long stockCount
    AlbumEntity a1 = new AlbumEntity("Sevmemeliz", 29.90, 50);
    AlbumEntity a2 = new AlbumEntity("insan gelir insan geçer", 29.90, 50);
    a2.setDiscountRate(10);
    AlbumEntity a3 = new AlbumEntity("Yerine Sevemem", 19.90, 50);
    // String name, String surname, String bio
    SingerEntity s1 = new SingerEntity("Sena", "Şener", "kadın - yaşıyor");
    SingerEntity s2 = new SingerEntity("Gökhan", "Kırdar", "erkek - yaşıyor");

    singerController.create(s1);
    logger.info(TAG + " - ********************************** - ");
    logger.info(TAG + " - CREATE  SENA ŞENER ADD TABLE -  ");
    logger.info(TAG + " - ********************************** - ");
    logger.info(TAG + " - ********************************** - ");
    singerController.create(s2);
    logger.info(TAG + " - CREATE GÖKHAN KIRDAR ADD TABLE - ");
    logger.info(TAG + " - ********************************** - ");
    logger.info(TAG + " - ********************************** - ");
    s1 = singerController.find(3);
    a1.setSinger(s1);
    a2.setSinger(s1);
    a3.setSinger(singerController.find(4));
    albumController.create(a1);
    albumController.create(a2);
    albumController.create(a3);

    System.out.println("**********************************");
    System.out.println("**********************************");


  }



}
