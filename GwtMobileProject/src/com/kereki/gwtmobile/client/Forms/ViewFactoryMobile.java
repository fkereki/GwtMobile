package com.kereki.gwtmobile.client.Forms;

import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesDisplay;
import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesViewMobile;
import com.kereki.gwtmobile.client.Forms.LoginForm.LoginDisplay;
import com.kereki.gwtmobile.client.Forms.LoginForm.LoginView;
import com.kereki.gwtmobile.client.Forms.SingleEntryForm.SingleEntryDisplay;
import com.kereki.gwtmobile.client.Forms.SingleEntryForm.SingleEntryViewMobile;

public class ViewFactoryMobile implements ViewFactory {

  @Override
  public AllEntriesDisplay getAllEntriesView() {
    return new AllEntriesViewMobile();
  }

  @Override
  public LoginDisplay getLoginView() {
    return new LoginView();
  }

  @Override
  public SingleEntryDisplay getSingleEntryView() {
    return new SingleEntryViewMobile();
  }
}