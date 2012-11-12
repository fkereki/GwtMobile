package com.kereki.gwtmobile.client.SingleEntryForm;

import com.google.gwt.user.client.Window;
import com.kereki.gwtmobile.client.Environment;
import com.kereki.gwtmobile.client.KeyValueMap;
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

    String dateToEdit= null;
    if (!params.isEmpty()) {
      KeyValueMap args= new KeyValueMap(params);
      dateToEdit= args.get("date");
    } else {
      dateToEdit= "";
    }

    if ((dateToEdit != null) & !dateToEdit.isEmpty()) {
      // conseguir el objeto de clave "dateToEdit"

      singleEntryDisplay.setEntryDate(dateToEdit);
      singleEntryDisplay.setEntryTitle("titulo traido");
      singleEntryDisplay.setEntryText("texto supuestamente traido del servidor");
      singleEntryDisplay.setMood(5);
    } else {

      // dateToEdit= DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").format(new
      // Date());
      singleEntryDisplay.setEntryDate("1111-22-33 44:55:66");
      singleEntryDisplay.setEntryTitle("");
      singleEntryDisplay.setEntryText("");
      singleEntryDisplay.setMood(0);
    }

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
