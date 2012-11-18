package com.kereki.gwtmobile.client.MVP;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.kereki.gwtmobile.client.Forms.ViewFactory;
import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesPresenter;
import com.kereki.gwtmobile.client.Forms.LoginForm.LoginPresenter;
import com.kereki.gwtmobile.client.Forms.SingleEntryForm.SingleEntryPresenter;

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
    final String args2= args;

    if (token.isEmpty()) {
      // no need to do anything...

    }
    else if (currentUser.isEmpty() | token.equals(LoginPresenter.PLACE)) {
      LoginPresenter loginPresenter= new LoginPresenter("", viewFactory.getLoginView(),
        self);
      RootPanel.get().clear();
      RootPanel.get().add(loginPresenter.getDisplay().asWidget());

    }
    else if (token.equals(AllEntriesPresenter.PLACE)) {
      GWT.runAsync(new RunAsyncCallback() {
        @Override
        public void onFailure(final Throwable reason) {
          showAlert("Cannot show the 'all entries' form...");
        }

        @Override
        public void onSuccess() {
          final AllEntriesPresenter allEntriesPresenter= new AllEntriesPresenter(args2,
            viewFactory.getAllEntriesView(), self);
          RootPanel.get().clear();
          RootPanel.get().add(allEntriesPresenter.getDisplay().asWidget());
        }
      });

    }
    else if (token.equals(SingleEntryPresenter.PLACE)) {
      GWT.runAsync(new RunAsyncCallback() {
        @Override
        public void onFailure(final Throwable reason) {
          showAlert("Cannot show the 'single entry' form...");
        }

        @Override
        public void onSuccess() {
          final SingleEntryPresenter singleEntryPresenter= new SingleEntryPresenter(
            args2, viewFactory.getSingleEntryView(), self);
          RootPanel.get().clear();
          RootPanel.get().add(singleEntryPresenter.getDisplay().asWidget());
        }
      });

    }
    else {
      showAlert("Unrecognized token=" + token);
      token= "";
    }
  }

  public void setUser(final String anUser) {
    currentUser= anUser;
  }

  public void showAlert(final String alertText) {
    Window.alert(alertText);
  }
}
