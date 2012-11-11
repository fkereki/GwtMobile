package com.kereki.gwtmobile.client.AllEntriesForm;

import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.kereki.gwtmobile.client.SingleEntryForm.SingleEntryPresenter;


public class AllEntriesView extends com.kereki.gwtmobile.client.View implements
  AllEntriesDisplay {

  public AllEntriesView() {
    VerticalPanel panel= new VerticalPanel();
    panel.add(new Label("ESTA ES LA"));
    panel.add(new Label("SUPER PANTALLA"));
    panel.add(new Label("GENERAL"));
    panel.add(new Hyperlink("vamos a la single entry", SingleEntryPresenter.PLACE));
    panel.add(new Label(""));
    initWidget(panel);
  }
}
