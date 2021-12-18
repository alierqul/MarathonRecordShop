package com.aliergul.entity;

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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import com.aliergul.util.EStatus;

@Entity
@Table(name = "tbl_albums")
public class AlbumEntity implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 9092640668543747511L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "album_id")
  private long id;
  @Column(name = "album_name")
  private String name = "";
  @Column(name = "album_pierce")
  private double pierce = 0.0;

  @Lob
  @Column(name = "album_imgAlbum")
  private byte[] imgAlbum;

  @Column(name = "album_discountRate")
  private double discountRate = 0.0;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "singer_id")
  private SingerEntity singer;

  @ManyToMany
  @JoinTable(name = "tbl_album_category", joinColumns = {@JoinColumn(name = "album_id")},
      inverseJoinColumns = {@JoinColumn(name = "category_id")})
  private Set<CategoryEntity> categories = new HashSet<CategoryEntity>();

  @Enumerated(EnumType.STRING)
  @Column(name = "album_status")
  private EStatus status = EStatus.ACTIVE;
  @Column(name = "album_stockCount")
  private long stockCount = 0;
  @Column(name = "album_salesCount")
  private long salesCount = 0;

  @OneToMany(mappedBy = "album", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<OrderEntity> orders = new ArrayList<OrderEntity>();
  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "album_createDate")
  private Date createDate;

  public AlbumEntity() {

  }

  public AlbumEntity(long id, String name, double pierce, byte[] imgAlbum, double discountRate,
      SingerEntity singer, Set<CategoryEntity> categories, EStatus status, long stockCount,
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



  public AlbumEntity(String name, double pierce, long stockCount) {
    super();
    this.name = name;
    this.pierce = pierce;
    this.stockCount = stockCount;
  }



  @Override
  public String toString() {
    return "AlbumEntity [id=" + id + ", name=" + name + ", pierce=" + pierce + ", imgAlbum="
        + Arrays.toString(imgAlbum) + ", discountRate=" + discountRate + ", status=" + status
        + ", stockCount=" + stockCount + ", salesCount=" + salesCount + ", orders=" + orders
        + ", createDate=" + createDate + "]";
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
    AlbumEntity other = (AlbumEntity) obj;
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


  public SingerEntity getSinger() {
    return singer;
  }

  public void setSinger(SingerEntity singer) {
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }



}
