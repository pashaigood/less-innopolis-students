package less.android.Factories;

import less.android.Interfaces.Serializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ByteSerializer extends ArrayList implements Serializer {
    static private String FILE_NAME = "./resources/collections/byte/";
    protected String fileName;

    public void list() {
        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME + fileName));
                ) {
            this.addAll((ArrayList) ois.readObject());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try (
                ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(FILE_NAME + fileName));
                ) {
            oout.writeObject(this);
            oout.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
