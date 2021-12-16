package com.aliergul.entity;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

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
  private String name;
  @Column(name = "album_pierce")
  private double pierce;

  @Lob
  @Column(name = "album_imgAlbum")
  private byte[] imgAlbum;

  @Column(name = "album_discountRate")
  private double discountRate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "singer_id")
  private SingerEntity singer;

  @Column(name = "album_type")
  private String type;
  @Column(name = "album_status")
  private String status;
  @Column(name = "album_stockCount")
  private long stockCount;
  @Column(name = "album_salesCount")
  private long salesCount;

  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "album_createDate")
  private Date createDate;

  public AlbumEntity() {

  }

  public AlbumEntity(long id, String name, double pierce, byte[] imgAlbum, double discountRate,
      SingerEntity singer, String type, String status, long stockCount, long salesCount,
      Date createDate) {
    super();
    this.id = id;
    this.name = name;
    this.pierce = pierce;
    this.imgAlbum = imgAlbum;
    this.discountRate = discountRate;
    this.singer = singer;
    this.type = type;
    this.status = status;
    this.stockCount = stockCount;
    this.salesCount = salesCount;
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
        + Arrays.toString(imgAlbum) + ", discountRate=" + discountRate + ", singer=" + singer
        + ", type=" + type + ", status=" + status + ", stockCount=" + stockCount + ", salesCount="
        + salesCount + ", createDate=" + createDate + "]";
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
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
