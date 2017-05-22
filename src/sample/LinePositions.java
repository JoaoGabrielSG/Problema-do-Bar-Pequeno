package sample;

/**
 * Created by João Gabriel on 21/05/2017.
 */
public enum LinePositions {

    first(-200.0, 50.0),
    second(-200.0, 100.0),
    third(-200.0, 150.0),
    fourth(100.0, 200.0),
    fiveth(100.0, 250.0);

    double x;
    double y;

    LinePositions(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
