package less.android.Collections;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import less.android.Models.Student;

import java.io.*;
import java.util.ArrayList;


// TODO: Create parent class and interface for CRUD collection.
public class Students extends ArrayList<Student> {
    static private final String FILE_NAME = "./resources/collections/students.json";

    private static Students ourInstance = new Students();

    public static Students getInstance() {
        return ourInstance;
    }

    private Students() {
    }

    public void list() {
        try (
                JsonReader dataReader = new JsonReader(new FileReader(FILE_NAME));
        ) {

            this.clear();
            this.addAll(new Gson().fromJson(dataReader, Students.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try (
                FileOutputStream fileUserList = new FileOutputStream(FILE_NAME);
                OutputStreamWriter fileUserListStream = new OutputStreamWriter(fileUserList);
        ) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            fileUserListStream.write(gson.toJson(this));
            fileUserListStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
