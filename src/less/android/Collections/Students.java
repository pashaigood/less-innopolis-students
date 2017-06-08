package less.android.Collections;


import less.android.Factories.ByteSerializer;
import less.android.Factories.JsonSerializer;

// TODO: Create parent class and interface for CRUD collection.
public class Students extends ByteSerializer {
    private static Students ourInstance = new Students();

    public static Students getInstance() {
        return ourInstance;
    }

    private Students() {
        fileName = "students";
    }
}
