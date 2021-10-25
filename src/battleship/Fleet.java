package battleship;

/*
 * Сгенерированный флот.
 */
public final class Fleet {
    private Ship[] ships;
    private int aliveCount;

    Fleet(Ship[] ships) {
        aliveCount = ships.length;
        this.ships = ships;
    }

    public Ship[] Ships() {
        return ships;
    }

    public void changeAliveCount() {
        --aliveCount;
    }

    public int AliveCount() {
        return aliveCount;
    }
}
