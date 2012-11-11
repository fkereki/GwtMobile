package com.kereki.gwtmobile.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesPresenter;

public class GwtMobileProject implements EntryPoint, ValueChangeHandler<String> {

  Environment environment;


  @Override
  public void onModuleLoad() {
    History.addValueChangeHandler(this);
    environment= new Environment(new Model());
    environment.launch(AllEntriesPresenter.PLACE);

    // Date currd= new Date();
    // Window.alert(DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").format(currd));

    // Model model= new Model();
    //
    // model.getListOfEntries(new SimpleCallback<ListOfEntries>() {
    // @Override
    // public void goBack(ListOfEntries result) {
    // String ss= "";
    // for (int i= 0; i < result.size(); i++) {
    // ss+= result.get(i).date + "-" + result.get(i).title + "\n";
    // }
    // Window.alert(ss);
    // }
    // });

    // diaryService.putEntry(new DiaryEntry(new Date(), "a title", "some text",
    // 8),
    // new AsyncCallback<Void>() {
    //
    // @Override
    // public void onFailure(Throwable caught) {
    // Window.alert("FAILURE 2!!");
    // }
    //
    // @Override
    // public void onSuccess(Void result) {
    // Window.alert("SUCCESS 2!!");
    // }
    // });

    // Storage stockStore= Storage.getLocalStorageIfSupported();
    // if (stockStore == null) {
    // Window.alert("No funciona stockstore...");
    // } else {
    // if (stockStore.getItem("myprefix." + "FK") == null) {
    // stockStore.setItem("myprefix.FK", "FEDERICO KEREKI");
    // Window.alert("no estaba, lo puse..." + stockStore.getLength());
    // } else {
    // Window.alert("Me encontré... " + stockStore.getItem("myprefix.FK"));
    // // stockStore.clear();
    // }
    // }

  }

  @Override
  public void onValueChange(ValueChangeEvent<String> event) {
    environment.launch(event.getValue());
  }
}
