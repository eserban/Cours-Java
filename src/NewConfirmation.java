import java.util.Scanner;

public class NewConfirmation extends TextInput {
    public NewConfirmation(Scanner scanner, String prompt) {
        super(scanner, prompt + " (G/S/N)", "G", "S", "N", "g", "s", "n");
    }

    public boolean askGroup() {
        return ask().equalsIgnoreCase("G");
    }
    public boolean askStudent() {
        return ask().equalsIgnoreCase("S");
    }
}