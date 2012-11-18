package com.kereki.gwtmobile.client.Forms;

import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesDisplay;
import com.kereki.gwtmobile.client.Forms.LoginForm.LoginDisplay;
import com.kereki.gwtmobile.client.Forms.SingleEntryForm.SingleEntryDisplay;

public interface ViewFactory {

  public AllEntriesDisplay getAllEntriesView();

  public LoginDisplay getLoginView();

  public SingleEntryDisplay getSingleEntryView();
}
