package com.kereki.gwtmobile.client.SingleEntryForm;

import com.kereki.gwtmobile.client.Display;
import com.kereki.gwtmobile.client.SimpleCallback;

public interface SingleEntryDisplay extends Display {

  String getEntryDate();

  String getEntryTitle();

  String getEntryText();

  int getMood();

  void setEntryDate(String date);

  void setEntryTitle(String title);

  void setEntryText(String text);

  void setMood(int mood);

  void setSaveCallback(SimpleCallback<Object> callback);

  void setCancelCallback(SimpleCallback<Object> callback);
}
