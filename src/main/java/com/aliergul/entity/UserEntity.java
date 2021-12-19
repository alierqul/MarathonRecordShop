package com.aliergul.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.aliergul.util.EStatus;

@Entity
@Table(name = "tbl_users")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = -6603154857135293053L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private long id;
  @Column(name = "user_name")
  private String name;
  @Column(name = "user_surname")
  private String surname;
  @Column(name = "user_email")
  private String email;
  @Column(name = "user_passsword")
  private String password;
  @Column(name = "user_phone")
  private String phone = "";
  @Column(name = "user_address")
  private String address = "";
  @Enumerated
  @Column(name = "user_status")
  private EStatus status = EStatus.ACTIVE;
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<OrderEntity> orders = new HashSet<OrderEntity>();
  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "user_createDate", nullable = false)
  private Date createDate;

  public UserEntity(String email, String passsword) {
    this.email = email;
    this.password = passsword;
  }



  public UserEntity(String name, String surname, String email, String passsword) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = passsword;
  }


  public UserEntity() {

  }



  public UserEntity(long id, String name, String surname, String email, String password,
      String phone, String address, EStatus status, Set<OrderEntity> orders, Date createDate) {
    super();
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.address = address;
    this.status = status;
    this.orders = orders;
    this.createDate = createDate;
  }



  @Override
  public String toString() {
    return "UserEntity [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email
        + ", passsword=" + password + ", phone=" + phone + ", address=" + address + ", status="
        + status + ", createDate=" + createDate + "]";
  }



  @Override
  public int hashCode() {
    return Objects.hash(address, createDate, email, id, name, password, phone, status, surname);
  }



  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserEntity other = (UserEntity) obj;
    return Objects.equals(address, other.address) && Objects.equals(createDate, other.createDate)
        && Objects.equals(email, other.email) && id == other.id && Objects.equals(name, other.name)
        && Objects.equals(password, other.password) && Objects.equals(phone, other.phone)
        && status == other.status && Objects.equals(surname, other.surname);
  }



  public EStatus getStatus() {
    return status;
  }



  public void setStatus(EStatus status) {
    this.status = status;
  }



  public Set<OrderEntity> getOrders() {
    return orders;
  }



  public void setOrders(Set<OrderEntity> orders) {
    this.orders = orders;
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

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPasssword() {
    return password;
  }

  public void setPasssword(String passsword) {
    this.password = passsword;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }



  public String getPassword() {
    return password;
  }



  public void setPassword(String password) {
    this.password = password;
  }



  public static long getSerialversionuid() {
    return serialVersionUID;
  }



}
