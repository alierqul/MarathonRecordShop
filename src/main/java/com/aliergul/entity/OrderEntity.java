package com.aliergul.entity;

import java.io.Serializable;
import java.util.Date;
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

  @Column(name = "order_total_pierce")
  private double sumPierce;

  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "user_createDate", nullable = false)
  private Date createDate;

  public OrderEntity() {

  }



  public OrderEntity(ProductEntity product, UserEntity user, long count) {
    super();
    this.product = product;
    this.user = user;
    this.count = count;

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
