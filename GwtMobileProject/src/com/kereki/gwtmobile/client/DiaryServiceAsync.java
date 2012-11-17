package com.kereki.gwtmobile.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;

public interface DiaryServiceAsync {
  void getAllEntries(AsyncCallback<ListOfEntries> callback);

  void ping(String pong, AsyncCallback<String> callback);

  void putEntry(DiaryEntry myEntry, AsyncCallback<Void> callback);
}
