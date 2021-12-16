package com.aliergul.controller.singer;

import org.hibernate.Session;
import com.aliergul.entity.SingerEntity;
import com.aliergul.util.HibernateUtils;

public interface ISingerControlable {
  public boolean create(SingerEntity singer);

  public boolean delete(SingerEntity singer);

  public SingerEntity find(long id);

  default Session databaseConnectionHibernate() {
    return HibernateUtils.getSessionFactory().openSession();
  }

}
