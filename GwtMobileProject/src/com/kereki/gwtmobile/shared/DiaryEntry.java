package com.kereki.gwtmobile.shared;

import java.io.Serializable;


public class DiaryEntry implements Serializable {
  private static final long serialVersionUID= -2627796481624444830L;

  public String user;
  public String date; // "yyyy-MM-dd HH:mm:ss" format, as used by MySQL
  public String title;
  public String text;
  public int mood;


  public DiaryEntry() {
  }

  public DiaryEntry(
    final String myUser,
    final String myDateTime,
    final String myTitle,
    final String myText,
    final int myMood) {

    user= myUser;
    date= myDateTime;
    title= myTitle;
    text= myText;
    mood= myMood;
  }

}
