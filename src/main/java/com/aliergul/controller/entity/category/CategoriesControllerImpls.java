package com.aliergul.controller.entity.category;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.controller.entity.user.UserControllerImpl;
import com.aliergul.entity.CategoryEntity;

public class CategoriesControllerImpls implements ICategoryControlable {
  private static final Logger logger = LogManager.getLogger(UserControllerImpl.class);
  private static final String TAG = "CategoriesControllerImpls=";

  @Override
  public boolean create(CategoryEntity category) {
    try {
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();
      session.persist(category);
      session.getTransaction().commit();
      logger.info(TAG + "/ create / isSuccesful \n" + category.toString());
      return true;
    } catch (Exception e) {
      logger.error(TAG + "/ create / ERROR:\n" + category + "\n" + e.getMessage());

    }
    return false;
  }

  @Override
  public boolean delete(CategoryEntity category) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<CategoryEntity> list() {
    Session session = databaseConnectionHibernate();

    String hql = "select c from CategoryEntity as c where c.id>=:startCount";
    TypedQuery<CategoryEntity> typedQuery = session.createQuery(hql, CategoryEntity.class);
    typedQuery.setParameter("startCount", 1L);

    ArrayList<CategoryEntity> arrayList = (ArrayList<CategoryEntity>) typedQuery.getResultList();

    return arrayList;
  }

  @Override
  public CategoryEntity find(long id) {
    Session session = databaseConnectionHibernate();
    CategoryEntity entity;
    try {
      entity = session.find(CategoryEntity.class, id);

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
