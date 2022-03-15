package midterm.Backend.Sort;

import java.util.Comparator;

import midterm.Backend.Book;

/* Custome quicksort algo - uses custom comparators to determine which obj property to sort*/

public class QuickSort {

    public static Book[] quickSort(Book[] data, int low, int high, Comparator < Book > comparator) {

        int i = low;
        int j = high;
        Book pivot = data[low + (high - low) / 2];

        while (i <= j) {
            while (i < high && comparator.compare(data[i], pivot) < 0) {
                i++;
            }
            while (j > low && comparator.compare(data[j], pivot) > 0) {
                j--;
            }

            if (i <= j) {
                swap(data, i, j);
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(data, low, j, comparator);
        if (i < high)
            quickSort(data, i, high, comparator);

        return data;
    }

    private static void swap(Book[] data, int i, int j) {
        Book temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}