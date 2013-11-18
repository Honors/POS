import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class POSLogger{
  private FileOutputStream fos;
 
  public void OutputPOS(String filename, boolean append){
    try{
      fos = new FileOutputStream(filename, append);
    }catch(FileNotFoundException e){
      System.err.println("FileNotFoundException: " + e.getMessage());
    }
  }

  public boolean write(char status, TimeStamp<Long, String> ts, String additional){
    try{
      String output = new String("(" + status + ") " + ts.toDateFormatted() + additional);
      fos.write(output.getBytes());
      fos.write((new String("\r\n")).getBytes());
    }catch(IOException e){
      return(false);
    }

    return(true);
  }
}
