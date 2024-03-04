package library.Utility;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Animation {

    public static void animationSlideLeft(Pane pane, Pane initial) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(pane);
        slide.setToX(-961);
        slide.play();
        initial.setTranslateX(0);
        setVisibility(pane, initial, true);
        slide.setOnFinished((e -> {
        }));
    }

    public static void animationSlideRight(Pane pane, Pane initial) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.8));
        slide.setNode(pane);
        slide.setToX(0);
        slide.play();
        initial.setTranslateX(0);
        setVisibility(pane, initial, false);
        slide.setOnFinished((e -> {
        }));
    }

    private static void setVisibility(Pane pane, Pane initial, boolean update) {
        pane.setVisible(update);
        initial.setVisible(!update);
    }

}
