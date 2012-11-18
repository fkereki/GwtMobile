package com.kereki.gwtmobile.client.Forms.AllEntriesForm;

import com.kereki.gwtmobile.client.MVP.Display;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;

public interface AllEntriesDisplay extends Display {

  String getSelectedDate();

  void setAddCallback(final SimpleCallback<Object> callback);

  void setEditCallback(final SimpleCallback<Object> callback);

  void setEntryData(
    final int i,
    final String aDate,
    final String aTitle,
    final String aText);
}
