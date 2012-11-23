package com.kereki.gwtmobile.client.Forms;

import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesDisplay;
import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesViewTablet;
import com.kereki.gwtmobile.client.Forms.LoginForm.LoginDisplay;
import com.kereki.gwtmobile.client.Forms.LoginForm.LoginView;
import com.kereki.gwtmobile.client.Forms.OneEntryForm.OneEntryDisplay;
import com.kereki.gwtmobile.client.Forms.OneEntryForm.OneEntryViewDesktop;

public class ViewFactoryTablet implements ViewFactory {

  @Override
  public AllEntriesDisplay getAllEntriesView() {
    return new AllEntriesViewTablet();
  }



  @Override
  public LoginDisplay getLoginView() {
    return new LoginView();
  }



  @Override
  public OneEntryDisplay getOneEntryView() {
    return new OneEntryViewDesktop();
  }
}
