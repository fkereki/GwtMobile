package com.kereki.gwtmobile.client.SingleEntryForm;

import com.kereki.gwtmobile.client.Environment;
import com.kereki.gwtmobile.client.Presenter;

public class SingleEntryPresenter extends Presenter<SingleEntryDisplay> {

  public static String PLACE= "singleentry";


  public SingleEntryPresenter(
    final String params,
    final SingleEntryDisplay allEntriesDisplay,
    final Environment environment) {

    super(params, allEntriesDisplay, environment);

    // getDisplay().setear callbacks, etc.
  }
}
