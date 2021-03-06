package com.kereki.gwtmobile.client.Forms.AllEntriesForm;

import com.kereki.gwtmobile.client.Forms.OneEntryForm.OneEntryPresenter;
import com.kereki.gwtmobile.client.MVP.Environment;
import com.kereki.gwtmobile.client.MVP.Presenter;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;
import com.kereki.gwtmobile.shared.ListOfEntries;

public class AllEntriesPresenter extends Presenter<AllEntriesDisplay> {

  public static String PLACE= "allentries";



  public AllEntriesPresenter(
    final String params,
    final AllEntriesDisplay allEntriesDisplay,
    final Environment environment) {

    super(params, allEntriesDisplay, environment);

    environment.getModel().getListOfEntries(environment.getUser(),
      new SimpleCallback<ListOfEntries>() {
        @Override
        public void goBack(final ListOfEntries result) {
          for (int i= 0; i < result.size(); i++) {
            allEntriesDisplay.setEntryData(i, result.get(i).date, result.get(i).title,
              result.get(i).text);
          }
        }
      });

    allEntriesDisplay.setAddCallback(new SimpleCallback<Object>() {
      @Override
      public void goBack(final Object result) {
        environment.launch(OneEntryPresenter.PLACE + "?date=");
      }
    });

    allEntriesDisplay.setEditCallback(new SimpleCallback<Object>() {
      @Override
      public void goBack(final Object result) {
        if (allEntriesDisplay.getSelectedDate().isEmpty()) {
          environment.launch(OneEntryPresenter.PLACE + "?date=");
        }
        else {
          environment.launch(OneEntryPresenter.PLACE + "?date="
            + allEntriesDisplay.getSelectedDate());
        }
      }
    });
  }
}
