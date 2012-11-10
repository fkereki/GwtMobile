package com.kereki.gwtmobile.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.kereki.gwtmobile.client.DiaryService;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DiaryServiceImpl extends RemoteServiceServlet implements DiaryService {
  static String jdbc_url= "jdbc:mysql://127.0.0.1/gwtDiary";
  static String mysql_user= "fkereki_user";
  static String mysql_password= "fkereki_pass";
  private final Connection conn= null;


  @Override
  public void PutEntry(DiaryEntry myEntry) throws IllegalArgumentException {
  }

  @Override
  public ListOfEntries GetAllEntries() {
    ListOfEntries myList= new ListOfEntries();

    myList.add(new DiaryEntry(new Date(), "first", "1st text", 1));
    myList.add(new DiaryEntry(new Date(), "second", "2nd text", 1));
    myList.add(new DiaryEntry(new Date(), "third", "3rd text", 1));
    myList.add(new DiaryEntry(new Date(), "fourth", "4th text", 1));

    return myList;
  }

  private void connectToDatabase() throws Exception {
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conn= DriverManager.getConnection(jdbc_url, mysql_user, mysql_password);
  }

  private void disconnectFromDatabase() throws Exception {
    conn.close();
  }
}
