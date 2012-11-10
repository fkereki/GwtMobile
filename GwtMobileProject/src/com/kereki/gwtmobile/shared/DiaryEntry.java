package com.kereki.gwtmobile.shared;

import java.io.Serializable;
import java.util.Date;


public class DiaryEntry implements Serializable {
  private static final long serialVersionUID= -2627796481624444830L;
  public Date date;
  public String title;
  public String text;
  public int mood;


  public DiaryEntry() {

  }

  public DiaryEntry(Date myDateTime, String myTitle, String myText, int myMood) {
    date= myDateTime;
    title= myTitle;
    text= myText;
    mood= myMood;
  }
}
