package com.kereki.gwtmobile.client.Forms;

import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesDisplay;
import com.kereki.gwtmobile.client.Forms.LoginForm.LoginDisplay;
import com.kereki.gwtmobile.client.Forms.OneEntryForm.OneEntryDisplay;

public interface ViewFactory {

  public AllEntriesDisplay getAllEntriesView();



  public LoginDisplay getLoginView();



  public OneEntryDisplay getOneEntryView();
}
