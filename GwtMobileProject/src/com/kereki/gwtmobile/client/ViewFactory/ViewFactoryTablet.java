package com.kereki.gwtmobile.client.ViewFactory;

import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesDisplay;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesViewTablet;
import com.kereki.gwtmobile.client.LoginForm.LoginDisplay;
import com.kereki.gwtmobile.client.LoginForm.LoginView;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryDisplay;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryViewTablet;

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
  public SingleEntryDisplay getSingleEntryView() {
    return new SingleEntryViewTablet();
  }
}
