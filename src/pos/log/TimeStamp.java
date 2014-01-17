package pos.log;

import java.text.SimpleDateFormat;
/* TimeStamp.java
 * object that holds current system time and UPC
 *
 */
import java.util.Date;

public class TimeStamp<L, S> extends Tuple<L,S>{
  public TimeStamp(L l, S s){
    super(l,s);
  }

  @SuppressWarnings("deprecation")
public String toDateFormatted(){
    return(new String("[" + (new Date((this.car()).toString())).toString() + "] [" + (this.cdr()).toString()) + "]");
  }

  public static String simpleDateAndTime(){
	  SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	  Date date = new Date();
	  return f.format(date);
  }
  
  public static String simpleDate(){
	  SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
	  Date date = new Date();
	  return f.format(date);
  }
  
  public static String sanitizedDateandTime() {
	  SimpleDateFormat f = new SimpleDateFormat("MM-dd-yyyy_HH.mm.ss");
	  Date date = new Date();
	  return f.format(date);
  }
}
