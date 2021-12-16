package com.aliergul.controller.album;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.SingerEntity;
import com.aliergul.util.HibernateUtils;

public interface IAlbumControlable {
  public Optional<AlbumEntity> create(AlbumEntity album);

  public boolean delete(AlbumEntity album);

  public List<AlbumEntity> list();

  public List<AlbumEntity> listTheLastTenAlbum();

  public List<AlbumEntity> listTheDiscountedFifteenAlbum();

  public List<AlbumEntity> listedByType(String tag);

  public List<AlbumEntity> listedBySinger(SingerEntity singer);

  public List<AlbumEntity> listedBySalesCount();


  public AlbumEntity find(long id);

  default Session databaseConnectionHibernate() {
    return HibernateUtils.getSessionFactory().openSession();
  }

}
