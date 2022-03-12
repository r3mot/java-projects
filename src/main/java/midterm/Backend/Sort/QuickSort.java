package midterm.Backend.Sort;

import java.util.Comparator;

import midterm.Backend.Book;

public class QuickSort {
    

    public static Book[] quickSort(Book[] data, int low, int high, Comparator<Book> comp){

        int i = low;
        int j = high;
        Book pivot = data[low + (high - low) / 2];

        while(i <= j){
            while(i < high && comp.compare(data[i] , pivot) < 0){
                i++;
            }
            while(j > low && comp.compare(data[j], pivot) > 0){
                j--;
            }

            if(i <= j){
                swap(data, i, j);
                i++;
                j--;
            }
        }

        if(low < j)
            quickSort(data, low, j, comp);
        if(i < high)
            quickSort(data, i, high, comp);

        return data;
    }

    private static void swap(Book[] data, int i , int j){
        Book temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
