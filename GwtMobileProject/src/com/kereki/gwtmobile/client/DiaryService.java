package com.kereki.gwtmobile.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("diary")
public interface DiaryService extends RemoteService {
  ListOfEntries getAllEntries();

  String ping(String pong);

  void putEntry(DiaryEntry myEntry) throws RuntimeException;
}
