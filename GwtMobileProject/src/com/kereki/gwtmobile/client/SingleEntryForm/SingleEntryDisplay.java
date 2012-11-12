package com.kereki.gwtmobile.client.SingleEntryForm;

import com.kereki.gwtmobile.client.Display;
import com.kereki.gwtmobile.client.SimpleCallback;

public interface SingleEntryDisplay extends Display {

  String getEntryDate();

  String getEntryTitle();

  String getEntryText();

  int getMood();

  void setEntryDate(final String date);

  void setEntryTitle(final String title);

  void setEntryText(final String text);

  void setMood(final int mood);

  void setSaveCallback(final SimpleCallback<Object> callback);

  void setCancelCallback(final SimpleCallback<Object> callback);
}
