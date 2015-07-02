package Test;

//------------------------------------------------------------//
//  JavaGetUrl.java:                                          //
//------------------------------------------------------------//
//  A Java program that demonstrates a procedure that can be  //
//  used to download the contents of a specified URL.         //
//------------------------------------------------------------//
//  Code created by Developer's Daily                         //
//  <a href="http://www.DevDaily.com" title="http://www.DevDaily.com">http://www.DevDaily.com</a>                                   //
//------------------------------------------------------------//
 
import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
 
public class JavaGetUrl {
    
 
   public static void main (String[] args) {
 
      //-----------------------------------------------------//
      //  Step 1:  Start creating a few objects we'll need.
      //-----------------------------------------------------//
 
      URL u;
      InputStream is = null;
      DataInputStream dis;
      String s;
 
      try {
 
         //------------------------------------------------------------//
         // Step 2:  Create the URL.                                   //
         //------------------------------------------------------------//
         // Note: Put your real URL here, or better yet, read it as a  //
         // command-line arg, or read it from a file.                  //
         //------------------------------------------------------------//
 
         u = new URL("http://www.dublincity.ie/dublintraffic/cpdata.xml");
 
         //----------------------------------------------//
         // Step 3:  Open an input stream from the url.  //
         //----------------------------------------------//
 
         is = u.openStream();         // throws an IOException
 
         //-------------------------------------------------------------//
         // Step 4:                                                     //
         //-------------------------------------------------------------//
         // Convert the InputStream to a buffered DataInputStream.      //
         // Buffering the stream makes the reading faster; the          //
         // readLine() method of the DataInputStream makes the reading  //
         // easier.                                                     //
         //-------------------------------------------------------------//
 
         dis = new DataInputStream(new BufferedInputStream(is));
 
         //------------------------------------------------------------//
         // Step 5:                                                    //
         //------------------------------------------------------------//
         // Now just read each record of the input stream, and print   //
         // it out.  Note that it's assumed that this problem is run   //
         // from a command-line, not from an application or applet.    //
         //------------------------------------------------------------//
 
         while ((s = dis.readLine()) != null) {
//            System.out.println(s);
          
         //Function to cut out carpark name and spaces of the whole XML output
         if(s.indexOf("carpark ")>=0)
         {
            int startIndex1 = s.indexOf("name")+6;
//            System.out.println("indexOf(name) = " + startIndex1);
            int endIndex1 = s.indexOf("spaces")-2;
//            System.out.println("indexOf(\"spaces\") = " + endIndex1);
//            System.out.println(s.substring(startIndex, endIndex));  
            String result1 = (s.substring(startIndex1, endIndex1));
            System.out.println(result1);

            int startIndex2 = s.indexOf("spaces")+8;
            int endIndex2 = s.indexOf(">")-1;  
            String result2 = (s.substring(startIndex2, endIndex2));
            System.out.println(result2);

         }//end if 
         
         else
         {
//           System.out.println( );
             
         }// end else

         } //end while loop
 

         
      } catch (MalformedURLException mue) {
 
         System.out.println("Ouch - a MalformedURLException happened.");
         mue.printStackTrace();
         System.exit(1);
 
      } catch (IOException ioe) {
 
         System.out.println("Oops- an IOException happened.");
         ioe.printStackTrace();
         System.exit(1);
 
      } finally {
 
         //---------------------------------//
         // Step 6:  Close the InputStream  //
         //---------------------------------//
 
         try {
            is.close();
         } catch (IOException ioe) {
            // just going to ignore this one
         }
 
      } // end of 'finally' clause
      
      //Calling function to retrieve time
      getDateTime();
    
   }  // end of main
   
   //Function to retrieve time
    private static String getDateTime() {
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    Date date = new Date();
     System.out.println(dateFormat.format(date));
    return dateFormat.format(date);
    }// end function time
    
} // end of class definition
