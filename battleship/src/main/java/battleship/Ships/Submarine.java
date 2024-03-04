package battleship.Ships;

import battleship.Database.DB;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Submarine extends Ship {

    final int size = 3;
    final String id = "s";
    final String name = "Submarine";
    int hitCount = 0;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updateHitCount() {
        hitCount++;  
    }

    @Override
    public int getHitCount() {
        return hitCount;
    } 

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Image[] getImages() {
        return DB.Images.SUBMARINE;
    }

    @Override
    public Image[] getRotatedImages() {
        return DB.Images.SUBMARINE_ROTATED;
    }
    
}
