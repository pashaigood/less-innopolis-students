package less.android.Utils;

import com.google.gson.Gson;

public class Logger {
    public static void print(Object o) {
        System.out.print(new Gson().toJson(o));
    }
}
