package com.kereki.gwtmobile.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.kereki.gwtmobile.client.DiaryService;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DiaryServiceImpl extends RemoteServiceServlet implements DiaryService {
  static String jdbc_url= "jdbc:mysql://192.168.1.200/gwtDiary";
  static String mysql_user= "fkereki_user";
  static String mysql_password= "fkereki_pass";


  private Connection conn= null;


  @Override
  public void PutEntry(DiaryEntry myEntry) throws IllegalArgumentException {
  }

  @Override
  public ListOfEntries GetAllEntries() {
    ListOfEntries myList= new ListOfEntries();

    try {
      connectToDatabase();

      final Statement stmt= conn.createStatement();
      final ResultSet rs= stmt
        .executeQuery("SELECT * FROM entries ORDER BY entryDate DESC");
      while (rs.next()) {

        /*
         * Careful: ResultSet.getDate() returns a java.sql.Date, not a
         * java.util.Date
         */

        myList.add(new DiaryEntry(rs.getTimestamp("entryDate"), rs
          .getString("entryTitle"), rs.getString("entryText"), rs.getInt("entryMood")));
      }
      stmt.close();

      disconnectFromDatabase();

    } catch (final Exception e) {
      e.printStackTrace();
    }

    return myList;
  }

  private void connectToDatabase() throws Exception {
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Class.forName("com.mysql.jdbc.Driver");
    conn= DriverManager.getConnection(jdbc_url, mysql_user, mysql_password);
  }

  private void disconnectFromDatabase() throws Exception {
    conn.close();
  }

}
