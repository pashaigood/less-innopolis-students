package less.android.Factories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import less.android.Collections.Students;
import less.android.Interfaces.Serializer;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class JsonSerializer extends ArrayList implements Serializer {
    static private String FILE_NAME = "./resources/collections/json/";
    protected String fileName;

    public void list() {
        try (
                JsonReader dataReader = new JsonReader(new FileReader(FILE_NAME + fileName + ".json"));
        ) {

            this.clear();
            this.addAll(new Gson().fromJson(dataReader, Students.class));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        System.out.println(fileName);
        System.out.println(this.fileName);
        try (
                FileOutputStream fileUserList = new FileOutputStream(FILE_NAME + fileName);
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
