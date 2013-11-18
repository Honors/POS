/* tuple.java
 * interface that holds a list of two types
 *
 */

public abstract class tuple<T, S>{
  private T t;
  private S s;

  public tuple(T t, S s){
    this.t = t;
    this.s = s;
  }

  public T car(){ return(t); }
  public S cdr(){ return(s); }

}
