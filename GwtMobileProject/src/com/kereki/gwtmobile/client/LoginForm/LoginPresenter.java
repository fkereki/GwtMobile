package com.kereki.gwtmobile.client.LoginForm;

import com.google.gwt.user.client.Window;
import com.kereki.gwtmobile.client.Environment;
import com.kereki.gwtmobile.client.Presenter;
import com.kereki.gwtmobile.client.AllEntriesForm.AllEntriesPresenter;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;

public class LoginPresenter extends Presenter<LoginDisplay> {
  public static String PLACE= "login";


  public LoginPresenter(
    final String params,
    final LoginDisplay loginDisplay,
    final Environment environment) {

    super(params, loginDisplay, environment);

    loginDisplay.setUser("");
    loginDisplay.setPassword("");
    loginDisplay.SetLoginCallback(new SimpleCallback<Object>() {
      @Override
      public void goBack(final Object result) {
        final String user= loginDisplay.getUser();
        final String password= loginDisplay.getPassword();

        environment.getModel().login(user, password, new SimpleCallback<Boolean>() {

          @Override
          public void goBack(final Boolean result) {
            if (result) {
              Window.alert("User " + user + " accepted.");
              environment.setUser(user);
              environment.launch(AllEntriesPresenter.PLACE);
            }
            else {
              Window.alert("User " + user + " rejected.");
            }
          }
        });
      }
    });

  }
}
