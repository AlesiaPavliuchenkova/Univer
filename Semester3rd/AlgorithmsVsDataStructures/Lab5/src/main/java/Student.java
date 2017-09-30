/**
 * Created by alesia on 9/29/17.
 * 10 - Прізвище, ім’я, курс, стать, ознака проживання в гуртожитку (так/ні)
 */

public class Student {
    private String lastName;
    private String firstName;
    private int course;
    private String gender;
    private boolean isFromDormitory;

    public Student(String lastName, String firstName, int course, String gender, boolean isFromDormitory) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.course = course;
        this.gender = gender;
        this.isFromDormitory = isFromDormitory;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public boolean isFromDormitory() {
        return isFromDormitory;
    }

    public void setFromDormitory(boolean fromDormitory) {
        this.isFromDormitory = fromDormitory;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("Student:     " +
                        "lastName = %1$-15s " +
                        "firstName = %2$-15s " +
                        "course = %3$-5d " +
                        "gender = %4$-10s " +
                        "Does student live in dormitory = %5$-6s"
                        ,lastName
                        ,firstName
                        ,course
                        ,gender
                        ,isFromDormitory ? "YES" : "NO");
    }
}
