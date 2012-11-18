package com.kereki.gwtmobile.client.SingleEntryForm;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.kereki.gwtmobile.client.View;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;


public class SingleEntryViewMobile extends View implements SingleEntryDisplay {

  private final HTML title= new HTML();
  private final FlexTable ft= new FlexTable();
  private final Label dateLabel= new Label("MOBILE Date:");
  private final Label titleLabel= new Label("Title:");
  private final Label textLabel= new Label("Text:");
  private final Label moodLabel= new Label("Mood:");
  private final HorizontalPanel hp1= new HorizontalPanel();
  private final HorizontalPanel hp2= new HorizontalPanel();
  private final TextBox dateTextbox= new TextBox();
  private final TextBox titleTextbox= new TextBox();
  private final TextArea textTextarea= new TextArea();
  private final ListBox moodPicker= new ListBox();
  private final Image moodIcon= new Image();
  private final Button saveButton= new Button("Save");
  private final Button cancelButton= new Button("Cancel");

  SimpleCallback<Object> onSaveClickCallback;
  SimpleCallback<Object> onCancelClickCallback;


  public SingleEntryViewMobile() {
    dateTextbox.setReadOnly(true);

    dateTextbox.setWidth("100%");
    titleTextbox.setWidth("100%");
    textTextarea.setWidth("100%");

    hp1.setWidth("100%");
    hp2.setWidth("100%");

    moodPicker.setWidth("100%");
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

    saveButton.setWidth("100%");
    saveButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(final ClickEvent event) {
        onSaveClickCallback.goBack();
      }
    });

    cancelButton.setWidth("100%");
    cancelButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(final ClickEvent event) {
        onCancelClickCallback.goBack();
      }
    });

    moodPicker.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(final ChangeEvent event) {
        setMoodIcon(getMood());
      }
    });

    Window.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(final ResizeEvent event) {
        redraw();
      }
    });

    ft.setWidth("100%");
    ft.setHeight("100%");
    redraw();
    initWidget(ft);
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
    if (Window.getClientHeight() > Window.getClientWidth()) { // portrait
      ft.clear();
      hp1.clear();

      ft.setWidget(0, 0, dateLabel);
      ft.setWidget(1, 0, dateTextbox);
      ft.setWidget(2, 0, titleLabel);
      ft.setWidget(3, 0, titleTextbox);
      ft.setWidget(4, 0, textLabel);
      ft.setWidget(5, 0, textTextarea);
      ft.setWidget(6, 0, moodLabel);
      ft.setWidget(7, 0, moodPicker);
      ft.setWidget(8, 0, saveButton);
      ft.setWidget(9, 0, cancelButton);

      ft.getFlexCellFormatter().setAlignment(8, 0, HasHorizontalAlignment.ALIGN_RIGHT,
        HasVerticalAlignment.ALIGN_TOP);
      ft.getFlexCellFormatter().setAlignment(9, 0, HasHorizontalAlignment.ALIGN_RIGHT,
        HasVerticalAlignment.ALIGN_TOP);

    }
    else { // landscape
      ft.clear();
      hp1.clear();
      hp1.add(moodPicker);
      hp1.add(moodIcon);

      hp2.clear();
      hp2.add(saveButton);
      hp2.add(cancelButton);

      ft.setWidget(0, 0, dateLabel);
      ft.setWidget(0, 1, dateTextbox);
      ft.setWidget(1, 0, titleLabel);
      ft.setWidget(1, 1, titleTextbox);
      ft.setWidget(2, 0, textLabel);
      ft.setWidget(2, 1, textTextarea);
      ft.setWidget(3, 0, moodLabel);
      ft.setWidget(3, 1, hp1);
      ft.setWidget(4, 1, hp2);

      ft.getFlexCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT,
        HasVerticalAlignment.ALIGN_TOP);
      ft.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_LEFT,
        HasVerticalAlignment.ALIGN_TOP);
      ft.getFlexCellFormatter().setAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT,
        HasVerticalAlignment.ALIGN_TOP);
      ft.getFlexCellFormatter().setAlignment(3, 0, HasHorizontalAlignment.ALIGN_LEFT,
        HasVerticalAlignment.ALIGN_TOP);
    }
  }

  @Override
  public void setCancelCallback(final SimpleCallback<Object> callback) {
    onCancelClickCallback= callback;
  }

  @Override
  public void setEntryDate(final String date) {
    dateTextbox.setValue(date);
  }

  @Override
  public void setEntryText(final String text) {
    textTextarea.setValue(text);
  }

  @Override
  public void setEntryTitle(final String title) {
    titleTextbox.setValue(title);
  }

  @Override
  public void setMood(final int mood) {
    moodPicker.setSelectedIndex(mood);
    setMoodIcon(mood);
  }

  private void setMoodIcon(final int mood) {
    moodIcon.setUrl("mood-fox-icons/" + mood + ".gif");
  }

  @Override
  public void setSaveCallback(final SimpleCallback<Object> callback) {
    onSaveClickCallback= callback;
  }

  @Override
  public void setViewTitle(final String viewTitle) {
    title.setHTML("<H1>" + viewTitle + " (Mobile)</H1>");
  }
}
