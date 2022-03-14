package midterm.Backend.Sort;

import java.util.Comparator;
import midterm.Backend.Book;


public class Filter extends QuickSort {

    private Book[] toSort;

    public Filter(Book[] toSort) {
        this.toSort = toSort;
    }


//======================== Public access to filtering =====================================================================

    public Book[] filterPages() {
        Comparator < Book > byPages = new ComparePages();
        return quickSort(toSort, 0, toSort.length - 1, byPages);
    }

    public Book[] filterYear() {
        Comparator < Book > byYear = new CompareYear();
        return quickSort(toSort, 0, toSort.length - 1, byYear);
    }

    public Book[] filterRating() {
        Comparator < Book > byRating = new CompareRating();
        return quickSort(toSort, 0, toSort.length - 1, byRating);
    }


//======================= Comparators used in quicksort implementation =====================================================

    private static class CompareYear implements Comparator < Book > {
        @Override
        public int compare(Book a, Book b) {
            return a.getPubYear() - b.getPubYear();
        }
    }
    private static class ComparePages implements Comparator < Book > {
        @Override
        public int compare(Book a, Book b) {
            return a.getNumPages() - b.getNumPages();
        }
    }
    private static class CompareRating implements Comparator < Book > {
        @Override
        public int compare(Book a, Book b) {
            return Double.compare(b.getRating(), a.getRating());
        }
    }
}