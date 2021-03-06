package com.kereki.gwtmobile.client.Utilities;

import java.util.HashMap;

/**
 * KeyValueMap: a short way of specifying a class that will be used to pass
 * parameters to forms.
 * 
 * @author fkereki
 */
public class KeyValueMap extends HashMap<String, String> {
  private static final long serialVersionUID= 5225712868559413562L;



  /**
   * Standard constructor; produces an empty KeyValueMap.
   */
  public KeyValueMap() {
    this("");
  }



  /**
   * Create a KeyValueMap, and initialize it with the params string.
   * 
   * @param parameters
   *          A string with URL-like parameters (see below)
   */
  public KeyValueMap(final String parameters) {
    initializeWithString(parameters);
  }



  /**
   * Initialize a KeyValueMap with a parameters URL-like string.
   * 
   * @param parameters
   *          A string formatted like param1=value1&param2=value2&... It is
   *          assumed that the value has been appropriately escaped.
   */
  public final void initializeWithString(final String parameters) {
    clear();
    if ((parameters != null) && !parameters.isEmpty()) {
      String[] args= parameters.split("&");
      for (String element : args) {
        int equalIndex= element.indexOf("=");
        if (equalIndex == -1) {
          put(element, "");
        }
        else {
          put(element.substring(0, equalIndex), element.substring(equalIndex + 1));
        }
      }
    }
  }



  @Override
  public String toString() {
    String result= "";
    String separator= "";
    for (String key : keySet()) {
      result+= separator + key + "=" + get(key);
      separator= "\n";
    }
    return result;
  }
}
