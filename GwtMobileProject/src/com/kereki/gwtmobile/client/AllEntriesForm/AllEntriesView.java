package com.kereki.gwtmobile.client.AllEntriesForm;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class AllEntriesView extends com.kereki.gwtmobile.client.View implements
  AllEntriesDisplay {

  String pepeValue= "";
  final PopupPanel pup= new PopupPanel(true);
  final Button popupOpener= new Button("Click me");


  public AllEntriesView() {
    VerticalPanel panel= new VerticalPanel();
    panel.add(new Label("kvm..."));
    panel.add(new Label("dos"));
    panel.add(new Hyperlink("vamos al foo", "foo?pepe=continua"));
    panel.add(new Hyperlink("vamos al bar", "bar"));
    panel.add(new Hyperlink("vamos al baZ", "baz"));
    panel.add(new Label(""));
    initWidget(panel);
  }
}
