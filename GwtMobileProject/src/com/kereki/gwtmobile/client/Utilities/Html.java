package com.kereki.gwtmobile.client.Utilities;

public class Html {

  /*
   * Do minimal changes (as in
   * www.php.net/manual/en/function.htmlspecialchars.php) so the output XML
   * won't include wrong characters.
   */
  public static String htmlSpecialChars(final String aString) {
    String aux= aString;
    aux= aux.replace("&", "&amp;");
    aux= aux.replace("\"", "&quot;");
    aux= aux.replace("'", "&apos;");
    aux= aux.replace("<", "&lt;");
    aux= aux.replace(">", "&gt;");
    return aux;
  }
}