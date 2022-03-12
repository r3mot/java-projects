package midterm.Backend.Sort;

import midterm.Backend.Book;

public class QuickSort {
    
    private final int PAGES = 1;
    private final int YEAR = 2;
    private final int RATING = 3;

    private Book[] array;
    private int length;

    private void quickSort(int low, int high, int sortBy){
        int i = low;
        int j = high;
        Book pivot = array[low + (high - low) / 2];

        while(i <= j){

            /* Determines what is to be sorted */
            switch(sortBy){

                case PAGES:
                    while(array[i].getNumPages() < pivot.getNumPages()){
                        i++;
                    }
                    while(array[j].getNumPages() > pivot.getNumPages()){
                        j--;
                    }
                    break;

                case YEAR:
                    while(array[i].getPubYear() < pivot.getPubYear()){
                        i++;
                    }
                    while(array[j].getPubYear() > pivot.getPubYear()){
                        j--;
                    }
                    break;

                case RATING:
                    while(array[i].getRating() > pivot.getRating()){
                        i++;
                    }
                    while(array[j].getRating() < pivot.getRating()){
                        j--;
                    }
                    break;
                }

            
            if(i <= j){
                swap(i, j);
                i++;
                j--;
            }
        }

        if(low < j)
            quickSort(low, j, sortBy);
        if(i < high)
            quickSort(i, high, sortBy);
    }

    private void swap(int i, int j){
        Book temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Book[] sort(Book[] input, int sortBy){
        if(input == null || input.length == 0)
            return null;
            
        this.array = input;
        length = input.length;
        quickSort(0, length - 1, sortBy);

        return array;
    }
}
