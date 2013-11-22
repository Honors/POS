package pos.log;

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

}