package com.kereki.gwtmobile.client.Forms.OneEntryForm;

import java.util.Date;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesPresenter;
import com.kereki.gwtmobile.client.MVP.Environment;
import com.kereki.gwtmobile.client.MVP.Presenter;
import com.kereki.gwtmobile.client.Utilities.Html;
import com.kereki.gwtmobile.client.Utilities.KeyValueMap;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;
import com.kereki.gwtmobile.shared.DiaryEntry;

public class OneEntryPresenter extends Presenter<OneEntryDisplay> {
  public static String PLACE= "oneentry";


  public OneEntryPresenter(
    final String params,
    final OneEntryDisplay oneEntryDisplay,
    final Environment environment) {

    super(params, oneEntryDisplay, environment);

    String dateToEdit= "";
    if (!params.isEmpty()) {
      dateToEdit= (new KeyValueMap(params)).get("date");
    }

    if ((dateToEdit != null) && !dateToEdit.isEmpty()) {
      final DiaryEntry toEdit= environment.getModel().getSingleEntry(dateToEdit);
      oneEntryDisplay.setViewTitle("Edit Entry");
      oneEntryDisplay.setEntryDate(Html.htmlSpecialChars(dateToEdit));
      oneEntryDisplay.setEntryTitle(toEdit.title);
      oneEntryDisplay.setEntryText(toEdit.text);
      oneEntryDisplay.setMood(toEdit.mood);
    }
    else {
      dateToEdit= DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      oneEntryDisplay.setViewTitle("Add Entry");
      oneEntryDisplay.setEntryDate(Html.htmlSpecialChars(dateToEdit));
      oneEntryDisplay.setEntryTitle("");
      oneEntryDisplay.setEntryText("");
      oneEntryDisplay.setMood(0);
    }

    oneEntryDisplay.setSaveCallback(new SimpleCallback<Object>() {
      @Override
      public void goBack(final Object result) {
        environment.getModel().putEntry(
          new DiaryEntry(environment.getUser(), oneEntryDisplay.getEntryDate(),
            oneEntryDisplay.getEntryTitle(), oneEntryDisplay.getEntryText(),
            oneEntryDisplay.getMood()), new SimpleCallback<Void>() {

            @Override
            public void goBack(final Void result) {
              environment.launch(AllEntriesPresenter.PLACE);
            }
          });
      }
    });

    oneEntryDisplay.setCancelCallback(new SimpleCallback<Object>() {
      @Override
      public void goBack(final Object result) {
        environment.launch(AllEntriesPresenter.PLACE);
      }
    });
  }
}
