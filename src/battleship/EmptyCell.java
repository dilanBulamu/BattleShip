package battleship;

/*
 * Класс реализующий интерфейс клетки. Является пустой клеткой (без корбля).
 */
public class EmptyCell extends Cell {
    EmptyCell(Point point) {
        super(point);
        myType = types.DEFAULT;
    }
}
