package com.kereki.gwtmobile.client.Forms.OneEntryForm;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.kereki.gwtmobile.client.MVP.View;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;


public class OneEntryViewDesktop extends View implements OneEntryDisplay {

  interface ViewUiBinder extends UiBinder<Widget, OneEntryViewDesktop> {
  }


  private static ViewUiBinder uiBinder= GWT
    .create(ViewUiBinder.class);

  @UiField
  TextBox dateTextbox;
  @UiField
  TextBox titleTextbox;
  @UiField
  TextArea textTextarea;
  @UiField
  ListBox moodPicker;
  @UiField
  Image moodIcon;
  @UiField
  Button saveButton;
  @UiField
  Button cancelButton;


  @UiHandler("cancelButton")
  void handleCancelClick(final ClickEvent event) {
    onCancelClickCallback.goBack();
  }

  @UiHandler("saveButton")
  void handleSaveClick(final ClickEvent event) {
    onSaveClickCallback.goBack();
  }

  @UiHandler("moodPicker")
  void handleMoodChange(final ChangeEvent event) {
    setMoodIcon(getMood());
  }


  SimpleCallback<Object> onSaveClickCallback;
  SimpleCallback<Object> onCancelClickCallback;


  public OneEntryViewDesktop() {
    initWidget(uiBinder.createAndBindUi(this));

    moodPicker.addItem("Annoyed");
    moodPicker.addItem("Blink");
    moodPicker.addItem("Creative");
    moodPicker.addItem("Doh");
    moodPicker.addItem("Evil");
    moodPicker.addItem("Happy");
    moodPicker.addItem("Horny");
    moodPicker.addItem("Hungry");
    moodPicker.addItem("Love");
    moodPicker.addItem("Oh, No");
    moodPicker.addItem("OO");
    moodPicker.addItem("Pissed");
    moodPicker.addItem("Relieved");
    moodPicker.addItem("Sad");
    moodPicker.addItem("Shocked");
    moodPicker.addItem("Sleep");
    moodPicker.addItem("Surprised");
    moodPicker.addItem("UhOh");
    moodPicker.addItem("Wheeee");
    moodPicker.addItem("XP");

    Window.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(final ResizeEvent event) {
        redraw();
      }
    });
  }


  @Override
  public String getEntryDate() {
    return dateTextbox.getValue();
  }

  @Override
  public String getEntryText() {
    return textTextarea.getValue();
  }

  @Override
  public String getEntryTitle() {
    return titleTextbox.getValue();
  }

  @Override
  public int getMood() {
    return moodPicker.getSelectedIndex();
  }

  @Override
  public void redraw() {
    // nothing to do; always the same view...
  }

  @Override
  public void setCancelCallback(final SimpleCallback<Object> aCallback) {
    onCancelClickCallback= aCallback;
  }


  @Override
  public void setEntryDate(final String aDate) {
    dateTextbox.setValue(aDate);
  }

  @Override
  public void setEntryText(final String aText) {
    textTextarea.setValue(aText);
  }


  @Override
  public void setEntryTitle(final String aTitle) {
    titleTextbox.setValue(aTitle);
  }

  @Override
  public void setMood(final int aMood) {
    moodPicker.setSelectedIndex(aMood);
    setMoodIcon(aMood);
  }

  private void setMoodIcon(final int aMood) {
    moodIcon.setUrl("mood-fox-icons/" + aMood + ".gif");
  }

  @Override
  public void setSaveCallback(final SimpleCallback<Object> aCallback) {
    onSaveClickCallback= aCallback;
  }

  @Override
  public void setViewTitle(final String aTitle) {
    // TODO Auto-generated method stub

  }


}
