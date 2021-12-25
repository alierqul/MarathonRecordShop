package com.aliergul.fx.user;


import com.aliergul.entity.AlbumEntity;
import javafx.scene.Node;
import javafx.scene.control.ListCell;

public class AlbumListViewCell extends ListCell<AlbumEntity> {

  private final AlbumLineItemViewController albumLineItemViewController =
      new AlbumLineItemViewController();
  private final Node view = albumLineItemViewController.getView();

  @Override
  protected void updateItem(AlbumEntity item, boolean empty) {
    // super.updateItem(item, empty);
    if (empty) {
      setGraphic(null);
    } else {
      albumLineItemViewController.setTicket(item);
      setGraphic(view);
    }
  }

}
