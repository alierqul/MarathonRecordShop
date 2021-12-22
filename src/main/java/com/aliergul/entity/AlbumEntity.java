package com.aliergul.entity;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "tbl_albums")
public class AlbumEntity implements Serializable {

  private static final long serialVersionUID = 9092640668543747511L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "album_id")
  private long id;

  @Column(name = "album_name")
  private String name = "";
  // org.hibernate.type.ImageType
  // org.hibernate.type.StringType
  // @Type(type = "org.hibernate.type.ImageType")
  @Lob
  @Type(type = "org.hibernate.type.MaterializedBlobType")
  @Column(name = "album_imgAlbum")
  private byte[] imgAlbum;

  @ManyToOne()
  @JoinColumn(name = "singer_id", referencedColumnName = "singer_id")
  private SingerEntity singer;

  @ManyToMany
  @JoinTable(name = "tbl_album_category", joinColumns = {@JoinColumn(name = "album_id")},
      inverseJoinColumns = {@JoinColumn(name = "category_id")})
  private Set<CategoryEntity> categories = new HashSet<CategoryEntity>();

  @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<ProductEntity> products = new HashSet<ProductEntity>();

  @Temporal(value = TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "album_createDate")
  private Date createDate;

  public AlbumEntity() {

  }

  public AlbumEntity(String name, String imgAlbumPATH, SingerEntity singer,
      Set<CategoryEntity> categories) throws IOException {
    super();
    this.name = name;
    if (imgAlbumPATH.trim().length() > 0)
      setImgAlbum(imgAlbumPATH);
    this.singer = singer;
    this.categories = categories;
  }



  public AlbumEntity(long id, String name, byte[] imgAlbum, SingerEntity singer,
      Set<CategoryEntity> categories, Set<ProductEntity> products, Date createDate) {
    super();
    this.id = id;
    this.name = name;
    this.imgAlbum = imgAlbum;
    this.singer = singer;
    this.categories = categories;
    this.products = products;
    this.createDate = createDate;
  }



  @Override
  public String toString() {
    return "AlbumEntity [id=" + id + ", name=" + name + ", imgAlbum=" + Arrays.toString(imgAlbum)
        + ", createDate=" + createDate + "]";
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(imgAlbum);
    result = prime * result + Objects.hash(createDate, id, name, singer);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AlbumEntity other = (AlbumEntity) obj;
    return Objects.equals(createDate, other.createDate) && id == other.id
        && Arrays.equals(imgAlbum, other.imgAlbum) && Objects.equals(name, other.name)
        && Objects.equals(singer, other.singer);
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


  public byte[] getImgAlbum() {
    return imgAlbum;
  }

  public void setImgAlbum(String path) throws IOException {
    if (path != null && path.length() > 5) {
      this.imgAlbum = Files.readAllBytes(Paths.get(path));
    }

  }

  public void setImgAlbum(byte[] imgAlbum) {
    this.imgAlbum = imgAlbum;
  }


  public SingerEntity getSinger() {
    return singer;
  }

  public void setSinger(SingerEntity singer) {
    this.singer = singer;
  }


  public Set<CategoryEntity> getCategories() {
    return categories;
  }


  public void setCategories(Set<CategoryEntity> categories) {
    this.categories = categories;
  }


  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Set<ProductEntity> getProducts() {
    return products;
  }

  public void setProducts(Set<ProductEntity> products) {
    this.products = products;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }



}
