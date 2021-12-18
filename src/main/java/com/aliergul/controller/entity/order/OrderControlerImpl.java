package com.aliergul.controller.entity.order;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.controller.entity.album.AlbumController;
import com.aliergul.controller.entity.user.UserControllerImpl;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.OrderEntity;

public class OrderControlerImpl implements IOrderControlable {

  private static final Logger logger = LogManager.getLogger(UserControllerImpl.class);
  private static final String TAG = "OrderControlerImpl=";

  @Override
  public boolean create(OrderEntity order) {
    try {
      AlbumEntity album = order.getAlbum();
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();
      session.persist(order);
      session.getTransaction().commit();
      logger.info(TAG + "/ create / isSuccesful \n" + order.toString());

      AlbumController albumController = new AlbumController();
      album.setSalesCount(album.getSalesCount() + order.getCount());
      album.setStockCount(album.getStockCount() - order.getCount());
      albumController.update(album);

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

}