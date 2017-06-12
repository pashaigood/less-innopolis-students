package less.android;

import less.android.Models.Student;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

public class TmpMain {
    public static void main(String[] args) {
        Student student = new Student(
                "John",
                "Reese",
                "Conor",
                new Date(85, 9, 1),
                0l
        );

        for (Field field: student.getClass().getDeclaredFields()) {
            System.out.printf("name: %s type:%s\n", field.getName(), field.getType());
        }

        System.out.println("");

        for (Method method: student.getClass().getMethods()) {
            System.out.printf("name: %s type:%s parameterTypes:%s\n", method.getName(), method.getReturnType(), method.getParameterTypes().length);
        }

        System.out.println("");

        for (Annotation annotation: Student.class.getAnnotations()) {
            System.out.printf("type:%s name: %s\n", annotation.annotationType().toString(), annotation);
        }

        try {
            Field firstName = student.getClass().getDeclaredField("firstName");
            firstName.setAccessible(true);
            System.out.println(firstName.get(student));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Field id = student.getClass().getDeclaredField("id");
//            Field modifiersField = Field.class.getDeclaredField("modifiers");
            id.setAccessible(true);
            System.out.println(id.get(student));
//            modifiersField.setInt(student, id.getModifiers() & ~Modifier.FINAL);
            id.set(student, 12L);
            System.out.println(id.get(student));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
