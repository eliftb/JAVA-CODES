📚 Doubly Linked List Library Management System
Java
Data Structures

A complete Java console application for managing book records using custom doubly linked list implementation with advanced operations.

✨ Features
📖 Book Operations
Insertion:

At beginning (O(1))

At end (O(1))

At middle position (O(n))

Deletion:

By book title (O(n))

Middle book removal (O(n))

Traversal:

Forward display (head to tail)

Backward display (tail to head)

🖥️ User Interface
Interactive console menu

Input validation

Continuous operation until exit

🧠 Core Implementation
Data Structure
java
class Book {
    String title;
    String author;
    Book next;      // Forward pointer
    Book previous;  // Backward pointer
}
Key Algorithms
Middle Insertion:

java
public void addAtMiddle(String title, String author) {
    if (size < 2) {
        addAtEnd(title, author);
        return;
    }
    
    Book newBook = new Book(title, author);
    Book current = head;
    int steps = (size - 1) / 2;  // Smart middle calculation
    
    // Traverse to middle
    for (int i = 0; i < steps; i++) {
        current = current.next;
    }
    
    // Pointer manipulation
    newBook.next = current.next;
    newBook.previous = current;
    if (current.next != null) {
        current.next.previous = newBook;
    }
    current.next = newBook;
    
    if (newBook.next == null) {
        tail = newBook;  // Update tail if inserted at end
    }
    size++;
}
Middle Deletion:

java
public void deleteMiddle() {
    if (head == null) return;
    
    Book current = head;
    int steps = size / 2;  // Precise middle location
    
    for (int i = 0; i < steps; i++) {
        current = current.next;
    }
    
    // Bypass the middle node
    if (current.previous != null) {
        current.previous.next = current.next;
    } else {
        head = current.next;  // Update head if deleting first
    }
    
    if (current.next != null) {
        current.next.previous = current.previous;
    } else {
        tail = current.previous;  // Update tail if deleting last
    }
    size--;
}
🚀 Getting Started
Prerequisites
Java JDK 17+

Git (optional)

Installation & Running
bash
# Clone repository
git clone https://github.com/yourusername/library-management.git
cd library-management/src

# Compile and run
javac Library.java
java Library
🧪 Testing Recommendations
Basic Operations:

text
1. Add 5 books using all insertion methods
2. Verify forward/backward traversal
3. Test middle deletion
4. Delete specific books by title
Edge Cases:

Empty list operations

Single-element list operations

Duplicate title handling

📜 Project Structure
/src
  ├── Library.java        # Main program
  ├── DoublyLinkedList.java # Core logic
  └── Book.java          # Data model
📌 Key Learning Points
Pointer manipulation in doubly linked lists

Time complexity optimization

Robust error handling

Clean OOP architecture

🤝 How to Contribute
Fork the repository

Create a feature branch

Submit a pull request


