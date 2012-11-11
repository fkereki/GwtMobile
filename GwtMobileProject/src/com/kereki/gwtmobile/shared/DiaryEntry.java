package com.kereki.gwtmobile.shared;

import java.io.Serializable;


public class DiaryEntry implements Serializable {
  private static final long serialVersionUID= -2627796481624444830L;
  public String date; // "yyyy-MM-dd HH:mm:ss" format, as used by MySQL
  public String title;
  public String text;
  public int mood;


  public DiaryEntry() {

  }

  public DiaryEntry(String myDateTime, String myTitle, String myText, int myMood) {
    date= myDateTime;
    title= myTitle;
    text= myText;
    mood= myMood;
  }

}
