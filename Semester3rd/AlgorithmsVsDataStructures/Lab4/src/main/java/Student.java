/**
 * Created by alesia on 9/29/17.
 * 10 - Прізвище, кількість усіх
 занять, кількість пропу-
 щених занять
 */
public class Student {
    private String firstName;
    private int lessonCount;
    private int lessonMissedCount;

    public Student(String firstName, int lessonCount, int lessonMissedCount) {
        this.firstName = firstName;
        this.lessonCount = lessonCount;
        this.lessonMissedCount = lessonMissedCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public int getLessonMissedCount() {
        return lessonMissedCount;
    }

    public void setLessonMissedCount(int lessonMissedCount) {
        this.lessonMissedCount = lessonMissedCount;
    }

    public double calculateMissedLessonPercent() { return ((double)lessonMissedCount/lessonCount) * 100; }

    @Override
    public String toString() {
        return String.format("Student:   " +
                "firstName = %1$-15s " +
                "lessonCount = %2$-10d " +
                "lessonMissedCount = %3$-10d " +
                "missed lessons in %5$-2s = %4$.2f"
                , firstName
                , lessonCount
                , lessonMissedCount
                , calculateMissedLessonPercent()
                , "%");
    }
}
