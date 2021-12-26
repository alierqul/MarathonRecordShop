package com.aliergul.fx.user;


import com.aliergul.entity.ProductEntity;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class AlbumListViewCell extends ListCell<ProductEntity> {

  private final AlbumLineItemViewController albumLineItemViewController =
      new AlbumLineItemViewController();
  private final Node view = albumLineItemViewController.getView();

  private UserPageController context;


  public AlbumListViewCell(UserPageController context) {
    this.context = context;
  }


  @Override
  protected void updateItem(ProductEntity item, boolean empty) {
    // super.updateItem(item, empty);
    if (empty) {
      setGraphic(null);
    } else {
      albumLineItemViewController.setTicket(item, context);
      setGraphic(view);
    }
  }

}
