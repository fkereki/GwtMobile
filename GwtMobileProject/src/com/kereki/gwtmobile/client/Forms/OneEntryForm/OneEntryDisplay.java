package com.kereki.gwtmobile.client.Forms.OneEntryForm;

import com.kereki.gwtmobile.client.MVP.Display;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;

public interface OneEntryDisplay extends Display {

  String getEntryDate();

  String getEntryText();

  String getEntryTitle();

  int getMood();

  void setCancelCallback(final SimpleCallback<Object> aCallback);

  void setEntryDate(final String aDate);

  void setEntryText(final String aText);

  void setEntryTitle(final String aTitle);

  void setMood(final int aMood);

  void setSaveCallback(final SimpleCallback<Object> aCallback);

  void setViewTitle(final String aTitle);
}
