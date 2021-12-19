package com.aliergul.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.dao.user.UserControllerImpl;
import com.aliergul.entity.OrderEntity;
import com.aliergul.entity.ProductEntity;
import com.aliergul.util.EStatus;
import com.aliergul.util.exception.ExceptionNotSalableProduct;

public class OrderControlerImpl implements IDBCrudControlable<OrderEntity> {

  private static final Logger logger = LogManager.getLogger(UserControllerImpl.class);
  private static final String TAG = "OrderControlerImpl=";

  @Override
  public boolean create(OrderEntity order) {

    try {
      if (order.getProduct().getStatus() == EStatus.PASIF) {
        throw new ExceptionNotSalableProduct("Ürün Satılmaz!");
      }
      ProductEntity product = order.getProduct();
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();
      session.persist(order);
      session.getTransaction().commit();
      logger.info(TAG + "/ create / isSuccesful \n" + order.toString());

      ProductControllerImpl productController = new ProductControllerImpl();
      product.setSalesCount(product.getSalesCount() + order.getCount());// 10+2=12 satldı
      product.setStockCount(product.getStockCount() - order.getCount());// 10-2=8 tane stockta

      productController.update(product);

      return true;

    } catch (Exception e) {
      logger.error(TAG + "/ create / ERROR:\n" + order.toString() + "\n" + e.getMessage());

    }
    return false;
  }

  @Override
  public boolean delete(OrderEntity order) {
    try {
      OrderEntity findEntity = find(order.getId());
      if (findEntity != null) {
        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.remove(findEntity);
        session.getTransaction().commit();
        logger.info(TAG + "/ delete / isSuccesful \n" + order.toString());
        return true;
      }
    } catch (Exception e) {
      logger.error(TAG + "/ delete / ERROR:\n" + order.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public List<OrderEntity> list() {
    Session session = databaseConnectionHibernate();

    String hql = "select u from OrderEntity as u where u.id>=:startCount";
    TypedQuery<OrderEntity> typedQuery = session.createQuery(hql, OrderEntity.class);
    typedQuery.setParameter("startCount", 1L);

    ArrayList<OrderEntity> arrayList = (ArrayList<OrderEntity>) typedQuery.getResultList();

    return arrayList;
  }

  @Override
  public OrderEntity find(long id) {
    Session session = databaseConnectionHibernate();
    OrderEntity entity;
    try {
      entity = session.find(OrderEntity.class, id);

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
  public boolean update(OrderEntity entity) {
    try {
      OrderEntity findEntity = find(entity.getId());
      if (findEntity != null) {

        findEntity.setCount(entity.getCount());
        findEntity.setProduct(entity.getProduct());
        findEntity.setSumPierce(entity.getSumPierce());
        findEntity.setUser(entity.getUser());

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
