package com.kereki.gwtmobile.client;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesPresenter;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesView;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryPresenter;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryView;

public class Environment {
  final Model model;
  final Grid rootDisplay= new Grid(2, 1);
  final MenuBar runMenuBar= new MenuBar();


  public Environment(Model aModel) {
    model= aModel;
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

    RootPanel.get().clear();

    if (token.isEmpty()) {
      // no need to do anything...

    } else if (token.equals(AllEntriesPresenter.PLACE)) {
      RootPanel.get()
        .add(
          new AllEntriesPresenter(args, new AllEntriesView(), this).getDisplay()
            .asWidget());

    } else if (token.equals(SingleEntryPresenter.PLACE)) {
      RootPanel.get().add(
        new SingleEntryPresenter(args, new SingleEntryView(), this).getDisplay()
          .asWidget());

    } else {
      showAlert("Unrecognized token=" + token);
      token= "";
    }
  }

  public void showAlert(String alertText) {
    Window.alert(alertText);
  }
}
