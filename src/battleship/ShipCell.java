package battleship;

/*
 * Класс реализующий интерфейс клетки. Является клеткой c корблем.
 */
public final class ShipCell extends Cell {
    private int shipNumber;

    ShipCell(Point point, int shipNumber) {
        super(point);
        myType = types.SHIP;
        this.shipNumber = shipNumber;
    }

    @Override
    public void shot (IShot mod) {
        System.out.println("Попали!");
        if (mod instanceof SimpleMod) {
            myType = types.AMAZED;
        } else if (mod instanceof TorpedoMod) {
            myType = types.TORPEDO;
        } else {
            myType = types.AMAZED;
        }
    }

    public int ShipNumber() {
        return shipNumber;
    }

    public void regenerate() { myType = types.SHIP; }
}
