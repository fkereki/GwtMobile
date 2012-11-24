package com.kereki.gwtmobile.client.MVP;

import java.util.Date;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kereki.gwtmobile.client.DiaryService;
import com.kereki.gwtmobile.client.DiaryServiceAsync;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;

public class Model {
  private final DiaryServiceAsync diaryService= GWT.create(DiaryService.class);
  private final ListOfEntries myList= new ListOfEntries();

  private final Storage localStorage;
  private final StorageMap cache;

  private static final String SEPARATOR= "~~~";
  private static final String ENTRY_PREFIX= "entry.";
  private static final String PENDING_PREFIX= "to.add.";



  public Model() {
    localStorage= Storage.getLocalStorageIfSupported();
    if (localStorage != null) {
      cache= new StorageMap(localStorage);
    }
    else {
      cache= null;
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



  public void getListOfEntries(
    final String user,
    final AsyncCallback<ListOfEntries> callback) {

    diaryService.getAllEntries(user, new AsyncCallback<ListOfEntries>() {

      @Override
      public void onFailure(final Throwable caught) {
        /**
         * On DB access failure, try to get whatever we can from the cache, if
         * available
         */
        myList.clear();

        if (cache != null) {
          Object[] cacheKeys= cache.keySet().toArray();
          for (Object cacheKey : cacheKeys) {
            if (((String) cacheKey).startsWith(ENTRY_PREFIX)) {
              String date= ((String) cacheKey).substring(ENTRY_PREFIX.length());
              String[] parts= cache.get(cacheKey).split(SEPARATOR);
              if (parts[0].equals(user)) {
                myList.add(new DiaryEntry(parts[0], date, parts[1], parts[2], Integer
                  .parseInt(parts[3])));
              }
            }
          }
          callback.onSuccess(myList);
        }
        else {
          callback.onFailure(new RuntimeException("Couldn't get entries..."));
        }
      }



      @Override
      public void onSuccess(final ListOfEntries result) {
        /**
         * On DB access success, put everything in the local cache, and then
         * return the list
         */

        myList.clear();

        if (cache != null) {
          for (int i= 0; i < result.size(); i++) {
            cache.put(
              ENTRY_PREFIX + result.get(i).date,
              result.get(i).user + SEPARATOR + result.get(i).title + SEPARATOR
                + result.get(i).text + SEPARATOR + result.get(i).mood);

            myList.add(new DiaryEntry(result.get(i).user, result.get(i).date, result
              .get(i).title, result.get(i).text, result.get(i).mood));
          }
        }

        callback.onSuccess(result);
      }
    });
  }



  public void login(
    final String user,
    final String password,
    final SimpleCallback<Boolean> callback) {

    diaryService.login(user, password, callback);
  }



  public void putEntry(final DiaryEntry myEntry, final AsyncCallback<Void> callback) {

    diaryService.putEntry(myEntry, new AsyncCallback<Void>() {

      @Override
      public void onFailure(final Throwable caught) {
        /**
         * On failure, add the entry to the cache and mark it as pending
         */
        if (cache != null) {
          cache.put(ENTRY_PREFIX + myEntry.date, myEntry.user + SEPARATOR + myEntry.title
            + SEPARATOR + myEntry.text + SEPARATOR + myEntry.mood);
          cache.put(PENDING_PREFIX + myEntry.date, myEntry.date);
          callback.onSuccess(null);
        }
        else {
          callback.onFailure(new RuntimeException("Couldn't put entry..."));
        }
      }



      @Override
      public void onSuccess(final Void result) {
        if (cache != null) {
          cache.put(ENTRY_PREFIX + myEntry.date, myEntry.user + SEPARATOR + myEntry.title
            + SEPARATOR + myEntry.text + SEPARATOR + myEntry.mood);
        }
        callback.onSuccess(null);
      }
    });
  }



  public void putPendingEntries() {

    if (cache != null) {
      Object[] cacheKeys= cache.keySet().toArray();
      for (Object cacheKey : cacheKeys) {
        if (((String) cacheKey).startsWith(PENDING_PREFIX)) {
          String date= cache.get(cacheKey);
          String[] parts= cache.get(ENTRY_PREFIX + date).split(SEPARATOR);
          DiaryEntry entry= new DiaryEntry(parts[0], date, parts[1], parts[2],
            Integer.parseInt(parts[3]));
          cache.remove(cacheKey); // if PUT fails, it will be added again
          putEntry(entry, new SimpleCallback<Void>() {
            @Override
            public void goBack(final Void result) {
              // nothing to do; putEntry(...) took care of everything
            }
          });
        }
      }
    }
  }



  public void testConnection(final int everySeconds, final AsyncCallback<Void> callback) {

    Scheduler.get().scheduleFixedDelay(new Scheduler.RepeatingCommand() {
      @Override
      public boolean execute() {
        final long sendTime= (new Date()).getTime();
        final String currentPing= "ping." + sendTime;
        diaryService.ping(currentPing, new AsyncCallback<String>() {

          @Override
          public void onFailure(final Throwable caught) {
            callback.onFailure(null);
          }



          @Override
          public void onSuccess(final String result) {
            /*
             * If the answer comes, but is delayed more than 3 seconds, let's
             * consider it to be offline.
             */
            final long answerTime= (new Date()).getTime();
            if ((answerTime - sendTime) < 3000) {
              callback.onSuccess(null);
            }
            else {
              callback.onFailure(null);
            }
          }
        });

        return true; // the command will be invoked again
      }
    }, everySeconds * 1000);
  }
}
