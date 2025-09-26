import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Notes Application ===");
            System.out.println("1. Create Note");
            System.out.println("2. View Notes");
            System.out.println("3. Delete All Notes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createNote(sc);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    deleteNotes();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }

    private static void createNote(Scanner sc) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            System.out.print("Enter your note: ");
            String note = sc.nextLine();
            writer.write(note);
            writer.newLine();
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error while saving note: " + e.getMessage());
        }
    }

    private static void viewNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Your Notes ---");
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Create one first!");
        } catch (IOException e) {
            System.out.println("Error while reading notes: " + e.getMessage());
        }
    }

    private static void deleteNotes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            // overwrite with empty content
            System.out.println("All notes deleted.");
        } catch (IOException e) {
            System.out.println("Error while deleting notes: " + e.getMessage());
        }
    }
}
