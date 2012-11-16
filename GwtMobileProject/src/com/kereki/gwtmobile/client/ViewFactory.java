package com.kereki.gwtmobile.client;

import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesDisplay;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryDisplay;

public interface ViewFactory {

  public AllEntriesDisplay getAllEntriesView();

  public SingleEntryDisplay getSingleEntryView();
}
