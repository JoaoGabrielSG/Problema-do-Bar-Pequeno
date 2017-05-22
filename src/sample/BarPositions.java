package sample;

/**
 * Created by João Gabriel on 21/05/2017.
 */
public enum BarPositions {
    first(100.0, 50.0),
    second(100.0, 100.0),
    third(100.0, 150.0),
    fourth(100.0, 200.0),
    fiveth(100.0, 250.0);

    double x;
    double y;

    BarPositions(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
