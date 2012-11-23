package com.kereki.gwtmobile.client.MVP;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.kereki.gwtmobile.client.Forms.ViewFactory;
import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesPresenter;
import com.kereki.gwtmobile.client.Forms.LoginForm.LoginPresenter;
import com.kereki.gwtmobile.client.Forms.OneEntryForm.OneEntryPresenter;

public class Environment {
  final Environment self= this;
  final ViewFactory viewFactory;
  final Model model;
  String currentUser= "";



  public Environment(final Model aModel, final ViewFactory aViewFactory) {
    model= aModel;
    viewFactory= aViewFactory;
  }



  public Model getModel() {
    return model;
  }



  public String getUser() {
    return currentUser;
  }



  public void launch(String token) {
    History.newItem(token, false);

    /*
     * There could be parameters after the "#token" in the classic form
     * "?key1=value1&key2=value2..."; for more on this, check
     * http://www.w3.org/TR/hash-in-uri/.
     */
    String args= "";
    int question= token.indexOf("?");
    if (question != -1) {
      args= token.substring(question + 1);
      token= token.substring(0, question);
    }

    if (token.isEmpty()) {
      // no need to do anything...

    }
    else if (currentUser.isEmpty() | token.equals(LoginPresenter.PLACE)) {
      LoginPresenter loginPresenter= new LoginPresenter("", viewFactory.getLoginView(),
        self);
      RootPanel.get().clear();
      RootPanel.get().add(loginPresenter.getDisplay().asWidget());
      showAllEntries("", false); // prefetch the "all entries" form

    }
    else if (token.equals(OneEntryPresenter.PLACE)) {
      showOneEntry(args, true);

    }
    else if (token.equals(AllEntriesPresenter.PLACE)) {
      showAllEntries(args, true);
      showOneEntry("", false); // prefetch the "one entry" form

    }
    else {
      showAlert("Unrecognized token=" + token);
      token= "";
    }
  }



  public void prefetchImages() {
    /*
     * Preload mood images with Image.prefetch(...)
     */
    for (int mood= 0; mood < 20; mood++) {
      Image.prefetch("mood-fox-icons/" + mood + ".gif");
    }
  }



  public void setUser(final String anUser) {
    currentUser= anUser;
  }



  public void showAlert(final String alertText) {
    Window.alert(alertText);
  }



  public void showAllEntries(final String args, final boolean forReal) {
    GWT.runAsync(new RunAsyncCallback() {
      @Override
      public void onFailure(final Throwable reason) {
        if (forReal) {
          showAlert("Cannot get & show the 'all entries' form...");
        }
      }



      @Override
      public void onSuccess() {
        if (forReal) {
          final AllEntriesPresenter allEntriesPresenter= new AllEntriesPresenter(args,
            viewFactory.getAllEntriesView(), self);
          RootPanel.get().clear();
          RootPanel.get().add(allEntriesPresenter.getDisplay().asWidget());
        }
      }
    });
  }



  public void showOneEntry(final String args, final boolean forReal) {
    GWT.runAsync(new RunAsyncCallback() {
      @Override
      public void onFailure(final Throwable reason) {
        if (forReal) {
          showAlert("Cannot show the 'one entry' form...");
        }
      }



      @Override
      public void onSuccess() {
        if (forReal) {
          final OneEntryPresenter oneEntryPresenter= new OneEntryPresenter(args,
            viewFactory.getOneEntryView(), self);
          RootPanel.get().clear();
          RootPanel.get().add(oneEntryPresenter.getDisplay().asWidget());
        }
      }
    });
  }
}
