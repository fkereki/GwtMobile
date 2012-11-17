package com.kereki.gwtmobile.client;

import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesDisplay;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesViewTablet;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryDisplay;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryViewTablet;

public class ViewFactoryTablet implements ViewFactory {

  @Override
  public AllEntriesDisplay getAllEntriesView() {
    return new AllEntriesViewTablet();
  }

  @Override
  public SingleEntryDisplay getSingleEntryView() {
    return new SingleEntryViewTablet();
  }
}
