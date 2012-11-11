package com.kereki.gwtmobile.client.SingleEntryForm;

import com.google.gwt.user.client.Window;
import com.kereki.gwtmobile.client.Environment;
import com.kereki.gwtmobile.client.Presenter;
import com.kereki.gwtmobile.client.SimpleCallback;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesPresenter;

public class SingleEntryPresenter extends Presenter<SingleEntryDisplay> {

  public static String PLACE= "singleentry";


  public SingleEntryPresenter(
    final String params,
    final SingleEntryDisplay singleEntryDisplay,
    final Environment environment) {

    super(params, singleEntryDisplay, environment);

    singleEntryDisplay.setSaveCallback(new SimpleCallback<Object>() {
      @Override
      public void goBack(Object result) {
        Window.alert("Here, we'd validate and possibly save the new entry");
        environment.launch(AllEntriesPresenter.PLACE);
      }
    });

    singleEntryDisplay.setCancelCallback(new SimpleCallback<Object>() {
      @Override
      public void goBack(Object result) {
        environment.launch(AllEntriesPresenter.PLACE);
      }
    });
  }
}
