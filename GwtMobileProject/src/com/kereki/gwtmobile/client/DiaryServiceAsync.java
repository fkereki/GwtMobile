package com.kereki.gwtmobile.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface DiaryServiceAsync {
  void GetAllEntries(AsyncCallback<ListOfEntries> callback);

  void PutEntry(DiaryEntry myEntry, AsyncCallback<Void> callback);
}
