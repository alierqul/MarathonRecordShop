package com.aliergul.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import com.aliergul.util.EStatus;
import com.aliergul.util.exception.ExceptionDiscountError;

@Entity
@Table(name = "tbl_product")
public class ProductEntity implements Serializable {

  private static final long serialVersionUID = 221274422397860728L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_id", nullable = false)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "album_id")
  private AlbumEntity album;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_id")
  private ProductTypeEntity typeProduct;

  @Enumerated(EnumType.STRING)
  @Column(name = "album_status")
  private EStatus status = EStatus.ACTIVE;

  @Column(name = "album_pierce")
  private double pierce = 0.0;

  @Column(name = "album_stockCount")
  private long stockCount = 0;
  @Column(name = "album_salesCount")
  private long salesCount = 0;

  @Column(name = "album_discountRate")
  private double discountRate = 0.0;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<OrderEntity> orders = new ArrayList<OrderEntity>();

  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "product_createDate")
  private Date createDate;

  public ProductEntity() {

  }

  public ProductEntity(long id, AlbumEntity album, ProductTypeEntity type, EStatus status,
      double pierce, long stockCount, long salesCount, double discountRate,
      List<OrderEntity> orders, Date createDate) {
    this.id = id;
    this.album = album;
    this.typeProduct = type;
    this.status = status;
    this.pierce = pierce;
    this.stockCount = stockCount;
    this.salesCount = salesCount;
    this.discountRate = discountRate;
    this.orders = orders;
    this.createDate = createDate;
  }

  public ProductEntity(AlbumEntity album, ProductTypeEntity type, double pierce, long stockCount) {
    this.album = album;
    this.typeProduct = type;
    this.pierce = pierce;
    this.stockCount = stockCount;


  }

  @Override
  public String toString() {
    return typeProduct.getType().name() + " " + typeProduct.getDescriptions();
  }



  @Override
  public int hashCode() {
    return Objects.hash(album, createDate, discountRate, id, pierce, salesCount, status, stockCount,
        typeProduct);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductEntity other = (ProductEntity) obj;
    return Objects.equals(album, other.album) && Objects.equals(createDate, other.createDate)
        && Double.doubleToLongBits(discountRate) == Double.doubleToLongBits(other.discountRate)
        && id == other.id
        && Double.doubleToLongBits(pierce) == Double.doubleToLongBits(other.pierce)
        && salesCount == other.salesCount && status == other.status
        && stockCount == other.stockCount && Objects.equals(typeProduct, other.typeProduct);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public ProductTypeEntity getTypeProduct() {
    return typeProduct;
  }

  public void setTypeProduct(ProductTypeEntity typeProduct) {
    this.typeProduct = typeProduct;
  }

  public AlbumEntity getAlbum() {
    return album;
  }

  public void setAlbum(AlbumEntity album) {
    this.album = album;
  }

  public ProductTypeEntity getType() {
    return typeProduct;
  }

  public void setType(ProductTypeEntity type) {
    this.typeProduct = type;
  }

  public EStatus getStatus() {
    return status;
  }

  public void setStatus(EStatus status) {
    this.status = status;
  }

  public double getPierce() {
    return pierce;
  }

  public void setPierce(double pierce) {
    this.pierce = pierce;
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

  public double getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(double discountRate) throws ExceptionDiscountError {
    if (discountRate < 0 || discountRate > 100) {
      throw new ExceptionDiscountError("İndirim oranı 0-100 arasında olmalı");
    } else {
      this.discountRate = discountRate;
    }

  }

  public List<OrderEntity> getOrders() {
    return orders;
  }

  public void setOrders(List<OrderEntity> orders) {
    this.orders = orders;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }



}
