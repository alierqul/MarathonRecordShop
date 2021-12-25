package com.aliergul.testmain;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.aliergul.dao.AlbumController;
import com.aliergul.dao.CategoriesControllerImpls;
import com.aliergul.dao.OrderControlerImpl;
import com.aliergul.dao.ProductControllerImpl;
import com.aliergul.dao.ProductTypeControllerImpl;
import com.aliergul.dao.SingerController;
import com.aliergul.dao.user.UserControllerImpl;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.CategoryEntity;
import com.aliergul.entity.OrderEntity;
import com.aliergul.entity.ProductEntity;
import com.aliergul.entity.ProductTypeEntity;
import com.aliergul.entity.SingerEntity;
import com.aliergul.entity.UserEntity;
import com.aliergul.util.EDiskQuality;
import com.aliergul.util.EProduct;
import com.aliergul.util.MenuBuilder;
import com.aliergul.util.exception.ExceptionDiscountError;
import com.aliergul.util.exception.ExceptionNotImageQuality;
import com.aliergul.util.exception.ExceptionNotInformationRunSpeedOrDisDiameter;

public enum TestMenu {
  getInstance;

  private static final String TAG = "TestMenu";
  private static final Logger logger = LogManager.getLogger(TestMenu.class);

  private UserControllerImpl uController = new UserControllerImpl();
  private AlbumController albumController = new AlbumController();
  private SingerController singerController = new SingerController();
  private OrderControlerImpl orderController = new OrderControlerImpl();
  private CategoriesControllerImpls catDao = new CategoriesControllerImpls();
  private ProductTypeControllerImpl typeControllerImpl = new ProductTypeControllerImpl();
  private ProductControllerImpl productControllerImpl = new ProductControllerImpl();

  public void showMenu() {
    mainTest1().show();
    test_01_loginAndRegister();
    mainTest2().show();
    test_02_addNewAlbum();
    mainTest3().show();
    try {
      test_03_addOrderBy();
    } catch (ExceptionDiscountError e) {
      e.printStackTrace();
    }

  }

