package com.kereki.gwtmobile.client.Forms.AllEntriesForm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.kereki.gwtmobile.client.MVP.View;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;


public class AllEntriesViewDesktop extends View implements AllEntriesDisplay {

  private final HTML title= new HTML();
  private final VerticalPanel vp= new VerticalPanel();
  private final FlexTable ft= new FlexTable();
  private final HorizontalPanel hp= new HorizontalPanel();
  private final ListBox list= new ListBox();
  private final Button addButton= new Button("Add");
  private final Button editButton= new Button("Edit");

  SimpleCallback<Object> onAddClickCallback;
  SimpleCallback<Object> onEditClickCallback;



  public AllEntriesViewDesktop() {
    addButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(final ClickEvent event) {
        onAddClickCallback.goBack();
      }
    });

    editButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(final ClickEvent event) {
        onEditClickCallback.goBack();
      }
    });

    Window.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(final ResizeEvent event) {
        redraw();
      }
    });

    addButton.setWidth("100%");

    editButton.setWidth("100%");

    ft.setWidth("100%");
    ft.setHeight("100%");

    hp.setWidth("100%");

    list.setVisibleItemCount(15);
    list.setWidth("100%");

    title.setHTML("<H1>All entries (Desktop Version)</H1>");

    vp.setWidth("100%");
    vp.setHeight("100%");
    vp.add(title);
    vp.add(ft);

    redraw();
    initWidget(vp);
  }



  @Override
  public String getSelectedDate() {
    if (list.getSelectedIndex() == -1) {
      return "";
    }
    else {
      return list.getValue(list.getSelectedIndex());
    }
  }



  @Override
  public void redraw() {
    if (Window.getClientHeight() > Window.getClientWidth()) { // portrait
      hp.clear();
      ft.clear();
      ft.setWidget(0, 0, list);
      ft.setWidget(1, 0, addButton);
      ft.setWidget(2, 0, editButton);
    }
    else { // landscape
      hp.clear();
      hp.add(addButton);
      hp.add(editButton);
      ft.clear();
      ft.setWidget(0, 0, list);
      ft.setWidget(1, 0, hp);
    }
  }



  @Override
  public void setAddCallback(final SimpleCallback<Object> callback) {
    onAddClickCallback= callback;
  }



  @Override
  public void setEditCallback(final SimpleCallback<Object> callback) {
    onEditClickCallback= callback;
  }



  @Override
  public void setEntryData(
    final int i,
    final String aDate,
    final String aTitle,
    final String aText) {
    if (aText.length() < 50) {
      list.addItem(aDate + ": (" + aTitle + ") " + aText, aDate);
    }
    else {
      list.addItem(aDate + ": (" + aTitle + ") " + aText.substring(0, 50) + "...", aDate);
    }
  }
}
