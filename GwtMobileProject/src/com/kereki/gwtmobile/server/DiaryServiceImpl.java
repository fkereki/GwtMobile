package com.kereki.gwtmobile.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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



  private void connectToDatabase() throws Exception {
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    Class.forName("com.mysql.jdbc.Driver");
    conn= DriverManager.getConnection(jdbc_url, mysql_user, mysql_password);
  }



  private void disconnectFromDatabase() throws Exception {
    conn.close();
  }



  @Override
  public ListOfEntries getAllEntries(final String user) throws RuntimeException {
    ListOfEntries myList= new ListOfEntries();

    try {
      connectToDatabase();

      final PreparedStatement stmt= conn
        .prepareStatement("SELECT * FROM entries WHERE entryUser=? ORDER BY entryDate DESC");
      stmt.setString(1, user);
      final ResultSet rs= stmt.executeQuery();
      while (rs.next()) {
        /*
         * The weird substring(...) call is to avoid an extra ".0" that gets
         * added after conversion of the date to a string
         */
        myList.add(new DiaryEntry(rs.getString("entryUser"), rs.getString("entryDate")
          .substring(0, 19), rs.getString("entryTitle"), rs.getString("entryText"), rs
          .getInt("entryMood")));
      }
      stmt.close();
      disconnectFromDatabase();
    }
    catch (final Exception e) {
      throw new RuntimeException("Couldn't get entries for user " + user + " - "
        + e.getMessage());
    }

    return myList;
  }



  @Override
  public boolean login(final String user, final String password) {
    boolean result= false;
    try {
      connectToDatabase();

      final PreparedStatement stmt= conn
        .prepareStatement("SELECT COUNT(*) AS nn FROM users WHERE userName=? AND userPassword=?");
      stmt.setString(1, user);
      stmt.setString(2, password);
      final ResultSet rs= stmt.executeQuery();
      rs.next();
      result= (rs.getInt("nn") == 1);
      stmt.close();
      disconnectFromDatabase();
    }
    catch (final Exception e) {
      throw new RuntimeException("Couldn't get entries for user " + user + " - "
        + e.getMessage());
    }
    return result;
  }



  @Override
  public String ping(final String pong) {
    return pong;
  }



  @Override
  public void putEntry(final DiaryEntry myEntry) throws RuntimeException {
    if (myEntry == null) {
      throw new RuntimeException("myEntry must be non-null");
    }
    else {
      try {
        connectToDatabase();

        final PreparedStatement stmt= conn
          .prepareStatement("INSERT INTO entries "
            + "(entryUser, entryDate, entryTitle, entryText, entryMood) VALUES (?, ?, ?, ?, ?) "
            + "ON DUPLICATE KEY UPDATE entryTitle=?, entryText=?, entryMood=?");

        stmt.setString(1, myEntry.user);
        stmt.setString(2, myEntry.date);
        stmt.setString(3, myEntry.title);
        stmt.setString(4, myEntry.text);
        stmt.setInt(5, myEntry.mood);
        stmt.setString(6, myEntry.title);
        stmt.setString(7, myEntry.text);
        stmt.setInt(8, myEntry.mood);
        stmt.execute();
        stmt.close();

        disconnectFromDatabase();
      }
      catch (final Exception e) {
        throw new RuntimeException("Cannot INSERT or UPDATE - " + e.getMessage());
      }
    }
  }
}
