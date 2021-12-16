package com.aliergul.controller.album;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.entity.AlbumEntity;
import com.aliergul.entity.SingerEntity;

public class AlbumController implements IAlbumControlable {

  private static final Logger logger = LogManager.getLogger(AlbumController.class);
  private static final String TAG = "AlbumController-";

  @Override
  public Optional<AlbumEntity> create(AlbumEntity album) {
    try {
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();

      session.persist(album);
      session.getTransaction().commit();


      long lastID =
          ((Number) session.createNativeQuery("select max(album.id) from AlbumEntity as album")
              .getSingleResult()).longValue();
      Optional<AlbumEntity> optUser = Optional.of(find(lastID));
      logger.info(TAG + "/ onCreate / isSuccesful \n" + optUser.toString());
      return optUser;
    } catch (Exception e) {
      logger.error(TAG + "/ onCreate / ERROR:\n" + album.toString() + "\n" + e.getMessage());

    }
    return null;
  }

  @Override
  public boolean delete(AlbumEntity album) {
    try {
      AlbumEntity findEntity = find(album.getId());
      if (findEntity != null) {
        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.remove(findEntity);
        session.getTransaction().commit();
        logger.info(TAG + "/ onDeleted / isSuccesful \n" + findEntity.toString());
        return true;
      }
    } catch (Exception e) {
      logger.error(TAG + "/ onDeleted / ERROR:\n" + album.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public AlbumEntity find(long id) {

    Session session = databaseConnectionHibernate();
    AlbumEntity entity;
    try {
      entity = session.find(AlbumEntity.class, id);

      if (entity != null) {
        logger.info(TAG + "/ onFinded / isSuccesful \n" + entity.toString());
        return entity;
      } else {
        return null;
      }
    } catch (Exception e) {

    }
    return null;
  }

  @Override
  public List<AlbumEntity> list() {
    Session session = databaseConnectionHibernate();

    String hql = "select str from MovieEntity as str where str.movieid>=:startCount";
    TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
    typedQuery.setParameter("startCount", 1L);

    ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();

    return arrayList;
  }

  @Override
  public List<AlbumEntity> listTheLastTenAlbum() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<AlbumEntity> listTheDiscountedFifteenAlbum() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<AlbumEntity> listedByType(String tag) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<AlbumEntity> listedBySinger(SingerEntity singer) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<AlbumEntity> listedBySalesCount() {
    // TODO Auto-generated method stub
    return null;
  }


}
