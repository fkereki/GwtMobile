package com.kereki.gwtmobile.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesPresenter;

public class GwtMobileProject implements EntryPoint, ValueChangeHandler<String> {
  Environment environment;


  @Override
  public void onModuleLoad() {
    History.addValueChangeHandler(this);
    environment= new Environment(new Model());
    environment.launch(AllEntriesPresenter.PLACE);
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    environment.launch(event.getValue());
  }
}
