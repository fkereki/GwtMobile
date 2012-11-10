package com.kereki.gwtmobile.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface DiaryService extends RemoteService {
  ListOfEntries GetAllEntries();

  void PutEntry(DiaryEntry myEntry) throws IllegalArgumentException;
}
