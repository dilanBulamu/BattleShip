package battleship;

/*
 * Интрефейс для типов стрельбы.
 */
public interface IShot {
    void attack(Board board, Point point);
}