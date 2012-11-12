package com.kereki.gwtmobile.client.AllEntriesForm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.kereki.gwtmobile.client.SimpleCallback;


public class AllEntriesView extends com.kereki.gwtmobile.client.View implements
  AllEntriesDisplay {

  private final FlexTable ft= new FlexTable();
  private final HorizontalPanel hp= new HorizontalPanel();
  private final ListBox list= new ListBox();
  private final Button addButton= new Button("Add");
  private final Button editButton= new Button("Edit");

  SimpleCallback<Object> onAddClickCallback;
  SimpleCallback<Object> onEditClickCallback;


  public AllEntriesView() {
    list.setVisibleItemCount(15);

    hp.add(addButton);
    hp.add(editButton);

    ft.setWidget(0, 0, list);
    ft.setWidget(1, 0, hp);

    addButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        onAddClickCallback.goBack();
      }
    });

    editButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        onEditClickCallback.goBack();
      }
    });

    initWidget(ft);
  }

  @Override
  public void setEntryData(int i, String date, String title, String text, int mood) {
    list.addItem(date + ": " + title, date);
  }

  @Override
  public String getSelectedDate() {
    if (list.getSelectedIndex() == -1) {
      return "";
    } else {
      return list.getValue(list.getSelectedIndex());
    }
  }

  @Override
  public void setAddCallback(SimpleCallback<Object> callback) {
    onAddClickCallback= callback;
  }

  @Override
  public void setEditCallback(SimpleCallback<Object> callback) {
    onEditClickCallback= callback;
  }
}
