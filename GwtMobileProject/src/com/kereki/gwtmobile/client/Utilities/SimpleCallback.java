package com.kereki.gwtmobile.client.Utilities;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class SimpleCallback<T> implements AsyncCallback<T> {

  @Override
  public final void onFailure(final Throwable caught) {
    // Should never be used...
  }



  @Override
  public final void onSuccess(final T result) {
    goBack(result);
  }



  public abstract void goBack(T result);



  public void goBack() {
    goBack(null);
  }
}
