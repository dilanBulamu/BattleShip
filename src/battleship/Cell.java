package battleship;
import java.util.*;

/*
 * Абстрактный класс клетки поля.
 */
abstract class Cell {
    // Коррдината клетки.
    protected Point point;
    // Тип клетки.
    protected types myType;
    // Доступные состояния клетки и их обозначения в String.
    protected final static Map<ShipCell.types, String> designations = fillDesignations();

    // Коструктор принимающий разположение клетки.
    Cell(Point point) {
        this.point = point;
    }

    // Доступные состояния клетки.
    enum types {
        TORPEDO,
        EMPTY,
        AMAZED,
        SHIP,
        DEFAULT
    }

    // Заполнение словаря с клетками и их обозначениями в String.
    private static Map<ShipCell.types, String> fillDesignations() {
        Map<ShipCell.types, String> designations = new HashMap<ShipCell.types, String>();

        designations.put(types.TORPEDO, "T");
        designations.put(types.EMPTY, "o");
        designations.put(types.AMAZED, "*");
        designations.put(types.SHIP, "1");
        designations.put(types.DEFAULT, "·");

        return designations;
    }

    // Возвращает состояние клетки в String.
    @Override
    public String toString() {
        return designations.get(myType);
    }

    // Стрельба по клетке и изменение ее состояния, в зависимости от текущего и типа стрельбы.
    public void shot(IShot mod) {
        System.out.println("Промах!");
        myType = types.EMPTY;
    }
}
