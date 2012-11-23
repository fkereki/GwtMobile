package com.kereki.gwtmobile.client.Forms.LoginForm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.kereki.gwtmobile.client.MVP.View;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;

public class LoginView extends View implements LoginDisplay {
  private final HTML title= new HTML();
  private final VerticalPanel vp= new VerticalPanel();
  private final FlexTable ft= new FlexTable();
  private final Label userLabel= new Label("User:");
  private final Label passwordLabel= new Label("Password:");
  private final TextBox userTextbox= new TextBox();
  private final TextBox passwordTextbox= new PasswordTextBox();
  private final Button loginButton= new Button("Enter");

  SimpleCallback<Object> onLoginClickCallback;



  public LoginView() {
    loginButton.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(final ClickEvent event) {
        onLoginClickCallback.goBack();
      }
    });

    Window.addResizeHandler(new ResizeHandler() {
      @Override
      public void onResize(final ResizeEvent event) {
        redraw();
      }
    });

    title.setHTML("<h1>Login</h1>");

    vp.setWidth("100%");
    vp.setHeight("100%");
    vp.add(title);
    vp.add(ft);

    redraw();
    initWidget(vp);
  }



  @Override
  public void redraw() {
    ft.clear();
    if (Window.getClientHeight() > Window.getClientWidth()) { // portrait
      ft.setWidget(0, 0, userLabel);
      ft.setWidget(1, 0, userTextbox);
      ft.setWidget(2, 0, passwordLabel);
      ft.setWidget(3, 0, passwordTextbox);
      ft.setWidget(4, 0, loginButton);
    }
    else { // landscape
      ft.setWidget(0, 0, userLabel);
      ft.setWidget(0, 1, userTextbox);
      ft.setWidget(1, 0, passwordLabel);
      ft.setWidget(1, 1, passwordTextbox);
      ft.setWidget(2, 1, loginButton);
    }
  }



  @Override
  public String getUser() {
    return userTextbox.getValue();
  }



  @Override
  public String getPassword() {
    return passwordTextbox.getValue();
  }



  @Override
  public void SetLoginCallback(final SimpleCallback<Object> callback) {
    onLoginClickCallback= callback;
  }



  @Override
  public void setUser(final String anUser) {
    userTextbox.setValue(anUser);
  }



  @Override
  public void setPassword(final String aPassword) {
    passwordTextbox.setValue(aPassword);
  }
}
