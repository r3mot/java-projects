package midterm.Backend;

public class Library {

    Generator random = new Generator();
    
    private Book[] catalog; // array of book objects
    private int bookCount = 20; // Initial array has 20 randomly generated books

    public Book[] getCatalog(){
        return catalog;
    }

    public Book addBook(String t, String s, int p, int y, double d){
        Book newBook = new Book(t, s, p, y, d);
        catalog[bookCount] = newBook;
        bookCount++;
        return newBook;
    }

    /* Randomly generates the first 20 books in the catalog*/
    public void initialize(){
        catalog = new Book[50];
        catalog = random.generateCatalog();
    }
}
