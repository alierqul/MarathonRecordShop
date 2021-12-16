package com.aliergul.util;

import java.io.Serializable;

public class ExceptionLowStockCount extends Exception implements Serializable {

  private static final long serialVersionUID = 8100380331886863280L;

  public ExceptionLowStockCount(String message) {
    super(message);

  }

}
