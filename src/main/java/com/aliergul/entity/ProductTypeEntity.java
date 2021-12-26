package com.aliergul.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import com.aliergul.util.EDiskDiameter;
import com.aliergul.util.EDiskQuality;
import com.aliergul.util.EProduct;
import com.aliergul.util.exception.ExceptionNotImageQuality;
import com.aliergul.util.exception.ExceptionNotInformationRunSpeedOrDisDiameter;

@Entity
@Table(name = "tbl_product_type")
public class ProductTypeEntity implements Serializable {

  private static final long serialVersionUID = -6294167136017019997L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "type_id")
  private long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_name", nullable = false)
  private EProduct type = EProduct.DVD;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_image_quality")
  private EDiskQuality imageQuality = EDiskQuality.BLU_RAY;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_disc_diameter")
  private EDiskDiameter discDiameter = EDiskDiameter.RECORD_33;

  @Enumerated(EnumType.STRING)
  @Column(name = "type_run_speed")
  private EDiskDiameter runSpeed = EDiskDiameter.RECORD_33;

  @Column(name = "type_descriptions")
  private String descriptions = "";

  @OneToMany(mappedBy = "typeProduct", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<ProductEntity> products = new HashSet<ProductEntity>();

  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "user_createDate", nullable = false)
  private Date createDate;


  public ProductTypeEntity()
      throws ExceptionNotImageQuality, ExceptionNotInformationRunSpeedOrDisDiameter {
    controlItemException();
  }



  public ProductTypeEntity(EProduct type, EDiskQuality imageQuality, EDiskDiameter discDiameter,
      EDiskDiameter runSpeed, String descriptions)
      throws ExceptionNotImageQuality, ExceptionNotInformationRunSpeedOrDisDiameter {
    this.type = type;
    this.imageQuality = imageQuality;
    this.discDiameter = discDiameter;
    this.runSpeed = runSpeed;
    this.descriptions = descriptions;
    controlItemException();
  }



  public ProductTypeEntity(long id, EProduct type, EDiskQuality imageQuality,
      EDiskDiameter discDiameter, EDiskDiameter runSpeed, String descriptions,
      Set<ProductEntity> products, Date createDate)
      throws ExceptionNotImageQuality, ExceptionNotInformationRunSpeedOrDisDiameter {
    this.id = id;
    this.type = type;
    this.imageQuality = imageQuality;
    this.discDiameter = discDiameter;
    this.runSpeed = runSpeed;
    this.descriptions = descriptions;
    this.products = products;
    this.createDate = createDate;
    controlItemException();
  }



  private void controlItemException()
      throws ExceptionNotImageQuality, ExceptionNotInformationRunSpeedOrDisDiameter {
    if (type == EProduct.DVD) {
      if (imageQuality == null) {
        throw new ExceptionNotImageQuality("DVD Kalitesi Girilmeli");
      }
    }
    if (type == EProduct.Vinvyl) {
      if (imageQuality == null || discDiameter == null) {
        throw new ExceptionNotInformationRunSpeedOrDisDiameter(
            "Çalma hızı ve Çap bilgisi girilmeli ");
      }
    }
  }

  @Override
  public String toString() {
    return type + "- " + descriptions;
  }



  @Override
  public int hashCode() {
    return Objects.hash(createDate, descriptions, discDiameter, id, imageQuality, runSpeed, type);
  }



  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ProductTypeEntity other = (ProductTypeEntity) obj;
    return Objects.equals(createDate, other.createDate)
        && Objects.equals(descriptions, other.descriptions) && discDiameter == other.discDiameter
        && id == other.id && imageQuality == other.imageQuality
        && Objects.equals(runSpeed, other.runSpeed) && type == other.type;
  }



  public long getId() {
    return id;
  }


  public void setId(long id) {
    this.id = id;
  }


  public EProduct getType() {
    return type;
  }


  public void setType(EProduct type) {
    this.type = type;
  }


  public EDiskQuality getImageQuality() {
    return imageQuality;
  }


  public void setImageQuality(EDiskQuality imageQuality)
      throws ExceptionNotImageQuality, ExceptionNotInformationRunSpeedOrDisDiameter {
    this.imageQuality = imageQuality;
    controlItemException();
  }


  public EDiskDiameter getDiscDiameter() {
    return discDiameter;
  }


  public void setDiscDiameter(EDiskDiameter discDiameter)
      throws ExceptionNotImageQuality, ExceptionNotInformationRunSpeedOrDisDiameter {
    this.discDiameter = discDiameter;
    controlItemException();
  }


  public EDiskDiameter getRunSpeed() {
    return runSpeed;
  }


  public void setRunSpeed(EDiskDiameter runSpeed)
      throws ExceptionNotImageQuality, ExceptionNotInformationRunSpeedOrDisDiameter {
    this.runSpeed = runSpeed;
    controlItemException();
  }


  public String getDescriptions() {
    return descriptions;
  }


  public void setDescriptions(String descriptions) {
    this.descriptions = descriptions;
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



  public Set<ProductEntity> getProducts() {
    return products;
  }



  public void setProducts(Set<ProductEntity> products) {
    this.products = products;
  }



}
