package com.aliergul.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.OrderEntity;
import com.aliergul.entity.SingerEntity;
import com.aliergul.entity.UserEntity;

public class HibernateUtils {
  private static final SessionFactory sessionFactory = sessionFactory();

  private static SessionFactory sessionFactory() {
    try {
      Configuration configuration = new Configuration();

      // entity class'larımızı buraya ekleyeceğiz

      configuration.addAnnotatedClass(UserEntity.class);
      configuration.addAnnotatedClass(AlbumEntity.class);
      configuration.addAnnotatedClass(SingerEntity.class);
      configuration.addAnnotatedClass(OrderEntity.class);
      SessionFactory factory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();

      return factory;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}
