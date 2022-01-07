import java.util.Arrays;

public class Group {
    private Student[] students;

    public Group(Student[] students){
        this.students = new Student[students.length];
        for(int i = 0 ; i < students.length ; i++)
            this.students[i] = students[i];
    }

    @Override
    public String toString() {
        return "";
    }
}
