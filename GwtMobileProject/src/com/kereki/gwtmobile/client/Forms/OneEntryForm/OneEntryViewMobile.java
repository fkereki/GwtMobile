package com.kereki.gwtmobile.client.Forms.OneEntryForm;


import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.kereki.gwtmobile.client.MVP.View;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;

public class OneEntryViewMobile extends View implements OneEntryDisplay {

  final Panel myself= new VerticalPanel();
  final OneEntryDisplay vp= new OneEntryViewMobilePortrait();
  final OneEntryDisplay vl= new OneEntryViewMobileLandscape();
  OneEntryDisplay currentView= null;



  @Override
  public String getEntryDate() {
    return currentView.getEntryDate();
  }



  @Override
  public String getEntryText() {
    return currentView.getEntryText();
  }



  @Override
  public String getEntryTitle() {
    return currentView.getEntryTitle();
  }



  @Override
  public int getMood() {
    return currentView.getMood();
  }



  @Override
  public void setCancelCallback(final SimpleCallback<Object> aCallback) {
    vl.setCancelCallback(aCallback);
    vp.setCancelCallback(aCallback);
  }



  @Override
  public void setEntryDate(final String aDate) {
    currentView.setEntryDate(aDate);
  }



  @Override
  public void setEntryText(final String aText) {
    currentView.setEntryText(aText);
  }



  @Override
  public void setEntryTitle(final String aTitle) {
    currentView.setEntryTitle(aTitle);
  }



  @Override
  public void setMood(final int aMood) {
    currentView.setMood(aMood);
  }



  @Override
  public void setSaveCallback(final SimpleCallback<Object> aCallback) {
    vl.setSaveCallback(aCallback);
    vp.setSaveCallback(aCallback);
  }



  @Override
  public void setViewTitle(final String aTitle) {
    // TODO Auto-generated method stub

  }



  @Override
  public void redraw() {
    OneEntryDisplay newView= (Window.getClientHeight() > Window.getClientWidth())? vp: vl;

    if (currentView != newView) {
      if (currentView != null) { // pass values from the swapped out view
        newView.setEntryDate(currentView.getEntryDate());
        newView.setEntryTitle(currentView.getEntryTitle());
        newView.setEntryText(currentView.getEntryText());
        newView.setMood(currentView.getMood());
      }

      currentView= newView;
      myself.clear();
      myself.add((Composite) currentView);
    }
  }



  public OneEntryViewMobile() {
    Window.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(final ResizeEvent event) {
        redraw();
      }
    });

    redraw();
    initWidget(myself);
  }

}
