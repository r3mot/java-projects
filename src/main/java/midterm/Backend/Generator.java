package midterm.Backend;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class Generator {
    
    private Set<Integer> yearsUsed = new HashSet<Integer>(); // Stores random year to check for duplicates
    private int[] subjectCount = new int[5]; // Used to determine the number of books assigned to a subject
    private String[] subjects = new String[] 
    {
        "Programming", 
        "Data Structures", 
        "Algorithms", 
        "Operating Systems", 
        "Gaming"
    };

     /* Returns an array of Random Book Objects */
     public Book[] getRandomBooks(){
        return generateRandomBooks();
    }

    private int randomPages(){
        return (int)(1 + (Math.random() * (1000 - 50) + 50));        
    }

    private double randomRating(){
        double rating = (0.1 + (Math.random() * (10 - 0.1))); 
        DecimalFormat df = new DecimalFormat("0.0");
        double roundedToOneDecimal = Double.parseDouble(df.format(rating));
        return roundedToOneDecimal;
    }

    private int randomYear(){
        return (int)(1 + (Math.random() * (2022 - 1980) + 1980));       
    }

    private int getRandomIndex(){
        return (int)(Math.floor(Math.random() * (4 + 1)));             
    }

    /* Generates a random & unique year */
    private int uniqueYear(){

        int year = randomYear();

        while(true)
        {
            if(!yearsUsed.contains(year))
                break;
            year = randomYear();
        }

        yearsUsed.add(year);
        return year;
    }

    /* Generates a random & uniqe subject */
    private String uniqueSubject(){ 

        int randomIndex = getRandomIndex();
    
        while(true)
        {
            if(subjectCount[randomIndex] != 5)
                break;
            randomIndex = getRandomIndex();
        }

        subjectCount[randomIndex]++;
        return subjects[randomIndex];
    }

    private Book[] generateRandomBooks(){

        Book[] randomBooks = new Book[50];

        for(int i = 0; i < 20; i++){
                randomBooks[i] = new Book
                (
                    "Book "+ (i+1), 
                    uniqueSubject(), 
                    uniqueYear(), 
                    randomPages(), 
                    randomRating()
                );
        }

        /* Workaround to ensure GUI Booklist doesn't return null object */
        for(int j = 20; j < 50; j++){  
            randomBooks[j] = new Book("temp","temp",-1,-1,-1);
        }
        return randomBooks;
    }
}
