package com.aliergul.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import com.aliergul.util.EStatus;
import com.aliergul.util.ExceptionLowStockCount;

@Entity
@Table(name = "tbl_orders")
public class OrderEntity implements Serializable {

  private static final long serialVersionUID = 5363422105205370063L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id", nullable = false)
  private long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "album_id")
  private AlbumEntity album;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @Column(name = "order_count")
  private long count;

  @Column(name = "order_total_pierce")
  private double sumPierce;

  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "user_createDate", nullable = false)
  private Date createDate;

  public OrderEntity() {

  }

  public OrderEntity(long id, AlbumEntity album, UserEntity user, long count, double sumPierce,
      Date createDate) {

    this.id = id;
    this.album = album;
    this.user = user;
    this.count = count;
    this.sumPierce = sumPierce;
    this.createDate = createDate;
  }

  public OrderEntity(AlbumEntity album, UserEntity user, long count) throws ExceptionLowStockCount {

    this.album = album;
    this.user = user;
    this.count = count;
    this.sumPierce = count * album.getPierce();
    if (album.getStatus() != EStatus.ACTIVE) {
      throw new ExceptionLowStockCount("Satış Yapılamaz");
    }
  }



  @Override
  public String toString() {
    return "OrderEntity [id=" + id + ", album=" + album.getName() + ", user=" + user.getName()
        + ", count=" + count + ", sumPierce=" + sumPierce + ", createDate=" + createDate + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(album, count, createDate, id, sumPierce, user);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    OrderEntity other = (OrderEntity) obj;
    return Objects.equals(album, other.album) && count == other.count
        && Objects.equals(createDate, other.createDate) && id == other.id
        && Double.doubleToLongBits(sumPierce) == Double.doubleToLongBits(other.sumPierce)
        && Objects.equals(user, other.user);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public AlbumEntity getAlbum() {
    return album;
  }

  public void setAlbum(AlbumEntity album) {
    this.album = album;
  }


  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public double getSumPierce() {
    return sumPierce;
  }

  public void setSumPierce(double sumPierce) {
    this.sumPierce = sumPierce;
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
