package midterm.Backend.Sort;

import midterm.Backend.Book;

public class Filter extends QuickSort {

    private static int PAGES = 1;
    private int YEAR = 2;
    private int RATING = 3;
    private Book[] toSort;

    public Filter(Book[] toSort){
        this.toSort = toSort;
    }
    public Book[] filterPages(){
        return sort(toSort, PAGES);
    }

    public Book[] filterYear(){
        return sort(toSort, YEAR);
    }

    public Book[] filterRating() {
        return sort(toSort, RATING);
    }
}
