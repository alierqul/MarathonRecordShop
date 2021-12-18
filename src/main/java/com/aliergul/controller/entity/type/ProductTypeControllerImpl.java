package com.aliergul.controller.entity.type;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.entity.ProductTypeEntity;

public class ProductTypeControllerImpl implements ITypeProductControlable<ProductTypeEntity> {

  private static final Logger logger = LogManager.getLogger(ProductTypeControllerImpl.class);
  private static final String TAG = "ProductTypeControllerImpl=";

  @Override
  public boolean create(ProductTypeEntity entity) {
    try {
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();
      session.persist(entity);
      session.getTransaction().commit();
      logger.info(TAG + "/ create / isSuccesful \n" + entity.toString());
      return true;

    } catch (Exception e) {
      logger.error(TAG + "/ create / ERROR:\n" + entity.toString() + "\n" + e.getMessage());

    }
    return false;

  }

  @Override
  public boolean delete(ProductTypeEntity entity) {
    try {
      ProductTypeEntity findEntity = find(entity.getId());
      if (findEntity != null) {
        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.remove(findEntity);
        session.getTransaction().commit();
        logger.info(TAG + "/ delete / isSuccesful \n" + entity.toString());
        return true;
      }
    } catch (Exception e) {
      logger.error(TAG + "/ delete / ERROR:\n" + entity.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean update(ProductTypeEntity entity) {
    try {
      ProductTypeEntity findEntity = find(entity.getId());
      if (findEntity != null) {

        findEntity.setDescriptions(entity.getDescriptions());
        findEntity.setDiscDiameter(entity.getDiscDiameter());
        findEntity.setImageQuality(entity.getImageQuality());
        findEntity.setRunSpeed(entity.getRunSpeed());
        findEntity.setType(entity.getType());

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

  @Override
  public List<ProductTypeEntity> list() {
    Session session = databaseConnectionHibernate();

    String hql = "select u from ProductTypeEntity as u where u.id>=:startCount";
    TypedQuery<ProductTypeEntity> typedQuery = session.createQuery(hql, ProductTypeEntity.class);
    typedQuery.setParameter("startCount", 0L);

    ArrayList<ProductTypeEntity> arrayList =
        (ArrayList<ProductTypeEntity>) typedQuery.getResultList();

    return arrayList;
  }

  @Override
  public ProductTypeEntity find(long id) {
    Session session = databaseConnectionHibernate();
    ProductTypeEntity entity;
    try {
      entity = session.find(ProductTypeEntity.class, id);

      if (entity != null) {
        return entity;
      } else {
        return null;
      }
    } catch (Exception e) {

    }
    return null;
  }

  // @Override
  // public boolean create(ProductEntity product) {

  // }
  //
  // @Override
  // public boolean delete(ProductEntity product) {

  // }
  //
  // @Override
  // public List<ProductEntity> list() {

  // }
  //
  // @Override
  // public ProductEntity find(long id) {

  // }
  //
  // @Override
  // public boolean update(ProductEntity product) {

  // }

}
