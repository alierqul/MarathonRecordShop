package com.aliergul.controller.user;

import java.util.Optional;
import org.hibernate.Session;
import com.aliergul.entity.UserEntity;
import com.aliergul.util.HibernateUtils;


public interface IUserLoginable {
  public Optional<UserEntity> onLogin(UserEntity user);

  public Optional<UserEntity> onRegister(UserEntity user);

  public UserEntity find(long id);

  public void sendActivasyonMail(String email);

  default Session databaseConnectionHibernate() {
    return HibernateUtils.getSessionFactory().openSession();
  }
}
