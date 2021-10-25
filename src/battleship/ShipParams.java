package battleship;

/*
 * Параметры корабля.
 */
public final class ShipParams {
    private int size;
    private Point point;
    private boolean isHorizontally;

    ShipParams(int size, Point point, boolean isHorizontally) {
        this.size = size;
        this.point = point;
        this.isHorizontally = isHorizontally;
    }

    public int Size() {
        return size;
    }

    public Point Point() {
        return point;
    }

    public boolean IsHorizontally() {
        return isHorizontally;
    }
}
