package com.kereki.gwtmobile.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesPresenter;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryPresenter;

public class Environment {
  final Environment self= this;
  final ViewFactory viewFactory;
  final Model model;

  /*
   * The Presenters are created lazily
   */
  AllEntriesPresenter allEntriesPresenter= null;
  SingleEntryPresenter singleEntryPresenter= null;


  public Environment(Model aModel, ViewFactory aViewFactory) {
    model= aModel;
    viewFactory= aViewFactory;
  }

  public Model getModel() {
    return model;
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

    } else if (token.equals(AllEntriesPresenter.PLACE)) {
      GWT.runAsync(new RunAsyncCallback() {
        @Override
        public void onFailure(Throwable reason) {
          showAlert("Cannot show the 'all entries' form...");
        }

        @Override
        public void onSuccess() {
          if (self.allEntriesPresenter == null) {
            self.allEntriesPresenter= new AllEntriesPresenter(args2, viewFactory
              .getAllEntriesView(), self);
          }
          RootPanel.get().clear();
          RootPanel.get().add(self.allEntriesPresenter.getDisplay().asWidget());
        }
      });


    } else if (token.equals(SingleEntryPresenter.PLACE)) {
      GWT.runAsync(new RunAsyncCallback() {
        @Override
        public void onFailure(Throwable reason) {
          showAlert("Cannot show the 'single entry' form...");
        }

        @Override
        public void onSuccess() {
          if (self.singleEntryPresenter == null) {
            self.singleEntryPresenter= new SingleEntryPresenter(args2, viewFactory
              .getSingleEntryView(), self);
          }
          RootPanel.get().clear();
          RootPanel.get().add(self.singleEntryPresenter.getDisplay().asWidget());
        }
      });

    } else {
      showAlert("Unrecognized token=" + token);
      token= "";
    }
  }

  public void showAlert(String alertText) {
    Window.alert(alertText);
  }
}
