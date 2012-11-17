package com.kereki.gwtmobile.client.AllEntriesForm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.kereki.gwtmobile.client.SimpleCallback;
import com.kereki.gwtmobile.client.View;


public class AllEntriesViewDesktop extends View implements AllEntriesDisplay {

  private final FlexTable ft= new FlexTable();
  private final HorizontalPanel hp= new HorizontalPanel();
  private final ListBox list= new ListBox();
  private final Button addButton= new Button("Add");
  private final Button editButton= new Button("Edit");

  SimpleCallback<Object> onAddClickCallback;
  SimpleCallback<Object> onEditClickCallback;


  public AllEntriesViewDesktop() {
    list.setVisibleItemCount(15);
    list.setWidth("100%");
    hp.setWidth("100%");
    addButton.setWidth("100%");
    editButton.setWidth("100%");

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

    Window.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(ResizeEvent event) {
        redraw();
      }
    });

    ft.setWidth("100%");
    ft.setHeight("100%");
    redraw();
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

  @Override
  public void redraw() {
    if (Window.getClientHeight() > Window.getClientWidth()) { // portrait
      hp.clear();
      ft.clear();

      ft.setWidget(0, 0, list);
      ft.setWidget(1, 0, addButton);
      ft.setWidget(2, 0, editButton);

    } else { // landscape
      hp.clear();
      hp.add(addButton);
      hp.add(editButton);

      ft.clear();
      ft.setWidget(0, 0, list);
      ft.setWidget(1, 0, hp);
    }
  }
}
