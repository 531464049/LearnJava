package src.book1.bounce;

import java.awt.geom.*;

public class Ball {
    private static final int XSIZE = 15;
    private static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 5;
    private double dy = 5;

    public void move(Rectangle2D bouns) {
        x += dx;
        y += dy;
        if (x < bouns.getMinX()) {
            x = bouns.getMinX();
            dx = -dx;
        }
        if (x + XSIZE >= bouns.getMaxX()) {
            x = bouns.getMaxX() - XSIZE;
            dx = -dx;
        }
        if (y < bouns.getMinY()) {
            y = bouns.getMinY();
            dy = -dy;
        }
        if (y + YSIZE >= bouns.getMaxY()) {
            y = bouns.getMaxY() - YSIZE;
            dy = -dy;
        }
    }

    public Ellipse2D getShape() {
        return new Ellipse2D.Double(this.x, this.y, XSIZE, YSIZE);
    }
}
