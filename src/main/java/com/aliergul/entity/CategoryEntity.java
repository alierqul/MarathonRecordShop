package com.aliergul.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tbl_category")

public class CategoryEntity implements Serializable {

  private static final long serialVersionUID = -4105520462914509927L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "category_id")
  private long id;

  @Column(name = "category_name")
  private String category;

  @ManyToMany(mappedBy = "categories")

  private Set<AlbumEntity> albums = new HashSet<AlbumEntity>();
  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "category_createDate")
  private Date createDate;

  public CategoryEntity(String category) {
    this.category = category;
  }

  public CategoryEntity() {

  }

  public CategoryEntity(long id, String category, Set<AlbumEntity> albums, Date createDate) {
    this.id = id;
    this.category = category;
    this.albums = albums;
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    return category;
  }



  @Override
  public int hashCode() {
    return Objects.hash(category, createDate, id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CategoryEntity other = (CategoryEntity) obj;
    return Objects.equals(category, other.category) && Objects.equals(createDate, other.createDate)
        && id == other.id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
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



}
