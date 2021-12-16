package com.aliergul.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

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
  private String phone;
  @Column(name = "user_address")
  private String address;
  @Column(name = "user_status")
  private String status;

  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "user_createDate", nullable = false)
  private Date createDate;

  public UserEntity(String email, String passsword) {
    this.email = email;
    this.password = passsword;
  }


  public UserEntity(long id, String name, String surname, String email, String passsword,
      String phone, String address, String status, Date createDate) {
    super();
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = passsword;
    this.phone = phone;
    this.address = address;
    this.status = status;
    this.createDate = createDate;
  }


  public UserEntity(String name, String surname, String email, String passsword) {
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = passsword;
  }


  public UserEntity() {

  }

  @Override
  public String toString() {
    return "UserEntity [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email
        + ", passsword=" + password + ", phone=" + phone + ", address=" + address + ", status="
        + status + ", createDate=" + createDate + "]";
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }



}
