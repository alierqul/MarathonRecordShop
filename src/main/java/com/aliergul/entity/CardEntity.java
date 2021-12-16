package com.aliergul.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CardEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "card_id", nullable = false)
  private long id;

  private AlbumEntity album;


  private long count;

  private double sumPierce;


}
