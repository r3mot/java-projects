package midterm.Backend;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class Generator {

    private Book[] randomBooks = new Book[50];
    private Set < Integer > yearsUsed = new HashSet < Integer > ();
    private int[] subjectCount = new int[5];
    private int max = 0;
    private String[] subjects = new String[] {
        "Programming",
        "Data Structures",
        "Algorithms",
        "Operating Systems",
        "Gaming"
    };


    public Book[] generateCatalog() {
        return generateRandomBooks();
    }

    public int getMax(){
        return max;
    }

    private int randomYear() {
        return (int)(1 + (Math.random() * (2022 - 1980) + 1980));
    }

    private int subjectIndex() {
        return (int)(Math.floor(Math.random() * (4 + 1)));
    }

    private int randomPages() {
        return (int)(1 + (Math.random() * (1000 - 50) + 50));
    }

    private double round(double input) {
        DecimalFormat df = new DecimalFormat("0.0");
        double rounded = Double.parseDouble(df.format(input));
        return rounded;
    }

    private double randomRating() {
        double rating = (0.1 + (Math.random() * (10 - 0.1)));
        return round(rating);
    }

    private int randomUniqueYear() {

        int year = randomYear();
        do {
            if (!yearsUsed.contains(year))
                break;
            year = randomYear();
        } while (true);

        yearsUsed.add(year);
        return year;
    }

    private String randomSubject() {

        int randomIndex = subjectIndex();
        do {
            if (subjectCount[randomIndex] != 5)
                break;
            randomIndex = subjectIndex();
        } while (true);

        subjectCount[randomIndex]++;
        return subjects[randomIndex];
    }


    private Book[] generateRandomBooks() {
        fillInitialBooks();
        fillTempBooks();
        return randomBooks;
    }

    private void fillInitialBooks() {
        for (int i = 0; i < 20; i++) 
            randomBooks[i] = addBook(i);
    }

    /* Workaround so GUI doesn't return null when iterating through book array */
    private void fillTempBooks() {
        for (int j = 20; j < 50; j++) 
            randomBooks[j] = new Book("", "", -1, -1, -1);
    }

    private Book addBook(int i) {
        Book book = new Book();
        book.setTitle("Book " + (i + 1));
        book.setSubject(randomSubject());
        book.setYear(randomUniqueYear());
        book.setPages(randomPages());
        book.setRating(randomRating());
        return book;

    }
}