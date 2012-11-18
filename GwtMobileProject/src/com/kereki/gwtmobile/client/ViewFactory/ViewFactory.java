package com.kereki.gwtmobile.client.ViewFactory;

import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesDisplay;
import com.kereki.gwtmobile.client.LoginForm.LoginDisplay;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryDisplay;

public interface ViewFactory {

  public AllEntriesDisplay getAllEntriesView();

  public LoginDisplay getLoginView();

  public SingleEntryDisplay getSingleEntryView();
}
