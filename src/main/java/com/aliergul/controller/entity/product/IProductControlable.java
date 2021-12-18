package com.aliergul.controller.entity.product;

import java.util.List;
import org.hibernate.Session;
import com.aliergul.entity.ProductEntity;
import com.aliergul.util.HibernateUtils;

public interface IProductControlable {

  public boolean create(ProductEntity product);

  public boolean delete(ProductEntity product);

  public boolean update(ProductEntity product);

  public List<ProductEntity> list();

  public ProductEntity find(long id);

  default Session databaseConnectionHibernate() {
    return HibernateUtils.getSessionFactory().openSession();
  }
}
