package com.kereki.gwtmobile.client;

import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesDisplay;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesViewDesktop;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryDisplay;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryViewDesktop;

public class ViewFactoryDesktop implements ViewFactory {

  @Override
  public AllEntriesDisplay getAllEntriesView() {
    return new AllEntriesViewDesktop();
  }

  @Override
  public SingleEntryDisplay getSingleEntryView() {
    return new SingleEntryViewDesktop();
  }
}
