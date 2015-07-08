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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class JavaGetUrl {
   
//    private static String time ;
        private static String time;
 
   public static void main (String[] args) {
 
      //-----------------------------------------------------//
      //  Step 1:  Start creating a few objects we'll need.
      //-----------------------------------------------------//
     
      Connection con = null;
      Statement state = null;
      
      String url = "jdbc:postgresql://smartparking.cukkzosj78ld.eu-west-1.rds.amazonaws.com:5432/parking";
      String user = "thorsten";
      String password = "Test1234";
 
     
      String space, carpark, sptime;  
      
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
 
         //Initialise totalCarPark to 0 -> this value will be used to count later
         // the ID in parking table.
         int totalCarPark=0;
         
         
         //Establish database connection
         try {
             con = DriverManager.getConnection(url, user, password);
             state = con.createStatement();

         //Delete all values of carpark table to have always the same ID number
         //in the ID field correct set 
         String query1 = "Delete from carpark";
         state.executeUpdate(query1); 
         
         //Calling function to retrieve time and store the result in variable time
         time = getDateTime();
             
         //Read each record of the input stream until the end of line
         while ((s = dis.readLine()) != null) {
                
         //Retrive carpark name and spaces of one line of the whole XML output
         if(s.indexOf("carpark ")>=0)
         {
             //Increment total number of car park -> used for ID number in the 
             //database carpark
             totalCarPark++;
             
             //Search for name in String and places curser 6 positions after
             //the "n" of name to star reading in the carpark name
            int startIndex1 = s.indexOf("name")+6;
//            System.out.println("indexOf(name) = " + startIndex1); //delete this
            
             //Search for space in String and places curser 2 positions before
             //the "spaces"-> this will be the end of the car park name
            int endIndex1 = s.indexOf("spaces")-2;
//            System.out.println("indexOf(\"spaces\") = " + endIndex1); //delete this
//            System.out.println(s.substring(startIndex, endIndex));  //delete this
            
            //Create car park name from the start and end index
            carpark = (s.substring(startIndex1, endIndex1));
            System.out.println(carpark); //for testing purpose -> delete this

             //Search for space in String and places curser 8 positions after
             //the "spaces"-> this will be the start of the car park space number            
            int startIndex2 = s.indexOf("spaces")+8;
             //Search for ">" in String and places curser 1 positions before
             //the ">"-> this will be the end of the car park space number  
            int endIndex2 = s.indexOf(">")-1;  
             //Create car park space from the start and end index
            space = (s.substring(startIndex2, endIndex2));
            System.out.println(space); //delete this


            System.out.println("bevore insert into DB");//delete this
            System.out.println(time);//delete this
            
//          Insert data into the database
            String query = "INSERT INTO carpark(id,name,space,timedate) VALUES('" + totalCarPark + "','" + carpark + "','" + space + "','" + time + "'  )";
                state.executeUpdate(query);           

         }//end if 
         
//         else
//         {
////           System.out.println( );//delete this
//             
//         }// end else

         } //end while loop
         
         }//end of try
         
           catch (SQLException ex) {
             Logger lgr = Logger.getLogger(JavaGetUrl.class.getName());
             lgr.log(Level.SEVERE, ex.getMessage(), ex);
         }//end of catch 
         

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


   }  // end of main
   
   //Function to retrieve time
    private static String getDateTime() {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
     System.out.println(dateFormat.format(date));
    return dateFormat.format(date);
    }// end function time
    
} // end of class definition
