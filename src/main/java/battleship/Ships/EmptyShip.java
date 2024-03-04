package battleship.Ships;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class EmptyShip extends Ship {

    final Image[] images = {};
    final Image[] images_rotated = { };

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getHitCount() {
        return -1;
    }

    @Override
    public void updateHitCount() {
        
    }

    @Override
    public Image[] getImages() {
        return images;
    }

    @Override
    public Image[] getRotatedImages() {
        return images_rotated;
    }
    
}
