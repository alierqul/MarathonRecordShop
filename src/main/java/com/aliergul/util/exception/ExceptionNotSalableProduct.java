package com.aliergul.util.exception;

import java.io.Serializable;

public class ExceptionNotSalableProduct extends Exception implements Serializable {

  private static final long serialVersionUID = 8100380331886863280L;

  public ExceptionNotSalableProduct(String message) {
    super(message);

  }

}
