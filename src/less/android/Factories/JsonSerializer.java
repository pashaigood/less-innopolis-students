package less.android.Factories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import less.android.Interfaces.Serializer;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class JsonSerializer extends ArrayList implements Serializer {
    static private String EXP = ".json";
    static private String PATH_TO_COLLECTION = "./resources/collections/json/";
    protected String FILE_NAME;

    public void list() {
        try (
                JsonReader dataReader = new JsonReader(new FileReader(PATH_TO_COLLECTION + FILE_NAME + EXP));
        ) {

            this.clear();
            this.addAll(new Gson().fromJson(dataReader, this.getClass()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try (
                FileOutputStream fileUserList = new FileOutputStream(PATH_TO_COLLECTION + FILE_NAME + EXP);
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
