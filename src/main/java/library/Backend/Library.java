package library.Backend;

import library.Api.Model.Book;

public class Library {

    BookFactory random = new BookFactory();

    private Book[] catalog; // array of book objects

    public Book[] getCatalog() {
        return catalog;
    }


    /* Randomly generates the first 20 books in the catalog */
    public void initialize() {
        catalog = new Book[20];
        catalog = random.generateCatalog();
    }
}
