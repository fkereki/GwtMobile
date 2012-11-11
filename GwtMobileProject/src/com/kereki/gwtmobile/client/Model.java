package com.kereki.gwtmobile.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;

public class Model {
  private final DiaryServiceAsync diaryService= GWT.create(DiaryService.class);
  private Storage localStorage= null;
  private StorageMap cache= null;


  public Model() {
    localStorage= Storage.getLocalStorageIfSupported();
    if (localStorage != null) {
      cache= new StorageMap(localStorage);
    }
  }

  public void getListOfEntries(final AsyncCallback<ListOfEntries> callback) {
    final String SEPARATOR= "~~~";
    final String PREFIX= "entry.";

    diaryService.getAllEntries(new AsyncCallback<ListOfEntries>() {

      @Override
      public void onFailure(Throwable caught) {
        /**
         * On DB access failure, try to get whatever we can from the cache, if
         * available
         */

        ListOfEntries myList= new ListOfEntries();

        if (localStorage != null) {
          Object[] cacheKeys= cache.keySet().toArray();
          for (Object cacheKey : cacheKeys) {
            if (((String) cacheKey).startsWith(PREFIX)) {
              String date= ((String) cacheKey).substring(PREFIX.length());
              String[] parts= cache.get(cacheKey).split(SEPARATOR);
              myList.add(new DiaryEntry(date, parts[0], parts[1], 22));
            }
          }
        }

        callback.onSuccess(myList);
      }

      @Override
      public void onSuccess(ListOfEntries result) {
        /**
         * On DB access success, put everything in the local cache, and then
         * return the list
         */

        if (localStorage != null) {
          for (int i= 0; i < result.size(); i++) {
            cache.put("entry." + result.get(i).date, result.get(i).title + SEPARATOR
              + result.get(i).text + SEPARATOR + result.get(i).mood);
          }
        }

        callback.onSuccess(result);
      }
    });
  }
}
