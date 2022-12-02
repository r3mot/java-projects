package library.Backend;

/* Book object to be used throughout program */

public class Book {

    private String title, subject;
    private int pubYear, numPages;
    private double rating;
    public Book sortBy;

    public Book() {
        title = null;
        subject = null;
        pubYear = 0;
        numPages = 0;
        rating = 0.0;
    };

    public Book(String title, String subject, int pubYear, int numPages, double rating) {
        this.title = title;
        this.subject = subject;
        this.pubYear = pubYear;
        this.numPages = numPages;
        this.rating = rating;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPubYear() {
        return this.pubYear;
    }

    public void setYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public int getNumPages() {
        return this.numPages;
    }

    public void setPages(int numPages) {
        this.numPages = numPages;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "\nTitle: " + title +
                "\tSubject: " + subject +
                "\tYear: " + pubYear +
                "\tPages: " + numPages +
                "\tRating: " + rating;
    }
}