package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//JavaUrlExample.java
 
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
 
public class JavaUrlExample
{
  public static void main(String[] args)
  {
    new JavaUrlExample();
  }
 
  public JavaUrlExample()
  {
    String contents = downloadURL("http://www.dublincity.ie/dublintraffic/cpdata.xml");  // a sample URL
    System.out.println("RAW CONTENTS:" + "\n" + contents);
 
    String strippedContents = parseString(contents);
    System.out.println("\n\nSTRIPPED CONTENTS:" + "\n" + strippedContents);
 
  }
 
  private String downloadURL(String theURL)
  {
    URL u;
    InputStream is = null;
    DataInputStream dis;
    String s;
    StringBuffer sb = new StringBuffer();
 
    try
    {
      u = new URL(theURL);
      is = u.openStream();
      dis = new DataInputStream(new BufferedInputStream(is));
      while ((s = dis.readLine()) != null)
      {
        sb.append(s + "\n");
      }
    }
    catch (MalformedURLException mue)
    {
      System.out.println("Ouch - a MalformedURLException happened.");
      mue.printStackTrace();
      System.exit(1);
    }
    catch (IOException ioe)
    {
      System.out.println("Oops- an IOException happened.");
      ioe.printStackTrace();
      System.exit(1);
    }
    finally
    {
      try
      {
        is.close();
      }
      catch (IOException ioe)
      {
      }
    }
    return sb.toString();
  }
 
  public String parseString(String s)
  {
    String output = null;
 
    Pattern replaceWhitespacePattern = Pattern.compile("\\s");
    Matcher matcher = null;
    matcher = replaceWhitespacePattern.matcher(s);
    output = matcher.replaceAll(" ");
 
    Pattern removeHTMLTagsPattern = Pattern.compile("<!--?[^-->]*>");
    matcher = removeHTMLTagsPattern.matcher(output);
    output = matcher.replaceAll("");
 
    Pattern leaveOnlyAlphaNumericCharactersPattern = Pattern.compile("[^0-9a-zA-Z ]");
    matcher = leaveOnlyAlphaNumericCharactersPattern.matcher(output);
    output = matcher.replaceAll("");
 
    return output;
 
  }
}