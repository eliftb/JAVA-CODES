import java.util.Scanner;

class Book {
    String title;
    String author;
    Book next;
    Book previous;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}

class DoublyLinkedList {
    private Book head, tail;
    private int size;

    public void addAtBeginning(String title, String author) {
        Book newBook = new Book(title, author);
        if (head == null) {
            tail = newBook;
            head = newBook;
        } else {
            newBook.next = head;
            head.previous = newBook;
            head = newBook;
        }
        size++;
    }

    public void addAtEnd(String title, String author) {
        Book newBook = new Book(title, author);
        if (tail == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.previous = tail;
            tail = newBook;
        }
        size++;
    }

    public void addAtMiddle(String title, String author) {
        if (size < 2) {
            addAtEnd(title, author);
            return;
        }

        Book newBook = new Book(title, author);
        Book current = head;
        int steps = (size - 1) / 2;

        for (int i = 0; i < steps; i++) {
            current = current.next;
        }

        newBook.next = current.next;
        newBook.previous = current;
        if (current.next != null) {
            current.next.previous = newBook;
        }
        current.next = newBook;

        if (newBook.next == null) {
            tail = newBook;
        }
        size++;
    }

    public void deleteByTitle(String title) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        Book current = head;
        while (current != null) {
            if (current.title.equalsIgnoreCase(title)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) head.previous = null;
                } else if (current == tail) {
                    tail = current.previous;
                    tail.next = null;
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                size--;
                return;
            }
            current = current.next;
        }
        System.out.println("No book found with title '" + title + "'!");
    }

    public void deleteMiddle() {
        if (head == null) {
            System.out.println("Nothing to delete!");
            return;
        }

        Book current = head;
        int steps = size / 2;

        for (int i = 0; i < steps; i++) {
            current = current.next;
        }

        if (current.previous != null) {
            current.previous.next = current.next;
        } else {
            head = current.next;
        }

        if (current.next != null) {
            current.next.previous = current.previous;
        } else {
            tail = current.previous;
        }
        size--;
    }

    public void listBooks() {
        Book current = head;
        System.out.println("\n=== LIBRARY ===");
        while (current != null) {
            System.out.println("✧ " + current.title + " - " + current.author);
            current = current.next;
        }
    }

    public void listBooksReverse() {
        Book current = tail;
        System.out.println("\n=== REVERSED LIST ===");
        while (current != null) {
            System.out.println("> " + current.title);
            current = current.previous;
        }
    }
}

public class Library {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList books = new DoublyLinkedList();

        while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Add at beginning");
            System.out.println("2. Add at end");
            System.out.println("3. Add at middle");
            System.out.println("4. Delete book");
            System.out.println("5. Delete from middle");
            System.out.println("6. List books");
            System.out.println("7. List books in reverse");
            System.out.println("8. Exit");
            System.out.print("-> Your choice: ");
            scanner.nextLine();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Author: ");
                    books.addAtBeginning(title, scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Title: ");
                    title = scanner.nextLine();
                    System.out.print("Author: ");
                    books.addAtEnd(title, scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Title: ");
                    title = scanner.nextLine();
                    System.out.print("Author: ");
                    books.addAtMiddle(title, scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Book to delete: ");
                    books.deleteByTitle(scanner.nextLine());
                    break;
                case 5:
                    books.deleteMiddle();
                    break;
                case 6:
                    books.listBooks();
                    break;
                case 7:
                    books.listBooksReverse();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
