package com.aliergul.dao.user;

import java.util.List;
import com.aliergul.entity.AlbumEntity;

public interface IAlbumSorted {
  public List<AlbumEntity> getTheLastAddedAlbums();

  public List<AlbumEntity> getFifteenOnSales();

  public List<AlbumEntity> getBestSellers();

}
