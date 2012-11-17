package com.kereki.gwtmobile.client.AllEntriesForm;

import com.kereki.gwtmobile.client.Display;
import com.kereki.gwtmobile.client.SimpleCallback;

public interface AllEntriesDisplay extends Display {

  String getSelectedDate();

  void setAddCallback(final SimpleCallback<Object> callback);

  void setEditCallback(final SimpleCallback<Object> callback);

  void setEntryData(
    final int i,
    final String date,
    final String title,
    final String text,
    final int mood);
}
