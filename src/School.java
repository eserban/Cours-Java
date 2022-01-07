import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class School extends HashMap<String, StudentGroup> implements Serializable {
    public void load(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String line;
        String[] fields;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            fields = line.split("\t");

            String groupName = fields[0];
            String studentName = fields[1];

            if (!containsKey(groupName)) {
                put(groupName, new StudentGroup(groupName));
            }

            Student student = new Student(studentName);
            get(groupName).put(student.getShortName(), student);

            try {
                fields = fields[2].split(" ");
                for (int i = 0; i < fields.length; i++) {
                    student.getNotes().add(Float.valueOf(fields[i]));
                }
            } catch (NumberFormatException e) {
                System.err.println("Can't read student " + student.getFullName());
            }
        }
        scanner.close();
    }

    public void save(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (StudentGroup g : values()) {
            for (Student s : g.values()) {
                fileWriter.write(String.format("%s\t%s\r\n", g.getName(), s));
            }
        }
        fileWriter.close();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : keySet()) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    public Student search(String name) {
        for (String group : keySet()) {
            if (get(group).containsKey(name)) {
                return get(group).get(name);
            }
        }
        return null;
    }

    public StudentGroup groupOf(Student student) {
        for (StudentGroup group : values()) {
            if (group.containsValue(student))
                return group;
        }
        return null;
    }

    public void remove(Student currentStudent) {
        for (StudentGroup group : values()) {
            group.remove(currentStudent.getShortName());
        }
    }
}
