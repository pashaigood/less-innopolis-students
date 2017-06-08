package less.android;

import less.android.Collections.Students;
import less.android.Models.Group;
import less.android.Models.Student;
import java.time.Month;
import java.util.Date;

public class Main {


    public static void main(String[] args) {
        testStudentsCollection();
    }

    private static void testStudentsCollection() {
        int prevSize;

        Group tmpGroup = new Group("Android 6");
        Students userList = Students.getInstance();

        userList.add(new Student(
                "Pavel",
                "Andreevich",
                "Belugin",
                new Date(90, Month.SEPTEMBER.getValue(), 30),
                tmpGroup.getId()
        ));

        userList.add(new Student(
                "Ivanov",
                "Ivan",
                "Ivanovich",
                new Date(85, Month.JANUARY.getValue(), 1),
                tmpGroup.getId()
        ));

        prevSize = userList.size();
        try {
            userList.commit();
        } catch (Exception e) {
            System.err.println("Collection should save its data.");
        }
        userList.clear();

        try {
            userList.list();
        } catch (Exception e) {
            System.err.println("Collection should load its data.");
        }

        if (userList.size() != prevSize) {
            System.err.println("Collection should restore its data.");
        }
    }
}
