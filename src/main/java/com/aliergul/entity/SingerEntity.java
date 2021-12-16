package com.aliergul.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tbl_singers")
public class SingerEntity implements Serializable {

  private static final long serialVersionUID = 7678684539767076712L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "singer_id")
  private long id;
  @Column(name = "singer_name")
  private String name;
  @Column(name = "singer_surname")
  private String surname;
  @Column(name = "singer_bio")
  private String bio;

  @OneToMany(mappedBy = "singer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<AlbumEntity> albums = new ArrayList<AlbumEntity>();

  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "singer_createDate", nullable = false)
  private Date createDate;


  public SingerEntity() {

  }


  public SingerEntity(String name, String surname, String bio) {
    super();
    this.name = name;
    this.surname = surname;
    this.bio = bio;
  }


  public SingerEntity(long id, String name, String surname, String bio, List<AlbumEntity> albums,
      Date createDate) {
    super();
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.bio = bio;
    this.albums = albums;
    this.createDate = createDate;
  }


  @Override
  public String toString() {
    return "SingerEntity [id=" + id + ", name=" + name + ", surname=" + surname + ", bio=" + bio
        + ", createDate=" + createDate.toString() + "]";
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


  public String getBio() {
    return bio;
  }


  public void setBio(String bio) {
    this.bio = bio;
  }


  public List<AlbumEntity> getAlbums() {
    return albums;
  }


  public void setAlbums(List<AlbumEntity> albums) {
    this.albums = albums;
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
