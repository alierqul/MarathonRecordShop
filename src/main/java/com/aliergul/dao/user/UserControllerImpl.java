package com.aliergul.dao.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.dao.IDBCrudControlable;
import com.aliergul.entity.UserEntity;
import com.aliergul.util.EStatus;

public class UserControllerImpl implements IUserLoginable, IDBCrudControlable<UserEntity> {

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
  public boolean onRegister(UserEntity user) {
    return create(user);
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

  @Override
  public List<UserEntity> list() {
    Session session = databaseConnectionHibernate();

    String hql = "select u from UserEntity as u where u.id>=:startCount and u.status=:status";
    TypedQuery<UserEntity> typedQuery = session.createQuery(hql, UserEntity.class);
    typedQuery.setParameter("startCount", 1L);
    typedQuery.setParameter("status", EStatus.ACTIVE);
    ArrayList<UserEntity> arrayList = (ArrayList<UserEntity>) typedQuery.getResultList();

    return arrayList;
  }

  @Override
  public boolean create(UserEntity entity) {
    try {
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();
      session.persist(entity);
      session.getTransaction().commit();

      sendActivasyonMail(entity.getEmail());
      logger.info(TAG + "/ onRegister / isSuccesful \n" + entity.toString());
      return true;
    } catch (Exception e) {
      logger.error(TAG + "/ onRegister / ERROR:\n" + entity.toString() + "\n" + e.getMessage());

    }
    return false;
  }

  @Override
  public boolean delete(UserEntity entity) {
    try {
      UserEntity findEntity = find(entity.getId());
      if (findEntity != null) {
        findEntity.setStatus(EStatus.DELETED);
        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.merge(findEntity);
        session.getTransaction().commit();
        logger.info(TAG + "/ onDeleted / isSuccesful \n" + findEntity.toString());
        return true;
      }
    } catch (Exception e) {
      logger.error(TAG + "/ onDeleted / ERROR:\n" + entity.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean update(UserEntity entity) {
    try {
      UserEntity findEntity = find(entity.getId());
      if (findEntity != null) {
        findEntity.setEmail(entity.getEmail());
        findEntity.setPasssword(entity.getPasssword());
        findEntity.setName(entity.getName());
        findEntity.setSurname(entity.getSurname());
        findEntity.setAddress(entity.getAddress());
        findEntity.setPhone(entity.getPhone());
        findEntity.setStatus(entity.getStatus());


        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.merge(findEntity);
        session.getTransaction().commit();


        logger.info(TAG + "/ onUpdate / isSuccesful \n" + findEntity.toString());
        return true;
      }

    } catch (Exception e) {
      logger.error(TAG + "/ onUpdate / ERROR:\n" + entity.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }

    return false;
  }



}
