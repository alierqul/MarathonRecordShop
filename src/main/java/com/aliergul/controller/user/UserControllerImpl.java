package com.aliergul.controller.user;

import java.util.Optional;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.entity.UserEntity;

public class UserControllerImpl implements IUserLoginable {

  /*
   * import org.apache.log4j.LogManager; import org.apache.log4j.Logger;
   */
  private static final Logger logger = LogManager.getLogger(UserControllerImpl.class);
  private static final String TAG = "UserControllerImpl=";

  @Override
  public Optional<UserEntity> onLogin(UserEntity user) {
    try {
      Session session = databaseConnectionHibernate();
      String hql = "select u from UserEntity as u where u.email=:email and u.password=:password";
      TypedQuery<UserEntity> typedQuery = session.createQuery(hql, UserEntity.class);
      typedQuery.setParameter("email", user.getEmail().trim().toLowerCase());
      typedQuery.setParameter("password", user.getPasssword());
      Optional<UserEntity> optUser = Optional.of(typedQuery.getResultList().get(0));
      logger.info(TAG + "/ onLogin / isSuccesful \n" + optUser.get().toString());
      return optUser;
    } catch (Exception e) {
      logger.error(TAG + "/ onLogin / ERROR:\n" + user.toString() + "\n" + e.getMessage());

    }
    return Optional.empty();
  }

  @Override
  public Optional<UserEntity> onRegister(UserEntity user) {
    try {
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();

      session.persist(user);
      session.getTransaction().commit();


      long lastID =
          ((Number) session.createNativeQuery("select max(id) from UserEntity;").getSingleResult())
              .longValue();
      Optional<UserEntity> optUser = Optional.of(find(lastID));
      sendActivasyonMail(user.getEmail());
      logger.info(TAG + "/ onRegister / isSuccesful \n" + optUser.toString());
      return optUser;
    } catch (Exception e) {
      logger.error(TAG + "/ onRegister / ERROR:\n" + user.toString() + "\n" + e.getMessage());

    }
    return null;
  }

  @Override
  public UserEntity find(long id) {
    Session session = databaseConnectionHibernate();
    UserEntity entity;
    try {
      entity = session.find(UserEntity.class, id);

      if (entity != null) {
        return entity;
      } else {
        return null;
      }
    } catch (Exception e) {

    }
    return null;
  }

  @Override
  public void sendActivasyonMail(String email) {
    logger.info(TAG + "/ sendActivasyonMail / Activasyon Email \n" + email);

  }



}
