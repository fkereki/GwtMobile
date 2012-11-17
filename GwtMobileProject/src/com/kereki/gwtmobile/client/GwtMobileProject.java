package com.kereki.gwtmobile.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

    /*
     * Set up a connectivity test every 5 seconds. If connection was down, and
     * is back up again, try to send all pending changes. At the beginning
     * assume there was no connectivity, so the first time the test succeeds,
     * we'll try to update the server.
     */
    model.testConnectivity(5, new AsyncCallback<Void>() {
      int wasOnline= 0; // 0=unknown, 1=offline, 2=online


      @Override
      public void onFailure(Throwable caught) {
        if (wasOnline != 1) {
          Window.setTitle("Diary (OFFLINE)");
          wasOnline= 1;
        }
      }

      @Override
      public void onSuccess(Void result) {
        if (wasOnline != 2) {
          Window.setTitle("Diary (Online)");
          model.putPendingEntries();
          wasOnline= 2;
        }
      }
    });
  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    environment.launch(event.getValue());
  }
}
