package com.aliergul.pojo;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import com.aliergul.entity.CategoryEntity;
import com.aliergul.entity.OrderEntity;
import com.aliergul.util.EStatus;


public class Album implements Serializable {

  private long id;
  private String name = "";
  private double pierce = 0.0;
  private byte[] imgAlbum;
  private double discountRate = 0.0;

  private Singer singer;
  private Set<CategoryEntity> categories = new HashSet<CategoryEntity>();
  private EStatus status = EStatus.ACTIVE;
  private long stockCount = 0;
  private long salesCount = 0;
  private List<OrderEntity> orders = new ArrayList<OrderEntity>();
  private Date createDate;

  public Album() {

  }

  public Album(long id, String name, double pierce, byte[] imgAlbum, double discountRate,
      Singer singer, Set<CategoryEntity> categories, EStatus status, long stockCount,
      long salesCount, List<OrderEntity> orders, Date createDate) {
    super();
    this.id = id;
    this.name = name;
    this.pierce = pierce;
    this.imgAlbum = imgAlbum;
    this.discountRate = discountRate;
    this.singer = singer;
    this.categories = categories;
    this.status = status;
    this.stockCount = stockCount;
    this.salesCount = salesCount;
    this.orders = orders;
    this.createDate = createDate;
  }



  public Album(String name, double pierce, long stockCount) {
    super();
    this.name = name;
    this.pierce = pierce;
    this.stockCount = stockCount;
  }



  @Override
  public String toString() {
    return "AlbumEntity [id=" + id + ", name=" + name + ", pierce=" + pierce + ", imgAlbum="
        + Arrays.toString(imgAlbum) + ", discountRate=" + discountRate + ", categories="
        + categories + ", status=" + status + ", stockCount=" + stockCount + ", salesCount="
        + salesCount + ", orders=" + orders + ", createDate=" + createDate + "]";
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(imgAlbum);
    result = prime * result + Objects.hash(categories, createDate, discountRate, id, name, orders,
        pierce, salesCount, singer, status, stockCount);
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Album other = (Album) obj;
    return Objects.equals(categories, other.categories)
        && Objects.equals(createDate, other.createDate)
        && Double.doubleToLongBits(discountRate) == Double.doubleToLongBits(other.discountRate)
        && id == other.id && Arrays.equals(imgAlbum, other.imgAlbum)
        && Objects.equals(name, other.name) && Objects.equals(orders, other.orders)
        && Double.doubleToLongBits(pierce) == Double.doubleToLongBits(other.pierce)
        && salesCount == other.salesCount && Objects.equals(singer, other.singer)
        && status == other.status && stockCount == other.stockCount;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPierce() {
    return pierce;
  }

  public void setPierce(double pierce) {
    this.pierce = pierce;
  }

  public byte[] getImgAlbum() {
    return imgAlbum;
  }

  public void setImgAlbum(String path) throws IOException {

    this.imgAlbum = Files.readAllBytes(Paths.get(path));
  }

  public void setImgAlbum(byte[] imgAlbum) {
    this.imgAlbum = imgAlbum;
  }

  public double getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(double discountRate) {
    this.discountRate = discountRate;
  }


  public Singer getSinger() {
    return singer;
  }

  public void setSinger(Singer singer) {
    this.singer = singer;
  }



  public Set<CategoryEntity> getCategories() {
    return categories;
  }


  public void setCategories(Set<CategoryEntity> categories) {
    this.categories = categories;
  }


  public List<OrderEntity> getOrders() {
    return orders;
  }


  public void setOrders(List<OrderEntity> orders) {
    this.orders = orders;
  }


  public EStatus getStatus() {
    return status;
  }

  public void setStatus(EStatus status) {
    this.status = status;
  }

  public long getStockCount() {
    return stockCount;
  }

  public void setStockCount(long stockCount) {
    this.stockCount = stockCount;
  }

  public long getSalesCount() {
    return salesCount;
  }

  public void setSalesCount(long salesCount) {
    this.salesCount = salesCount;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }



}
