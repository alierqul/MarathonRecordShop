package com.aliergul.controller.entity.singer;

import java.util.List;
import org.hibernate.Session;
import com.aliergul.entity.SingerEntity;
import com.aliergul.util.HibernateUtils;

public interface ISingerControlable {
  public boolean create(SingerEntity singer);

  public boolean delete(SingerEntity singer);

  public SingerEntity find(long id);

  public List<SingerEntity> list();

  default Session databaseConnectionHibernate() {
    return HibernateUtils.getSessionFactory().openSession();
  }

}
