package com.aliergul.controller.entity.order;

import java.util.List;
import org.hibernate.Session;
import com.aliergul.entity.OrderEntity;
import com.aliergul.util.HibernateUtils;

public interface IOrderControlable {

  public boolean create(OrderEntity order);

  public boolean delete(OrderEntity order);

  public List<OrderEntity> list();

  public OrderEntity find(long id);

  default Session databaseConnectionHibernate() {
    return HibernateUtils.getSessionFactory().openSession();
  }
}
