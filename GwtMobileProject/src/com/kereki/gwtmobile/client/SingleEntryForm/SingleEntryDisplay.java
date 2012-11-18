package com.kereki.gwtmobile.client.SingleEntryForm;

import com.kereki.gwtmobile.client.Display;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;

public interface SingleEntryDisplay extends Display {

  String getEntryDate();

  String getEntryText();

  String getEntryTitle();

  int getMood();

  void setCancelCallback(final SimpleCallback<Object> callback);

  void setEntryDate(final String date);

  void setEntryText(final String text);

  void setEntryTitle(final String title);

  void setMood(final int mood);

  void setSaveCallback(final SimpleCallback<Object> callback);

  void setViewTitle(final String title);
}
