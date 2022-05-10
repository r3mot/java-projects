package social.Database.LocalStorage;

import java.util.ArrayList;
import java.util.Collections;

public class Data {
    
    private ArrayList<ArrayList<String>> list;
    
    public Data(int row, int column, String type){
        
        list = new ArrayList<>();

        for(int i = 0; i < row; i++){
            ArrayList<String> temp = new ArrayList<String>(Collections.nCopies(column+1, type));
            list.add(temp);
        }
    }

    public boolean nextNull(int index){
        return list.get(index).get(0).isEmpty();
    }

    public void add(String what, int row, int column){
        list.get(row).set(column, what);
    }

    public void remove(int row, int column){
        list.get(row).remove(column);
    }

    public String get(int row, int column){
        return list.get(row).get(column);
    }

    public void clear(){
        list.clear();
    }

    public ArrayList<ArrayList<String>> getList(){
        return this.list;
    }

    public int size(){
        return list.size();
    }

    public void reset(int row, int column, String what){

        list.clear();

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                list.get(i).add(j, what);
            }
        }
    }
}
