package com.kereki.gwtmobile.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kereki.gwtmobile.client.LoginForm.LoginPresenter;
import com.kereki.gwtmobile.client.ViewFactory.ViewFactory;

enum Status {
  UNTESTED, OFFLINE, ONLINE
}

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
    environment.launch(LoginPresenter.PLACE);

    /*
     * Set up a connectivity test every 5 seconds. If connection was down, and
     * is back up again, try to send all pending changes. At the beginning
     * assume there was no connectivity, so the first time the test succeeds,
     * we'll try to update the server.
     */
    model.testConnection(5, new AsyncCallback<Void>() {
      Status wasOnline= Status.UNTESTED;


      @Override
      public void onFailure(final Throwable caught) {
        if (wasOnline != Status.OFFLINE) {
          Window.setTitle("Diary (OFFLINE)");
          wasOnline= Status.OFFLINE;
        }
      }

      @Override
      public void onSuccess(final Void result) {
        if (wasOnline != Status.ONLINE) {
          Window.setTitle("Diary (Online)");
          model.putPendingEntries();
          wasOnline= Status.ONLINE;
        }
      }
    });
  }

  @Override
  public void onValueChange(final ValueChangeEvent<String> event) {
    environment.launch(event.getValue());
  }
}
