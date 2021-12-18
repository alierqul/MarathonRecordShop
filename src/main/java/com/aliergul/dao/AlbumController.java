package com.aliergul.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.entity.AlbumEntity;

public class AlbumController implements IDBCrudControlable<AlbumEntity> {

  private static final Logger logger = LogManager.getLogger(AlbumController.class);
  private static final String TAG = "AlbumController-";

  @Override
  public boolean create(AlbumEntity album) {
    try {
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();

      session.persist(album);
      session.getTransaction().commit();
      logger.info(TAG + "/ onCreate / isSuccesful \n" + album.toString());
      return true;
    } catch (Exception e) {
      logger.error(TAG + "/ onCreate / ERROR:\n" + album.toString() + "\n" + e.getMessage());

    }
    return false;
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
      }
    } catch (Exception e) {

    }
    return null;
  }

  @Override
  public List<AlbumEntity> list() {
    Session session = databaseConnectionHibernate();

    String hql = "select a from AlbumEntity as a where a.id>=:startCount";
    TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
    typedQuery.setParameter("startCount", 1L);

    ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();

    return arrayList;
  }

  @Override
  public boolean update(AlbumEntity album) {
    try {
      AlbumEntity findEntity = find(album.getId());
      if (findEntity != null) {

        findEntity.setImgAlbum(album.getImgAlbum());
        findEntity.setName(album.getName());
        findEntity.setSinger(album.getSinger());
        findEntity.setCategories(album.getCategories());
        findEntity.setProducts(album.getProducts());

        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.merge(findEntity);
        session.getTransaction().commit();


        logger.info(TAG + "/ onUpdate / isSuccesful \n" + findEntity.toString());
        return true;
      }

    } catch (Exception e) {
      logger.error(TAG + "/ onUpdate / ERROR:\n" + album.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }

    return false;
  }
  // @Override
  // public List<AlbumEntity> listTheLastTenAlbum() {
  // Session session = databaseConnectionHibernate();
  //
  // String hql = "select str from AlbumEntity as str order by str.createDate desc";
  // TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
  // typedQuery.setMaxResults(10);
  // ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
  //
  // return arrayList;
  // }
  //
  // @Override
  // public List<AlbumEntity> listTheDiscountedFifteenAlbum() {
  // Session session = databaseConnectionHibernate();
  //
  // String hql =
  // "select str from AlbumEntity as str where str.discountRate>0 order by str.discountRate desc";
  // TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
  // typedQuery.setMaxResults(15);
  // ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
  //
  // return arrayList;
  // }
  //
  // @Override
  // public List<AlbumEntity> listedByType(String tag) {
  // Session session = databaseConnectionHibernate();
  //
  // String hql =
  // "select str from AlbumEntity as str where str.type like(lower(:tag)) order by str.createDate
  // desc";
  // TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
  // typedQuery.setParameter("tag", "%" + tag + "%");
  // ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
  //
  // return arrayList;
  // }
  //
  // @Override
  // public List<AlbumEntity> listedBySinger(SingerEntity singer) {
  // Session session = databaseConnectionHibernate();
  //
  // String hql = "select str from AlbumEntity as str where str.singer=:tag";
  // TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
  // typedQuery.setParameter("tag", singer);
  // ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
  //
  // return arrayList;
  // }
  //
  // @Override
  // public List<AlbumEntity> listedBySalesCount() {
  // Session session = databaseConnectionHibernate();
  //
  // String hql = "select str from AlbumEntity as str order by str.salesCount desc";
  // TypedQuery<AlbumEntity> typedQuery = session.createQuery(hql, AlbumEntity.class);
  // ArrayList<AlbumEntity> arrayList = (ArrayList<AlbumEntity>) typedQuery.getResultList();
  //
  // return arrayList;
  // }



}
