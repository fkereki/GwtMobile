package com.kereki.gwtmobile.client.MVP;

import com.kereki.gwtmobile.client.Utilities.KeyValueMap;

abstract public class Presenter<D> {
  private String params;
  private D display;
  private Environment environment;
  private KeyValueMap kvm;



  public Presenter() {
  }



  public Presenter(
    final String someParams,
    final D aDisplay,
    final Environment anEnvironment) {
    super();
    params= someParams;
    display= aDisplay;
    environment= anEnvironment;
    kvm= new KeyValueMap(params);
  }



  public D getDisplay() {
    return display;
  }



  public Environment getEnvironment() {
    return environment;
  }



  public KeyValueMap getKvm() {
    return kvm;
  }
}
