package sample;

/**
 * Created by João Gabriel on 21/05/2017.
 */
public enum LinePositions {

    first(100.0, 100.0),
    second(200.0, 100.0),
    third(300.0, 100.0);

    double x;
    double y;

    LinePositions(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
