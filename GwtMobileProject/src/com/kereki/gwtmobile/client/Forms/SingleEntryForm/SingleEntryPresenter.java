package com.kereki.gwtmobile.client.Forms.SingleEntryForm;

import java.util.Date;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.kereki.gwtmobile.client.Forms.AllEntriesForm.AllEntriesPresenter;
import com.kereki.gwtmobile.client.MVP.Environment;
import com.kereki.gwtmobile.client.MVP.Presenter;
import com.kereki.gwtmobile.client.Utilities.Html;
import com.kereki.gwtmobile.client.Utilities.KeyValueMap;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;
import com.kereki.gwtmobile.shared.DiaryEntry;

public class SingleEntryPresenter extends Presenter<SingleEntryDisplay> {
  public static String PLACE= "singleentry";


  public SingleEntryPresenter(
    final String params,
    final SingleEntryDisplay singleEntryDisplay,
    final Environment environment) {

    super(params, singleEntryDisplay, environment);

    String dateToEdit= "";
    if (!params.isEmpty()) {
      dateToEdit= (new KeyValueMap(params)).get("date");
    }

    if ((dateToEdit != null) && !dateToEdit.isEmpty()) {
      final DiaryEntry toEdit= environment.getModel().getSingleEntry(dateToEdit);
      singleEntryDisplay.setViewTitle("Edit Entry");
      singleEntryDisplay.setEntryDate(Html.htmlSpecialChars(dateToEdit));
      singleEntryDisplay.setEntryTitle(toEdit.title);
      singleEntryDisplay.setEntryText(toEdit.text);
      singleEntryDisplay.setMood(toEdit.mood);
    }
    else {
      dateToEdit= DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      singleEntryDisplay.setViewTitle("Add Entry");
      singleEntryDisplay.setEntryDate(Html.htmlSpecialChars(dateToEdit));
      singleEntryDisplay.setEntryTitle("");
      singleEntryDisplay.setEntryText("");
      singleEntryDisplay.setMood(0);
    }

    singleEntryDisplay.setSaveCallback(new SimpleCallback<Object>() {
      @Override
      public void goBack(final Object result) {
        environment.getModel().putEntry(
          new DiaryEntry(environment.getUser(), singleEntryDisplay.getEntryDate(),
            singleEntryDisplay.getEntryTitle(), singleEntryDisplay.getEntryText(),
            singleEntryDisplay.getMood()), new SimpleCallback<Void>() {

            @Override
            public void goBack(final Void result) {
              environment.launch(AllEntriesPresenter.PLACE);
            }
          });
      }
    });

    singleEntryDisplay.setCancelCallback(new SimpleCallback<Object>() {
      @Override
      public void goBack(final Object result) {
        environment.launch(AllEntriesPresenter.PLACE);
      }
    });
  }
}