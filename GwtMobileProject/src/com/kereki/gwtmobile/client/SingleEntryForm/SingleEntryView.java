package com.kereki.gwtmobile.client.SingleEntryForm;

import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesPresenter;


public class SingleEntryView extends com.kereki.gwtmobile.client.View implements
  SingleEntryDisplay {

  public SingleEntryView() {
    VerticalPanel panel= new VerticalPanel();
    panel.add(new Label("ESTA ES LA SINGLEENTRYVIEW"));
    panel.add(new Hyperlink("vamos al principal", AllEntriesPresenter.PLACE));
    panel.add(new Label(""));
    initWidget(panel);
  }
}
