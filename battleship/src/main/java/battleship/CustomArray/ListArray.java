package battleship.CustomArray;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Custom data structre in the form of a 2D arraylist
 * Generic -> Allows for reusability
 * 
 * Custom methods to crate easier access to elements
 */
public class ListArray <T> {

    private ArrayList<ArrayList<T>> list;
    
    public ListArray(int row, int column, T type){
        
        list = new ArrayList<>();

        for(int i = 0; i < row; i++){
            ArrayList<T> temp = new ArrayList<T>(Collections.nCopies(column+1, type));
            list.add(temp);
        }
    }


    /**
     * 
     * @param what
     * @param row
     * @param column
     * 
     * Replaces the element at this location
     */
    public void add(T what, int row, int column){
        list.get(row).set(column, what);
    }

    public void remove(int row, int column){
        list.get(row).remove(column);
    }

    public T get(int row, int column){
        return list.get(row).get(column);
    }

    public void clear(){
        list.clear();
    }

    public ArrayList<ArrayList<T>> getList(){
        return this.list;
    }

    public int size(){
        return list.size();
    }

    public void reset(int row, int column, T what){

        list.clear();

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                list.get(i).add(j, what);
            }
        }
    }
    
}
