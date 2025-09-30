import java.util.*;

class User {
    String name, email, password, role;
    List<Integer> quizScores = new ArrayList<>();

    User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    void addScore(int score) {
        quizScores.add(score);
    }

    void showProgress() {
        if (quizScores.isEmpty()) {
            System.out.println("No quizzes attempted yet.");
            return;
        }
        System.out.println("\n--- Progress for " + name + " ---");
        for (int i = 0; i < quizScores.size(); i++) {
            System.out.println("Quiz " + (i + 1) + ": " + quizScores.get(i));
        }
        double avg = quizScores.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Average Score: " + avg);
        
    }
}

class Note {
    String title, content;
    Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

class Question {
    String question;
    String[] options;
    int correctAnswer;

    Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class java {
    static Scanner sc = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<Note> notes = new ArrayList<>();
    static List<Question> quiz = new ArrayList<>();
    static User currentUser = null;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Login  2. Register  3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: login(); break;
                case 2: register(); break;
                case 3: System.exit(0);
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        for (User u : users) {
            if (u.email.equals(email) && u.password.equals(password)) {
                currentUser = u;
                System.out.println("Welcome " + u.name + " (" + u.role + ")");
                if (u.role.equals("admin")) adminMenu();
                else if (u.role.equals("teacher")) teacherMenu();
                else studentMenu();
                return;
            }
        }
        System.out.println("Invalid credentials. Try again.");
    }

    static void register() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Role (student/teacher/admin): ");
        String role = sc.nextLine().toLowerCase();

        users.add(new User(name, email, password, role));
        System.out.println("registerd!");
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu: 1.Add Note 2.View Notes 3.Add Quiz 4.View Quiz 5.Logout");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: addNote(); break;
                case 2: viewNotes(); break;
                case 3: addQuizQuestion(); break;
                case 4: viewQuizQuestions(); break;
                case 5: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void teacherMenu() {
        while (true) {
            System.out.println("\nTeacher Menu: 1.Add Note 2.Add Quiz 3.Logout");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: addNote(); break;
                case 2: addQuizQuestion(); break;
                case 3: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void studentMenu() {
        while (true) {
            System.out.println("\nStudent Menu: 1.View Notes 2.Attempt Quiz 3.View Progress 4.Logout");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: viewNotes(); break;
                case 2: attemptQuiz(); break;
                case 3: currentUser.showProgress(); break;
                case 4: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void addNote() {
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Content: ");
        String content = sc.nextLine();
        notes.add(new Note(title, content));
        System.out.println("notes added");
    }

    static void viewNotes() {
        if (notes.isEmpty()) {
            System.out.println("empty.");
            return;
        }
        for (Note n : notes) {
            System.out.println("Title: " + n.title);
            System.out.println("Content: " + n.content);
            System.out.println("-------------------");
        }
    }

    static void addQuizQuestion() {
        System.out.print("Question: ");
        String question = sc.nextLine();
        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Option " + (i+1) + ": ");
            options[i] = sc.nextLine();
        }
        System.out.print("Correct option (1-4): ");
        int correct = sc.nextInt();
        sc.nextLine();

        quiz.add(new Question(question, options, correct));
        System.out.println("Question added!");
    }

    static void viewQuizQuestions() {
        if (quiz.isEmpty()) {
            System.out.println("No questions.");
            return;
        }
        for (int i = 0; i < quiz.size(); i++) {
            Question q = quiz.get(i);
            System.out.println((i+1) + ". " + q.question);
            for (int j = 0; j < 4; j++) {
                System.out.println("  " + (j+1) + ". " + q.options[j]);
            }
        }
    }

    static void attemptQuiz() {
        if (quiz.isEmpty()) {
            System.out.println("No quiz available.");
            return;
        }
        int score = 0;
        for (int i = 0; i < quiz.size(); i++) {
            Question q = quiz.get(i);
            System.out.println("Q" + (i+1) + ": " + q.question);
            for (int j = 0; j < 4; j++) {
                System.out.println("  " + (j+1) + ". " + q.options[j]);
            }
            System.out.print("Your answer: ");
            int ans = sc.nextInt();
            sc.nextLine();
            if (ans == q.correctAnswer) score++;
        }
        System.out.println("Your Score: " + score + "/" + quiz.size());
        currentUser.addScore(score);
        System.out.println("keep learning");
    }
}
