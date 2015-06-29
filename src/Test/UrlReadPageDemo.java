package Test;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
 
public class UrlReadPageDemo {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.dublincity.ie/dublintraffic/cpdata.xml");
 
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter("thorst.txt"));
 
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
 
            reader.close();
            writer.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}