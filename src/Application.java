import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Application {
    private static final String FILENAME = "studentsCC.txt";
    private static final String SYMBOL_MOVE = "+";
    private File fileStudents;
    private final School school;
    private Scanner cli;
    private Student currentStudent;
    private StudentGroup currentGroup;

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    public Application() {
        cli = SingletonScanner.getInstance().getScanner();
        fileStudents = new File(Application.FILENAME);
        school = new School();

        try {
            school.load(fileStudents);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void run() {
        String name;
        currentGroup = null;
        currentStudent = null;
        do {
            System.out.print(school);

            TextInput command = new TextInput(cli, "");
            name = command.ask().trim();

            if (school.containsKey(name)) {
                System.out.println(school.get(name));
                currentGroup = school.get(name);
            } else {
                Student student = school.search(name);
                if (student != null) {
                    System.out.print(school.groupOf(student).getName() + "\t");
                    System.out.println(student);
                    currentStudent = student;
                } else if (name.startsWith(SYMBOL_MOVE) && currentStudent != null) {
                    moveStudent(name);
                } else if (!name.isBlank()) {
                    TextInput confirm = new TextInput(cli, "Nom inconnu. Créer ? [G/S/N]", "g", "s", "n", "G", "S", "N");
                    String res = confirm.ask();
                    if (res.equalsIgnoreCase("G")) {
                        createGroup(name);
                    } else if (res.equalsIgnoreCase("S")) {
                        createStudent(name);
                    }
                }
            }
        } while (!name.isBlank());

        save(school, fileStudents);
    }

    private void createGroup(String name) {
        currentGroup = new StudentGroup(name);
        school.put(name, currentGroup);
    }

    private void createStudent(String name) {
        if (currentGroup != null) {
            currentStudent = new Student(name);
            currentGroup.put(currentStudent.getShortName(), currentStudent);
        } else {
            System.err.println("Pas de groupe en cours");
        }
    }

    private void moveStudent(String name) {
        String groupName = name.substring(1);
        if (school.containsKey(groupName)) {
            school.remove(currentStudent);
            school.get(groupName).put(currentStudent.getShortName(), currentStudent);
        } else {
            System.err.println("Groupe de destination inconnu");
        }
    }

    private void save(Serializable serializable, File file) {
        try {
            serializable.save(file);
        } catch (IOException e) {
            System.err.println("Can't save file");
        }
    }

    private void askToAddNotes(Student student) {
        Confirmation confirm = new Confirmation(cli, "Voulez-vous ajouter des notes ?");
        if (confirm.askOK()) {
            NoteListInput noteListInput = new NoteListInput(cli, "Saisir les notes séparées par des espaces");
            Float[] notes = noteListInput.ask();
            student.getNotes().addAll(Arrays.asList(notes));
        }
    }
}
