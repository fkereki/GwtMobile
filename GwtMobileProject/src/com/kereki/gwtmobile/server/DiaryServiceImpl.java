package com.kereki.gwtmobile.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.kereki.gwtmobile.client.DiaryService;
import com.kereki.gwtmobile.shared.DiaryEntry;
import com.kereki.gwtmobile.shared.ListOfEntries;


@SuppressWarnings("serial")
public class DiaryServiceImpl extends RemoteServiceServlet implements DiaryService {
  static String jdbc_url= "jdbc:mysql://192.168.1.200/gwtDiary";
  static String mysql_user= "fkereki_user";
  static String mysql_password= "fkereki_pass";
  private Connection conn= null;


  @Override
  public void putEntry(DiaryEntry myEntry) throws RuntimeException {
    if (myEntry == null) {
      throw new RuntimeException("myEntry must be non-null");
    } else {
      try {
        connectToDatabase();

        final PreparedStatement stmt= conn.prepareStatement("INSERT INTO entries "
          + "(entryDate, entryTitle, entryText, entryMood) VALUES (?, ?, ?, ?) "
          + "ON DUPLICATE KEY UPDATE entryTitle=?, entryText=?, entryMood=?");

        stmt.setString(1, myEntry.date);
        stmt.setString(2, myEntry.title);
        stmt.setString(3, myEntry.text);
        stmt.setInt(4, myEntry.mood);
        stmt.setString(5, myEntry.title);
        stmt.setString(6, myEntry.text);
        stmt.setInt(7, myEntry.mood);
        stmt.execute();
        stmt.close();

        disconnectFromDatabase();

      } catch (final Exception e) {
        throw new RuntimeException("Cannot INSERT or UPDATE - " + e.getMessage());
      }
    }
  }

  @Override
  public ListOfEntries getAllEntries() throws RuntimeException {
    ListOfEntries myList= new ListOfEntries();

    try {
      connectToDatabase();

      final Statement stmt= conn.createStatement();
      final ResultSet rs= stmt
        .executeQuery("SELECT * FROM entries ORDER BY entryDate DESC");
      while (rs.next()) {
        /*
         * The weird substring(...) call is to avoid an extra ".0" that gets
         * added after conversion of the date to a string
         */
        myList.add(new DiaryEntry(rs.getString("entryDate").substring(0, 19), rs
          .getString("entryTitle"), rs.getString("entryText"), rs.getInt("entryMood")));
      }
      stmt.close();

      disconnectFromDatabase();

    } catch (final Exception e) {
      throw new RuntimeException("Couldn't get list - " + e.getMessage());
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

  @Override
  public String ping(String pong) {
    return pong;
  }
}
