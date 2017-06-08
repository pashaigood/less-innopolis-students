package less.android.Models;

import less.android.Utils.Generator;

public class Journal {
    public static boolean PRESENTED = true;
    public static boolean NOT_PRESENTED = false;

    private Long id = Generator.generateId();
    private Long lessonId;
    private Long studentId;
    private boolean present;

    public Journal(Long lessonId, Long studentId) {
        this.lessonId = lessonId;
        this.studentId = studentId;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Generator.generateHash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Journal)) {
            return false;
        }

        return ((Journal) obj).getId().equals(id);
    }
}
