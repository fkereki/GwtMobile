package com.kereki.gwtmobile.client;

import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesView;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryDisplay;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryViewTablet;

public class ViewFactoryTablet implements ViewFactory {

  @Override
  public AllEntriesView getAllEntriesView() {
    return new AllEntriesView();
  }

  @Override
  public SingleEntryDisplay getSingleEntryView() {
    return new SingleEntryViewTablet();
  }
}
