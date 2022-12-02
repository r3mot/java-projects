package library.Api.Model;

import java.util.UUID;

public class Book {

    private final UUID id;
    private String title;
    private String subject;
    private int pubYear;
    private int numPages;
    private double rating;

    public Book(UUID id, String title, String subject, int pubYear, int numPages, double rating) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.subject = subject;
        this.pubYear = pubYear;
        this.numPages = numPages;
        this.rating = rating;
    }

    public UUID getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubject() {
        return this.subject;
    }

    public int getPubYear() {
        return this.pubYear;
    }

    public int getNumPages() {
        return this.numPages;
    }

    public double getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", title='" + getTitle() + "'" +
                ", subject='" + getSubject() + "'" +
                ", pubYear='" + getPubYear() + "'" +
                ", numPages='" + getNumPages() + "'" +
                ", rating='" + getRating() + "'" +
                "}";
    }

}
