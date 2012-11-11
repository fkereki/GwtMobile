package com.kereki.gwtmobile.client.AllEntriesForm;

import com.kereki.gwtmobile.client.Environment;
import com.kereki.gwtmobile.client.Presenter;

public class AllEntriesPresenter extends Presenter<AllEntriesDisplay> {

  public static String PLACE= "allentries";


  public AllEntriesPresenter(
    final String params,
    final AllEntriesDisplay allEntriesDisplay,
    final Environment environment) {

    super(params, allEntriesDisplay, environment);

    // getDisplay().setear callbacks, etc.
  }
}
