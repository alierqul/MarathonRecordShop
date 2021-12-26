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
import com.aliergul.util.exception.ExceptionDiscountError;

@Entity
@Table(name = "tbl_orders")
public class OrderEntity implements Serializable {

  private static final long serialVersionUID = 5363422105205370063L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id", nullable = false)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private ProductEntity product;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @Column(name = "order_count")
  private long count;

  @Column(name = "order_discountRate")
  private double discountRate = 0.0;

  @Column(name = "order_total_pierce")
  private double sumPierce;

  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "user_createDate", nullable = false)
  private Date createDate;

  public OrderEntity() {

  }



  public double getDiscountRate() {
    return discountRate;
  }



  public void setDiscountRate(double discountRate) {
    this.discountRate = discountRate;
  }



  public OrderEntity(ProductEntity product, UserEntity user, long count)
      throws ExceptionDiscountError {
    super();

    this.product = product;
    this.user = user;
    this.count = count;
    this.discountRate = product.getDiscountRate();
    if (discountRate < 0 || discountRate > 100) {
      throw new ExceptionDiscountError("İndirim oranı 0-100 arasında olmalı");
    }
    this.sumPierce = count * getDiscounted_pierce();
  }



  public OrderEntity(long id, ProductEntity product, UserEntity user, long count, double sumPierce,
      Date createDate) {
    super();
    this.id = id;
    this.product = product;
    this.user = user;
    this.count = count;
    this.sumPierce = sumPierce;
    this.createDate = createDate;
  }



  @Override
  public String toString() {
    return "OrderEntity [id=" + id + ", product=" + product.getAlbum() + ", count=" + count
        + ", discountRate=" + discountRate + ", sumPierce=" + sumPierce + ", createDate="
        + createDate + "]";
  }



  @Override
  public int hashCode() {
    return Objects.hash(count, createDate, id, product, sumPierce, user);
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
    return Objects.equals(product, other.product);
  }



  public ProductEntity getProduct() {
    return product;
  }



  public void setProduct(ProductEntity product) {
    this.product = product;
  }



  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
    this.sumPierce = count * getDiscounted_pierce();
  }

  public double getSumPierce() {

    return sumPierce;
  }

  public double getDiscounted_pierce() {
    return product.getPierce() * ((100 - product.getDiscountRate()) / 100);
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
