package com.aliergul.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Singer implements Serializable {

  private long id;
  private String name;
  private String surname;
  private String bio;
  private List<Album> albums = new ArrayList<Album>();
  private Date createDate;

  public Singer() {

  }


  public Singer(String name, String surname, String bio) {
    super();
    this.name = name;
    this.surname = surname;
    this.bio = bio;
  }


  public Singer(long id, String name, String surname, String bio, List<Album> albums,
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
        + "]";
  }


  @Override
  public int hashCode() {
    return Objects.hash(albums, bio, createDate, id, name, surname);
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Singer other = (Singer) obj;
    return Objects.equals(albums, other.albums) && Objects.equals(bio, other.bio)
        && Objects.equals(createDate, other.createDate) && id == other.id
        && Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
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


  public List<Album> getAlbums() {
    return albums;
  }


  public void setAlbums(List<Album> albums) {
    this.albums = albums;
  }


  public Date getCreateDate() {
    return createDate;
  }


  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }



}
