package com.kereki.gwtmobile.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesPresenter;

public class GwtMobileProject implements EntryPoint, ValueChangeHandler<String> {
  Environment environment;


  @Override
  public void onModuleLoad() {
    final Model model= new Model();
    final ViewFactory viewFactory= GWT.create(ViewFactory.class);
    final DiaryResources diaryResources= GWT.create(DiaryResources.class);

    History.addValueChangeHandler(this);
    diaryResources.css().ensureInjected();
    environment= new Environment(model, viewFactory);
    environment.launch(AllEntriesPresenter.PLACE);
    environment.model.putPendingEntries();
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    environment.launch(event.getValue());
  }
}
