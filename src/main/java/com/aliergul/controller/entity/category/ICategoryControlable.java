package com.aliergul.controller.entity.category;

import java.util.List;
import org.hibernate.Session;
import com.aliergul.entity.CategoryEntity;
import com.aliergul.util.HibernateUtils;

public interface ICategoryControlable {

  public boolean create(CategoryEntity category);

  public boolean delete(CategoryEntity category);


  public List<CategoryEntity> list();

  public CategoryEntity find(long id);

  default Session databaseConnectionHibernate() {
    return HibernateUtils.getSessionFactory().openSession();
  }
}
