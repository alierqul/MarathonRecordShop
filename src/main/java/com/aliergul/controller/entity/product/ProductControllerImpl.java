package com.aliergul.controller.entity.product;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.controller.entity.user.UserControllerImpl;
import com.aliergul.entity.ProductEntity;
import com.aliergul.util.EStatus;

public class ProductControllerImpl implements IProductControlable {

  private static final Logger logger = LogManager.getLogger(UserControllerImpl.class);
  private static final String TAG = "ProductControllerImpl=";

  @Override
  public boolean create(ProductEntity product) {
    try {
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();
      session.persist(product);
      session.getTransaction().commit();
      logger.info(TAG + "/ create / isSuccesful \n" + product.toString());
      return true;

    } catch (Exception e) {
      logger.error(TAG + "/ create / ERROR:\n" + product.toString() + "\n" + e.getMessage());

    }
    return false;
  }

  @Override
  public boolean delete(ProductEntity product) {
    try {
      ProductEntity findEntity = find(product.getId());
      if (findEntity != null) {
        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.remove(findEntity);
        session.getTransaction().commit();
        logger.info(TAG + "/ delete / isSuccesful \n" + product.toString());
        return true;
      }
    } catch (Exception e) {
      logger.error(TAG + "/ delete / ERROR:\n" + product.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<ProductEntity> list() {
    Session session = databaseConnectionHibernate();

    String hql = "select u from ProductEntity as u where u.id>=:startCount";
    TypedQuery<ProductEntity> typedQuery = session.createQuery(hql, ProductEntity.class);
    typedQuery.setParameter("startCount", 1L);

    ArrayList<ProductEntity> arrayList = (ArrayList<ProductEntity>) typedQuery.getResultList();

    return arrayList;
  }

  @Override
  public ProductEntity find(long id) {
    Session session = databaseConnectionHibernate();
    ProductEntity entity;
    try {
      entity = session.find(ProductEntity.class, id);

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
  public boolean update(ProductEntity product) {
    try {
      ProductEntity findEntity = find(product.getId());
      if (findEntity != null) {

        if (product.getStockCount() < 2) {
          product.setStatus(EStatus.PASIF);
        }
        findEntity.setAlbum(product.getAlbum());
        findEntity.setDiscountRate(product.getDiscountRate());
        findEntity.setPierce(product.getPierce());
        findEntity.setSalesCount(product.getSalesCount());
        findEntity.setStockCount(product.getStockCount());
        findEntity.setType(product.getType());


        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.merge(findEntity);
        session.getTransaction().commit();


        logger.info(TAG + "/ onUpdate / isSuccesful \n" + findEntity.toString());
        return true;
      }

    } catch (Exception e) {
      logger.error(TAG + "/ onUpdate / ERROR:\n" + product.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }

    return false;
  }

}
