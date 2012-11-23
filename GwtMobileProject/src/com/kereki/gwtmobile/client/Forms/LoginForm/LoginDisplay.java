package com.kereki.gwtmobile.client.Forms.LoginForm;

import com.kereki.gwtmobile.client.MVP.Display;
import com.kereki.gwtmobile.client.Utilities.SimpleCallback;

public interface LoginDisplay extends Display {
  String getUser();



  String getPassword();



  void SetLoginCallback(SimpleCallback<Object> callback);



  void setUser(final String anUser);



  void setPassword(final String aPassword);
}
