package com.kereki.gwtmobile.client.SingleEntryForm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.kereki.gwtmobile.client.SimpleCallback;


public class SingleEntryView extends com.kereki.gwtmobile.client.View implements
  SingleEntryDisplay {

  private final FlexTable ft= new FlexTable();
  private final TextBox dateTextbox= new TextBox();
  private final TextBox titleTextbox= new TextBox();
  private final TextArea textTextarea= new TextArea();
  private final TextBox moodPicker= new TextBox();
  private final Button saveButton= new Button("Save");
  private final Button cancelButton= new Button("Cancel");

  SimpleCallback<Object> onSaveClickCallback;
  SimpleCallback<Object> onCancelClickCallback;


  public SingleEntryView() {
    dateTextbox.setReadOnly(true);

    ft.setWidget(0, 0, new Label("Date:"));
    ft.setWidget(1, 0, new Label("Title:"));
    ft.setWidget(2, 0, new Label("Text:"));
    ft.setWidget(3, 0, new Label("Mood:"));

    ft.setWidget(0, 1, dateTextbox);
    ft.setWidget(1, 1, titleTextbox);
    ft.setWidget(2, 1, textTextarea);
    ft.setWidget(3, 1, moodPicker);

    ft.setWidget(4, 1, saveButton);
    ft.setWidget(5, 1, cancelButton);

    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        onSaveClickCallback.goBack();
      }
    });

    cancelButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        onCancelClickCallback.goBack();
      }
    });

    initWidget(ft);
  }

  @Override
  public String getEntryDate() {
    return dateTextbox.getValue();
  }

  @Override
  public String getEntryTitle() {
    return titleTextbox.getValue();
  }

  @Override
  public String getEntryText() {
    return textTextarea.getValue();
  }

  @Override
  public int getMood() {
    try {
      return Integer.parseInt(moodPicker.getValue());
    } catch (Exception e) {
      return 0;
    }
  }

  @Override
  public void setEntryDate(String date) {
    dateTextbox.setValue(date);
  }

  @Override
  public void setEntryTitle(String title) {
    titleTextbox.setValue(title);
  }

  @Override
  public void setEntryText(String text) {
    textTextarea.setValue(text);
  }

  @Override
  public void setMood(int mood) {
    moodPicker.setValue("" + mood);
  }

  @Override
  public void setSaveCallback(SimpleCallback<Object> callback) {
    onSaveClickCallback= callback;
  }

  @Override
  public void setCancelCallback(SimpleCallback<Object> callback) {
    onCancelClickCallback= callback;
  }
}
