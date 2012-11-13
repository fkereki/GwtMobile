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
  private final ListOfEntries myList= new ListOfEntries();
  private final String SEPARATOR= "~~~";
  private final String PREFIX= "entry.";
  private final String PREFIX_PENDING= "to.add.";


  public Model() {
    localStorage= Storage.getLocalStorageIfSupported();
    if (localStorage != null) {
      cache= new StorageMap(localStorage);
    }
  }

  public DiaryEntry getSingleEntry(final String date) {
    for (int i= 0; i < myList.size(); i++) {
      if (myList.get(i).date.equals(date)) {
        return myList.get(i);
      }
    }
    return null;
  }

  public void getListOfEntries(final AsyncCallback<ListOfEntries> callback) {

    diaryService.getAllEntries(new AsyncCallback<ListOfEntries>() {

      @Override
      public void onFailure(Throwable caught) {
        /**
         * On DB access failure, try to get whatever we can from the cache, if
         * available
         */
        myList.clear();

        if (localStorage != null) {
          Object[] cacheKeys= cache.keySet().toArray();
          for (Object cacheKey : cacheKeys) {
            if (((String) cacheKey).startsWith(PREFIX)) {
              String date= ((String) cacheKey).substring(PREFIX.length());
              String[] parts= cache.get(cacheKey).split(SEPARATOR);
              myList.add(new DiaryEntry(date, parts[0], parts[1], Integer
                .parseInt(parts[2])));
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

        myList.clear();

        if (localStorage != null) {
          for (int i= 0; i < result.size(); i++) {
            cache.put(PREFIX + result.get(i).date, result.get(i).title + SEPARATOR
              + result.get(i).text + SEPARATOR + result.get(i).mood);

            myList.add(new DiaryEntry(result.get(i).date, result.get(i).title, result
              .get(i).text, result.get(i).mood));
          }
        }

        callback.onSuccess(result);
      }
    });
  }



  public void putEntry(final DiaryEntry myEntry, final AsyncCallback<Void> callback) {
    diaryService.putEntry(myEntry, new AsyncCallback<Void>() {

      @Override
      public void onFailure(Throwable caught) {
        /**
         * On failure, add the entry to the cache and mark it as pending
         */
        if (localStorage != null) {
          cache.put(PREFIX + myEntry.date, myEntry.title + SEPARATOR + myEntry.text
            + SEPARATOR + myEntry.mood);
          cache.put(PREFIX_PENDING + myEntry.date, myEntry.date);
        }
        callback.onSuccess(null);
      }

      @Override
      public void onSuccess(Void result) {
        /**
         * On success, just return
         */
        callback.onSuccess(null);
      }
    });
  }



  public void putPendingEntries() {
    if (localStorage != null) {
      Object[] cacheKeys= cache.keySet().toArray();
      for (Object cacheKey : cacheKeys) {
        if (((String) cacheKey).startsWith(PREFIX_PENDING)) {
          String date= cache.get(cacheKey);
          String[] parts= cache.get(PREFIX + date).split(SEPARATOR);
          DiaryEntry entry= new DiaryEntry(date, parts[0], parts[1],
            Integer.parseInt(parts[2]));
          cache.remove(cacheKey); // remove it; if the PUT fails, it will be
                                  // added again
          putEntry(entry, new SimpleCallback<Void>() {
            @Override
            public void goBack(Void result) {
              // nothing to do; putEntry(...) took care of everything
            }
          });
        }
      }
    }
  }
}
