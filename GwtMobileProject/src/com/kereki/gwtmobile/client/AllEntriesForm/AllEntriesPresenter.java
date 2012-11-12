package com.kereki.gwtmobile.client.AllEntriesForm;

import com.kereki.gwtmobile.client.Environment;
import com.kereki.gwtmobile.client.Presenter;
import com.kereki.gwtmobile.client.SimpleCallback;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryPresenter;

public class AllEntriesPresenter extends Presenter<AllEntriesDisplay> {

  public static String PLACE= "allentries";


  public AllEntriesPresenter(
    final String params,
    final AllEntriesDisplay allEntriesDisplay,
    final Environment environment) {

    super(params, allEntriesDisplay, environment);

    allEntriesDisplay.setEntryData(0, "1960-09-22 01:23:45", "titulo de FK",
      "un gran texto de FK", 22);
    allEntriesDisplay.setEntryData(1, "1963-11-24 05:06:07", "ines aca",
      "el texto de inesofa", 33);
    allEntriesDisplay.setEntryData(2, "1973-12-20 08:18:28", "griselda's",
      "la griselda canta", 44);
    allEntriesDisplay.setEntryData(3, "1977-11-19 21:20:19", "dale ale", "alejopo", 11);

    allEntriesDisplay.setAddCallback(new SimpleCallback<Object>() {

      @Override
      public void goBack(Object result) {
        environment.launch(SingleEntryPresenter.PLACE + "?date=");
      }
    });

    allEntriesDisplay.setEditCallback(new SimpleCallback<Object>() {

      @Override
      public void goBack(Object result) {
        environment.launch(SingleEntryPresenter.PLACE + "?date="
          + allEntriesDisplay.getSelectedDate());
      }
    });
  }
}
