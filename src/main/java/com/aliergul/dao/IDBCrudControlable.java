package com.aliergul.dao;

import java.util.List;
import org.hibernate.Session;
import com.aliergul.util.HibernateUtils;

public interface IDBCrudControlable<T> {

  public boolean create(T entity);

  public boolean delete(T entity);

  public boolean update(T entity);

  public List<T> list();

  public T find(long id);

  default Session databaseConnectionHibernate() {
    return HibernateUtils.getSessionFactory().openSession();
  }
}
