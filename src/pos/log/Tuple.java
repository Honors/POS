package pos.log;

/* tuple.java
 * interface that holds a list of two types
 *
 */

public abstract class Tuple<T, S>{
  private T t;
  private S s;

  public Tuple(T t, S s){
    this.t = t;
    this.s = s;
  }

  public T car(){ return(t); }
  public S cdr(){ return(s); }

}