  void test_03_addOrderBy() throws ExceptionDiscountError {

    // AlbumEntity album, UserEntity user, long count
    List<ProductEntity> listAlbum = productControllerImpl.list();
    List<UserEntity> listUser = uController.list();
    ProductEntity product = listAlbum.get(0);
    logger.info(TAG + " -ProductEntity= - " + product);
    UserEntity user = listUser.get(0);
    logger.info(TAG + " - UserEntity - " + user);
    OrderEntity o1 = null;
    o1 = new OrderEntity(product, user, 2L);
    orderController.create(o1);
    logger.info(TAG + "/ orderController / isSuccesful \n Stok Düştü");


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
    UserEntity u1_yesAdress = new UserEntity("Ali", "ergul", "mehmet", "123");
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
    /* ********************************************** */
    // DVD CD VinVyl Tanımlıyoruz
    ProductTypeEntity CD = null, DVD = null;
    try {
      CD = new ProductTypeEntity(EProduct.CD, EDiskQuality.HD, null, null, "standart");
      typeControllerImpl.create(CD);
      DVD = new ProductTypeEntity(EProduct.DVD, EDiskQuality.HD, null, null, "Standart");
      typeControllerImpl.create(DVD);
      List<ProductTypeEntity> listType = typeControllerImpl.list();
      CD = listType.get(0);// id si olan CD
      DVD = listType.get(1); // id si olan DVD
    } catch (ExceptionNotImageQuality | ExceptionNotInformationRunSpeedOrDisDiameter e) {
      logger.warn(TAG + " TEST ERROR EProduct \n" + e.getMessage());
      e.printStackTrace();
    }

    /* ********************************************** */
    Set<CategoryEntity> pop = new HashSet<CategoryEntity>();
    pop.add(listCategori.get(0));

    // Müzik türleri tanımlama
    Set<CategoryEntity> pop_slow = new HashSet<CategoryEntity>();
    pop_slow.add(listCategori.get(0));
    pop_slow.add(listCategori.get(3));

    Set<CategoryEntity> pop_slow_caz = new HashSet<CategoryEntity>();
    pop_slow_caz.add(listCategori.get(0));
    pop_slow_caz.add(listCategori.get(3));
    pop_slow_caz.add(listCategori.get(2));
    /* ********************************************** */

    // Sanatçı ekleme
    SingerEntity sena_sener = new SingerEntity("Sena", "Şener", "30 yaşında ,kadın - yaşıyor");
    SingerEntity gokhan_kirdar = new SingerEntity("Gökhan", "Kırdar", "51 yaşında erkek - yaşıyor");
    SingerEntity tarkan = new SingerEntity("Tarkan", " ", "49 yaşında erkek - yaşıyor");

    singerController.create(sena_sener);
    singerController.create(gokhan_kirdar);
    singerController.create(tarkan);
    // id'leri ile birlikte Databsedeb verlieri tekrar çekelim
    List<SingerEntity> listSinger = singerController.list();
    sena_sener = listSinger.get(0);// database den id'leri ile çekiyoruz
    gokhan_kirdar = listSinger.get(1);// database den id'leri ile çekiyoruz
    tarkan = listSinger.get(2);// database den id'leri ile çekiyoruz
    String gokhan_path = "./src/main/resources/com/aliergul/img/gokhan_kirdar.jpg";
    String sena_path = "./src/main/resources/com/aliergul/img/senasener.png";
    String tarkan_path = "./src/main/resources/com/aliergul/img/tarkan.jpg";


    /* ********************************************** */
    // Album ekleme
    AlbumEntity sevmemeliyiz = null, insanGelirInsanGecer = null;
    AlbumEntity yolla = null, dudududu = null;
    AlbumEntity serser_mayin = null;
    try {
      sevmemeliyiz = new AlbumEntity("Sevmemeliz", "", sena_sener, pop);
      sevmemeliyiz.setImgAlbum(sena_path);
      insanGelirInsanGecer =
          new AlbumEntity("insan gelir insan geçer", "", sena_sener, pop_slow_caz);
      insanGelirInsanGecer.setImgAlbum(sena_path);
      yolla = new AlbumEntity("yolla", "", tarkan, pop_slow_caz);
      yolla.setImgAlbum(tarkan_path);
      dudududu = new AlbumEntity("dudu dudu", "", tarkan, pop_slow_caz);
      dudududu.setImgAlbum(tarkan_path);
      serser_mayin = new AlbumEntity("Serseri Mayın", "", gokhan_kirdar, pop_slow);
      serser_mayin.setImgAlbum(gokhan_path);

      albumController.create(insanGelirInsanGecer);
      albumController.create(sevmemeliyiz);
      albumController.create(yolla);
      albumController.create(dudududu);
      albumController.create(serser_mayin);
      List<AlbumEntity> listAlbum = albumController.list();
      insanGelirInsanGecer = listAlbum.get(0);
      sevmemeliyiz = listAlbum.get(1);
      yolla = listAlbum.get(2);
      dudududu = listAlbum.get(3);
      serser_mayin = listAlbum.get(4);
    } catch (IOException e) {
      logger.warn(TAG + " TEST Image Loading ERROR \n" + e.getMessage());
      e.printStackTrace();
    }

    /* ********************************************** */
    // Ürün Product oluşturalım
    // AlbumEntity album, ProductTypeEntity type, double pierce, long stockCount
    ProductEntity sevmemeliyizCD = new ProductEntity(sevmemeliyiz, CD, 29.90, 10);
    ProductEntity sevmemeliyizDVD = new ProductEntity(sevmemeliyiz, DVD, 99.90, 10);
    ProductEntity insanGelirInsanGecerCD = new ProductEntity(insanGelirInsanGecer, CD, 9.90, 10);
    ProductEntity insanGelirInsanGecerDVD = new ProductEntity(insanGelirInsanGecer, DVD, 99.90, 10);
    ProductEntity yollaCD = new ProductEntity(yolla, CD, 15.90, 10);
    ProductEntity yollaDVD = new ProductEntity(yolla, CD, 39.90, 10);
    ProductEntity dudududuCD = new ProductEntity(dudududu, CD, 9.90, 10);
    ProductEntity dudududuDVD = new ProductEntity(dudududu, DVD, 19.90, 10);
    ProductEntity serser_mayinCD = new ProductEntity(serser_mayin, CD, 9.90, 10);
    ProductEntity serser_mayinDVD = new ProductEntity(serser_mayin, DVD, 19.90, 10);


    productControllerImpl.create(sevmemeliyizCD);
    productControllerImpl.create(sevmemeliyizDVD);
    productControllerImpl.create(insanGelirInsanGecerCD);
    productControllerImpl.create(insanGelirInsanGecerDVD);
    productControllerImpl.create(yollaCD);
    productControllerImpl.create(yollaDVD);
    productControllerImpl.create(dudududuCD);
    productControllerImpl.create(dudududuDVD);
    productControllerImpl.create(serser_mayinCD);
    productControllerImpl.create(serser_mayinDVD);


    /* ********************************************** */

  }



}
