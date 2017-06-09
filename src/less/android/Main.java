package less.android;

import less.android.Collections.Students;
import less.android.Models.Group;
import less.android.Models.Student;
import less.android.Utils.Logger;

import java.time.Month;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        testStudentsCollection();
        testStudentsDesiaralizeCollection();
    }

    private static void testStudentsDesiaralizeCollection() {
        Students testedStudents = Students.getInstance();

        try {
            testedStudents.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (testedStudents.size() == 0) {
            System.err.println("Stored items should be loaded.");
        }
    }

    private static void testStudentsCollection() {
        int prevSize;

        Group testedGroup = new Group("Android 6");
        Students testedStudents = Students.getInstance();

        testedStudents.add(new Student(
                "Pavel",
                "Andreevich",
                "Belugin",
                new Date(90, Month.SEPTEMBER.getValue(), 30),
                testedGroup.getId()
        ));

        testedStudents.add(new Student(
                "Ivanov",
                "Ivan",
                "Ivanovich",
                new Date(85, Month.JANUARY.getValue(), 1),
                testedGroup.getId()
        ));

        prevSize = testedStudents.size();
        try {
            testedStudents.commit();
        } catch (Exception e) {
            System.err.println("Collection should save its data.");
        }
        testedStudents.clear();

        try {
            testedStudents.list();
        } catch (Exception e) {
            System.err.println("Collection should load its data.");
        }

        if (testedStudents.size() != prevSize) {
            System.err.println("Collection should restore its data.");
        }

        Logger.print(testedStudents);
    }
}
