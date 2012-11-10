package com.kereki.gwtmobile.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kereki.gwtmobile.shared.ListOfEntries;

public class GwtMobileProject implements EntryPoint {

  private final DiaryServiceAsync diaryService= GWT.create(DiaryService.class);


  @Override
  public void onModuleLoad() {
    diaryService.GetAllEntries(new AsyncCallback<ListOfEntries>() {

      @Override
      public void onFailure(Throwable caught) {
        Window.alert("FAILURE!!");
      }

      @Override
      public void onSuccess(ListOfEntries result) {
        Window.alert("Got " + result.size() + " and the zeroth says "
          + result.get(0).title + " at " + result.get(0).date.toString());
      }
    });

  }
}
