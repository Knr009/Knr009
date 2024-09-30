import java.util.ArrayList;
import java.util.Scanner;

// Book Class
class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println("You have successfully borrowed " + title);
        } else {
            System.out.println("Sorry, this book is currently borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("You have successfully returned " + title);
        } else {
            System.out.println("This book wasn't borrowed.");
        }
    }
}

// Library Class
class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " added to the library.");
    }

    public void showAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isBorrowed()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.borrowBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

// Main Class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Add some initial books to the library
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));

        while (running) {
            System.out.println("\n1. Show Available Books");
            System.out.println("\n2. Borrow a Book");
            System.out.println("\n3. Return a Book");
            System.out.println("\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    library.showAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter the title of the book to borrow: ");
                    String bookToBorrow = scanner.nextLine();
                    library.borrowBook(bookToBorrow);
                    break;
                case 3:
                    System.out.print("Enter the title of the book to return: ");
                    String bookToReturn = scanner.nextLine();
                    library.returnBook(bookToReturn);
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }

        scanner.close();
    }
}
