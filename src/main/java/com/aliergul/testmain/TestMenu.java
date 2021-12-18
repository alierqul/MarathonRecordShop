package com.aliergul.testmain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.aliergul.controller.entity.album.AlbumController;
import com.aliergul.controller.entity.category.CategoriesControllerImpls;
import com.aliergul.controller.entity.order.OrderControlerImpl;
import com.aliergul.controller.entity.singer.SingerController;
import com.aliergul.controller.entity.user.UserControllerImpl;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.CategoryEntity;
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
  private CategoriesControllerImpls catDao = new CategoriesControllerImpls();

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
    List<AlbumEntity> listAlbum = albumController.list();
    List<UserEntity> listUser = uController.list();
    AlbumEntity album = listAlbum.get(0);
    logger.info(TAG + " -AlbumEntity= - " + album);
    UserEntity user = listUser.get(0);
    logger.info(TAG + " - UserEntity - " + user);
    OrderEntity o1 = null;
    try {
      o1 = new OrderEntity(album, user, 2L);
      orderController.create(o1);
    } catch (ExceptionLowStockCount e) {
      logger.info(TAG + " - OrderBy - " + album + e.getMessage());
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
    // Adım Bir En Küçük Parça Database e yazıır
    // Kaetegori
    CategoryEntity c1 = new CategoryEntity("POP");
    CategoryEntity c2 = new CategoryEntity("ROCK");
    CategoryEntity c3 = new CategoryEntity("CAZ");
    CategoryEntity c4 = new CategoryEntity("SLOW");
    // Yukarıdakilerden hiçbirinde id ve diğer şeyler yok.
    catDao.create(c1);
    catDao.create(c2);
    catDao.create(c3);
    catDao.create(c4);

    // kategorileri database e yazdık:
    // hepsini id'leri ile birlikte databaseden geri çekelim
    List<CategoryEntity> listCategori = catDao.list();
    // Artık hespinin id'si var
    Set<CategoryEntity> pop = new HashSet<CategoryEntity>();
    pop.add(listCategori.get(0));

    Set<CategoryEntity> pop_slow = new HashSet<CategoryEntity>();
    pop_slow.add(listCategori.get(0));
    pop_slow.add(listCategori.get(3));

    Set<CategoryEntity> pop_slow_caz = new HashSet<CategoryEntity>();
    pop_slow_caz.add(listCategori.get(0));
    pop_slow_caz.add(listCategori.get(3));
    pop_slow_caz.add(listCategori.get(2));

    // TODO DISK DVD eklenicek
    // ****

    // Sanatçı eklenicek
    SingerEntity sena_sener = new SingerEntity("Sena", "Şener", "30 yaşında ,kadın - yaşıyor");
    SingerEntity gokhan_kirdar = new SingerEntity("Gökhan", "Kırdar", "51 yaşında erkek - yaşıyor");

    singerController.create(sena_sener);
    singerController.create(gokhan_kirdar);

    List<SingerEntity> listSinger = singerController.list();
    sena_sener = listSinger.get(0);// database den id'leri ile çekiyoruz
    gokhan_kirdar = listSinger.get(1);// database den id'leri ile çekiyoruz
    AlbumEntity a1 = new AlbumEntity("Sevmemeliz", 29.90, 50);
    a1.setDiscountRate(10);// stok
    a1.setSinger(sena_sener);
    a1.setCategories(pop);
    albumController.create(a1);// database e yaz
    AlbumEntity a2 = new AlbumEntity("insan gelir insan geçer", 29.90, 50);
    a2.setCategories(pop_slow_caz);
    a2.setDiscountRate(10);// stok
    a2.setSinger(sena_sener);// sena şener
    albumController.create(a2);// database e yaz
    AlbumEntity a3 = new AlbumEntity("Yerine Sevemem", 19.90, 50);
    a3.setCategories(pop);
    a3.setDiscountRate(10);// stok
    a3.setSinger(gokhan_kirdar);// gökhan kırdar
    albumController.create(a3);// database e yaz


  }



}
