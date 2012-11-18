package com.kereki.gwtmobile.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;

public interface DiaryServiceAsync {
  void getAllEntries(AsyncCallback<ListOfEntries> callback);

  void login(String user, String password, AsyncCallback<Boolean> callback);

  void ping(final String pong, AsyncCallback<String> callback);

  void putEntry(final DiaryEntry myEntry, AsyncCallback<Void> callback);
}
