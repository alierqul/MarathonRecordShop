package com.aliergul.dao.user;

import java.util.Optional;
import com.aliergul.entity.UserEntity;


public interface IUserLoginable {
  public Optional<UserEntity> onLogin(UserEntity user);

  public boolean onRegister(UserEntity user);

  public void sendActivasyonMail(String email);



}
