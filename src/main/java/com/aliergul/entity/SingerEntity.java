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
import com.aliergul.util.EStatus;

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

  @Enumerated(EnumType.STRING)
  @Column(name = "singer_status")
  private EStatus status = EStatus.ACTIVE;

  @OneToMany(mappedBy = "singer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<AlbumEntity> albums = new HashSet<AlbumEntity>();

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



  public SingerEntity(long id, String name, String surname, String bio, EStatus status,
      Set<AlbumEntity> albums, Date createDate) {
    super();
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.bio = bio;
    this.status = status;
    this.albums = albums;
    this.createDate = createDate;
  }


  @Override
  public String toString() {
    return "SingerEntity [id=" + id + ", name=" + name + ", surname=" + surname + ", bio=" + bio
        + "]";
  }



  @Override
  public int hashCode() {
    return Objects.hash(bio, createDate, id, name, surname);
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SingerEntity other = (SingerEntity) obj;
    return Objects.equals(bio, other.bio) && Objects.equals(createDate, other.createDate)
        && id == other.id && Objects.equals(name, other.name)
        && Objects.equals(surname, other.surname);
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


  public EStatus getStatus() {
    return status;
  }


  public void setStatus(EStatus status) {
    this.status = status;
  }



  public Set<AlbumEntity> getAlbums() {
    return albums;
  }


  public void setAlbums(Set<AlbumEntity> albums) {
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
