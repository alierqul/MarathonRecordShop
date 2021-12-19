package com.aliergul.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.aliergul.entity.SingerEntity;
import com.aliergul.util.EStatus;
import com.aliergul.util.HibernateUtils;

public class SingerController implements IDBCrudControlable<SingerEntity> {

  private static final Logger logger = LogManager.getLogger(SingerController.class);
  private static final String TAG = "SingerController-";

  @Override
  public boolean create(SingerEntity singer) {
    try {
      Session session = databaseConnectionHibernate();
      session.getTransaction().begin();
      session.persist(singer);
      session.getTransaction().commit();
      logger.info(TAG + "/ onCreate / isSuccesful \n" + singer.toString());
      return true;
    } catch (Exception e) {
      logger.error(TAG + "/ onCreate / ERROR:\n" + singer.toString() + "\n" + e.getMessage());

    }
    return false;
  }

  @Override
  public boolean delete(SingerEntity singer) {
    try {
      SingerEntity findEntity = find(singer.getId());
      if (findEntity != null) {

        findEntity.setStatus(EStatus.DELETED);

        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.merge(findEntity);
        session.getTransaction().commit();
        logger.info(TAG + "/ onDeleted / isSuccesful \n" + findEntity.toString());
        return true;
      }
    } catch (Exception e) {
      logger.error(TAG + "/ onDeleted / ERROR:\n" + singer.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }
    logger.error(TAG + "/ onDeleted / ERROR:\n" + singer.toString() + "\n");
    return false;
  }

  @Override
  public SingerEntity find(long id) {

    Session session = HibernateUtils.getSessionFactory().openSession();
    SingerEntity entity;
    try {
      entity = session.find(SingerEntity.class, id);

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
  public List<SingerEntity> list() {
    Session session = databaseConnectionHibernate();

    String hql = "select s from SingerEntity as s where s.id>=:startCount and s.status=:status";
    TypedQuery<SingerEntity> typedQuery = session.createQuery(hql, SingerEntity.class);
    typedQuery.setParameter("startCount", 1L);
    typedQuery.setParameter("status", EStatus.ACTIVE);

    ArrayList<SingerEntity> arrayList = (ArrayList<SingerEntity>) typedQuery.getResultList();

    return arrayList;
  }

  @Override
  public boolean update(SingerEntity entity) {
    try {
      SingerEntity findEntity = find(entity.getId());
      if (findEntity != null) {
        findEntity.setAlbums(entity.getAlbums());
        findEntity.setBio(entity.getBio());
        findEntity.setName(entity.getName());
        findEntity.setSurname(entity.getSurname());

        Session session = databaseConnectionHibernate();
        session.getTransaction().begin();
        session.merge(findEntity);
        session.getTransaction().commit();


        logger.info(TAG + "/ onUpdate / isSuccesful \n" + findEntity.toString());
        return true;
      }

    } catch (Exception e) {
      logger.error(TAG + "/ onUpdate / ERROR:\n" + entity.toString() + "\n" + e.getMessage());
      e.printStackTrace();
    }

    return false;
  }



}
