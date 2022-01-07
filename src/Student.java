public class Student {
    private String shortName, fullName;
    private NoteList notes;

    public Student(String fullName) {
        this.fullName = fullName;
        String[] fields = fullName.split(" ");
        if (fields.length >= 2) {
            shortName = fullName.split(" ")[1];
        } else {
            shortName = fullName;
        }
        notes = new NoteList();
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public NoteList getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s", fullName, notes);
    }
}
