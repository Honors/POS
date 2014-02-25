package pos.log;

import java.util.Map;
import java.util.HashMap;
import com.google.gson.*;

class JsonTool {
  public static final <X, Y> Tuple<X, Y> $(X a, Y b) {
    return new Tuple(a, b);
  }
}

class Tuple<T1, T2> {
  public T1 car;
  public T2 cdr;
  public Tuple(T1 a, T2 b) {
    car = a;
    cdr = b;
  }
}

class JSON<T1, T2> {
  public Map<T1, T2> _;
  public String toString() {
    Gson gson = new Gson();
    return gson.toJson(_);
  }
  public static final <T> T fromString(String string, Class<T> cls) {
    Gson gson = new Gson();
    return gson.fromJson(string, cls);
  }
  public JSON(Tuple<T1, T2>... pairs) {
    Map<T1,T2> map = new HashMap<T1,T2>();
    for( Tuple<T1,T2> pair: pairs ) {
      map.put(pair.car, pair.cdr);
    }
    _ = map; 
  }
}

