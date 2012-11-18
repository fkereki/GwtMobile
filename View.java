package com.kereki.gwtmobile.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public abstract class View extends Composite {

  @Override
  public final Widget asWidget() {
    return this;
  }

  /**
   * Redraw/reposition objects after a window resize
   */
  abstract public void redraw();
}
