package org.oosd.UI.sprite;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Star extends Polygon {
    public Star() {this(10,5,5);}
    public Star (double outerRadius, double innerRadius, int numPoints){
        super();
        double angelStep = Math.PI/numPoints;

        for (int i=0; i<numPoints * 2; i++){
            double radius = (i % 2 == 0) ? outerRadius: innerRadius;
            double angel = i * angelStep - Math.PI / 2;
            double x = Math.cos(angel) * radius;
            double y = Math.sin(angel) * radius;
            getPoints().addAll(x, y);
        }
        setFill (Color.GOLD);
        setStroke(Color.ORANGE);
    }
}
