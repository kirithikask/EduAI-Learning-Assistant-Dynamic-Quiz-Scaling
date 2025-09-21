import java.util.Scanner;

class User {
    String name;
    String role;
    String email;
    String password;  
    int[] scores = new int[10];
    int scoreCount = 0;

    User(String n, String r, String e, String p) {
        name = n;
        role = r;
        email = e;
        password = p;
    }

    void addScore(int s) {
        scores[scoreCount++] = s;
    }

    void progressView() {
        if (scoreCount == 0) {
            System.out.println("No quiz taken yet!");
            return;
        }
        int total = 0;
        for (int i = 0; i < scoreCount; i++) {
            System.out.println("Attempt " + (i+1) + " : " + scores[i]);
            total += scores[i];
        }
        System.out.println("Average: " + (total / scoreCount));
    }
}

class Note {
    String title;
    String text;

    Note(String t, String x) { 
        title = t; 
        text = x; 
    }
}

class Quiz {
    String question;
    String[] options = new String[4];
    int correct;

    Quiz(String q, String[] o, int c) {
        question = q; 
        options = o; 
        correct = c;
    }
}

public class eduAi {
    static Scanner sc = new Scanner(System.in);
    static User[] users = new User[50];
    static int userCount = 0;

    static Note[] notes = new Note[50];
    static int noteCount = 0;

    static Quiz[] questions = new Quiz[50];
    static int qCount = 0;

    static User current = null;

    public static void main(String[] args) {
        users[userCount++] = new User("admin", "admin", "admin@mail.com", "123");

        while (true) {
            System.out.println("\nType: login | register | quit");
            String act = sc.nextLine();
            if (act.equals("login")) login();
            else if (act.equals("register")) register();
            else if (act.equals("quit")) break;
            else System.out.println("Invalid choice");
        }
    }

    static void login() {
        System.out.print("Email: ");
        String e = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        for (int i = 0; i < userCount; i++) {
            if (users[i].email.equals(e) && users[i].password.equals(p)) {
                current = users[i];
                System.out.println("Welcome " + current.name + " (" + current.role + ")");
                if (current.role.equals("admin")) adminMenu();
                else studentMenu();
                return;
            }
        }
        System.out.println("User not found. Please register!");
    }

    static void register() {
        System.out.print("Enter your name: ");
        String n = sc.nextLine();
        System.out.print("Enter your role (student/admin): ");
        String r = sc.nextLine();
        System.out.print("Enter your email: ");
        String e = sc.nextLine();
        System.out.print("Enter your password: ");
        String p = sc.nextLine();

        users[userCount++] = new User(n, r, e, p);
        System.out.println("Registered Successfully!");
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu: addnote | shownotes | addq | showq | logout");
            String c = sc.nextLine();
            if (c.equals("addnote")) addNote();
            else if (c.equals("shownotes")) showNotes();
            else if (c.equals("addq")) addQuestion();
            else if (c.equals("showq")) showQuestions();
            else if (c.equals("logout")) break;
            else System.out.println("Invalid option");
        }
    }

    static void studentMenu() {
        while (true) {
            System.out.println("\nStudent Menu: search | notes | quiz | progress | logout");
            String c = sc.nextLine();
            if (c.equals("search")) searchNote();
            else if (c.equals("notes")) showNotes();
            else if (c.equals("quiz")) takeQuiz();
            else if (c.equals("progress")) current.progressView();
            else if (c.equals("logout")) break;
            else System.out.println("Invalid option");
        }
    }

    static void addNote() {
        System.out.print("Title: ");
        String t = sc.nextLine();
        System.out.print("Text: ");
        String b = sc.nextLine();
        notes[noteCount++] = new Note(t, b);
        System.out.println("Note added.");
    }

    static void showNotes() {
        if (noteCount == 0) {
            System.out.println("No notes yet.");
            return;
        }
        for (int i = 0; i < noteCount; i++) {
            System.out.println("## " + notes[i].title);
            System.out.println(notes[i].text);
        }
    }

    static void searchNote() {
        System.out.print("Keyword: ");
        String k = sc.nextLine().toLowerCase();
        boolean found = false;
        for (int i = 0; i < noteCount; i++) {
            if (notes[i].title.toLowerCase().contains(k) || notes[i].text.toLowerCase().contains(k)) {
                System.out.println("## " + notes[i].title);
                System.out.println(notes[i].text);
                found = true;
            }
        }
        if (!found) System.out.println("No notes found.");
    }

    static void addQuestion() {
        System.out.print("Question: ");
        String q = sc.nextLine();
        String[] opts = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Option " + (i+1) + ": ");
            opts[i] = sc.nextLine();
        }
        System.out.print("Correct option (1-4): ");
        int a = Integer.parseInt(sc.nextLine());

        questions[qCount++] = new Quiz(q, opts, a);
        System.out.println("Question added.");
    }

    static void showQuestions() {
        for (int i = 0; i < qCount; i++) {
            System.out.println((i+1) + ". " + questions[i].question);
            for (int j = 0; j < 4; j++) {
                System.out.println("  " + (j+1) + ") " + questions[i].options[j]);
            }
            System.out.println("Answer: " + questions[i].correct);
        }
    }

    static void takeQuiz() {
        if (qCount == 0) {
            System.out.println("No quiz available. Ask admin to add questions.");
            return;
        }
        int score = 0;
        for (int i = 0; i < qCount; i++) {
            System.out.println("Q" + (i+1) + ": " + questions[i].question);
            for (int j = 0; j < 4; j++) {
                System.out.println("  " + (j+1) + ") " + questions[i].options[j]);
            }
            System.out.print("Your answer: ");
            int ans = Integer.parseInt(sc.nextLine());
            if (ans == questions[i].correct) score++;
        }
        System.out.println("Your Score: " + score + "/" + qCount);
        current.addScore(score);
        System.out.println("Great! Keep learning!");
    }
}
