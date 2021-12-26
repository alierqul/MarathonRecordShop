package com.aliergul.dao;

import java.util.List;

public interface IAlbumSorted<T> {
  public List<T> getTheLastAddedAlbums();

  public List<T> getFifteenOnSales();

  public List<T> getBestSellers();

  public List<T> getProductByCategories(String categories);

}
