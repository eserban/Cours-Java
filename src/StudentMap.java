import java.util.HashMap;

public class StudentMap extends HashMap<String, Student> {
    NoteList getNotes(String name) {
        return get(name).getNotes();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Student student : values()) {
            sb.append(student);
            sb.append("\n");
        }
        return sb.toString();
    }
}
